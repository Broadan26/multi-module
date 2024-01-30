package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day3ServiceTest {

    @Inject
    Day3Service day3Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile3.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile3.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    vJrwpWtwJgWrhcsFMMfFFhFp
                    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                    PmmdzqPrVvPwwTWBwg
                    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                    ttgJtRGJQctTZtZT
                    CrZsJsPPZsGzwwsLwLmpwMDw
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    vJrwpWtwJgWrhcsFMMfFFhFp
                    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                    PmmdzqPrVvPwwTWBwg
                    PEMDAS1234SADMEP
                    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                    ttgJtRGJQctTZtZT
                    CrZsJsPPZsGzwwsLwLmpwMDw
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
        long answer = day3Service.part1Solve(testFile);
        assertEquals(157L, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day3Service.part2Solve(testFile);
        assertEquals(70L, answer);
    }

    @Test
    void testParseDay3Part1File() throws IOException {
        List<Map<Character, Integer>> parsedFile = day3Service.parseDay3Part1File(testFile);
        assertEquals(12, parsedFile.size());
        assertEquals(8, parsedFile.getFirst().size());

        IOException exception = assertThrows(
                IOException.class,
                () -> day3Service.parseDay3Part1File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

    @Test
    void testParseDay3Part2File() throws IOException {
        List<Map<Character, Integer>> parsedFile = day3Service.parseDay3Part2File(testFile);
        assertEquals(6, parsedFile.size());
        assertEquals(14, parsedFile.getFirst().size());

        IOException exception = assertThrows(
                IOException.class,
                () -> day3Service.parseDay3Part2File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
