package enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import states.Game;

public class Enemy implements Mover {
	private Game game;
	
	private Vector2f position;

	private int tileposx, tileposy;
	
	private int health, maxhp, radius;
	private float speed;
	private boolean isBoss;
	private float slowValue;
	private Image texture;
	
	private AStarPathFinder pathfinder;
	private Point targetPoint;

    public Enemy(Game game, Vector2f position, int hp, float speed, boolean isBoss, Image texture) {
		this.game = game;
		
		this.position = position;
		this.tileposx = (int)Math.floor((position.x)/48);
		this.tileposy = (int)Math.floor((position.y)/48);
		
		this.health = hp;
		this.maxhp = hp;
		this.radius = 20;
		this.speed = speed;
		this.slowValue = 1;
		this.isBoss = isBoss;
		this.texture = texture;
		
		if(!isBoss) {
			texture.setCenterOfRotation(24, 24);
			radius = 10;
		}

		//this.targetPoint = game.getMap().getBase();
		//this.pathfinder = new AStarPathFinder(game.getMap(), 200, false);
	}
	
	public int getHealth() {
	    return health;
	}

	public void setHealth(int health) {
	    this.health = health;
	}

	public int getRadius() {
	    return radius;
	}

	public Vector2f getPosition() {
	    return position;
	}

	public void update(int delta) {

		if(Math.abs(tileposx*48+24-position.x) >= 48) {
			tileposx = (int)Math.floor((position.x)/48);
		} else if(Math.abs(tileposy*48+24-position.y) >= 48) {
			tileposy = (int)Math.floor((position.y)/48);
		}

        float rotation = (delta / 1000.0f) * 200 * slowValue;
		texture.rotate(rotation);

		Path p = pathfinder.findPath(this, tileposx, tileposy , (int)targetPoint.getX(), (int)targetPoint.getY());
		if(p != null) {
			/*如果路徑上有下一個點*/
			if(p.getLength() > 0) {
				/*創建一個向量到下一個點，用delta對其進行縮放並設置新的位置*/
				Step step = p.getStep(1);
				Vector2f nextPoint = new Vector2f(step.getX() - tileposx, step.getY() - tileposy);			
				nextPoint = nextPoint.scale((delta/1000.f)*48*speed*slowValue);
				position.add(nextPoint);
				
				/*移動後，slowValue 將再次init*/
				slowValue = 1;
			}
		}
		/*當對手在基地*/
//		else {
//			Point base = game.getMap().getBase();
//			if(base.getX() == tileposx && base.getY() == tileposy) {
//				/*消除敵人+扣血*/
//				game.removeEntity(this);
//				game.setHealth(game.getHealth() - ((isBoss)? 5 : 1));
//			}
//		}
	}

	public void render(Graphics g) {
		if(isBoss) {
			texture.drawCentered(position.x, position.y);
		} else {
			texture.draw(position.x-24, position.y-24, 48, 48);
		}

		g.setColor(Color.red); /*血條顏色*/
		g.fillRect(position.x-18, position.y-24, 36, 2);
		
		g.setColor(Color.green); /*血條顏色*/
		float width = 36*((float)health/(float)maxhp);
		if(width > 0) {	
			g.fillRect(position.x-18, position.y-24, (int)width, 2);	
		}
	}
}



