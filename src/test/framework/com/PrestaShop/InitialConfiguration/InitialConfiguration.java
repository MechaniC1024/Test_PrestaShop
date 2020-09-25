package com.PrestaShop.InitialConfiguration;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.*;
import org.testng.ITestContext;
import org.testng.annotations.*;

import io.qameta.allure.Step;

public class InitialConfiguration {

	private RemoteWebDriver driver;
	private Map<String, List<InstanceDriver.Driver>> mapOfBrowsers;

	protected PageGenerator page;

	private String browserSuite;

	@Step("Браузер на котором выполняются тесты {browser}.")
	@BeforeSuite(description = "Инициализация браузера.")
	@Parameters({ "browser", "threadCountFirefox", "threadCountChrome" })
	public void setUp(@Optional("Chrome") String browser, @Optional("1") String threadCountFirefox,
			@Optional("1") String threadCountChrome, ITestContext cont) {

		browserSuite = browser;

		DesiredCapabilities cap = Browsers.valueOf(browser.toUpperCase()).getBrowser();

		switch (browser) {
		case "Chrome": {
			InstanceDriver.INSTANCE_DRIVER.addBrowser(browser, Integer.parseInt(threadCountChrome), cap);
			cont.getCurrentXmlTest().setThreadCount(Integer.valueOf(threadCountChrome));
			break;
		}
		case "Firefox": {
			InstanceDriver.INSTANCE_DRIVER.addBrowser(browser, Integer.parseInt(threadCountFirefox), cap);
			cont.getCurrentXmlTest().setThreadCount(Integer.valueOf(threadCountFirefox));
			break;
		}
		}

		mapOfBrowsers = InstanceDriver.INSTANCE_DRIVER.getMapDriver();
	}

	@BeforeTest
	public void beforeTest(ITestContext context) {

		String browser = context.getCurrentXmlTest().getParameter("browser");

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browser);

		bre: while (true) {
			cont: for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getFlag() == true) {
					list.get(i).setFlag(false);
					driver = list.get(i).getDriver();

					if (driver != null) {
						page = new PageGenerator(driver);
					} else {
						mapOfBrowsers.get(browser).remove(i);
					}

					break bre;
				} else
					continue cont;
			}
		}
	}

	@AfterTest
	public void afterTest(ITestContext context) {

		String browser = context.getCurrentXmlTest().getParameter("browser");

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browser);

		for (int i = 0; i < list.size(); i++) {

			if (driver == list.get(i).getDriver())
				list.get(i).setFlag(true);
			if (driver == null) {
				mapOfBrowsers.get(browser).remove(i);
			}
		}
	}

	public RemoteWebDriver getDriver() {

		return driver;
	}

	@AfterSuite
	public void afterSuite() {

		List<InstanceDriver.Driver> list = mapOfBrowsers.get(browserSuite);

		for (int i = 0; i < list.size(); i++) {
			RemoteWebDriver webDriver = list.get(i).getDriver();
			if (webDriver != null)
				webDriver.quit();
		}
		mapOfBrowsers.remove(browserSuite);
	}
}
