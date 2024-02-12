package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day8ServiceTest {

    @Inject
    Day8Service day8Service;

    private static List<List<Integer>> mapOfFile;
    private static File testFile;
    private static File badFile;
    private static final String FILE_NAME = "src/test/resources/testFile8.txt";
    private static final String BAD_FILE_NAME = "src/test/resources/badFile8.txt";

    @BeforeAll
    static void setup() throws IOException {
        mapOfFile = new ArrayList<>();
        mapOfFile.add(List.of(3,0,3,7,3));
        mapOfFile.add(List.of(2,5,5,1,2));
        mapOfFile.add(List.of(6,5,3,3,2));
        mapOfFile.add(List.of(3,3,5,4,9));
        mapOfFile.add(List.of(3,5,3,9,0));

        testFile = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("""
                    30373
                    25512
                    65332
                    33549
                    35390
                    """);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not write to test file");
        }

        badFile = new File(BAD_FILE_NAME);
        try (FileWriter writer = new FileWriter(BAD_FILE_NAME)) {
            writer.write("""
                    303731
                    255122
                    653323
                    12aa21
                    335492
                    353903
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
        long answer = day8Service.part1Solve(testFile);
        assertEquals(21, answer);
    }

    @Test
    void testPart2Solve() throws IOException {
        long answer = day8Service.part2Solve(testFile);
        assertEquals(8, answer);
    }

    @Test
    void testCalculateVisible() {
        boolean[][] visibleGrid = new boolean[3][3];
        visibleGrid[0][0] = true;
        visibleGrid[1][1] = true;
        visibleGrid[2][2] = true;

        long visibleCount = day8Service.calculateVisible(visibleGrid);
        assertEquals(3, visibleCount);
    }

    @Test
    void testCalculateMaxScenicScore() {
        int[][] scenicGrid = new int[3][3];
        scenicGrid[0][0] = 1;
        scenicGrid[1][1] = 2;
        scenicGrid[2][2] = 3;

        long scenicScore = day8Service.calculateMaxScenicScore(scenicGrid);
        assertEquals(3, scenicScore);
    }

    @Test
    void testCalculateHorizontal() {
        boolean[][] visibleGrid = new boolean[5][5];
        day8Service.calculateHorizontal(mapOfFile, visibleGrid);

        int count = 0;
        for (boolean[] row : visibleGrid) {
            for (boolean col : row) {
                count += col ? 1 : 0;
            }
        }
        assertEquals(18, count);
    }

    @Test
    void testCalculateVertical() {
        boolean[][] visibleGrid = new boolean[5][5];
        day8Service.calculateVertical(mapOfFile, visibleGrid);

        int count = 0;
        for (boolean[] row : visibleGrid) {
            for (boolean col : row) {
                count += col ? 1 : 0;
            }
        }
        assertEquals(15, count);
    }

    @Test
    void testCalculateScenicHorizontal() {
        int[][] scenicGrid = new int[][]{{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        day8Service.calculateScenicHorizontal(mapOfFile, scenicGrid);

        int count = 0;
        for (int[] row : scenicGrid) {
            for (int col : row) {
                count += col > 0 ? 1 : 0;
            }
        }
        assertEquals(15, count);
        assertEquals(1, scenicGrid[2][2]);
    }

    @Test
    void testCalculateScenicVertical() {
        int[][] scenicGrid = new int[][]{{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        day8Service.calculateScenicVertical(mapOfFile, scenicGrid);

        int count = 0;
        for (int[] row : scenicGrid) {
            for (int col : row) {
                count += col > 0 ? 1 : 0;
            }
        }
        assertEquals(15, count);
        assertEquals(1, scenicGrid[2][2]);
    }

    @Test
    void testParseDay8File() throws IOException {
        List<List<Integer>> parsedFile = day8Service.parseDay8File(testFile);
        assertEquals(5, parsedFile.size());
        assertEquals(3, parsedFile.getFirst().getFirst());
        assertEquals(0, parsedFile.getLast().getLast());

        IOException exception = assertThrows(
                IOException.class,
                () -> day8Service.parseDay8File(badFile),
                "Expected IO Exception from bad file, IOException did not occur");
        assertEquals("Invalid file provided", exception.getMessage());
    }

}
