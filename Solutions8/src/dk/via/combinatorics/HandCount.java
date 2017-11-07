package dk.via.combinatorics;

import java.math.BigInteger;

public class HandCount {
	public static BigInteger combination(BigInteger[][] cache, int m, int n) {
		if (cache[m][n] != null) return cache[m][n];
		cache[m][n] = combination(cache, m, n - 1).add(combination(cache, m - 1, n - 1));
		return cache[m][n];
	}
	
	public static BigInteger dyn_combination(int m, int n) {
		BigInteger[][] cache = new BigInteger[m + 1][n + 1];
		for(int i = 0; i <= n; i++) {
			cache[0][i] = BigInteger.ONE;
		}
		for(int i = 0; i <= m; i++) {
			cache[i][i] = BigInteger.ONE;
		}
		BigInteger combination = combination(cache, m, n);
		return combination;
	}
	
	public static long combination(int m, int n) {
		if (m == n || m == 0) return 1;
		return combination(m, n - 1) + combination(m - 1, n - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(dyn_combination(13, 52 - 13).doubleValue() / dyn_combination(13, 52).doubleValue());
	}
}
