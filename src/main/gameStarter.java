package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.mapMenu;

public class gameStarter extends StateBasedGame {

    private static final int WINDOWED_WIDTH = 1024;
    private static final int WINDOWED_HEIGHT = 768;
    private AppGameContainer container;

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
        game.setDisplayMode(WINDOWED_WIDTH, WINDOWED_HEIGHT, false);
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
        this.container = (AppGameContainer) container;
        addState(new mapMenu());
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_F11) {
            try {
                if (container.isFullscreen()) {
                    container.setDisplayMode(WINDOWED_WIDTH, WINDOWED_HEIGHT, false);
                } else {
                    container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), true);
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
}
