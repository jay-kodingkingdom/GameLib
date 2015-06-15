package GameLib.fiveinarow;

import GameLib.archi.Action;
import GameLib.archi.State;


public class FIARAction implements Action{

	
	
	int posX, posY;
	
	public FIARAction(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	public State runAction(State state) {
		
		if(!(state instanceof FIARState)) {
			throw new IllegalArgumentException();
		};
		
		FIARState fireState = (FIARState)state;

		fireState.GameMap.setBoxItem(posX, posY, fireState.turn);
		return fireState;
	}

	
}
