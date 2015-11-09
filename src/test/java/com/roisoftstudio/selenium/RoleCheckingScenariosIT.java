package com.roisoftstudio.selenium;

import com.roisoftstudio.selenium.helpers.SeleniumHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.roisoftstudio.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoleCheckingScenariosIT {

    private WebDriver driver;
    private SeleniumHelper seleniumHelper;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        seleniumHelper = new SeleniumHelper(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void rolesAreAppliedForUserWithRole1() throws Exception {
        seleniumHelper.login("user1", "pass1");
        assertHasValidRole(ROLE_PROTECTED_MSG, 2, "page1");
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 3, UNAUTHORIZED_ROLE_PAGE);
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 4, UNAUTHORIZED_ROLE_PAGE);
    }

    @Test
    public void rolesAreAppliedForUserWithRole2() throws Exception {
        seleniumHelper.login("user2", "pass2");
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 2, UNAUTHORIZED_ROLE_PAGE);
        assertHasValidRole(ROLE_PROTECTED_MSG, 3, "page2");
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 4, UNAUTHORIZED_ROLE_PAGE);
    }

    @Test
    public void rolesAreAppliedForUserWithRole3() throws Exception {
        seleniumHelper.login("user3", "pass3");
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 2, UNAUTHORIZED_ROLE_PAGE);
        assertHasInvalidRole(UNAUTHORIZED_ERROR_MESSAGE, 3, UNAUTHORIZED_ROLE_PAGE);
        assertHasValidRole(ROLE_PROTECTED_MSG, 4, "page3");
    }
    @Test
    public void rolesAreAppliedForUserWithAllRoles() throws Exception {
        seleniumHelper.login("admin", "password");
        assertHasValidRole(ROLE_PROTECTED_MSG, 2, "page1");
        assertHasValidRole(ROLE_PROTECTED_MSG, 3, "page2");
        assertHasValidRole(ROLE_PROTECTED_MSG, 4, "page3");
    }
    private void assertHasValidRole(String message, long listItem, String page) {
        driver.findElement(By.xpath("//ul//li["+ listItem +"]")).click();
        assertThat(driver.getCurrentUrl().contains(page), is(true));
        assertThat(driver.findElement(By.id("successBox")).getText().contains(message), is(true));
    }

    private void assertHasInvalidRole(String message, long listItem, String page) {
        driver.findElement(By.xpath("//ul//li["+ listItem +"]")).click();
        assertThat(driver.getCurrentUrl().contains(page), is(true));
        assertThat(driver.findElement(By.id("errorBox")).getText().contains(message), is(true));
    }


}
