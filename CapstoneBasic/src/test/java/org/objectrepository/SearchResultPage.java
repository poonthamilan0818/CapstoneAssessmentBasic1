package org.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	public WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@rel='noopener noreferrer']//parent::div)[1]")
	WebElement firstSearchResult;
	@FindBy(xpath = "(//a[@rel='noopener noreferrer']//parent::div)[1]//div/span[contains(@id, 'productRating_')]/parent::div/preceding-sibling::div")
	WebElement searchedProductCompleteText;
	public final By searchResult = By.xpath("(//a[@rel='noopener noreferrer']//parent::div)[1]");

	public WebElement getFirstSearchResult() {
		return firstSearchResult;
	}

	public void setFirstSearchResult(WebElement firstSearchResult) {
		this.firstSearchResult = firstSearchResult;
	}

	public WebElement getSearchedProductCompleteText() {
		return searchedProductCompleteText;
	}

	public void setSearchedProductCompleteText(WebElement searchedProductCompleteText) {
		this.searchedProductCompleteText = searchedProductCompleteText;
	}
}
