
public class Particle {
	public double x, y;
	public double vx, vy;
	public double size;
	
	public boolean marked = false;
	
	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
		this.vx = 20.0 * Math.random() - 10.0;
		this.vy = 20.0 * Math.random() - 10.0;
		this.size = Math.random()/2.0;
		
	}

	public void move(double sizex, double sizey, int FPS) {
		double adjustedVx = vx / FPS;
		double newx = x + adjustedVx;
		if (newx > sizex || newx < 0.0) {
			vx *= -1;
			newx = x + adjustedVx;
		}

		double adjustedVy = vy / FPS;
		double newy = y + adjustedVy;
		if (newy > sizey || newy < 0.0) {
			vy *= -1;
			newy = y + adjustedVy;
		}
		
		x = newx; y = newy;		
	}

	public boolean intersects(Particle neighbor) {
		if (neighbor.equals(this)) {
			return false;
		}
		double dx = x - neighbor.x;
		double dy = y - neighbor.y;
		double dr = size + neighbor.size;
		
		if (((dx * dx) + (dy * dy)) < (dr * dr)) {
			return true;
		}
		return false;
		
	}
}
