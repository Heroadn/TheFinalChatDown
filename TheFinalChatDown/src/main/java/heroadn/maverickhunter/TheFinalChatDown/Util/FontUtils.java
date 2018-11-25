package heroadn.maverickhunter.TheFinalChatDown.Util;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FontUtils{

	@Path
	public static Font loadFont(String name,int size) {
		String path = Path.font_path+name;
		
		Font font = null;
	
		try {
			InputStream input = new FileInputStream(path);
			
			Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, input);
			Font ttfReal = ttfBase.deriveFont(Font.PLAIN, size);
			
			font = ttfReal;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return font;
	}
	
}
