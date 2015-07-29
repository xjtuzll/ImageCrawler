package util;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL; 

public class imagedownload {
	public static void downLoadImage(String url,String imagefilename){  
		byte[] btImg = getImageFromNetByUrl(url);  
        if(null != btImg && btImg.length > 0){  
            System.out.println("读取到：" + btImg.length + " 字节");   
            writeImageToDisk(btImg, imagefilename);  
        }else{  
            System.out.println("没有从该连接获得内容");  
        }
	}
	public static void writeImageToDisk(byte[] img, String fileName){  
        try {  
            File file = new File(fileName);
            System.out.println(fileName);
            if(!file.exists()){       
                file.createNewFile();
            } 
            FileOutputStream fops = new FileOutputStream(file);  
            fops.write(img);  
            fops.flush();  
            fops.close();  
            System.out.println(fileName);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	public static byte[] getImageFromNetByUrl(String strUrl){  
        try {  
            URL url = new URL(strUrl);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(15 * 1000);  
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
}
