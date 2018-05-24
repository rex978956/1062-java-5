package towers;

import enemy.Enemy;
import main.ImageManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import states.Game;

public class NormalTower2 extends ShootingTower {

    public NormalTower2(Point position, Game game) {
        super(position,
                new int[]{4,5,6}, //Schaden //傷害
                new int[]{100,80,70}, //Schussintervall 攻擊間隔
                new int[]{300,400,500}, //範圍
                new int[]{150,200,300}, //cost
                new Image[]{
                        ImageManager.getImage(ImageManager.NORMAL_TOWER2_1),
                        ImageManager.getImage(ImageManager.NORMAL_TOWER2_2),
                        ImageManager.getImage(ImageManager.NORMAL_TOWER2_3)
                },
                ImageManager.getImage(ImageManager.PROJECTILE_ICE), game);
    }

    @Override
    public boolean canTarget(Enemy entity) {
        return true;
    }
}



