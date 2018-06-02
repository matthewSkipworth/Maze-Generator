import java.util.Arrays;
/**
 * Main class drives the program
 * @author Matthew Skipworth
 * @version 1 June 2018
 */
public class Main {
/**
 * main method drives the program	
 * @param args represents input passed into the console.
 */
	public static void main(String[] args) {
		
		Maze myMaze = new Maze(10,10, false);
		//Maze myMaze = new Maze(5, 5, true);
		myMaze.mazeGraph.connectAllAdjacentVertices();
		
		myMaze.maze = myMaze.prims(0);
		
		myMaze.getSolution();
		myMaze.display();
		//System.out.println(myMaze.maze);
		
	}
	

}