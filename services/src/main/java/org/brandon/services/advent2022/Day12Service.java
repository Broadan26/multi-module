package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.brandon.data.advent2022.Day12Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
public class Day12Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day12Service.class);

    /**
     * Simulates the situation for the Advent of Code 2022: Day 12 Part 1 scenario.
     * @param day12File The file that was provided by the calling method
     * @return A long representing the shortest path through the grid from the origin to the end.
     */
    public long part1Solve(File day12File) throws IOException {
        Day12Model day12Model = parseDay12File(day12File);
        return day12Model.solve();
    }

    /**
     * Simulates the situation for the Advent of Code 2022: Day 12 Part 2 scenario.
     * @param day12File The file that was provided by the calling method
     * @return A long representing the shortest path through the grid from the origin to the end.
     */
    public long part2Solve(File day12File) throws IOException {
        Day12Model day12Model = parseDay12File(day12File);
        return day12Model.solve();
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 12 challenge.
     * @param day12File The file that was provided by the calling method
     * @return A 2D array of characters that represent a vertical relief map
     */
    public Day12Model parseDay12File(File day12File) throws IOException {
        // Determine dimensions of the 2D array
        int length = 0;
        int height = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(day12File.getPath()))) {
            String currentLine = reader.readLine();
            length = currentLine.length();
            while (currentLine != null) {
                height += 1;
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("File could not be read");
        }

        // Parse the file for the 2D array
        char[][] grid = new char[height][length];
        try (BufferedReader reader = new BufferedReader(new FileReader(day12File.getPath()))) {
            String currentLine = reader.readLine();
            for (int i = 0; i < height; i++) {
                grid[i] = currentLine.toCharArray();
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return new Day12Model(grid);
    }

}
