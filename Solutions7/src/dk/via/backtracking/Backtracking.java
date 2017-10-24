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
}
