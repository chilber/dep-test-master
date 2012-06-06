package net.neobp.neo.nms;

import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleContext;


/**
 * @author Rolf Kulemann
 */
public abstract class AbstractUIPlugin 
extends org.eclipse.ui.plugin.AbstractUIPlugin {
	private BundleContext context;

	public static final String IMG_PATH = "image/";

    
	@Override
    public void start(final BundleContext context)
    throws Exception
    {
        super.start(context);        
        this.context = context;
        
        
        /*context.addServiceListener(new ServiceListener() {
			@Override
			public void serviceChanged(final ServiceEvent event) {
				if(event.getType() != ServiceEvent.REGISTERED) {
					return;
				}
				processExtensionRegistry();
			}
		}, "(org.springframework.context.service.name=" + context.getBundle().getSymbolicName() + ")");
		*/
    }
	
	/*private void processExtensionRegistry() {
		final ApplicationContext applicationContext = OsgiUtil.getApplicationContext(getContext());
    	
    	if(applicationContext != null) {
    		final Map<String, EditorRegistryDecorator> decorators = applicationContext.getBeansOfType(EditorRegistryDecorator.class);
    		
    		for(final EditorRegistryDecorator decorator : decorators.values()) {
    			decorator.decorate();
    		}
    	}
	}*/
	
	@Override
    public void stop(final BundleContext context)
    throws Exception
    {
        try
        {
        }
        finally
        {
            super.stop(context);
        }
    }
	
	public Image getImage(final String key)
    {
        return getImageRegistry().get(key);
    }

    public ImageDescriptor getImageDescriptor(final String key)
    {
        return getImageRegistry().getDescriptor(key);
    }
    
    public void registerImage(final String key, final String fileName) {
    	registerImage(IMG_PATH, getImageRegistry(), key, fileName);
    }
    
    protected final void registerImage(final String imagePath, final ImageRegistry registry, final String key, final String fileName)
    {
        final IPath path = new Path(imagePath + fileName);
        final URL url = FileLocator.find(getBundle(), path, null);

        if(url != null)
        {
            final ImageDescriptor desc = ImageDescriptor.createFromURL(url);
            registry.put(key, desc);
        }
    }

    /**
     * Returns the workspace instance.
     */
    public static IWorkspace getWorkspace()
    {
        return ResourcesPlugin.getWorkspace();
    }
    
    /**
	 * @return the context
	 */
	public BundleContext getContext() {
		return context;
	}
}
