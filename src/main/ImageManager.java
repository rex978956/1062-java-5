package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class ImageManager {

    public static final int NORMAL_TOWER = 0;
    public static final int GROUND_TOWER = 1;
    public static final int AIR_TOWER= 2;
    public static final int SLOW_TOWER = 3;
    public static final int ARTILLERY_TOWER = 4;
    public static final int MACHINE_TOWER = 5;
    public static final int ANTIAIRCRAFA_TOWER = 6;
    public static final int RADIATION_TOWER = 7;

    public static final int PROJECTILE_BLUE = 8;
    public static final int PROJECTILE_GREEN = 9;
    public static final int PROJECTILE_BULLET = 10;
    public static final int PROJECTILE_ARMOR = 11;
    public static final int PROJECTILE_ROCKET = 12;
    public static final int PROJECTILE_LASER = 13;

    public static final int MENU_BACKGROUND = 14;
    public static final int MENU_BUTTON_PLAY = 15;
    public static final int MENU_BUTTON_EXIT = 16;
    public static final int MENU_TEXT_CHOOSEMAP = 17;
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
    public static final int GAME_GEM = 28;
    public static final int GAME_SCORE_BOARD = 29;
    public static final int GAME_SUBMIT = 30;


    /*Emeny*/
    public static final int BLUESOLDIER = 0;
    public static final int GREENSOLDIER = 1;
    public static final int PURPLESSOLDIER = 2;
    public static final int YELLOWELEPHANT = 3;
    public static final int REDELEPHANT = 4;
    public static final int BLUETIGER = 5;
    public static final int YELLOWTIGER = 6;
    public static final int FAT = 7;
    public static final int SKELETON = 8;
    public static final int DEMON = 9;
    public static final int ORANGEDRAGON = 10;
    public static final int BEE = 11;
    public static final int PHOENIX = 12;
    public static final int BLUEDRAGON = 13;
    public static final int SKELETONDRAGON = 14;
    public static final int DRAGONDRIVER = 15;

    private static ArrayList<Image> imageList;

    static {
        imageList = new ArrayList<>();

        try {
            /*Tower*/
            imageList.add(new Image("res/images/towers/Normal.png")); //0
            imageList.add(new Image("res/images/towers/Ground.png")); //1
            imageList.add(new Image("res/images/towers/Air.png")); //2
            imageList.add(new Image("res/images/towers/Slow.png")); //3
            imageList.add(new Image("res/images/towers/Artillery.png")); //4
            imageList.add(new Image("res/images/towers/Machine.png")); //5
            imageList.add(new Image("res/images/towers/Antiaircraft.png")); //6
            imageList.add(new Image("res/images/towers/Radiation.png")); //7

            /*Projectiles*/
            imageList.add(new Image("res/images/projectiles/projectile-blue.png")); //8
            imageList.add(new Image("res/images/projectiles/projectile-green.png")); //9
            imageList.add(new Image("res/images/projectiles/bullet.png")); //10
            imageList.add(new Image("res/images/projectiles/Armor.png")); //11
            imageList.add(new Image("res/images/projectiles/Rocket.png")); //12
            imageList.add(new Image("res/images/projectiles/Laser.png")); //13

            /*Menu*/
            imageList.add(new Image("res/images/menus/background.png")); //14
            imageList.add(new Image("res/images/menus/button-play.png")); //15
            imageList.add(new Image("res/images/menus/button-exit.png")); //16
            imageList.add(new Image("res/images/menus/text-choose-map.png")); //17
            imageList.add(new Image("res/images/menus/button-arrow-left.png")); //18
            imageList.add(new Image("res/images/menus/button-arrow-right.png")); //19

            /*Game*/
            imageList.add(new Image("res/images/menus/game-sidebarbg.png")); //20
            imageList.add(new Image("res/images/menus/info.png")); //21
            imageList.add(new Image("res/images/menus/button-upgrade.png")); //22
            imageList.add(new Image("res/images/menus/button-sell.png")); //23
            imageList.add(new Image("res/images/menus/button-startwave.png")); //24
            imageList.add(new Image("res/images/menus/button-quitgame.png")); //25
            imageList.add(new Image("res/images/menus/button-cancel.png")); //26
            imageList.add(new Image("res/images/menus/button-retry.png")); //27
            imageList.add(new Image("res/images/menus/gem.png")); //28
            imageList.add(new Image("res/images/menus/score_board.png")); //29
            imageList.add(new Image("res/images/menus/button-submit.png")); //30


        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    private static Image[] BluesoldierList;

    static {
        BluesoldierList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                BluesoldierList[i] = new Image("res/images/enemy/bluesoldier/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] GreensoldierList;

    static {
        GreensoldierList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                GreensoldierList[i] = new Image("res/images/enemy/greensoldier/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] PurplesoldierList;

    static {
        PurplesoldierList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                PurplesoldierList[i] = new Image("res/images/enemy/purplesoldier/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    private static Image[] YellowelephantList;

    static {
        YellowelephantList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                YellowelephantList[i] = new Image("res/images/enemy/yellowelephant/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] RedelephantList;

    static {
        RedelephantList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                RedelephantList[i] = new Image("res/images/enemy/redelephant/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] BluetigerList;

    static {
        BluetigerList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                BluetigerList[i] = new Image("res/images/enemy/bluetiger/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] YellowtigerList;

    static {
        YellowtigerList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                YellowtigerList[i] = new Image("res/images/enemy/yellowtiger/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] FatList;

    static {
        FatList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                FatList[i] = new Image("res/images/enemy/fat/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] SkeletonList;

    static {
        SkeletonList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                SkeletonList[i] = new Image("res/images/enemy/skeleton/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] DemonList;

    static {
        DemonList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                DemonList[i] = new Image("res/images/enemy/demon/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] OrangeDragonList;

    static {
        OrangeDragonList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                OrangeDragonList[i] = new Image("res/images/enemy/orangedragon/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] BeeList;

    static {
        BeeList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                BeeList[i] = new Image("res/images/enemy/bee/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] PhoenixList;

    static {
        PhoenixList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                PhoenixList[i] = new Image("res/images/enemy/phoenix/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] BlueDragonList;

    static {
        BlueDragonList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                BlueDragonList[i] = new Image("res/images/enemy/bluedragon/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] SkeletonDragonList;

    static {
        SkeletonDragonList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                SkeletonDragonList[i] = new Image("res/images/enemy/skeletondragon/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static Image[] DragonDriverList;

    static {
        DragonDriverList = new Image[16];

        try {
            for (int i = 0; i < 16; i++) {
                DragonDriverList[i] = new Image("res/images/enemy/dragondriver/" + i + ".png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Image getImage(int id) {
        return imageList.get(id).copy();
    }

    public static Image[] getEnemyImage(int id) {
        switch (id) {
            case BLUESOLDIER:
                return BluesoldierList;
            case GREENSOLDIER:
                return GreensoldierList;
            case PURPLESSOLDIER:
                return PurplesoldierList;
            case YELLOWELEPHANT:
                return YellowelephantList;
            case REDELEPHANT:
                return RedelephantList;
            case BLUETIGER:
                return BluetigerList;
            case YELLOWTIGER:
                return YellowtigerList;
            case FAT:
                return FatList;
            case SKELETON:
                return SkeletonList;
            case DEMON:
                return DemonList;
            case ORANGEDRAGON:
                return OrangeDragonList;
            case BEE:
                return BeeList;
            case PHOENIX:
                return PhoenixList;
            case BLUEDRAGON:
                return BlueDragonList;
            case SKELETONDRAGON:
                return SkeletonDragonList;
            case DRAGONDRIVER:
                return DragonDriverList;
            default:
                return BluesoldierList;
        }
    }
}
