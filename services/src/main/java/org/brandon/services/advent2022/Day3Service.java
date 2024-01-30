package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@ApplicationScoped
public class Day3Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day3Service.class);

    /**
     * Solves the day 3 part 1 problem for Advent of Code 2022.
     * Finds the item type that appears in each pair of provided maps.
     * Lowercase item types a through z have priorities 1 through 26.
     * Uppercase item types A through Z have priorities 27 through 52.
     * @param day3File The file that was provided by the calling method
     * @return A long indicating the sum of points assigned to like items in each map
     */
    public long part1Solve(File day3File) throws IOException {
        List<Map<Character, Integer>> parsedFile = parseDay3Part1File(day3File);
        long charSum = 0;
        for (int i = 0; i < parsedFile.size(); i += 2) {
            for (var key : parsedFile.get(i).keySet()) {
                if (parsedFile.get(i + 1).containsKey(key)) {
                    charSum += calculateCharValue(key);
                    break;
                }
            }
        }
        return charSum;
    }

    /**
     * Solves the day 3 part 2 problem for Advent of Code 2022.
     * Finds the item type that appears in each group of 3 maps.
     * Lowercase item types a through z have priorities 1 through 26.
     * Uppercase item types A through Z have priorities 27 through 52.
     * @param day3File The file that was provided by the calling method
     * @return A long indicating the sum of points assigned to like items in each map
     */
    public long part2Solve(File day3File) throws IOException {
        List<Map<Character, Integer>> parsedFile = parseDay3Part2File(day3File);
        long charSum = 0;
        for (int i = 0; i < parsedFile.size(); i += 3) {
            for (var key : parsedFile.get(i).keySet()) {
                if (parsedFile.get(i + 1).containsKey(key) && parsedFile.get(i + 2).containsKey(key)) {
                    charSum += calculateCharValue(key);
                    break;
                }
            }
        }
        return charSum;
    }

    /**
     * Helper function that calculates the arbitrary value of a given character
     * @param ch The character being evaluated
     * @return An integer value representing the value of the character
     */
    private int calculateCharValue(Character ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        } else if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 27;
        } else {
            throw new UnsupportedOperationException("Invalid character provided");
        }
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 3 challenge.
     * File contains lines of lower and uppercase alphabetic characters.
     * @param day3File The file that was provided by the calling method
     * @return A list of maps that contain the character counts of half of each line of the original file
     */
    public List<Map<Character, Integer>> parseDay3Part1File(File day3File) throws IOException {
        List<Map<Character, Integer>> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day3File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {

                if (!Pattern.matches("[a-zA-Z]+", currentLine)) {
                    throw new IOException("File contains invalid characters");
                }

                    StringBuilder sb = new StringBuilder(currentLine);
                String firstHalf = sb.substring(0, (currentLine.length() / 2));
                String secondHalf = sb.substring((currentLine.length() / 2), currentLine.length());

                // Map the first half of the string
                Map<Character, Integer> characterMap = new HashMap<>();
                for (char currentChar : firstHalf.toCharArray()) {
                    characterMap.put(currentChar, characterMap.getOrDefault(currentChar, 0) + 1);
                }
                parsedFile.add(characterMap);

                // Map the second half of the string
                characterMap = new HashMap<>();
                for (char currentChar : secondHalf.toCharArray()) {
                    characterMap.put(currentChar, characterMap.getOrDefault(currentChar, 0) + 1);
                }
                parsedFile.add(characterMap);

                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error("File Parsing Failed with error: {}", ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 3 challenge.
     * File contains lines of lower and uppercase alphabetic characters.
     * @param day3File The file that was provided by the calling method
     * @return A list of maps that contain the character counts of each line of the original file
     */
    public List<Map<Character, Integer>> parseDay3Part2File(File day3File) throws IOException {
        List<Map<Character, Integer>> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day3File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {

                if (!Pattern.matches("[a-zA-Z]+", currentLine)) {
                    throw new IOException("File contains invalid characters");
                }

                Map<Character, Integer> characterMap = new HashMap<>();
                for (char currentChar : currentLine.toCharArray()) {
                    characterMap.put(currentChar, characterMap.getOrDefault(currentChar, 0) + 1);
                }
                parsedFile.add(characterMap);

                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error("File Parsing Failed with error: {}", ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

}
