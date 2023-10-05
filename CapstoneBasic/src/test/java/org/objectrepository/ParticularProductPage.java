package org.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParticularProductPage {
	public WebDriver driver;

	public ParticularProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[text() = 'Add to cart']")
	WebElement addToCartButton;
	@FindBy(xpath = "//button[text() = 'GO TO CART']")
	WebElement goToCartButton;
	public final By goToCart = By.xpath("//button[text() = 'GO TO CART']");
	public final By addToCart = By.xpath("//button[text() = 'Add to cart']");

	public WebElement getAddToCartButton() {
		return addToCartButton;
	}

	public void setAddToCartButton(WebElement addToCartButton) {
		this.addToCartButton = addToCartButton;
	}

	public WebElement getGoToCartButton() {
		return goToCartButton;
	}

	public void setGoToCartButton(WebElement goToCartButton) {
		this.goToCartButton = goToCartButton;
	}

	public By getGoToCart() {
		return goToCart;
	}
}
