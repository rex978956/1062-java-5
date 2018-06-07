package misc;

import main.ImageManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Result extends BasicGameState{

    private boolean isWin;
    private MouseOverArea retryButton, quitButton;

    public Result(boolean isWin) {
        this.isWin = isWin;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        retryButton = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),400,345);
        quitButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),700,345);

        retryButton.addListener(cmp -> {
            sbg.enterState(2,new FadeOutTransition(), new FadeInTransition());
        });

        quitButton.addListener(cmp -> {
            sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
        });
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if(isWin){
            retryButton.render(gc, g);
            g.drawString("WIN!!",100,100);
        }else{
            quitButton.render(gc, g);
            g.drawString("LOST!!",100,100);
        }
    }

    @Override
    public int getID() {
        return 4;
    }
}
