package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.javatuples.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class Day9ServiceTest {

    @Inject
    Day9Service day9Service;

    private static File testFile;
    private static File badFile1;
    private static File badFile2;
    private static final String FILE_NAME = "src/test/resources/testFile9.txt";
    private static final String BAD_FILE_NAME_1 = "src/test/resources/badFile_1_9.txt";
    private static final String BAD_FILE_NAME_2 = "src/test/resources/badFile_2_9.txt";

    @BeforeAll
    static void setup() throws IOException {
        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    R 4
                    U 4
                    L 3
                    D 1
                    R 4
                    D 1
                    L 5
                    R 2
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile1 = new File(BAD_FILE_NAME_1);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME_1)) {
            writer.write("""
                    R 4
                    U 4
                    L 3
                    D 1
                    DR a
                    R 4
                    D 1
                    L 5
                    R 2
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to bad test file");
        }

        badFile2 = new File(BAD_FILE_NAME_2);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME_2)) {
            writer.write("""
                    Z 1
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

        boolean deletedBadFile1 = badFile1.delete();
        if (!deletedBadFile1) {
            throw new IOException("Unable to delete bad test file");
        }

        boolean deletedBadFile2 = badFile2.delete();
        if (!deletedBadFile2) {
            throw new IOException("Unable to delete bad test file");
        }
    }

    @Test
    void testPart1Solve() throws IOException {
        long answer = day9Service.part1Solve(testFile);
        assertEquals(13, answer);
    }

    @Test
    void testPart1SolveBad() throws UnsupportedOperationException {
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> day9Service.part1Solve(badFile2),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid direction given in file", exception.getMessage());
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day9Service.part2Solve(testFile);
        assertEquals(36, answer);
    }

    @Test
    void testIsTailCloseEnough() {
        Pair<Integer, Integer> headLocation1 = new Pair<>(0,0);
        Pair<Integer, Integer> tailLocation1 = new Pair<>(0,1);
        Pair<Integer, Integer> tailLocation2 = new Pair<>(0,-1);
        Pair<Integer, Integer> tailLocation3 = new Pair<>(1,0);
        Pair<Integer, Integer> tailLocation4 = new Pair<>(-1,0);
        Pair<Integer, Integer> tailLocation5 = new Pair<>(1,1);
        Pair<Integer, Integer> tailLocation6 = new Pair<>(1,-1);
        Pair<Integer, Integer> tailLocation7 = new Pair<>(-1,1);
        Pair<Integer, Integer> tailLocation8 = new Pair<>(-1,-1);
        Pair<Integer, Integer> tailLocation9 = new Pair<>(0,0);
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation1));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation2));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation3));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation4));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation5));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation6));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation7));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation8));
        assertTrue(day9Service.isTailCloseEnough(headLocation1, tailLocation9));

        Pair<Integer, Integer> tailLocation10 = new Pair<>(2,2);
        assertFalse(day9Service.isTailCloseEnough(headLocation1, tailLocation10));
    }

    @Test
    void testParseDay9File() throws IOException {
        List<Pair<Character, Integer>> parsedFile = day9Service.parseDay9File(testFile);
        assertEquals(8, parsedFile.size());
        assertEquals('R', parsedFile.getFirst().getValue0());
        assertEquals(4, parsedFile.getFirst().getValue1());
        assertEquals('R', parsedFile.getLast().getValue0());
        assertEquals(2, parsedFile.getLast().getValue1());

        IOException exception = assertThrows(
                IOException.class,
                () -> day9Service.parseDay9File(badFile1),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
