package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.brandon.data.advent2022.Day10Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Day10Service {

    private static final String NOOP = "noop";
    private static final String ADD_X = "addx";
    private static final Logger LOG = LoggerFactory.getLogger(Day10Service.class);

    /**
     * Simulates the situation for the Advent of Code 2022: Day 10 Part 1 scenario.
     * @param day10File The file that was provided by the calling method
     * @return A long representing the sum of all the interesting clock cycles in the parsed file
     */
    public long part1Solve(File day10File) throws IOException {
        List<Day10Model> parsedFile = parseDay10File(day10File);
        int cycle = 1;
        long currentValue = 1;
        long sum = 0;
        while (!parsedFile.isEmpty()) {
            Day10Model operation = parsedFile.removeFirst();

            switch (operation.getAction()) {
                case NOOP -> {
                    sum = cycleCheckPartOne(cycle, sum, currentValue);
                    cycle += 1;
                }
                case ADD_X -> {
                    sum = cycleCheckPartOne(cycle, sum, currentValue);
                    cycle += 1;
                    sum = cycleCheckPartOne(cycle, sum, currentValue);
                    cycle += 1;
                    currentValue += operation.getNumber();
                }
                default -> {
                    LOG.error("Invalid file provided - Action: {}", operation.getAction());
                    throw new IOException("Invalid action in file");
                }
            }
        }
        return sum;
    }

    /**
     * Simulates the situation for the Advent of Code 2022: Day 10 Part 2 scenario.
     * @param day10File The file that was provided by the calling method
     * @return A string with multiple lines that contains uppercase characters
     */
    public String part2Solve(File day10File) throws IOException {
        List<Day10Model> parsedFile = parseDay10File(day10File);
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        int cycle = 1;
        int location = 1;
        String symbol;
        while (!parsedFile.isEmpty()) {
            Day10Model operation = parsedFile.removeFirst();
            switch (operation.getAction()) {
                case NOOP -> {
                    symbol = checkLocation(cycle, location);
                    currentLine = cycleCheckPartTwo(result, currentLine, symbol);
                    cycle += 1;
                }
                case ADD_X -> {
                    symbol = checkLocation(cycle, location);
                    currentLine = cycleCheckPartTwo(result, currentLine, symbol);
                    cycle += 1;

                    symbol = checkLocation(cycle, location);
                    currentLine = cycleCheckPartTwo(result, currentLine, symbol);
                    cycle += 1;

                    location += operation.getNumber();
                }
                default -> {
                    LOG.error("Invalid file provided - Action: {}", operation.getAction());
                    throw new IOException("Invalid action in file");
                }
            }
        }
        result.append(currentLine);
        return result.toString();
    }

    /**
     * Helper method which performs the special summation for the Day 10 part 1 simulation
     * @param cycle The current cycle
     * @param sum The current summation
     * @param currentVal The value to be multiplied with the cycle
     * @return A long representing the updated sum
     */
    protected long cycleCheckPartOne(int cycle, long sum, long currentVal) {
        if (cycle == 20 || (cycle + 20) % 40 == 0) {
            sum += cycle * currentVal;
        }
        return sum;
    }

    /**
     * Adds new symbols to the current line and determines when the current line has reached its end.
     * @param result The final resultant String from combining every symbol
     * @param currentLine The current line of the resultant String being worked on
     * @param symbol The symbol to be added to the current line
     * @return An updated string builder object
     */
    protected StringBuilder cycleCheckPartTwo(StringBuilder result, StringBuilder currentLine, String symbol) {
        if (currentLine.length() == 40) {
            result.append(currentLine).append("\n");
            currentLine = new StringBuilder();
        }
        currentLine.append(symbol);
        return currentLine;
    }

    /**
     * Helper method which checks the location of the pointer in comparison with the current cycle.
     * @param cycle The current cycle that is running
     * @param location The location of the pointer
     * @return The symbol that would correspond with the relationship between the cycle and the pointer
     */
    protected String checkLocation(int cycle, int location) {
        while (cycle > 40) {
            cycle -= 40;
        }
        if (location + 2 >= cycle && cycle >= location) {
            return "#";
        }
        return ".";
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 10 challenge.
     * @param day10File The file that was provided by the calling method
     * @return A list of Day 10 Models with each item containing an operation and optionally a value
     */
    public List<Day10Model> parseDay10File(File day10File) throws IOException {
        List<Day10Model> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day10File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String[] splitLine = currentLine.split(" ", 2);
                if (splitLine[0].equals("noop")) {
                    parsedFile.add(new Day10Model(splitLine[0]));
                } else {
                    parsedFile.add((new Day10Model(splitLine[0], Integer.parseInt(splitLine[1]))));
                }
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

}
