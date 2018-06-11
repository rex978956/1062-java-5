package states;

import main.ImageManager;

import misc.FontSet;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sql.ReadJdbc;

public class Rank extends BasicGameState {

    private Game game;

    private Image scoreboard, background, rankbg;
    private MouseOverArea retryButton, quitButton;
    ReadJdbc read;

    Rank(Game game) {
        this.game = game;
    }

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg) {
        scoreboard = ImageManager.getImage(ImageManager.GAME_SCORE_BOARD);
        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);
        rankbg = ImageManager.getImage(ImageManager.RESULT_RANKBG);
        read = new ReadJdbc(game.getMapName());
        retryButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),
                440 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getWidth()/2,
                700 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getHeight()/2);
        quitButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),
                840 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getWidth()/2,
                700 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getHeight()/2);

        retryButton.addListener(cmp -> {
            game.getMap().resetTowerList();
            game = new Game(game.getMap());
            try {
                game.init(gc, sbg);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            sbg.addState(game);
            sbg.enterState(3,new FadeOutTransition(), new FadeInTransition());
        });

        quitButton.addListener(cmp -> {
            game.getMap().resetTowerList();
            sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
        });
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        read = new ReadJdbc(game.getMapName());
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        background.draw(0,0);
        rankbg.draw(640 - rankbg.getWidth()/2,8);
        for (int i=0 ; i<read.getUsername().size() && i<10 ; i++){
            FontSet.drawButterScotch(""+(i+1),400,130+i*45,34, Color.decode("#deb008"));
            FontSet.drawButterScotch("User: "+read.getUsername().get(i),442,130+i*45,34, Color.decode("#deb008"));
            FontSet.drawButterScotch("Score: "+read.getScore().get(i),730,130+i*45,34 ,Color.decode("#deb008"));
        }
        quitButton.render(gc, g);
        retryButton.render(gc, g);
        scoreboard.draw( 640- scoreboard.getWidth()/2, 10);
    }

    @Override
    public int getID() {
        return 5;
    }
}
