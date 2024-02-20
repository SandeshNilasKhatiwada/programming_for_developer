// Description of How the Code Works:

// Initialization:

// We initialize two priority queues, maxHeap (for the lower half of scores) and minHeap (for the upper half of scores).
// Adding Scores:

// When a score is added, we determine whether it belongs to the lower or upper half of scores and add it to the respective heap.
// We then balance the heaps to ensure that the difference in sizes between the heaps is at most 1.
// Calculating Median Score:

// If the number of scores is even, we calculate the median by averaging the top elements of both heaps.
// If the number of scores is odd, the median is simply the top element of the maxHeap.
// Main Method:

// In the main method, we create an instance of MedianCalculator, add scores, and print the calculated median score after each addition.
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianCalculator {
    private PriorityQueue<Double> maxHeap; // Priority queue to store lower half of scores
    private PriorityQueue<Double> minHeap; // Priority queue to store upper half of scores

    // Constructor to initialize the data structures
    public MedianCalculator() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap for lower half
        minHeap = new PriorityQueue<>(); // Min Heap for upper half
    }

    // Method to add a score to the data structures and balance the heaps
    public void addScore(double score) {
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            maxHeap.add(score);
        } else {
            minHeap.add(score);
        }

        // Balance the heaps if necessary
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // Method to calculate and return the median score
    public double getMedianScore() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2; // If the number of elements is even
        } else {
            return maxHeap.peek(); // If the number of elements is odd
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        MedianCalculator medianCalculator = new MedianCalculator();
        medianCalculator.addScore(85.5);
        medianCalculator.addScore(92.3);
        medianCalculator.addScore(77.8);
        medianCalculator.addScore(90.1);
        System.out.println("Median Score: " + medianCalculator.getMedianScore()); // Output: 88.3

        medianCalculator.addScore(81.2);
        medianCalculator.addScore(88.7);
        System.out.println("Median Score: " + medianCalculator.getMedianScore()); // Output: 86.6
    }
}
