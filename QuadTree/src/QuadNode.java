import java.util.ArrayList;

public class QuadNode {
	private final int MAX_SIZPoint = 5;
	private QuadNode[] children;
	private Rectangle boundary;
	ArrayList<Particle> objects;
	
	public QuadNode(Rectangle boundary) {
		this.boundary = boundary;
	}
	
	public void insert(Particle object) {
		if (this.objects == null) {
			this.objects = new ArrayList<>();
		}
		this.objects.add(object);
		if (objects.size() > MAX_SIZPoint) {
			createChildren();
		}		
		if (children != null) {
			// push objects down
			for (Particle p : objects) {
				int i = getQuadrant(p);
				children[i].insert(p);
			}
			objects.clear();
		}
	}
	
	public void fillBoundaryList(ArrayList<Rectangle> boundaries) {
		boundaries.add(boundary);
		if (children == null) return;
		for (QuadNode child : children) {
			child.fillBoundaryList(boundaries);
		}
	}
	
	public void getNeighbors(Particle p, ArrayList<Particle> neighbors) {
		if (children == null) {
			neighbors.addAll(objects);
			return;
		}
		int i = getQuadrant(p);
		children[i].getNeighbors(p, neighbors);
	}
	
	private void createChildren() {
		double centerX = boundary.left + ((boundary.right - boundary.left) / 2.0);
		double centerY = boundary.bot + ((boundary.top - boundary.bot) / 2.0);
		
		children = new QuadNode[4];
		children[0] = new QuadNode(new Rectangle(centerX, boundary.right, centerY, boundary.top)); // top right
		children[1] = new QuadNode(new Rectangle(boundary.left, centerX, centerY, boundary.top));  // top left
		children[2] = new QuadNode(new Rectangle(boundary.left, centerX, boundary.bot, centerY));  // bottom left
		children[3] = new QuadNode(new Rectangle(centerX, boundary.right, boundary.bot, centerY)); // bottom right
	}
	
	private int getQuadrant(Particle p) {
		double centerX = boundary.left + ((boundary.right - boundary.left) / 2.0);
		double centerY = boundary.bot + ((boundary.top - boundary.bot) / 2.0);
		boolean left = p.x < centerX;
		boolean right = !left;
		boolean top = p.y > centerY;
		boolean bot = !top;
		
		if (top && right) return 0;
		if (top && left) return 1;
		if (bot && left) return 2;
		if (bot && right) return 3;
		
		return 0;
	}
}
