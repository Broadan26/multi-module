package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.brandon.data.advent2022.Day10Model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day10ServiceTest {

    @Inject
    Day10Service day10Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile10.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile10.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    addx 15
                    addx -11
                    addx 6
                    addx -3
                    addx 5
                    addx -1
                    addx -8
                    addx 13
                    addx 4
                    noop
                    addx -1
                    addx 5
                    addx -1
                    addx 5
                    addx -1
                    addx 5
                    addx -1
                    addx 5
                    addx -1
                    addx -35
                    addx 1
                    addx 24
                    addx -19
                    addx 1
                    addx 16
                    addx -11
                    noop
                    noop
                    addx 21
                    addx -15
                    noop
                    noop
                    addx -3
                    addx 9
                    addx 1
                    addx -3
                    addx 8
                    addx 1
                    addx 5
                    noop
                    noop
                    noop
                    noop
                    noop
                    addx -36
                    noop
                    addx 1
                    addx 7
                    noop
                    noop
                    noop
                    addx 2
                    addx 6
                    noop
                    noop
                    noop
                    noop
                    noop
                    addx 1
                    noop
                    noop
                    addx 7
                    addx 1
                    noop
                    addx -13
                    addx 13
                    addx 7
                    noop
                    addx 1
                    addx -33
                    noop
                    noop
                    noop
                    addx 2
                    noop
                    noop
                    noop
                    addx 8
                    noop
                    addx -1
                    addx 2
                    addx 1
                    noop
                    addx 17
                    addx -9
                    addx 1
                    addx 1
                    addx -3
                    addx 11
                    noop
                    noop
                    addx 1
                    noop
                    addx 1
                    noop
                    noop
                    addx -13
                    addx -19
                    addx 1
                    addx 3
                    addx 26
                    addx -30
                    addx 12
                    addx -1
                    addx 3
                    addx 1
                    noop
                    noop
                    noop
                    addx -9
                    addx 18
                    addx 1
                    addx 2
                    noop
                    noop
                    addx 9
                    noop
                    noop
                    noop
                    addx -1
                    addx 2
                    addx -37
                    addx 1
                    addx 3
                    noop
                    addx 15
                    addx -21
                    addx 22
                    addx -6
                    addx 1
                    noop
                    addx 2
                    addx 1
                    noop
                    addx -10
                    noop
                    noop
                    addx 20
                    addx 1
                    addx 2
                    addx 2
                    addx -6
                    addx -11
                    noop
                    noop
                    noop
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    addx 15
                    addx -11
                    addx 6
                    addx -3
                    addx 5
                    addx a
                    addx -8
                    addx 13
                    addx 4
                    noop
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to bad test file");
        }
    }

    @AfterAll
    static void cleanup() throws IOException {
        boolean deletedTestFile = testFile.delete();
        if (!deletedTestFile) {
            throw new IOException("Unable to delete test file");
        }

        boolean deletedBadFile = badFile.delete();
        if (!deletedBadFile) {
            throw new IOException("Unable to delete bad test file");
        }
    }

    @Test
    void testPart1Solve() throws IOException {
        long answer = day10Service.part1Solve(testFile);
        assertEquals(13140, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        String answer = day10Service.part2Solve(testFile);
        assertEquals("""
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....""", answer);
    }

    @Test
    void testCycleCheckPartOne() {
        int cycle1 = 0;
        long sum1 = 100;
        long currentVal1 = 10;
        assertEquals(sum1, day10Service.cycleCheckPartOne(cycle1, sum1, currentVal1));

        int cycle2 = 20;
        long sum2 = 200;
        long currentVal2 = 20;
        assertEquals(sum2 + cycle2 * currentVal2, day10Service.cycleCheckPartOne(cycle2, sum2, currentVal2));

        int cycle3 = 40;
        long sum3 = 300;
        long currentVal3 = 30;
        assertEquals(sum3, day10Service.cycleCheckPartOne(cycle3, sum3, currentVal3));

        int cycle4 = 60;
        long sum4 = 400;
        long currentVal4 = 40;
        assertEquals(sum4 + cycle4 * currentVal4, day10Service.cycleCheckPartOne(cycle4, sum4, currentVal4));
    }

    @Test
    void testCycleCheckPartTwo() {
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        String symbol = "";
        StringBuilder outcome = day10Service.cycleCheckPartTwo(result, currentLine, symbol);
    }

    @Test
    void testCheckLocation() {

    }

    @Test
    void testParseDay10File() throws IOException {
        List<Day10Model> parsedFile = day10Service.parseDay10File(testFile);
        assertEquals("addx", parsedFile.getFirst().getAction());
        assertEquals(15, parsedFile.getFirst().getNumber());
        assertEquals("noop", parsedFile.getLast().getAction());
        assertEquals(0, parsedFile.getLast().getNumber());

        IOException exception = assertThrows(
                IOException.class,
                () -> day10Service.parseDay10File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
