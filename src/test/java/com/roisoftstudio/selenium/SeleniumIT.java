package com.roisoftstudio.selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumIT {

    @Test
    public void testName() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:9090/");
        String str = driver.getCurrentUrl();
        System.out.println("The current URL is " + str);
        driver.close();    }
}