package dk.via.graphs;

import dk.via.graphs.algorithms.BreadthFirstTraversal;
import dk.via.graphs.algorithms.DepthFirstTraversal;
import dk.via.graphs.undirected.DenseGraph;

public class Exercise9_1 {
	public static void main(String[] args) {
		DenseGraph undirected = new DenseGraph(7);
		undirected.addEdge(0, 2);
		undirected.addEdge(1, 3);
		undirected.addEdge(4, 0);
		undirected.addEdge(5, 6);
		undirected.addEdge(6, 0);
		undirected.addEdge(1, 2);
		undirected.addEdge(5, 4);
		undirected.addEdge(5, 5);
		undirected.addEdge(5, 1);
		System.out.println(undirected);
		BreadthFirstTraversal bft = new BreadthFirstTraversal(undirected);
		System.out.println(bft.getTraversal());
		System.out.println(bft.getTree());
		DepthFirstTraversal dft = new DepthFirstTraversal(undirected);
		System.out.println(dft.getTraversal());
		System.out.println(dft.getTree());
	}
}
