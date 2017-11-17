package defined;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Temp {

	@Test
	public void C01(){
		String path = "F:/ImageRealStore/zzz.png";
		Image src = null;
		try {
			src = ImageIO.read(new File(path));
		} 
		catch (IOException e) {
			System.out.println("ImageIO read的时候出问题");
			e.printStackTrace();
		}
		BufferedImage tag = new BufferedImage(270, 150, BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src.getScaledInstance(270, 150, Image.SCALE_SMOOTH), 0, 0, null);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("F:/ImageRealStore/zzz.png"));
		} 
		catch (FileNotFoundException e) {
			System.out.println("fos出错");
			e.printStackTrace();
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		try {
			encoder.encode(tag);
		} 
		catch (ImageFormatException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
