package dk.via.queens.generic;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
	public static SolutionSpace findSolution(SolutionSpace p) {
		if (p.reject()) return null;
		if (p.accept()) return p;
		SolutionSpace ext = p.extend();
		while(ext.hasNext()) {
			SolutionSpace solution = findSolution(ext);
			if (solution != null) 
				return solution;
			ext.next();
		}
		return null;
	}
	
	public static List<SolutionSpace> findAllSolutions(SolutionSpace p) {
		ArrayList<SolutionSpace> solutions = new ArrayList<>();
		if (p.reject()) return solutions;
		if (p.accept()) solutions.add(p.copy());
		SolutionSpace ext = p.extend();
		while(ext.hasNext()) {
			solutions.addAll(findAllSolutions(ext));
			ext.next();
		}
		return solutions;
	}
	
	public static void main(String[] args) {
		SolutionSpace p = new QueenPositions(8);
		System.out.println(findAllSolutions(p));
	}
}
