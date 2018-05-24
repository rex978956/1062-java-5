package towers;

import main.ImageManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import enemy.Enemy;
import states.Game;

public class NormalTower extends ShootingTower {

	public NormalTower(Point position, Game game) {
		super(position,
				new int[]{8,9,10}, //Schaden //傷害
				new int[]{800,650,500}, //Schussintervall 攻擊間隔
				new int[]{150,200,250}, //範圍
				new int[]{150,200,300}, //cost
				new Image[]{
					ImageManager.getImage(ImageManager.NORMAL_TOWER_1),
					ImageManager.getImage(ImageManager.NORMAL_TOWER_2),
					ImageManager.getImage(ImageManager.NORMAL_TOWER_3)		
				},
				ImageManager.getImage(ImageManager.PROJECTILE_BLUE), game);
	}

	@Override
	public boolean canTarget(Enemy entity) {
		return true;
	}
}



