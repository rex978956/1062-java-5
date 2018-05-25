package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class ImagerManager {

    public static final int TEST = 0;

    public static final int BTN_ARROW_LEFT = 1;
    public static final int BTN_ARROW_RIGHT = 2;

    private static ArrayList<Image> imageList;

    static {
        imageList = new ArrayList<Image>();

        try {
            imageList.add(new Image("res/asd.png"));

            imageList.add(new Image("res/images/menus/button-arrow-left.png"));
            imageList.add(new Image("res/images/menus/button-arrow-right.png"));

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Image getImage(int id) {
        return imageList.get(id).copy();
    }
}
