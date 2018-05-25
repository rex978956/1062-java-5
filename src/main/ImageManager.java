package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class ImageManager {

    public static final int NORMAL_TOWER_1 = 0;
    public static final int NORMAL_TOWER_2 = 1;
    public static final int NORMAL_TOWER_3 = 2;

    public static final int GROUND_TOWER_1 = 3;
    public static final int GROUND_TOWER_2 = 4;
    public static final int GROUND_TOWER_3 = 5;

    public static final int AIR_TOWER_1 = 6;
    public static final int AIR_TOWER_2 = 7;
    public static final int AIR_TOWER_3 = 8;

    public static final int SLOW_TOWER_1 = 9;
    public static final int SLOW_TOWER_2 = 10;
    public static final int SLOW_TOWER_3 = 11;

    public static final int MENU_BACKGROUND = 12;
    public static final int MENU_LOGO = 13;
    public static final int MENU_TEXT_CHOOSEMAP = 14;

    public static final int MENU_BUTTON_PLAY = 15;
    public static final int MENU_BUTTON_CREDITS = 16;
    public static final int MENU_BUTTON_EXIT = 17;

    public static final int MENU_BUTTON_LARROW = 18;
    public static final int MENU_BUTTON_RARROW = 19;

    public static final int GAME_SIDEBAR_BG = 20;
    public static final int GAME_INFO = 21;
    public static final int GAME_BUTTON_UPGRADE = 22;
    public static final int GAME_BUTTON_SELL = 23;
    public static final int GAME_BUTTON_STARTWAVE = 24;
    public static final int GAME_BUTTON_QUITGAME = 25;
    public static final int GAME_BUTTON_CANCEL = 26;
    public static final int GAME_BUTTON_RETRY = 27;

    public static final int PROJECTILE_BLUE = 28;
    public static final int PROJECTILE_CYAN = 29;
    public static final int PROJECTILE_GREEN = 30;

    public static final int ENEMY_GROUND = 31;
    public static final int ENEMY_AIR = 32;

    public static final int NORMAL_TOWER2_1 = 33;
    public static final int NORMAL_TOWER2_2 = 34;
    public static final int NORMAL_TOWER2_3 = 35;
    public static final int PROJECTILE_OP = 36;
    public static final int ENEMY_BIG = 37;
    public static final int PROJECTILE_ICE = 38;

    private static ArrayList<Image> imageList;

    static {
        imageList = new ArrayList<>();

        try {
            imageList.add(new Image("res/images/towers/CircleTower1.png")); //0
            imageList.add(new Image("res/images/towers/CircleTower2.png")); //1
            imageList.add(new Image("res/images/towers/CircleTower3.png")); //2

            imageList.add(new Image("res/images/towers/HexagonTower1.png")); //3
            imageList.add(new Image("res/images/towers/HexagonTower2.png")); //4
            imageList.add(new Image("res/images/towers/HexagonTower3.png")); //5

            imageList.add(new Image("res/images/towers/PentagonTower1.png")); //6
            imageList.add(new Image("res/images/towers/PentagonTower2.png")); //7
            imageList.add(new Image("res/images/towers/PentagonTower3.png")); //8

            imageList.add(new Image("res/images/towers/GearTower1.png")); //9
            imageList.add(new Image("res/images/towers/GearTower2.png")); //10
            imageList.add(new Image("res/images/towers/GearTower3.png")); //11

            imageList.add(new Image("res/images/menus/background.png")); //12

            imageList.add(new Image("res/images/menus/text-neontd.png")); //13
            imageList.add(new Image("res/images/menus/text-choose-map.png")); //14

            imageList.add(new Image("res/images/menus/button-play.png")); //15
            imageList.add(new Image("res/images/menus/button-credits.png")); //16
            imageList.add(new Image("res/images/menus/button-exit.png")); //17

            imageList.add(new Image("res/images/menus/button-arrow-left.png")); //18
            imageList.add(new Image("res/images/menus/button-arrow-right.png")); //19

            imageList.add(new Image("res/images/menus/game-sidebarbg.png")); //20
            imageList.add(new Image("res/images/menus/info.png")); //21
            imageList.add(new Image("res/images/menus/button-upgrade.png")); //22
            imageList.add(new Image("res/images/menus/button-sell.png")); //23
            imageList.add(new Image("res/images/menus/button-startwave.png")); //24
            imageList.add(new Image("res/images/menus/button-quitgame.png")); //25
            imageList.add(new Image("res/images/menus/button-cancel.png")); //26
            imageList.add(new Image("res/images/menus/button-retry.png")); //27

            imageList.add(new Image("res/images/projectiles/projectile-blue.png")); //28
            imageList.add(new Image("res/images/projectiles/projectile-cyan.png")); //29
            imageList.add(new Image("res/images/projectiles/projectile-green.png")); //30

            imageList.add(new Image("res/images/enemy/enemy-ground.png")); //31
            imageList.add(new Image("res/images/enemy/enemy-air.png")); //32

            imageList.add(new Image("res/images/towers/PentagonTower1.png")); //33
            imageList.add(new Image("res/images/towers/PentagonTower2.png")); //34
            imageList.add(new Image("res/images/towers/PentagonTower3.png")); //35
            imageList.add(new Image("res/images/projectiles/projectile-op.png")); //36
            imageList.add(new Image("res/images/enemy/black-hole2.png")); //37
            imageList.add(new Image("res/images/projectiles/projectile-ice.png")); //38


        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public static Image getImage(int id) {
        return imageList.get(id).copy();
    }
}
