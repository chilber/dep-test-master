package net.neobp.neo.nms.product.view.perspective;

import net.neobp.neo.nms.AbstractMobileSuitePerspective;

import org.eclipse.ui.IPageLayout;

/**
 * @author Rolf Kulemann
 */
public class UserSettingsPerspective
extends AbstractMobileSuitePerspective
{
	public static final String ID = "userSettings";

	@Override
	public void createInitialLayout(final IPageLayout layout) 
	{
		super.createInitialLayout(layout);
		layout.setEditorAreaVisible(false);
	}
}
