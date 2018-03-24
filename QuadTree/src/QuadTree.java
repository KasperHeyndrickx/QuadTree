import java.util.ArrayList;

public class QuadTree {
	QuadNode root;
	private double sizex, sizey;
	
	public QuadTree(double sizex, double sizey) {
		this.sizex = sizex;
		this.sizey = sizey;
	}
	
	public void insert(Particle p) {
		if (root == null) {
			root = new QuadNode(new Rectangle(0.0, sizex, 0.0, sizey));
		}
		root.insert(p);
	}
	
	public ArrayList<Rectangle> getBoundaries() {
		ArrayList<Rectangle> boundaries = new ArrayList<>();
		if (root != null) {
			root.fillBoundaryList(boundaries);
		}
		return boundaries;
	}
	
	public ArrayList<Particle> getNeighbors(Particle p) {
		ArrayList<Particle> neighbors = new ArrayList<>();
		if (root != null) {
			root.getNeighbors(p, neighbors);
		}
		return neighbors;
	}
}
