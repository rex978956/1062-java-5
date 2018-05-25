package states;

import main.ImageManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {

    private Image background;
    private MouseOverArea playButton, exitButton;

    @Override
    public void init(GameContainer gc, final StateBasedGame sbg) {
        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);

        playButton = new MouseOverArea(gc, ImageManager.getImage(ImageManager.MENU_BUTTON_PLAY), 454, 260);
        playButton.addListener(cmp -> sbg.enterState(2, new FadeOutTransition(), new FadeInTransition()));

        exitButton = new MouseOverArea(gc, ImageManager.getImage(ImageManager.MENU_BUTTON_EXIT), 454, 370);
        exitButton.addListener(cmp -> System.exit(0));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        background.draw(0, 0);
        playButton.render(gc, g);
        exitButton.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
    }

    @Override
    public int getID() {
        return 1;
    }
}
