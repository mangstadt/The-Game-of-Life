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
		Arguments args = new Arguments(new String[] { "--first=George", "-l=Washington", "--president", "-m" , "-abc" });
		Assert.assertFalse(args.exists("d", "doesNotExist"));
		Assert.assertTrue(args.exists("f", "first"));
		Assert.assertTrue(args.exists("l", "last"));
		Assert.assertTrue(args.exists("m", "male"));
		Assert.assertTrue(args.exists("p", "president"));
		Assert.assertTrue(args.exists("a", "aGroup"));
		Assert.assertTrue(args.exists("b", "bGroup"));
		Assert.assertTrue(args.exists("c", "cGroup"));
	}

	@Test
	public void testValue() {
		Arguments args = new Arguments(new String[] { "--first=George", "-l=Washington", "-e=", "-n", "-abc=value" });
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
		
		//grouped flags with a value
		expected = "value";
		actual = args.value("a", "aGroup");
		Assert.assertEquals(expected, actual);
		actual = args.value("b", "bGroup");
		Assert.assertEquals(expected, actual);
		actual = args.value("c", "cGroup");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testValueInt() {
		Arguments args = new Arguments(new String[] { "--port=80", "-m=05", "-e=", "-n", "-abc=123" });
		Integer actual, expected;

		//long arg with value
		actual = args.valueInt("p", "port", 11);
		expected = 80;
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
		
		//grouped flags with a value
		expected = 123;
		actual = args.valueInt("a", "aGroup");
		Assert.assertEquals(expected, actual);
		actual = args.valueInt("b", "bGroup");
		Assert.assertEquals(expected, actual);
		actual = args.valueInt("c", "cGroup");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testValueDouble() {
		Arguments args = new Arguments(new String[] { "--total=24.99", "-m=32.5", "-e=", "-n", "-abc=123.456" });
		Double actual, expected;

		//long arg with value
		actual = args.valueDouble("t", "total", 12.99);
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
		
		//grouped flags with a value
		expected = 123.456;
		actual = args.valueDouble("a", "aGroup");
		Assert.assertEquals(expected, actual);
		actual = args.valueDouble("b", "bGroup");
		Assert.assertEquals(expected, actual);
		actual = args.valueDouble("c", "cGroup");
		Assert.assertEquals(expected, actual);
	}
}
