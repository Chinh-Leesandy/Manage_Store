from selenium.webdriver.common.by import By

from pages.Add_staff_page import txt_name, txt_work_start_time, txt_password, txt_username, txt_position, txt_address, \
    txt_email, txt_phone, btn_add


class stepAddStaff:
    def __init__(self, driver):
        self.driver = driver

    def add_staff(self, name, phone, email, address, position, username, password, work_start_time):
        print("[Step] add_staff")
        self.inputName(name)
        self.inputPhone(phone)
        self.inputEmail(email)
        self.inputAddress(address)
        self.inputPosition(position)
        self.inputUsername(username)
        self.inputPassword(password)
        self.inputWorkStartTime(work_start_time)
        self.clickAdd()

    def inputName(self, name):
        print("[+] Input name", name, sep=": ")
        self.get_txtName().send_keys(name)

    def inputPhone(self, phone):
        print("[+] Input phone", phone, sep=": ")
        self.get_txtPhone().send_keys(phone)

    def inputEmail(self, email):
        print("[+] Input email", email, sep=": ")
        self.get_txtEmail().send_keys(email)

    def inputAddress(self, address):
        print("[+] Input address", address, sep=": ")
        self.get_txtAddress().send_keys(address)

    def inputPosition(self, position):
        print("[+] Input position", position, sep=": ")
        self.get_txtPosition().send_keys(position)

    def inputUsername(self, username):
        print("[+] Input username", username, sep=": ")
        self.get_txtUsername().send_keys(username)

    def inputPassword(self, password):
        print("[+] Input password", password, sep=": ")
        self.get_txtPassword().send_keys(password)

    def inputWorkStartTime(self, work_start_time):
        print("[+] Input work start time", work_start_time, sep=": ")
        self.get_txtWorkStartTime().send_keys(work_start_time)

    def clickAdd(self):
        print("[+] Click add")
        self.get_btnAdd().click()

    # get element
    def get_txtName(self):
        return self.driver.find_element(By.XPATH, txt_name())

    def get_txtPhone(self):
        return self.driver.find_element(By.XPATH, txt_phone())

    def get_txtEmail(self):
        return self.driver.find_element(By.XPATH, txt_email())

    def get_txtAddress(self):
        return self.driver.find_element(By.XPATH, txt_address())

    def get_txtPosition(self):
        return self.driver.find_element(By.XPATH, txt_position())

    def get_txtUsername(self):
        return self.driver.find_element(By.XPATH, txt_username())

    def get_txtPassword(self):
        return self.driver.find_element(By.XPATH, txt_password())

    def get_txtWorkStartTime(self):
        return self.driver.find_element(By.XPATH, txt_work_start_time())

    def get_btnAdd(self):
        return self.driver.find_element(By.XPATH, btn_add())
