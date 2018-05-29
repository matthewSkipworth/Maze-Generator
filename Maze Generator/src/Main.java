import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		testMyGraph();
	}
	


	public static void testMyGraph() {
		Maze graph = new Maze(5,5, false);
		
		for (int i = 0; i < graph.mazeGraph.size(); i++) {
			//graph.mazeGraph.setLabel(i, (char) (65 + i));
			graph.mazeGraph.setLabel(i, "V" + i);
			
		}
		graph.mazeGraph.addEdge(0, 2);
		System.out.println(graph.mazeGraph.isEdge(2, 0));
		graph.mazeGraph.addEdge(2, 1);
		graph.mazeGraph.addEdge(0, 1);
		System.out.println(Arrays.toString(graph.mazeGraph.neighbors(0)));
		System.out.println(graph.mazeGraph.toString());
		
		
		
		graph.connectAllAdjacentVertices();
		
		
		System.out.println(Arrays.toString(graph.mazeGraph.neighbors(19)));
		
		graph.display();
	}
}