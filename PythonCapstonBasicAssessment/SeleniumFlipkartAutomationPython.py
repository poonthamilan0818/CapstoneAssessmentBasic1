import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture
def browser():
    # Initialize the WebDriver
    driver = webdriver.Chrome()
    driver.implicitly_wait(10)
    yield driver
    driver.quit()

def test_addAProductToCart(browser):
    browser.get("https://www.flipkart.com/")
    assert "Flipkart" in browser.find_element(By.CSS_SELECTOR, "div._1jA3uo").text
    searchBox = browser.find_element(By.CSS_SELECTOR, "input._3704LK")
    searchBox.send_keys("Macbook air m2")
    searchBox.send_keys(Keys.RETURN)
    firstSearchReasultItem = WebDriverWait(browser, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "div._1AtVbE > a"))
    )
    firstSearchReasultItem.click()
    addToCartButton = WebDriverWait(browser, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "button._2KpZ6l._2U9uOA._3v1-ww"))
    )
    addToCartButton.click()
    cartText = WebDriverWait(browser, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "span._1_D7I5"))
    ).text
    assert "1 item" in cartText

if __name__ == "__main__":
    pytest.main()
