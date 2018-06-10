package states;

import main.ImageManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sql.ReadJdbc;

public class Rank extends BasicGameState {

    private Game game;

    private MouseOverArea retryButton, quitButton;
    ReadJdbc read;

    Rank(Game game) {
        this.game = game;
    }

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg) {
        read = new ReadJdbc(game.getMapName());
        retryButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),
                440 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getWidth()/2,
                600 - ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY).getHeight()/2);
        quitButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),
                840 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getWidth()/2,
                600 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getHeight()/2);

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
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        read = new ReadJdbc(game.getMapName());
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        for (int i=0 ; i<read.getUsername().size() ; i++){
            g.drawString("User: "+read.getUsername().get(i)+"    Score: "+read.getScore().get(i),400,(i+1)*100);
        }
        quitButton.render(gc, g);
        retryButton.render(gc, g);
        System.out.println("FGHJHG");
    }

    @Override
    public int getID() {
        return 5;
    }
}
