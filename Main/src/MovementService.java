import java.awt.*;

public class MovementService {
    private Robot robot;
    private GameEngine engine;
    private GamePanel panel;

    public MovementService(Robot robot, GameEngine engine, GamePanel panel) {
        this.robot = robot;
        this.engine = engine;
        this.panel = panel;
    }

    public void executeMovement(ControlType controlType, int step) {
        switch(controlType) {
            case LEFT:
                robot.rotateLeft();
                break;
            case RIGHT:
                robot.rotateRight();
                break;
            case FORWARD:
                moveForward(step);
                break;
        }
    }

    private void moveForward(int step) {
        Point nextPosition = robot.calculateNextPosition(step);
        Rectangle robotBounds = new Rectangle(nextPosition.x, nextPosition.y, 30, 30);

        if(isCollision(robotBounds) || isOutOfBounds(nextPosition)) {
            panel.setStatus("Столкновение!");
            return;
        }

        robot.applyMove(nextPosition);
        panel.setStatus("ОК");

        if(engine.isWin(robotBounds)) {
            panel.fireLevelComplete();
        }
    }

    private boolean isCollision(Rectangle bounds) {
        return engine.isCollision(bounds);
    }

    private boolean isOutOfBounds(Point position) {
        return position.x < 0 || position.y < 0 ||
                position.x + 30 > panel.getWidth() || position.y + 30 > panel.getHeight();
    }
}
