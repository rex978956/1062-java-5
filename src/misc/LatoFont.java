package misc;

import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;

public class LatoFont {
	private static TrueTypeFont lato14, lato22, lato32;
	private static Color color;
	static {
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font/Lato.ttf");
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			lato14 = new TrueTypeFont(awtFont2.deriveFont(14f),true);
			lato22 = new TrueTypeFont(awtFont2.deriveFont(22f),true);
			lato32 = new TrueTypeFont(awtFont2.deriveFont(32f), true);
			
			color = Color.decode("#2988ff");
	 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void draw(int x, int y, String str, int size) {
		draw(x,y,str,size,color);
	}

	public static void draw(int x, int y, String str, int size, Color color) {
		if(size == 14) {
			lato14.drawString(x, y, str, color);
		} else if(size == 22) {
			lato22.drawString(x, y, str, color);
		} else if(size == 32) {
			lato32.drawString(x, y, str, color);
		}
	}
}
