package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class AntiaircraftTower extends ShootingTower {

    public AntiaircraftTower(Point position, Game game) {
        super(position, 6,
                new int[]{7, 8, 9},
                new int[]{600, 500, 400},
                new int[]{150, 200, 250},
                new int[]{150, 200, 300},
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



