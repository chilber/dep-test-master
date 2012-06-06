package net.neobp.neo.nms.product.view.navigation;

/**
 * @author Rolf Kulemann
 */
public interface NavigationListener {
	public boolean createFirstLevelItem(final String id);
	public boolean createSecondLevelItem(final String id);
}
