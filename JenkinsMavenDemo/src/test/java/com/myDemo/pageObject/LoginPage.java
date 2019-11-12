package com.myDemo.pageObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.myDemo.utilities.CSVFileReader;

public class LoginPage {

	private static final String USER_NAME = "Username_";
	private static final String PASSWORD = "Password_";
	private static final String SEARCH_PARAMETER = "SearchParameter_";
	private static final String CLICK_MAIL = "ClickMail_";
	private static final String CLICK_MAIL_ITEM = "ClickMailItem_";
	private static final String WAIT_TIME = "WaitTime_";
	private static final String PROXY = "Proxy_";



	static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	static int totalTestCaseCount;

	public static List<String> keyCount;

	public static void main(String[] args) throws NumberFormatException, InterruptedException, UnknownHostException {
		System.out.println(System.getProperty("user.dir"));
		String filePath = System.getProperty("user.dir")+"//TestExecutionDriver.csv";
		CSVFileReader csv = new CSVFileReader(filePath);
		map = csv.openCSVReader();
		totalTestCaseCount = CSVFileReader.keyCount.size();
		String driverPath = System.getProperty("user.dir")+"//Drivers//chromedriver";
		System.out.println(driverPath);
		for (int i = 1; i <= totalTestCaseCount; i++) {
			//set Proxy
			Proxy proxy =new Proxy();
			proxy.setHttpProxy(map.get(PROXY + i)).setFtpProxy(map.get(PROXY + i)).setSslProxy(map.get(PROXY + i));
			System.out.println(map.get(PROXY + i));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.PROXY, proxy);
			cap.setVersion("Mozilla/5.0 (Linux; Android 6.0.1; CPH1607 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.111 Mobile Safari/537.36");
			System.setProperty("webdriver.chrome.driver",
					driverPath);

//			 ChromeOptions options = new ChromeOptions();
//		        options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
//		        options.addArguments("--start-maximized");
		      WebDriver  driver = new ChromeDriver(cap);
			
//			WebDriver driver = new ChromeDriver(cap);
			
			
			driver.manage().window().maximize();
			driver.get("https://www.netflix.com");
//			Mozilla/5.0 (Linux; Android 6.0.1; CPH1607 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.111 Mobile Safari/537.36
			
//			driver.get(
//					"https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
			System.out.println(userAgent);
			
//			driver.findElement(By.id("identifierId")).sendKeys(map.get(USER_NAME + i));
//			driver.findElement(By.xpath("//*[@id=\'identifierNext\']")).click();
//			TimeUnit.SECONDS.sleep(Integer.valueOf(map.get(WAIT_TIME+i)));
//			driver.findElement(By.name("password")).sendKeys(map.get(PASSWORD + i));
//			driver.findElement(By.xpath("//*[@id=\'passwordNext\']")).click();
//			TimeUnit.SECONDS.sleep(Integer.valueOf(map.get(WAIT_TIME+i)));
//			driver.findElement(By.xpath("//input[@placeholder='Search mail']")).sendKeys(map.get(SEARCH_PARAMETER + i));
//			driver.findElement(By.xpath("//button[@class='gb_mf gb_nf']")).click();

//			driver.findElement(By.xpath("//tr[@id=':82']")).click();

			// driver.findElement(By.xpath("//div[@id=':2w']//span[@class=\"bA4\"]")).click();

			// driver.findElement(By.linkText("//a[@href='https://www.google.com/']")).click();

//			driver.close();

		}

	}
}