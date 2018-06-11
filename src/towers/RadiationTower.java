package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class RadiationTower extends Tower {

    private float[] slowValues;

    public RadiationTower(Point position, Game game) {
        super(position,
                new int[]{110, 170, 230},
                new int[]{200, 250, 350},
                new Image[]{
                        ImageManager.getImage(ImageManager.RADIATION_TOWER),
                        ImageManager.getImage(ImageManager.RADIATION_TOWER),
                        ImageManager.getImage(ImageManager.RADIATION_TOWER)
                }, game);
        slowValues = new float[]{0.6f, 0.5f, 0.4f};
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
        return true;
    }
}



