package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class GroundTower extends ShootingTower {

    public GroundTower(Point position, Game game) {
        super(position, 2,
                new int[]{16, 18, 24},
                new int[]{1400, 1300, 1100},
                new int[]{200, 250, 300},
                new int[]{150, 200, 300},
                new Image[]{
                        ImageManager.getImage(ImageManager.GROUND_TOWER),
                        ImageManager.getImage(ImageManager.GROUND_TOWER),
                        ImageManager.getImage(ImageManager.GROUND_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_GREEN), game);
    }

    @Override
    public boolean canTarget(Enemy entity) {
        return !entity.isFlying();
    }
}



