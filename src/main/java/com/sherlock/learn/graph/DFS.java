package com.sherlock.learn.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RouteBetweenNodes {

	public static void main(String[] args) {
		Graph graph = createNewGraph();
		List<Node> vertices = graph.getVertices();
		Node start = vertices.get(0);
		Node end = vertices.get(6);
		System.out.println(search(graph, start, end));
	}

	private static boolean search(Graph graph, Node start, Node end) {
		graph.getVertices()
				.stream()
				.forEach(vertex ->vertex.setState(State.UNVISITED));

		LinkedList<Node> currentlyVisitingQueue = new LinkedList<>();
		start.setState(State.VISITING);
		currentlyVisitingQueue.add(start);

		while(!currentlyVisitingQueue.isEmpty()) {

			Node u = currentlyVisitingQueue.removeFirst();
			System.out.println(u.getVertex());

			if(u == null)
				return false;

			for (Node adjacentVertex : u.getAdjacentNodes()){
				if(adjacentVertex.state==State.UNVISITED){
					if(adjacentVertex == end){
						return true;
					}
					else {
						adjacentVertex.setState(State.VISITING);
						currentlyVisitingQueue.add(adjacentVertex);
					}
				}
			}
			u.setState(State.VISITED);
		}
		return false;
	}

	private static Graph createNewGraph() {
		Graph graph = new Graph();
		Node n0 = new Node("a");
		Node n1 = new Node("b");
		Node n2 = new Node("c");
		Node n3 = new Node("d");
		Node n4 = new Node("e");
		Node n5 = new Node("f");
		Node n6 = new Node("g");

		n0.addAdjacent(n1);
		n0.addAdjacent(n2);
		n0.addAdjacent(n3);
		n3.addAdjacent(n4);
		n3.addAdjacent(n6);
		n4.addAdjacent(n5);


		graph.setVertices(Arrays.asList(n0,n1,n2,n3,n4,n5,n6));
		return graph;
	}
}
