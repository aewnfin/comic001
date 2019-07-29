package test.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class ImgIOTest {
	public static void main(String[] args) {
		FileOutputStream fos=null;
		try {
			File fis=new File("E:/comic/1/1260_1497244625_ZKc7bCo47OrM.big.jpg");
			BufferedImage image=ImageIO.read(fis);
			fos=new FileOutputStream("H:/comicTest/2.jpg");
			if(image!=null) {
				ImageIO.write(image, "jpg", fos);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos!=null)fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
