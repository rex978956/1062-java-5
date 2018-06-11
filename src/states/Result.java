package states;

import main.ImageManager;
import misc.FontSet;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;
import sql.InsertJdbc;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Result extends BasicGameState {

    private Game game;
    private boolean isWin;
    private MouseOverArea nextButton;
    private Image background, sb, win, lose, scorebg;
    private Sound winSound, loseSound;

    private String username;
    private TextField textField;
    private Image textbg;
    private MouseOverArea submit;
    private java.awt.Font awtFont = null;

    Result(Game game, boolean isWin) {
        this.game = game;
        this.isWin = isWin;
    }

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg) throws SlickException {
        winSound = new Sound("res/sound/win.ogg");
        loseSound = new Sound("res/sound/lose.ogg");

        username = "Anonymous";
        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);
        win = ImageManager.getImage(ImageManager.RESULT_WIN);
        lose = ImageManager.getImage(ImageManager.RESULT_LOSE);
        scorebg = ImageManager.getImage(ImageManager.RESULT_SCOREBG);
        textbg = ImageManager.getImage(ImageManager.RESULT_TEXTFIELD_BG);

        InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/gnyrwn.ttf");


        try {
            awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        TrueTypeFont font35 = new TrueTypeFont(awtFont.deriveFont(45f), true);
        textField = new TextField(gc, font35, 441, 520, 398, 58);
        textField.setText(username);
        textField.setTextColor(Color.decode("#90ee90"));
        textField.setBorderColor(null);
        textField.setConsumeEvents(true);
        textField.setCursorPos(9);
        textField.setMaxLength(13);
        textField.setCursorVisible(true);

        sb = ImageManager.getImage(ImageManager.GAME_SUBMIT);

        submit = new MouseOverArea(gc, sb, 839 - sb.getWidth(), 590);
        submit.addListener(cmp -> {
            username = textField.getText();
            new InsertJdbc(game.getMapName(), username, game.getScore());
            System.out.println("user: " + username);
        });


        nextButton = new MouseOverArea(gc, ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW),
                1100 - ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW).getWidth() / 2, 650);

        nextButton.addListener(cmp -> {
            sbg.enterState(5, new FadeOutTransition(Color.black, 600), new FadeInTransition(Color.black, 600));
            winSound.stop();
            loseSound.stop();
        });
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        background.draw(0, 0);
        scorebg.draw(640 - scorebg.getWidth() / 2, 350);
        if (isWin) {
            win.draw(640 - win.getWidth() / 2, 40);
            FontSet.drawButterScotch("MapName: " + game.getMapName() + "   Score: " + game.getScore(), 380, 360, 42, Color.decode("#deb008"));
        } else {
            lose.draw(640 - lose.getWidth() / 2, 40);
            FontSet.drawButterScotch("MapName: " + game.getMapName() + "   Score: " + game.getScore(), 380, 360, 42, Color.decode("#deb008"));
        }
        nextButton.render(gc, g);
        textField.render(gc, g);
        textbg.draw(441, 520);
        submit.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (isWin && !winSound.playing() && !loseSound.playing()) {
            winSound.play();
        } else if (!winSound.playing() && !loseSound.playing()) {
            loseSound.play();
        }
    }

    @Override
    public int getID() {
        return 4;
    }
}
