package misc;

import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;

public class FontSet {
    private static TrueTypeFont Akrobat18, Akrobat26, Akrobat34, Akrobat42, Akrobat58, Akrobat73;
    private static TrueTypeFont Lucinda18, Lucinda26, Lucinda34, Lucinda42, Lucinda58, Lucinda73;
    private static TrueTypeFont Fontaine18, Fontaine26, Fontaine34, Fontaine42, Fontaine58, Fontaine73;
    private static TrueTypeFont ButterScotch18, ButterScotch26, ButterScotch34, ButterScotch42, ButterScotch58, ButterScotch73;
    private static TrueTypeFont Stylus18, Stylus26, Stylus34, Stylus42, Stylus58, Stylus73;
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


            InputStream inputStream2= ResourceLoader.getResourceAsStream("res/fonts/Lucinda.ttf");

            Font awtFont3 = Font.createFont(Font.TRUETYPE_FONT, inputStream2);
            Lucinda18 = new TrueTypeFont(awtFont3.deriveFont(18F),true);
            Lucinda26 = new TrueTypeFont(awtFont3.deriveFont(26F),true);
            Lucinda34 = new TrueTypeFont(awtFont3.deriveFont(34F),true);
            Lucinda42 = new TrueTypeFont(awtFont3.deriveFont(42F),true);
            Lucinda58 = new TrueTypeFont(awtFont3.deriveFont(58F),true);
            Lucinda73 = new TrueTypeFont(awtFont3.deriveFont(73F),true);


            InputStream inputStream3= ResourceLoader.getResourceAsStream("res/fonts/Fontaine.ttf");

            Font awtFont4 = Font.createFont(Font.TRUETYPE_FONT, inputStream3);
            Fontaine18 = new TrueTypeFont(awtFont4.deriveFont(18F),true);
            Fontaine26 = new TrueTypeFont(awtFont4.deriveFont(26F),true);
            Fontaine34 = new TrueTypeFont(awtFont4.deriveFont(34F),true);
            Fontaine42 = new TrueTypeFont(awtFont4.deriveFont(42F),true);
            Fontaine58 = new TrueTypeFont(awtFont4.deriveFont(58F),true);
            Fontaine73 = new TrueTypeFont(awtFont4.deriveFont(73F),true);


            InputStream inputStream4= ResourceLoader.getResourceAsStream("res/fonts/ButterScotch.ttf");

            Font awtFont5 = Font.createFont(Font.TRUETYPE_FONT, inputStream4);
            ButterScotch18 = new TrueTypeFont(awtFont5.deriveFont(18F),true);
            ButterScotch26 = new TrueTypeFont(awtFont5.deriveFont(26F),true);
            ButterScotch34 = new TrueTypeFont(awtFont5.deriveFont(34F),true);
            ButterScotch42 = new TrueTypeFont(awtFont5.deriveFont(42F),true);
            ButterScotch58 = new TrueTypeFont(awtFont5.deriveFont(58F),true);
            ButterScotch73 = new TrueTypeFont(awtFont5.deriveFont(73F),true);


            InputStream inputStream5= ResourceLoader.getResourceAsStream("res/fonts/Stylus.ttf");

            Font awtFont6 = Font.createFont(Font.TRUETYPE_FONT, inputStream5);
            Stylus18 = new TrueTypeFont(awtFont6.deriveFont(18F),true);
            Stylus26 = new TrueTypeFont(awtFont6.deriveFont(26F),true);
            Stylus34 = new TrueTypeFont(awtFont6.deriveFont(34F),true);
            Stylus42 = new TrueTypeFont(awtFont6.deriveFont(42F),true);
            Stylus58 = new TrueTypeFont(awtFont6.deriveFont(58F),true);
            Stylus73 = new TrueTypeFont(awtFont6.deriveFont(73F),true);

            color = Color.decode("#2988ff");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drawAkrobat(String str, int x, int y, int size) {
        drawAkrobat(str,x,y,size,color);
    }
    private static void drawAkrobat(String str, int x, int y, int size, Color color) {
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
    public static void drawLucinda(String str, int x, int y, int size) {
        drawLucinda(str,x,y,size,color);
    }
    private static void drawLucinda(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            Lucinda18.drawString(x, y, str, color);
        } else if(size == 26) {
            Lucinda26.drawString(x, y, str, color);
        } else if(size == 34) {
            Lucinda34.drawString(x, y, str, color);
        } else if(size == 42) {
            Lucinda42.drawString(x, y, str, color);
        } else if(size == 58) {
            Lucinda58.drawString(x, y, str, color);
        } else if(size == 73) {
            Lucinda73.drawString(x, y, str, color);
        }
    }
    public static void drawFontaine(String str, int x, int y, int size) {
        drawFontaine(str,x,y,size,color);
    }
    private static void drawFontaine(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            Fontaine18.drawString(x, y, str, color);
        } else if(size == 26) {
            Fontaine26.drawString(x, y, str, color);
        } else if(size == 34) {
            Fontaine34.drawString(x, y, str, color);
        } else if(size == 42) {
            Fontaine42.drawString(x, y, str, color);
        } else if(size == 58) {
            Fontaine58.drawString(x, y, str, color);
        } else if(size == 73) {
            Fontaine73.drawString(x, y, str, color);
        }
    }
    public static void drawButterScotch(String str, int x, int y, int size) {
        drawButterScotch(str,x,y,size,color);
    }
    private static void drawButterScotch(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            ButterScotch18.drawString(x, y, str, color);
        } else if(size == 26) {
            ButterScotch26.drawString(x, y, str, color);
        } else if(size == 34) {
            ButterScotch34.drawString(x, y, str, color);
        } else if(size == 42) {
            ButterScotch42.drawString(x, y, str, color);
        } else if(size == 58) {
            ButterScotch58.drawString(x, y, str, color);
        } else if(size == 73) {
            ButterScotch73.drawString(x, y, str, color);
        }
    }
    public static void drawStylus(String str, int x, int y, int size) {
        drawStylus(str,x,y,size,color);
    }
    private static void drawStylus(String str, int x, int y, int size, Color color) {
        if(size == 18) {
            Stylus18.drawString(x, y, str, color);
        } else if(size == 26) {
            Stylus26.drawString(x, y, str, color);
        } else if(size == 34) {
            Stylus34.drawString(x, y, str, color);
        } else if(size == 42) {
            Stylus42.drawString(x, y, str, color);
        } else if(size == 58) {
            Stylus58.drawString(x, y, str, color);
        } else if(size == 73) {
            Stylus73.drawString(x, y, str, color);
        }
    }
}
