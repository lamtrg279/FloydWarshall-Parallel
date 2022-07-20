package MultiThread;

import SingleThread.FloydWarshallSingle;

public class Driver {
	public static final int dim = 5000;
	private static int matrix[][] = new int[dim][dim];

	public static void main(String[] args) {
		long start, end;
		FloydWarshallSingle.generateMatrix();

		start = System.nanoTime();
		FloydWarshallSingle.execute();
		end = System.nanoTime();
		System.out.println("time consumed " + 1 + " core " + (double) (end - start) / 1000000000);

		start = System.nanoTime();
		Creator.execute(matrix);
		end = System.nanoTime();
		System.out.println("time consumed " + 10 + " cores " + (double) (end - start) / 1000000000);
		FloydWarshallSingle.compare(FloydWarshallSingle.d, matrix);
//		FloydWarshallSingle.print(matrix);
		System.out.println();
//		FloydWarshallSingle.print(FloydWarshallSingle.d);
//		FloydWarshallSingle.print(FloydWarshallSingle.adjacencyMatrix);

	}
}
