import java.awt.Point;

public class Robot {
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;

    private int x, y;
    private int direction;

    public Robot(int x, int y){
        this.x = x;
        this.y = y;
        this.direction = DIRECTION_UP;
    }

    public void rotateLeft(){
        direction = (direction + 3) % 4;
    }

    public void rotateRight(){
        direction = (direction + 1) % 4;
    }

    public Point calculateNextPosition(int step){
        int newX = x, newY = y;
        switch(direction){
            case DIRECTION_UP:
                newY -= step;
                break;
            case DIRECTION_RIGHT:
                newX += step;
                break;
            case DIRECTION_DOWN:
                newY += step;
                break;
            case DIRECTION_LEFT:
                newX -= step;
                break;
        }
        return new Point(newX, newY);
    }

    public void applyMove(Point position){
        x = position.x;
        y = position.y;
    }

    public void resetPosition() {
        x = 0;
        y = 0;
        direction = DIRECTION_UP;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getDirection() { return direction; }
}
