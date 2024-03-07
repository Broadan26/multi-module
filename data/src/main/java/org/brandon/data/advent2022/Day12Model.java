package org.brandon.data.advent2022;

import lombok.Data;

@Data
public class Day12Model {

    // Base grid information
    private final char[][] grid;

    // 2D array filled in as grid is traversed
    // 0 = not visited, 1 = tried, 2 = on path
    private final int[][] traversed;

    // Start origin
    private int startX;
    private int startY;

    /**
     * Creates a 2D array called grid which is traversable. Sets up dimensions, traversal map and finds the origin.
     * @param grid The 2d character array that will be traversed
     */
    public Day12Model(char[][] grid) {
        this.grid = grid;
        this.traversed = new int[this.grid.length][this.grid[0].length];
        this.findStart();
    }

    /**
     * Finds the shortest path through the grid from the origin to the end.
     * @return An integer representing the number of steps it took from Start ---> End
     */
    public long solve() {
        return this.traverse(this.startY, this.startX, this.grid[this.startY][this.startX]);
    }

    /**
     * Performs the traversal through the grid.
     * @return An integer representing the number of steps it took from current step to next step
     */
    private long traverse(int y, int x, char start) {
        // Don't leave the 2D array
        if (this.isOutOfBounds(y, x)) {
            return 0L;
        }

        // Path cannot be taken
        if (this.cannotTakeStep(y, x, start)) {
            return 0L;
        }

        // End was found
        if (this.isTheEnd(y, x, start)) {
            traversed[y][x] = 2;
            return 1L;
        }
        traversed[y][x] = 1;

        // Converting problem in a Djikstra's Shortest Path algo.
        // https://stackoverflow.com/questions/12887921/shortest-path-in-a-2d-array-using-dijkstras-algorithm
        if (traverse(y + 1, x, this.grid[y][x]) > 0) {
            this.traversed[y + 1][x] = 2;
            return 1L;
        }
        if (traverse(y - 1, x, this.grid[y][x]) > 0) {
            this.traversed[y - 1][x] = 2;
            return 1L;
        }
        if (traverse(y, x + 1, this.grid[y][x]) > 0) {
            this.traversed[y][x + 1] = 2;
            return 1L;
        }
        if (traverse(y, x - 1, this.grid[y][x]) > 0) {
            this.traversed[y][x - 1] = 2;
            return 1L;
        }
        return 0L;
    }

    /**
     * Checks if a next step is within the bounds of the grid.
     * @param y The y coordinate of the grid being checked
     * @param x The x coordinate of the grid being checked
     * @return A boolean indicating true if the cell is out of bounds, false if it is in bounds
     */
    private boolean isOutOfBounds(int y, int x) {
        return (x < 0 || y < 0 || x == this.grid[0].length || y == this.grid.length || this.traversed[y][x] == 1);
    }

    /**
     * Checks if the next step can be taken by the rules of the grid.
     * @param y The y coordinate of the grid being checked
     * @param x The x coordinate of the grid being checked
     * @param start The previous character being checked as a next step
     * @return A boolean indicating true if the cell cannot be moved to, false if it can be moved to
     */
    private boolean cannotTakeStep(int y, int x, char start) {
        return this.grid[y][x] - 1 != start && this.grid[y][x] != start && this.grid[y][x] - 2 > start && start != 'S';
    }

    private boolean isTheEnd(int y, int x, char start) {
        return start == 'z' && grid[y][x] =='E';
    }

    /**
     * Finds where the official start is in the provided 2D character array called "grid."
     */
    private void findStart() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'S') {
                    this.startX = col;
                    this.startY = row;
                    break;
                }
            }
        }
    }
}
