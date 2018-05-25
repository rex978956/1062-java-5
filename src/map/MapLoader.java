package map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Map Loader can load maps to game.
 */
public class MapLoader {

    public static ArrayList<Map> loadMaps() {

        ArrayList<Map> mapList = new ArrayList<Map>();
        File[] files = new File("res/maps").listFiles();


        for (File file : files) {
            if (file.isDirectory()) {
                File xml = new File(file.getPath() + "/properties.xml");
                File mapFile = new File(file.getPath() + "/map.tmx");
                File preview = new File(file.getPath() + "/preview.png");

                if (xml.exists() && mapFile.exists()) {
                    TiledMap tiledMap = null;
                    try {
                        tiledMap = new TiledMap(mapFile.getPath());
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }

                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

                    try {
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document doc = documentBuilder.parse(xml);
                        Element root = doc.getDocumentElement();

                        /* Load basic settings */
                        String mapName = root.getElementsByTagName("Name").item(0).getTextContent();

                        //TODO: Load spawn and base position

                        // TODO: Load Wave Settings

//                        mapList.add(new Map(tiledMap, mapName));

                        System.out.println("Maps Loaded!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // TODO: Make Preview PNG available
                }
            }
        }
        return mapList;
    }
}
