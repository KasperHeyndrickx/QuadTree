import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

class MyCanvas extends JComponent {
	private ArrayList<Rectangle> rects;
	private ArrayList<Particle> points;
	
	private static final long serialVersionUID = 1L;

	public MyCanvas() {
	}
	
	public void paint(Graphics g) {	
		double sf = 8.0;
		double pointSf = 20.0;
		if (rects != null) {
			for (Rectangle r : rects) {
				g.drawRect((int)(sf*r.left), (int)(sf*r.bot), (int)(sf*r.width), (int)(sf*r.height));
			}
		}
		if (points != null) {
			for (Particle p : points) {
				if (p.marked) {
					g.setColor(Color.RED);
				}
				g.fillOval((int)(sf*p.x), (int)(sf*p.y), (int)(p.size*pointSf), (int)(p.size*pointSf));
				g.setColor(Color.BLACK);
			}
		}
	}

	public void set(ArrayList<Rectangle> boundaries, ArrayList<Particle> points2) {
		this.rects = boundaries;
		this.points = points2;
		
	}
}
