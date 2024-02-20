/*
At first I have initialize the variables (numVenues and numThemes)
to store the number of venues and themes respectively and then I created
an 2D array with name of minCosts to store the minimum costs for each 
venue and theme combination.
Before proceeding further, I check if the input array 'venueThemes' is 
null or has zero length in either dimension. If this is the case, 
It return 0 since there are no venues or themes to decorate.
Following input validation, I copy the costs associated with decorating 
the first venue with each theme from 'venueThemes' to the initial row of 
'minCosts'. This is achieved using the 'System.arraycopy' method.
Now, I move on to the dynamic programming phase. I iterate 
through each venue, starting from the second venue. Within this loop, 
for each venue, I further iterate through each theme, calculating the 
minimum and second minimum costs for the previous venue based on the 
adjacency constraint. I then update the current venue's costs based 
on these calculations and the costs associated with the current venue 
and theme.
Following the dynamic programming iteration, I iterate 
through the last row of 'minCosts' to identify the minimum cost 
for decorating the last venue.
Finally, I return the minimum cost found for decorating all 
venues while adhering to the adjacency constraint.
In the main method, I demonstrate the functionality with an 
example input array 'venueThemes'. I call the 'findMinimumCost' 
function with this input and print the result to the console, 
providing the minimum cost of venue decoration.
Output : 7
Time complexity : O(numVenues * numThemes)
 */

public class Question1a {
    // Method to find the minimum cost of decorating venues with themes
    public static int findMinimumCost(int[][] venueThemes) {
        // Check if the input array is null or empty
        if (venueThemes == null || venueThemes.length == 0 || venueThemes[0].length == 0) {
            return 0; // Return 0 if there are no venues or themes
        }

        // Get the number of venues and themes
        int numberOfVenues = venueThemes.length;
        int numThemes = venueThemes[0].length;
        // Create a 2D array to store minimum costs for each venue and theme combination
        int[][] minCosts = new int[numberOfVenues][numThemes];

        // Initialize the first row with the same costs as the first venue
        System.arraycopy(venueThemes[0], 0, minCosts[0], 0, numThemes);

        // Iterating through each venue
        for (int venueIndex = 1; venueIndex < numberOfVenues; venueIndex++) {
            int minCost = Integer.MAX_VALUE;
            int secondMinCost = Integer.MAX_VALUE;

            // Iterating through each theme
            for (int themeIndex = 0; themeIndex < numThemes; themeIndex++) {
                // Finding the minimum and second minimum costs for the previous venue based on
                // adjacency constraint
                if (minCosts[venueIndex - 1][themeIndex] < minCost) {
                    secondMinCost = minCost;
                    minCost = minCosts[venueIndex - 1][themeIndex];
                } else if (minCosts[venueIndex - 1][themeIndex] < secondMinCost) {
                    secondMinCost = minCosts[venueIndex - 1][themeIndex];
                }
            }

            // Updating the current venue's costs based on the calculated minimum and second
            // minimum costs
            for (int themeIndex = 0; themeIndex < numThemes; themeIndex++) {
                minCosts[venueIndex][themeIndex] = (minCosts[venueIndex - 1][themeIndex] == minCost ? secondMinCost
                        : minCost)
                        + venueThemes[venueIndex][themeIndex];
            }
        }

        // Finding the minimum cost for decorating the last venue
        int result = Integer.MAX_VALUE;
        for (int cost : minCosts[numberOfVenues - 1]) {
            result = Math.min(result, cost);
        }
        return result; // Return the minimum cost
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        // Example input array of venueThemes
        int[][] venueThemes = { { 1, 3, 2 }, { 4, 6, 8 }, { 3, 1, 5 } };
        // Call the findMinimumCost method with the input array and print the result
        int minCost = findMinimumCost(venueThemes);
        System.out.println("The minimum cost to decorate all the venues while\n"
                + "satisfying the adjacency constraint is : " + minCost);
    }
}
