package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Day7Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day7Service.class);

    /**
     * Stub method until I get around to solving this problem.
     * @param day7File The file that was provided by the calling method
     * @return The long form of 1 as temporary method stub
     */
    public long part1Solve(File day7File) throws IOException {
        LOG.warn("Not solved yet!");
        List<String> parsedFile = parseDay7File(day7File);
        return 1L;
    }

    /**
     * Stub method until I get around to solving this problem.
     * @param day7File The file that was provided by the calling method
     * @return The long form of 1 as temporary method stub
     */
    public long part2Solve(File day7File) throws IOException {
        LOG.warn("Not solved yet!");
        List<String> parsedFile = parseDay7File(day7File);
        return 1L;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 7 challenge.
     * @param day7File The file that was provided by the calling method
     * @return A list of Strings that contain the contents of the provided file
     */
    public List<String> parseDay7File(File day7File) throws IOException {
        List<String> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day7File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                parsedFile.add(currentLine);
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

}
