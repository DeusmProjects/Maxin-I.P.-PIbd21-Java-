
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeDock extends JPanel {
	public static ITransport ship;
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if (ship != null)
        {
			ship.SetPosition(5, 5, this.getWidth(), this.getHeight());
			ship.DrawShip(g);
        }
	}
}