package dk.via.backtracking;


import java.util.ArrayList;
import java.util.List;

public class Backtracking {
	public static <T extends SolutionSpace<T>> T findSolution(T p) {
		if (p.reject()) return null;
		if (p.accept()) return p;
		T ext = p.extend();
		while(ext.hasMore()) {
			T solution = findSolution(ext);
			if (solution != null) 
				return solution;
			ext.next();
		}
		return null;
	}
	
	private static <T extends SolutionSpace<T>> void findAllSolutions(ArrayList<T> solutions, T p) {
		if (p.reject()) return;
		if (p.accept()) solutions.add(p.copy());
		T ext = p.extend();
		while(ext.hasMore()) {
			findAllSolutions(solutions, ext);
			ext.next();
		}
	}
	
	public static <T extends SolutionSpace<T>> List<T> findAllSolutions(T p) {
		ArrayList<T> solutions = new ArrayList<>();
		findAllSolutions(solutions, p);
		return solutions;
	}
	private static <T extends CostSolutionSpace<T>> double cost(T p) {
		return (p == null) ? Double.POSITIVE_INFINITY : p.cost();
	}
	
	private static <T extends CostSolutionSpace<T>> void findBestSolution(T[] best, T p) {
		if (p.reject() || p.cost() >= cost(best[0])) return;
		if (p.accept() && p.cost() < cost(best[0])) best[0] = p.copy();
		T ext = p.extend();
		while(ext.hasMore()) {
			findBestSolution(best, ext);
			ext.next();
		}
	}

	public static <T extends CostSolutionSpace<T>> T findBestSolution(T p) {
		T[] best = (T[]) new CostSolutionSpace<?>[1];
		findBestSolution(best, p);
		return best[0];
	}
}
