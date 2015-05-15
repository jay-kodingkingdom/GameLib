package GameLib;

/**
 * Created by Wilson on 4/30/2015.
 */
public class GameObject {
    int x, y;
    ObjectGraphic graphic;

    public GameObject(int x, int y, int size,char character) {
        this.graphic = new ObjectGraphic(size, character);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSize() {
        return this.graphic.size;
    }

    public char getCharacter() {
        return this.graphic.character;
    }

    class ObjectGraphic {
        int size;
        char character;

        ObjectGraphic(int size, char character) {
            this.size = size;
            this.character = character;
        }
    }

    public class GameAction {
        int x;
        int y;

        public GameAction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void MoveToPosition() {
            (GameObject.this).x = x;
            (GameObject.this).y = y;
        }

    }

}
