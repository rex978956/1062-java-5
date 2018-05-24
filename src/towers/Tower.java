package towers;

import java.util.ArrayList;

import enemy.Enemy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import states.Game;

public abstract class Tower {
	protected Game game; //contanier
	
	protected Point position; //方向
	
	int upgradeLevel;
	private int[] range, cost;
	Image[] textures;

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
	
	public Point getTilePosition() { //找座標點
		return new Point(
			(int)Math.floor(position.getX()/48),
			(int)Math.floor(position.getY()/48)
		);
	}

	public void setPosition(Point position) {
		this.position = position;
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
		return (upgradeLevel < 2)? cost[upgradeLevel+1] : 0;
	}

    ArrayList<Enemy> getEntitiesInRange() {
		ArrayList<Enemy> entityList = game.getEntityList();
		ArrayList<Enemy> entitiesInRange = new ArrayList<>();
		for(Enemy entity: entityList) {

			Vector2f entityPos = entity.getPosition();

			double distance = Math.sqrt(Math.pow(Math.abs(entityPos.x-position.getX()), 2)+Math.pow(Math.abs(entityPos.y-position.getY()), 2));
			if(distance <= getRange()) {
				entitiesInRange.add(entity);
			}
		}
		return entitiesInRange;
	}


	public abstract boolean canTarget(Enemy entity);

	public abstract void update(GameContainer gc, int delta);

	public abstract void render(GameContainer gc, Graphics g);
}




