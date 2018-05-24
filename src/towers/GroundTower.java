package towers;

import enemy.Enemy;
import main.ImageManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import states.Game;

public class GroundTower extends ShootingTower {

	public GroundTower(Point position,Game game) {
		super(position,
				new int[]{16,18,24}, //傷害
				new int[]{1400,1300,1100}, //攻擊間隔
				new int[]{200,250,300}, //範圍
				new int[]{150,200,300}, //cost
				new Image[]{
					ImageManager.getImage(ImageManager.GROUND_TOWER_1),
					ImageManager.getImage(ImageManager.GROUND_TOWER_2),
					ImageManager.getImage(ImageManager.GROUND_TOWER_3)		
				},
				ImageManager.getImage(ImageManager.PROJECTILE_GREEN), game);
	}

	@Override
	public boolean canTarget(Enemy entity) {
		return !entity.isFlying();
	}
}



