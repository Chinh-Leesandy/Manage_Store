import time

from selenium.webdriver.support import expected_conditions as EC

from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

from pages.Add_staff_page import msg_result


class verifyAddStaff:
    def __init__(self, driver):
        self.driver = driver

    def waitAddFinish(self):
        return WebDriverWait(self.driver, 3).until()
        # .until(EC.presence_of_element_located((By.CLASS_NAME, msg_result()))))

    def add_staff(self):
        # self.waitAddFinish()
        time.sleep(1)
        content = self.driver.find_element(By.CLASS_NAME, msg_result()).text
        return content
