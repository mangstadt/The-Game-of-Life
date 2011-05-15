package com.mangst.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the GameOfLife class.
 * @author mangst
 */
public class GameOfLifeTest {
	/**
	 * Test to make sure it follows the proper game rules when iterating from
	 * state to state.
	 */
	@Test
	public void testIterate() {
		/*
		 * set initial board state
		 * xxx..
		 * .....
		 * ..xxx
		 * x.xx.
		 */
		Grid grid = new Grid(4, 5);
		grid.setAlive(0, 0, true);
		grid.setAlive(0, 1, true);
		grid.setAlive(0, 2, true);
		grid.setAlive(2, 2, true);
		grid.setAlive(2, 3, true);
		grid.setAlive(2, 4, true);
		grid.setAlive(3, 0, true);
		grid.setAlive(3, 2, true);
		grid.setAlive(3, 3, true);

		GameOfLife gameOfLife = new GameOfLife(grid);

		//iterate once
		grid = gameOfLife.iterate();

		/*
		 * .x...
		 * .....
		 * .xx.x
		 * .xx.x
		 */
		// @formatter:off
		boolean expected[][] = new boolean[][] { { false, true, false, false, false }, { false, false, false, false, false }, { false, true, true, false, true }, { false, true, true, false, true } };
		// @formatter:on
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Assert.assertEquals(expected[i][j], grid.isAlive(i, j));
			}
		}

		//iterate twice
		grid = gameOfLife.iterate(2);

		/*
		 * .....
		 * .xx..
		 * .xx..
		 * .xx..
		 * 
		 * .....
		 * .xx..
		 * x..x.
		 * .xx..
		 */
		// @formatter:off
		expected = new boolean[][] { { false, false, false, false, false }, { false, true, true, false, false }, { true, false, false, true, false }, { false, true, true, false, false } };
		// @formatter:on
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Assert.assertEquals(expected[i][j], grid.isAlive(i, j));
			}
		}
	}

	/**
	 * Test to make sure it generates random noise properly.
	 * <p>
	 * NOTE: I found a bug thanks to this unit test! If noise > 1, there is a
	 * chance that the same cell will be randomly chosen twice, and thus, its
	 * state will not have changed.
	 */
	@Test
	public void testSetNoise() {
		/*
		 * set initial board state
		 * xxx..
		 * .....
		 * ..xxx
		 * x.xx.
		 */
		Grid grid = new Grid(4, 5);
		grid.setAlive(0, 0, true);
		grid.setAlive(0, 1, true);
		grid.setAlive(0, 2, true);
		grid.setAlive(2, 2, true);
		grid.setAlive(2, 3, true);
		grid.setAlive(2, 4, true);
		grid.setAlive(3, 0, true);
		grid.setAlive(3, 2, true);
		grid.setAlive(3, 3, true);
		GameOfLife gameOfLife = new GameOfLife(grid);

		//set noise
		gameOfLife.setNoise(10);

		//iterate
		grid = gameOfLife.iterate();

		int noised = 0;
		/*
		 * .x...
		 * .....
		 * .xx.x
		 * .xx.x
		 */
		// @formatter:off
		boolean expected[][] = new boolean[][] { { false, true, false, false, false }, { false, false, false, false, false }, { false, true, true, false, true }, { false, true, true, false, true } };
		// @formatter:on
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (expected[i][j] != grid.isAlive(i, j)) {
					//cell wasn't what it was expected to be
					noised++;
				}
			}
		}

		//10 cells should not be what they are expected to be
		Assert.assertEquals(10, noised);
	}

	/**
	 * Changing the number of threads should not effect the output of the
	 * program.
	 */
	@Test
	public void testSetThreads() {
		/*
		 * set initial board state
		 * xxx..
		 * .....
		 * ..xxx
		 * x.xx.
		 */
		Grid grid = new Grid(4, 5);
		grid.setAlive(0, 0, true);
		grid.setAlive(0, 1, true);
		grid.setAlive(0, 2, true);
		grid.setAlive(2, 2, true);
		grid.setAlive(2, 3, true);
		grid.setAlive(2, 4, true);
		grid.setAlive(3, 0, true);
		grid.setAlive(3, 2, true);
		grid.setAlive(3, 3, true);
		GameOfLife gameOfLife = new GameOfLife(grid);

		//set number of threads to use
		gameOfLife.setThreads(4);

		//iterate
		grid = gameOfLife.iterate();

		/*
		 * .x...
		 * .....
		 * .xx.x
		 * .xx.x
		 */
		// @formatter:off
		boolean expected[][] = new boolean[][] { { false, true, false, false, false }, { false, false, false, false, false }, { false, true, true, false, true }, { false, true, true, false, true } };
		// @formatter:on
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Assert.assertEquals(expected[i][j], grid.isAlive(i, j));
			}
		}
	}

	/**
	 * Test initializing all cells in the grid to dead.
	 */
	@Test
	public void testInitDead() {
		GameOfLife gameOfLife = new GameOfLife(3, 3, 0.0);
		Grid grid = gameOfLife.getGrid();
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Assert.assertFalse(grid.isAlive(i, j));
			}
		}
	}

	/**
	 * Test initializing all cells in the grid to alive.
	 */
	@Test
	public void testInitAlive() {
		GameOfLife gameOfLife = new GameOfLife(3, 3, 1.0);
		Grid grid = gameOfLife.getGrid();
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				Assert.assertTrue(grid.isAlive(i, j));
			}
		}
	}
}
