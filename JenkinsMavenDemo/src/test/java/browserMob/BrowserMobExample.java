package browserMob;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;

public class BrowserMobExample {
	
	String driverPath = System.getProperty("user.dir")+"//Drivers//chromedriver";
	String sFileName = System.getProperty("user.dir")+"/SeleniumEasy.har";
	
	public WebDriver driver;
	public BrowserMobProxy proxy;
	
	@Test
	public void test() throws Exception {
		File harFile = new File(sFileName);

	System.setProperty("webdriver.chrome.driver", driverPath);
	DesiredCapabilities capabilities = new DesiredCapabilities();
	BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy
	Proxy seleniumProxy = getSeleniumProxy(proxy);
	capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
	WebDriver driver = new ChromeDriver(capabilities);
	proxy.newHar(); // creating new HAR
	driver.get("http://www.facebook.com");
	Har har = proxy.getHar();
	List<HarEntry> entries = proxy.getHar().getLog().getEntries();
	try {
		har.writeTo(harFile);
	} catch (IOException ex) {
		 System.out.println (ex.toString());
	     System.out.println("Could not find file " + sFileName);
	}
	
	
	for (HarEntry entry : entries) {
	System.out.println(entry.getRequest().getUrl());
	}
	if (driver != null) {
		proxy.stop();
		driver.quit();
	}
//	proxy.stop();
//	driver.close();
	}
	
	
	public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
	Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
	try {
	String hostIp = Inet4Address.getLocalHost().getHostAddress();
	seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
	seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
	} catch (UnknownHostException e) {
	e.printStackTrace();
	Assert.fail("invalid Host Address");
	}
	return seleniumProxy;
	}
	public BrowserMobProxy getProxyServer() {
	BrowserMobProxy proxy = new BrowserMobProxyServer();
	proxy.setTrustAllServers(true);
	proxy.start();
	return proxy;
	}
}