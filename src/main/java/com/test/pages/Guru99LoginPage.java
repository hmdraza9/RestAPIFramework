package com.test.pages;

import java.time.Duration;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Guru99LoginPage {

	private static final Logger log = LogManager.getLogger(Guru99LoginPage.class);

	static WebDriver driver;
	private static final String guru99URL = "https://demo.guru99.com/v4/index.php";
	public static String uidxpath = "//input[@name='uid']";
	public static String upassxpath = "//input[@name='password']";
	public static String loginBtn = "//input[@name='btnLogin']";
	public static String homePageLable = "//tr[@class='heading3']/td";// Manger Id : mngr549176
	public static String logoutBtn = "//a[@href='Logout.php']";

	public void browserSetup() {

		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("Setting up Pay Load");
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));// com.youtube.adfree
		driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(guru99URL);
	}

	public void login(String userNameValue, String userPasswordValue) {

		driver.findElement(By.xpath(uidxpath)).sendKeys(userNameValue);
		driver.findElement(By.xpath(upassxpath)).sendKeys(userPasswordValue);
		driver.findElement(By.xpath(loginBtn)).click();

	}

	public void verifyLoginHomeLabel(String label) {

		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		String homeLabelAct = driver.findElement(By.xpath(homePageLable)).getText().replace("Manger Id : ", "");
		org.junit.Assert.assertTrue(homeLabelAct.equals(label));

	}

	public void logout() {

		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.findElement(By.xpath(logoutBtn)).click();
		driver.switchTo().alert().accept();
		driver.quit();
	}

}
