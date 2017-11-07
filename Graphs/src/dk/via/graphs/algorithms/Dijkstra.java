package dk.via.graphs.algorithms;

import dk.via.graphs.Graph;
import dk.via.graphs.WeightedEdge;
import dk.via.graphs.undirected.SparseWeightedGraph;
import dk.via.trees.Heap;
import dk.via.trees.PriorityQueue;

public class Dijkstra {
	private SparseWeightedGraph graph;
	private dk.via.graphs.directed.SparseWeightedGraph tree;
	
	public Dijkstra(SparseWeightedGraph graph) {
		this.graph = graph;
		this.tree = new dk.via.graphs.directed.SparseWeightedGraph(graph.getSize());
		traverse();
	}
	
	private static class QueueElement implements Comparable<QueueElement> {
		private WeightedEdge edge;
		private double distance;

		public QueueElement(WeightedEdge e, double distance) {
			this.edge = e;
			this.distance = distance;
		}

		public WeightedEdge getEdge() {
			return edge;
		}

		public double getDistance() {
			return distance;
		}

		@Override
		public int compareTo(QueueElement o) {
			return Double.compare(distance, o.distance);
		}
	}
	
	private void traverse() {
		PriorityQueue<QueueElement> queue = new Heap<>();
		boolean[] marks = new boolean[graph.getSize()];
		marks[0] = true;
		for(WeightedEdge e: graph.getOutgoingEdges(0))
			queue.add(new QueueElement(e, e.getWeight()));
		while(queue.size() != 0) {
			QueueElement min = queue.removeMin();
			int v = min.getEdge().getTo();
			if (!marks[v]) {
				marks[v] = true;
				tree.addEdge(min.getEdge());
				for(WeightedEdge e: graph.getOutgoingEdges(v)) 
					queue.add(new QueueElement(e, min.getDistance() + e.getWeight()));
			}
		}
	}
	
	public Graph getTree() {
		return tree;
	}
}
