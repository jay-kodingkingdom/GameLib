package GameLib.fiveinarow;

import java.util.HashSet;
import java.util.Set;

import GameLib.archi.Action;
import GameLib.archi.Rule;
import GameLib.archi.State;

public class FIARRule implements Rule{

	@Override
	public Set<Action> getActions(State state) {
		if(!(state instanceof FIARState)) {
			throw new IllegalArgumentException();}
		FIARState fireState = (FIARState)state;
		
		HashSet<Action> validActions = new HashSet<Action>();
		
		for (int widthX=0;widthX<fireState.GameMap.getWidth();widthX++){
			for (int heightY=0;heightY<fireState.GameMap.getHeight();heightY++){
				if (fireState.GameMap
						.getBoxItem(widthX, heightY)
						.equals(BoardItem.nothing)){
					validActions.add(new FIARAction(widthX,heightY));}}}
		
		return validActions;}}
