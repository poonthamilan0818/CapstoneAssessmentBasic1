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

def test_add_macbook_to_cart(browser):
    # Step 1: Launch the URL
    browser.get("https://www.flipkart.com/")
    browser.maximize_window()
    browser.delete_all_cookies()
    browser.set_page_load_timeout(10)
    browser.find_element(By.XPATH, "//div[@class = 'fbDBuK _3CYmv5']//span[@role = 'button']").click()

    # Verify that "Flipkart" is present on the left side of the top
    assert browser.find_element(By.XPATH, "//a[@title='Flipkart']/img[@title = 'Flipkart']").is_displayed()

    # Step 2: Enter "Macbook air m2" in the search text box and search
    search_box = browser.find_element(By.XPATH, "//input[@type = 'text']")
    search_box.send_keys("Macbook")
    search_box.send_keys(Keys.RETURN)

    # Step 3: Click on the first displayed item
    first_item = WebDriverWait(browser, 10).until(
        EC.presence_of_element_located((By.XPATH, "(//a[@rel='noopener noreferrer']//parent::div)[1]"))
    )
    full_Product_Item_Text_From_Search_Result_Page = browser.find_element(By.XPATH, "(//a[@rel='noopener noreferrer']//parent::div)[1]//div/span[contains(@id, 'productRating_')]/parent::div/preceding-sibling::div").text
    first_item.click()
    print(full_Product_Item_Text_From_Search_Result_Page)

    # Step 4: Switch to next window
    current_Win_Id = browser.current_window_handle
    allWinId = browser.window_handles
    for winId in allWinId:
        if winId != current_Win_Id:
            browser.switch_to.window(winId)

    # Step 5: Click on "Add To Cart" button
    add_to_cart_button = WebDriverWait(browser, 10).until(
        EC.presence_of_element_located((By.XPATH, "//button[text() = 'Add to cart']"))
    )
    browser.find_element(By.ID, "pincodeInputId").send_keys("600096")
    browser.find_element(By.XPATH, "//span[text() = 'Check']").click()
    add_to_cart_button.click()

    # Step 5: Verify that the item has been added to the cart successfully
    WebDriverWait(browser, 10).until(EC.element_to_be_clickable((By.XPATH, "//button/span[text() = 'Place Order']")))
    cart_Page_Product_Text = browser.find_element(By.XPATH, "//a[@class = '_2Kn22P gBNbID']").text
    print(cart_Page_Product_Text)
    assert cart_Page_Product_Text == full_Product_Item_Text_From_Search_Result_Page

if __name__ == "__main__":
    pytest.main()
