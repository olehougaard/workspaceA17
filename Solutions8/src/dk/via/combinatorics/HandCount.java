package dk.via.combinatorics;

public class HandCount {
	public static long combination(long[][] cache, int m, int n) {
		if (cache[m][n] > 0) return cache[m][n];
		cache[m][n] = combination(cache, m, n - 1) + combination(cache, m - 1, n - 1);
		return cache[m][n];
	}
	
	public static long dyn_combination(int m, int n) {
		long[][] cache = new long[m + 1][n + 1];
		for(int i = 0; i <= n; i++) {
			cache[0][i] = 1;
		}
		for(int i = 0; i <= m; i++) {
			cache[i][i] = 1;
		}
		long combination = combination(cache, m, n);
		for(int i = 0; i <= m; i ++) {
			for(int j = 0; j <= n; j++) {
				System.out.print(cache[i][j] + "\t");
			}
			System.out.println();
		}
		return combination;
	}
	
	public static long combination(int m, int n) {
		if (m == n || m == 0) return 1;
		return combination(m, n - 1) + combination(m - 1, n - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(dyn_combination(13, 52));
	}
}
