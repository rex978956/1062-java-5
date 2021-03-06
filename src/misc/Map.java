package misc;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import states.Game;
import towers.Tower;

import java.util.ArrayList;
import java.util.Arrays;

public class Map implements TileBasedMap {

    /* Settings */
    private Game game;
    private String name;
    private TiledMap map;
    private Image preview;
    private ArrayList<Integer[]> waveList;
    private int[][] towerList = new int[22][15];
    private int startMoney, killMoney, waveMoney;

    /* Positions */
    private Point base;
    private ArrayList<Point> spawnList;

    /* Health settings */
    private int waveHealthMultiplier;
    private int startHpBlueSolider, startHpGreenSolider, startHpPurpleSolider,
            startHpYellowElephant, startHPRedElephant, startHpBlueTiger, startHpYellowTiger,
            startHpFat, startHpSkeleton, startHpDemon, startHpOrangeDragon,
            startHpBee, startHpPhoenix, startHpBlueDragon, startHpSkeletonDragon, startHpDragonDriver;

    public Map(TiledMap map, Image preview, String name,
               int startMoney, int killMoney, int waveMoney,
               int startHpBlueSolider, int startHpGreenSolider, int startHpPurpleSolider,
               int startHpYellowElephant, int startHPRedElephant, int startHpBlueTiger, int startHpYellowTiger,
               int startHpFat, int startHpSkeleton, int startHpDemon, int startHpOrangeDragon,
               int startHpBee, int startHpPhoenix, int startHpBlueDragon, int startHpSkeletonDragon, int startHpDragonDriver,
               int waveHealthMultiplier, ArrayList<Point> spawnList, Point base, ArrayList<Integer[]> waveList) {
        this.preview = preview;
        this.map = map;
        this.name = name;
        this.startMoney = startMoney;
        this.killMoney = killMoney;
        this.waveMoney = waveMoney;
        this.startHpBlueSolider = startHpBlueSolider;
        this.startHpGreenSolider = startHpGreenSolider;
        this.startHpPurpleSolider = startHpPurpleSolider;
        this.startHpYellowElephant = startHpYellowElephant;
        this.startHPRedElephant = startHPRedElephant;
        this.startHpBlueTiger = startHpBlueTiger;
        this.startHpYellowTiger = startHpYellowTiger;
        this.startHpFat = startHpFat;
        this.startHpSkeleton = startHpSkeleton;
        this.startHpDemon = startHpDemon;
        this.startHpOrangeDragon = startHpOrangeDragon;
        this.startHpBee = startHpBee;
        this.startHpPhoenix = startHpPhoenix;
        this.startHpBlueDragon = startHpBlueDragon;
        this.startHpSkeletonDragon = startHpSkeletonDragon;
        this.startHpDragonDriver = startHpDragonDriver;
        this.waveHealthMultiplier = waveHealthMultiplier;
        this.spawnList = spawnList;
        this.base = base;
        this.waveList = waveList;
    }

    public void resetTowerList() {
        this.towerList = new int[22][15];
    }

    public Integer[] getWaveUnits(int wave) {
        return (wave <= waveList.size()) ? waveList.get(wave - 1) : null;
    }

    public void setTower(int x, int y, boolean isTower) {
        towerList[x][y] = (isTower) ? 1 : 0;
    }

    public boolean isTower(int x, int y) {
        return towerList[x][y] == 1;
    }

    /* Setter And Getter */
    // TODO: Remove Unused Functions
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Image getPreview() {
        return preview;
    }

    public void setPreview(Image preview) {
        this.preview = preview;
    }

    public ArrayList<Integer[]> getWaveList() {
        return waveList;
    }

    public void setWaveList(ArrayList<Integer[]> waveList) {
        this.waveList = waveList;
    }

    public int[][] getTowerList() {
        return towerList;
    }

    public void setTowerList(int[][] towerList) {
        this.towerList = towerList;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public int getKillMoney() {
        return killMoney;
    }

    public void setKillMoney(int killMoney) {
        this.killMoney = killMoney;
    }

    public int getWaveMoney() {
        return waveMoney;
    }

    public void setWaveMoney(int waveMoney) {
        this.waveMoney = waveMoney;
    }

    public ArrayList<Point> getSpawnList() {
        return spawnList;
    }

    public void setSpawnList(ArrayList<Point> spawnList) {
        this.spawnList = spawnList;
    }

    public Point getBase() {
        return base;
    }

    public void setBase(Point base) {
        this.base = base;
    }

    public int getWaveHealthMultiplier() {
        return waveHealthMultiplier;
    }

    public void setWaveHealthMultiplier(int waveHealthMultiplier) {
        this.waveHealthMultiplier = waveHealthMultiplier;
    }

    /**
     * Check if the given location is blocked, i.e. blocks movement of
     * the supplied mover.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return True if the location is blocked
     */
    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        /* Tiled Map X and Y and layout 1 is empty */
        if (map.getTileId(tx, ty, 1) == 0)
            return true;
        /* Tower is here or not */
        if (towerList[tx][ty] != 0)
            return true;

        Mover mover = context.getMover();
        if (mover == null) {
            Tower tower = game.getBuyTower();
            if (tower != null) {
                Point position = tower.getTilePosition();
                return tx == position.getX() && ty == position.getY();
            }
        }
        return false;
    }

    /**
     * Get the cost of moving through the given tile. This can be used to
     * make certain areas more desirable. A simple and valid implementation
     * of this method would be to return 1 in all cases.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return The relative cost of moving across the given tile
     */
    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1.0f;
    }

    /**
     * Get the height of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles down the map
     */
    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }

    /**
     * Get the width of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles across the map
     */
    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }

    /**
     * Notification that the path finder visited a given tile. This is
     * used for debugging new heuristics.
     *
     * @param x The x coordinate of the tile that was visited
     * @param y The y coordinate of the tile that was visited
     */
    @Override
    public void pathFinderVisited(int x, int y) {
    }

    /**
     * Get the enemy list and add a new object.
     *
     * @param wave Wave is now playing
     * @return null if wave is outside the waveList
     */
    public ArrayList<Enemy> getEnemyList(int wave) {
        ArrayList<Enemy> enemyList = new ArrayList<>();

        /* Get wave list enemies */
        if (wave <= waveList.size() && wave > 0) {
            /* Monster Waves */
            Integer[] numbers = waveList.get(wave - 1);

            /* Spawn Enemy from 0 to all
             * Serialize run all the spawn points */
            int spawn = 0;

            /* Enemy BlueSoldier */
            for (int i = 0; i < numbers[0]; i++) {
                if (spawn < spawnList.size() - 1) {
                    spawn++;
                } else {
                    spawn = 0;
                }

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpBlueSolider + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.BLUESOLDIER)));
            }
            /* Enemy GreenSoldier */
            for (int i = 0; i < numbers[1]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpGreenSolider + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.GREENSOLDIER)));
            }
            /* Enemy PurpleSolider */
            for (int i = 0; i < numbers[2]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpPurpleSolider + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.PURPLESSOLDIER)));
            }
            /* Enemy YellowElephant */
            for (int i = 0; i < numbers[3]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpYellowElephant + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.YELLOWELEPHANT)));
            }
            /* Enemy RedElephant */
            for (int i = 0; i < numbers[4]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHPRedElephant + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.REDELEPHANT)));
            }
            /* Enemy  BlueTiger */
            for (int i = 0; i < numbers[5]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpBlueTiger + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.BLUETIGER)));
            }
            /* Enemy  YellowTiger */
            for (int i = 0; i < numbers[6]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpYellowTiger + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.YELLOWTIGER)));
            }
            /* Enemy  Fat */
            for (int i = 0; i < numbers[7]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpFat + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.FAT)));
            }
            /* Enemy  Skeleton */
            for (int i = 0; i < numbers[8]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpSkeleton + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.SKELETON)));
            }
            /* Enemy  Demon */
            for (int i = 0; i < numbers[9]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpDemon + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.DEMON)));
            }
            /* Enemy  OrangeDragon */
            for (int i = 0; i < numbers[10]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpOrangeDragon + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, false, false, ImageManager.getEnemyImage(ImageManager.ORANGEDRAGON)));
            }

            /* Enemy  Bee */
            for (int i = 0; i < numbers[11]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpBee + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, true, false, ImageManager.getEnemyImage(ImageManager.BEE)));
            }

            /* Enemy  Phoenix */
            for (int i = 0; i < numbers[12]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpPhoenix + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, true, false, ImageManager.getEnemyImage(ImageManager.PHOENIX)));
            }

            /* Enemy  BlueDragon */
            for (int i = 0; i < numbers[13]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpBlueDragon + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, true, false, ImageManager.getEnemyImage(ImageManager.BLUEDRAGON)));
            }

            /* Enemy  SkeletonDragon */
            for (int i = 0; i < numbers[14]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpSkeletonDragon + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, true, false, ImageManager.getEnemyImage(ImageManager.SKELETONDRAGON)));
            }

            /* Enemy  DragonDriver */
            for (int i = 0; i < numbers[15]; i++) {
                if (spawn < spawnList.size() - 1)
                    spawn++;
                else
                    spawn = 0;

                Point spawnPoint = spawnList.get(spawn);

                int health = startHpDragonDriver + wave * waveHealthMultiplier;
                float speed = 1.5f;

                enemyList.add(new Enemy(game, new Vector2f(spawnPoint.getX() * 48 + 24, spawnPoint.getY() * 48 + 24)
                        , health, speed, true, false, ImageManager.getEnemyImage(ImageManager.DRAGONDRIVER)));
            }

            System.out.println("This wave Enemies: " + Arrays.toString(waveList.get(wave - 1)));
            return enemyList;
        }
        return null;
    }
}
