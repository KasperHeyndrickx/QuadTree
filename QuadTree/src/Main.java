import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
	private static MyCanvas canvas;
	
	private static final int PARTICLES = 500;
	private static final double SIZEX = 100.0;
	private static final double SIZEY = 100.0;
	private static final int FPS = 100;
	
	public static void main(String[] args) {
	    JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(50, 50, 850, 850);
	    canvas = new MyCanvas();
	    window.getContentPane().add(canvas);
	    window.setVisible(true);
	    
	    loop();
	}
	
	public static void loop() {
		QuadTree qt = new QuadTree(SIZEX, SIZEY);
		
		ArrayList<Particle> points = new ArrayList<>();
		for (int i = 0; i < PARTICLES; i++) {
			points.add(new Particle(Math.random()*100.0, Math.random()*100.0));
		}

	    while (true) {
			long frameStart = System.currentTimeMillis();
			for (Particle p : points) {
				p.move(SIZEX, SIZEY, FPS);
			}
			qt = new QuadTree(SIZEX, SIZEY);
			for (Particle p : points) {
				qt.insert(p);
				p.marked = false;
			}	
			
			for (Particle p : points) {
				ArrayList<Particle> neighbors = qt.getNeighbors(p);
				for (Particle neighbor : neighbors) {
					if (neighbor.marked) continue;
					if (p.intersects(neighbor)) {
						neighbor.marked = true;
						p.marked = true;
						
					}
				}
			}
			
			canvas.set(qt.getBoundaries(), points);
	    	canvas.repaint();
	    	
	        long framerate = 1000 / FPS;
            long elapsedTime = System.currentTimeMillis() - frameStart;
            try {
                if (elapsedTime < framerate) {
                    Thread.sleep(framerate - elapsedTime);
                }
                else {
                    Thread.sleep(5);
                }
            }
            catch (InterruptedException e) {
                break;
            }
	    }
	}
}