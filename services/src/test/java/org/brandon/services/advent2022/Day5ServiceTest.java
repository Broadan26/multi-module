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
class Day5ServiceTest {

    @Inject
    Day5Service day5Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile5.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile5.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    move 1 from 3 to 9
                    move 2 from 2 to 1
                    move 3 from 5 to 4
                    move 1 from 1 to 8
                    move 1 from 3 to 9
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    Bad File
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
        String answer = day5Service.part1Solve(testFile);
        assertEquals("FQVJGLNHS", answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        String answer = day5Service.part2Solve(testFile);
        assertEquals("HQVZGLNFS", answer);
    }

    @Test
    void testParseDa54File() throws IOException {
        List<Map<String, Integer>> parsedList = day5Service.parseDay5File(testFile);
        assertEquals(5, parsedList.size());
        assertEquals(1, parsedList.getFirst().get("amount"));
        assertEquals(3, parsedList.getFirst().get("startStack"));
        assertEquals(9, parsedList.getFirst().get("endStack"));

        IOException exception = assertThrows(
                IOException.class,
                () -> day5Service.parseDay5File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
