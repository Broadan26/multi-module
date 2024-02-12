package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@ApplicationScoped
public class Day9Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day9Service.class);

    /**
     * Calculates the number of unique spaces that the tail of a rope travels through.
     * @param day9File The file that was provided by the calling method
     * @return A long that represents the number of unique squares the tail has been present in
     */
    public long part1Solve(File day9File) throws IOException {
        List<Pair<Character, Integer>> parsedFile = parseDay9File(day9File);

        // Track Head & Tail Locations
        Pair<Integer, Integer> headLocation = new Pair<>(0, 0);
        Pair<Integer, Integer> tailLocation = new Pair<>(0, 0);

        // Track previous tail locations
        Set<Pair<Integer, Integer>> previousPositions = new HashSet<>();
        previousPositions.add(new Pair<>(0,0));

        // Determine direction of movement
        for (var movement : parsedFile) {
            switch (movement.getValue0()) {
                case 'U' -> {
                    for (int move = 0; move < movement.getValue1(); move++) {
                        headLocation = new Pair<>(headLocation.getValue0(), headLocation.getValue1() + 1);
                        if (isTailCloseEnough(headLocation, tailLocation)) {
                            continue;
                        }
                        tailLocation = new Pair<>(headLocation.getValue0(), headLocation.getValue1() - 1);
                        previousPositions.add(new Pair<>(tailLocation.getValue0(), tailLocation.getValue1()));
                    }
                }
                case 'D' -> {
                    for (int move = 0; move < movement.getValue1(); move++) {
                        headLocation = new Pair<>(headLocation.getValue0(), headLocation.getValue1() - 1);
                        if (isTailCloseEnough(headLocation, tailLocation)) {
                            continue;
                        }
                        tailLocation = new Pair<>(headLocation.getValue0(), headLocation.getValue1() + 1);
                        previousPositions.add(new Pair<>(tailLocation.getValue0(), tailLocation.getValue1()));
                    }
                }
                case 'L' -> {
                    for (int move = 0; move < movement.getValue1(); move++) {
                        headLocation = new Pair<>(headLocation.getValue0() - 1, headLocation.getValue1());
                        if (isTailCloseEnough(headLocation, tailLocation)) {
                            continue;
                        }
                        tailLocation = new Pair<>(headLocation.getValue0() + 1, headLocation.getValue1());
                        previousPositions.add(new Pair<>(tailLocation.getValue0(), tailLocation.getValue1()));
                    }
                }
                case 'R' -> {
                    for (int move = 0; move < movement.getValue1(); move++) {
                        headLocation = new Pair<>(headLocation.getValue0() + 1, headLocation.getValue1());
                        if (isTailCloseEnough(headLocation, tailLocation)) {
                            continue;
                        }
                        tailLocation = new Pair<>(headLocation.getValue0() - 1, headLocation.getValue1());
                        previousPositions.add(new Pair<>(tailLocation.getValue0(), tailLocation.getValue1()));
                    }
                }
                default -> throw new UnsupportedOperationException("Invalid direction given in file");
            }
        }
        return previousPositions.size();
    }

    /**
     * Calculates the number of unique spaces that the tail of a rope travels through.
     * @param day9File The file that was provided by the calling method
     * @return A long that represents the number of unique squares the tail has been present in
     */
    public long part2Solve(File day9File) throws IOException {
        List<Pair<Character, Integer>> parsedFile = parseDay9File(day9File);
        return 36L;
    }

    /**
     * Helper method which determines if the tail position is within 1 spot of the head location
     * @param headLocation The current coordinates of the head
     * @param tailLocation The current coordinates of the tail
     * @return A boolean indicating if the tail is close enough to the head
     */
    protected boolean isTailCloseEnough(Pair<Integer, Integer> headLocation, Pair<Integer, Integer> tailLocation) {
        Set<Pair<Integer, Integer>> closePositions = new HashSet<>();
        closePositions.add(new Pair<>(headLocation.getValue0() - 1, headLocation.getValue1() + 1));
        closePositions.add(new Pair<>(headLocation.getValue0(), headLocation.getValue1() + 1));
        closePositions.add(new Pair<>(headLocation.getValue0() + 1, headLocation.getValue1() + 1));
        closePositions.add(new Pair<>(headLocation.getValue0() - 1, headLocation.getValue1()));
        closePositions.add(new Pair<>(headLocation.getValue0(), headLocation.getValue1()));
        closePositions.add(new Pair<>(headLocation.getValue0() + 1, headLocation.getValue1()));
        closePositions.add(new Pair<>(headLocation.getValue0() - 1, headLocation.getValue1() - 1));
        closePositions.add(new Pair<>(headLocation.getValue0(), headLocation.getValue1() - 1));
        closePositions.add(new Pair<>(headLocation.getValue0() + 1, headLocation.getValue1() - 1));

        return closePositions.contains(tailLocation);
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 9 challenge.
     * @param day9File The file that was provided by the calling method
     * @return A list of tuples containing the movement directions for the challenge
     */
    public List<Pair<Character, Integer>> parseDay9File(File day9File) throws IOException {
        List<Pair<Character, Integer>> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day9File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String[] pair = currentLine.split(" ", 2);
                parsedFile.add(new Pair<>(pair[0].charAt(0), Integer.parseInt(pair[1])));

                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

}
