package misc;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MapLoader {
	public static ArrayList<Map> loadMaps() {
		ArrayList<Map> mapList = new ArrayList<>();
		
		File[] files = new File("res/maps").listFiles();

		for(File file : Objects.requireNonNull(files)) {
			if(file.isDirectory()) {
				File xml = new File(file.getPath()+"/properties.xml");
				File mapFile = new File(file.getPath()+"/map.tmx");
				File preview = new File(file.getPath()+"/preview.png");

				if(xml.exists() && mapFile.exists() && preview.exists()) {
					Image previewImage = null;
					TiledMap tiledMap = null;
					try {
						previewImage = new Image(preview.getPath());
						tiledMap = new TiledMap(mapFile.getPath());
					} catch (SlickException se) {
						se.printStackTrace();
					}

					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					try {
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(xml);
						Element root = doc.getDocumentElement();

						/* Einige Eigenschaften lesen */
						String mapName = root.getElementsByTagName("Name").item(0).getTextContent();
						int startMoney = Integer.parseInt(root.getElementsByTagName("StartMoney").item(0).getTextContent());
						int killMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerKill").item(0).getTextContent());
						int waveMoney = Integer.parseInt(root.getElementsByTagName("MoneyPerWave").item(0).getTextContent());
						int startHpGround = Integer.parseInt(root.getElementsByTagName("BaseHealthGround").item(0).getTextContent());
						int startHpAir = Integer.parseInt(root.getElementsByTagName("BaseHealthAir").item(0).getTextContent());
						int startHpGroundBoss = Integer.parseInt(root.getElementsByTagName("BaseHealthGroundBoss").item(0).getTextContent());
                        int startHpAirBoss = Integer.parseInt(root.getElementsByTagName("BaseHealthAirBoss").item(0).getTextContent()); /*自己加的*/
						int waveHpMultiplier = Integer.parseInt(root.getElementsByTagName("HealthMultiplier").item(0).getTextContent());

						/* Spawnpunkte auslesen */
						ArrayList<Point> spawnList = new ArrayList<>();
						NodeList spawnNodes = root.getElementsByTagName("Spawn");
						for(int i = 0; i < spawnNodes.getLength(); i++) {
							Element spawn = (Element)spawnNodes.item(i);
							int x = Integer.parseInt(spawn.getElementsByTagName("PosX").item(0).getTextContent());
							int y = Integer.parseInt(spawn.getElementsByTagName("PosY").item(0).getTextContent());
							spawnList.add(new Point(x,y));
						}

						/* Basiskoordinaten auslesen */
						Element baseNode = (Element)root.getElementsByTagName("Base").item(0);
						int baseX = Integer.parseInt(baseNode.getElementsByTagName("PosX").item(0).getTextContent());
						int baseY = Integer.parseInt(baseNode.getElementsByTagName("PosY").item(0).getTextContent());
						Point base = new Point(baseX,baseY);

						/* Waves auslesen */
						ArrayList<Integer[]> waveList = new ArrayList<>();
						NodeList waveNodes = root.getElementsByTagName("Wave");
						for(int i = 0; i < waveNodes.getLength(); i++) {
							Element wave = (Element)waveNodes.item(i);
							int groundUnits = Integer.parseInt(wave.getElementsByTagName("Ground").item(0).getTextContent());
							int airUnits = Integer.parseInt(wave.getElementsByTagName("Air").item(0).getTextContent());
							int groundbossUnits = Integer.parseInt(wave.getElementsByTagName("GroundBoss").item(0).getTextContent());
							int airbossUnits = Integer.parseInt(wave.getElementsByTagName("AirBoss").item(0).getTextContent());
							waveList.add(new Integer[]{groundUnits,airUnits,groundbossUnits,airbossUnits});
						}

						System.out.println("Added map "+mapName);
						mapList.add(new Map(tiledMap, previewImage, mapName, startMoney, killMoney, waveMoney, startHpGround, startHpAir, startHpGroundBoss, startHpAirBoss, waveHpMultiplier, spawnList, base, waveList));
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("Could not read map '"+file.getName()+"'");
                    }
				} else {
					System.out.println("Invalid map folder '"+file.getName()+"'");
                }
			}
		}
		return mapList;
	}
}
