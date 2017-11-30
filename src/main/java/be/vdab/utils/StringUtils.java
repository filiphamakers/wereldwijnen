package be.vdab.utils;

import java.math.BigDecimal;

public class StringUtils {

	public static boolean isEmptyOrNull(String string) {
		return string == null || string.trim().isEmpty();
	}

	public static boolean isLong(String string) {
		if (isEmptyOrNull(string)) {
			return false;
		}
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isPositiveLong(String string) {
		if (isLong(string) && Long.parseLong(string) > 0) {
			return true;
		}
		return false;
	}

	public static boolean isBigDecimal(String string) {
		if (isEmptyOrNull(string)) {
			return false;
		}
		try {
			new BigDecimal(string);
			return true;
		} catch (NullPointerException | NumberFormatException ex) { 
			/*gezien isEmptyOrNull() test op null, mag NullPointerException uit het catch block*/
			return false;
		}
	}
}
