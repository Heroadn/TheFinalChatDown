package heroadn.maverickhunter.TheFinalChatDown.GUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import heroadn.maverickhunter.TheFinalChatDown.Util.FrameMovable;
import heroadn.maverickhunter.TheFinalChatDown.Util.Path;

public class GUI extends JFrame{
	
	@Path
	public GUI() {
		setLocationRelativeTo(null);
		setUndecorated(true);
		movable();
	}
	
	public void movable() {
		FrameMovable fm = new FrameMovable(this);
		addMouseListener(fm);
		addMouseMotionListener(fm);	
	}
	
	public MouseAdapter exitButton(final JLabel label,final Color in,final Color out) {
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setForeground(in);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setForeground(out);
			}
			
		};
		
		return mouseAdapter;
	}
}
