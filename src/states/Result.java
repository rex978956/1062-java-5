package states;

import main.ImageManager;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
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
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

public class Result extends BasicGameState {

    private Game game;
    private boolean isWin;
    private MouseOverArea nextButton;

    private String username ;
    private TextField textField;
    private Image textbg;
    private MouseOverArea submit;
    private java.awt.Font awtFont = null;

    Result(Game game, boolean isWin) {
        this.game = game;
        this.isWin = isWin;
    }

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg) {
        username = "Anonymous";
//        background = new Image("images/world.png");
        try {
            textbg = new Image("res/images/menus/textfield_bg_active.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/gnyrwn.ttf");


        try {
            awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        TrueTypeFont font35 = new TrueTypeFont(awtFont.deriveFont(45f), true);
        textField = new TextField(gc, font35, 441, 450, 398, 58);
        textField.setText(username);
        textField.setTextColor(org.newdawn.slick.Color.green);
        textField.setBorderColor(null);
        textField.setConsumeEvents(true);
        textField.setCursorPos(9);
        textField.setMaxLength(13);
        textField.setCursorVisible(true);

        Image ig = null;
        try {
            ig = new Image("res/images/menus/button-arrow-left.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        submit = new MouseOverArea(gc, ig, 454, 600);
        submit.addListener(cmp -> {
            username = textField.getText();
            new InsertJdbc(game.getMapName(), username, game.getScore());
            System.out.println("!!!!!!!!!!!!");
        });



        nextButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),
                1000 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getWidth()/2,
                600 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getHeight()/2);

        nextButton.addListener(cmp -> {
            sbg.enterState(5,new FadeOutTransition(Color.black,600), new FadeInTransition(Color.black,600));
        });
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        if(isWin) {
            g.drawString("WIN", 100,100);
            g.drawString("mapID: "+game.getMapName()+"   Score: "+game.getScore(), 300,300);
        } else {
            g.drawString("LOSE", 100,100);
            g.drawString("mapID: "+game.getMapName()+"   Score: "+game.getScore(), 300,300);
        }

        nextButton.render(gc, g);

        textField.render(gc, g);
        textbg.draw(441,450);
        submit.render(gc, g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

    }

    @Override
    public int getID() {
        return 4;
    }
}
