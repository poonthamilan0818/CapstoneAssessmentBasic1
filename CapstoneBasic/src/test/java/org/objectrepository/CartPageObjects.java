package org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageObjects {
	public WebDriver driver;

	public CartPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class = '_2Kn22P gBNbID']")
	WebElement cartProductText;
	@FindBy(xpath = "//button/span[text() = 'Place Order']")
	WebElement placeOrderButton;

	public WebElement getCartProductText() {
		return cartProductText;
	}
	public void setCartProductText(WebElement cartProductText) {
		this.cartProductText = cartProductText;
	}
	public WebElement getPlaceOrderButton() {
		return placeOrderButton;
	}
	public void setPlaceOrderButton(WebElement placeOrderButton) {
		this.placeOrderButton = placeOrderButton;
	}
}
