
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JavaSample 
{
    public static void main(String[] args) throws IOException 
    {
    	/*URL u=new URL("https://api.projectoxford.ai/vision/v1.0/tag");
    	HttpURLConnection conn=(HttpURLConnection)u.openConnection();
    	conn.setRequestProperty("Content-Type", "application/octet-stream");
    	conn.setRequestProperty("Ocp-Apim-Subscription-Key", "a69c03eb83a44ec789045ffee811a228");
    	 conn.setDoOutput(true);
         conn.setDoInput(true);
         conn.setUseCaches(false);
         conn.setRequestMethod("POST");
         String str=readImg("D:/1.jpg");
         
         OutputStream out = new DataOutputStream(conn.getOutputStream());
         out.write(str.getBytes());
         StringBuffer strBuf = new StringBuffer();
         BufferedReader reader = new BufferedReader(new InputStreamReader(
                 conn.getInputStream(),"UTF-8"));
         String line = null;
         while ((line = reader.readLine()) != null) {
             strBuf.append(line).append("\n");
         }
         String res = strBuf.toString();
         reader.close();
         reader = null;
         System.out.println(res);*/
    	 
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/vision/v1.0/tag");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "a69c03eb83a44ec789045ffee811a228");
            

            // Request body
            byte[] str=readImg("D:/1.jpg");
          //  System.out.println(str);
            
            ByteArrayEntity reqEntity = new ByteArrayEntity(str);
           
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
    public static byte[] readImg(String f){
		try {
			String imageType=f.substring(f.lastIndexOf(".")+1,f.length());
			
			BufferedImage bufI = ImageIO.read(new File(f));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			ImageIO.write(bufI, imageType, baos );
			baos.flush();
			byte[] immAsBytes = baos.toByteArray();
			baos.close();
			return immAsBytes;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
