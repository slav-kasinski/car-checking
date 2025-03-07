/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

/**
 * Represents a car with a registration number, make, model, and manufacturing year.
 * This class serves as a data model for vehicle-related operations.
 */
public class Car

{
    private String registrationNumber;
    private String make;
    private String model;
    private String year;

    public Car(String registrationNumber, String make, String model, String year)
    {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public String getYear()
    {
        return year;
    }

    @Override
    public String toString()
    {
        return "Car {" +
                "registration='" + registrationNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}

