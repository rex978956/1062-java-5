package misc;

import enemy.Enemy;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
    private Vector2f position, direction;
    private Enemy target;
    private Image texture;
    private int radius;

    private int lifeTime;

    public Bullet(Vector2f position, Vector2f direction, Enemy target, Image texture) {
        this.position = position;
        this.direction = direction;
        this.target = target;
        this.texture = texture;
        this.radius = 12;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void update(int delta) {
        lifeTime += delta;
        if (target != null)
            direction = new Vector2f(target.getPosition().x - position.x, target.getPosition().y - position.y).normalise();
        position.add(direction.copy().scale((delta / 1000f) * 500));
    }

    public void render() {
        texture.setRotation(180 + (float) direction.getTheta());
        texture.drawCentered(position.x, position.y);
    }

    public boolean hitsTarget() {
        if (target != null) {
            Vector2f enemyPos = target.getPosition();
            double distance = Math.sqrt(Math.pow(Math.abs(enemyPos.x - position.getX()), 2) + Math.pow(Math.abs(enemyPos.y - position.getY()), 2));
            int targetRadius = target.getRadius();
            return distance < (targetRadius + radius);
        }
        return false;
    }
}




