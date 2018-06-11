package towers;

import enemy.Enemy;
import main.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import states.Game;

public class NormalTower extends ShootingTower {

    public NormalTower(Point position, Game game) {
        super(position, 1,
                new int[]{7, 8, 9},
                new int[]{800, 650, 500},
                new int[]{150, 200, 250},
                new int[]{100, 150, 200},
                new Image[]{
                        ImageManager.getImage(ImageManager.NORMAL_TOWER),
                        ImageManager.getImage(ImageManager.NORMAL_TOWER),
                        ImageManager.getImage(ImageManager.NORMAL_TOWER)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_BLUE), game);
    }

    @Override
    public boolean canTarget(Enemy entity) {
        return true;
    }
}



