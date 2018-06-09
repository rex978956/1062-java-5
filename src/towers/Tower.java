package towers;

import enemy.Enemy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import states.Game;

import java.util.ArrayList;

public abstract class Tower {
    protected Game game;

    protected Point position;

    int upgradeLevel;
    Image[] textures;
    private int[] range, cost;

    Tower(Point position, int[] range, int[] cost, Image[] textures, Game game) {
        this.position = position;
        this.range = range;
        this.cost = cost;
        this.textures = textures;
        this.game = game;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getTilePosition() {
        return new Point(
                (int) Math.floor(position.getX() / 48),
                (int) Math.floor(position.getY() / 48)
        );
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public int getRange() {
        return range[upgradeLevel];
    }

    public int getCost() {
        return cost[upgradeLevel];
    }

    public int getCost(int i) {
        return cost[i];
    }

    public int getUpgradeCost() {
        return (upgradeLevel < 2) ? cost[upgradeLevel + 1] : 0;
    }

    ArrayList<Enemy> getEntitiesInRange() {
        ArrayList<Enemy> entityList = game.getEntityList();
        ArrayList<Enemy> entitiesInRange = new ArrayList<>();
        for (Enemy entity : entityList) {

            Vector2f entityPos = entity.getPosition();

            double distance = Math.sqrt(Math.pow(Math.abs(entityPos.x - position.getX()), 2) + Math.pow(Math.abs(entityPos.y - position.getY()), 2));
            if (distance <= getRange()) {
                entitiesInRange.add(entity);
            }
        }

        return entitiesInRange;
    }

    public abstract boolean canTarget(Enemy entity);

    public abstract void update(GameContainer gc, int delta);

    public abstract void render(GameContainer gc, Graphics g);
}
