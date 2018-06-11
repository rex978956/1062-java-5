package states;

import main.ImageManager;
import misc.FontSet;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {

    private Image background;
    private MouseOverArea playButton, exitButton;

    private Sound japariSound;

    @Override
    public void init(GameContainer container, final StateBasedGame game) throws SlickException {
        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);

        japariSound = new Sound("res/sound/Japari.ogg");

        playButton = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_PLAY),
                640 + 35 - ImageManager.getImage(ImageManager.MENU_BUTTON_PLAY).getWidth() / 2,
                200);
        playButton.addListener(source -> {
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            japariSound.stop();
        });

        exitButton = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_EXIT),
                640 + 35 - ImageManager.getImage(ImageManager.MENU_BUTTON_EXIT).getWidth() / 2,
                300);
        exitButton.addListener(source -> {
            AL.destroy();
            System.exit(0);
        });
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0);
        playButton.render(container, g);
        exitButton.render(container, g);
        FontSet.drawAkrobat("OH, YOU KNOW THE FLAG NOW.", 1080, 1000, 18);
        FontSet.drawAkrobat("THIS IS FUCKING JAVA.", 1080, 1018, 18);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        while (!japariSound.playing()) {
            japariSound.play();
        }
    }

    @Override
    public int getID() {
        return 1;
    }
}
