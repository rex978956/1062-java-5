package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class MachineTower extends ShootingTower {

    public MachineTower(Point position, Game game) {
        super(position, 1,
                new int[]{8, 9, 10},
                new int[]{800, 650, 500},
                new int[]{150, 200, 250},
                new int[]{150, 200, 300},
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



