/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.selenium.WebPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Represents the car report page, displaying detailed vehicle information.
 * Extends {@link WebPage} to provide structured interactions and navigation.
 */
public class CarReport extends WebPage<CarReport>
{
    @FindBy(id = "subForm")
    private WebElement registration;

    @FindBy(css = ".first-title")
    private WebElement title;

    @FindBy(css = "table.table-responsive:first-of-type")
    private WebElement table;

    public CarReport(WebDriver driver)
    {
        super(driver);
    }

    public void verifyData(Car car)
    {
        System.out.println("Verifying data: " + car);

        assertThat(car).as("Car data").isNotNull();

        assertThat(registration.getAttribute("value")).
                as("Registration number").
                isEqualTo(car.getRegistrationNumber().replace(" ", ""));

        assertThat(title.getText()).
                as("Report title").
                isEqualTo(String.format("%s %s", car.getMake(), car.getModel()));

        CarInfo info = new CarInfo(table);

        assertThat(info.getMake().getText()).
                as("Make").
                isEqualTo(car.getMake());

        assertThat(info.getModel().getText()).
                as("Model").
                isEqualTo(car.getModel());

        assertThat(info.getYear().getText()).
                as("Year").
                isEqualTo(car.getYear());
    }

}
