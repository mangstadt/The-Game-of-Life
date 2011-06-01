package com.mangst.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the Arguments class.
 * @author mangst
 */
public class ArgumentsTest {
	@Test
	public void testExists() {
		Arguments args = new Arguments(new String[] { "--first=George", "-l=Washington", "--president", "-m"  });
		Assert.assertFalse(args.exists("a", "arg"));
		Assert.assertTrue(args.exists("f", "first"));
		Assert.assertTrue(args.exists("l", "last"));
		Assert.assertTrue(args.exists("m", "male"));
		Assert.assertTrue(args.exists("p", "president"));
	}

	@Test
	public void testValue() {
		Arguments args = new Arguments(new String[] { "--first=George", "-l=Washington", "-e=", "-n" });
		String actual, expected;

		//long arg with value
		actual = args.value("f", "first", "default");
		expected = "George";
		Assert.assertEquals(expected, actual);

		//short arg with value
		actual = args.value("l", "last", "default");
		expected = "Washington";
		Assert.assertEquals(expected, actual);

		//non-existent arg
		actual = args.value("f", "foo");
		expected = null;
		Assert.assertEquals(expected, actual);

		//non-existent arg with default value
		actual = args.value("f", "foo", "default");
		expected = "default";
		Assert.assertEquals(expected, actual);

		//empty arg
		actual = args.value("e", "empty");
		expected = "";
		Assert.assertEquals(expected, actual);
		
		//no value
		actual = args.value("n", "novalue");
		expected = null;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testValueInt() {
		Arguments args = new Arguments(new String[] { "--address=1300", "-m=05", "-e=", "-n" });
		Integer actual, expected;

		//long arg with value
		actual = args.valueInt("a", "address", 11);
		expected = 1300;
		Assert.assertEquals(expected, actual);

		//short arg with value
		actual = args.valueInt("m", "month", 11);
		expected = 5;
		Assert.assertEquals(expected, actual);

		//non-existent arg
		actual = args.valueInt("f", "foo");
		expected = null;
		Assert.assertEquals(expected, actual);

		//non-existent arg with default value
		actual = args.valueInt("f", "foo", 11);
		expected = 11;
		Assert.assertEquals(expected, actual);

		//empty arg
		actual = args.valueInt("e", "empty");
		expected = 0;
		Assert.assertEquals(expected, actual);
		
		//no value
		actual = args.valueInt("n", "novalue");
		expected = null;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testValueDouble() {
		Arguments args = new Arguments(new String[] { "--cost=24.99", "-m=32.5", "-e=", "-n" });
		Double actual, expected;

		//long arg with value
		actual = args.valueDouble("c", "cost", 12.99);
		expected = 24.99;
		Assert.assertEquals(expected, actual);

		//short arg with value
		actual = args.valueDouble("m", "mpg", 30.0);
		expected = 32.5;
		Assert.assertEquals(expected, actual);

		//non-existent arg
		actual = args.valueDouble("f", "foo");
		expected = null;
		Assert.assertEquals(expected, actual);

		//non-existent arg with default value
		actual = args.valueDouble("f", "foo", 12.99);
		expected = 12.99;
		Assert.assertEquals(expected, actual);

		//empty arg
		actual = args.valueDouble("e", "empty");
		expected = 0.0;
		Assert.assertEquals(expected, actual);
		
		//no value
		actual = args.valueDouble("n", "novalue");
		expected = null;
		Assert.assertEquals(expected, actual);
	}
}
