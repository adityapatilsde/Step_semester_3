
package class_problems;

public class MaximumSumFixedWindow {

    public static int maxSumSubarray(int[] sales, int k) {

        int sum = 0;

        // Calculate the sum of the first window
        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }

        int maxSum = sum;

        // Slide the window
        for (int i = k; i < sales.length; i++) {

            sum = sum + sales[i] - sales[i - k];

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] sales = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSumSubarray(sales, k));
    }
}