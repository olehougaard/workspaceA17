
public class BigO {
	public static long bigMult(long n, long m) {
		long r = 0;
		while(m > 0) {
			if (m % 2 == 0) {
				n = n + n;
				m = m / 2;
			} else {
				r = r + n;
				m = m - 1;
			}
		}
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(bigMult(1023, 133) == 1023L*133L);
	}
}
