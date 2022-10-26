from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time
from selenium.webdriver.common.by import By

options = Options()

# options.headless = True

driver = webdriver.Chrome("/usr/bin/chromedriver", options=options)

driver.get("https://www.nesine.com/sportoto")

arr = [ ["1", "1-0", "2", "2", "1-0", "2-1", "2", "2-0", "2", "2-1", "2", "1", "2", "0", "1"], ["2", "1-2", "2", "2-0", "1-0", "2-1", "1", "2-0", "2", "2-1", "2", "1", "2", "0", "1"] ]

print(driver.title)

time.sleep(3)
element = driver.find_element(By.XPATH, "//*[@id=\"c-p-bn\"]")
element.click()

file1 = open('config.txt', 'r')
temp = file1.read().splitlines()
print(temp)

element = driver.find_element(By.XPATH, "//*[@id=\"txtUsername\"]")
element.send_keys(temp[0])

element = driver.find_element(By.XPATH, "//*[@id=\"realpass\"]")
element.send_keys(temp[1])

element = driver.find_element(By.XPATH, "//*[@id=\"header-login\"]/div[1]/form/a")
element.click()

time.sleep(3)

for index1, x in enumerate(arr):
  for index2, y in enumerate(x):
    split = y.split("-")
    for n in split:
        if n == "1":
            value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(index1 + 7) + "]/label[1]"
            element = driver.find_element(By.XPATH, value)
            element.click()
        if n == "0":
            value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(index1 + 7) + "]/label[2]"
            element = driver.find_element(By.XPATH, value)
            element.click()
        if n == "2":
            value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(index1 + 7) + "]/label[3]"
            element = driver.find_element(By.XPATH, value)
            element.click()
        
#element = driver.find_element(By.XPATH, "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[1]/td[7]/label[1]")
#element.click()

element = driver.find_element(By.XPATH, "//*[@id=\"btnCalculate\"]")
element.location_once_scrolled_into_view
element.click()

time.sleep(10)
#driver.quit()