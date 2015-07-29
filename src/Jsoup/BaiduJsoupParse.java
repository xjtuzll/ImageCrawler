package Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BaiduJsoupParse {
	public static ArrayList<String> parseByHtml(String htmlpath) {
		ArrayList<String> ImageURLList;
		ImageURLList = new ArrayList<String>();
		Document doc = null;
		try {
			File input = new File(htmlpath);
			doc = Jsoup.parse(input, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements imgitems = doc.getElementsByClass("imgitem");
		Elements imgitems1 = doc.getElementsByClass("imgitem hactive");
		for(org.jsoup.nodes.Element imgitem:imgitems){
			String imglist = imgitem.attr("data-objurl");
			ImageURLList.add(imglist);
		}
//		for(org.jsoup.nodes.Element imgitem:imgitems1){
//			String imglist = imgitem.attr("data-objurl");
//			ImageURLList.add(imglist);
//		}
		return ImageURLList;
	}
	
}
