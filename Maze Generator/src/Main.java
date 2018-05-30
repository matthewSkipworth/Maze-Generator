import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		testMyGraph();
	}
	


	public static void testMyGraph() {
		Maze graph = new Maze(5,5, false);
		
		for (int i = 0; i < graph.mazeGraph.size(); i++) {
			//graph.mazeGraph.setLabel(i, (char) (65 + i));
			graph.mazeGraph.setLabel(i, "+");
			
		}
		for (int i = 0; i < graph.cells.size(); i++) {
			graph.cells.setLabel(i, i);
		}
		graph.mazeGraph.addEdge(0, 2, 1);
		System.out.println(graph.mazeGraph.isEdge(2, 0));
		graph.mazeGraph.addEdge(2, 1, 1);
		graph.mazeGraph.addEdge(0, 1, 1);
		System.out.println(Arrays.toString(graph.mazeGraph.neighbors(0)));
		//System.out.println(graph.mazeGraph.toString());
		
		
		
		graph.mazeGraph.connectAllAdjacentVertices();
		graph.cells.connectAllAdjacentVertices();
		
		
		
		System.out.println(Arrays.toString(graph.mazeGraph.neighbors(19)));
		//System.out.println();
		graph.prims(0);
		
		graph.breakLeastWall(9);
		//graph.removeBottom(9);
		System.out.println((graph.mazeGraph.isEdge(11,12)));
		System.out.println((graph.mazeGraph.isEdge(11,16)));
		System.out.println((graph.mazeGraph.isEdge(16,17)));
		System.out.println((graph.mazeGraph.isEdge(12,17)));
		graph.display();
	}
}