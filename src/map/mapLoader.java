package map;

import java.io.File;
import java.util.ArrayList;

/**
 * Map Loader can load maps to game.
 */
public class mapLoader {

    public static ArrayList<map> loadMaps() {

        ArrayList<map> mapList = new ArrayList<map>();
        File[] files = new File("res/maps").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

        return mapList;
    }
}
