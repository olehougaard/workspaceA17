package dk.via.queens.generic;

public interface SolutionSpace {

	boolean reject();

	boolean accept();

	boolean hasNext();

	void next();

	SolutionSpace extend();

	SolutionSpace copy();

}