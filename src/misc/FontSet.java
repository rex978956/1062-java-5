package misc;

import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;

public class FontSet {
    private static TrueTypeFont Akrobat18, Akrobat26, Akrobat34, Akrobat42, Akrobat58, Akrobat73;
    private static Color color;
    static{
        try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Akrobat-Regular.otf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            Akrobat18 = new TrueTypeFont(awtFont2.deriveFont(18F),true);
            Akrobat26 = new TrueTypeFont(awtFont2.deriveFont(26F),true);
            Akrobat34 = new TrueTypeFont(awtFont2.deriveFont(34F),true);
            Akrobat42 = new TrueTypeFont(awtFont2.deriveFont(42F),true);
            Akrobat58 = new TrueTypeFont(awtFont2.deriveFont(58F),true);
            Akrobat73 = new TrueTypeFont(awtFont2.deriveFont(73F),true);

            color = Color.decode("#2988ff");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void draw(String str, int x, int y, int size) {
        draw(str,x,y,size,color);
    }

    private static void draw(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            Akrobat18.drawString(x, y, str, color);
        } else if(size == 26) {
            Akrobat26.drawString(x, y, str, color);
        } else if(size == 34) {
            Akrobat34.drawString(x, y, str, color);
        } else if(size == 42) {
            Akrobat42.drawString(x, y, str, color);
        } else if(size == 58) {
            Akrobat58.drawString(x, y, str, color);
        } else if(size == 73) {
            Akrobat73.drawString(x, y, str, color);
        }
    }

}
