package towers;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Bullet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import states.Game;
import enemy.Enemy;

public abstract class ShootingTower extends Tower {
	private Image projectile; //攻擊img
	
	private int[] damage, shootInterval;
	private int lastShot;
	private int angle;
	
	private Enemy target;
	private ArrayList<Bullet> bullets; //子彈
	
	private int killCount;

	ShootingTower(Point position, int[] damage, int[] shootInterval, int[] range, int[] cost, Image[] textures, Image projectile, Game game) {
		super(position, range, cost, textures, game);
		this.projectile = projectile;
		this.damage = damage;
		this.shootInterval = shootInterval;		
		this.bullets = new ArrayList<>();
	}

	private Enemy lookForTarget() {
		ArrayList<Enemy> entitiesInRange = getEntitiesInRange();

		if(!entitiesInRange.isEmpty()) {	
			

			int index = -1;
			

			for(int i = 0; i < entitiesInRange.size();i++) {
				if(canTarget(entitiesInRange.get(i))) {

					if(index == -1 || entitiesInRange.get(i).getHealth() < entitiesInRange.get(index).getHealth()) {
						index = i;
					}
				}
			}

			if(index != -1) {
				return entitiesInRange.get(index);
			}
		}
		return null;
	}
	
	public Enemy getTarget() {
		return target;
	}

	public void clearTarget() {
		target = null;
		for(Bullet b : bullets) {
			b.setTarget(null);
		}
	}
	
	public int getDamage() {
		return damage[upgradeLevel];
	}


	@Override
	public void update(GameContainer gc, int delta) {
		lastShot += delta;

		if(target != null) {

			angle = (int)new Vector2f(target.getPosition().getX()-position.getX(),target.getPosition().getY() - position.getY()).getTheta();

			if(lastShot >= shootInterval[upgradeLevel]) {
				bullets.add(new Bullet(new Vector2f(position.getX(),position.getY()),new Vector2f(angle), target,projectile.copy()));
				lastShot = 0;
			}

			double distanceToTarget = Math.sqrt(Math.pow(Math.abs(target.getPosition().getX()-position.getX()), 
					2)+Math.pow(Math.abs(target.getPosition().getY()-position.getY()), 2));
			if(distanceToTarget > getRange()) { //超過範圍 就 不是目標所以 null
				target = null;
			}
		} else {
			target = lookForTarget(); //反之就是找到目標
		}
		

		Iterator<Bullet> iterator = bullets.iterator();
		while(iterator.hasNext()){
           	Bullet b = iterator.next();
           	
            b.update(delta);
            
            if(b.getLifeTime() > 10000) {
				iterator.remove();
			}
            

            if(b.hitsTarget()) {
            	

            	Enemy t = b.getTarget();
            	t.setHealth(t.getHealth()-damage[upgradeLevel]);
            	

            	if(t.getHealth() <= 0) {
            		game.removeEntity(t);
            		//game.setGold(game.getGold()+game.getMap().getKillMoney());
            		killCount++;
            	}
            	
            	iterator.remove();
            }
        }
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Image tower = this.textures[upgradeLevel];
		tower.setRotation(angle);
		tower.drawCentered(position.getX(), position.getY());

		for(Bullet b : bullets) {
			b.render();
		}
	}
}

