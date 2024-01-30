package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Rules:
 * Your total score is the sum of your scores for each round.
 * The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
 * plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
 * A = Rock, B = Paper, C = Scissors
 * X = ?, Y = ?, Z = ?
 */
@ApplicationScoped
public class Day2Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day2Service.class);

    /**
     * Solves the day 2 part 1 problem for Advent of Code 2022.
     * @param day2File The file that was provided by the calling method
     * @return A long value indicating the best possible outcome
     */
    public long part1Solve(File day2File) throws IOException {
        Map<String, Integer> parsedFile = parseDay2File(day2File);
        Map<String, Integer> pointMap = createPointMapPart1();
        return day2Solver(parsedFile, pointMap);
    }

    /**
     * Solves the day 2 part 2 problem for Advent of Code 2022.
     * X = lose, Y = draw, Z = win
     * @param day2File The file that was provided by the calling method
     * @return A long value indicating the best possible outcome
     */
    public long part2Solve(File day2File) throws IOException {
        Map<String, Integer> parsedFile = parseDay2File(day2File);
        Map<String, Integer> pointMap = createPointMapPart2();
        return day2Solver(parsedFile, pointMap);
    }

    /**
     * asdf
     * @param parsedFile A map of the counts of each possible combination of file inputs
     * @param pointMap Hashmap of possible combinations for inputs and values for each outcome
     * @return A long value indicating the best possible outcome
     */
    private long day2Solver(Map<String, Integer> parsedFile, Map<String, Integer> pointMap) {
        long totalPoints = 0L;
        for (var entry : parsedFile.entrySet()) {
            switch (entry.getKey()) {
                case "A X" -> totalPoints += (long) entry.getValue() * pointMap.get("A X");
                case "A Y" -> totalPoints += (long) entry.getValue() * pointMap.get("A Y");
                case "A Z" -> totalPoints += (long) entry.getValue() * pointMap.get("A Z");
                case "B X" -> totalPoints += (long) entry.getValue() * pointMap.get("B X");
                case "B Y" -> totalPoints += (long) entry.getValue() * pointMap.get("B Y");
                case "B Z" -> totalPoints += (long) entry.getValue() * pointMap.get("B Z");
                case "C X" -> totalPoints += (long) entry.getValue() * pointMap.get("C X");
                case "C Y" -> totalPoints += (long) entry.getValue() * pointMap.get("C Y");
                case "C Z" -> totalPoints += (long) entry.getValue() * pointMap.get("C Z");
                default -> throw new UnsupportedOperationException("Invalid mapping");
            }
        }
        return totalPoints;
    }

    /**
     * Helper method which creates a custom map of all possible combinations of inputs and the point values for each outcome.
     * @return The created hashmap
     */
    private Map<String, Integer> createPointMapPart1() {
        Map<String, Integer> pointMap = new HashMap<>();
        pointMap.put("A X", 4);
        pointMap.put("A Y", 8);
        pointMap.put("A Z", 3);
        pointMap.put("B X", 1);
        pointMap.put("B Y", 5);
        pointMap.put("B Z", 9);
        pointMap.put("C X", 7);
        pointMap.put("C Y", 2);
        pointMap.put("C Z", 6);
        return pointMap;
    }

    /**
     * Helper method which creates a custom map of all possible combinations of inputs and the point values associated for each outcome.
     * @return The created hashmap
     */
    private Map<String, Integer> createPointMapPart2() {
        Map<String, Integer> pointMap = new HashMap<>();
        pointMap.put("A X", 3);
        pointMap.put("A Y", 4);
        pointMap.put("A Z", 8);
        pointMap.put("B X", 1);
        pointMap.put("B Y", 5);
        pointMap.put("B Z", 9);
        pointMap.put("C X", 2);
        pointMap.put("C Y", 6);
        pointMap.put("C Z", 7);
        return pointMap;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 2 challenge.
     * File of the form "<opponent-choice> <user-choice>"
     * @param day2File The file that was provided by the calling method
     * @return A map of the counts of each possible combination of file inputs
     */
    public Map<String, Integer> parseDay2File(File day2File) throws IOException {
        Map<String, Integer> parsedFile = createCountMap();
        try (BufferedReader reader = new BufferedReader(new FileReader(day2File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {

                // Current line is just a newline
                if (currentLine.trim().isEmpty()) {
                    break;
                } else {
                    // Current line has values
                    String cleanedLine = currentLine.trim();
                    if (parsedFile.containsKey(cleanedLine)) {
                        parsedFile.put(cleanedLine, parsedFile.get(cleanedLine) + 1);
                    } else {
                        throw new IOException("Invalid combination of characters");
                    }

                    // Move to the next line to continue the loop
                    currentLine = reader.readLine();
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

    /**
     * Helper method which creates a custom map of all possible combinations of inputs and an initial starting count of 0 for each.
     * @return The created hashmap
     */
    private Map<String, Integer> createCountMap() {
        Map<String, Integer> parsedFile = new HashMap<>();
        parsedFile.put("A X", 0);
        parsedFile.put("A Y", 0);
        parsedFile.put("A Z", 0);
        parsedFile.put("B X", 0);
        parsedFile.put("B Y", 0);
        parsedFile.put("B Z", 0);
        parsedFile.put("C X", 0);
        parsedFile.put("C Y", 0);
        parsedFile.put("C Z", 0);
        return parsedFile;
    }

}
