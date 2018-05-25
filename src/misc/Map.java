package misc;

import java.util.ArrayList;

import enemy.Enemy;
import states.Game;
import towers.Tower;
import main.ImageManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Map implements TileBasedMap {
	private String name;
	private Image preview;
	private TiledMap map;
	private int startMoney, killMoney, waveMoney;
	private int waveHealthMultiplier;
	private int startHpGround, startHpAir, startHpGroundBoss, startHpAirBoss; /****/
	private ArrayList<Point> spawnList;
	private Point base;
	private ArrayList<Integer[]> waveList;
	
	private Game game;
	private int[][] towerList = new int[22][15];

    Map(TiledMap map, Image preview, String name,
        int startMoney, int killMoney, int waveMoney,
        int startHpGround, int startHpAir, int startHpGroundBoss, int startHpAirBoss,
        int waveHealthMultiplier, ArrayList<Point> spawnList, Point base, ArrayList<Integer[]> waveList) {
		this.preview = preview;
		this.map = map;
		this.name = name;
		this.startMoney = startMoney;
		this.killMoney = killMoney;
		this.waveMoney = waveMoney;
		this.startHpGround = startHpGround;
		this.startHpAir = startHpAir;
		this.startHpGroundBoss = startHpGroundBoss;
        this.startHpAirBoss = startHpAirBoss;
		this.waveHealthMultiplier = waveHealthMultiplier;
		this.spawnList = spawnList;
		this.base = base;
		this.waveList = waveList;
    }

	public void resetTowerList() {
		this.towerList = new int[22][15];
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	public void setTower(int x, int y, boolean isTower) {
		towerList[x][y] = (isTower)? 1 : 0; 
	}

	public boolean isTower(int x, int y) {
		return towerList[x][y] == 1;
	}

	public String getName() {
		return name;
	}

	public Image getPreview() {
		return preview;
	}

    public int getStartMoney() {
        return startMoney;
    }

	public int getKillMoney() {
		return killMoney;
	}

	public int getWaveMoney() {
		return waveMoney;
	}

	public ArrayList<Point> getSpawnList() {
		return spawnList;
	}

	public Point getBase() {
		return base;
	}

	public ArrayList<Integer[]> getWaveList() {
		return waveList;
	}
	
	public Integer[] getWaveUnits(int wave) {
		return (wave <= waveList.size())? waveList.get(wave-1): null;
	}

	@Override
	public boolean blocked(PathFindingContext context, int x, int y) {
		if(map.getTileId(x, y, 1) == 0)
			return true;
		
		if(towerList[x][y] != 0)
			return true;

		Mover mover = context.getMover();
		if(mover == null) {
			Tower buyTower = game.getBuyTower();
			if(buyTower != null) {
				Point towerPos = buyTower.getTilePosition();
                return x == towerPos.getX() && y == towerPos.getY();
			}
		}
		return false;
	}

	@Override
	public float getCost(PathFindingContext context, int x, int y) {
		return 1.0f;
	}

	@Override
	public int getHeightInTiles() {
		return map.getHeight();
	}

	@Override
	public int getWidthInTiles() {
		return map.getWidth();
	}

	public TiledMap getMap() {
		return map;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	public ArrayList<Enemy> getEntityList(int wave) {
		ArrayList<Enemy> entityList = new ArrayList<>();

		if(wave <= waveList.size() && wave > 0) {
			Integer[] numbers = waveList.get(wave-1);
			
			int spawn = 0;

			for(int g = 0; g < numbers[0];g++) {
				if(spawn < spawnList.size()-1) {
					spawn++;
				} else {
					spawn = 0;
				}

				Point spawnPoint = spawnList.get(spawn);

				entityList.add(new Enemy(game, new Vector2f(spawnPoint.getX()*48+24,spawnPoint.getY()*48+24),
						startHpGround+(wave)*waveHealthMultiplier, 1.5f, false, ImageManager.getImage(ImageManager.ENEMY_GROUND)));
			}

			for(int g = 0; g < numbers[1];g++) {
				if(spawn < spawnList.size()-1) {
					spawn++;
				} else {
					spawn = 0;
				}

				Point spawnPoint = spawnList.get(spawn);

				entityList.add(new Enemy(game, new Vector2f(spawnPoint.getX()*48+24,spawnPoint.getY()*48+24),
						startHpAir+(wave)*waveHealthMultiplier, 2, true, ImageManager.getImage(ImageManager.ENEMY_AIR)));
			}
			for(int g = 0; g < numbers[2];g++) {
				if(spawn < spawnList.size()-1) {
					spawn++;
				} else {
					spawn = 0;
				}

				Point spawnPoint = spawnList.get(spawn);

				entityList.add(new Enemy(game, new Vector2f(spawnPoint.getX()*48+24,spawnPoint.getY()*48+24),
                        startHpGroundBoss+(wave)*waveHealthMultiplier, 1.5f, false,ImageManager.getImage(ImageManager.ENEMY_GROUND)));
			}
			return entityList;
		}
		return null;
	}

}

