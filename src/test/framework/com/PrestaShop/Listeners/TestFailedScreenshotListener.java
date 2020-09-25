package com.PrestaShop.Listeners;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.google.common.io.Files;

import static com.PrestaShop.Report.Report.*;

public class TestFailedScreenshotListener extends TestListenerAdapter {

	private static ByteArrayInputStream takeScreenshotInStream(RemoteWebDriver driver) {

		WebDriver remoteDriver = new Augmenter().augment(driver);

		byte[] screen = ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.BYTES);

		return new ByteArrayInputStream(screen);
	}

	private static void takeScreenshotInFile(RemoteWebDriver driver, ITestResult result) {

		WebDriver remoteDriver = new Augmenter().augment(driver);

		File screen = ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);

		File outputDirectory = new File(System.getProperty("user.dir") + File.separator + "Screenshot" + File.separator
				+ ((HasCapabilities) remoteDriver).getCapabilities().getBrowserName() + File.separator
				+ result.getTestClass().getName() + File.separator + result.getName());
		try {
			if (outputDirectory.exists() == false)
				outputDirectory.mkdirs();

			Files.copy(screen, new File(outputDirectory, "FailerScreenshot.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		Object currentClass = result.getInstance();
		RemoteWebDriver driver = ((InitialConfiguration) currentClass).getDriver();

		takeScreenshotInFile(driver, result);
		addAttachmentToReport("Скриншот страницы.", takeScreenshotInStream(driver));
	}
}
