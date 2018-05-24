package states;

import java.util.ArrayList;

import main.ImageManager;
import misc.Map;
import misc.MapLoader;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MapMenu extends BasicGameState {

	private Image background,chooseMap;
	private MouseOverArea nextButton, prevButton;
	
	private ArrayList<Map> mapList;
	private int selectedMap;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		chooseMap = ImageManager.getImage(ImageManager.MENU_TEXT_CHOOSEMAP);
		background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);
		
		mapList = MapLoader.loadMaps();

		prevButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW),514,506);
		prevButton.addListener(cmp -> selectPrevMap());
		
		nextButton = new MouseOverArea(gc,ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW),678,506);
		nextButton.addListener(cmp -> selectNextMap());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		background.draw(0,0);
		chooseMap.draw(366,130);
		
		prevButton.render(gc, g);
		nextButton.render(gc, g);
		
		if(mapList != null) {

			Map map2 = mapList.get(selectedMap);
			
			map2.getPreview().draw(466,255);
			g.setColor(Color.white);
			g.drawString(map2.getName(), 480, 471);
			
			if(selectedMap > 0) {
				mapList.get(selectedMap-1).getPreview().draw(-224,255);
			}
			
			if(selectedMap < mapList.size()-1) {
				mapList.get(selectedMap+1).getPreview().draw(1156,255);
			}
			
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			selectPrevMap();
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			selectNextMap();
		}
		
		if(input.isMousePressed(0)) {
			int x = Mouse.getX();
			int y = 800-Mouse.getY();
			
			if(x > 466 && x < 814 && y > 255 && y < 497) {
				Game game = new Game(mapList.get(selectedMap));
				game.init(gc, sbg);
				sbg.addState(game);
				sbg.enterState(3,new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	private void selectPrevMap() {
		if(selectedMap > 0) {
			selectedMap--;
		} else {
			selectedMap = mapList.size()-1;
		}
	}
	
	private void selectNextMap() {
		if(selectedMap < mapList.size()-1) {
			selectedMap++;
		} else {
			selectedMap = 0;
		}
	}

	@Override
	public int getID() {
		return 2;
	}
}
