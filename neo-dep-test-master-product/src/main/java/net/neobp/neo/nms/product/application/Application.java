package net.neobp.neo.nms.product.application;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.internal.adaptor.BasicLocation;
import org.eclipse.core.runtime.internal.adaptor.EclipseAdaptorMsg;
import org.eclipse.core.runtime.internal.adaptor.Locker;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

    private static final String LOCK_MODE = "java.io";
    private static final String LOCK_FILE_NAME = "nms.lock";

    private Locker locker; // The locker for the lock
    private final File lockerRoot = new File(System.getProperty("user.home"));

    /**
     * Saves the state of the storage manager and releases any locks held.
     */
    private void release() {
        if (locker == null)
            return;
        locker.release();
    }

    private boolean lock() throws IOException {
        final File lockFile = new File(lockerRoot, Application.LOCK_FILE_NAME);
        if (locker == null) {
            locker = BasicLocation.createLocker(lockFile, Application.LOCK_MODE);
            if (locker == null)
                throw new IOException(EclipseAdaptorMsg.fileManager_cannotLock);
        }
        final boolean locked = locker.lock();
        return locked;
    }

    @Override
    public Object start(final IApplicationContext context) throws Exception {
        final boolean locked = lock();
        final WorkbenchAdvisor workbenchAdvisor;
        final Display display;

        display = PlatformUI.createDisplay();

        if (!locked) {
            System.exit(0);
        }

        // presentation to allow editors to have tabs without close buttons and other niceties
        // Move this to a default preferences file
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PRESENTATION_FACTORY_ID, "nmsPresentationFactory");

        try {
            Platform.addLogListener(new NmsLogListener());
            return null;
        }
        finally {
            display.dispose();
        }
    }

    @Override
    public void stop() {
        final IWorkbench workbench = PlatformUI.getWorkbench();

        if (workbench == null) {
            return;
        }
        final Display display = workbench.getDisplay();
        display.syncExec(new Runnable() {

            @Override
            public void run() {
                if (!display.isDisposed()) {
                    workbench.close();
                }
            }
        });

        release();
    }

}
