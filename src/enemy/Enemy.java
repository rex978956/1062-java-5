package enemy;

import org.newdawn.slick.Animation;
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
    int[] duration;
    int angle;
    private Game game;
    private Vector2f position;
    private int tileposx, tileposy;
    private int health, maxhp, radius;
    private float speed;
    private boolean isFlying, isBoss;
    private float slowValue;
    private Image[] texture;
    private Animation enemy, movingUp, movingDown, movingLeft, movingRight;
    private AStarPathFinder pathfinder;
    private Point targetPoint;

    public Enemy(Game game, Vector2f position, int hp, float speed, boolean isFlying, boolean isBoss, Image[] texture) {
        this.game = game;

        this.position = position;
        this.tileposx = (int) Math.floor((position.x) / 48);
        this.tileposy = (int) Math.floor((position.y) / 48);

        this.health = hp;
        this.maxhp = hp;
        this.radius = 20;
        this.speed = speed;
        this.slowValue = 1;
        this.isFlying = isFlying;
        this.isBoss = isBoss;
        this.texture = texture;

        Image[] walkUp = {texture[0], texture[1], texture[2], texture[3]};
        Image[] walkDown = {texture[4], texture[5], texture[6], texture[7]};
        Image[] walkLeft = {texture[8], texture[9], texture[10], texture[11]};
        Image[] walkRight = {texture[12], texture[13], texture[14], texture[15]};

        duration = new int[]{(int) (200 * slowValue), (int) (200 * slowValue), (int) (200 * slowValue), (int) (200 * slowValue)};

        movingUp = new Animation(walkUp, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        enemy = movingRight;

        this.targetPoint = game.getMap().getBase();
        this.pathfinder = new AStarPathFinder(game.getMap(), 200, false);
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

    public boolean isFlying() {
        return isFlying;
    }

    public void setSlowValue(float slowValue) {
        if (slowValue < this.slowValue)
            this.slowValue = slowValue;
    }

    public void update(int delta) {

        if (Math.abs(tileposx * 48 + 24 - position.x) >= 48) {
            tileposx = (int) Math.floor((position.x) / 48);
        } else if (Math.abs(tileposy * 48 + 24 - position.y) >= 48) {
            tileposy = (int) Math.floor((position.y) / 48);
        }

        Path p = pathfinder.findPath(this, tileposx, tileposy, (int) targetPoint.getX(), (int) targetPoint.getY());
        if (p != null) {
            if (p.getLength() > 0) {
                Step step = p.getStep(1);
                Vector2f nextPoint = new Vector2f(step.getX() - tileposx, step.getY() - tileposy);
                nextPoint = nextPoint.scale((delta / 1000.f) * 48 * speed * slowValue);
                position.add(nextPoint);
                angle = (int) new Vector2f(nextPoint.getX(), nextPoint.getY()).getTheta();
                if (angle > 315 || angle <= 45) {
                    enemy = movingRight;
                } else if (angle > 45 && angle <= 135) {
                    enemy = movingDown;
                } else if (angle > 135 && angle <= 225) {
                    enemy = movingLeft;
                } else if (angle > 225 && angle <= 315) {
                    enemy = movingUp;
                }
                slowValue = 1;
            }
        } else {
            Point base = game.getMap().getBase();
            if (base.getX() == tileposx && base.getY() == tileposy) {
                game.removeEntity(this);
                game.setHealth(game.getHealth() - ((isBoss) ? 5 : 1));
            }
        }
    }

    public void render(Graphics g) {
        if (isBoss) {
            enemy.draw(position.x - texture[0].getWidth() / 2, position.y - texture[0].getHeight() / 2);
        } else {
            enemy.draw(position.x - texture[0].getWidth() / 2, position.y - texture[0].getHeight() / 2);
        }

        g.setColor(Color.red);
        g.fillRect(position.x - 18, position.y - 24, 36, 2);

        g.setColor(Color.green);
        float width = 36 * ((float) health / (float) maxhp);
        if (width > 0) {
            g.fillRect(position.x - 18, position.y - 24, (int) width, 2);
        }
    }
}



