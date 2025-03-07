/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ui.selenium.Data;

import java.util.Collection;

/**
 * This test class validates car reports using the car-checking.com website service.
 * It leverages the Parameterized runner to execute tests for multiple car registration numbers.
 * Each test verifies the accuracy of the car report for a given registration number.
 */
@RunWith(Parameterized.class)
@Data(input = "car_input.txt", output = "car_output.txt")
public class CarTest extends CarService
{
    @Parameterized.Parameters
    public static Collection<String> data()
    {
        return CarService.provider(CarTest.class);
    }

    public CarTest(String registrationNumber)
    {
        super(registrationNumber);
    }

    @Test
    public void checkCarReport()
    {

        carSite()
                .navigateTo()
                .enterCarRegistration(registrationNumber)
                .clickCheckNow();

        carReport()
                .verifyData(cars.get(registrationNumber));
    }

}
