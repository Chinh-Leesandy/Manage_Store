from selenium.webdriver.chrome.options import Options
from selenium import webdriver


def customChrome():
    option = Options()
    option.add_argument("--enable-extensions")

    driver = webdriver.Chrome()
    driver.implicitly_wait(3)
    driver.maximize_window()

    print("[Open browser] Open Chrome browser")
    return driver
