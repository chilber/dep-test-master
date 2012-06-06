package net.neobp.neo.nms.product.application;


import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @author Roland Kärcher, Rolf Kulemann
 */
public class ApplicationWorkbenchAdvisor 
extends WorkbenchAdvisor 
implements WorkbenchWindowAdvisorConfigurationSupport {

	private Class<?> workbenchWindowAdvisorClass;
	private String initialSystemPerspectiveId;
	private String initialApplicationPerspectiveId;
	private WorkbenchWindowAdvisorConfiguration workbenchWindowAdvisorConfiguration;
	
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
		return null;
	}

	@Override
	public String getInitialWindowPerspectiveId() {
		// TODO This is an ugly hack, since we can not access the user service from here ?!!
		if(System.getProperty("nms.user", null) == null)
			return getInitialSystemPerspectiveId();
		else 
			return getInitialApplicationPerspectiveId();
	}

	/**
	 * @param workbenchWindowAdvisorClass the workbenchWindowAdvisorClass to set
	 */
	public void setWorkbenchWindowAdvisorClass(
			final Class<?> workbenchWindowAdvisorClass) {
		this.workbenchWindowAdvisorClass = workbenchWindowAdvisorClass;
	}

	/**
	 * @return the workbenchWindowAdvisorClass
	 */
	public Class<?> getWorkbenchWindowAdvisorClass() {
		return workbenchWindowAdvisorClass;
	}

	/**
	 * @param initialSystemPerspectiveId the initialSystemPerspectiveId to set
	 */
	public void setInitialSystemPerspectiveId(final String initialSystemPerspectiveId) {
		this.initialSystemPerspectiveId = initialSystemPerspectiveId;
	}

	/**
	 * @return the initialSystemPerspectiveId
	 */
	public String getInitialSystemPerspectiveId() {
		return initialSystemPerspectiveId;
	}

	/**
	 * @param initialApplicationPerspectiveId the initialApplicationPerspectiveId to set
	 */
	public void setInitialApplicationPerspectiveId(
			final String initialApplicationPerspectiveId) {
		this.initialApplicationPerspectiveId = initialApplicationPerspectiveId;
	}

	/**
	 * @return the initialApplicationPerspectiveId
	 */
	public String getInitialApplicationPerspectiveId() {
		return initialApplicationPerspectiveId;
	}

	/**
	 * @param workbenchWindowAdvisorConfiguration the workbenchWindowAdvisorConfiguration to set
	 */
	@Override
	public void setWorkbenchWindowAdvisorConfiguration(
			final WorkbenchWindowAdvisorConfiguration workbenchWindowAdvisorConfiguration) {
		this.workbenchWindowAdvisorConfiguration = workbenchWindowAdvisorConfiguration;
	}

	/**
	 * @return the workbenchWindowAdvisorConfiguration
	 */
	public WorkbenchWindowAdvisorConfiguration getWorkbenchWindowAdvisorConfiguration() {
		return workbenchWindowAdvisorConfiguration;
	}
}
