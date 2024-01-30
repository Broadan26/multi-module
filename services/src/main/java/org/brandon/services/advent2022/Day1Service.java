package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Service methods for Day 1 of Advent of Code 2022
 */
@ApplicationScoped
public class Day1Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day1Service.class);

    /**
     * Solves the day 1 part 1 problem for Advent of Code 2022.
     * @param day1File The file that was provided by the calling method
     * @return A long indicating the highest sum of a single map in the parsedFile list
     */
    public long part1Solve(File day1File) throws IOException {
        List<Map<Integer, Integer>> parsedFile = parseDay1File(day1File);
        long largestSum = Long.MIN_VALUE;
        for (var map: parsedFile) {
            long currentSum = 0;
            for (var entry: map.entrySet()) {
                currentSum += (long) entry.getKey() * entry.getValue();
            }
            largestSum = Math.max(currentSum, largestSum);
        }
        return largestSum;
    }

    /**
     * Solves the day 1 part 2 problem for Advent of Code 2022.
     * @param day1File The file that was provided by the calling method
     * @return A long indicating the highest 3 sums of the maps in the parsedFile list
     */
    public long part2Solve(File day1File) throws IOException {
        List<Map<Integer, Integer>> parsedFile = parseDay1File(day1File);
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (var map: parsedFile) {
            long currentSum = 0;
            for (var entry: map.entrySet()) {
                currentSum += (long) entry.getKey() * entry.getValue();
            }
            maxHeap.add(currentSum);
        }

        long top3Sums = 0;
        for (int i = 0; i < 3; i++) {
            top3Sums += maxHeap.peek() == null ? 0 : maxHeap.poll();
        }
        return top3Sums;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 1 challenge.
     * @param day1File The file that was provided by the calling method
     * @return A list of maps that indicating the number of items and calories of each elf
     */
    public List<Map<Integer, Integer>> parseDay1File(File day1File) throws IOException {
        List<Map<Integer, Integer>> parsedFile = new ArrayList<>();
        Map<Integer, Integer> currentEntry = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day1File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {

                // Current line is just a newline
                if (currentLine.trim().isEmpty()) {
                    parsedFile.add(currentEntry);
                    currentEntry = new HashMap<>();
                } else {
                    // Current line has a value
                    int valueToAdd = Integer.parseInt(currentLine);
                    currentEntry.put(valueToAdd, currentEntry.getOrDefault(valueToAdd, 0) + 1);
                }

                // Move to the next line to continue the loop
                currentLine = reader.readLine();
            }
            if (!currentEntry.isEmpty()) {
                parsedFile.add(currentEntry);
            }

        } catch (Exception ex) {
            LOG.error("File Parsing Failed with error: {}", ex.getMessage());
            throw new IOException("Invalid file provided");
        }

        return parsedFile;
    }

}
