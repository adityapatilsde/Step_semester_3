
package assignment_problems;

public class ExamScoreBandCounter {

    public static int countInBand(int[] scores, int low, int high) {

        int first = findFirst(scores, low);
        int last = findFirst(scores, high + 1);

        return last - first;
    }

    public static int findFirst(int[] scores, int target) {

        int left = 0;
        int right = scores.length - 1;
        int answer = scores.length;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] >= target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}