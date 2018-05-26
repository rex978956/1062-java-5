package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import states.MainMenu;
import states.MapMenu;

public class TKUTD extends StateBasedGame {

    private static final int WINDOWED_WIDTH = 1280;
    private static final int WINDOWED_HEIGHT = 800;
    private AppGameContainer container;

    private TKUTD(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gc) {
        this.container = (AppGameContainer) gc;
        addState(new MainMenu());
        addState(new MapMenu());
    }

    public static void main(String[] args) {
        try {
            System.setProperty("org.newdawn.slick.pngloader", "false");

            AppGameContainer game = new AppGameContainer(new TKUTD("TKUTD"));
            game.setDisplayMode(1280, 800, false);
            game.setAlwaysRender(true);
            game.setShowFPS(false);
            game.setVSync(true);
            game.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     * @param c
     * @see InputListener#keyPressed(int, char)
     */
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
