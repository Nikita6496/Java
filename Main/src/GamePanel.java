import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int ROBOT_SIZE = 30;
    private static final int GRID_SIZE = 30;

    private Robot robot;
    private GameEngine engine;
    private Timer timer;
    private JLabel statusLabel;
    private JTextField stepField;
    private MovementService movementService;

    public GamePanel(JLabel statusLabel, JTextField stepField){
        this.statusLabel = statusLabel;
        this.stepField = stepField;

        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();

        robot = new Robot(0,0);
        engine = new GameEngine();
        movementService = new MovementService(robot, engine, this);

        timer = new Timer(30, this);
        timer.start();
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    int getStep(){
        try {
            return Integer.parseInt(stepField.getText());
        }
        catch(Exception e){
            return 30;
        }
    }

    public void handleControlInput(ControlType controlType){
        setStatus("Выполняется: " + controlTypeToString(controlType));
        int step = getStep();
        movementService.executeMovement(controlType, step);
        repaint();
    }

    private String controlTypeToString(ControlType controlType) {
        switch(controlType) {
            case LEFT: return "ВЛЕВО";
            case RIGHT: return "ВПРАВО";
            case FORWARD: return "ПРЯМО";
            default: return "";
        }
    }

    void fireLevelComplete(){
        LevelCompletedEvent ev = new LevelCompletedEvent(this);
        JOptionPane.showMessageDialog(this, "Уровень пройден!");

        int result = JOptionPane.showConfirmDialog(this, "Перезапустить игру?", "Победа!", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            robot.resetPosition();
            setStatus("Новая игра!");
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        drawGrid(g2);
        drawObstacles(g2);
        drawWinZone(g2);
        drawRobot(g2);
    }

    private void drawGrid(Graphics2D g2) {
        for(int x = 0; x < getWidth(); x += GRID_SIZE) {
            g2.drawLine(x, 0, x, getHeight());
        }
        for(int y = 0; y < getHeight(); y += GRID_SIZE) {
            g2.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawObstacles(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        for(Rectangle obstacle : engine.getObstacles()) {
            g2.fill(obstacle);
        }
    }

    private void drawWinZone(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fill(engine.getWinZone());
    }

    private void drawRobot(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(robot.getX(), robot.getY(), ROBOT_SIZE, ROBOT_SIZE);

        g2.setColor(Color.WHITE);
        String directionSymbol = getDirectionSymbol();
        g2.drawString(directionSymbol, robot.getX() + 11, robot.getY() + 20);
    }

    private String getDirectionSymbol() {
        switch(robot.getDirection()) {
            case Robot.DIRECTION_UP: return "^";
            case Robot.DIRECTION_RIGHT: return ">";
            case Robot.DIRECTION_DOWN: return "v";
            case Robot.DIRECTION_LEFT: return "<";
            default: return "^";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                handleControlInput(ControlType.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                handleControlInput(ControlType.RIGHT);
                break;
            case KeyEvent.VK_UP:
                handleControlInput(ControlType.FORWARD);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}
}