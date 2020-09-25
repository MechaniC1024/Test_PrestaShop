package com.PrestaShop.Cookies;

import static com.PrestaShop.Wait.Wait.waitingForInvisibilityOfElementLocated;
import static com.PrestaShop.Wait.Wait.waitingForVisibilityOfElementLocated;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.PrestaShop.Admin.LoginPage;
import com.PrestaShop.DataResources.GetLoginAndPassword;
import com.PrestaShop.DataResources.GetPathToCookie;
import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WriteCookies {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		GetLoginAndPassword loginAndPasword = ConfigFactory.create(GetLoginAndPassword.class);
		GetPathToCookie pathToCookie = ConfigFactory.create(GetPathToCookie.class);

		By loading = By.xpath("//span[@id='ajax_running']");

		RemoteWebDriver driver = new ChromeDriver(new ChromeOptions().setHeadless(true));

		new PageGenerator(driver).getInstance(LoginPage.class).signIn().inputLogin(loginAndPasword.login())
				.inputPassword(loginAndPasword.password()).clickOnLoginButton();

		waitingForVisibilityOfElementLocated(driver, loading);
		waitingForInvisibilityOfElementLocated(driver, loading);

		Set<Cookie> cookies = driver.manage().getCookies();
		File fileCookies = new File(pathToCookie.getPathToCookie());

		try (FileOutputStream fileWriteCookies = new FileOutputStream(fileCookies);
				ObjectOutputStream writeCookies = new ObjectOutputStream(fileWriteCookies);) {

			fileCookies.delete();
			fileCookies.createNewFile();

			writeCookies.writeInt(cookies.size());

			for (Cookie cookie : cookies) {
				writeCookies.writeObject(cookie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (driver != null)
			driver.quit();
	}
}