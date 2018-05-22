package test;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.Random;

public class GameState3 extends BasicGameState{
    private ArrayList<Circle> balls;
    private Circle mouseBall;
    private Image cursor = null; private Image cursormiddle = null; private Image cursortail = null;
    private Image[] slider = new Image[10];

    private int timePassed;
    private Random random;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame sbg) throws SlickException {
        mouseBall = new Circle(0,0,25);
        timePassed = 0;
        random = new Random();
        cursor = new Image("res/cursor.png");
        cursormiddle = new Image("res/cursormiddle.png");
        cursortail = new Image("res/cursortrail.png");
//        slider = new Image("res/sliderb"+i+".png");
        for (int i =0 ;i<=9;i++){
            slider[i] = new Image("res/sliderb"+i+".png");
        }
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
        }


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame sbg, Graphics g) throws SlickException {
        float MouseX = gameContainer.getInput().getMouseX();
        float MouseY = gameContainer.getInput().getMouseY();
        //g.setColor(new Color(0x9ADFF6));

        cursor.rotate(1);
        cursor.draw(MouseX - cursor.getWidth() / 2, MouseY - cursor.getHeight() / 2);
        cursortail.draw(MouseX - cursortail.getWidth() / 2, MouseY - cursortail.getHeight() / 2);
        cursormiddle.draw(MouseX - cursormiddle.getWidth() / 2, MouseY - cursormiddle.getHeight() / 2);
        //g.drawString("Current balls: "+balls.size(),20,50);

        slider[(int) ((MouseY/5) % 9 + 1)].setRotation(90);
        slider[(int) ((MouseY/5) % 9 + 1)].draw(MouseX - 150, MouseY + 15);

    }
    @Override
    public int getID() {
        return 0;
    }
}
