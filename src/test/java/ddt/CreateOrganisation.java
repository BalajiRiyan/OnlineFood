package ddt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisation {
	public static WebDriver driver;
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata/Vtiger.property");
		Properties p = new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String un = p.getProperty("username");
		String pw = p.getProperty("password");


		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pw);
		driver.findElement(By.id("submitButton")).click();

		Random ran = new Random();
		int random = ran.nextInt(1000);

		FileInputStream fiss = new FileInputStream("./src/test/resources/commondata/VTiger.xlsx");
		Workbook wb = WorkbookFactory.create(fiss);
		Sheet sh = wb.getSheet("createorganisation");
		int count = sh.getLastRowNum();
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i = 0;i<=count;i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt = 'Create Organization...']")).click();

		for(Entry<String, String> set :map.entrySet())
		{
			if (set.getKey().contains("accountname")) 
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+random);
			}
			else
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
			}
		}
		driver.findElement(By.xpath("(//input[@title = 'Save [Alt+S]'])[1]")).click();
		String expectedoutput = "Organization";
		String actualoutput = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actualoutput.contains(expectedoutput))
		{
		System.out.println("Organisation created sucessfully");
		}
		else
		{
			System.out.println("Organisation not created");
		}
		driver.quit();
	}
}
