package states;

import enemy.Enemy;
import main.ImageManager;
import misc.Map;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import towers.NormalTower;
import towers.Tower;

import java.util.ArrayList;

public class Game extends BasicGameState {


    private MouseOverArea button_normalTower;
    private Tower buyTower, selectedTower;

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
            buyTower = new NormalTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });
    }


    public void update(GameContainer gc, StateBasedGame sbg, Graphics g, int i) throws SlickException {
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = 800-Mouse.getY();
        if(buyTower != null) {
            if(mouseX < 1056 && mouseY < 720) {
                int tileposx = (int)Math.floor((mouseX)/48);
                int tileposy = (int)Math.floor((mouseY)/48);

                int towerPosX = tileposx*48+24;
                int towerPosY = tileposy*48+24;
                buyTower.setPosition(new Point(towerPosX,towerPosY));
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        button_normalTower.render(gc,g);
        if(buyTower != null) {
            buyTower.render(gc, g);
        }

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
