import unittest

from steps.ReadDataTest import readDataTest
from utils.CustomChromeDriver import customChrome
from parameterized import parameterized
from steps.Step_login import stepLogin
from verifys.Verify_login import verifyLogin

dataTests = readDataTest().data_test("../dataTest/data_test.xlsx")


class MyTestCase(unittest.TestCase):
    def setUp(self):
        print("==== [Begin Test] ====")
        self.browser = customChrome()
        self.browser.get("https://the-internet.herokuapp.com/login")

    def tearDown(self):
        self.browser.quit()
        print("==== [End Test] ====")

    @parameterized.expand(dataTests)
    def test_login(self, no, username, password, desiredResult, message):
        stepLogin(self.browser).login(username, password)
        self.assertIn(message, verifyLogin(self.browser).login())


if __name__ == '__main__':
    unittest.main()
