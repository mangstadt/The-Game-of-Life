package com.mangst.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the Grid class.
 * @author mangst
 */
public class GridTest {
	/**
	 * The grid should start with all cells in the "dead" state.
	 */
	@Test
	public void testAllDead(){
		Grid grid = new Grid(10, 10);
		for (int i = 0; i < grid.getRows(); i++){
			for (int j = 0; j < grid.getCols(); j++){
				Assert.assertFalse(grid.isAlive(i, j));
			}
		}
	}
	
	/**
	 * Test the getRows() method.
	 */
	@Test
	public void testGetRows(){
		Grid grid = new Grid(10, 20);
		int actual, expected;
		
		actual = grid.getRows();
		expected = 10;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test the getCols() method.
	 */
	@Test
	public void testGetCols(){
		Grid grid = new Grid(10, 20);
		int actual, expected;
		
		actual = grid.getCols();
		expected = 20;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test the getAliveSurrounding() method.
	 */
	@Test
	public void testGetAliveSurrounding(){
		Grid grid = new Grid(10, 10);
		int actual, expected;
		
		//all surrounding cells dead
		actual = grid.getAliveSurrounding(0, 0);
		expected = 0;
		Assert.assertEquals(expected, actual);
		
		//one surrounding cell alive
		grid.setAlive(0, 1, true);
		actual = grid.getAliveSurrounding(0, 0);
		expected = 1;
		Assert.assertEquals(expected, actual);
		
		//it shouldn't count itself as a surrounding cell
		grid.setAlive(0, 0, true);
		actual = grid.getAliveSurrounding(0, 0);
		expected = 1;
		Assert.assertEquals(expected, actual);
		
		//non-surrounding cells shouldn't be counted
		grid.setAlive(5, 5, true);
		actual = grid.getAliveSurrounding(0, 0);
		expected = 1;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Tests the setAlive() and isAlive() methods.
	 */
	@Test
	public void testSetAliveIsAlive(){
		Grid grid = new Grid(10, 10);
		
		grid.setAlive(1, 1, true);
		Assert.assertTrue(grid.isAlive(1, 1));
		
		grid.setAlive(1, 1, false);
		Assert.assertFalse(grid.isAlive(1, 1));
	}
}
