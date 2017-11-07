package dk.via.graphs.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import dk.via.graphs.Graph;
import dk.via.graphs.UnweightedEdge;
import dk.via.graphs.directed.SparseGraph;

public class DepthFirstTraversal {
	private enum Mark { identified, visited };
	private LinkedList<Character> traversal;
	private Graph graph;
	private SparseGraph tree;
	
	public DepthFirstTraversal(Graph graph) {
		this.graph = graph;
		this.tree = new SparseGraph(graph.getSize());
		this.traversal = new LinkedList<>();
		traverse();
	}
	
	private void traverse() {
		Stack<UnweightedEdge> edgeStack = new Stack<>();
		Mark[] marks = new Mark[graph.getSize()];
		for(int w: graph.getOutgoingNodes(0)) {
			edgeStack.push(new UnweightedEdge(0, w));
		}
		traversal.add(graph.getName(0));
		marks[0] = Mark.visited;
		while(!edgeStack.isEmpty()) {
			UnweightedEdge e = edgeStack.pop();
			int parent = e.getFrom();
			int v = e.getTo();
			if (marks[v] != Mark.visited) {
				tree.addEdge(parent, v);
				marks[v] = Mark.visited;
				traversal.add(graph.getName(v));
				for(int w: graph.getOutgoingNodes(v))
					if (marks[w] != Mark.visited) 
						edgeStack.push(new UnweightedEdge(v, w));
			}
		}
	}
	
	public List<Character> getTraversal() {
		return new LinkedList<>(traversal); 
	}

	public SparseGraph getTree() {
		return tree;
	}
}
