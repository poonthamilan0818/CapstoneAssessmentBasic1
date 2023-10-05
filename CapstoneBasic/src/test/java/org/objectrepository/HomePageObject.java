package org.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject {
	public WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'fbDBuK _3CYmv5']//span[@role = 'button']")
	WebElement popCloseButton;
	@FindBy(xpath = "//input[@type = 'text']")
	WebElement homePageSearchBox;
	@FindBy(xpath = "//a[@title='Flipkart']/img[@title = 'Flipkart']")
	WebElement fipkartLogo;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchIconButton;
	@FindBy(xpath = "//a/span[text() = 'Cart']")
	WebElement cartOption;
	public final By popCloseIcon = By.xpath("//div[@class = 'fbDBuK _3CYmv5']//span[@role = 'button']");

	public WebElement getPopCloseButton() {
		return popCloseButton;
	}
	public void setPopCloseButton(WebElement popCloseButton) {
		this.popCloseButton = popCloseButton;
	}
	public WebElement getHomePageSearchBox() {
		return homePageSearchBox;
	}
	public void setHomePageSearchBox(WebElement homePageSearchBox) {
		this.homePageSearchBox = homePageSearchBox;
	}
	public WebElement getFipkartLogo() {
		return fipkartLogo;
	}
	public void setFipkartLogo(WebElement fipkartLogo) {
		this.fipkartLogo = fipkartLogo;
	}
	public WebElement getSearchIconButton() {
		return searchIconButton;
	}
	public void setSearchIconButton(WebElement searchIconButton) {
		this.searchIconButton = searchIconButton;
	}
	public WebElement getCartOption() {
		return cartOption;
	}
	public void setCartOption(WebElement cartOption) {
		this.cartOption = cartOption;
	}
}
