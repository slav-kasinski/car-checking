/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.selenium.WebPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Represents a car registration check page.
 */
public class CarSite extends WebPage<CarSite>
{
    public CarSite(WebDriver driver)
    {
        super(driver);
    }

    public CarSite navigateTo()
    {
        driver.get("https://car-checking.com/");

        return self();
    }

    public CarSite enterCarRegistration(String registration)
    {
        System.out.println("Entering registration: " + registration);

        driver.findElement(By.id("subForm1")).sendKeys(registration);

        return self();
    }

    public CarSite clickCheckNow()
    {
        driver.findElement(By.cssSelector(".check-now-button")).click();

        return checkCarReport();
    }

    protected CarSite checkCarReport()
    {
        WebElement element = waitForVisibilityOf(By.cssSelector(".third-title, div.alert-danger"));

        assertThat(element.getText()).as("Car Report Status").contains("YOUR CAR REPORT IS READY!");

        return self();
    }

}
