import java.util.Arrays;
import java.util.Random;

//Matthew Skipworth
//TCSS 342, Spring Quarter 2018

//Modified version of the graph implementation from "DATA STRUCTURES & Other Objects Using JAVA", Fourth Edition by MICHAEL MAIN

public class myGraph<E> implements Cloneable {
	
	private int [][] edges;
	//private int[][]
	private Object[] labels; //The vertex labels will be E objects.
	
	private int depth;
	private int width;
	
	public myGraph(int nVertices, int deep, int wide) {
		if (nVertices < 0) {
			throw new NegativeArraySizeException("Please try a different number of vertices in your graph constructor.");
		}
		
		edges = new int[nVertices][nVertices];
		labels = (E[]) new Object[nVertices];
		depth = deep;
		width = wide;
	}
	/*public myGraph(int width, int depth) {
		int nVertices = width * depth;
		
		edges = new boolean [nVertices][nVertices];
		labels = (E[]) new Object[nVertices];
		
	}*/
	
	int size() {
		return labels.length;
	}
	int width() {
		return this.width;
	}
	int depth() {
		return this.depth;
	}
	public void addEdge(int source, int target, int weight) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your addEdge method.");
		} else if (target < 0 || source > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your addEdge method.");
		} else {
			edges[source][target] = edges[target][source] = weight;
		}
	}
	public boolean isEdge(int source, int target) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your isEdge method.");
		} else if (target < 0 || target > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your isEdge method.");
		} else {
			return (edges[source][target] > 0 && edges[target][source] > 0);
		}
	}
	public void removeEdge(int source, int target) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your removeEdge method.");
		} else if (target < 0 || source > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your removeEdge method.");
		} else {
			edges[source][target] = edges[source][target] = 0;
			
		}
	}
	public int getEdge(int source, int target) {
		return edges[source][target];
	}
	@SuppressWarnings("unchecked")
	public E getLabel(int vertex) {
		return (E) labels[vertex];
	}
	
	public int[] neighbors(int vertex) {
		int count = 0;
		int[] answer;
		
		if (vertex < 0 || vertex > size() - 1) {
			throw new IndexOutOfBoundsException("please try a different vertex number in your neighbors method.");
		} else {
			for (int i = 0; i < labels.length; i++) {
				if (edges[vertex][i]>0) {
					count++;
				}
			}
			answer = new int[count];
			count = 0;
			for (int i = 0; i < labels.length; i++) {
				if (edges[vertex][i]>0) {
					answer[count++] = i;
				}
			}
		}
		return answer;
	}
	public int minEdgeNeighbor(int vertex) {
		int[] nabes = new int[this.neighbors(vertex).length]; 
		for (int i = 0; i < this.neighbors(vertex).length; i++) {
			nabes[i] = this.neighbors(vertex)[i];
		}
		int minNeighbor = Integer.MAX_VALUE;
		for (int i = 0; i < nabes.length; i++) {
			if (getEdge(vertex, nabes[i]) < minNeighbor) {
				minNeighbor = nabes[i];//getEdge(vertex, nabes[i]);
			}
		}
		System.out.println(minNeighbor + " minNeighbor");
		return minNeighbor;
	}
	
	public void setLabel(int vertex, E newLabel) {
		labels[vertex] = newLabel;
	}
	
	public myGraph<E> clone() {
		myGraph<E> answer;
		
		try {
			answer = (myGraph<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement Cloneable");
		}
		answer.edges = edges.clone();
		answer.labels = labels.clone();
		
		return answer;
	}
	public void connectAllAdjacentVertices() {
		Random randomInt = new Random();
		for (int i = 0; i < size() - 1; i++) {
			
			if (i % width < (width - 1)) {
				addEdge(i, i + 1, (randomInt.nextInt(size())) + 1); //sets horizontal edge weights. We add 1 to ensure an edge isn't set to 0.
			}
			if (i < size() - width) {
				addEdge(i, i + width, (randomInt.nextInt(size())) + 1); //sets vertical edge weights.
			}	
		}
	}
	public String toString() {
		String graphString = "";
		graphString += labels[0];
		for (int i = 1; i < this.size(); i++) {
			
			graphString += ", " + labels[i];
			
		}
		
		return graphString;
	}
	
}
