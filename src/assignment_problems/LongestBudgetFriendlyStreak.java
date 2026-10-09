
package assignment_problems;

public class LongestBudgetFriendlyStreak {

    public static int[] longestStreak(int[] costs, int budget) {

        int left = 0;
        int sum = 0;
        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {

            sum += costs[right];

            while (sum > budget) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }

        if (maxLength == 0) {
            return new int[]{0, -1};
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {

        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        int budget = 8;

        int[] result = longestStreak(costs, budget);

        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}