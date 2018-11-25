package heroadn.maverickhunter.TheFinalChatDown.Util;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class FrameMovable extends MouseAdapter{
	
	private JFrame frame;
	private Point mouseCord = null;
	
	public FrameMovable(JFrame frame) {
		this.frame = frame;
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseCord = null;
	}
	
	public void mousePressed(MouseEvent e) {
		mouseCord = e.getPoint();
	}
	
	public void mouseDragged(MouseEvent e) {
		Point cords = e.getLocationOnScreen();
		
		//Mouse pode ficar no lugar que esta durante o drag
		frame.setLocation(cords.x - mouseCord.x, cords.y - mouseCord.y);
	}
}	

