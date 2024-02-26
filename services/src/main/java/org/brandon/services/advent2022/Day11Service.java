package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.brandon.data.advent2022.Day11Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Day11Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day11Service.class);

    /**
     * Simulates the situation for the Advent of Code 2022: Day 11 Part 1 scenario.
     * @param day11File The file that was provided by the calling method
     * @return A long representing the product of the two highest monkey item seen counts
     */
    public long part1Solve(File day11File) throws IOException {
        List<Day11Model> monkeyList = parseDay11File(day11File);

        // Track each round
        int rounds = 20;
        int worryModifier = 3;
        long gcd = calculateGCD(monkeyList);
        for (int i = 0; i < rounds; i++) {
            // Each monkey goes once in a round
            for (Day11Model monkey : monkeyList) {
                performMonkeyAction(monkey, monkeyList, worryModifier, gcd);
            }
        }
        // Check the two highest values at the end
        return calculateTwoLargest(monkeyList);
    }

    /**
     * Simulates the situation for the Advent of Code 2022: Day 11 Part 2 scenario.
     * @param day11File The file that was provided by the calling method
     * @return A long representing the product of the two highest monkey item seen counts
     */
    public long part2Solve(File day11File) throws IOException {
        List<Day11Model> monkeyList = parseDay11File(day11File);

        // Track each round
        int rounds = 10_000;
        int worryModifier = 1;
        long gcd = calculateGCD(monkeyList);
        for (int i = 0; i < rounds; i++) {
            // Each monkey goes once in a round
            for (Day11Model monkey : monkeyList) {
                performMonkeyAction(monkey, monkeyList, worryModifier, gcd);
            }
        }
        // Check the two highest values at the end
        return calculateTwoLargest(monkeyList);
    }

    /**
     * Helper method which simulates an individual monkey's turn with each item they currently possess.
     * @param monkey The monkey whose turn it currently is
     * @param monkeyList The list of monkey model objects
     * @param worryModifier The modifier by which an item is divided prior to being tested
     */
    private void performMonkeyAction(Day11Model monkey, List<Day11Model> monkeyList, int worryModifier, long gcd) {
        // Each monkey has a list of items they currently have
        List<Long> itemList = monkey.getStartingItems();
        while (!itemList.isEmpty()) {
            Long item = itemList.removeFirst();
            monkey.updateItemsInspected();
            long operationValue = monkey.getOperationValue().equals("old") ?
                    item :
                    Integer.parseInt(monkey.getOperationValue());

            // Determine type of operation and perform it
            switch (monkey.getOperationType()) {
                case "ADD" -> item += operationValue;
                case "MULTIPLY" -> item *= operationValue;
                case "SUBTRACT" -> item -= operationValue;
                case "DIVIDE" -> item /= operationValue;
            }

            // Update value and send to new monkey
            item /= worryModifier;
            item = (item % gcd);
            int sendToMonkey = item % monkey.getTest() == 0 ?
                    monkey.getTrueResult() :
                    monkey.getFalseResult();
            monkeyList.get(sendToMonkey).getStartingItems().add(item);
        }
    }

    protected long calculateGCD(List<Day11Model> monkeyList) {
        long gcd = 1;
        for (Day11Model monkey : monkeyList) {
            gcd *= monkey.getTest();
        }
        return gcd;
    }

    /**
     * Calculates the two largest items inspected among all the monkeys present.
     * @param monkeyList The list of monkey model objects
     * @return A long indicating the product of the two largest items inspected count
     */
    protected long calculateTwoLargest(List<Day11Model> monkeyList) {
        long largest = Integer.MIN_VALUE;
        long secondLargest = Integer.MIN_VALUE;
        for (Day11Model monkey : monkeyList) {
            if (monkey.getItemsInspected() > largest) {
                secondLargest = largest;
                largest = monkey.getItemsInspected();
            } else if (monkey.getItemsInspected() > secondLargest) {
                secondLargest = monkey.getItemsInspected();
            }
        }
        return largest * secondLargest;
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 11 challenge.
     * @param day11File The file that was provided by the calling method
     * @return A list of Day 11 Models with each object containing information about how a monkey acts
     */
    public List<Day11Model> parseDay11File(File day11File) throws IOException {
        List<Day11Model> monkeyList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day11File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                Day11Model monkey = new Day11Model();

                // Monkey Name
                monkey.setName(currentLine.trim().substring(0, currentLine.length() - 1));
                // Starting List
                generateStartingList(monkey, reader.readLine().trim());
                // Operation
                generateOperation(monkey, reader.readLine().trim());
                // Test
                generateTest(monkey, reader.readLine().trim());
                generateTrueResult(monkey, reader.readLine().trim());
                generateFalseResult(monkey, reader.readLine().trim());

                monkeyList.add(monkey);
                reader.readLine(); // Burn an empty line
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return monkeyList;
    }

    /**
     * Converts the current line into a list of integers representing a list of items.
     * @param monkey The Monkey Model object containing monkey information
     * @param currentLine The line read in by the file parser
     */
    protected void generateStartingList(Day11Model monkey, String currentLine) {
        List<Long> startingItems = new ArrayList<>();
        String itemList = currentLine.replace("Starting items: ", "");
        String[] splitItemList = itemList.split(",", 20);
        for (String item : splitItemList) {
            startingItems.add(Long.parseLong(item.trim()));
        }
        monkey.setStartingItems(startingItems);
    }

    /**
     * Converts the current line into an operation type and the value used with that operation type.
     * @param monkey The Monkey Model object containing monkey information
     * @param currentLine The line read in by the file parser
     */
    protected void generateOperation(Day11Model monkey, String currentLine) throws IOException {
        String operationLine = currentLine.replace("Operation: new = old ", "");

        // Collect the type of operation
        if (operationLine.contains("+")) {
            monkey.setOperationType("ADD");
            operationLine = operationLine.replace("+ ", "");
        } else if (operationLine.contains("*")) {
            monkey.setOperationType("MULTIPLY");
            operationLine = operationLine.replace("* ", "");
        } else if (operationLine.contains("-")) {
            monkey.setOperationType("SUBTRACT");
            operationLine = operationLine.replace("- ", "");
        } else if (operationLine.contains("/")) {
            monkey.setOperationType("DIVIDE");
            operationLine = operationLine.replace("/ ", "");
        } else {
            throw new IOException("Invalid file provided");
        }

        // Collect the operation value
        monkey.setOperationValue(operationLine.trim());
    }

    /**
     * Reads the line containing the test function and collects the divisible value.
     * @param monkey The Monkey Model object containing monkey information
     * @param currentLine The line read in by the file parser
     */
    protected void generateTest(Day11Model monkey, String currentLine) {
        String testLine = currentLine.replace("Test: divisible by ", "");
        monkey.setTest(Integer.parseInt(testLine));
    }

    /**
     * Reads the line containing the true result and assigns it to the Monkey Model.
     * @param monkey The Monkey Model object containing monkey information
     * @param currentLine The line read in by the file parser
     */
    protected void generateTrueResult(Day11Model monkey, String currentLine) {
        monkey.setTrueResult(Integer.parseInt(currentLine.substring(currentLine.length() - 1)));
    }

    /**
     * Reads the line containing the false result and assigns it to the Monkey Model.
     * @param monkey The Monkey Model object containing monkey information
     * @param currentLine The line read in by the file parser
     */
    protected void generateFalseResult(Day11Model monkey, String currentLine) {
        monkey.setFalseResult(Integer.parseInt(currentLine.substring(currentLine.length() - 1)));
    }

}
