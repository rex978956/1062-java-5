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
                new int[]{100, 150, 200},
                new int[]{150, 200, 300},
                new Image[]{
                        ImageManager.getImage(ImageManager.SLOW_TOWER),
                        ImageManager.getImage(ImageManager.SLOW_TOWER),
                        ImageManager.getImage(ImageManager.SLOW_TOWER)
                }, game);
        slowValues = new float[]{0.7f, 0.6f, 0.5f};
    }

    public float getSlowValue() {
        return slowValues[upgradeLevel];
    }

    @Override
    public void update(GameContainer gc, int time) {
        for (Enemy entity : this.getEntitiesInRange()) {
            if (canTarget(entity))
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



