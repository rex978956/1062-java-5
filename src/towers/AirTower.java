package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class AirTower extends ShootingTower {

    public AirTower(Point position, Game game) {
        super(position, 3,
                new int[]{10, 13, 16},
                new int[]{600, 500, 400},
                new int[]{150, 200, 250},
                new int[]{150, 200, 300},
                new Image[]{
                        ImageManager.getImage(ImageManager.AIR_TOWER),
                        ImageManager.getImage(ImageManager.AIR_TOWER),
                        ImageManager.getImage(ImageManager.AIR_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_LASER), game);
    }

    public boolean canTarget(Enemy entity) {
        return entity.isFlying();
    }
}



