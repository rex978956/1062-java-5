package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class ArtilleryTower extends ShootingTower {

    public ArtilleryTower(Point position, Game game) {
        super(position, 5,
                new int[]{8, 10, 12},
                new int[]{200, 150, 100},
                new int[]{200, 250, 300},
                new int[]{200, 300, 400},
                new Image[]{
                        ImageManager.getImage(ImageManager.ARTILLERY_TOWER),
                        ImageManager.getImage(ImageManager.ARTILLERY_TOWER),
                        ImageManager.getImage(ImageManager.ARTILLERY_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_BULLET), game);
    }

    @Override
    public boolean canTarget(Enemy entity) {
        return !entity.isFlying();
    }
}



