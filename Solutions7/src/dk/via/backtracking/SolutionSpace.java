package dk.via.backtracking;


public interface SolutionSpace<T extends SolutionSpace<T>> {

	boolean reject();

	boolean accept();

	boolean hasMore();

	void next();

	T extend();

	T copy();

}