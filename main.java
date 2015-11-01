/*
 * A* algorithm for finding shortest path
 * Author : Joker
 * 20:44/28/11/2015
 */

/* graph

   N0 - N1 - N2 - N3
        |     |
        N4 - N5 - N6 
        |         |
        N7 - N8 - N9             
*/

/* start :N0, end :N8
 * the best path is 
 * N0 -> N1 -> N4 -> N7 -> N8
 */

public class main {
	public static void main(String[] argv) {
		System.out.println("Hello World");
		PathSlove aStar = new A_Star();
		
		aStar.preProcess();
		
		if(aStar.traverse(0, 8) == true) {
			aStar.output();
		} else {
			System.out.println("Sorry,can not find a path for this problem");
		}
	}
}
