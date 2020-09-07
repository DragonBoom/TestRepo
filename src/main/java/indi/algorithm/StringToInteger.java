package indi.algorithm;

public class StringToInteger {

	/**
	 * 循规蹈矩的解法
	 * 
	 * @param str
	 * @return
	 */
	int solution1(String str) {
		int i = 0;
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if (i < str.length()) {
			boolean negative = false;
			if (str.charAt(i) == '-') {
				i++;
				negative = true;
			} else if (str.charAt(i) == '+') {
				i++;
			}
			int result = 0;
			int prev = 0; 
			while (i < str.length()) {
				if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
					prev = result;
					result = result * 10 + (str.charAt(i) - '0');
					if (result / 10 != prev) {// why ? a * 10 != a / 10 => a > Integer.MAX_VALUE ??
						return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
					}
					i++;
				} else {
					break;
				}
			}
			return negative ? -result : result;
		}
		return 0;
	}
}
