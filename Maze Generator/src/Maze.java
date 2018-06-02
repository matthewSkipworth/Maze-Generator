//Written by Matthew Skipworth

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
/**
 * This class generates a random maze and solution. To generate the maze, 
 * the class uses Prim's algorithm to generate the maze and a stack to store
 * the the path taken.
 * 
 * @author Matthew Skipworth
 * @version 1 June 2018
 */
public class Maze {
	
	int width;
	int depth;
	
	static myGraph mazeGraph;
	static myGraph cells;
	
	static Stack<Integer> maze;
	static Stack<Integer> hold;
	
	boolean[] visited;
	
	boolean debug;
	
	//ArrayList<Integer> shortest = new ArrayList<Integer>();
/**
 * Maze constructor	
 * @param wide represents the width of the maze to be generated.
 * @param deep represents the depth of the maze to be generated.
 * @param debug when true enters a debug state.
 */
	public Maze(int wide, int deep, boolean debug) {
		this.width = wide;
		this.depth = deep;
		
		
		cells = new myGraph(((wide-1)*(deep-1)), (deep-1), (wide-1)); 
		mazeGraph = new myGraph(wide * deep, deep, wide);
		
		visited = new boolean[cells.size()]; 
		if (debug) {
			testMyGraph(this);
		}
	}
	
/**
 * prims method uses the Prim's algorithm to generate a maze.	
 * @param cell represents the starting cell of the maze.
 * @return returns a Stack representing the solution path.
 */
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
		
		while ((!walls.isEmpty() || containsFalse) && !walls.isEmpty()) {
			//methodCycle++;
			//System.out.println(maze.toString());
			//System.out.println("current cell"+ cell);
			//System.out.println("cell walls "+getRight(cell) +", "+ getLeft(cell) +", " + getTop(cell) +", "+ getBottom(cell));
		
			
			containsFalse = false;
			for (boolean el:visited) {
				if (el == false) {
					containsFalse = true;
				}
			}
			//System.out.println(maze.toString());
			//System.out.println(maze.peek());
			//System.out.println("walls: " + walls.toString());
			//System.out.println("current cell: " + cell + "\n");
			
			smallWall = walls.peek();
			//System.out.println(isWall(cell, smallWall));
			
		if (maze.size() >= 1 && !isWall(cell, smallWall)) {
			
			
			//System.out.println("stack popped!");
			hold.push(maze.pop());
			//System.out.println(cell);
			if (maze.size() == 0) {
				while (!isWall(cell, smallWall) && !hold.isEmpty())  {
				
				maze.push(hold.pop());
				cell = maze.peek();
				}
			}
			cell = maze.peek();
			
		} else if (isTop(smallWall, cell)) {
				if (!topValid(cell)) {
					walls.poll();
				//	System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					//System.out.println("up");
					removeTop(cell);
					
				//	System.out.println(walls.poll() + " polled");
					int nextCell = cell-cells.width();
					maze.push(nextCell);
					visited[nextCell]=true;
					
					walls.poll();
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
					//System.out.println("walls added: " +getRight(nextCell)+" "+getTop(nextCell)+" "+getLeft(nextCell));

					
					//walls.poll();
					
					cell = nextCell;
					
				}
			} else if (isBottom(smallWall, cell)) {
				if (!bottomValid(cell)) {
					walls.poll();
					//System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
					//System.out.println("down");
					removeBottom(cell);
					
					//System.out.println(walls.poll() + " polled");
					int nextCell = cell+cells.width();
					maze.push(nextCell);
					visited[nextCell]=true;
					//if (getTop(cell) > 0) {
					//	walls.offer(getTop(cell));	
					//}
					walls.poll();
					if (getRight(nextCell) > 0 ) {
						walls.offer(getRight(nextCell));	
					}
					if (getBottom(nextCell) > 0 ) {
						walls.offer(getBottom(nextCell));
					}
					if (getLeft(nextCell) > 0 ) {
						walls.offer(getLeft(nextCell));
					}
					//System.out.println("walls added: " +getRight(nextCell)+" "+getBottom(nextCell)+" "+getLeft(nextCell));
					
					//walls.poll();
					cell=nextCell;
					
				}
			} else if (isLeft(smallWall, cell)) {
				if (!leftValid(cell)) {
					walls.poll();
					//System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
				//	System.out.println("left");
					removeLeft(cell);
					walls.poll();
				//	System.out.println(walls.poll() + " polled");
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
				//	System.out.println("walls added: " +getTop(nextCell)+" "+getBottom(nextCell)+" "+getLeft(nextCell));

					
					//walls.poll();
					cell=nextCell;
				}
			} else if (isRight(smallWall, cell)) {
				if (!rightValid(cell)) {
					walls.poll();
					//System.out.println(walls.poll() + " polled, visited or boundary error.");
				} else {
				//	System.out.println("right");
					removeRight(cell);
					walls.poll();
				//	System.out.println(walls.poll() + " polled");
					
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
				//	System.out.println("walls added: " +getRight(nextCell)+" "+getBottom(nextCell)+" "+getTop(nextCell));

					
					//walls.poll();
					cell = nextCell;
				}
			}	
		
		}
		//while (maze.peek() != cells.size()) {
		//	if (!hold.isEmpty()) {
		//		maze.push(hold.pop());
		//	} else if (!maze.isEmpty()) {
		//		hold.push(maze.pop());
		//	}
		//}
		while(!maze.isEmpty()) {
			hold.push(maze.pop());
		}
		maze.push(hold.pop());
		while((maze.peek() != cells.size()-1) && !hold.isEmpty()) {
			maze.push(hold.pop());
		}
		return maze;
	}
/**
 * topValid method returns a boolean value based on whether or not moving up
 * in the maze is a valid move.
 * 	
 * @param cell represents the current cell in the maze.
 * @return returns a boolean based on whether moving up is a valid move.
 */
	boolean topValid(int cell) {
		return !(cell < cells.width() || visited[cell-cells.width()]);
	}
/**
 * bottomValid method returns a boolean based on whether or not moving down is
 * a valid move.
 * 	
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not moving down is a 
 * valid move.
 */
	boolean bottomValid(int cell) {
		return !(cell >= cells.size() - cells.width() || visited[cell+cells.width()]);
	}
/**
 * leftValid method returns a boolean value based on whether or not moving
 * left is a valid move.	
 * 
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not moving left is 
 * a valid move.
 */
	boolean leftValid(int cell) {
		return !(cell % cells.width() == 0 || visited[cell-1]);
	}
/**
 * rightValid method returns a boolean value based on whether or not moving 
 * right is a valid move.
 * 	
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not moving right is a
 * valid move.
 */
	boolean rightValid(int cell) {
		return !(cell % cells.width() == (cells.width() - 1) || visited[cell+1]);
	}
	
/**
 * depthFirstRecurse method does a depth first search of the maze.
 * 
 * @param g represents the graph to be searched
 * @param v represents a starting vertex
 * @param marked represents an array of visited vertices
 */
	
	public static <E> void depthFirstRecurse(myGraph<E> g, int v, boolean[] marked) {
		int[] connections = g.neighbors(v);
		Collections.shuffle(Arrays.asList(connections));
		int i; 
		
		
		marked[v] = true;
		//cells.setLabel(v, "v");
		//System.out.println(g.getLabel(v));
		
		//Traverse all the neighbors, looking for unmarked vertices:
		for(int nextNeighbor : connections) {
			
			if (!marked[nextNeighbor]) {
				//cells.setLabel(nextNeigbor,"v");
				depthFirstRecurse(g, nextNeighbor, marked);
			}
		}
	}
/**
 * difference method calculates the difference between the cell index and the
 * grid vertex index.   
 * 	
 * @param cell represents the current cell in the maze
 * @return returns the difference
 */
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
	
/**
 * method cellCOORDS is used to compute the cells coordinates in relation
 * to the grid. 
 * 	
 * @param cell represents the current cell in the maze.
 * @return returns the cell's coordinates.
 */
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
/**
 * breakLeastWall method looks for the cell wall with the lowest weight and
 * breaks that wall.
 * 	
 * @param cell represents the current cell in the maze.
 */
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
/**
 * breakAllWalls method breaks all the walls of the current cell.	
 * 
 * @param cell represents the current cell in the maze.
 */
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
/**
 * isWall method takes in a cell and a wall weight and returns a boolean value 
 * based on whether or not the wall weight belongs to the cell.
 * 	
 * @param cell represents the current cell in the maze.
 * @param edgeWeight represents the weight of the wall to be checked
 * @return returns a boolean value of whether or not the wall weight belongs
 * to the current cell.
 */
	boolean isWall(int cell, int edgeWeight) {
		return getRight(cell) == edgeWeight || getLeft(cell) == edgeWeight || getTop(cell) == edgeWeight || getBottom(cell) == edgeWeight;
	}
/**
 * removeRight method removes the cells right wall.	
 * @param vertex represents the current cell in the maze.
 */
	void removeRight(int vertex) {
		mazeGraph.removeEdge(topRight(vertex), bottomRight(vertex));
	}
/**
 * removeLeft method removes the current cells left wall	
 * @param vertex represents the current cell in the maze.
 */
	void removeLeft(int vertex) {
		mazeGraph.removeEdge(topLeft(vertex), bottomLeft(vertex));
	}
/**
* removeTop method removes the current cells top wall	
* @param vertex represents the current cell in the maze.
* 
*/	
	void removeTop(int vertex) {
		mazeGraph.removeEdge(topLeft(vertex), topRight(vertex));
	}
/**
* removeBottom method removes the current cells bottom wall	
* @param vertex represents the current cell in the maze.
* 
*/	
	void removeBottom(int vertex) {
		mazeGraph.removeEdge(bottomLeft(vertex), bottomRight(vertex));
	}
/**
 * isRight method takes in an edge weight and a cell and determines whether
 * the wall containing the edge weight belongs to the cell.	
 * @param edgeWeight represents a walls weight
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not the
 * edge weight belongs to the cell.
 */
	boolean isRight(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topRight(cell), bottomRight(cell));
	}
/**
 * isBottom method takes in an edge weight and a cell and determines whether
 * the wall containing the edge weight belongs to the cell.		
 * @param edgeWeight represents a wall's edge weight
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not the edge weight 
 * belongs to the cell.
 */
	boolean isBottom(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(bottomRight(cell), bottomLeft(cell));
	}
/**
 * isLeft method takes in an edge weight and a cell and determines whether
 * the wall containing the edge weight belongs to the cell.		
 * @param edgeWeight represents a wall's edge weight
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not the edge weight 
 * belongs to the cell.
 */
	boolean isLeft(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topLeft(cell), bottomLeft(cell));
	}
/**
 * isTop method takes in an edge weight and a cell and determines whether
 * the wall containing the edge weight belongs to the cell.		
 * @param edgeWeight represents a wall's edge weight
 * @param cell represents the current cell in the maze.
 * @return returns a boolean value based on whether or not the edge weight 
 * belongs to the cell.
 */
	boolean isTop(int edgeWeight, int cell) {
		return edgeWeight == mazeGraph.getEdge(topRight(cell), topLeft(cell));
	}
/**
 * method getRight returns the current cells right wall edge weight	
 * @param cell represents the current cell in the maze.
 * @return returns the wall's edge weight.
 */
	Integer getRight(int cell) {
		return mazeGraph.getEdge(topRight(cell), bottomRight(cell));
	}
/**
 * method getLeft returns the current cells right wall edge weight	
 * @param cell represents the current cell in the maze.
 * @return returns the wall's edge weight.
 */
	Integer getLeft(int cell) {
		return mazeGraph.getEdge(topLeft(cell), bottomLeft(cell));
	}
/**
 * method getTop returns the current cells right wall edge weight	
 * @param cell represents the current cell in the maze.
 * @return returns the wall's edge weight.
 */
	Integer getTop(int cell) {
		return mazeGraph.getEdge(topLeft(cell), topRight(cell));
	}
/**
 * method getBottom returns the current cells right wall edge weight	
 * @param cell represents the current cell in the maze.
 * @return returns the wall's edge weight.
 */
	Integer getBottom(int cell) {
		return mazeGraph.getEdge(bottomLeft(cell), bottomRight(cell));
	}
/**
 * getSolution method changes the labels of the cells stored in the 
 * maze stack to display the path to the finish.	
 */
	void getSolution() {
		String found = new String("x");
		int index = 0;
		while(!maze.isEmpty()) {
			//System.out.println(index);
			index = maze.peek();
			cells.labels[index]=found;
			maze.pop();
			//hold.push(maze.pop());
		}
		for (int i=0; i<cells.size();i++) {
			if (cells.getLabel(i)==null) {
				cells.setLabel(i, "");
			} 
		}
		//cells.setLabel(3, "b");
	}
/**
 * display method displays the maze to the console.	
 */
	void display() {	
		for (int i = 0; i < mazeGraph.size(); i++) {
			//graph.mazeGraph.setLabel(i, (char) (65 + i));
			mazeGraph.setLabel(i, "+");
			
		}
		//for (int i = 0; i < cells.size(); i++) {
		//	cells.setLabel(i, "");
		//}
		//for (int i = 0; i < maze.size(); i++) {
		//	cells.setLabel(maze.peek(), "N");
		//}
		
		int count = 0;
		int rowCount = 1;
		for (int i = 0; i < mazeGraph.size(); i++) {
			if (i < width) { //if i is in the first row...
				System.out.printf("%2s", mazeGraph.getLabel(i));
				if (mazeGraph.isEdge(i, i+1)) {
					//System.out.print(mazeGraph.getEdge(i,i+1));
					System.out.printf("%2s", "-");
				} else {
					System.out.printf("%2s"," ");
				}
				if(i % width == (width - 1)) {
					System.out.println();
					if (i < (mazeGraph.size() - width)) {
						for(int j = 1-width; j < 1; j++) { //the start of the last row to the end of the last row. (0-4 for the first row)
						
						
							//System.out.print(i);
							if (mazeGraph.isEdge((i+j), (i+j+width))) {	
								System.out.printf("%2s","|");
							} else {
								System.out.printf("%2s"," ");
							}	
							if (count < cells.width()) {
								System.out.printf("%2s",cells.getLabel(j+i));
								count++;
							}
						}
					}
				}
				
			} else {
				if (i % width == 0) {
					
					rowCount++;
					System.out.println();
					System.out.printf("%2s", mazeGraph.getLabel(i));
					
					
				} else {
					if (mazeGraph.isEdge(i, i-1)) {
						System.out.printf("%2s","-");
					}
					else {
						System.out.printf("%2s"," ");
					}
					System.out.printf("%2s", mazeGraph.getLabel(i));
					
					if(i % width == (width - 1)) {
						System.out.println();
						if (i < (mazeGraph.size() - width)) {
							count = -(width - 1);
							for(int j = -width; j < 0; j++) {//the start of the last row to the end of the last row.
								//System.out.print(i);
								if (mazeGraph.isEdge((i+j+1), (i+j+width)+1)) {
									System.out.printf("%2s","|");
								} 
								else {
									System.out.printf("%2s"," ");
								}
								if (count < 0) {
									//System.out.print(i);
									System.out.printf("%2s", cells.getLabel((i + 1)-rowCount+count));
									count++;
									
								}
							}
						}
					}
				}
			}
		}	
	}
/**
 * method topRight returns the top right corner of the current cell.	
 * @param cell represents the current cell in the maze.
 * @return returns a the index of the top right corner in the mazeGraph
 * graph.
 */
	int topRight(int cell) {
		return topLeft(cell) + 1;//difference(cell) + cellCOORDS(cell);
	}
/**
 * method topLeft returns the top right corner of the current cell.	
 * @param cell represents the current cell in the maze.
 * @return returns a the index of the top left corner in the mazeGraph
 * graph.
 */	
	int topLeft(int cell) {
		return cellCOORDS(cell);
	}
/**
 * method bottomRight returns the top right corner of the current cell.	
 * @param cell represents the current cell in the maze.
 * @return returns a the index of the bottom right corner in the mazeGraph
 * graph.
 */	
	int bottomRight(int cell) {
		return topRight(cell) + mazeGraph.width();
	}
/**
 * method bottomLeft returns the top right corner of the current cell.	
 * @param cell represents the current cell in the maze.
 * @return returns a the index of the bottom left corner in the mazeGraph
 * graph.
 */	
	int bottomLeft(int cell) {
		return topLeft(cell) + mazeGraph.width();
	}
	/**
	 * testMyGraph method tests the methods contained in the myGraph class.
	 */
		public static void testMyGraph(Maze graph) {
			//Maze graph = new Maze(5,5, false);
			
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
			graph.debugPrims(0);
			//graph.getSolution();
			graph.display();
			
			
		}
		Stack<Integer> debugPrims(int cell) {
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
			
			while ((!walls.isEmpty() || containsFalse) && !walls.isEmpty()) {
				methodCycle++;
				System.out.println(maze.toString());
				System.out.println("current cell"+ cell);
				System.out.println("cell walls "+getRight(cell) +", "+ getLeft(cell) +", " + getTop(cell) +", "+ getBottom(cell));
			
				
				containsFalse = false;
				for (boolean el:visited) {
					if (el == false) {
						containsFalse = true;
					}
				}
				System.out.println(maze.toString());
				System.out.println(maze.peek());
				System.out.println("walls: " + walls.toString());
				System.out.println("current cell: " + cell + "\n");
				
				smallWall = walls.peek();
				System.out.println(isWall(cell, smallWall));
				
			if (maze.size() >= 1 && !isWall(cell, smallWall)) {
				
				
				System.out.println("stack popped!");
				hold.push(maze.pop());
				System.out.println(cell);
				if (maze.size() == 0) {
					while (!isWall(cell, smallWall) && !hold.isEmpty())  {
					
					maze.push(hold.pop());
					cell = maze.peek();
					}
				}
				cell = maze.peek();
				
			} else if (isTop(smallWall, cell)) {
					if (!topValid(cell)) {
						//walls.poll();
						System.out.println(walls.poll() + " polled, visited or boundary error.");
					} else {
						System.out.println("up");
						removeTop(cell);
						//walls.poll();
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
						//walls.poll();
						System.out.println(walls.poll() + " polled, visited or boundary error.");
					} else {
						System.out.println("down");
						removeBottom(cell);
						//walls.poll();
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
						//walls.poll();
						System.out.println(walls.poll() + " polled, visited or boundary error.");
					} else {
						System.out.println("left");
						removeLeft(cell);
						//walls.poll();
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
						//walls.poll();
						System.out.println(walls.poll() + " polled, visited or boundary error.");
					} else {
						System.out.println("right");
						removeRight(cell);
						//walls.poll();
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
			
			}
			//while (maze.peek() != cells.size()) {
			//	if (!hold.isEmpty()) {
			//		maze.push(hold.pop());
			//	} else if (!maze.isEmpty()) {
			//		hold.push(maze.pop());
			//	}
			//}
			while(!maze.isEmpty()) {
				hold.push(maze.pop());
			}
			maze.push(hold.pop());
			while((maze.peek() != cells.size()-1) && !hold.isEmpty()) {
				maze.push(hold.pop());
			}
			return maze;
		}
	

}