package net.neobp.neo.nms;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


/**
 * @author Roland Kärcher, Rolf Kulemann
 */
public abstract class AbstractMobileSuitePerspective implements IPerspectiveFactory
{
    private PerspectiveLayoutStrategy perspectiveLayoutStrategy;
    
    @Override
	public void createInitialLayout(final IPageLayout layout) 
    {
    	getPerspectiveLayoutStrategy().createInitialLayout(layout);
    }

	/**
	 * @param perspectiveLayoutStrategy the perspectiveLayoutStrategy to set
	 */
	public void setPerspectiveLayoutStrategy(final PerspectiveLayoutStrategy perspectiveLayoutStrategy) {
		this.perspectiveLayoutStrategy = perspectiveLayoutStrategy;
	}

	/**
	 * @return the perspectiveLayoutStrategy
	 */
	public PerspectiveLayoutStrategy getPerspectiveLayoutStrategy() {
		return perspectiveLayoutStrategy;
	}

}