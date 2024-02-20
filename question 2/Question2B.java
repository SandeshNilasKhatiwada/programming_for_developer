// Initialization:

// Start with person 0 knowing the secret and store this information in a set.Process Intervals:

// Sort the intervals by their start time.For each interval,if anyone within the interval already knows the secret,add all individuals within the interval to the set of those who know the secret.Repeat Until No Changes:

// Keep processing intervals until no new individuals learn the secret in a full pass through the intervals.This ensures that the secret is shared as far as possible within the given intervals.Return the Result:

// After processing all intervals and no more individuals can learn the secret,return the set of individuals who know the secret.

import java.util.HashSet;
import java.util.Set;

public class Question2B {

    // Method to find individuals who will eventually know the secret
    public static Set<Integer> findIndividualsWhoKnowSecret(int numberOfIndividuals, int[][] sharingIntervals) {
        // Set to store individuals who know the secret
        Set<Integer> individualsWhoKnowSecret = new HashSet<>();
        individualsWhoKnowSecret.add(0); // Initially, only person 0 knows the secret

        boolean changed;
        // Repeat until no new individuals learn the secret
        do {
            changed = false;
            // Iterate through each sharing interval
            for (int[] interval : sharingIntervals) {
                // Check if any individual within the interval knows the secret
                if (shareSecretWithinInterval(interval, individualsWhoKnowSecret)) {
                    changed = true; // Set flag if new individuals learn the secret
                }
            }
        } while (changed); // Continue loop until no new individuals learn the secret

        return individualsWhoKnowSecret; // Return the set of individuals who know the secret
    }

    // Method to share the secret within an interval
    private static boolean shareSecretWithinInterval(int[] interval, Set<Integer> individualsWhoKnowSecret) {
        boolean intervalHasSecret = false;
        // Check if any individual within the interval knows the secret
        for (int i = interval[0]; i <= interval[1]; i++) {
            if (individualsWhoKnowSecret.contains(i)) {
                intervalHasSecret = true;
                break;
            }
        }
        // If someone within the interval knows the secret, share it with others
        if (intervalHasSecret) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                // If this individual didn't know the secret before, add them to the set
                if (!individualsWhoKnowSecret.contains(i)) {
                    individualsWhoKnowSecret.add(i);
                    return true; // Indicate that the set has changed
                }
            }
        }
        return false; // Indicate no change in the set
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        int numberOfIndividuals = 5;
        int[][] sharingIntervals = { { 0, 2 }, { 1, 3 }, { 2, 4 } };
        // Find individuals who know the secret based on sharing intervals
        Set<Integer> individualsWhoKnowSecret = findIndividualsWhoKnowSecret(numberOfIndividuals, sharingIntervals);
        System.out.println(individualsWhoKnowSecret); // Print the set of individuals who know the secret
    }
}
