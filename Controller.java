/*
 * using Strategy Pattern for different algorithms which can solve the shortest problems. 
 */

interface PathSlove {
	public void preProcess();
	public boolean traverse(int start, int end);
	public void output();
}

public class Controller {

}
