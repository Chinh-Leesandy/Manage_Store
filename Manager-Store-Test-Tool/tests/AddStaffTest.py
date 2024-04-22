import unittest

from parameterized import parameterized
from steps.Step_add_staff import stepAddStaff
from steps.ReadDataTest import readDataTest
from utils.CustomChromeDriver import customChrome
from verifys.Verify_add_staff import verifyAddStaff

dataTests = readDataTest().data_test("../dataTest/data_test_add_staff.xlsx")


class MyTestCase(unittest.TestCase):
    def setUp(self):
        print("==== [Begin Test] ====")
        self.browser = customChrome()
        self.browser.get("http://localhost:3000/employee/-1")

    def tearDown(self):
        self.browser.quit()
        print("==== [End Test] ====")

    @parameterized.expand(dataTests)
    def test_add_staff(self, no, name, phone, email, address, position, username, password, work_start_time, message):
        stepAddStaff(self.browser).add_staff(name, phone, email, address, position, username, password, work_start_time)
        print(message, verifyAddStaff(self.browser).add_staff(), ']', sep=" ")
        self.assertIn(message, verifyAddStaff(self.browser).add_staff())


if __name__ == '__main__':
    unittest.main()
