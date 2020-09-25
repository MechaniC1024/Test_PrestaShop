package com.PrestaShop.tests;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.PrestaShop.Admin.LoginPage;
import com.PrestaShop.Admin.UserPage;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 2.")
@Feature("Проверка титулов страниц сайта.")
public class Titles extends InitialConfiguration {

	private String title = "";
	private String titleRefresh = "";
	
	private UserPage userPage;
	
	
	@BeforeGroups(value = "title", description = "Вход при помощи Cookies.", alwaysRun = true)
	public void signIn() {
		
		userPage = page.getInstance(LoginPage.class).enterWithCookie();
	}

	@Story("Проверка титула страницы на вкладке \"Dashboard\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Dashboard\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDashboard() {

		title = userPage.clickOnDashboard().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Заказы\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Заказы\" после обновления страницы.", alwaysRun = true)
	public void checkTitleOrder() {

		title = userPage.clickOnOrders().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Каталог\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Каталог\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCatalog() {

		title = userPage.clickOnCatalog().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Клиенты\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Клиенты\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCustomer() {

		title = userPage.clickOnCustomer().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Служба поддержки\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Служба поддержки\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCustomerService() {

		title = userPage.clickOnCustomerService().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Статистика\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Статистика\" после обновления страницы.", alwaysRun = true)
	public void checkTitleStatistics() {

		title = userPage.clickOnStatistics().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Modules\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Modules\" после обновления страницы.", alwaysRun = true)
	public void checkTitleModules() {

		title = userPage.clickOnModules().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Design\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Design\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDesign() {

		title = userPage.clickOnDesign().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Delivery\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Delivery\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDelivery() {

		title = userPage.clickOnDelivery().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Способ оплаты\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Способ оплаты\" после обновления страницы.", alwaysRun = true)
	public void checkTitlePayment() {

		title = userPage.clickOnPayment().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"International\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"International\" после обновления страницы.", alwaysRun = true)
	public void checkTitleInternational() {

		title = userPage.clickOnInternational().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Shop Parameters\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Shop Parameters\" после обновления страницы.", alwaysRun = true)
	public void checkTitleShopParameters() {

		title = userPage.clickOnShopParameters().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Story("Проверка титула страницы на вкладке \"Конфигурация\".")
	@Test(groups = "title", description = "Проверка титула страницы на вкладке \"Конфигурация\" после обновления страницы.", alwaysRun = true)
	public void checkTitleAdvancedParameters() {

		title = userPage.clickOnAdvancedParameters().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}
}