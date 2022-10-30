from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time
from selenium.webdriver.common.by import By
import pandas as pd

options = Options()

# options.headless = True

df = pd.read_excel("output/colon_combinations.xlsx", sheet_name='Sheet1')

driver = webdriver.Chrome("/usr/bin/chromedriver", options=options)

driver.get("https://www.nesine.com/sportoto")

#arr = [ ["1", "1-0", "2", "2", "1-0", "2-1", "2", "2-0", "2", "2-1", "2", "1", "2", "0", "1"], ["2", "1-2", "2", "2-0", "1-0", "2-1", "1", "2-0", "2", "2-1", "2", "1", "2", "0", "1"] ]

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

time.sleep(0.5)

for i in range(364, len(df.index)):
    print(i)
    arr = df.loc[i, 0:14].values.tolist()
    for index2, y in enumerate(arr):
        split = str(y).split("-")
        for n in split:
            if n == "1":
                value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(i % 4 + 7) + "]/label[1]"
                element = driver.find_element(By.XPATH, value)
                element.location_once_scrolled_into_view
                element.click()
            if n == "0":
                value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(i% 4 + 7) + "]/label[2]"
                element = driver.find_element(By.XPATH, value)
                element.location_once_scrolled_into_view
                element.click()
            if n == "2":
                value = "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[" + str(index2 + 1) + "]/td[" + str(i % 4 + 7) + "]/label[3]"
                element = driver.find_element(By.XPATH, value)
                element.location_once_scrolled_into_view
                element.click()
    if i % 4 == 3:
        element = driver.find_element(By.XPATH, "//*[@id=\"btnCalculate\"]")
        element.location_once_scrolled_into_view
        element.click()

        time.sleep(1)

        element = driver.find_element(By.XPATH, "//*[@id=\"btnPlay\"]")
        element.location_once_scrolled_into_view
        element.click()

        time.sleep(2.5)

        element = driver.find_element(By.XPATH, "//*[@id=\"process-completed\"]/div[1]/a[2]")
        element.location_once_scrolled_into_view
        element.click()

        #element = driver.find_element(By.XPATH, "//*[@id=\"spor-toto-coupon\"]/div[3]/div/a")
        #element.location_once_scrolled_into_view
        #element.click()

        #time.sleep(0.5)

        #element = driver.find_element(By.XPATH, "//*[@id=\"spor-toto-coupon\"]/div[3]/div/a")
        #element.location_once_scrolled_into_view
        #element.click()
        #exit(1)

if len(df.index) % 4 != 3:
    element = driver.find_element(By.XPATH, "//*[@id=\"btnCalculate\"]")
    element.location_once_scrolled_into_view
    element.click()

    time.sleep(0.5)

    element = driver.find_element(By.XPATH, "//*[@id=\"btnPlay\"]")
    element.location_once_scrolled_into_view
    element.click()

    time.sleep(2)

    element = driver.find_element(By.XPATH, "//*[@id=\"process-completed\"]/div[1]/a[2]")
    element.location_once_scrolled_into_view
    element.click()
        
#element = driver.find_element(By.XPATH, "//*[@id=\"page-content\"]/div[1]/table[1]/tbody/tr[1]/td[7]/label[1]")
#element.click()

#driver.quit()