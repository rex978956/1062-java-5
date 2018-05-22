package test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;
import java.util.Random;

public class GameState4 extends BasicGameState{
    private ArrayList<Circle1> circle ;
    private Circle1 circle1 =null;
    private Color[] color;
    private Color colora ;
    private Random random;
    private int i=0;
    private int timepass = 0;
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        random = new Random();
        color = new Color[5];
        color[0] = new Color(Color.green);
        color[1] = new Color(Color.red);
        color[2] = new Color(Color.yellow);
        color[3] = new Color(Color.pink);
        color[4] = new Color(Color.orange);
        System.out.println("幹");
        circle = new ArrayList<Circle1>();
      //  circle1 = new Circle1(300,300,i, Color.green);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
//        circle.add(new Circle1(300,300,1, Color.green));
//        circle.add(new Circle1(310,310,2, Color.blue));
//        circle.add(new Circle1(320,320,3, Color.red));
//        circle.add(new Circle1(350,354,4, Color.pink));
        int time=0;
        time+=delta;
        timepass+=delta;
        if(timepass>500){
            timepass = 0;
//            circle.add(new Circle1(100+random.nextInt(400),100+random.nextInt(400),
//                    ((i + ((timepass) / 600)) % 9) + 1, color[(i+(timepass)/600)%5+1]));
            circle.add(new Circle1(100+random.nextInt(400),100+random.nextInt(400),
                    i++%9+1,color[random.nextInt(5)]));
        }
        if(circle.size()>5){
            circle.remove(circle.get(0));
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
       //circle1.drawmap();
        for (Circle1 c : circle){
             c.drawmap();
        }
    }

    @Override
    public int getID() {
        return 4;
    }
}
