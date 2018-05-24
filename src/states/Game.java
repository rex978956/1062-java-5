package states;

import java.util.ArrayList;

import enemy.Enemy;
import main.ImageManager;
import misc.Map;
import misc.LatoFont;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;

import towers.AirTower;
import towers.GroundTower;
import towers.NormalTower;
import towers.ShootingTower;
import towers.SlowTower;
import towers.Tower;

import towers.NormalTower2; /**++**/


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
	
	private boolean lost;
	
	private int wave;
    private int lastSpawn;
	
	private int gold;
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

	public Map getMap() {
		return map;
	}

	public ArrayList<Enemy> getEntityList() {
		if(!entityRemovalList.isEmpty()) {
			for(Enemy e : entityRemovalList) {
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
		button_upgrade = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_UPGRADE),1100,332);
		button_sell =  new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_SELL),1100,378);
		button_startWave =  new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_STARTWAVE),1100,755);
		
		button_normalTower = new MouseOverArea(gc,ImageManager.getImage(ImageManager.NORMAL_TOWER_1),1104,48);
		button_groundTower = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GROUND_TOWER_1),1154,48);
		button_airTower = new MouseOverArea(gc,ImageManager.getImage(ImageManager.AIR_TOWER_1),1104,96);
		button_slowTower = new MouseOverArea(gc,ImageManager.getImage(ImageManager.SLOW_TOWER_1),1154,96);
        /*++*/
        button_normalTower2 = new MouseOverArea(gc,ImageManager.getImage(ImageManager.NORMAL_TOWER2_1),1104,144);
		
		button_quitGame = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_QUITGAME),550,287);
		button_cancel = new MouseOverArea(gc,ImageManager.getImage(ImageManager.GAME_BUTTON_CANCEL),550,345);

		button_normalTower.addListener(arg0 -> {
            buyTower = new NormalTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });
        /*++*/
        button_normalTower2.addListener(arg0 -> {
            buyTower = new NormalTower2(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });


		button_groundTower.addListener(arg0 -> {
            buyTower = new GroundTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });
		button_airTower.addListener(arg0 -> {
            buyTower = new AirTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });
		button_slowTower.addListener(arg0 -> {
            buyTower = new SlowTower(new Point(-1000,-1000), Game.this);
            selectedTower = buyTower;
        });

		button_startWave.addListener(arg0 -> {
            if(spawnList.isEmpty() && wave < map.getWaveList().size()) {
                wave++;
                spawnList = map.getEntityList(wave);

                if(!gotMoneyForWave) {
                    gold += map.getWaveMoney();
                } else {
                    gotMoneyForWave = false;
                }
            }
        });

		button_upgrade.addListener(arg0 -> {
            int upgradeLevel = selectedTower.getUpgradeLevel();
            int cost = selectedTower.getUpgradeCost();
            if(upgradeLevel < 2 && gold >= cost) {
                selectedTower.setUpgradeLevel(upgradeLevel+1);
                gold -= cost;
            }
        });
		
		button_sell.addListener(arg0 -> {
            int reward = 0;
            for(int i = 0; i <= selectedTower.getUpgradeLevel();i++) {
                reward += selectedTower.getCost(i)/2;
            }

            gold += reward;

            Game.this.removeTower(selectedTower);
            Point position = selectedTower.getTilePosition();
            map.setTower((int)position.getX(), (int)position.getY(), false);

            selectedTower = null;
        });

		button_quitGame.addListener(arg0 -> {
            map.resetTowerList();
            sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
        });
		
		button_cancel.addListener(arg0 -> pause = false);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {

		if(lost) {
			Summary summary = new Summary(this,false);
			summary.init(gc, sbg);
			sbg.addState(summary);
			sbg.enterState(4,new FadeOutTransition(), new FadeInTransition());
		}

		Input input = gc.getInput();
		if(!pause) {
			lastSpawn += delta;

            int spawnInterval = 1000;
            if(!spawnList.isEmpty() && lastSpawn > spawnInterval) {

				entityList.add(spawnList.get(0));
				spawnList.remove(0);
				lastSpawn = 0;

			} else if(spawnList.isEmpty() && entityList.isEmpty()) {
				
				if(!gotMoneyForWave) {
					gold += map.getWaveMoney();
					gotMoneyForWave = true;
				}
				
				if(wave == map.getWaveList().size()) {

					Summary summary = new Summary(this,true);
					summary.init(gc, sbg);
					sbg.addState(summary);
					sbg.enterState(4,new FadeOutTransition(), new FadeInTransition());
				}
			}

			if(!entityRemovalList.isEmpty()) {
				for(Enemy e : entityRemovalList) {
					entityList.remove(e);
				}
			}
			if(!towerRemovalList.isEmpty()) {
				for(Tower t : towerRemovalList) {
					towerList.remove(t);
				}
			}

			for(Tower tower : towerList) {
				tower.update(gc, delta);
			}
			for(Enemy entity : entityList) {
				entity.update(delta);
			}
			
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
								Point base = map.getBase();
								for(Point spawn : map.getSpawnList()) {
									if(buyTowerPathfinder.findPath(null,(int)spawn.getX(),(int)spawn.getY(),(int)base.getX(),(int)base.getY()) == null) {
										blocking = true;
										break;
									} else if(tileposx == spawn.getX() && tileposy == spawn.getY()) {
										blocking = true;
									}
								}

								if(!blocking) {
									gold -= buyTower.getCost();
									towerList.add(buyTower);
									map.setTower(tileposx, tileposy, true);

									selectedTower = null;

									if(input.isKeyDown(Input.KEY_LSHIFT)) {
										if(buyTower instanceof NormalTower) {			
											buyTower = new NormalTower(new Point(-1000,-1000), Game.this);
											
										} else if(buyTower instanceof GroundTower) {
											buyTower = new GroundTower(new Point(-1000,-1000), Game.this);
											
										} else if(buyTower instanceof AirTower) {
											buyTower = new AirTower(new Point(-1000,-1000), Game.this);
											
										} else if(buyTower instanceof SlowTower) {
											buyTower = new SlowTower(new Point(-1000,-1000), Game.this);
											
										}else if(buyTower instanceof NormalTower2) {
                                            buyTower = new NormalTower2(new Point(-1000,-1000), Game.this);

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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

		map.getMap().render(0,0);

		sidebar_background.draw(0,0);

		LatoFont.draw(66,763,gold+"",22);

		LatoFont.draw(215,763,currentHealth+"/"+baseHealth,22);

		LatoFont.draw(960,763,"Wave "+wave+"/"+map.getWaveList().size(),22);

		button_startWave.render(gc,g);

		Integer[] nextWave = map.getWaveUnits(wave+1);
		if(nextWave != null) {
			LatoFont.draw(1105,660,"Next Wave:",14);
			LatoFont.draw(1105,680,"Ground: "+nextWave[0],14);
			LatoFont.draw(1105,700,"Air: "+nextWave[1],14);
			LatoFont.draw(1105,720,"GroundBoss: "+nextWave[2],14);
            LatoFont.draw(1105,740,"AirBoss: "+nextWave[3],14);
		} else {
			LatoFont.draw(1105,720,"Last Wave!",14);
		}
		

		button_normalTower.render(gc,g);
		button_groundTower.render(gc,g);
		button_airTower.render(gc,g);
		button_slowTower.render(gc,g);

		/*++*/
        button_normalTower2.render(gc,g);
		
		
		if(selectedTower != null) {
			Point position = selectedTower.getPosition();
			Circle rangeCircle = new Circle(position.getX(),position.getY(),selectedTower.getRange());
			
			g.setColor(selectedFill);
			g.fill(rangeCircle);
			
			g.setColor(selectedRing);
			g.draw(rangeCircle);
			

			info.draw(1100,180);

			if(selectedTower instanceof ShootingTower) {
				ShootingTower shTower = (ShootingTower) selectedTower;
				LatoFont.draw(1105, 220, "Damage: " + shTower.getDamage(), 14);
				LatoFont.draw(1105, 240, "Range: " + shTower.getRange(), 14);
				LatoFont.draw(1105, 260, "Atk Speed: " + Math.round(1000f/shTower.getShootInterval()*100)/100f, 14);
				LatoFont.draw(1105, 280, "Kills: " + shTower.getKillCount(), 14);
				if(selectedTower == buyTower)
					LatoFont.draw(1105, 300, "Cost: " + buyTower.getCost(), 14);
			} else if(selectedTower instanceof SlowTower) {
				SlowTower slowTower = (SlowTower) selectedTower;
				LatoFont.draw(1105, 220, "Range: "+slowTower.getRange(), 14);
				LatoFont.draw(1105, 240, "Slow Value: "+(int)((1-slowTower.getSlowValue())*100)+"%", 14);
				if(selectedTower == buyTower)
					LatoFont.draw(1105, 260, "Cost: " + buyTower.getCost(), 14);
			}
			
			if(selectedTower != buyTower && selectedTower != null) {
				if(selectedTower.getUpgradeLevel() < 2) {
					LatoFont.draw(1170, 339, selectedTower.getUpgradeCost()+"", 22);
					button_upgrade.render(gc, g);
				}
				button_sell.render(gc, g);		
			}
		}

		if(buyTower != null) {
			buyTower.render(gc, g);			
		}

		for(Tower tower : towerList) {
			tower.render(gc, g);
		}
		for(Enemy entity : entityList) {
			entity.render(g);
		}

		if(pause) {
			g.setColor(new Color(0, 15, 35, 198));
			g.fillRect(0,0,1280,800);
			
			LatoFont.draw(455, 233, "Do you really want to quit?", 32);
			button_quitGame.render(gc, g);
			button_cancel.render(gc, g);
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
		if(health > 0) {
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
		

		for(Tower t : towerList) {
			if(t instanceof ShootingTower) {
				ShootingTower st = (ShootingTower) t;
				if(st.getTarget() == entity) {
					st.clearTarget();
				}
			}
		}
	}
}
