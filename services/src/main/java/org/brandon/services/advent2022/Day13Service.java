package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
public class Day13Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day13Service.class);

    /**
     * Simulates the situation for the Advent of Code 2022: Day 13 Part 1 scenario.
     * @param day13File The file that was provided by the calling method
     * @return A long representing the answer
     */
    public long part1Solve(File day13File) throws IOException {
        parseDay13File(day13File);
        return 1L;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 13 challenge.
     * @param day13File The file that was provided by the calling method
     */
    public void parseDay13File(File day13File) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(day13File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("File could not be read");
        }
    }

}
