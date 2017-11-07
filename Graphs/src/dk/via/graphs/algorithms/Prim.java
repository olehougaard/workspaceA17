package dk.via.graphs.algorithms;

import dk.via.graphs.Graph;
import dk.via.graphs.WeightedEdge;
import dk.via.graphs.undirected.SparseWeightedGraph;
import dk.via.trees.Heap;
import dk.via.trees.PriorityQueue;

public class Prim {
	private SparseWeightedGraph graph;
	private SparseWeightedGraph tree;
	
	public Prim(SparseWeightedGraph graph) {
		this.graph = graph;
		this.tree = new SparseWeightedGraph(graph.getSize());
		traverse();
	}
	
	private void traverse() {
		PriorityQueue<WeightedEdge> queue = new Heap<>();
		boolean[] marks = new boolean[graph.getSize()];
		marks[0] = true;
		for(WeightedEdge e: graph.getOutgoingEdges(0))
			queue.add(e);
		while(queue.size() != 0) {
			WeightedEdge min = queue.removeMin();
			int v = min.getTo();
			if (!marks[v]) {
				marks[v] = true;
				tree.addEdge(min);
				for(WeightedEdge e: graph.getOutgoingEdges(v)) 
					queue.add(e);
			}
		}
	}
	
	public Graph getTree() {
		return tree;
	}
}
