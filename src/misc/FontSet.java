package misc;

import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;

public class FontSet {
    private static TrueTypeFont gnyrwn18, gnyrwn26, gnyrwn34, gnyrwn42, gnyrwn50, gnyrwn58;
    private static Color color;
    static{
        try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/gnyrwn.ttf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            gnyrwn18 = new TrueTypeFont(awtFont2.deriveFont(18F),true);
            gnyrwn26 = new TrueTypeFont(awtFont2.deriveFont(26F),true);
            gnyrwn34 = new TrueTypeFont(awtFont2.deriveFont(34F),true);
            gnyrwn42 = new TrueTypeFont(awtFont2.deriveFont(42F),true);
            gnyrwn50 = new TrueTypeFont(awtFont2.deriveFont(50F),true);
            gnyrwn58 = new TrueTypeFont(awtFont2.deriveFont(58F),true);

            color = Color.decode("#2988ff");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void draw(String str, int x, int y, int size) {
        draw(str,x,y,size,color);
    }

    public static void draw(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            gnyrwn18.drawString(x, y, str, color);
        } else if(size == 26) {
            gnyrwn26.drawString(x, y, str, color);
        } else if(size == 34) {
            gnyrwn34.drawString(x, y, str, color);
        } else if(size == 42) {
            gnyrwn42.drawString(x, y, str, color);
        } else if(size == 50) {
            gnyrwn50.drawString(x, y, str, color);
        } else if(size == 58) {
            gnyrwn58.drawString(x, y, str, color);
        }
    }

}
