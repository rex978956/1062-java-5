package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class AntiaircraftTower extends ShootingTower {

    public AntiaircraftTower(Point position, Game game) {
        super(position, 6,
                new int[]{45, 70, 95},
                new int[]{1500, 1400, 1300},
                new int[]{250, 300, 350},
                new int[]{200, 300, 400},
                new Image[]{
                        ImageManager.getImage(ImageManager.ANTIAIRCRAFA_TOWER),
                        ImageManager.getImage(ImageManager.ANTIAIRCRAFA_TOWER),
                        ImageManager.getImage(ImageManager.ANTIAIRCRAFA_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_ROCKET), game);
    }

    public boolean canTarget(Enemy entity) {
        return entity.isFlying();
    }
}



