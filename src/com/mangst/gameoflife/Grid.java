package com.mangst.gameoflife;

/**
 * Represents the grid on which the Game of Life is played.
 * @author mangst
 */
public class Grid {
	/**
	 * The grid of cells.
	 */
	private boolean grid[][];

	/**
	 * Constructs a new grid.
	 * @param rows the number of rows in the grid
	 * @param cols the number of columns in the grid
	 */
	public Grid(int rows, int cols) {
		grid = new boolean[rows + 2][cols + 2]; //edge cells will always be dead to simplify calculations
	}

	/**
	 * Determines whether a cell is alive or dead
	 * @param row the cell's row
	 * @param col the cell's column
	 * @return true if the cell is alive, false if not
	 */
	public boolean isAlive(int row, int col) {
		return grid[row + 1][col + 1];
	}

	/**
	 * Marks a cell as being alive or dead
	 * @param row the cell's row
	 * @param col the cell's column
	 * @param alive true to mark the cell as alive, false to mark it as dead
	 */
	public void setAlive(int row, int col, boolean alive) {
		grid[row + 1][col + 1] = alive;
	}

	/**
	 * Determines how many cells surrounding the given cell are alive.
	 * @param row the cell's row
	 * @param col the cell's column
	 * @return the number of cells surrounding the given cell that are alive
	 */
	public int getAliveSurrounding(int row, int col) {
		int count = 0;

		if (isAlive(row - 1, col - 1)) count++;
		if (isAlive(row, col - 1)) count++;
		if (isAlive(row + 1, col - 1)) count++;

		if (isAlive(row - 1, col)) count++;
		if (isAlive(row + 1, col)) count++;

		if (isAlive(row - 1, col + 1)) count++;
		if (isAlive(row, col + 1)) count++;
		if (isAlive(row + 1, col + 1)) count++;

		return count;
	}

	/**
	 * Gets the number of rows in the grid
	 * @return the number of rows in the grid
	 */
	public int getRows() {
		return grid.length - 2;
	}

	/**
	 * Gets the number of columns in the grid.
	 * @return the number of columns in the grid
	 */
	public int getCols() {
		return grid[0].length - 2;
	}

	@Override
	public String toString() {
		return toStringSerial();
	}

	/**
	 * A serial (single-threaded) version of toString().
	 * @return
	 */
	public String toStringSerial() {
		final String newline = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder(getRows() * (getCols() + newline.length()));

		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++) {
				char c = isAlive(i, j) ? 'x' : ' ';
				sb.append(c);
			}
			sb.append(newline);
		}

		return sb.toString();
	}

	/**
	 * A concurrent (multi-threaded) version of toString().
	 * @return
	 */
	public String toStringConcurrent() {
		int threads = Runtime.getRuntime().availableProcessors();
		
		//decompose data (split the task up into threads)
		ToStringThread[] toStringThreads = new ToStringThread[threads];
		for (int i = 0; i < threads; i++) {
			toStringThreads[i] = new ToStringThread(i, threads);
		}

		//start threads
		for (ToStringThread t : toStringThreads) {
			t.start();
		}

		//wait for threads to finish
		for (ToStringThread t : toStringThreads) {
			try {
				t.join();
			} catch (InterruptedException e) {
			}
		}

		//recombine results
		final String newline = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder(getRows() * (getCols() + newline.length()));
		for (int i = 0; i < getRows(); i++) {
			int mod = i % threads;
			int div = i / threads;
			sb.append(toStringThreads[mod].rows[div]);
			sb.append(newline);
		}

		return sb.toString();
	}

	/**
	 * The thread class that is used to concurrently build the String returned
	 * by toString().
	 * @author mangst
	 */
	private class ToStringThread extends Thread {
		/**
		 * This thread's number.
		 */
		private int num;

		/**
		 * The total number of threads.
		 */
		private int max;

		/**
		 * The rows that this thread has created a String for.
		 */
		public String[] rows;

		/**
		 * Constructs a new thread object.
		 * @param num the thread's number
		 * @param max the total number of threads completing the task
		 */
		public ToStringThread(int num, int max) {
			this.num = num;
			this.max = max;
			this.rows = new String[(int) Math.ceil(getRows() / (double) max)];
		}

		@Override
		public void run() {
			int row = num;
			int i = 0;
			char rowString[] = new char[getCols()];
			while (row < getRows()) {
				for (int j = 0; j < getCols(); j++) {
					rowString[j] = Grid.this.isAlive(row, j) ? 'x' : ' ';
				}
				rows[i++] = new String(rowString);

				row += max;
			}
		}
	}
}
