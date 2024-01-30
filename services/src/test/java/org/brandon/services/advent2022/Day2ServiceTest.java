package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day2ServiceTest {

    @Inject
    Day2Service day2Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile2.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile2.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    A Y
                    B X
                    C Z
                    
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    A Y
                    B X
                    C Z
                    B B
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
        long answer = day2Service.part1Solve(testFile);
        assertEquals(15L, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day2Service.part2Solve(testFile);
        assertEquals(12L, answer);
    }

    @Test
    void testParseDay2File() throws IOException {
        Map<String, Integer> parsedFile = day2Service.parseDay2File(testFile);
        assertEquals(0, parsedFile.get("A X"));
        assertEquals(1, parsedFile.get("A Y"));
        assertEquals(9, parsedFile.size());

        IOException exception = assertThrows(
                IOException.class,
                () -> day2Service.parseDay2File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
