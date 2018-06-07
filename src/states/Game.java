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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import towers.NormalTower;
import towers.ShootingTower;
import towers.Tower;

import java.util.ArrayList;

public class Game extends BasicGameState {


    private Image sidebarBackground, info;

    private MouseOverArea buttonUpgrade, buttonSell, buttonStartWave,
            buttonNormalTower, buttonGroundTower, buttonAirTower, buttonSlowTower, buttonNormalTower2,
            buttonQuitGame, buttonCancel;

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

    private boolean lost;

    private int wave;
    private int lastSpawn;

    private int gold;
    private int baseHealth, currentHealth;

    private boolean gotMoneyForWave = true;

    public Game(Map map) {

        sidebarBackground = ImageManager.getImage(ImageManager.GAME_SIDEBAR_BG);
        info = ImageManager.getImage(ImageManager.GAME_INFO);

        map.setGame(this);

        buyTowerPathfinder = new AStarPathFinder(map, 200, false);

        this.map = map;
        this.entityList = new ArrayList<>();
        this.entityRemovalList = new ArrayList<>();
        this.towerList = new ArrayList<>();
        this.towerRemovalList = new ArrayList<>();
        this.spawnList = new ArrayList<>();
        this.gold = map.getStartMoney();
        this.baseHealth = 20;
        this.currentHealth = baseHealth;

        this.selectedFill = new Color(41, 136, 255, 40);
        this.selectedRing = new Color(41, 136, 255, 180);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Enemy> getEntityList() {
        if (!entityRemovalList.isEmpty()) {
            for (Enemy e : entityRemovalList) {
                entityList.remove(e);
            }
        }
        return entityList;
    }

    public Tower getBuyTower() {
        return buyTower;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame sbg) {
        buttonUpgrade = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE),
                1280+35 - ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE).getWidth(), 332);
        buttonSell = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_SELL),
                1280+35 - ImageManager.getImage(ImageManager.GAME_BUTTON_SELL).getWidth(), 400);
        buttonStartWave = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE),
                1280+35 - ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE).getWidth(), 705);

        buttonNormalTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.NORMAL_TOWER_1), 1104, 48);
        buttonGroundTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GROUND_TOWER_1), 1154, 48);
        buttonAirTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.AIR_TOWER_1), 1104, 96);
        buttonSlowTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.SLOW_TOWER_1), 1154, 96);
        /*++*/
        buttonNormalTower2 = new MouseOverArea(gc, ImageManager.getImage(ImageManager.NORMAL_TOWER2_1), 1104, 144);

        buttonQuitGame = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),
                640+35 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getWidth()/2, 287);
        buttonCancel = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL),
                640+35 - ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL).getWidth()/2, 360);

        buttonNormalTower.addListener(arg0 -> {
            buyTower = new NormalTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonStartWave.addListener(arg0 -> {
            if (spawnList.isEmpty() && wave < map.getWaveList().size()) {
                wave++;
                spawnList = map.getEnemyList(wave);

                if (!gotMoneyForWave) {
                    gold += map.getWaveMoney();
                } else {
                    gotMoneyForWave = false;
                }
            }
        });

        buttonUpgrade.addListener(arg0 -> {
            int upgradeLevel = selectedTower.getUpgradeLevel();
            int cost = selectedTower.getUpgradeCost();
            if (upgradeLevel < 2 && gold >= cost) {
                selectedTower.setUpgradeLevel(upgradeLevel + 1);
                gold -= cost;
            }
        });

        buttonSell.addListener(arg0 -> {
            int reward = 0;
            for (int i = 0; i <= selectedTower.getUpgradeLevel(); i++) {
                reward += selectedTower.getCost(i) / 2;
            }

            gold += reward;

            Game.this.removeTower(selectedTower);
            Point position = selectedTower.getTilePosition();
            map.setTower((int) position.getX(), (int) position.getY(), false);

            selectedTower = null;
        });

        buttonQuitGame.addListener(arg0 -> {
            map.resetTowerList();
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        });

        buttonCancel.addListener(arg0 -> pause = false);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if (lost) {
            System.out.println("LOST");
        }

        Input input = gc.getInput();
        if (!pause) {
            lastSpawn += delta;

            int spawnInterval = 1000;
            if (!spawnList.isEmpty() && lastSpawn > spawnInterval) {

                entityList.add(spawnList.get(0));
                spawnList.remove(0);
                lastSpawn = 0;

            } else if (spawnList.isEmpty() && entityList.isEmpty()) {

                if (!gotMoneyForWave) {
                    gold += map.getWaveMoney();
                    gotMoneyForWave = true;
                }

                if (wave == map.getWaveList().size()) {

                    System.out.println("WIN");
                }
            }

            if (!entityRemovalList.isEmpty()) {
                for (Enemy e : entityRemovalList) {
                    entityList.remove(e);
                }
            }
            if (!towerRemovalList.isEmpty()) {
                for (Tower t : towerRemovalList) {
                    towerList.remove(t);
                }
            }

            for (Tower tower : towerList) {
                tower.update(gc, delta);
            }
            for (Enemy entity : entityList) {
                entity.update(delta);
            }

            int mouseX = Mouse.getX();
            int mouseY = 800 - Mouse.getY();

            if (buyTower != null) {

                if (mouseX < 1056 && mouseY < 720) {
                    int tileposx = (int) Math.floor((mouseX) / 48);
                    int tileposy = (int) Math.floor((mouseY) / 48);

                    int towerPosX = tileposx * 48 + 24;
                    int towerPosY = tileposy * 48 + 24;
                    buyTower.setPosition(new Point(towerPosX, towerPosY));
                }
            }


            if (input.isMousePressed(0)) {

                if (mouseX < 1056 && mouseY < 720) {

                    int tileposx = (int) Math.floor((mouseX) / 48);
                    int tileposy = (int) Math.floor((mouseY) / 48);

                    if (buyTower != null) {

                        if (!map.isTower(tileposx, tileposy)) {

                            if (buyTower.getCost() <= gold) {

                                boolean blocking = false;

                                if (!blocking) {
                                    gold -= buyTower.getCost();
                                    towerList.add(buyTower);
                                    map.setTower(tileposx, tileposy, true);

                                    selectedTower = null;

                                    if (input.isKeyDown(Input.KEY_LSHIFT)) {
                                        if (buyTower instanceof NormalTower) {
                                            buyTower = new NormalTower(new Point(-1000, -1000), Game.this);

                                        }
                                        selectedTower = buyTower;
                                    } else {
                                        buyTower = null;
                                    }
                                }
                            }
                        }

                    } else if (map.isTower(tileposx, tileposy)) {
                        Tower clickedTower = null;

                        Point clickedTilePos = new Point(tileposx, tileposy);
                        for (Tower t : towerList) {
                            Point towerTilePos = t.getTilePosition();
                            if (clickedTilePos.getX() == towerTilePos.getX() && clickedTilePos.getY() == towerTilePos.getY()) {
                                clickedTower = t;
                            }
                        }

                        selectedTower = clickedTower;

                    } else {
                        selectedTower = null;
                    }
                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (buyTower != null) {
                buyTower = null;
                selectedTower = null;
            } else {
                pause = !pause;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        map.getMap().render(0, 0);

        sidebarBackground.draw(0, 0);
        g.drawString("gold: " + gold, 66, 763);
        g.drawString(currentHealth + "/" + baseHealth, 215, 763);
        g.drawString("Wave " + wave + "/" + map.getWaveList().size(), 960, 763);

        buttonStartWave.render(gc, g);

        Integer[] nextWave = map.getWaveUnits(wave + 1);
        if (nextWave != null) {
            g.drawString("Next Wave:", 1105, 660);
            g.drawString("Ground: " + nextWave[0], 1105, 680);
            g.drawString("Air: " + nextWave[1], 1105, 700);
            g.drawString("GroundBoss: " + nextWave[2], 1105, 720);
        } else {
            g.drawString("Last Wave!", 1105, 720);
        }


        buttonNormalTower.render(gc, g);

        if (selectedTower != null) {
            Point position = selectedTower.getPosition();
            Circle rangeCircle = new Circle(position.getX(), position.getY(), selectedTower.getRange());

            g.setColor(selectedFill);
            g.fill(rangeCircle);

            g.setColor(selectedRing);
            g.draw(rangeCircle);


            info.draw(1168-info.getWidth()/2, 180);

            if (selectedTower instanceof ShootingTower) {
                ShootingTower shTower = (ShootingTower) selectedTower;
            }

            if (selectedTower != buyTower && selectedTower != null) {
                if (selectedTower.getUpgradeLevel() < 2) {
                    buttonUpgrade.render(gc, g);
                }
                buttonSell.render(gc, g);
            }
        }

        if (buyTower != null) {
            buyTower.render(gc, g);
        }

        for (Tower tower : towerList) {
            tower.render(gc, g);
        }
        for (Enemy entity : entityList) {
            entity.render(g);
        }

        if (pause) {
            g.setColor(new Color(0, 15, 35, 198));
            g.fillRect(0, 0, 1280, 800);

            buttonQuitGame.render(gc, g);
            buttonCancel.render(gc, g);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    public int getHealth() {
        return currentHealth;
    }

    public void setHealth(int health) {
        if (health > 0) {
            currentHealth = health;
        } else {

            lost = true;
        }
    }

    private void removeTower(Tower tower) {
        towerRemovalList.add(tower);
    }

    public void removeEntity(Enemy entity) {

        entityRemovalList.add(entity);


        for (Tower t : towerList) {
            if (t instanceof ShootingTower) {
                ShootingTower st = (ShootingTower) t;
                if (st.getTarget() == entity) {
                    st.clearTarget();
                }
            }
        }
    }
}
