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
			graph.cells.setLabel(i, "");
		}
		graph.mazeGraph.addEdge(0, 2, 1);
		System.out.println(graph.mazeGraph.isEdge(2, 0));
		graph.mazeGraph.addEdge(2, 1, 1);
		graph.mazeGraph.addEdge(0, 1, 1);
		System.out.println(Arrays.toString(graph.mazeGraph.neighbors(0)));
		//System.out.println(graph.mazeGraph.toString());
		
		
		
		graph.mazeGraph.connectAllAdjacentVertices();
		//graph.cells.connectAllAdjacentVertices();
		
		
		
		//System.out.println(Arrays.toString(graph.mazeGraph.neighbors(19)));
	//	System.out.println(graph.topRight(0) + "top_right");
	//	System.out.println(graph.topLeft(0) + "top_left");
	//	System.out.println(graph.bottomLeft(0) + "bottom_left");
	//	System.out.println(graph.bottomRight(0) + "bottom_right");
		//graph.prims(0);
		int cell = 1;
		//System.out.println(graph.isBottom(graph.getBottom(cell), cell));
		//System.out.println(graph.isTop(graph.getTop(cell), cell));
		//System.out.println(graph.isRight(graph.getRight(cell), cell));
		//System.out.println(graph.isLeft(graph.getLeft(cell), cell));
		
	//	graph.breakLeastWall(8);
		for (int i = 0; i < graph.cells.size(); i++) {
		//	graph.breakAllWalls(i);
		}
		//graph.depthFirstRecurse(graph.cells, 0, graph.visited);
		//System.out.println(graph.leftValid(5));
		//System.out.println(graph.rightValid(5));
		//System.out.println(graph.topValid(5));
		//System.out.println(graph.bottomValid(5));
		//System.out.println(graph.isWall(0, graph.getRight(0)));
		graph.prims(0);
		graph.display();
		
	}
}