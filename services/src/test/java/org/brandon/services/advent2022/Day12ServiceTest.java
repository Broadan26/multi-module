package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.brandon.data.advent2022.Day12Model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day12ServiceTest {

    @Inject
    Day12Service day12Service;

    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile12.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile12.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    Sabqponm
                    abcryxxl
                    accszExk
                    acctuvwj
                    abdefghi
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    Sabqponm
                    abcryxxlaccszExk
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
    void testParseDay12File() throws IOException {
        Day12Model day12Model = day12Service.parseDay12File(testFile);
        assertEquals(0, day12Model.getStartX());
        assertEquals(0, day12Model.getStartY());
        assertEquals(5, day12Model.getTraversed().length);
        assertEquals(8, day12Model.getTraversed()[0].length);

        IOException exception = assertThrows(
                IOException.class,
                () -> day12Service.parseDay12File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
