package dk.via.graphs.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dk.via.graphs.Graph;

public class TopologicalOrder {
	private enum Mark { visited, completed };
	private LinkedList<Character> traversal;
	private Graph graph;
	private Mark[] marks;
	
	public TopologicalOrder(Graph graph) {
		this.graph = graph;
		this.traversal = new LinkedList<>();
		this.marks = new Mark[graph.getSize()];
		traverse();
	}
	
	private Set<Integer> getRoots() {
		Set<Integer> roots = new TreeSet<>();
		for(int v = 0; v < graph.getSize(); v++)
			roots.add(v);
		for(int v = 0; v < graph.getSize(); v++) 
			for(int w: graph.getOutgoingNodes(v))
				roots.remove(w);
		return roots;
	}
	
	private void visit(int v) {
		marks[v] = Mark.visited;
		for(int w: graph.getOutgoingNodes(v)) {
			if (marks[w] == Mark.visited)
				throw new IllegalArgumentException("Cyclical graph");
			else if (marks[w] != Mark.completed)
				visit(w);
		}
		traversal.add(0, graph.getName(v));
		marks[v] = Mark.completed;
	}
	
	public void traverse() {
		Set<Integer> roots = getRoots();
		if (roots.isEmpty()) throw new IllegalArgumentException("Cyclical graph");
		for(int v: roots)
			visit(v);
		
	}
	
	public List<Character> getTraversal() {
		return new LinkedList<>(traversal); 
	}
}
