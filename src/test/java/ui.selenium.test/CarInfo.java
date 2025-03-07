/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import org.openqa.selenium.WebElement;
import ui.selenium.WebTable;

/**
 * Represents a car table containing vehicle details such as registration number, make, model, year, and more.
 * Extends {@link WebTable} to provide structured access to car-related data.
 */
public class CarInfo extends WebTable
{

    public CarInfo(WebElement parent)
    {
        super(parent);
    }

    public WebElement getMake()
    {
        return getCell(0, 1);
    }

    public WebElement getModel()
    {
        return getCell(1, 1);
    }

    public WebElement getColour()
    {
        return getCell(2, 1);
    }

    public WebElement getYear()
    {
        return getCell(3, 1);
    }

    public WebElement getTopSpeed()
    {
        return getCell(4, 1);
    }

    public WebElement getGearbox()
    {
        return getCell(6, 1);
    }
}
