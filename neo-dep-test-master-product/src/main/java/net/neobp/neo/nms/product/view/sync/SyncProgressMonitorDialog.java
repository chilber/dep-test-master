package net.neobp.neo.nms.product.view.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SyncProgressMonitorDialog extends ProgressMonitorDialog {

	private static class Message {
		private String message;
		private int type;

		public Message(final String message , final int type) {
			setMessage(message);
			setType(type);
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
		/**
		 * @param message the message to set
		 */
		public void setMessage(final String message) {
			this.message = message;
		}
		/**
		 * @return the type
		 */
		public int getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(final int type) {
			this.type = type;
		}
	}

	private final Collection<Message> messages = new ArrayList<Message>(256);
	private Browser messageBrowser;

	private Button ok;
	private boolean okPressed = false;

	public static final int MESSAGE_TYPE_ERROR = 0;
	public static final int MESSAGE_TYPE_INFO = 1;

	;
	/**
	 * @param parent
	 */
	public SyncProgressMonitorDialog(final Shell parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.jface.dialogs.ProgressMonitorDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite dialogAreaComposite = (Composite)super.createDialogArea(parent);
		final GridData gd = new GridData();

		gd.horizontalAlignment = 4;
		gd.verticalAlignment = 0;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.widthHint = GridData.FILL;
		gd.heightHint = 0;
		gd.horizontalSpan = 20;

		messageBrowser = new Browser(parent.getShell(), SWT.NONE);
		messageBrowser.setLayoutData(gd);

		messageBrowser.setVisible(false);
		dialogAreaComposite.layout();

		/*gd.heightHint = 100;
		messageBrowser.setVisible(true);
		dialogAreaComposite.layout();*/

		return dialogAreaComposite;
	}

	public void setOkEnabled(final boolean enabled) {
		ok.setEnabled(enabled);
		ok.setFocus();
	}

	public boolean isOkEnabled() {
		return ok.getEnabled();
	}
	
	public void setErrorText(final String label, final String message) {
		super.messageLabel.setText(label);
		super.subTaskLabel.setText(message);
		super.imageLabel.setImage(getErrorImage());
	}
	
	public void setSuccessText(final String label, final String message) {
		if(label != null) {
			super.messageLabel.setText(label);
		}
		if(message != null) {
			super.subTaskLabel.setText(message);
		}
	}
	
	/**
	 * Appends a message to the detail area
	 * @param text
	 * @param messageType
	 */
	public void appendMessage(final String text, final int messageType) {
		final StringBuilder infoBuffer;
		final List<Message> reversedMessages = new ArrayList<Message>();
		
		messages.add(new Message(text, messageType));
		
		reversedMessages.addAll(messages);
		
		Collections.reverse(reversedMessages);
		
		if(messageBrowser == null) {
			return;
		}

		infoBuffer = new StringBuilder(4096);

		infoBuffer.append("<html style=\"padding:0px; margin: 0px;\"><body style=\"padding:0px; margin: 0px;\">");

		for(final SyncProgressMonitorDialog.Message message : reversedMessages) {
			String backgroundColor = "d1e1ef";

			switch(message.getType()) {
			case MESSAGE_TYPE_ERROR:
				backgroundColor = "efadb9";
			default: 
			}

			infoBuffer.append("<p style=\"background-color:#" + backgroundColor + "; border-bottom: 1px solid #afd1ef; font-family:sans-serif; font-size: 12px; margin:0px; padding: 2px;\">" + message.getMessage() + "</p>");
		}

		infoBuffer.append("</body></html>");

		messageBrowser.setText(infoBuffer.toString());
	}

	/**
	 * @see org.eclipse.jface.dialogs.ProgressMonitorDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(final Composite parent) {
		createDetailButton(parent);
		createOKButton(parent);
		parent.pack();
	}

	protected void createOKButton(final Composite parent)
	{
		// ok button has ID 0
		ok = createButton(parent, 0, 
				IDialogConstants.OK_LABEL, true);

		if (this.arrowCursor == null) {
			this.arrowCursor = new Cursor(ok.getDisplay(), 0);
		}
		ok.setCursor(this.arrowCursor);
		ok.setEnabled(false);
		ok.addMouseListener(new MouseAdapter() {

			/**
			 * @see org.eclipse.swt.events.MouseAdapter#mouseUp(org.eclipse.swt.events.MouseEvent)
			 */
			@Override
			public void mouseUp(final MouseEvent e) {
				okPressed = true;
			}
		});
	}

	protected void createDetailButton(final Composite parent)
	{
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());


	}

	/**
	 * @return the okPressed
	 */
	public boolean isOkPressed() {
		return okPressed;
	}
}