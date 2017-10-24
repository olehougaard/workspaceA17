package dk.via.queens.generic;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
	public static SolutionSpace findSolution(SolutionSpace p) {
		if (p.reject()) return null;
		if (p.accept()) return p;
		SolutionSpace ext = p.extend();
		while(ext.hasMore()) {
			SolutionSpace solution = findSolution(ext);
			if (solution != null) 
				return solution;
			ext.next();
		}
		return null;
	}
	
	private static void findAllSolutions(ArrayList<SolutionSpace> solutions, SolutionSpace p) {
		if (p.reject()) return;
		if (p.accept()) solutions.add(p.copy());
		SolutionSpace ext = p.extend();
		while(ext.hasMore()) {
			findAllSolutions(solutions, ext);
			ext.next();
		}
	}
	
	public static List<SolutionSpace> findAllSolutions(SolutionSpace p) {
		ArrayList<SolutionSpace> solutions = new ArrayList<>();
		findAllSolutions(solutions, p);
		return solutions;
	}
	
	public static void main(String[] args) {
		SolutionSpace p = new QueenPositions(13);
		System.out.println(findAllSolutions(p));
	}
}
