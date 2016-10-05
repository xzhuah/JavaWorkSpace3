import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
public class Random {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static String readImg(String f){
		try {
			String imageType=f.substring(f.lastIndexOf(".")+1,f.length());
			BufferedImage bufI = ImageIO.read(new File(f));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//use another encoding if JPG is innappropriate for you
			ImageIO.write(bufI, imageType, baos );
			baos.flush();
			byte[] immAsBytes = baos.toByteArray();
			baos.close();
			return immAsBytes.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
