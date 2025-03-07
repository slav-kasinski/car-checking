/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import org.junit.BeforeClass;
import ui.selenium.WebTest;

import java.util.Collection;
import java.util.Map;

/**
 * Provides a car service for validating vehicle registration numbers.
 * Utilizes the Parameterized runner to execute tests for multiple registrations.
 * Extends {@link WebTest} to support structured web-based testing.
 */
public class CarService extends WebTest<CarService>
{
    private static String inputFile = "unknown.txt";
    private static String dataFile = "unknown.txt";

    protected final String registrationNumber;
    protected static Map<String, Car> cars;
    private CarSite carSite;
    private CarReport carReport;

    public CarService(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    public static Collection<String> provider(Class<?> dataProvider)
    {
        WebTest.data(dataProvider, data -> {
            inputFile = data.input();
            dataFile = data.output();
        });

        return CarData.getRegistrationNumbers(inputFile);
    }

    @BeforeClass
    public static void setup()
    {
        cars = CarData.getVehicleData(dataFile);
    }

    public CarSite carSite()
    {
        if (carSite == null)
        {
            carSite = createPage(CarSite.class);
        }

        return carSite;
    }

    public CarReport carReport()
    {
        if (carReport == null)
        {
            carReport = createPage(CarReport.class);
        }

        return carReport;
    }
}
