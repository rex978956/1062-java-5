package test;

import org.newdawn.slick.*;

import org.newdawn.slick.state.StateBasedGame;

public class Gametest1 extends StateBasedGame {

    public static int gameScore = 0;
    public static int live = 3;

    public Gametest1(String title) {
        super(title);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
        this.addState(new GameState2());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Gametest1("test.Gametest1"));
        app.setDisplayMode(800, 600, false);        //app.setVSync(true); //set FPS 同步 (多線程) 好像預設60
        app.setTargetFrameRate(60); //set FPS
        app.setAlwaysRender(true);
        app.start();
    }
}