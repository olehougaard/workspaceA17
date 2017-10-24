package dk.via.moneychanging;

import java.util.Arrays;
import java.util.HashMap;

public class GreedyChanger {
	private int[] coins;
	
	public GreedyChanger(int[] coins) {
		this.coins = Arrays.copyOf(coins, coins.length);
		Arrays.sort(this.coins);
		if (this.coins[0] != 1) throw new IllegalArgumentException("Cannot pay all amounts without a 1-coin");
	}
	
	public HashMap<Integer, Integer> change(int amount) {
		int[] payOuts = new int[coins.length];
		int i = coins.length - 1;
		while(amount > 0) {
			if (amount >= coins[i]) {
				payOuts[i]++;
				amount -= coins[i];
			} else {
				i--;
			}
		}
		HashMap<Integer, Integer> result = new HashMap<>();
		for(int j = 0; j < coins.length; j++) {
			result.put(coins[j], payOuts[j]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		GreedyChanger DKK = new GreedyChanger(new int[]{ 20, 10, 5, 2, 1});
		System.out.println(DKK.change(51));
	}
}
