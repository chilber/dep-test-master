package net.neobp.neo.nms.product.view.sync;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Rolf Kulemann
 *
 */
public class SyncProgressMonitor implements IProgressMonitor {
	private final IProgressMonitor progressMonitorDelegate;
	private boolean enabled = true; 
	
	public SyncProgressMonitor(final IProgressMonitor progressMonitorDelegate) {
		this.progressMonitorDelegate = progressMonitorDelegate;
	}

	/**
	 * @param name
	 * @param totalWork
	 * @see org.eclipse.core.runtime.IProgressMonitor#beginTask(java.lang.String, int)
	 */
	@Override
	public synchronized void beginTask(final String name, final int totalWork) {
		if(isEnabled()) {
			progressMonitorDelegate.beginTask(name, totalWork);
		}
	}

	/**
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor#done()
	 */
	@Override
	public void done() {
		progressMonitorDelegate.done();
	}

	/**
	 * @param work
	 * @see org.eclipse.core.runtime.IProgressMonitor#internalWorked(double)
	 */
	@Override
	public void internalWorked(final double work) {
		progressMonitorDelegate.internalWorked(work);
	}

	/**
	 * @return
	 * @see org.eclipse.core.runtime.IProgressMonitor#isCanceled()
	 */
	@Override
	public boolean isCanceled() {
		return progressMonitorDelegate.isCanceled();
	}

	/**
	 * @param value
	 * @see org.eclipse.core.runtime.IProgressMonitor#setCanceled(boolean)
	 */
	@Override
	public void setCanceled(final boolean value) {
		progressMonitorDelegate.setCanceled(value);
	}

	/**
	 * @param name
	 * @see org.eclipse.core.runtime.IProgressMonitor#setTaskName(java.lang.String)
	 */
	@Override
	public synchronized void setTaskName(final String name) {
		if(isEnabled()) {
			progressMonitorDelegate.setTaskName(name);
		}
	}

	/**
	 * @param name
	 * @see org.eclipse.core.runtime.IProgressMonitor#subTask(java.lang.String)
	 */
	@Override
	public synchronized void subTask(final String name) {
		if(isEnabled()) {
			progressMonitorDelegate.subTask(name);
		}
	}

	/**
	 * @param work
	 * @see org.eclipse.core.runtime.IProgressMonitor#worked(int)
	 */
	@Override
	public void worked(final int work) {
		progressMonitorDelegate.worked(work);
	}

	/**
	 * @param enabled the enabled to set
	 */
	public synchronized void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

}
