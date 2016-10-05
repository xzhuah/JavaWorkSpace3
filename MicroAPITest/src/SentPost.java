import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;


public class SentPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			System.out.println(Image2Byte("D:/1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			byte[] a=Image2Byte("D:/1.jpg");
			System.out.println(a.length);
			System.out.println(formUpload("http://139.129.40.103:5678/Ecospy/servlet/main",a));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}

	}
	public static String formUpload(String urlStr,byte[] image) {
        String res = "";
        HttpURLConnection conn = null;
       // String BOUNDARY = "---------------------------123821742118716"; //boundary����requestͷ���ϴ��ļ����ݵķָ���
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(300000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            //conn.setRequestProperty("Content-Type",
             //       "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Request-Type","Resolve-Image");
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text

            out.write(image);

            // ��ȡ��������
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("����POST�������" );
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }
	
	public static byte[] Image2Byte(String filePath) throws IOException{
		/*
		 * In this function the first part shows how to convert an
		 * image file to byte array. 
		 * The second part of the code shows how to change byte array
		 * back to a image
		 */
		File file = new File(filePath);
		System.out.println(file.exists() + "!!");
		 
		FileInputStream fis = new FileInputStream(file);
		//create FileInputStream which obtains input bytes from a file in a file system
		//FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
		 
		//InputStream in = resource.openStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
		    for (int readNum; (readNum = fis.read(buf)) != -1;) {
		        bos.write(buf, 0, readNum); 
		        //no doubt here is 0
		        //Writes len bytes from the specified byte array starting at offset 
		       // off to this byte array output stream.
		        //System.out.println("read " + readNum + " bytes,");
		    }
		} catch (IOException ex) {
		   // Logger.getLogger(ConvertImage.class.getName()).log(Level.SEVERE, null, ex);
		}
		byte[] bytes = bos.toByteArray();
		//bytes is the ByteArray we need
		 
		 return bytes;
		
	}

}
