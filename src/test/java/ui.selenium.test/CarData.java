/*
 * Copyright (c) 2025, Kasinski Labs. All rights reserved.
 *
 * @author slav.kasinski
 */

package ui.selenium.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles reading and processing car registration numbers and vehicle data from files.
 * Provides utility methods for extracting and managing vehicle information.
 */
public class CarData
{
    private static final String REGEX_PATTERN = "\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b";

    public static List<String> getRegistrationNumbers(String filePath) throws RuntimeException
    {
        URL resourceUrl = CarData.class.getClassLoader().getResource(filePath);

        if (resourceUrl == null)
        {
            throw new RuntimeException("Test input file not found: " + filePath);
        }

        try (Stream<String> lines = Files.lines(Paths.get(resourceUrl.toURI())))
        {
            String content = lines.collect(Collectors.joining(" "));
            Pattern pattern = Pattern.compile(REGEX_PATTERN);
            Matcher matcher = pattern.matcher(content);
            return matcher.results().map(MatchResult::group).collect(Collectors.toList());
        }
        catch (IOException | URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Car> getVehicleData(String filePath) throws RuntimeException
    {
        URL resourceUrl = CarData.class.getClassLoader().getResource(filePath);
        if (resourceUrl == null)
        {
            throw new RuntimeException("Expected test output file not found: " + filePath);
        }

        try (Stream<String> lines = Files.lines(Paths.get(resourceUrl.toURI())))
        {
            Map<String, Car> data = new HashMap<>();
            lines.skip(1) // Skip the CSV header
                    .forEach(line -> {
                        String[] parts = line.split(",", 4);
                        if (parts.length == 4)
                        {
                            String number = parts[0].trim();
                            String make = parts[1].trim();
                            String model = parts[2].trim();
                            String year = parts[3].trim();

                            data.put(number, new Car(number, make, model, year));
                        }
                    });
            return data;
        }
        catch (IOException | URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }
}
