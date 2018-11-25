package heroadn.maverickhunter.TheFinalChatDown.Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Convert {
	
	public static byte[] toBytes(String path) {
		BufferedImage image = null;
		ByteArrayOutputStream bos = null;
		try {
			image = ImageIO.read(new File(path));
			bos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", bos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bos.toByteArray();
	}
	
	public static BufferedImage toImage(byte[] img) {
		InputStream is = new ByteArrayInputStream(img);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return image;
	}
}
