package com.roisoftstudio.selenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumHelper {
    private WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String admin, String password) {
        driver.get("http://localhost:9090/");
        driver.findElement(By.id("username")).sendKeys(admin);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
    }
}
