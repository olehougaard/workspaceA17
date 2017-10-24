package dk.via.dynamic.coins;

import java.util.Arrays;

public class CoinChanger {
	private static int[] change(int amount, int[] coins) {
		Arrays.sort(coins);
		if (coins.length == 0 || coins[0] != 1) throw new IllegalArgumentException();
		int[] solutions = new int[amount + 1];
		int[] costs = new int[amount + 1];
		costs[0] = 0;		
		for(int i = 1; i <= amount; i++) {
			solutions[i] = coins[0];
			costs[i] = 1 + costs[i - coins[0]];
			for(int j = 1; j < coins.length; j++) {
				if (coins[j] <= i && costs[i - coins[j]] < costs[i]) {
					solutions[i] = coins[j];
					costs[i] = 1 + costs[i - coins[j]];
				}
			}
		}
		int[] result = new int[costs[amount]];
		int index = 0;
		while(amount > 0) {
			result[index++] = solutions[amount];
			amount -= solutions[amount];
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] change = change(1051, new int[]{ 33, 20, 11, 10, 5, 2, 1});
		for(int coin: change) System.out.println(coin);
	}
}
