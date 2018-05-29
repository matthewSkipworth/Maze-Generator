
public class Maze {
	
	int width;
	int depth;
	
	myGraph mazeGraph;
	
	//boolean debug;
	
	public Maze(int width, int depth, boolean debug) {
		this.width = width;
		this.depth = depth;
		
		mazeGraph = new myGraph(width * depth);
		
	}
	
	public void connectAllAdjacentVertices() {
		for (int i = 0; i < mazeGraph.size() - 1; i++) {
			if (i % width < width - 1) {
				mazeGraph.addEdge(i, i + 1); 
			}
			if (i < mazeGraph.size() - width) {
				mazeGraph.addEdge(i, i + width);
			}	
		}
		
		
	}
	
	void display() {		
		for (int i = 0; i < mazeGraph.size(); i++) {
			
			if (i % width == (width - 1)) {
				System.out.printf(" %5s \n", mazeGraph.getLabel(i));
				
				if ((i / width) < (depth - 1)) { //if not the last row
					for (int j = i + 1; j < width; j++) { //check to see if this row has any edges with adjacent vertices in the last row.
						if (mazeGraph.isEdge(j, j - width)) {
							System.out.printf("%5s", "|");
						}
					}
				}
				System.out.println(i);
				
			} else {
				System.out.printf("%5s ", mazeGraph.getLabel(i));
				if (mazeGraph.isEdge(i, i+1)) {
					System.out.print("-");
				}
			}
			
		}
		
		
		
	}
}
