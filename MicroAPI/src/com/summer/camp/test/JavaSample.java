package com.summer.camp.test;
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JavaSample 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/vision/v1.0/tag");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "a69c03eb83a44ec789045ffee811a228");
            

            // Request body
            String str=Random.readImg("C:/Users/Xinyu/Pictures/ËÞÉáÇø/1.jpg");
            System.out.println(str.length());
            StringEntity reqEntity = new StringEntity(str);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
