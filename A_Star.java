import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class A_Star implements PathSlove {
	// open table
	private PriorityQueue<Node> openList;
	
	// close table
	private ArrayList<Node> closeList;
	
	// each node's g value
	HashMap<Node,Integer> g;
	
	// each node's f value
	HashMap<Node,Integer> f;
	
	// Initial capacity
	private int capacity = 100;
	
	// distance between two nodes
	private int spacing = 1;
	
	// nodes in graph
	private Node[] nodes = new Node[capacity];
	
	private int endNodeNumber = -1;
	
	public A_Star() {
		this.g = new HashMap<Node,Integer>();
		this.f = new HashMap<Node,Integer>();
		this.openList = new PriorityQueue<Node>(capacity,new Compare());
		this.closeList = new ArrayList<Node>();
	}
	
	private int calculateH(Node neighbour, Node current) {
		int xTmp = neighbour.getX() - current.getX();
		int yTmp = neighbour.getY() - current.getY();
		
		return xTmp * xTmp + yTmp * yTmp;
	}
	
	class Compare implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			if (f.get(o1) < f.get(o2)) {
				return -1;
			} else if (f.get(o1) > f.get(o2)){
				return 1;
			} else {
				return 1;
			}
		}
	}
	
	@Override
	public void preProcess() {
		for(int i = 0;i < 10;i ++) {
			this.nodes[i] = new Node();
			this.nodes[i].setPath("N" + i);
		}
		
		this.nodes[0].setXY(0, 2);
		this.nodes[1].setXY(1, 2);
		this.nodes[2].setXY(2, 2);
		this.nodes[3].setXY(3, 2);
		this.nodes[4].setXY(1, 1);
		this.nodes[5].setXY(2, 1);
		this.nodes[6].setXY(3, 1);
		this.nodes[7].setXY(1, 0);
		this.nodes[8].setXY(2, 0);
		this.nodes[9].setXY(3, 0);
		
		this.nodes[0].addNeighbours(this.nodes[1]);
		this.nodes[1].addNeighbours(this.nodes[0],this.nodes[2],this.nodes[4]);
		this.nodes[2].addNeighbours(this.nodes[1],this.nodes[3],this.nodes[5]);
		this.nodes[3].addNeighbours(this.nodes[2]);
		this.nodes[4].addNeighbours(this.nodes[1],this.nodes[5],this.nodes[7]);
		this.nodes[5].addNeighbours(this.nodes[2],this.nodes[4],this.nodes[6]);
		this.nodes[6].addNeighbours(this.nodes[5],this.nodes[9]);
		this.nodes[7].addNeighbours(this.nodes[4],this.nodes[8]);
		this.nodes[8].addNeighbours(this.nodes[7],this.nodes[9]);
		this.nodes[9].addNeighbours(this.nodes[6],this.nodes[8]);
	}

	@Override
	public void output() {
		Node tmp = this.nodes[this.endNodeNumber];
		Stack<Node> stack = new Stack<Node>();
		
		while(tmp.getParent() != null) {
			stack.push(tmp);
			tmp = tmp.getParent();
		}
		stack.push(tmp);
		while(!stack.isEmpty()) {
			if (stack.size() == 1) {
				System.out.print(stack.pop().getPath());
			} else {
				System.out.print(stack.pop().getPath() + " -> ");
			}
		}
		System.out.println();
	}

	@Override
	public boolean traverse(int start, int end) {
		this.endNodeNumber = end;
		this.openList.clear();
		this.closeList.clear();
		this.g.put(this.nodes[start], 0);
		this.openList.add(this.nodes[start]);
		
		while(!this.openList.isEmpty()) {
			Node current = this.openList.element();
			
			if (current.equals(this.nodes[end])) {
				System.out.println("Hi~,I'm finished");
				return true;
			}
			
			this.closeList.add(this.openList.poll());
			ArrayList<Node> neighbours = current.getNeighbour();
			
			for(Node neighbour : neighbours) {
				int gTmp = this.g.get(current) + this.spacing;
				int fTmp = gTmp + calculateH(neighbour,current);
				
				if (this.closeList.contains(neighbour)) {
					if (this.g.get(neighbour) == null) {
						this.g.put(neighbour, gTmp);
					}
					if (this.f.get(neighbour) == null) {
						this.f.put(neighbour,fTmp);
					}
					
					if (fTmp >= this.f.get(neighbour)) {
						continue;
					}
				}
				if(!this.openList.contains(neighbour) || fTmp < this.f.get(neighbour)) {
					neighbour.setParent(current);
					this.g.put(neighbour, gTmp);
					this.f.put(neighbour, fTmp);
					if (!this.openList.contains(neighbour)) {
						this.openList.add(neighbour);
					}
				}
			}
		}
		
		return false;
	}
}
