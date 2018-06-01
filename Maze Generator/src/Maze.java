import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class Maze {
	
	int width;
	int depth;
	
	static myGraph mazeGraph;
	static myGraph cells;
	
	//static Stack<Integer> maze;
	
	boolean[] visited;
	
	boolean debug;
	
	public Maze(int wide, int deep, boolean debug) {
		this.width = wide;
		this.depth = deep;
		
		
		cells = new myGraph(((wide-1)*(deep-1)), (deep-1), (wide-1)); 
		mazeGraph = new myGraph(wide * deep, deep, wide);
		
		visited = new boolean[cells.size()];
		
		//maze = prims(0);
		 
	}
	
	
	Stack<Integer> prims(int cell) {
		Stack<Integer> maze = new Stack<Integer>();
		//ArrayList<Integer> mazeList = new ArrayList<Integer>();
		Stack<Integer> hold = new Stack<Integer>();
		
		int methodCycle = 0;
		int smallWall;
		PriorityQueue<Integer> walls = new PriorityQueue();
		boolean containsFalse = false;
		visited[cell] = true;
		//System.out.println()
		
		maze.push(cell);
		int[] cellWalls = {getLeft(cell), getTop(cell), getRight(cell), getBottom(cell)};
		walls.offer(getTop(cell));
		walls.offer(getBottom(cell));
		walls.offer(getLeft(cell));
		walls.offer(getRight(cell));
		
		while (!walls.isEmpty() ) {
			System.out.println(maze.toString());
			//System.out.println("current cell"+ cell);
			System.out.println("cell walls "+getRight(cell) +", "+ getLeft(cell) +", " + getTop(cell) +", "+ getBottom(cell));
		
			
			containsFalse = false;
			for (boolean el:visited) {
				if (el == false) {
					containsFalse = true;
				}
			}
			//System.out.println(maze.toString());
			//System.out.println(maze.peek());
			System.out.println("walls: " + walls.toString());
			System.out.println("current cell: " + cell + "\n");
			
			smallWall = walls.peek();
			//System.out.println(isWall(cell, smallWall));
			
		if (maze.size() > 1 && !isWall(cell, smallWall)) {
			
			
			System.out.println("stack popped!");
			hold.push(maze.pop());
			cell = maze.peek();
			System.out.println(cell);
			if (cell==0) {
				while (((cell == 0) || !isWall(cell, smallWall)) && !hold.isEmpty())  {
				
				maze.push(hold.pop());
				cell = maze.peek();
				}
			}
			
		} else if (isTop(smallWall, cell)) {
				if (!topValid(cell)) {
					System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					System.out.println("up");
					removeTop(cell);
					System.out.println(walls.poll() + " polled");
					int nextCell = cell-cells.width();
					maze.push(nextCell);
					visited[nextCell]=true;
					if (getTop(nextCell) > 0 ) {
						walls.offer(getTop(nextCell) );	
					}
					if (getRight(nextCell) > 0 ) {
						walls.offer(getRight(nextCell));	
					}
					//if (getBottom(v) > 0) {
					//	walls.offer(getBottom(v));
					//}
					if (getLeft(nextCell) > 0 ) {
						walls.offer(getLeft(nextCell));
					}
					System.out.println("walls added: " +getRight(nextCell)+" "+getTop(nextCell)+" "+getLeft(nextCell));

					
					//walls.poll();
					
					cell = nextCell;
					
				}
			} else if (isBottom(smallWall, cell)) {
				if (!bottomValid(cell)) {
					System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					System.out.println("down");
					removeBottom(cell);
					System.out.println(walls.poll() + " polled");
					int nextCell = cell+cells.width();
					maze.push(nextCell);
					visited[nextCell]=true;
					//if (getTop(cell) > 0) {
					//	walls.offer(getTop(cell));	
					//}
					if (getRight(nextCell) > 0 ) {
						walls.offer(getRight(nextCell));	
					}
					if (getBottom(nextCell) > 0 ) {
						walls.offer(getBottom(nextCell));
					}
					if (getLeft(nextCell) > 0 ) {
						walls.offer(getLeft(nextCell));
					}
					System.out.println("walls added: " +getRight(nextCell)+" "+getBottom(nextCell)+" "+getLeft(nextCell));
					
					//walls.poll();
					cell=nextCell;
					
				}
			} else if (isLeft(smallWall, cell)) {
				if (!leftValid(cell)) {
					System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					System.out.println("left");
					removeLeft(cell);
					System.out.println(walls.poll() + " polled");
					int nextCell = cell-1;
					maze.push(nextCell);
					visited[nextCell]=true;
					if (getTop(nextCell) > 0 ) {
						walls.offer(getTop(nextCell));	
					}
					//if (getRight(v) > 0) {
					//	walls.offer(getRight(v));	
					//}
					if (getBottom(nextCell) > 0 ) {
						walls.offer(getBottom(nextCell));
					}
					if (getLeft(nextCell) > 0 ) {
						walls.offer(getLeft(nextCell));
					}
					System.out.println("walls added: " +getTop(nextCell)+" "+getBottom(nextCell)+" "+getLeft(nextCell));

					
					//walls.poll();
					cell=nextCell;
				}
			} else if (isRight(smallWall, cell)) {
				if (!rightValid(cell)) {
					System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					System.out.println("right");
					removeRight(cell);
					System.out.println(walls.poll() + " polled");
					
					int nextCell = cell+1;
					maze.push(nextCell);
					visited[nextCell]=true;
					if (getTop(nextCell) > 0 ) {
						walls.offer(getTop(nextCell));	
					}
					if (getRight(nextCell) > 0 ) {
						walls.offer(getRight(nextCell));	
					}
					if (getBottom(nextCell) > 0 ) {
						walls.offer(getBottom(nextCell));
					}
					//if (getLeft(cell) > 0) {
					//	walls.offer(getLeft(cell));
					//}
					System.out.println("walls added: " +getRight(nextCell)+" "+getBottom(nextCell)+" "+getTop(nextCell));

					
					//walls.poll();
					cell = nextCell;
				}
			}	
		methodCycle++;
		}
		return maze;
	}
	
	boolean topValid(int cell) {
		return !(cell < cells.width() || visited[cell-cells.width()]);
	}
	boolean bottomValid(int cell) {
		return !(cell >= cells.size() - cells.width() || visited[cell+cells.width()]);
	}
	boolean leftValid(int cell) {
		return !(cell % cells.width() == 0 || visited[cell-1]);
	}
	boolean rightValid(int cell) {
		return !(cell % cells.width() == (cells.width() - 1) || visited[cell+1]);
	}
	
	//used to account for row / cell offset
	
	public static <E> void depthFirstRecurse(myGraph<E> g, int v, boolean[] marked) {
		int[] connections = g.neighbors(v);
		Collections.shuffle(Arrays.asList(connections));
		int i; 
		
		
		marked[v] = true;
		//cells.setLabel(v, "v");
		System.out.println(g.getLabel(v));
		
		//Traverse all the neighbors, looking for unmarked vertices:
		for(int nextNeighbor : connections) {
			
			if (!marked[nextNeighbor]) {
				//cells.setLabel(nextNeigbor,"v");
				depthFirstRecurse(g, nextNeighbor, marked);
			}
		}
	}
	
	int difference(int cell) {
		int rowCount = (cell / cells.width());
		int difference = 0;
		if (rowCount > 0) {
			difference = 1;//mazeGraph.width() * rowCount - cells.width() * rowCount;
		} 
	//	System.out.println(rowCount + "rowCount");
		
		int path = cells.minEdgeNeighbor(cell);
		int cellCOORDS = cell + rowCount;
		return difference;
	}
	int cellCOORDS(int cell) {
		int rowCount = (cell / cells.width());
		int difference = 0;
		if (rowCount > 0) {
			difference = 1;//mazeGraph.width() * rowCount - cells.width() * rowCount;
		} 
	//	System.out.println(rowCount + "rowCount");
		
		//int path = cells.minEdgeNeighbor(cell);
		int cellCOORDS = cell + rowCount;
		return cellCOORDS;
	}
	
	void breakLeastWall(int cell) {
	//int other = cell;

		
		//System.out.println(path + "path");
		
		int rowCount = (cell / cells.width());
		int difference = 0;
		if (rowCount > 0) {
			difference = 1;//mazeGraph.width() * rowCount - cells.width() * rowCount;
		} 
	//	System.out.println(rowCount + "rowCount");
		
		int path = cells.minEdgeNeighbor(cell);
		int cellCOORDS = cell + rowCount;
		
		if (path == (cell - cells.width())) {
			removeTop(cell);
		} else if (path == (cell + cells.width())) {
			removeBottom(cell);
		} else if (path == cell + 1) {
			removeRight(cell);
		} else if (path == cell - 1){
			removeLeft(cell);
		}
	//	System.out.println(cell + " cell");
		
	}
	
	void breakAllWalls(int cell) {
		//int other = cell;

		
		//System.out.println(path + "path");
		
		int rowCount = (cell / cells.width());
		int difference = 0;
		if (rowCount > 0) {
			difference = 1;//mazeGraph.width() * rowCount - cells.width() * rowCount;
		} 
	//	System.out.println(rowCount + "rowCount");
		
		//int path = cells.minEdgeNeighbor(cell);
		int cellCOORDS = cell + rowCount;
		
		removeTop(cell);
		removeBottom(cell);
		removeRight(cell);
		removeLeft(cell);
		
	}
	boolean isWall(int cell, int edgeWeight) {
		return getRight(cell) == edgeWeight || getLeft(cell) == edgeWeight || getTop(cell) == edgeWeight || getBottom(cell) == edgeWeight;
	}
	
	void removeRight(int vertex) {
		mazeGraph.removeEdge(topRight(vertex), bottomRight(vertex));
	}
	void removeLeft(int vertex) {
		mazeGraph.removeEdge(topLeft(vertex), bottomLeft(vertex));
	}
	void removeTop(int vertex) {
		mazeGraph.removeEdge(topLeft(vertex), topRight(vertex));
	}
	
	void removeBottom(int vertex) {
		mazeGraph.removeEdge(bottomLeft(vertex), bottomRight(vertex));
	}
	
	boolean isRight(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topRight(cell), bottomRight(cell));
	}
	boolean isBottom(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(bottomRight(cell), bottomLeft(cell));
	}
	boolean isLeft(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topLeft(cell), bottomLeft(cell));
	}
	boolean isTop(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topRight(cell), topLeft(cell));
	}
	
	Integer getRight(int cell) {
		return mazeGraph.getEdge(topRight(cell), bottomRight(cell));
	}
	Integer getLeft(int cell) {
		return mazeGraph.getEdge(topLeft(cell), bottomLeft(cell));
	}
	Integer getTop(int cell) {
		return mazeGraph.getEdge(topLeft(cell), topRight(cell));
	}
	Integer getBottom(int cell) {
		return mazeGraph.getEdge(bottomLeft(cell), bottomRight(cell));
	}
	void display() {	
		//for (int i = 0; i < maze.size(); i++) {
		//	cells.setLabel(maze.pop(), "N");
		//}
		
		int count = 0;
		int rowCount = 1;
		for (int i = 0; i < mazeGraph.size(); i++) {
			if (i < width) { //if i is in the first row...
				System.out.printf("%3s", mazeGraph.getLabel(i));
				if (mazeGraph.isEdge(i, i+1)) {
					//System.out.print(mazeGraph.getEdge(i,i+1));
					System.out.printf("%3s", "-");
				} else {
					System.out.printf("%3s"," ");
				}
				if(i % width == (width - 1)) {
					System.out.println();
					if (i < (mazeGraph.size() - width)) {
						for(int j = 1-width; j < 1; j++) { //the start of the last row to the end of the last row. (0-4 for the first row)
						
						
							//System.out.print(i);
							if (mazeGraph.isEdge((i+j), (i+j+width))) {	
								System.out.printf("%3s","|");
							} else {
								System.out.printf("%3s"," ");
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
					else {
						System.out.printf("%3s"," ");
					}
					System.out.printf("%3s", mazeGraph.getLabel(i));
					
					if(i % width == (width - 1)) {
						System.out.println();
						if (i < (mazeGraph.size() - width)) {
							count = -(width - 1);
							for(int j = -width; j < 0; j++) {//the start of the last row to the end of the last row.
								//System.out.print(i);
								if (mazeGraph.isEdge((i+j+1), (i+j+width)+1)) {
									System.out.printf("%3s","|");
								} 
								else {
									System.out.printf("%3s"," ");
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
	int topRight(int cell) {
		return topLeft(cell) + 1;//difference(cell) + cellCOORDS(cell);
	}
	int topLeft(int cell) {
		return cellCOORDS(cell);
	}
	int bottomRight(int cell) {
		return topRight(cell) + mazeGraph.width();
	}
	int bottomLeft(int cell) {
		return topLeft(cell) + mazeGraph.width();
	}

}
