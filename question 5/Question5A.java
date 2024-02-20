import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class to implement the Ant Colony Optimization algorithm
class AntColonyOptimization {
    private double[][] distances; // Matrix to store distances between cities
    private int numAnts; // Number of ants in the colony
    private int numIterations; // Number of iterations for the algorithm
    private double[][] pheromones; // Matrix to store pheromone levels between cities
    private double alpha; // Parameter to control the influence of pheromones
    private double beta; // Parameter to control the influence of distances
    private double evaporationRate; // Rate at which pheromones evaporate
    private int numCities; // Total number of cities

    // Constructor to initialize the Ant Colony Optimization with parameters
    public AntColonyOptimization(double[][] distances, int numAnts, int numIterations, double alpha, double beta,
            double evaporationRate) {
        this.distances = distances;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.numCities = distances.length;
        initializePheromones(); // Initialize pheromone levels
    }

    // Method to initialize pheromone levels
    private void initializePheromones() {
        pheromones = new double[numCities][numCities];
        double initialPheromone = 1.0 / (numCities * numCities); // Initial pheromone level
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromones[i][j] = initialPheromone; // Set initial pheromone level for all edges
            }
        }
    }

    // Method to solve the optimization problem
    public List<Integer> solve() {
        List<Integer> bestTour = null; // Store the best tour found
        double bestTourLength = Double.MAX_VALUE; // Initialize the length of the best tour to a very large value
        Random random = new Random(); // Random number generator
        for (int iter = 0; iter < numIterations; iter++) { // Iterate through the specified number of iterations
            List<List<Integer>> antTours = new ArrayList<>(); // Store tours of each ant
            for (int ant = 0; ant < numAnts; ant++) { // Iterate through each ant in the colony
                List<Integer> tour = generateTour(random); // Generate a tour for the ant
                antTours.add(tour); // Add the tour to the list
                double tourLength = calculateTourLength(tour); // Calculate the length of the tour
                if (tourLength < bestTourLength) { // Check if the tour is the best so far
                    bestTourLength = tourLength; // Update the best tour length
                    bestTour = new ArrayList<>(tour); // Update the best tour
                }
            }
            updatePheromones(antTours); // Update pheromone levels based on ant tours
        }
        return bestTour; // Return the best tour found
    }

    // Method to generate a tour for a single ant
    private List<Integer> generateTour(Random random) {
        List<Integer> tour = new ArrayList<>(numCities); // Initialize the tour list
        boolean[] visited = new boolean[numCities]; // Array to keep track of visited cities
        int startCity = random.nextInt(numCities); // Randomly choose a starting city
        tour.add(startCity); // Add the starting city to the tour
        visited[startCity] = true; // Mark the starting city as visited
        for (int i = 1; i < numCities; i++) { // Iterate to select the next city in the tour
            int nextCity = selectNextCity(tour.get(i - 1), visited, random); // Select the next city based on
                                                                             // probabilities
            tour.add(nextCity); // Add the next city to the tour
            visited[nextCity] = true; // Mark the next city as visited
        }
        return tour; // Return the generated tour
    }

    // Method to select the next city based on probabilities
    private int selectNextCity(int currentCity, boolean[] visited, Random random) {
        double[] probabilities = new double[numCities]; // Array to store probabilities for each city
        double totalProbability = 0; // Total probability for normalization
        for (int i = 0; i < numCities; i++) { // Calculate probabilities for unvisited cities
            if (!visited[i]) {
                double pheromone = Math.pow(pheromones[currentCity][i], alpha); // Pheromone influence
                double distance = 1.0 / Math.pow(distances[currentCity][i], beta); // Distance influence
                probabilities[i] = pheromone * distance; // Combined probability
                totalProbability += probabilities[i]; // Update total probability
            }
        }
        double threshold = random.nextDouble() * totalProbability; // Random threshold for selection
        double cumulativeProbability = 0; // Cumulative probability for selection
        for (int i = 0; i < numCities; i++) { // Select the next city based on probabilities
            if (!visited[i]) {
                cumulativeProbability += probabilities[i]; // Update cumulative probability
                if (cumulativeProbability >= threshold) {
                    return i; // Return the selected city
                }
            }
        }
        return -1; // If no city is selected, return -1 (should not occur)
    }

    // Method to update pheromone levels based on ant tours
    private void updatePheromones(List<List<Integer>> antTours) {
        for (int i = 0; i < numCities; i++) { // Update pheromones for each edge
            for (int j = 0; j < numCities; j++) {
                if (i != j) { // Exclude self-loops
                    pheromones[i][j] *= (1 - evaporationRate); // Evaporate pheromones
                }
            }
        }
        for (List<Integer> tour : antTours) { // Update pheromones based on ant tours
            double tourLength = calculateTourLength(tour); // Calculate the length of the tour
            for (int i = 0; i < numCities - 1; i++) { // Update pheromones for each edge in the tour
                int city1 = tour.get(i); // Current city
                int city2 = tour.get(i + 1); // Next city
                pheromones[city1][city2] += 1.0 / tourLength; // Deposit pheromones on the edge
                pheromones[city2][city1] += 1.0 / tourLength; // Symmetrically update pheromones
            }
        }
    }

    // Method to calculate the length of a tour
    private double calculateTourLength(List<Integer> tour) {
        double length = 0; // Initialize tour length
        for (int i = 0; i < numCities - 1; i++) { // Calculate length between consecutive cities
            int city1 = tour.get(i); // Current city
            int city2 = tour.get(i + 1); // Next city
            length += distances[city1][city2]; // Add distance between cities to total length
        }
        length += distances[tour.get(numCities - 1)][tour.get(0)]; // Add distance from last city to starting city
        return length; // Return the total length of the tour
    }
}

// Main class to test the Ant Colony Optimization algorithm
public class Question5A {
    public static void main(String[] args) {
        double[][] distances = { // Distance matrix between cities
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        int numAnts = 10; // Number of ants in the colony
        int numIterations = 100; // Number of iterations for the algorithm
        double alpha = 1.0; // Parameter controlling pheromone influence
        double beta = 2.0; // Parameter controlling distance influence
        double evaporationRate = 0.5; // Rate at which pheromones evaporate

        AntColonyOptimization aco = new AntColonyOptimization(distances, numAnts, numIterations, alpha, beta,
                evaporationRate); // Create instance of AntColonyOptimization
        List<Integer> bestTour = aco.solve(); // Solve the optimization problem
        System.out.println("Best tour: " + bestTour); // Print the best tour found
    }
}
