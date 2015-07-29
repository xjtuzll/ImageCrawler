package webpage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.gargoylesoftware.htmlunit.javascript.host.Element;


public class BaiduWebPage {
	public static void seleniumCrawlerBaiduWeb(String filepath, String word) throws InterruptedException {
		String url = "http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438046631522_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&"
				+ "word="+ word;
		File file = new File(filepath);
		if(!file.exists()){
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);    //5秒内没打开，重新加载
			while (true){
				try{
					driver.get(url);
				}
				catch (Exception e)
				{
					driver.quit();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					continue;
				}
				break;
			}
			System.out.println("Page title is: " + driver.getTitle());
			// roll the page
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			try {
				WebElement webele = driver.findElement(By.id("pageMore"));
				
				for (int m = 0; m < 2; m++) {
					for (int i = 0; i < 10; i++) {
						JS.executeScript("scrollTo(0, document.body.scrollHeight)");
						System.out.println(m * 10 + i + 1);
						Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
					}
					if (m == 1) {
						break;
					}
					webele.click();
				}
				
				String html = driver.getPageSource();
				saveHtml(filepath, html);
				System.out.println("save finish");
				
				// Close the browser
				Thread.sleep(2000);
				driver.quit();
				
			} catch (Exception e) {
				System.out.println("Error at loading the page ...");
				driver.quit();
			}
			// save page
			
		}else{
			System.out.println(filepath + "已经存在，不必再次爬取...");
		}
	}
	
	public static void saveHtml(String filepath, String str) {
		try {
			OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");
			outs.write(str);
			outs.flush();
			outs.close();
		} catch (IOException e) {
			System.out.println("Error at save html...");
			e.printStackTrace();
		}
	}
	
}
