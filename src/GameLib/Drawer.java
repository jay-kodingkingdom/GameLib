/**
 * Created by Wilson on 4/30/2015.
 */

package GameLib;

public class Drawer {
    int mapSizeX, mapSizeY;

    public Drawer(int mapSizeX, int mapSizeY) {
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;
    }

    public void draw(GameObject[] objects) {
        char[][] map = new char[mapSizeX][mapSizeY];
        for (GameObject object : objects) {
            for (int i = 0; i < object.graphic.size; i++) {
                for (int j = 0; j < object.graphic.size; j++) {
                    map[object.x + i][object.y + j] = object.graphic.character;
                }
            }
        }
        for(char[] line : map)
        {
            String newLine = new String(line);
            System.out.println(newLine);
        }

    }

}
