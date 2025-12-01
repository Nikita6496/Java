import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel implements ActionListener, KeyListener {

    Robot robot;
    GameEngine engine;
    Timer timer;
    JLabel status;
    JTextField stepField;

    public GamePanel(JLabel status, JTextField stepField){
        this.status = status;
        this.stepField = stepField;

        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();

        robot = new Robot(0,0);
        engine = new GameEngine();

        timer = new Timer(30, this);
        timer.start();
    }

    int getStep(){
        try { return Integer.parseInt(stepField.getText()); }
        catch(Exception e){ return 30; }
    }

    void executeCommand(String cmd){
        status.setText("Выполняется: " + cmd);
        int step = getStep();

        if(cmd.equals("ВЛЕВО")) robot.rotateLeft();
        if(cmd.equals("ВПРАВО")) robot.rotateRight();
        if(cmd.equals("ПРЯМО")){
            move(step);
        }

        repaint();
    }

    void move(int step){
        Point next = robot.nextStep(step);
        Rectangle r = new Rectangle(next.x, next.y, 30, 30);

        if(engine.isCollision(r) || next.x < 0 || next.y < 0 || next.x+30 > getWidth() || next.y+30 > getHeight()){
            status.setText("Столкновение!");
            return;
        }

        robot.applyMove(next);
        status.setText("ОК");

        if(engine.isWin(r)){
            fireLevelComplete();
        }
    }

    //Пользовательское событие
    private void fireLevelComplete(){
        LevelCompletedEvent ev = new LevelCompletedEvent(this);
        JOptionPane.showMessageDialog(this, "Уровень пройден!");

        int res = JOptionPane.showConfirmDialog(this, "Перезапустить игру?", "Победа!", JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION){
            robot.x = 0;
            robot.y = 0;
            robot.dir = 0;
            status.setText("Новая игра!");
        }
    }

    //Рендеринг
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(int x=0;x<getWidth();x+=30) g2.drawLine(x,0,x,getHeight());
        for(int y=0;y<getHeight();y+=30) g2.drawLine(0,y,getWidth(),y);

        g2.setColor(Color.DARK_GRAY);
        for(Rectangle b: engine.obstacles) g2.fill(b);

        g2.setColor(Color.GREEN);
        g2.fill(engine.winZone);

        g2.setColor(Color.BLUE);
        g2.fillRect(robot.x,robot.y,30,30);

        g2.setColor(Color.WHITE);
        String arrow = "^";
        if(robot.dir==1) arrow=">";
        if(robot.dir==2) arrow="v";
        if(robot.dir==3) arrow="<";
        g2.drawString(arrow, robot.x+11, robot.y+20);
    }

    //Таймер
    @Override public void actionPerformed(ActionEvent e){ repaint(); }

    //КЛАВИАТУРА
    @Override
    public void keyPressed(KeyEvent e){
        int step = getStep();
        if(e.getKeyCode()==KeyEvent.VK_LEFT) robot.rotateLeft();
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) robot.rotateRight();
        if(e.getKeyCode()==KeyEvent.VK_UP) move(step);
        repaint();
    }
    @Override public void keyReleased(KeyEvent e){}
    @Override public void keyTyped(KeyEvent e){}
}