package states;

import main.ImageManager;
import misc.FontSet;
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
    public void init(GameContainer container, final StateBasedGame game) {
        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);

        playButton = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_PLAY),
                640 + 35 - ImageManager.getImage(ImageManager.MENU_BUTTON_PLAY).getWidth() / 2,
                200);
        playButton.addListener(source -> game.enterState(2, new FadeOutTransition(), new FadeInTransition()));

        exitButton = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_EXIT),
                640 + 35 - ImageManager.getImage(ImageManager.MENU_BUTTON_EXIT).getWidth() / 2,
                300);
        exitButton.addListener(source -> System.exit(0));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0);
        playButton.render(container, g);
        exitButton.render(container, g);
        FontSet.draw("OH, YOU KNOW THE FLAG NOW.", 1080, 1000, 18);
        FontSet.draw("THIS IS FUCKING JAVA.", 1080, 1018, 18);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
    }

    @Override
    public int getID() {
        return 1;
    }
}
