package com.PrestaShop.InitialConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum InstanceDriver {

	INSTANCE_DRIVER {

		private Map<String, List<InstanceDriver.Driver>> mapOfDrivers = new HashMap<>();

		public Map<String, List<InstanceDriver.Driver>> getMapDriver() {
			return mapOfDrivers;
		}

		public void addBrowser(String browser, int counterOfBrowser, DesiredCapabilities capabilities) {

			List<InstanceDriver.Driver> listOfDrivers = new ArrayList<>();

			while (listOfDrivers.size() < counterOfBrowser) {

				try {
					RemoteWebDriver driver = new RemoteWebDriver(new URL(System.getProperty("GridOfBrowsers")),
							capabilities);
					driver.manage().window().maximize();

					listOfDrivers.add(new Driver().setDriver(driver).setFlag(true));

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			mapOfDrivers.put(browser, listOfDrivers);
		}
	};

	public void addBrowser(String browser, int counterOfBrowser, DesiredCapabilities capabilities) {};

	public Map<String, List<InstanceDriver.Driver>> getMapDriver() {
		return null;
	};

	static class Driver {
		private boolean flag;
		private RemoteWebDriver driver;

		public boolean getFlag() {
			return flag;
		}

		public Driver setFlag(boolean flag) {
			this.flag = flag;
			return this;
		}

		public RemoteWebDriver getDriver() {
			return driver;
		}

		public Driver setDriver(RemoteWebDriver driver) {
			this.driver = driver;
			return this;
		}
	}
}
