package net.neobp.neo.nms.product.application;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @author Roland Kärcher, Rolf Kulemann
 */
public class ApplicationWorkbenchWindowAdvisor
    extends WorkbenchWindowAdvisor
    implements WorkbenchWindowAdvisorConfigurationSupport
{
	private WorkbenchWindowAdvisorConfiguration workbenchWindowAdvisorConfiguration;
	
    public ApplicationWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer)
    {
        super(configurer);
    }
    
    @Override
    public ActionBarAdvisor createActionBarAdvisor(final IActionBarConfigurer configurer)
    {
        return new ApplicationActionBarAdvisor(configurer);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void preWindowOpen()
    {
        final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        
        configurer.setTitle(getWorkbenchWindowAdvisorConfiguration().getTitle());
        configurer.setInitialSize(getWorkbenchWindowAdvisorConfiguration().getInitialSize());
        configurer.setShowCoolBar(getWorkbenchWindowAdvisorConfiguration().isShowCoolBar());
        configurer.setShowStatusLine(getWorkbenchWindowAdvisorConfiguration().isShowStatusLine());
        configurer.setShowPerspectiveBar(getWorkbenchWindowAdvisorConfiguration().isShowPerspectiveBar());
    }
    
    @Override
	public void postWindowOpen() {
		final IStatusLineManager statusline = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
	}
    
	/**
	 * @return the workbenchWindowAdvisorConfiguration
	 */
	public WorkbenchWindowAdvisorConfiguration getWorkbenchWindowAdvisorConfiguration() {
		return workbenchWindowAdvisorConfiguration;
	}

	/**
	 * @param workbenchWindowAdvisorConfiguration the workbenchWindowAdvisorConfiguration to set
	 */
	@Override
	public void setWorkbenchWindowAdvisorConfiguration(
			final WorkbenchWindowAdvisorConfiguration workbenchWindowAdvisorConfiguration) {
		this.workbenchWindowAdvisorConfiguration = workbenchWindowAdvisorConfiguration;
	}
}
