package MultiThread;

import java.util.ArrayList;
import java.util.List;

public class Creator {
	public static void execute(int[][] matrix) {
		Worker.copyMatrix(matrix);
		List<Thread> threads = new ArrayList<Thread>();
		int len = matrix.length;
		for (int k = 0; k < len; k++) {
			Worker worker = new Worker(matrix, k);
			Thread thread = new Thread(worker);
			thread.start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threads.add(thread);
			if (threads.size() == 10) {
				waitAndClearThreads(threads);
			}
		}
	}

	public static void waitAndClearThreads(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threads.clear();
	}
}
