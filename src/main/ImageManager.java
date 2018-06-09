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

    public static final int NORMAL_TOWER2_1 = 31;
    public static final int NORMAL_TOWER2_2 = 32;
    public static final int NORMAL_TOWER2_3 = 33;
    public static final int PROJECTILE_OP = 34;
    public static final int PROJECTILE_ICE = 35;

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

            imageList.add(new Image("res/images/towers/PentagonTower1.png")); //31
            imageList.add(new Image("res/images/towers/PentagonTower2.png")); //32
            imageList.add(new Image("res/images/towers/PentagonTower3.png")); //33
            imageList.add(new Image("res/images/projectiles/projectile-op.png")); //34
            imageList.add(new Image("res/images/projectiles/projectile-ice.png")); //35


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
            default:
                return BluesoldierList;
        }
    }
}
