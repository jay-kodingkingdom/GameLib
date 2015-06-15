package GameLib.fiveinarow;

import GameLib.archi.State;

public class FIARState implements State{	
	BoardItem turn;
	Box<BoardItem> GameMap;
	
	public FIARState(BoardItem turn, Box<BoardItem> map){
		this.turn = turn;
		this.GameMap = map;
	}
}
