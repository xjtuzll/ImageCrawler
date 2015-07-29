package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Jsoup.BaiduJsoupParse;
import util.imagedownload;
import webpage.BaiduWebPage;

public class BaiduWebPageTest {
	public static void main(String[] args) throws InterruptedException {
		//从百度图库下载图像
		//获得术语对应的网页，并将HTML存到本地;
		String filepath = "imagedata/baidu/HTML";
		File file = new File(filepath);
		if(!file.exists()){
			file.mkdir();
		}
		String word = "binary tree";
		filepath = filepath + "/"+word+".html";
		BaiduWebPage.seleniumCrawlerBaiduWeb(filepath, word);
		
		//解析本地所存储的HTML文件，用Jsoup解析，获取图像的URL;
		ArrayList<String> imageUrlLists = new ArrayList<String>();
		imageUrlLists = BaiduJsoupParse.parseByHtml(filepath);

		//根据图像的URL下载图像到本地;
		filepath = "imagedata/baidu/image";
		file = new File(filepath);
		if(!file.exists()){       
            file.mkdir();
        }
		filepath = filepath + "/" + word;
		file = new File(filepath);
		if(!file.exists()){       
            file.mkdir();
        }
		
		
		int i = 0;
		for(String url:imageUrlLists){
			i++;
			System.out.println(url+":minus");
			
			String imagefilepath = filepath + "/" + i + ".jpeg";
			file = new File(imagefilepath);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			imagedownload.downLoadImage(url,imagefilepath);
			
		}
	}

}
