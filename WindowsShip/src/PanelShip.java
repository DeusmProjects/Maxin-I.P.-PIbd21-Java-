package lab8;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelShip extends JPanel{
	
	public static ITransport ship;
	public static boolean initialization = false;
	
	void setShip(ITransport transport) {
        ship = transport;
    }
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if(ship != null) {
			ship.DrawShip(g);
		}
	}
}
