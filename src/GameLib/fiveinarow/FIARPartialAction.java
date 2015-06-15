package GameLib.fiveinarow;

import GameLib.archi.Action;
import GameLib.archi.PartialAction;
import GameLib.archi.State;


public class FIARPartialAction implements PartialAction {
	int posX, posY;
	
	public FIARPartialAction(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public FIARAction combineActions(PartialAction... actions) {
		// TODO Auto-generated method stub
		FIARAction action = new FIARAction(posX, posY);
		return action;
	}

	
}
