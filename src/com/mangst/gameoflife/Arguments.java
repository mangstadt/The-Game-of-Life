package com.mangst.gameoflife;

/**
 * A class that is used to parse command-line arguments.
 * @author mangst
 */
public class Arguments {
	/**
	 * The command-line arguments.
	 */
	private String args[];

	/**
	 * Constructs a new arguments object.
	 * @param args the command line arguments
	 */
	public Arguments(String args[]) {
		this.args = args;
	}

	/**
	 * Determines whether the given argument was included (flag arguments).
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return true if the flag argument exists, false if not
	 */
	public boolean exists(String shortArg, String longArg) {
		String value = find(shortArg, longArg);
		return value != null;
	}

	/**
	 * Gets the value of an argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return the argument's value or null if it has no value (example: "bar"
	 * is returned for the argument "--foo=bar")
	 */
	public String value(String shortArg, String longArg) {
		return value(shortArg, longArg, null);
	}

	/**
	 * Gets the value of an argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @return the argument's value or defaultValue if it has no value (example:
	 * "bar" is returned for the argument "--foo=bar")
	 */
	public String value(String shortArg, String longArg, String defaultValue) {
		String arg = find(shortArg, longArg);
		if (arg == null) {
			return defaultValue;
		}
		int equals = arg.indexOf('=');
		return (equals >= 0 && equals < arg.length() - 1) ? arg.substring(equals + 1).trim() : "";
	}

	/**
	 * Gets the value of an integer argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or null if it has no value (example: 2011 is
	 * returned for the argument "--year=2011")
	 */
	public Integer valueInt(String shortArg, String longArg) {
		return valueInt(shortArg, longArg, null);
	}

	/**
	 * Gets the value of an integer argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or defaultValue if it has no value (example:
	 * 2011 is returned for the argument "--year=2011")
	 */
	public Integer valueInt(String shortArg, String longArg, Integer defaultValue) {
		String value = value(shortArg, longArg);

		if (value == null || value.isEmpty()) {
			return defaultValue;
		}
		return Integer.valueOf(value);
	}

	/**
	 * Gets the value of a floating-point argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or null if it has no value (example: 12.5 is
	 * returned for the argument "--cash=12.50")
	 */
	public Double valueDouble(String shortArg, String longArg) {
		return valueDouble(shortArg, longArg, null);
	}

	/**
	 * Gets the value of a floating-point argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or defaultValue if it has no value (example:
	 * 12.5 is returned for the argument "--cash=12.50")
	 */
	public Double valueDouble(String shortArg, String longArg, Double defaultValue) {
		String value = value(shortArg, longArg);

		if (value == null || value.isEmpty()) {
			return defaultValue;
		}
		return Double.valueOf(value);
	}

	/**
	 * Finds the specified argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return the entire argument (example: "--foo=bar")
	 */
	private String find(String shortArg, String longArg) {
		//prepend the hypens
		if (shortArg != null) shortArg = "-" + shortArg;
		if (longArg != null) longArg = "--" + longArg;

		for (String arg : args) {
			if ((shortArg != null && arg.matches(shortArg + "(=.*)?")) || (longArg != null && arg.matches(longArg + "(=.*)?"))) {
				return arg;
			}
		}
		return null;
	}
}
