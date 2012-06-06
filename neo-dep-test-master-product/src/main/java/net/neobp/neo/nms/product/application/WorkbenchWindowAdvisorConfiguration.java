package net.neobp.neo.nms.product.application;

import org.eclipse.swt.graphics.Point;

/**
 * @author Rolf Kulemann
 *
 */
public interface WorkbenchWindowAdvisorConfiguration {
	public Point getInitialSize();
	public String getTitle();
	public boolean isShowCoolBar();
	public boolean isShowStatusLine();
	public boolean isShowPerspectiveBar();
}
