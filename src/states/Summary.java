package states;

import main.ImageManager;
import misc.LatoFont;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Summary extends BasicGameState {

	private Game game;
	private boolean won;
	
	private Image background;
	private MouseOverArea retryButton, quitButton;
	
	Summary(Game game, boolean won) {
		this.game = game;
		this.won = won;
	}
	
	@Override
	public void init(final GameContainer gc, final StateBasedGame sbg) {
		background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);
		
		retryButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_RETRY),400,345);
		quitButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),700,345);
		
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

		background.draw(0,0);
		

		if(won) {
			LatoFont.draw(450, 150, "Congratulations, you won!", 32);
		} else {
			LatoFont.draw(435, 150, "Unexpected Error: You lost!", 32);
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
