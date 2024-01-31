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
class Day4ServiceTest {

    @Inject
    Day4Service day4Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile4.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile4.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    2-4,6-8
                    2-3,4-5
                    5-7,7-9
                    2-8,3-7
                    6-6,4-6
                    2-6,4-8
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    2-4,6-8
                    2-3,4-5
                    5-7,7-9
                    1-2,a-b
                    2-8,3-7
                    6-6,4-6
                    2-6,4-8
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
        long answer = day4Service.part1Solve(testFile);
        assertEquals(2L, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day4Service.part2Solve(testFile);
        assertEquals(4L, answer);
    }

    @Test
    void testParseDay4File() throws IOException {
        List<Map<String, Integer>> parsedFile = day4Service.parseDay4File(testFile);
        assertEquals(6, parsedFile.size());
        assertEquals(4, parsedFile.getFirst().size());

        IOException exception = assertThrows(
                IOException.class,
                () -> day4Service.parseDay4File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
