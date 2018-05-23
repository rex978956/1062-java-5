package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class imageManager {

    public static final int TEST = 0;

    private static ArrayList<Image> imageList;

    static {
        imageList = new ArrayList<Image>();

        try {
            imageList.add(new Image("res/asd.png"));

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Image getImage(int id) {
        return imageList.get(id).copy();
    }
}
