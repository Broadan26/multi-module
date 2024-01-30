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
class Day1ServiceTest {

    @Inject
    Day1Service day1Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    1000
                    2000
                    3000

                    4000

                    5000
                    6000

                    7000
                    8000
                    9000

                    10000""");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    1000
                    2000
                    3000
                    
                    abcd
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
        long answer = day1Service.part1Solve(testFile);
        assertEquals(24_000, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day1Service.part2Solve(testFile);
        assertEquals(45_000, answer);
    }

    @Test
    void testParseDay1File() throws IOException {
        List<Map<Integer, Integer>> parsedFile = day1Service.parseDay1File(testFile);
        assertEquals(5, parsedFile.size());
        assertEquals(1, parsedFile.getFirst().get(1000));

        IOException exception = assertThrows(
                IOException.class,
                () -> day1Service.parseDay1File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
