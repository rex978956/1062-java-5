package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import sql.InitDatabase;
import sql.InitTable;
import states.MainMenu;
import states.MapMenu;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class TKUTD extends StateBasedGame {

    private static final int WINDOWED_WIDTH = 1280;
    private static final int WINDOWED_HEIGHT = 800;
    private AppGameContainer container;

    private TKUTD(String name) {
        super(name);
    }

    public static void main(String[] args) {
        new InitDatabase();
        new InitTable();
        try {
            System.setProperty("org.newdawn.slick.pngloader", "false");

            extractNatives();
            extractResources();

            AppGameContainer game = new AppGameContainer(new TKUTD("TKUTD"));
            game.setDisplayMode(1280, 800, false);
            game.setAlwaysRender(true);
            game.setShowFPS(false);
            game.setVSync(true);
            game.start();
        } catch (SlickException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Auto extract all needed natives in the jar, and detect the os you are using.
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void extractNatives() throws NoSuchFieldException, IllegalAccessException {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();

        try {
            File nativesPath = new File("natives/");
            if (!nativesPath.exists() || !nativesPath.isDirectory()) {
                System.out.println(extractFromJar("/natives.zip"));
                zipDecompress("natives.zip", "natives");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (osName.startsWith("windows")) {
            if (osArch.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
                System.setProperty("java.library.path", "natives/windows_64");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            } else {
                System.setProperty("java.library.path", "natives/windows_32");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            }
        } else if (osName.startsWith("linux")) {
            if (osArch.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
                System.setProperty("java.library.path", "natives/linux_64");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            } else {
                System.setProperty("java.library.path", "natives/linux_32");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            }
        } else if (osName.endsWith("osx")) {
            System.setProperty("java.library.path", "natives/osx");
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } else {
            throw new IllegalArgumentException("OS UNKNOWN: " + osName);
        }
    }

    /**
     * Auto extract all game needed resources in the jar file.
     */
    private static void extractResources() {
        try {
            File resPath = new File("res/");
            if (!resPath.exists() || !resPath.isDirectory()) {
                System.out.println(extractFromJar("/res.zip"));
                zipDecompress("res.zip", "res");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     * @see "https://stackoverflow.com/questions/10308221/how-to-copy-file-inside-jar-to-outside-the-jar"
     */
    private static String extractFromJar(String resourceName) throws Exception {
        String jarFolder;
        jarFolder = new File(TKUTD.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
        try (InputStream stream = TKUTD.class.getResourceAsStream(resourceName); OutputStream resStreamOut = new FileOutputStream(jarFolder + resourceName)) {
            byte[] buffer = new byte[4096];
            int readBytes;
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }
            //note that each / is a directory down in the "jar tree" been the jar the root of the tree

            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        }
        return jarFolder + resourceName;
    }

    /**
     * Decompress zip file in the directory, must at the same directory.
     *
     * @param zip   The zip file you want to decompress
     * @param start The file that contain in the zip
     * @throws IOException
     */
    private static void zipDecompress(String zip, String start) throws IOException {
        ZipFile zipFile = new ZipFile(zip);
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            if (zipEntry.getName().startsWith(start)) {
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                int size;
                byte[] buffer = new byte[2048];

                File f = new File(zipEntry.getName());
                if (!zipEntry.isDirectory()) {
                    f.getParentFile().mkdirs();
                    BufferedOutputStream bos = new BufferedOutputStream(
                            new FileOutputStream(zipEntry.getName()), buffer.length);
                    while ((size = bis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, size);
                    }
                    bos.flush();
                    bos.close();
                    bis.close();
                } else {
                    f.mkdirs();
                }
            }
        }
        new File(zip).delete();
    }

    public void initStatesList(GameContainer gc) {
        this.container = (AppGameContainer) gc;
        addState(new MainMenu());
        addState(new MapMenu());
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_F11) {
            try {
                if (container.isFullscreen()) {
                    container.setDisplayMode(WINDOWED_WIDTH, WINDOWED_HEIGHT, false);
                } else {
                    container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), true);
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
}
