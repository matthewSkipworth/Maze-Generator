import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class Maze {
	
	int width;
	int depth;
	
	myGraph mazeGraph;
	myGraph cells;
	
	boolean debug;
	
	public Maze(int wide, int deep, boolean debug) {
		this.width = wide;
		this.depth = deep;
		
		
		cells = new myGraph(((wide-1)*(deep-1)), (deep-1), (wide-1)); 
		mazeGraph = new myGraph(wide * deep, deep, wide);
		
	}
	
	
	
	void prims(int start) {
		PriorityQueue<Integer> walls = new PriorityQueue<Integer>();
		//PrioriyQueue<Integer>
		boolean [] visited = new boolean[cells.size()];
		visited[start] = true;
		//Integer[] currentWalls = {mazeGraph.getEdge(start,start+1),  mazeGraph.getEdge(start, start + mazeGraph.width()),  mazeGraph.getEdge(start+1, start+1+mazeGraph.width()),  mazeGraph.getEdge(start+mazeGraph.width(), start+1+mazeGraph.width()) };
		for (int i = 0; i < cells.neighbors(start).length; i++) {
			walls.add(cells.neighbors(start)[i]);
		}
		//PriorityQueue<Integer> sortedWalls = new PriorityQueue<Integer>(currentWalls.length);
		//sortedWalls.addAll(Arrays.asList(currentWalls));
		
		//walls.addAll(sortedWalls);
		
		while (Arrays.asList(visited).contains(false)) {
			breakLeastWall(start);
			start = cells.minEdgeNeighbor(start);
			for (int i = 0; i < cells.neighbors(start).length; i++) {
				if (! walls.contains(cells.neighbors(start)[i]))
				walls.add(cells.neighbors(start)[i]);
			}
			visited[start] = true;
			
		}
		System.out.println("prims done");
		
		
		
		
	}
	
	void breakLeastWall(int cell) {
		//int other = cell;

		int path = cells.minEdgeNeighbor(cell);
		//System.out.println(path + "path");
		if (path == (cell - cells.width())) {
			removeTop(cell);
		} else if (path == (cell + cells.width())) {
			removeBottom(cell);
		} else if (path == cell + 1) {
			removeRight(cell);
		} else if (path == cell - 1){
			removeLeft(cell);
		}
		System.out.println(cell + " cell");
		
	}
	
	void removeRight(int cell) {
		if (cell > cells.width() && cell < cells.width() * 2) {
			cell +=1;
		} else if (cell > (cells.width() * 2) && cell < (cells.width() * 3)) {
			cell +=2;
		} else if (cell > (cells.width() * 3) && cell < (cells.size())) {
			cell +=3;
		} 
		System.out.println(cell + "cell right");
		mazeGraph.removeEdge(cell + 1, cell + 1 + mazeGraph.width());
	}
	void removeLeft(int cell) {
		if (cell > cells.width() && cell < cells.width() * 2) {
			cell +=1;
		} else if (cell > (cells.width() * 2) && cell < (cells.width() * 3)) {
			cell +=2;
		} else if (cell > (cells.width() * 3) && cell < (cells.size())) {
			cell +=3;
		} 
		System.out.println(cell + "cell left");
		mazeGraph.removeEdge(cell, cell + mazeGraph.width());
	}
	void removeTop(int cell) {
		if (cell > cells.width() && cell < cells.width() * 2) {
			cell +=1;
		} else if (cell > (cells.width() * 2) && cell < (cells.width() * 3)) {
			cell +=2;
		} else if (cell > (cells.width() * 3) && cell < (cells.size())) {
			cell +=3;
		} 
		System.out.println(cell + "cell top");
		mazeGraph.removeEdge(cell, cell + 1);
	}
	
	void removeBottom(int cell) {
		if (cell > cells.width() && cell < cells.width() * 2) {
			cell +=1;
		} else if (cell > (cells.width() * 2) && cell < (cells.width() * 3)) {
			cell +=2;
		} else if (cell > (cells.width() * 3) && cell < (cells.size())) {
			cell +=3;
		} 
		System.out.println(cell + "cell bottom");
		mazeGraph.removeEdge(cell + mazeGraph.width(), cell + 1 + mazeGraph.width());
	}
	
	void display() {		
		int count = 0;
		int rowCount = 1;
		for (int i = 0; i < mazeGraph.size(); i++) {
			if (i < width) {
				System.out.printf("%3s", mazeGraph.getLabel(i));
				if (mazeGraph.isEdge(i, i+1)) {
					//System.out.print(mazeGraph.getEdge(i,i+1));
					System.out.printf("%3s", "-");
				}
				if(i % width == (width - 1)) {
					System.out.println();
					if (i < (mazeGraph.size() - width)) {
						for(int j = 1-width; j < 1; j++) {//the start of the last row to the end of the last row. (0-4 for the first row)
						
						
							//System.out.print(i);
							if (mazeGraph.isEdge((i+j), (i+j+width))) {	
								System.out.printf("%3s","|");
							}	
							if (count < cells.width()) {
								System.out.printf("%3s",cells.getLabel(j+i));
								count++;
							}
						}
					}
				}
				
			} else {
				if (i % width == 0) {
					
					rowCount++;
					System.out.println();
					System.out.printf("%3s", mazeGraph.getLabel(i));
					
					
				} else {
					if (mazeGraph.isEdge(i, i-1)) {
						System.out.printf("%3s","-");
					}
					System.out.printf("%3s", mazeGraph.getLabel(i));
					
					if(i % width == (width - 1)) {
						System.out.println();
						if (i < (mazeGraph.size() - width)) {
							count = -4;
							for(int j = -width; j < 0; j++) {//the start of the last row to the end of the last row.
								//System.out.print(i);
								if (mazeGraph.isEdge((i+j), (i+j+width))) {
									System.out.printf("%3s","|");
								}
								if (count < 0) {
									//System.out.print(i);
									System.out.printf("%3s", cells.getLabel((i + 1)-rowCount+count));
									count++;
									
								}
							}
						}
					}
				}
			}
		}	
	}
}
