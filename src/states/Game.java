package states;

import enemy.Enemy;
import main.ImageManager;
import misc.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import towers.Tower;

import java.util.ArrayList;

public class Game extends BasicGameState {


    private MouseOverArea button_normalTower;


    public Game(Map map) {
    }

    public Game() {

    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
        button_normalTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.NORMAL_TOWER_1),1104,48);
        button_normalTower.addListener(arg0 -> {
        });
    }


    public void update(GameContainer gc, StateBasedGame sbg, Graphics g, int i) throws SlickException {
        button_normalTower.render(gc,g);
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void removeEntity(Enemy t) {
    }

    public void getGold() {
    }
    public void setGold() {
    }

    public void getMap() {

    }

    public ArrayList<Enemy> getEntityList() {
        return null;
    }

    public Tower getBuyTower() {

        return null;
    }
}
