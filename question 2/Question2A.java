// Description of how the solution works:

// The method minimumMovesToEqualize takes an array dresses representing the number of dresses each person has.
// It calculates the total number of dresses and checks if the total can be equally distributed among all people. If not, it returns -1.
// It calculates the average number of dresses each person should have.
// It iterates through each person's dresses, calculates the difference between the actual and average dresses, and updates the minimum number of moves required to equalize dresses.
// Finally, it returns the minimum number of moves required to equalize dresses.

public class Question2A {

    // Method to calculate the minimum number of moves to equalize dresses
    static int minimumMovesToEqualize(int[] dresses) {
        int totalDresses = 0; // Variable to store the total number of dresses
        int n = dresses.length; // Number of dresses

        // Calculate the total number of dresses
        for (int dress : dresses) {
            totalDresses += dress;
        }

        // Check if it's possible to equalize the number of dresses
        if (totalDresses % n != 0) {
            return -1; // Return -1 if equal distribution is not possible
        }

        // Calculate the average number of dresses each person should have
        int averageDresses = totalDresses / n;

        int moves = 0; // Variable to store the minimum number of moves
        int diff = 0; // Variable to store the difference between actual and average dresses

        // Iterate through each dress
        for (int i = 0; i < n; i++) {
            // Calculate the difference between actual and average dresses
            diff += dresses[i] - averageDresses;
            // Update the minimum number of moves
            moves = Math.max(moves, Math.abs(diff));
            // Equalize the number of dresses for this person
            dresses[i] = averageDresses;
        }

        return moves; // Return the minimum number of moves required to equalize dresses
    }

    public static void main(String[] args) {
        int[] dresses = { 1, 0, 5 }; // Example array representing dresses
        // Print the minimum number of moves to equalize dresses
        System.out.println("Minimum number of moves to equalize dresses: " + minimumMovesToEqualize(dresses));
    }
}
