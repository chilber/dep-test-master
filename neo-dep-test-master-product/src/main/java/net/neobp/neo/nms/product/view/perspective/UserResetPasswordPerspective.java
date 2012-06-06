package net.neobp.neo.nms.product.view.perspective;

import net.neobp.neo.nms.AbstractMobileSuitePerspective;

import org.eclipse.ui.IPageLayout;

/**
 * @author Rolf Kulemann
 */
public class UserResetPasswordPerspective
extends AbstractMobileSuitePerspective
{
	public static final String ID = "userResetPassword";

	@Override
	public void createInitialLayout(final IPageLayout layout) 
	{
		super.createInitialLayout(layout);
		layout.setEditorAreaVisible(false);
	}
}
