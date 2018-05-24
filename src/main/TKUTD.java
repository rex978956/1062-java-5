package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.*;

public class TKUTD extends StateBasedGame {

	private TKUTD(String name) {
		super(name);
	}

	public void initStatesList(GameContainer gc) {
				addState(new MainMenu());
				addState(new MapMenu());
				addState(new Credits());
	}
	
	public static void main(String[] args) {
		try {
			System.setProperty("org.newdawn.slick.pngloader", "false");
			
			AppGameContainer game = new AppGameContainer(new TKUTD("TKUTD"));
			game.setDisplayMode(1280,800,false);
			game.setAlwaysRender(true);
			game.setShowFPS(false);
			game.setVSync(true);
			game.start();		
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
