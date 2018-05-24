package states;

import enemy.Enemy;
import main.ImageManager;
import misc.Map;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import towers.NormalTower;
import towers.ShootingTower;
import towers.Tower;

import java.util.ArrayList;

public class Game extends BasicGameState {


    private Image sidebar_background, info;

    private MouseOverArea button_upgrade,button_sell, button_startWave,
            button_normalTower, button_groundTower, button_airTower, button_slowTower,button_normalTower2,
            button_quitGame, button_cancel;

    private boolean pause;

    private Map map;
    private ArrayList<Enemy> entityList;
    private ArrayList<Tower> towerList;

    private ArrayList<Enemy> entityRemovalList;
    private ArrayList<Tower> towerRemovalList;

    private Tower buyTower, selectedTower;
    private AStarPathFinder buyTowerPathfinder;

    private Color selectedFill, selectedRing;

    private ArrayList<Enemy> spawnList;

    private int gold = 3000;
    private int baseHealth, currentHealth;

    private boolean gotMoneyForWave = true;

    public Game(Map map) {

        sidebar_background = ImageManager.getImage(ImageManager.GAME_SIDEBAR_BG);
        info = ImageManager.getImage(ImageManager.GAME_INFO);

        map.setGame(this);

        buyTowerPathfinder = new AStarPathFinder(map,200,false);

        this.map = map;
        this.entityList = new ArrayList<>();
        this.entityRemovalList = new ArrayList<>();
        this.towerList = new ArrayList<>();
        this.towerRemovalList = new ArrayList<>();
        this.spawnList = new ArrayList<>();
        this.gold = map.getStartMoney();
        this.baseHealth = 20;
        this.currentHealth = baseHealth;

        this.selectedFill =  new Color(41,136,255,40);
        this.selectedRing =  new Color(41,136,255,180);
    }

    public Game() {

    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
        button_upgrade = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE),1100,332);
        button_sell =  new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_SELL),1100,378);
        button_startWave =  new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE),1100,755);

        button_normalTower = new MouseOverArea(gc,ImageManager.getImage(ImageManager.NORMAL_TOWER_1),1104,48);

        button_quitGame = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),550,287);
        button_cancel = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL),550,345);

        button_normalTower.addListener(arg0 -> {
            buyTower = new NormalTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
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
        if(input.isMousePressed(0)) {

            if(mouseX < 1056 && mouseY < 720) {

                int tileposx = (int)Math.floor((mouseX)/48);
                int tileposy = (int)Math.floor((mouseY)/48);

                if(buyTower != null) {
                    if(!map.isTower(tileposx, tileposy)) {
                        if(buyTower.getCost() <= gold) {
                            boolean blocking = false;

                            if(!blocking) {
                                gold -= buyTower.getCost();
                                towerList.add(buyTower);
                                map.setTower(tileposx, tileposy, true);

                                selectedTower = null;

                                if(input.isKeyDown(Input.KEY_LSHIFT)) {
                                    if(buyTower instanceof NormalTower) {
                                        buyTower = new NormalTower(new Point(-1000,-1000), Game.this);
                                    }
                                    selectedTower = buyTower;
                                } else {
                                    buyTower = null;
                                }
                            }
                        }
                    }

                } else if(map.isTower(tileposx, tileposy)) {
                    Tower clickedTower = null;
                    Point clickedTilePos = new Point(tileposx, tileposy);
                    for(Tower t: towerList) {
                        Point towerTilePos = t.getTilePosition();
                        if(clickedTilePos.getX() == towerTilePos.getX() && clickedTilePos.getY() == towerTilePos.getY()) {
                            clickedTower = t;
                        }
                    }
                    selectedTower = clickedTower;
                } else {
                    selectedTower = null;
                }
            }
        }

        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            if(buyTower != null) {
                buyTower = null;
                selectedTower = null;
            } else {
                pause = !pause;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        button_normalTower.render(gc,g);
        button_startWave.render(gc,g);
        if(buyTower != null) {
            buyTower.render(gc, g);
        }
        if(selectedTower != null) {
            Point position = selectedTower.getPosition();
            Circle rangeCircle = new Circle(position.getX(),position.getY(),selectedTower.getRange());

            g.setColor(selectedFill);
            g.fill(rangeCircle);

            g.setColor(selectedRing);
            g.draw(rangeCircle);


            if(selectedTower instanceof ShootingTower) {
                ShootingTower shTower = (ShootingTower) selectedTower;
            }

            if(selectedTower != buyTower && selectedTower != null) {
                if(selectedTower.getUpgradeLevel() < 2) {
                    button_upgrade.render(gc, g);
                }
                button_sell.render(gc, g);
            }
        }

        if(buyTower != null) {
            buyTower.render(gc, g);
        }



    }

    public void removeEntity(Enemy t) {
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getGold() {
        return gold;
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
