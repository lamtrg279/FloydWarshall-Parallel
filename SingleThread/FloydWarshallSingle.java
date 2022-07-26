package SingleThread;

import java.util.Random;

import MultiThread.Driver;

public class FloydWarshallSingle {
	private static final int I = Integer.MAX_VALUE; // Infinity
	private static final int dim = Driver.dim;
	private static double fill = 0.3;
	private static int maxDistance = 100;
	public static int adjacencyMatrix[][] = new int[dim][dim];
	public static int d[][] = new int[dim][dim];

	/*
	 * Generate a randomized matrix to use for the algorithm.
	 */
	public static void generateMatrix() {
		Random random = new Random();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (i != j)
					adjacencyMatrix[i][j] = I;
			}
		}
		for (int i = 0; i < dim * dim * fill; i++) {
			adjacencyMatrix[random.nextInt(dim)][random.nextInt(dim)] = random.nextInt(maxDistance + 1);
		}
	}

	/*
	 * Execute Floyd Warshall on adjacencyMatrix.
	 */
	public static void execute() {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				d[i][j] = adjacencyMatrix[i][j];
				if (i == j) {
					d[i][j] = 0;
				}
			}
		}
		for (int k = 0; k < dim; k++) {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (d[i][k] == I || d[k][j] == I) {
						continue;
					} else if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
//			System.out.println("pass " + (k + 1) + "/" + dim);
		}
	}

	/*
	 * Print matrix[dim][dim]
	 */
	public static void print(int matrix[][]) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (matrix[i][j] == I) {
					System.out.print("I" + " ");
				} else {
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	/*
	 * Compare two matrices, matrix1[dim][dim] and matrix2[dim][dim] and print
	 * whether they are equivalent.
	 */
	public static void compare(int matrix1[][], int matrix2[][]) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (matrix1[i][j] != matrix2[i][j]) {
					System.out.println("Comparison failed " + i + " " + j);
				}
			}
		}
		System.out.println("Comparison succeeded");
	}

	public static void main(String[] args) {
		long start, end;
		generateMatrix();
		print(adjacencyMatrix);
		start = System.nanoTime();
		execute();
		end = System.nanoTime();
		System.out.println("time consumed: " + (double) (end - start) / 1000000000);
		print(d);
		compare(d, d);
	}
}
