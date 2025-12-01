import java.awt.Point;

public class Robot {
    int x, y, dir; // 0 up, 1 right, 2 down, 3 left

    public Robot(int x, int y){
        this.x = x; this.y = y;
        this.dir = 0;
    }

    public void rotateLeft(){ dir = (dir + 3) % 4; }
    public void rotateRight(){ dir = (dir + 1) % 4; }

    public Point nextStep(int step){
        int nx = x, ny = y;
        switch(dir){
            case 0: ny -= step; break;
            case 1: nx += step; break;
            case 2: ny += step; break;
            case 3: nx -= step; break;
        }
        return new Point(nx, ny);
    }

    public void applyMove(Point p){
        x = p.x; y = p.y;
    }
}