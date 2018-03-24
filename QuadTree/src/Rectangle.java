
public class Rectangle {
	public double left, right, top, bot;
	public double width, height;
	
	public Rectangle(double left, double right, double bot, double top) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bot = bot;
		this.width = right - left;
		this.height = top - bot;
	}
	
	
}
