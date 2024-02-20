/*
Initially, I set the variable totalDuration to 0. Next, I 
iterate through each construction duration in the given array. 
For each construction duration, I calculate its contribution to 
totalDuration by adding the minimum of splitExpense and half of 
the duration. Additionally, I include the contribution of splitExpense 
for each split between constructions (equal to the total number of 
constructions minus 1). The final totalDuration represents the minimum 
time required to construct engines. In the main method, 
I present an example input array of construction durations along with 
a specified value for splitExpense. Subsequently, I invoke the 
findMinimumConstructionTime function with the provided input and 
print the result to the console, indicating the minimum time needed 
to construct engines.
Output : 4
Time complexity : O(N)
 */

import java.util.PriorityQueue;

class EngineBuilder {
    public int minTimeToBuildAllEngines(int[] engineConstructionTimes, int splitCostPerEngine) {
        // Create a priority queue to store engine construction times.
        PriorityQueue<Integer> engineTimesQueue = new PriorityQueue<>();

        // Add each engine's construction time to the queue.
        for (int time : engineConstructionTimes) {
            engineTimesQueue.offer(time);
        }

        // While there are at least two engines in the queue:
        while (engineTimesQueue.size() > 1) {
            // Remove the smallest construction time (the top element).
            engineTimesQueue.poll();

            // Get the second smallest construction time.
            int secondSmallestTime = engineTimesQueue.poll();

            // Combine the second smallest construction time with the split cost
            // and add it back to the queue.
            engineTimesQueue.offer(secondSmallestTime + splitCostPerEngine);
        }

        // The final result is the construction time of the last remaining engine.
        return engineTimesQueue.poll();
    }

    public static void main(String[] args) {
        int[] engineConstructionTimes = { 1, 2, 3 };
        int splitCostPerEngine = 1;
        EngineBuilder engineBuilder = new EngineBuilder();
        System.out.println("Minimum time cost to complete all engine builds: "
                + engineBuilder.minTimeToBuildAllEngines(engineConstructionTimes, splitCostPerEngine));
    }
}
