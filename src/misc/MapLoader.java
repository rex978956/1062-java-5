package misc;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Map Loader can load maps to game.
 */
public class MapLoader {

    public static ArrayList<Map> loadMaps() {

        ArrayList<Map> mapList = new ArrayList<>();
        File[] files = new File("res/maps").listFiles();

        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                File xml = new File(file.getPath() + "/properties.xml");
                File mapFile = new File(file.getPath() + "/map.tmx");
                File preview = new File(file.getPath() + "/preview.png");

                if (xml.exists() && mapFile.exists()) {
                    TiledMap tiledMap = null;
                    Image previewImage = null;

                    try {
                        if (preview.exists()) {
                            previewImage = new Image(preview.getPath());
                        } else {
                            //TODO: Load the Image of the map that doesn't have preview
                            previewImage = new Image(preview.getPath());
                        }

                        tiledMap = new TiledMap(mapFile.getPath());
                    } catch (SlickException e) {
                        e.printStackTrace();
                        System.out.println("Loaded Preview failed!");
                    }

                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

                    try {
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document doc = documentBuilder.parse(xml);
                        Element root = doc.getDocumentElement();

                        /* Load basic settings */
                        String mapName = root.getElementsByTagName("Name").item(0).getTextContent();
                        int startMoney = Integer.parseInt(root.getElementsByTagName("StartMoney").item(0).getTextContent());
                        int killMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerKill").item(0).getTextContent());
                        int waveMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerWave").item(0).getTextContent());
                        int startHpGround = Integer.parseInt(root.getElementsByTagName("BaseHealthGround").item(0).getTextContent());
                        int startHpAir = Integer.parseInt(root.getElementsByTagName("BaseHealthAir").item(0).getTextContent());
                        int startHpBoss = Integer.parseInt(root.getElementsByTagName("BaseHealthBoss").item(0).getTextContent());
                        int waveHpMultiplier = Integer.parseInt(root.getElementsByTagName("HealthMultiplier").item(0).getTextContent());

                        /* Spawn Positions */
                        ArrayList<Point> spawnList = new ArrayList<>();
                        NodeList spawnNodes = root.getElementsByTagName("Spawn");
                        for (int i = 0; i < spawnNodes.getLength(); i++) {
                            Element spawn = (Element) spawnNodes.item(i);
                            int x = Integer.parseInt(spawn.getElementsByTagName("PosX").item(0).getTextContent());
                            int y = Integer.parseInt(spawn.getElementsByTagName("PosY").item(0).getTextContent());
                            spawnList.add(new Point(x, y));
                        }

                        /* Base Position */
                        Element baseNode = (Element) root.getElementsByTagName("Base").item(0);
                        int baseX = Integer.parseInt(baseNode.getElementsByTagName("PosX").item(0).getTextContent());
                        int baseY = Integer.parseInt(baseNode.getElementsByTagName("PosX").item(0).getTextContent());
                        Point base = new Point(baseX, baseY);

                        /* Load Wave Settings */
                        ArrayList<Integer[]> waveList = new ArrayList<>();
                        NodeList waveNodes = root.getElementsByTagName("Wave");
                        for (int i = 0; i < waveNodes.getLength(); i++) {
                            Element wave = (Element) waveNodes.item(i);
                            int groundUnits = Integer.parseInt(wave.getElementsByTagName("Ground").item(0).getTextContent());
                            int airUnits = Integer.parseInt(wave.getElementsByTagName("Air").item(0).getTextContent());
                            int bossUnits = Integer.parseInt(wave.getElementsByTagName("Boss").item(0).getTextContent());
                            waveList.add(new Integer[]{groundUnits, airUnits, bossUnits});
                        }

                        /* Add new map to the list */
                        mapList.add(new Map(tiledMap, previewImage, mapName, startMoney, killMoney, waveMoney, startHpGround, startHpAir, startHpBoss, startHpAir, waveHpMultiplier, spawnList, base, waveList));

                        System.out.println("Map: " + String.format("%1$-12s", mapName) + "loaded successful!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Map loaded failed!");
                }
            }
        }
        return mapList;
    }
}
