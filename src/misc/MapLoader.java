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

        /* Make an ArrayList of Maps */
        ArrayList<Map> mapList = new ArrayList<>();
        File[] files = new File("res/maps").listFiles();

        /* Read Map files */
        for (File file : Objects.requireNonNull(files)) {
            /* One map per directory */
            if (file.isDirectory()) {
                File xml = new File(file.getPath() + "/properties.xml");
                File mapFile = new File(file.getPath() + "/map.tmx");
                File preview = new File(file.getPath() + "/preview.png");

                /* XML is the map settings */
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
                        /* Money settings */
                        int startMoney = Integer.parseInt(root.getElementsByTagName("StartMoney").item(0).getTextContent());
                        int killMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerKill").item(0).getTextContent());
                        int waveMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerWave").item(0).getTextContent());

                        /* Health settings */
                        int startHpBluesoldier = Integer.parseInt(root.getElementsByTagName("BaseHpBlueSoldier").item(0).getTextContent());
                        int startHpGreensoldier = Integer.parseInt(root.getElementsByTagName("BaseHpGreenSoldier").item(0).getTextContent());
                        int startHpPurplesoldier = Integer.parseInt(root.getElementsByTagName("BaseHpPurpleSoldier").item(0).getTextContent());
                        int startHpYellowelephant = Integer.parseInt(root.getElementsByTagName("BaseHpYellowElephant").item(0).getTextContent());
                        int startRedelephant = Integer.parseInt(root.getElementsByTagName("BaseHpRedElephant").item(0).getTextContent());
                        int startHpBluetiger = Integer.parseInt(root.getElementsByTagName("BaseHpBlueTiger").item(0).getTextContent());
                        int startHpYellowtiger = Integer.parseInt(root.getElementsByTagName("BaseHpYellowTiger").item(0).getTextContent());
                        int startHpSkeleton = Integer.parseInt(root.getElementsByTagName("BaseHpSkeleton").item(0).getTextContent());
                        int startHpFat = Integer.parseInt(root.getElementsByTagName("BaseHpFat").item(0).getTextContent());
                        int startHpDemon = Integer.parseInt(root.getElementsByTagName("BaseHpDemon").item(0).getTextContent());
                        int startHpOrangeDragon = Integer.parseInt(root.getElementsByTagName("BaseHpOrangeDragon").item(0).getTextContent());

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
                        int baseY = Integer.parseInt(baseNode.getElementsByTagName("PosY").item(0).getTextContent());
                        Point base = new Point(baseX, baseY);

                        /* Load Wave Settings */
                        ArrayList<Integer[]> waveList = new ArrayList<>();
                        NodeList waveNodes = root.getElementsByTagName("Wave");
                        for (int i = 0; i < waveNodes.getLength(); i++) {
                            Element wave = (Element) waveNodes.item(i);
                            int BluesoldierUnits = Integer.parseInt(wave.getElementsByTagName("BlueSoldier").item(0).getTextContent());
                            int GreensoldierUnits = Integer.parseInt(wave.getElementsByTagName("GreenSoldier").item(0).getTextContent());
                            int PurplesoldierUnits = Integer.parseInt(wave.getElementsByTagName("PurpleSoldier").item(0).getTextContent());
                            int YellowelephantUnits = Integer.parseInt(wave.getElementsByTagName("YellowElephant").item(0).getTextContent());
                            int RedelephantUnits = Integer.parseInt(wave.getElementsByTagName("RedElephant").item(0).getTextContent());
                            int BluetigerUnits = Integer.parseInt(wave.getElementsByTagName("BlueTiger").item(0).getTextContent());
                            int YellowtigerUnits = Integer.parseInt(wave.getElementsByTagName("YellowTiger").item(0).getTextContent());
                            int FatUnits = Integer.parseInt(wave.getElementsByTagName("Fat").item(0).getTextContent());
                            int SkeletonUnits = Integer.parseInt(wave.getElementsByTagName("Skeleton").item(0).getTextContent());
                            int DemonUnits = Integer.parseInt(wave.getElementsByTagName("Demon").item(0).getTextContent());
                            int OrangeDragonUnits = Integer.parseInt(wave.getElementsByTagName("OrangeDragon").item(0).getTextContent());


                            waveList.add(new Integer[]{BluesoldierUnits, GreensoldierUnits, PurplesoldierUnits,
                                    YellowelephantUnits,RedelephantUnits,BluetigerUnits,YellowtigerUnits,
                                    FatUnits,SkeletonUnits,DemonUnits,OrangeDragonUnits});
                        }

                        /* Add new map to the list */
                        mapList.add(new Map(tiledMap, previewImage, mapName, startMoney, killMoney, waveMoney,
                                startHpBluesoldier, startHpGreensoldier, startHpPurplesoldier,
                                startHpYellowelephant, startRedelephant, startHpBluetiger, startHpYellowtiger,
                                startHpFat, startHpSkeleton, startHpDemon, startHpOrangeDragon,
                                waveHpMultiplier, spawnList, base, waveList));

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
