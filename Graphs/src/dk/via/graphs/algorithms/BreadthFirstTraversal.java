package dk.via.graphs.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dk.via.graphs.Graph;
import dk.via.graphs.directed.SparseGraph;

public class BreadthFirstTraversal {
	private enum Mark { identified, visited };
	private LinkedList<Character> traversal;
	private Graph graph;
	private SparseGraph tree;
	
	public BreadthFirstTraversal(Graph graph) {
		this.graph = graph;
		this.tree = new SparseGraph(graph.getSize());
		this.traversal = new LinkedList<>();
		traverse();
	}
	
	private void traverse() {
		Queue<Integer> queue = new LinkedList<>();
		Mark[] marks = new Mark[graph.getSize()];
		queue.add(0);
		marks[0] = Mark.identified;
		while(!queue.isEmpty()) {
			int v = queue.remove();
			marks[v] = Mark.visited;
			traversal.add(graph.getName(v));
			for(int w: graph.getOutgoingNodes(v)) {
				if (marks[w] == null) {
					marks[w] = Mark.identified;
					tree.addEdge(v, w);
					queue.add(w);
				}
			}
		}
	}
	
	public List<Character> getTraversal() {
		return new LinkedList<>(traversal); 
	}
	
	public Graph getTree() {
		return tree;
	}
}
