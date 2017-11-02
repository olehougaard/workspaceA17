package dk.via.dynamic.coins;

public class Fibonacci {
	private static int dynamic_fibonacci(int[] cache, int n) {
		if (cache[n] != 0) return cache[n];
		cache[n] = dynamic_fibonacci(cache, n- 1) + dynamic_fibonacci(cache, n-2);
		return cache[n];
	}
	
	public static int fibonacci(int n) {
		int[] cache = new int[n+1];
		cache[1] = cache[2] = 1;
		return dynamic_fibonacci(cache, n);
	}
	
	public static int bottom_up_fibonacci(int n) {
		int[] cache = new int[n + 1];
		cache[1] = cache[2] = 1;
		for(int i = 3; i <= n; i++) {
			cache[i] = cache[i-1] + cache[i-2];
		}
		return cache[n];
	}
	
	public static void main(String[] args) {
		System.out.println(fibonacci(50));
	}
}
