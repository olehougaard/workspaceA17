package dk.via.llcs;

public class LLCS {
	private static int llcs(int[][] cache, String s1, String s2, int i1, int i2) {
		if (cache[i1][i2] >= 0) return cache[i1][i2];
		if (s1.charAt(i1) == s2.charAt(i2)) {
			cache[i1][i2] = 1 + llcs(cache, s1, s2, i1 + 1, i2 + 1);
		} else {
			int l1 = llcs(cache, s1, s2, i1 + 1, i2);
			int l2 = llcs(cache, s1, s2, i1, i2 + 1);
			if (l1 >= l2)
				cache[i1][i2] = l1;
			else
				cache[i1][i2] = l2;
		}
		return cache[i1][i2];
	}

	public static int llcs(String s1, String s2, int i1, int i2) {
		if (i1 == s1.length() || i2 == s2.length()) return 0;
		if (s1.charAt(i1) == s2.charAt(i2)) {
			return 1 + llcs(s1, s2, i1 + 1, i2 + 1);
		} else {
			int l1 = llcs(s1, s2, i1 + 1, i2);
			int l2 = llcs(s1, s2, i1, i2 + 1);
			if (l1 >= l2)
				return l1;
			else
				return l2;
		}
	}

	public static int llcs(String s1, String s2) {
		int[][] cache = new int[s1.length() + 1][s2.length() + 2];
		for(int i1 = 0; i1 < cache.length; i1++) {
			for (int i2 = 0; i2 < cache[i1].length; i2++) {
				if (i1 == s1.length() || i2 == s2.length()) {
					cache[i1][i2] = 0;
				} else {
					cache[i1][i2] = -1;
				}
			}
		}
		return llcs(cache, s1, s2, 0, 0);
	}
	
	public static void main(String[] args) {
		System.out.println(llcs("Length of Longest Common Subsequence", "Green Dreams Sleep Furiously"));
	}
}
