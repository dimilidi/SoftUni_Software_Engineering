package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseMatrixDiagonals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];
        fillMatrix(matrix, scanner);

        // print first part
        for (int c = cols - 1;  c > 0;  c--) {
            for (int row = rows - 1, col = c; row >= 0 && col < cols ; row--, col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        // print second part
        for (int r = rows - 1; r >= 0 ; r--) {
            for (int row = r, col = 0; row >= 0 && col < cols; row--, col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = input;
        }
    }
}
