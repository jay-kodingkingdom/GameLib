/**
 * Created by Wilson on 4/30/2015.
 */

import GameLib.*;
import jline.ConsoleReader;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private static char player = 'O';
    private static char player2 = 'X';
    private static int winAmount = 5;
    private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private static int mapSize = 20;
    private static int cursorPositionX = mapSize/2, cursorPositionY = mapSize/2;
    private static boolean player1Go = true;
    private static boolean running = true;
    private static ConsoleReader reader=null;

    static public void main(String[] args) {

        try{
            reader = new ConsoleReader();}
        catch(IOException e){
            System.out.println("NO! I CANT READ YOUR INPUT! DEATH!");
            running = false;
            return;}
        Drawer render = new Drawer(mapSize, mapSize);
        boolean blinkShow = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                input();
            }
        }).start();

        while(running) {
            try{
                Thread.sleep(1000);}
            catch(InterruptedException e){}

            GameObject blinker=null;
            if(blinkShow == false) {
                blinkShow = true;
                blinker = new GameObject(cursorPositionY, cursorPositionX, 1, '?');}
            else {
                blinkShow = false;}

            GameObject[] objectArray = new GameObject[0];
            ArrayList<GameObject> newObjects = new ArrayList<>(objects);
            if(blinker != null) {
                newObjects.add(blinker);
            }
            objectArray = newObjects.toArray(objectArray);

            for(int i = 0; i <= reader.getTermheight(); i++)
            {
                System.out.println();
            }
            System.out.println((player1Go?player:player2) + "'s turn");
            render.draw(objectArray);}}

    public static void input() {

        char[] listenChars = new char[]{(char) 0x1B, 'w', 's', 'a', 'd', '\r'};
        gameloop:
        while (running) {
            try {
                switch (reader.readCharacter(listenChars)) {
                    case 0x1B:
                        System.out.println("adios mi amigo!");
                        System.out.println("adeus meu amigo!");
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

                        for (GameObject object : objects) {
                            if (cursorPositionY == object.getX() && cursorPositionX == object.getY()) {
                                continue gameloop;}}

                        GameObject object = new GameObject(cursorPositionY, cursorPositionX, 1, (player1Go ? player : player2));
                        player1Go = !player1Go;
                        objects.add(object);}}
            catch (IOException e) {
                System.out.println("NO! I CANT READ YOUR INPUT! DEATH!");
                return;}}}

    static boolean checkWin(char[] characters, char winChar) {
        int count = 0;

        for(char character : characters)
        {
            if(character == winChar) {
                ++count;
            }
            else {
                count = 0;
            }
        }

        return count >= 5;
    }

}