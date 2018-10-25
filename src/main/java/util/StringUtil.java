package util;

public class StringUtil {

	public static boolean isNotBlank (String str) {
		
		if (str != null && str.length() > 0 && str.trim().length() > 0) {
			return true;
		}
		
		return false;
	}
}
