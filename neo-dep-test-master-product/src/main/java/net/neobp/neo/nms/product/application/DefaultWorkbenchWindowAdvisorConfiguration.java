package net.neobp.neo.nms.product.application;

import java.util.ResourceBundle;

import org.eclipse.swt.graphics.Point;

/**
 * @author Rolf Kulemann
 *
 */
public class DefaultWorkbenchWindowAdvisorConfiguration implements WorkbenchWindowAdvisorConfiguration {
	private Point initialSize;
	private boolean showCoolBar;
	private boolean showStatusLine;
	private boolean showPerspectiveBar;
	
	protected final ResourceBundle getResourceBundleMessageSource() {
		return null;
	}
	
	/**
	 * @return the initialSize
	 */
	@Override
	public Point getInitialSize() {
		return initialSize;
	}
	/**
	 * @param initialSize the initialSize to set
	 */
	public void setInitialSize(final Point initialSize) {
		this.initialSize = initialSize;
	}
	/**
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return getResourceBundleMessageSource().getString("application.title");
	}
	/**
	 * @return the showCoolBar
	 */
	@Override
	public boolean isShowCoolBar() {
		return showCoolBar;
	}
	/**
	 * @param showCoolBar the showCoolBar to set
	 */
	public void setShowCoolBar(final boolean showCoolBar) {
		this.showCoolBar = showCoolBar;
	}
	/**
	 * @return the showStatusLine
	 */
	@Override
	public boolean isShowStatusLine() {
		return showStatusLine;
	}
	/**
	 * @param showStatusLine the showStatusLine to set
	 */
	public void setShowStatusLine(final boolean showStatusLine) {
		this.showStatusLine = showStatusLine;
	}
	/**
	 * @return the showPerspectiveBar
	 */
	@Override
	public boolean isShowPerspectiveBar() {
		return showPerspectiveBar;
	}
	/**
	 * @param showPerspectiveBar the showPerspectiveBar to set
	 */
	public void setShowPerspectiveBar(final boolean showPerspectiveBar) {
		this.showPerspectiveBar = showPerspectiveBar;
	}
}
