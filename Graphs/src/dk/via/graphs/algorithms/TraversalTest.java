package dk.via.graphs.algorithms;

public class TraversalTest {
	public static void main(String[] args) {
		dk.via.graphs.undirected.SparseGraph undirected = new dk.via.graphs.undirected.SparseGraph(11);
		undirected.addEdge(0, 1);
		undirected.addEdge(0, 2);
		undirected.addEdge(0, 4);
		undirected.addEdge(1, 3);
		undirected.addEdge(2, 7);
		undirected.addEdge(3, 5);
		undirected.addEdge(4, 8);
		undirected.addEdge(5, 9);
		undirected.addEdge(7, 8);
		undirected.addEdge(7, 9);
		undirected.addEdge(8, 10);
		undirected.addEdge(9, 10);
		BreadthFirstTraversal breadthFirstTraversal = new BreadthFirstTraversal(undirected);
		System.out.println(breadthFirstTraversal.getTraversal());
		System.out.println(breadthFirstTraversal.getTree());
		
		undirected = new dk.via.graphs.undirected.SparseGraph(6);
		undirected.addEdge(0, 1);
		undirected.addEdge(0, 2);
		undirected.addEdge(0, 5);
		undirected.addEdge(1, 3);
		undirected.addEdge(2, 3);
		undirected.addEdge(2, 4);
		undirected.addEdge(3, 4);
		undirected.addEdge(4, 5);
		DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal(undirected);
		System.out.println(depthFirstTraversal.getTraversal());
		System.out.println(depthFirstTraversal.getTree());
		
		dk.via.graphs.directed.SparseGraph directed = new dk.via.graphs.directed.SparseGraph(5);
		directed.addEdge(0, 1);
		directed.addEdge(0, 2);
		directed.addEdge(1, 3);
		directed.addEdge(3, 2);
		directed.addEdge(3, 4);
		TopologicalOrder topologicalOrder = new TopologicalOrder(directed);
		System.out.println(topologicalOrder.getTraversal());
		
		dk.via.graphs.undirected.SparseWeightedGraph swg = new dk.via.graphs.undirected.SparseWeightedGraph(8);
		swg.addEdge(0, 1, 3);
		swg.addEdge(0, 2, 4);
		swg.addEdge(0, 3, 2);
		swg.addEdge(1, 3, 1);
		swg.addEdge(1, 4, 5);
		swg.addEdge(1, 7, 6);
		swg.addEdge(2, 3, 5);
		swg.addEdge(2, 5, 6);
		swg.addEdge(3, 4, 2);
		swg.addEdge(3, 5, 2);
		swg.addEdge(3, 6, 3);
		swg.addEdge(4, 6, 4);
		swg.addEdge(4, 7, 4);
		swg.addEdge(5, 6, 2);
		swg.addEdge(6, 7, 9);
		Prim prim = new Prim(swg);
		System.out.println(prim.getTree());
		
		swg = new dk.via.graphs.undirected.SparseWeightedGraph(11);
		swg.addEdge(0, 1, 6);
		swg.addEdge(0, 2, 2);
		swg.addEdge(0, 3, 4);
		swg.addEdge(1, 4, 3);
		swg.addEdge(1, 5, 1);
		swg.addEdge(2, 5, 7);
		swg.addEdge(3, 5, 5);
		swg.addEdge(3, 6, 6);
		swg.addEdge(4, 5, 3);
		swg.addEdge(4, 7, 6);
		swg.addEdge(5, 6, 2);
		swg.addEdge(5, 8, 9);
		swg.addEdge(6, 9, 3);
		swg.addEdge(7, 8, 5);
		swg.addEdge(7, 10, 4);
		swg.addEdge(8, 9, 1);
		swg.addEdge(8, 10, 2);
		swg.addEdge(9, 10, 4);
		Dijkstra dijkstra = new Dijkstra(swg);
		System.out.println(dijkstra.getTree());
	}
}
