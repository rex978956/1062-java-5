package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class MachineTower extends ShootingTower {

    public MachineTower(Point position, Game game) {
        super(position, 4,
                new int[]{15, 18, 21},
                new int[]{500, 375, 250},
                new int[]{170, 220, 270},
                new int[]{200, 300, 400},
                new Image[]{
                        ImageManager.getImage(ImageManager.MACHINE_TOWER),
                        ImageManager.getImage(ImageManager.MACHINE_TOWER),
                        ImageManager.getImage(ImageManager.MACHINE_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_ARMOR), game);
    }

    @Override
    public boolean canTarget(Enemy entity) {
        return true;
    }
}



