
package class_problems;

public class WarehouseBinGridScan {

    public static int[] warehouseSummary(int[][] grid) {

        int total = 0;
        int max = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        return new int[]{total, maxRow, maxCol};
    }

    public static void main(String[] args) {

        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);

        System.out.println("Total = " + result[0]);
        System.out.println("MaxCoordinate = (" + result[1] + ", " + result[2] + ")");
    }
}