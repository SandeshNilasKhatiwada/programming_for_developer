// The code implements a method to find the K closest values to a target in a binary search tree (BST). Here's the flow of the code:

// 1. **Method Definition**: 
//    - The `closestValues()` method takes three parameters: the root of the BST, the target value, and the number of closest values to find (K).
//    - It returns a list containing the K closest values to the target.

// 2. **Initialization**:
//    - Initialize an empty list `result` to store the K closest values.
//    - Check if the root is null or if the value of K is zero. If so, return the empty `result` list.

// 3. **Priority Queue Initialization**:
//    - Create a priority queue `pq` to store the K closest values. 
//    - The priority queue is initialized with a custom comparator that compares integers based on their absolute difference from the target. This ensures that the smallest absolute differences are at the top of the queue.

// 4. **Iterative In-order Traversal**:
//    - Perform an iterative in-order traversal of the BST using a stack.
//    - Start with the root node and initialize a stack to traverse the tree.
//    - While the current node is not null or the stack is not empty:
//      - Push all left child nodes onto the stack until reaching the leftmost node (smallest value in the subtree).
//      - Pop the top node from the stack, add its value to the priority queue, and move to its right child.
//      - Repeat until all nodes are processed.

// 5. **Extracting K Closest Values**:
//    - After traversing the entire BST, the priority queue will contain the K closest values to the target.
//    - Extract the K closest values from the priority queue and store them in the `result` list.

// 6. **Return Result**:
//    - Return the list containing the K closest values to the target.

// 7. **Main Method**:
//    - In the `main()` method, an example BST is created.
//    - The `closestValues()` method is called with the target value and the number of closest values to find.
//    - The resulting list of closest values is printed to the console.
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Question4B {
    // Method to find the K closest values to the target in a binary search tree
    public List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> result = new ArrayList<>();
        if (root == null || x == 0)
            return result;

        // Priority queue to store the K closest values, ordered by absolute difference
        // from the target
        PriorityQueue<Integer> pq = new PriorityQueue<>(x,
                (a, b) -> Double.compare(Math.abs(b - target), Math.abs(a - target)));

        // Iterative in-order traversal using a stack
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            pq.offer(current.val);
            if (pq.size() > x)
                pq.poll();
            current = current.right;
        }

        // Extract K closest values from the priority queue
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        // Example BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double target = 3.8; // Target value
        int x = 2; // Number of closest values to find

        // Create instance of Question4B class
        Question4B solution = new Question4B();

        // Find and print the K closest values to the target
        List<Integer> closestValues = solution.closestValues(root, target, x);
        System.out.println("Output: " + closestValues);
    }
}
