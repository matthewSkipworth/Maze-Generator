//Matthew Skipworth
//TCSS 342, Spring Quarter 2018

//Modified version of the graph implementation from "DATA STRUCTURES & Other Objects Using JAVA", Fourth Edition by MICHAEL MAIN

public class myGraph<E> implements Cloneable {
	
	private boolean [][] edges;
	private Object[] labels; //The vertex labels will be E objects.
	
	public myGraph(int nVertices) {
		if (nVertices < 0) {
			throw new NegativeArraySizeException("Please try a different number of vertices in your graph constructor.");
		}
		
		edges = new boolean[nVertices][nVertices];
		labels = (E[]) new Object[nVertices];
	}
	/*public myGraph(int width, int depth) {
		int nVertices = width * depth;
		
		edges = new boolean [nVertices][nVertices];
		labels = (E[]) new Object[nVertices];
		
	}*/
	
	int size() {
		return labels.length;
	}
	
	public void addEdge(int source, int target) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your addEdge method.");
		} else if (target < 0 || source > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your addEdge method.");
		} else {
			edges[source][target] = edges[target][source] = true;
		}
	}
	public boolean isEdge(int source, int target) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your isEdge method.");
		} else if (target < 0 || target > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your isEdge method.");
		} else {
			return (edges[source][target] || edges[target][source]);
		}
	}
	public void removeEdge(int source, int target) {
		if (source < 0 || source > this.size() - 1) {
			throw new IndexOutOfBoundsException("please try a different source in your removeEdge method.");
		} else if (target < 0 || source > this.size()) {
			throw new IndexOutOfBoundsException("please try a different target in your removeEdge method.");
		} else {
			edges[source][target] = edges[source][target] = false;
		}
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
				if (edges[vertex][i]) {
					count++;
				}
			}
			answer = new int[count];
			count = 0;
			for (int i = 0; i < labels.length; i++) {
				if (edges[vertex][i]) {
					answer[count++] = i;
				}
			}
		}
		return answer;
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
	
	public String toString() {
		String graphString = "";
		graphString += labels[0];
		for (int i = 1; i < this.size(); i++) {
			
			graphString += ", " + labels[i];
			
		}
		
		return graphString;
	}
	
}
