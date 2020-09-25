package com.PrestaShop.Cookies;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.PrestaShop.DataResources.GetPathToCookie;
import com.PrestaShop.DataResources.GetUrl;

public class ReadCookies {

	public static void readCookies(RemoteWebDriver driver) {
		
		GetPathToCookie pathToCookie = ConfigFactory.create(GetPathToCookie.class);
		GetUrl url = ConfigFactory.create(GetUrl.class);
		
		driver.manage().deleteAllCookies();
		
		File fileCookies = new File(pathToCookie.getPathToCookie());

		int i;
		Set<Cookie> cookies = new HashSet<>();
		
		try (FileInputStream fileReadCookies = new FileInputStream(fileCookies);
				ObjectInputStream readCookies = new ObjectInputStream(fileReadCookies);) {

			i = readCookies.readInt();
			while (i-- > 0) {
				cookies.add((Cookie) readCookies.readObject());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		cookies.forEach(cookie -> driver.manage().addCookie(cookie));
		driver.get(url.urlAdmin());
	}
}