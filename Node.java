import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	// position of node
	private int x;
	private int y;
	
	// node 's parent or vanguard
	private Node parent;
	
	// nodes which can be reached in one step
	private ArrayList<Node> neighbour;
	
	// best path stored
	private Object path;
	
	public Node() {
		this.neighbour = new ArrayList<Node>();
		this.path = new Object();
	}
	
	public Node(int x,int y) {
		this();
		this.setXY(x, y);
	}
	
	public Node(Node parent) {
		this();
		this.parent = parent;
	}
	
	public Node(int x, int y, Node par) {
		this();
		this.setXY(x, y);
		this.parent = par;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x,int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void setParent(Node par) {
		this.parent = par;
	}
	
	public void setNeighbour(ArrayList<Node> neighbours) {
		this.neighbour = neighbours;
	}
	
	public void addNeighbour(Node nei) {
		this.neighbour.add(nei);
	}
	
	public void addNeighbours(Node... node) {
		this.neighbour.addAll(Arrays.asList(node));
	}
	
	public void setPath(Object obj) {
		this.path = obj;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public Object getPath() {
		return this.path;
	}
	
	public ArrayList<Node> getNeighbour() {
		return this.neighbour;
	}
	
	public boolean equals(Node node) {
		return this.x == node.getX() && this.y == node.getY();
	}
}