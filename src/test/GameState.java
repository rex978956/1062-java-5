package test;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import test.Gametest1;

import java.util.ArrayList;
import java.util.Random;

public class GameState extends BasicGameState{
    private ArrayList<Circle> balls;
    private Circle mouseBall;
    private Image ms = null;
    private Image cursor = null;
    private Image cursortail = null;
    private int timePassed;
    private Random random;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame sbg) throws SlickException {
        balls = new ArrayList<Circle>();
        mouseBall = new Circle(0,0,25);
                timePassed = 0;
        random = new Random();
        ms = new Image("res/cursormiddle.png");
        cursor = new Image("res/cursor.png");
        cursortail = new Image("res/cursortrail.png");
        gameContainer.setMouseCursor(cursor, 0, 0);
        //gameContainer.setMouseGrabbed(true); //移除鼠標
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame sbg, int delta) throws SlickException {
        mouseBall.setCenterX(gameContainer.getInput().getMouseX());
        mouseBall.setCenterY(gameContainer.getInput().getMouseY());
        timePassed+=delta;

        if(timePassed > 500){
            timePassed = 0;
            balls.add(new Circle(200+random.nextInt(400),0,10));
        }
        for (Circle c : balls){
            System.out.println(delta);
            c.setCenterY(c.getCenterY()+(delta/5));
        }
        for(int i = balls.size()-1;i>=0;i--){
            Circle c = balls.get(i);
            if(c.getCenterY()>610){
                balls.remove(i);
                Gametest1.live--;
            }else if(c.intersects(mouseBall)){
                balls.remove(i);
                Gametest1.gameScore++;
            }

            if(Gametest1.live <= 0){
                    sbg.enterState(1,new FadeOutTransition(),new FadeInTransition());
                    //id FadeOutTransition 和 in 可以轉場做漸進漸出
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame sbg, Graphics g) throws SlickException {
        float MouseX = gameContainer.getInput().getMouseX();
        float MouseY = gameContainer.getInput().getMouseY();
        //g.setColor(new Color(0x9ADFF6));
        //g.fill(mouseBall);
        g.setColor(Color.red);
        for (Circle c : balls){
            g.fill(c);
        }
        cursor.rotate(1);
        cursor.draw(MouseX - cursor.getWidth()/2,MouseY - cursor.getHeight()/2);
        cursortail.draw(MouseX - cursortail.getWidth()/2,MouseY - cursortail.getHeight()/2);
        ms.draw(MouseX - ms.getWidth()/2,MouseY - ms.getHeight()/2);
        //g.drawString("Current balls: "+balls.size(),20,50);
        g.drawString("Score: "+ Gametest1.gameScore,20,35);
        g.drawString("Lives: "+ Gametest1.live,20,55);
    }

    @Override
    public int getID() {
        return 0;
    }
}
