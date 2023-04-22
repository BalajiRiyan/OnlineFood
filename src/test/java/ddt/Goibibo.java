package ddt;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Goibibo {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		//driver.findElement(By.xpath("//p[@class='sc-jlwm9r-1 dRQhOp']")).click();
		driver.findElement(By.xpath("//span[text()='Round-trip']")).click();
		
		
	}
	
	
	


}
