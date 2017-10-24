package dk.via.moneychaning;

import java.util.HashMap;
import java.util.List;

import dk.via.backtracking.Backtracking;

public class BacktrackChanger {
	private int[] coins;

	public BacktrackChanger(int[] coins) {
		this.coins = coins;
	}

	public HashMap<Integer, Integer> change(int amount) {
		ChangeSolution root = new ChangeSolution(coins, amount);
		List<ChangeSolution> allSolutions = Backtracking.findAllSolutions(root);
		int best = amount + 1;
		ChangeSolution bestSolution = null;
		for(ChangeSolution solution: allSolutions){
			if (solution.size() < best) {
				best = solution.size();
				bestSolution = solution;
			}
		}
		return bestSolution == null ? null : bestSolution.change();
	}
	
	public static void main(String[] args) {
		BacktrackChanger DKK = new BacktrackChanger(new int[]{ 33, 20, 11, 10, 5, 2, 1});
		System.out.println(DKK.change(1051));
	}
}
