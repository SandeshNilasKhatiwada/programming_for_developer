// Explanation of how the code works:

// We iterate through the grid to find the starting position ('S'), target keys ('E'), and other keys ('a' to 'f').
// We use a breadth-first search (BFS) approach to explore all possible paths from the starting position, keeping track of visited cells and collected keys.
// During BFS traversal, we move in all four directions (up, down, left, right) from each cell, considering the constraints such as walls ('W') and locked doors ('A' to 'F').
// We continue BFS traversal until we collect all target keys or exhaust all possible paths.
// Finally, we return the minimum number of moves required to collect all keys, or -1 if it's not possible.

import java.util.LinkedList;
import java.util.Queue;

public class Question4A {
    // Function to find the minimum number of moves required to collect all keys
    public static int findMinimumMovesToCollectAllKeys(String[] grid) {
        // Get the number of rows and columns in the grid
        int rows = grid.length;
        int cols = grid[0].length();
        // Initialize variables to track target keys and starting position
        int targetKeys = 0;
        int startX = 0, startY = 0;
        // Loop through the grid to find the target keys and starting position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } else if (cell == 'E') {
                    // Set the corresponding bit for each key
                    targetKeys |= (1 << ('f' - 'a'));
                } else if (cell >= 'a' && cell <= 'f') {
                    // Set the corresponding bit for each key
                    targetKeys |= (1 << (cell - 'a'));
                }
            }
        }
        // Create a queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        // Create a 3D array to track visited cells and collected keys
        boolean[][][] visited = new boolean[rows][cols][1 << 6];
        // Add the starting position to the queue
        queue.offer(new int[] { startX, startY, 0, 0 });

        // Define the directions for movement: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        // BFS traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int keys = current[2];
            int steps = current[3];
            // Check if all target keys are collected
            if (keys == targetKeys) {
                return steps; // Return the number of steps
            }
            // Explore all possible directions
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                // Check if the new position is within the grid and not blocked by walls
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX].charAt(newY) != 'W') {
                    char cell = grid[newX].charAt(newY);
                    // Check if the cell contains a key or a lock that can be unlocked
                    if (cell == 'E' || cell == 'P' || (cell >= 'a' && cell <= 'f')
                            || (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) != 0)) {
                        int newKeys = keys;
                        if (cell >= 'a' && cell <= 'f') {
                            // Collect the key by setting the corresponding bit
                            newKeys |= (1 << (cell - 'a'));
                        }
                        // Check if the new state (position and keys collected) is visited
                        if (!visited[newX][newY][newKeys]) {
                            // Mark the new state as visited
                            visited[newX][newY][newKeys] = true;
                            // Add the new state to the queue
                            queue.offer(new int[] { newX, newY, newKeys, steps + 1 });
                        }
                    }
                }
            }
        }

        return -1; // No solution found
    }

    public static void main(String[] args) {
        // Example grid
        String[] grid = { "SPaPP", "WWWPW", "bPAPB" };
        // Find the minimum number of moves to collect all keys
        int result = findMinimumMovesToCollectAllKeys(grid);
        // Print the result
        System.out.println("Minimum number of moves: " + result);
    }
}
