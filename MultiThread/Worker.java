package MultiThread;

import java.util.concurrent.locks.ReentrantLock;

import SingleThread.FloydWarshallSingle;

public class Worker implements Runnable {
	private static final int dim = Driver.dim;
	private static final int I = Integer.MAX_VALUE;
	private int[][] matrix;
	private int k;
	ReentrantLock lock = new ReentrantLock();

	public Worker(int[][] matrix, int k) {
		this.matrix = matrix;
		this.k = k;
	}

	public static void copyMatrix(int[][] matrix) {
		int dim = matrix.length;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				matrix[i][j] = FloydWarshallSingle.adjacencyMatrix[i][j];
				if (i == j) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	@Override
	public synchronized void run() {
		lock.lock();
		try {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (matrix[i][k] == I || matrix[k][j] == I) {
						continue;
					} else if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
//		System.out.println("pass " + (k + 1) + "/" + dim);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
