package com.myDemo.pageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseMove {

	public static void main(String[] args) throws InterruptedException, AWTException {
//		String driverPath = System.getProperty("user.dir") + "//Drivers//chromedriver";
//		System.setProperty("webdriver.chrome.driver", driverPath);
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://www.netflix.com");
//		TimeUnit.SECONDS.sleep(Integer.valueOf(5));
		int x=100,y=400;
		boolean bool=true;
		Robot robot =new Robot();
		while(true) {
			
			robot.mouseMove(x, y);
			if(bool) {
				x+=5;y+=5;			
			}else {
				x+=5;y-=5;			
			}
			
			if(y==420)bool=false;
			else if(y==300)bool=true;
			Thread.sleep(20);
			
		}
	

	}
	
	public void  moveMouseUsingAction() throws InterruptedException {
		String driverPath = System.getProperty("user.dir") + "//Drivers//chromedriver";
		System.setProperty("webdriver.chrome.driver", driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.get("https://www.netflix.com");
		Actions action = new Actions(driver);
		TimeUnit.SECONDS.sleep(Integer.valueOf(10));
		action.moveByOffset(100, 500).contextClick().perform();
	    System.out.println("Curser movement Performed Successfully");

	}

}
