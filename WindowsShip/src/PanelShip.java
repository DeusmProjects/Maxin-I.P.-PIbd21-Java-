import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelShip extends JPanel{
	public static ITransport ship;
	public static boolean initialization = false;
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if(initialization) {
			ship.DrawShip(g);
		}
	}
}
