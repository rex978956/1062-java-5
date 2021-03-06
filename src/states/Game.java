package states;

import enemy.Enemy;
import main.ImageManager;
import misc.FontSet;
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
import towers.*;

import java.util.ArrayList;

public class Game extends BasicGameState {
    private Sound nyanCatSound;

    private Image sidebarBackground, info, itemlist;

    private MouseOverArea buttonUpgrade, buttonSell, buttonStartWave,
            buttonNormalTower, buttonAirTower, buttonGroundTower, buttonSlowTower,
            buttonMachineTower, buttonAntiaircraftTower, buttonArtilleryTower, buttonRadiationTower,
            buttonQuitGame, buttonCancel;

    private boolean pause;

    private Map map;
    private ArrayList<Enemy> entityList;
    private ArrayList<Tower> towerList;

    private ArrayList<Enemy> entityRemovalList;
    private ArrayList<Tower> towerRemovalList;

    private Tower buyTower, selectedTower;
    private AStarPathFinder buyTowerPathfinder;

    private Color selectedFill, selectedRing, selectedUpFill, selectedUpRing;

    private ArrayList<Enemy> spawnList;

    private boolean lost;

    private int wave;
    private int lastSpawn;
    private String mapName;
    private int score = 0, dieNum = 0;
    private int gold;
    private int baseHealth, currentHealth;

    private boolean gotMoneyForWave = true;
    private boolean isClickedTower = false;

    public Game(Map map) {

        sidebarBackground = ImageManager.getImage(ImageManager.GAME_SIDEBAR_BG);
        info = ImageManager.getImage(ImageManager.GAME_INFO);

        map.setGame(this);

        buyTowerPathfinder = new AStarPathFinder(map, 200, false);

        this.map = map;
        this.mapName = map.getName();
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
        this.selectedUpFill = new Color(255, 136, 41, 40);
        this.selectedUpRing = new Color(255, 136, 41, 180);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDieNum() {
        return dieNum;
    }

    public void setDieNum(int dieNum) {
        this.dieNum = dieNum;
    }

    public String getMapName() {
        return mapName;
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
        nyanCatSound = new Sound("res/sound/NyanCat.ogg");
        itemlist = ImageManager.getImage(ImageManager.GAME_ITEMLIST);

        buttonUpgrade = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE),
                1168 - ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE).getWidth() / 2, 500);
        buttonSell = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_SELL),
                1168 - ImageManager.getImage(ImageManager.GAME_BUTTON_SELL).getWidth() / 2, 550);
        buttonStartWave = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE),
                1168 - ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE).getWidth() / 2, 750);

        buttonNormalTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.NORMAL_TOWER), 1100, 50);
        buttonGroundTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GROUND_TOWER), 1180, 50);
        buttonAirTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.AIR_TOWER), 1100, 100);
        buttonSlowTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.SLOW_TOWER), 1180, 100);
        buttonMachineTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.MACHINE_TOWER), 1100, 150);
        buttonArtilleryTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.ARTILLERY_TOWER), 1180, 150);
        buttonAntiaircraftTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.ANTIAIRCRAFA_TOWER), 1100, 200);
        buttonRadiationTower = new MouseOverArea(gc, ImageManager.getImage(ImageManager.RADIATION_TOWER), 1180, 200);

        buttonQuitGame = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),
                640 + 35 - ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME).getWidth() / 2, 287);
        buttonCancel = new MouseOverArea(gc, ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL),
                640 + 35 - ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL).getWidth() / 2, 360);

        buttonNormalTower.addListener(arg0 -> {
            buyTower = new NormalTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonAirTower.addListener(arg0 -> {
            buyTower = new AirTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonSlowTower.addListener(arg0 -> {
            buyTower = new SlowTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonGroundTower.addListener(arg0 -> {
            buyTower = new GroundTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonMachineTower.addListener(arg0 -> {
            buyTower = new MachineTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonArtilleryTower.addListener(arg0 -> {
            buyTower = new ArtilleryTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonAntiaircraftTower.addListener(arg0 -> {
            buyTower = new AntiaircraftTower(new Point(-1000, -1000), Game.this);
            selectedTower = buyTower;
        });

        buttonRadiationTower.addListener(arg0 -> {
            buyTower = new RadiationTower(new Point(-1000, -1000), Game.this);
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
            nyanCatSound.stop();
        });

        buttonCancel.addListener(arg0 -> pause = false);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        while (!nyanCatSound.playing()) {
            nyanCatSound.play();
        }

        if (lost) {
            Result result = new Result(this, false);
            Rank rank = new Rank(this);
            rank.init(gc, sbg);
            result.init(gc, sbg);
            sbg.addState(result);
            sbg.addState(rank);
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
            nyanCatSound.stop();
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
                    Result result = new Result(this, true);
                    Rank rank = new Rank(this);
                    result.init(gc, sbg);
                    rank.init(gc, sbg);
                    sbg.addState(result);
                    sbg.addState(rank);
                    sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
                    nyanCatSound.stop();
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

                isClickedTower = false;

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
                                Point base = map.getBase();
                                for (Point spawn : map.getSpawnList()) {
                                    if (buyTowerPathfinder.findPath(null, (int) spawn.getX(), (int) spawn.getY(), (int) base.getX(), (int) base.getY()) == null) {
                                        blocking = true;
                                        break;
                                    } else if (tileposx == spawn.getX() && tileposy == spawn.getY()) {
                                        blocking = true;
                                    }
                                }

                                if (!blocking) {
                                    gold -= buyTower.getCost();
                                    towerList.add(buyTower);
                                    map.setTower(tileposx, tileposy, true);

                                    selectedTower = null;

                                    if (input.isKeyDown(Input.KEY_LSHIFT)) {
                                        if (buyTower instanceof NormalTower) {
                                            buyTower = new NormalTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof AirTower) {
                                            buyTower = new AirTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof GroundTower) {
                                            buyTower = new GroundTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof SlowTower) {
                                            buyTower = new SlowTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof MachineTower) {
                                            buyTower = new MachineTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof AntiaircraftTower) {
                                            buyTower = new AntiaircraftTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof ArtilleryTower) {
                                            buyTower = new ArtilleryTower(new Point(-1000, -1000), Game.this);

                                        } else if (buyTower instanceof RadiationTower) {
                                            buyTower = new RadiationTower(new Point(-1000, -1000), Game.this);
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
                        isClickedTower = true;

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
        itemlist.draw(1097, 50);
        FontSet.drawStylus("gold: " + gold, 40, 771 ,18, Color.decode("#730000"));
        FontSet.drawStylus(currentHealth + "/" + baseHealth, 180, 771, 18, Color.decode("#730000"));
        FontSet.drawStylus("Wave " + wave + "/" + map.getWaveList().size(), 960, 770, 18, Color.decode("#730000"));

        buttonStartWave.render(gc, g);

        buttonNormalTower.render(gc, g);
        buttonAirTower.render(gc, g);
        buttonSlowTower.render(gc, g);
        buttonGroundTower.render(gc, g);
        buttonMachineTower.render(gc, g);
        buttonAntiaircraftTower.render(gc, g);
        buttonArtilleryTower.render(gc, g);
        buttonRadiationTower.render(gc, g);

        if (selectedTower != null) {
            Point position = selectedTower.getPosition();
            Circle rangeCircle = new Circle(position.getX(), position.getY(), selectedTower.getRange());
            Circle gradeRangeCircle = new Circle(position.getX(), position.getY(), selectedTower.getUpgradeRange());

            if (isClickedTower) {
                g.setColor(selectedUpFill);
                g.fill(gradeRangeCircle);

                g.setColor(selectedUpRing);
                g.draw(gradeRangeCircle);

                g.setColor(selectedFill);
                g.fill(rangeCircle);

                g.setColor(selectedRing);
                g.draw(rangeCircle);

            } else {
                g.setColor(selectedFill);
                g.fill(rangeCircle);

                g.setColor(selectedRing);
                g.draw(rangeCircle);
            }

            info.draw(1168 - info.getWidth() / 2, 270);

            if (selectedTower instanceof ShootingTower) {
                ShootingTower shTower = (ShootingTower) selectedTower;
                FontSet.drawAkrobat("Damage: " + shTower.getDamage(), 1085, 310, 18 ,Color.decode("#730000"));
                FontSet.drawAkrobat("Range: " + shTower.getRange(),1085, 330, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("Atk Speed: " + Math.round(1000f/shTower.getShootInterval()*100)/100f, 1085, 350, 18,Color.decode("#730000"));
                if(shTower.getType() == 1){
                    FontSet.drawAkrobat("Cheap towers allow you ", 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("to quickly deploy.", 1085, 400, 18,Color.decode("#730000"));
                }else if(shTower.getType() == 2){
                    FontSet.drawAkrobat("It can cause more damage to" , 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("enemies on the ground but " , 1085, 400, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("can't attack the air enemies." , 1085, 420, 18,Color.decode("#730000"));
                }else if(shTower.getType() == 3){
                    FontSet.drawAkrobat("It can cause more damage to", 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("enemies in the air but can't", 1085, 400, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("attack the ground enemies.", 1085, 420, 18,Color.decode("#730000"));
                }else if(shTower.getType() == 4){
                    FontSet.drawAkrobat("It can cause a lot of", 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("damage to the entire area of", 1085, 400, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("the enemy.", 1085, 420, 18,Color.decode("#730000"));
                }else if(shTower.getType() == 5){
                    FontSet.drawAkrobat("It can quickly attack the", 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("enemies on the ground,", 1085, 400, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("causing heavy damage to", 1085, 420, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("the enemy.", 1085, 440, 18,Color.decode("#730000"));
                }else if(shTower.getType() == 6) {
                    FontSet.drawAkrobat("Accurate antiaircraft tower", 1085, 380, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("can very easily shoot down", 1085, 400, 18,Color.decode("#730000"));
                    FontSet.drawAkrobat("the enemy in the air", 1085, 420, 18,Color.decode("#730000"));
                }

                if(selectedTower == buyTower){
                    ImageManager.getImage(ImageManager.GAME_GEM).draw(1080,460);
                    FontSet.drawAkrobat("Cost: " + buyTower.getCost(),1120, 467, 26,Color.decode("#730000"));
                }
            } else if(selectedTower instanceof SlowTower) {
                SlowTower slowTower = (SlowTower) selectedTower;
                FontSet.drawAkrobat("Range: "+slowTower.getRange(),1085, 310, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("Slow Value: "+(int)((1-slowTower.getSlowValue())*100)+"%",1085, 330, 18,Color.decode("#730000"));

                FontSet.drawAkrobat("It can reduce the speed of", 1085, 360, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("enemy movement on the", 1085, 380, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("ground.", 1085, 400, 18,Color.decode("#730000"));
                if(selectedTower == buyTower){
                    ImageManager.getImage(ImageManager.GAME_GEM).draw(1080,460);
                    FontSet.drawAkrobat("Cost: " + buyTower.getCost(),1120, 467, 26,Color.decode("#730000"));
                }

            } else if(selectedTower instanceof RadiationTower) {
                RadiationTower radiationTower = (RadiationTower) selectedTower;
                FontSet.drawAkrobat("Range: "+radiationTower.getRange(),1085, 310, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("Slow Value: "+(int)((1-radiationTower.getSlowValue())*100)+"%",1085, 330, 18,Color.decode("#730000"));

                FontSet.drawAkrobat("Powerful radiation slows", 1085, 360, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("down the movement of all the enemy", 1085, 380, 18,Color.decode("#730000"));
                FontSet.drawAkrobat("all the enemy.", 1085, 400, 18,Color.decode("#730000"));
                if(selectedTower == buyTower){
                    ImageManager.getImage(ImageManager.GAME_GEM).draw(1080,460);
                    FontSet.drawAkrobat("Cost: " + buyTower.getCost(),1120, 467, 26,Color.decode("#730000"));
                }
            }

            if (selectedTower != buyTower && selectedTower != null) {
                if (selectedTower.getUpgradeLevel() < 2) {
                    ImageManager.getImage(ImageManager.GAME_GEM).draw(1080,450);
                    FontSet.drawAkrobat("Upgrade: "+selectedTower.getUpgradeCost(),1120, 457, 26,Color.decode("#730000"));
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
