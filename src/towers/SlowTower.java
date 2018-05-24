package towers;

import enemy.Enemy;
import main.ImageManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import states.Game;

public class SlowTower extends Tower {
	
	private float[] slowValues;

	public SlowTower(Point position, Game game) {
		super(position,
				new int[]{1000,1000,1000}, //範圍
				new int[]{150,200,300}, //cost
				new Image[]{
					ImageManager.getImage(ImageManager.SLOW_TOWER_1),
					ImageManager.getImage(ImageManager.SLOW_TOWER_2),
					ImageManager.getImage(ImageManager.SLOW_TOWER_3)		
				}, game);
		slowValues = new float[]{0.70f,0.6f,0.5f};
	}
	
	public float getSlowValue() {
		return slowValues[upgradeLevel];
	}

	@Override
	public void update(GameContainer gc, int time) {
		for(Enemy entity : this.getEntitiesInRange()) {
			if(canTarget(entity))
				entity.setSlowValue(slowValues[upgradeLevel]);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Image tower = this.textures[upgradeLevel];
		tower.drawCentered(position.getX(), position.getY());
	}

	@Override
	public boolean canTarget(Enemy entity) {
		return !entity.isFlying();
	}
}



