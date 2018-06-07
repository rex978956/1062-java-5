package states;

import main.ImageManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Result extends BasicGameState {

    private Game game;
    private boolean isWin;

    private MouseOverArea retryButton, quitButton;

    Result(Game game, boolean isWin) {
        this.game = game;
        this.isWin = isWin;
    }

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg) {

        retryButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),
                440 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getWidth()/2,345);
        quitButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),
                840 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getWidth()/2,345);

        retryButton.addListener(cmp -> {
            game.getMap().resetTowerList();

            game = new Game(game.getMap());
            game.init(gc, sbg);
            sbg.addState(game);
            sbg.enterState(3,new FadeOutTransition(), new FadeInTransition());
        });

        quitButton.addListener(cmp -> {
            game.getMap().resetTowerList();
            sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
        });
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        if(isWin) {
            g.drawString("WIN", 100,100);
        } else {
            g.drawString("LOSE", 100,100);
        }

        quitButton.render(gc, g);
        retryButton.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

    }

    @Override
    public int getID() {
        return 4;
    }
}
