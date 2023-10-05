package org.teststeps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.objectrepository.CartPageObjects;
import org.objectrepository.HomePageObject;
import org.objectrepository.ParticularProductPage;
import org.objectrepository.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.utilities.BaseClass;

public class TestFlipcartSteps extends BaseClass {
	public WebDriver driver;
	public HomePageObject homePage = null;
	SearchResultPage searchResultPage = null;
	ParticularProductPage particulatProductPage = null;
	CartPageObjects cartPage = null;
	WebDriverWait wait = null;
	String productFullText = "";

	@Parameters("BrowserName")
	@BeforeSuite(alwaysRun = true)
	private void oneTimeSetup(@Optional("chrome") String browserName) throws Exception {
		browserName = browserName.isEmpty() ? browserName : getValueFromPropertyFile("BrowserName");
		driver = LaunchBrowser(browserName, getValueFromPropertyFile("Url"));
		wait = new WebDriverWait(driver, Duration.ofMinutes(Integer.parseInt(getValueFromPropertyFile("TimeOut"))));
	}

	@Test(priority = 0)
	private void searchAProduct() throws Exception {
		homePage = new HomePageObject(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(homePage.popCloseIcon));
		homePage.getPopCloseButton().click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getHomePageSearchBox()));
		assertTrue(homePage.getFipkartLogo().isDisplayed());
		homePage.getHomePageSearchBox().sendKeys(getValueFromPropertyFile("DeviceName"));
		homePage.getSearchIconButton().click();
	}

	@Test(priority = 1)
	private void selectFirstSearchedResultProduct() {
		searchResultPage = new SearchResultPage(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(searchResultPage.searchResult));
		productFullText = searchResultPage.getSearchedProductCompleteText().getText();
		System.out.println("Actual first full product text from Search result page: " + productFullText);
		searchResultPage.getFirstSearchResult().click();
	}

	@Test(priority = 2)
	private void addingAProductIntoCart() throws InterruptedException {
		BaseClass.switchToNextTab();
		particulatProductPage = new ParticularProductPage(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(particulatProductPage.addToCart));
		particulatProductPage.getAddToCartButton().click();
		Thread.sleep(3000);
		driver.navigate().refresh();
	}

	@Test(priority = 3)
	private void veriftThatPorductHasAddedToTheCart() {
		wait.until(ExpectedConditions.presenceOfElementLocated(particulatProductPage.goToCart));
		particulatProductPage.getGoToCartButton().click();
		cartPage = new CartPageObjects(driver);
		wait.until(ExpectedConditions.elementToBeClickable(cartPage.getPlaceOrderButton()));
		String actualText = cartPage.getCartProductText().getText();
		System.out.println("Actual full product text from Cart Page: " + actualText);;
		assertEquals(actualText, productFullText);
		assertTrue(cartPage.getPlaceOrderButton().isEnabled());
	}
	
	@AfterSuite
	private void oneTimeTearDown() {
		driver.quit();
	}
}
