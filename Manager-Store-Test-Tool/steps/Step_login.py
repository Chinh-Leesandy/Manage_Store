from selenium.webdriver.common.by import By
from pages.Page_login import txt_username, txt_password, btn_login


class stepLogin:
    def __init__(self, driver):
        self.driver = driver

    def login(self, username, password):
        print("[Step] login")
        self.inputUserName(username)
        self.inputPassword(password)
        self.clickLogin()

    def inputUserName(self, username):
        print("[+] Input username", username, sep=": ")
        self.get_txtUsername().send_keys(username)

    def inputPassword(self, password):
        print("[+] Input password", password, sep=": ")
        self.get_txtPassword().send_keys(password)

    def clickLogin(self):
        print("[+] Click login")
        self.get_btnLogin().click()

    # get element
    def get_txtUsername(self):
        return self.driver.find_element(By.XPATH, txt_username())

    def get_txtPassword(self):
        return self.driver.find_element(By.XPATH, txt_password())

    def get_btnLogin(self):
        return self.driver.find_element(By.XPATH, btn_login())
