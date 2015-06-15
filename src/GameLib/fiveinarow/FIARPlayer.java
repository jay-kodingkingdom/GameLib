package GameLib.fiveinarow;

import java.io.IOException;
import java.util.ArrayList;

import jline.ConsoleReader;
import GameLib.Drawer;
import GameLib.GameObject;
import GameLib.archi.PartialAction;
import GameLib.archi.Player;

public class FIARPlayer implements Player {

    private static int mapSize = 20;
    private static int cursorPositionX = mapSize/2
    		, cursorPositionY = mapSize/2;
    private static ConsoleReader reader=null;
    
	public FIARPlayer(){

        try{
            reader = new ConsoleReader();}
        catch(IOException e){
            return;}        
        new Thread(()->getInput()).start();}
	
	void getInput(){
		char[] listenChars = new char[]{(char) 0x1B, 'w', 's', 'a', 'd', '\r'};
        gameloop:
        while (Game.running) {
            try {
                switch (reader.readCharacter(listenChars)) {
                    case 0x1B:
                        running = false;
                        return;
                    case 'w':
                        if (cursorPositionY > 0) {
                            cursorPositionY--;}
                        break;
                    case 's':
                        if (cursorPositionY < mapSize - 1) {
                            cursorPositionY++;}
                        break;
                    case 'a':
                        if (cursorPositionX > 0) {
                            cursorPositionX--;}
                        break;
                    case 'd':
                        if (cursorPositionX < mapSize - 1) {
                            cursorPositionX++;}
                        break;
                    case '\r':
                        char[][] map = new char[mapSize][mapSize];
                        for (GameObject object : objects) {
                            map[object.getX()][object.getY()]=object.getCharacter();}

                        if (map[cursorPositionY][cursorPositionX]!='\0') {
                            continue gameloop;}

                        GameObject object = new GameObject(cursorPositionY, cursorPositionX, 1, (player1Go ? player : player2));
                        objects.add(object);
                        
                        map[cursorPositionY][cursorPositionX]=(player1Go ? player : player2);

                        char[] verticalCheck = new char[2*winAmount-1];
                        for(int i = -winAmount + 1; i < winAmount; ++i){
                            if (cursorPositionX+i<0) continue;
                            if (cursorPositionX+i>=mapSize) continue;
                            verticalCheck[i+winAmount-1]=map[cursorPositionY][cursorPositionX+i];}
                        char[] cross1Check = new char[2*winAmount-1];
                        for(int i = -winAmount + 1; i < winAmount; ++i){
                            if (cursorPositionX+i<0) continue;
                            if (cursorPositionY+i<0) continue;
                            if (cursorPositionX+i>=mapSize) continue;
                            if (cursorPositionY+i>=mapSize) continue;
                            cross1Check[i+winAmount-1]=map[cursorPositionY+i][cursorPositionX+i];}
                        char[] horizontalCheck = new char[2*winAmount-1];
                        for(int i = -winAmount + 1; i < winAmount; ++i){
                            if (cursorPositionY+i<0) continue;
                            if (cursorPositionY+i>=mapSize) continue;
                            horizontalCheck[i+winAmount-1]=map[cursorPositionY+i][cursorPositionX];}
                        char[] cross2Check = new char[2*winAmount-1];
                        for(int i = -winAmount + 1; i < winAmount; ++i){
                            if (cursorPositionX+i<0) continue;
                            if (cursorPositionY-i<0) continue;
                            if (cursorPositionX+i>=mapSize) continue;
                            if (cursorPositionY-i>=mapSize) continue;
                            cross2Check[i+winAmount-1]=map[cursorPositionY-i][cursorPositionX+i];}

                        if (checkWin(verticalCheck, (player1Go ? player : player2)) ||
                                checkWin(cross1Check, (player1Go ? player : player2)) ||
                                checkWin(horizontalCheck, (player1Go ? player : player2)) ||
                                checkWin(cross2Check, (player1Go ? player : player2))){
                        	exitMessage="lol you win!\n"+(player1Go?"player 1":"PLAYER 1 NOT YOU");
                            running = false;
                            return;}
                        
                		player1Go = !player1Go;}}

            catch (IOException e) {
            	exitMessage="NO! I CANT READ YOUR INPUT! DEATH!";
                running = false;
                return;}}}
	
	@Override
	public FIARPartialAction getAction() {
		
		return null;
	}

}
