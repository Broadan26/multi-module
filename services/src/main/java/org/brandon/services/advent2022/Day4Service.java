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

@ApplicationScoped
public class Day4Service {

    private static final String PAIR_1_START = "pair1Start";
    private static final String PAIR_2_START = "pair2Start";
    private static final String PAIR_1_END = "pair1End";
    private static final String PAIR_2_END = "pair2End";

    private static final Logger LOG = LoggerFactory.getLogger(Day4Service.class);

    /**
     * Solves the day 4 part 1 problem for Advent of Code 2022.
     * Determines if a pair of integers is completely overlapped by one member of the pair.
     * @param day4File The file that was provided by the calling method
     * @return A long indicating the total number of pairs that completely overlap
     */
    public long part1Solve(File day4File) throws IOException {
        List<Map<String, Integer>> parsedFile = parseDay4File(day4File);
        long overlappingPairs = 0;
        for (var pairMap : parsedFile) {

            boolean pair1Overlap = pairMap.get(PAIR_1_START) >= pairMap.get(PAIR_2_START) &&
                    pairMap.get(PAIR_1_END) <= pairMap.get(PAIR_2_END);
            boolean pair2Overlap = pairMap.get(PAIR_2_START) >= pairMap.get(PAIR_1_START) &&
                    pairMap.get(PAIR_2_END) <= pairMap.get(PAIR_1_END);

            if (pair1Overlap || pair2Overlap) {
                overlappingPairs += 1;
            }
        }
        return overlappingPairs;
    }

    /**
     * Solves the day 4 part 2 problem for Advent of Code 2022.
     * Determines if a pair of integers is at least partially overlapped by another member of the pair.
     * @param day4File The file that was provided by the calling method
     * @return A long indicating the total number of pairs that at least partially overlap
     */
    public long part2Solve(File day4File) throws IOException {
        List<Map<String, Integer>> parsedFile = parseDay4File(day4File);
        long overlappingPairs = 0;
        for (var pairMap : parsedFile) {

            // Start overlaps
            boolean pair1StartOverlap = pairMap.get(PAIR_1_START) <= pairMap.get(PAIR_2_START) &&
                    pairMap.get(PAIR_1_END) >= pairMap.get(PAIR_2_START);
            boolean pair2StartOverlap = pairMap.get(PAIR_2_START) <= pairMap.get(PAIR_1_START) &&
                    pairMap.get(PAIR_2_END) >= pairMap.get(PAIR_1_START);

            // End overlaps
            boolean pair1EndOverlap = pairMap.get(PAIR_1_END) >= pairMap.get(PAIR_2_END) &&
                    pairMap.get(PAIR_1_START) <= pairMap.get(PAIR_2_END);
            boolean pair2EndOverlap = pairMap.get(PAIR_2_END) >= pairMap.get(PAIR_1_END) &&
                    pairMap.get(PAIR_2_START) <= pairMap.get(PAIR_1_END);

            if (pair1StartOverlap || pair2StartOverlap || pair1EndOverlap || pair2EndOverlap) {
                overlappingPairs += 1;
            }
        }
        return overlappingPairs;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 4 challenge.
     * File contains lines of numeric pairs indicating a range and separated by a comma.
     * @param day4File The file that was provided by the calling method
     * @return A list of maps which contain the start of each range pair and the end of each range pair
     */
    public List<Map<String, Integer>> parseDay4File(File day4File) throws IOException {
        List<Map<String, Integer>> parsedFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(day4File.getPath()))) {
            String currentLine = reader.readLine();
            Map<String, Integer> pairMap;
            while (currentLine != null) {
                pairMap = new HashMap<>();

                // Collect the numeric values
                String[] rangePairs = currentLine.split(",", 2);
                int[] firstPair = splitRange(rangePairs[0]);
                int[] secondPair = splitRange(rangePairs[1]);

                // Place pairs into map and store in list
                pairMap.put(PAIR_1_START, firstPair[0]);
                pairMap.put(PAIR_1_END, firstPair[1]);
                pairMap.put(PAIR_2_START, secondPair[0]);
                pairMap.put(PAIR_2_END, secondPair[1]);
                parsedFile.add(pairMap);

                // Move to the next line to continue the loop
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

    /**
     * Helper method which splits a string of integers and returns an array containing the actual values.
     * @param range A string in the format of `<int>-<int>`
     * @return An integer array containing each of the int entries
     */
    private int[] splitRange(String range) {
        String[] rangeValues = range.split("-", 2);
        return new int[]{Integer.parseInt(rangeValues[0]), Integer.parseInt(rangeValues[1])};
    }

}
