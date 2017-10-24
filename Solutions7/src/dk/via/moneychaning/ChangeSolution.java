package dk.via.moneychaning;

import java.util.Arrays;
import java.util.HashMap;

import dk.via.backtracking.SolutionSpace;

public class ChangeSolution implements SolutionSpace<ChangeSolution> {
	int best;
	private int[] coins;
	private int[] payOuts;
	private int amount;
	private int index;
	private ChangeSolution root;


	public ChangeSolution(int[] coins, int amount) {
		this.coins = Arrays.copyOf(coins, coins.length);
		Arrays.sort(this.coins);
		this.payOuts = new int[coins.length];
		this.amount = amount;
		this.best = amount + 1;
		this.index = -1;
		this.root = this;
	}

	private ChangeSolution(int[] coins, int[] payOuts, int amount, ChangeSolution root) {
		this.coins = coins;
		this.root = root;
		this.payOuts = Arrays.copyOf(payOuts, payOuts.length);
		this.amount = amount;
		this.index = coins.length - 1;
	}

	@Override
	public boolean reject() {
		return amount < 0 || size() >= root.best;
	}

	@Override
	public boolean accept() {
		if (amount == 0) {
			if (size() < root.best) {
				root.best = size();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasMore() {
		return index >= 0;
	}

	@Override
	public void next() {
		amount += coins[index];
		payOuts[index]--;
		index--;
		if (index >= 0) {
			amount -= coins[index];
			payOuts[index]++;
		}
	}

	@Override
	public ChangeSolution extend() {
		ChangeSolution ext = new ChangeSolution(coins, payOuts, amount, root);
		ext.amount -= coins[ext.index];
		ext.payOuts[ext.index]++;
		return ext;
	}

	@Override
	public ChangeSolution copy() {
		return new ChangeSolution(coins, payOuts, amount, root);
	}
	
	public int size() {
		int size = 0;
		for(int n: payOuts)
			size += n;
		return size;
	}
	
	public HashMap<Integer, Integer> change() {
		HashMap<Integer, Integer> result = new HashMap<>();
		for(int i = 0; i < coins.length; i++) {
			result.put(coins[i], payOuts[i]);
		}
		return result;
	}
}
