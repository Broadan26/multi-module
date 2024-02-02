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

@ApplicationScoped
public class Day6Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day6Service.class);

    /**
     * Solves the day 6 part 1 problem for Advent of Code 2022.
     * Utilizes a shifting window to determine the location of the first marker.
     * @param day6File The file that was provided by the calling method
     * @return A number indicating the location of the first marker in the parsedFile
     */
    public long solve(File day6File, int windowSize) throws IOException {
        String parsedFile = parseDay6File(day6File);
        Map<Character, Integer> window = new HashMap<>();
        long start = 0;
        long end = 0;

        for (Character ch : parsedFile.toCharArray()) {
            if (end - start == windowSize) {
                Character chStart = parsedFile.charAt((int) start);
                if (window.get(chStart) != null && window.get(chStart) > 1) {
                    window.put(chStart, window.get(chStart) - 1);
                } else {
                    window.remove(chStart);
                }
                start += 1;
            }

            if (window.containsKey(ch)) {
                window.put(ch, window.get(ch) + 1);
            } else {
                window.put(ch, 1);
            }

            if (window.size() == windowSize) {
                end += 1;
                break;
            }
            end += 1;
        }
        return end;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 6 challenge.
     * @param day6File The file that was provided by the calling method
     * @return A string containing the parsed day 6 file
     */
    public String parseDay6File(File day6File) throws IOException {
        String input;
        try (BufferedReader reader = new BufferedReader(new FileReader(day6File.getPath()))) {
            input = reader.readLine();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return input;
    }

}
