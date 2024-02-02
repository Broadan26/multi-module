package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@ApplicationScoped
public class Day5Service {

    private static final String AMOUNT = "amount";
    private static final String START_STACK = "startStack";
    private static final String END_STACK = "endStack";

    private static final Logger LOG = LoggerFactory.getLogger(Day5Service.class);

    /**
     * Solves the day 5 part 1 problem for Advent of Code 2022.
     * Moves characters between stacks by following the instructions provided in the parsed input file.
     * @param day5File The file that was provided by the calling method
     * @return A string showing the top character of the resulting stacks
     */
    public String part1Solve(File day5File) throws IOException {
        List<Map<String, Integer>> parsedFile = parseDay5File(day5File);
        var stackList = createBaseStacks();

        // Perform the steps
        for (var step : parsedFile) {
            for (int i = 0; i < step.get(AMOUNT); i++) {
                Character current = stackList.get(step.get(START_STACK)).pop();
                stackList.get(step.get(END_STACK)).addFirst(current);
            }
        }
        return topOfTheStacks(stackList);
    }

    /**
     * Solves the day 5 part 2 problem for Advent of Code 2022.
     * Moves characters between stacks by following the instructions provided in the parsed input file.
     * @param day5File The file that was provided by the calling method
     * @return A string showing the top character of the resulting stacks
     */
    public String part2Solve(File day5File) throws IOException {
        List<Map<String, Integer>> parsedFile = parseDay5File(day5File);
        var stackList = createBaseStacks();

        // Perform the steps
        for (var step : parsedFile) {
            Deque<Character> stackToAdd = new ArrayDeque<>();
            for (int i = 0; i < step.get(AMOUNT); i++) {
                stackToAdd.addFirst(stackList.get(step.get(START_STACK)).pop());
            }

            for (int i = 0; i < step.get(AMOUNT); i++) {
                stackList.get(step.get(END_STACK)).addFirst(stackToAdd.pop());
            }
        }
        return topOfTheStacks(stackList);
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 5 challenge.
     * Files are of the form `move <amount> from <startStack> to <endStack>`
     * @param day5File The file that was provided by the calling method
     * @return A list of maps containing the amount of items moved and where stacks are moved
     */
    public List<Map<String, Integer>> parseDay5File(File day5File) throws IOException {
        List<Map<String, Integer>> parsedFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(day5File.getPath()))) {
            String currentLine = reader.readLine();
            Map<String, Integer> movements;
            while (currentLine != null) {

                movements = new HashMap<>();
                String[] splitLine = currentLine.split(" ", 6);

                movements.put(AMOUNT, Integer.parseInt(splitLine[1]));
                movements.put(START_STACK, Integer.parseInt(splitLine[3]));
                movements.put(END_STACK, Integer.parseInt(splitLine[5]));
                parsedFile.add(movements);

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
     * Helper method which creates the original state of the stacks per the problem.
     * @return A list of stacks containing letters indicating the contents of the stack
     */
    private List<Deque<Character>> createBaseStacks() {
        List<Deque<Character>> stackList = new ArrayList<>();
        stackList.add(new ArrayDeque<>()); // 0
        stackList.add(new ArrayDeque<>(Arrays.asList('D', 'H', 'R', 'Z', 'S', 'P', 'W', 'Q'))); // 1
        stackList.add(new ArrayDeque<>(Arrays.asList('F', 'H', 'Q', 'W', 'R', 'B', 'V'))); // 2
        stackList.add(new ArrayDeque<>(Arrays.asList('H', 'S', 'V', 'C'))); // 3
        stackList.add(new ArrayDeque<>(Arrays.asList('G', 'F', 'H'))); // 4
        stackList.add(new ArrayDeque<>(Arrays.asList('Z', 'B', 'J', 'G', 'P'))); // 5
        stackList.add(new ArrayDeque<>(Arrays.asList('L', 'F', 'W', 'H', 'J', 'T', 'Q'))); // 6
        stackList.add(new ArrayDeque<>(Arrays.asList('N', 'J', 'V', 'L', 'D', 'W', 'T', 'Z'))); // 7
        stackList.add(new ArrayDeque<>(Arrays.asList('F', 'H', 'G', 'J', 'C', 'Z', 'T', 'D'))); // 8
        stackList.add(new ArrayDeque<>(Arrays.asList('H', 'B', 'M', 'V', 'P', 'W'))); // 9

        return stackList;
    }

    /**
     * Helper method which collects the top character from each stack in the provided list of stacks.
     * @param stackList A list of stacks containing letters indicating the contents of the stack
     * @return A string representation of the top character from each stack
     */
    private String topOfTheStacks(List<Deque<Character>> stackList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < stackList.size(); i++) {
            sb.append(stackList.get(i).getFirst());
        }
        return sb.toString();
    }

}
