package dk.via.queens.generic;

public interface SolutionSpace {

	boolean reject();

	boolean accept();

	boolean hasMore();

	void next();

	SolutionSpace extend();

	SolutionSpace copy();

}