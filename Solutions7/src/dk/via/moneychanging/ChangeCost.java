package dk.via.moneychanging;

import java.util.Arrays;
import java.util.HashMap;

import dk.via.backtracking.CostSolutionSpace;


public class ChangeCost implements CostSolutionSpace<ChangeCost> {
	private int[] coins;
	private int[] payOuts;
	private int amount;
	private int index;


	public ChangeCost(int[] coins, int amount) {
		this.coins = Arrays.copyOf(coins, coins.length);
		Arrays.sort(this.coins);
		this.payOuts = new int[coins.length];
		this.amount = amount;
		this.index = -1;
	}

	private ChangeCost(int[] coins, int[] payOuts, int amount) {
		this.coins = coins;
		this.payOuts = Arrays.copyOf(payOuts, payOuts.length);
		this.amount = amount;
		this.index = coins.length - 1;
	}

	@Override
	public boolean reject() {
		return amount < 0;
	}

	@Override
	public boolean accept() {
		return amount == 0;
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
	public ChangeCost extend() {
		ChangeCost ext = new ChangeCost(coins, payOuts, amount);
		ext.amount -= coins[ext.index];
		ext.payOuts[ext.index]++;
		return ext;
	}

	@Override
	public ChangeCost copy() {
		return new ChangeCost(coins, payOuts, amount);
	}
	
	@Override
	public double cost() {
		int cost = 0;
		for(int payOut: payOuts) {
			cost += payOut;
		}
		return cost;
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
