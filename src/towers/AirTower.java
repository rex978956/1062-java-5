package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class AirTower extends ShootingTower {

    public AirTower(Point position, Game game) {
        super(position,
                new int[]{7, 8, 9},
                new int[]{600, 500, 400},
                new int[]{150, 200, 250},
                new int[]{150, 200, 300},
                new Image[]{
                        ImageManager.getImage(ImageManager.AIR_TOWER_1),
                        ImageManager.getImage(ImageManager.AIR_TOWER_2),
                        ImageManager.getImage(ImageManager.AIR_TOWER_3)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_CYAN), game);
    }

    public boolean canTarget(Enemy entity) {
        return entity.isFlying();
    }
}



