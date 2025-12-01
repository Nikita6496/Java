import java.awt.Rectangle;
import java.util.ArrayList;

public class GameEngine {
    ArrayList<Rectangle> obstacles = new ArrayList<>();
    Rectangle winZone;

    public GameEngine() {
        //Границы
        obstacles.add(new Rectangle(60,90,30,360));

        obstacles.add(new Rectangle(90,30,870,30));

        obstacles.add(new Rectangle(90,450,870,30));

        obstacles.add(new Rectangle(960,60,30,390));

        //Барьеры
        obstacles.add(new Rectangle(120,60,30,60));
        obstacles.add(new Rectangle(90,210,60,30));
        obstacles.add(new Rectangle(120,240,30,60));
        obstacles.add(new Rectangle(120,150,60,30));
        obstacles.add(new Rectangle(180,90,30,270));
        obstacles.add(new Rectangle(120,330,60,30));
        obstacles.add(new Rectangle(120,360,30,60));
        obstacles.add(new Rectangle(150,390,180,30));
        obstacles.add(new Rectangle(210,90,60,30));
        obstacles.add(new Rectangle(240,120,30,240));
        obstacles.add(new Rectangle(270,330,120,30));
        obstacles.add(new Rectangle(360,360,30,60));
        obstacles.add(new Rectangle(390,390,120,30));
        obstacles.add(new Rectangle(480,330,30,60));
        obstacles.add(new Rectangle(510,330,60,30));
        obstacles.add(new Rectangle(540,270,30,60));
        obstacles.add(new Rectangle(570,270,180,30));
        obstacles.add(new Rectangle(720,300,30,90));
        obstacles.add(new Rectangle(660,390,270,30));
        obstacles.add(new Rectangle(900,330,30,60));
        obstacles.add(new Rectangle(540,390,30,60));
        obstacles.add(new Rectangle(570,390,60,30));
        obstacles.add(new Rectangle(600,330,30,60));
        obstacles.add(new Rectangle(630,330,60,30));
        obstacles.add(new Rectangle(840,300,30,60));
        obstacles.add(new Rectangle(840,270,120,30));
        obstacles.add(new Rectangle(780,240,30,150));
        obstacles.add(new Rectangle(780,210,150,30));
        obstacles.add(new Rectangle(900,90,30,120));
        obstacles.add(new Rectangle(840,90,60,30));
        obstacles.add(new Rectangle(780,60,30,90));
        obstacles.add(new Rectangle(720,150,150,30));
        obstacles.add(new Rectangle(720,180,30,60));
        obstacles.add(new Rectangle(660,210,60,30));
        obstacles.add(new Rectangle(660,150,30,60));
        obstacles.add(new Rectangle(600,90,150,30));
        obstacles.add(new Rectangle(600,120,30,150));
        obstacles.add(new Rectangle(480,150,120,30));
        obstacles.add(new Rectangle(420,210,180,30));
        obstacles.add(new Rectangle(480,240,30,30));
        obstacles.add(new Rectangle(300,270,210,30));
        obstacles.add(new Rectangle(420,300,30,60));
        obstacles.add(new Rectangle(300,60,30,210));
        obstacles.add(new Rectangle(330,90,60,30));
        obstacles.add(new Rectangle(540,60,30,60));
        obstacles.add(new Rectangle(420,90,120,30));
        obstacles.add(new Rectangle(420,120,30,60));
        obstacles.add(new Rectangle(360,150,60,30));
        obstacles.add(new Rectangle(360,180,30,60));

        //Победа
        winZone = new Rectangle(930, 420, 30, 30); // Зелёная зона победы
    }


    public boolean isCollision(Rectangle r){
        for(Rectangle o : obstacles)
            if(o.intersects(r)) return true;
        return false;
    }

    public boolean isWin(Rectangle r){
        return winZone.intersects(r);
    }
}