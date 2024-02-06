package org.brandon.services.advent2022;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class Day8Service {

    private static final Logger LOG = LoggerFactory.getLogger(Day8Service.class);

    /**
     * Solves the day 8 part 1 problem for Advent of Code 2022.
     * @param day8File The file that was provided by the calling method
     * @return A long indicating the number of trees visible from the edges of the forest
     */
    public long part1Solve(File day8File) throws IOException {
        List<List<Integer>> parsedFile = parseDay8File(day8File);
        boolean[][] visibleGrid = new boolean[parsedFile.size()][parsedFile.getFirst().size()];

        // Determine visible trees
        calculateHorizontal(parsedFile, visibleGrid);
        calculateVertical(parsedFile, visibleGrid);

        // Calculate the total
        return calculateVisible(visibleGrid);
    }

    /**
     * Solves the day 8 part 2 problem for Advent of Code 2022.
     * @param day8File The file that was provided by the calling method
     * @return A long indicating the number of trees visible from the edges of the forest
     */
    public long part2Solve(File day8File) throws IOException {
        List<List<Integer>> parsedFile = parseDay8File(day8File);
        int[][] scenicGrid = new int[parsedFile.size()][parsedFile.getFirst().size()];
        for (int[] ints : scenicGrid) {
            Arrays.fill(ints, 1);
        }

        // Calculate scenic scores
        calculateScenicHorizontal(parsedFile, scenicGrid);
        calculateScenicVertical(parsedFile, scenicGrid);

        // Calculate the maximum scenic score in the forest
        return calculateMaxScenicScore(scenicGrid);
    }

    /**
     * Calculates the total number of trees that can be seen from every edge of the forest.
     * @param visibleGrid The 2D array of trees that are visible to an observer outside the forest
     * @return A long indicating the number of trees visible from the edges of the forest
     */
    protected long calculateVisible(boolean[][] visibleGrid) {
        // Count all visible trees
        long seenCount = 0;
        for (boolean[] row : visibleGrid) {
            for (boolean col : row) {
                if (col) {
                    seenCount += 1;
                }
            }
        }
        return seenCount;
    }

    /**
     * Calculates the maximum scenic score in the provided forest.
     * @param scenicGrid A 2D array of integers indicating the scenic score of a particular tree
     * @return A long indicating the maximum scenic score
     */
    protected long calculateMaxScenicScore(int[][] scenicGrid) {
        long scenicScore = -1;
        for (int[] row : scenicGrid) {
            for (int col : row) {
                if (col > scenicScore) {
                    scenicScore = col;
                }
            }
        }
        return scenicScore;
    }

    /**
     * Determines which trees can be seen from the left and right side of the forest and stores that data in the visibleGrid.
     * @param parsedFile A 2D list of integers indicating the height of trees in a forest
     * @param visibleGrid The 2D array of trees that are visible to an observer outside the forest
     */
    protected void calculateHorizontal(List<List<Integer>> parsedFile, boolean[][] visibleGrid) {
        for (int row = 0; row < parsedFile.size(); row++) {
            // View from the left
            int height = -1;
            for (int col = 0; col < parsedFile.get(row).size(); col++) {
                if (parsedFile.get(row).get(col) > height) {
                    visibleGrid[row][col] = true;
                    height = parsedFile.get(row).get(col);
                }
            }
            // View from the right
            height = -1;
            for (int col = parsedFile.getFirst().size() - 1; col >= 0; col--) {
                if (parsedFile.get(row).get(col) > height) {
                    visibleGrid[row][col] = true;
                    height = parsedFile.get(row).get(col);
                }
            }
        }
    }

    /**
     * Determines which trees can be seen from the top and bottom side of the forest and stores that data in the visibleGrid.
     * @param parsedFile A 2D list of integers indicating the height of trees in a forest
     * @param visibleGrid The 2D array of trees that are visible to an observer outside the forest
     */
    protected void calculateVertical(List<List<Integer>> parsedFile, boolean[][] visibleGrid) {
        for (int col = 0; col < parsedFile.getFirst().size(); col++) {
            // View from the top
            int height = -1;
            for (int row = 0; row < parsedFile.size(); row++) {
                if (parsedFile.get(row).get(col) > height) {
                    visibleGrid[row][col] = true;
                    height = parsedFile.get(row).get(col);
                }
            }

            // View from the bottom
            height = -1;
            for (int row = parsedFile.size() - 1; row >= 0; row--) {
                if (parsedFile.get(row).get(col) > height) {
                    visibleGrid[row][col] = true;
                    height = parsedFile.get(row).get(col);
                }
            }
        }
    }

    /**
     * Calculates the scenic score of trees from their left and right sides
     * @param parsedFile A 2D list of integers indicating the height of trees in a forest
     * @param scenicGrid A 2D array of integers indicating the scenic score of a particular tree
     */
    protected void calculateScenicHorizontal(List<List<Integer>> parsedFile, int[][] scenicGrid) {
        for (int row = 0; row < parsedFile.size(); row++) {
            // Trees seen from the left
            for (int col = 0; col < parsedFile.get(row).size(); col++) {
                int height = parsedFile.get(row).get(col);
                for (int left = col - 1; left >= -1; left--) {
                    // Exited left bound of the forest
                    if (left == -1) {
                        scenicGrid[row][col] *= (col);
                        break;
                    }
                    // Still in the forest
                    if (parsedFile.get(row).get(left) >= height) {
                        scenicGrid[row][col] *= (col - left);
                        break;
                    }
                }
            }

            // Trees seen from the right
            for (int col = parsedFile.getFirst().size() - 1; col >= 0; col--) {
                int height = parsedFile.get(row).get(col);
                for (int right = col + 1; right <= parsedFile.get(row).size(); right++) {
                    // Exited right bound of the forest
                    if (right == parsedFile.get(row).size()) {
                        scenicGrid[row][col] *= (right - col - 1);
                        break;
                    }
                    // Still in the forest
                    if (parsedFile.get(row).get(right) >= height) {
                        scenicGrid[row][col] *= (right - col);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Calculates the scenic score of trees from their top and bottom sides
     * @param parsedFile A 2D list of integers indicating the height of trees in a forest
     * @param scenicGrid A 2D array of integers indicating the scenic score of a particular tree
     */
    protected void calculateScenicVertical(List<List<Integer>> parsedFile, int[][] scenicGrid) {
        for (int col = 0; col < parsedFile.getFirst().size(); col++) {
            // Trees seen from the top
            for (int row = 0; row < parsedFile.size(); row++) {
                int height = parsedFile.get(row).get(col);
                for (int top = row - 1; top >=  -1; top--) {
                    // Exited top bound of the forest
                    if (top == -1) {
                        scenicGrid[row][col] *= row;
                        break;
                    }
                    // Still in the forest
                    if (parsedFile.get(top).get(col) >= height) {
                        scenicGrid[row][col] *= (row - top);
                        break;
                    }
                }
            }

            // Trees seen from top
            for (int row = parsedFile.size() - 1; row >= 0; row--) {
                int height = parsedFile.get(row).get(col);
                for (int bottom = row + 1; bottom <= parsedFile.size(); bottom++) {
                    // Exited bottom bound of the forest
                    if (bottom == parsedFile.size()) {
                        scenicGrid[row][col] *= (bottom - row - 1);
                        break;
                    }
                    // Still in the forest
                    if (parsedFile.get(bottom).get(col) >= height) {
                        scenicGrid[row][col] *= (bottom - row);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Parses a file that is of the type provided for the Advent of Code 2022: Day 8 challenge.
     * @param day8File The file that was provided by the calling method
     * @return A 2D list of integers that contain the contents of the provided file
     */
    public List<List<Integer>> parseDay8File(File day8File) throws IOException {
        List<List<Integer>> parsedFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(day8File.getPath()))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                List<Integer> lineToAdd = new ArrayList<>();
                for (int i = 0; i < currentLine.length(); i++) {
                    lineToAdd.add(Integer.parseInt(String.valueOf(currentLine.charAt(i))));
                }
                parsedFile.add(lineToAdd);
                currentLine = reader.readLine();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new IOException("Invalid file provided");
        }
        return parsedFile;
    }

}
