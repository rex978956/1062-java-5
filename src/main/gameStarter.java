package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.mapMenu;

public class gameStarter extends StateBasedGame {

    /**
     * Create a new state based game
     *
     * @param name The name of the game
     */
    public gameStarter(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {

        AppGameContainer game = new AppGameContainer(new gameStarter("Final Tower Defense"));
        game.setDisplayMode(640, 480, false);
        game.setMouseGrabbed(false);
        game.setAlwaysRender(true);
        game.setShowFPS(true);
        game.setVSync(true);
        game.start();
    }

    /**
     * Initialise the list of states making up this game
     *
     * @param container The container holding the game
     * @throws SlickException Indicates a failure to initialise the state based game resources
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new mapMenu());
    }
}
