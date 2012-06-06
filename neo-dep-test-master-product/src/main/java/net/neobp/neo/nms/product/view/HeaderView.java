package net.neobp.neo.nms.product.view;


import java.util.logging.Logger;

import net.neobp.neo.nms.product.view.sync.SyncProgressMonitorDialog;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;

/**
 * The header view including 
 * logo, sync button, logged in user name and logout action
 * @author Roland Kärcher, Rolf Kulemann, Bernd Schneider
 */
public class HeaderView 
extends ViewPart
// For some initialization issues, we can not implement ApplicationListener here. Thats why we use StatusMessageEventListenerDelegate 
{
	// the id of the toolbar view
    public static final String ID = HeaderView.class.getName();
    
	private BundleContext bundleContext;
	
	private Label loggedInUserLabel;

	private Image neoMobileSuiteLogoImage;

	private Image neoMobileSuiteImage;

	private Image buttonHelpImage;

	private Image buttonSyncImage;

	private Image iconSmallImage;

	private Image iconLargeImage;

	private Hyperlink logoutHyperlink;

	private Button syncButton;
	
	
	private boolean syncInProgress = false;
	
	private IProgressMonitor progressMonitor = null;
	private SyncProgressMonitorDialog progressMonitorDialog = null;
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		final Composite userComposite;
		final GridLayout userCompositeGridLayout = new GridLayout(2, false);
		FormData fd;
		final Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		final FormLayout layout = new FormLayout();

		
		parent.setLayout(layout);
		parent.setBackground(white);

		final Label neoMobileSuiteLabel = new Label(parent, SWT.NONE);
		neoMobileSuiteLabel.setImage(HeaderView.this.neoMobileSuiteLogoImage);
		neoMobileSuiteLabel.setBackground(white);

		fd = new FormData();
		fd.top = new FormAttachment(50, -23);
		fd.left = new FormAttachment(0, 10);
		// fd.bottom = new FormAttachment(30, 0);
		// fd.right = new FormAttachment(40, 0);

		neoMobileSuiteLabel.setLayoutData(fd);


		fd = new FormData();
		fd.bottom = new FormAttachment(60, -5);
		fd.right = new FormAttachment(100, -5);

		fd = new FormData();
		fd.bottom = new FormAttachment(100, -5);
		fd.right = new FormAttachment(100, -5);


		final GridData gridData = new GridData();
		userComposite = toolkit.createComposite(parent);
		userComposite.setLayout(userCompositeGridLayout);
		userComposite.setLayoutData(fd);

		gridData.widthHint = 100;
		gridData.horizontalAlignment = SWT.RIGHT;
		logoutHyperlink.setLayoutData(gridData);


		createUserLabel(userComposite, gridData, white);

		displayLoggedInUser();

		// set shell icon
		parent.getShell().setImages( new Image[] {iconSmallImage, iconLargeImage} );
	}

	private void createUserLabel(final Composite parent, final GridData layoutData, final Color bgColor) {
		loggedInUserLabel = new Label(parent, SWT.RIGHT);
		loggedInUserLabel.setBackground(bgColor);
		loggedInUserLabel.setLayoutData(layoutData);
	}

	private void displayLoggedInUser() {
		if(loggedInUserLabel == null) {
			return;
		}

	}
	protected void onUserLoggedOutEvent() {
		logoutHyperlink.setText("");
		logoutHyperlink.setEnabled(false);
		syncButton.setEnabled(false);
		displayLoggedInUser();
	}
	

	private boolean checkAuth() {
		/*try {
			getAuthenticationService().loginWithPasswordReset(getUserService().getCurrentUser().getId(), getUserService().getCurrentUser().getPassword());
		} catch (final WrongPasswordException e) {
			showPasswordResetMessageDialog();
			return false;
		} catch (final PasswordExpiredException e) {
			showPasswordChangeMessageDialog();
			return false;
		} catch(final IOException e) {
			showNotOnlineMessageDialog(e);
			return false;
		} catch(final IllegalStateException e) {
			// ok, since client seems to be initial
		}*/
		
		return true;
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{

	}

	@Override
	public void dispose()
	{
		this.neoMobileSuiteLogoImage.dispose();
		this.neoMobileSuiteImage.dispose();
		this.buttonHelpImage.dispose();
		this.buttonSyncImage.dispose();
		this.iconSmallImage.dispose();
		this.iconLargeImage.dispose();
		super.dispose();
	}

	/**
	 * @return the bundleContext
	 */
	public BundleContext getBundleContext() {
		return bundleContext;
	}

	protected void setSyncInProgress(final boolean syncInProgress) {
		this.syncInProgress = syncInProgress;
	}

	protected boolean isSyncInProgress() {
		return syncInProgress;
	}

}