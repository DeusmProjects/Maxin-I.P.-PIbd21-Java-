
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelConfig extends JPanel {
	private ITransport ship;

    void setShip(ITransport transport) {
    	ship = transport;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ship != null) {
        	ship.DrawShip(g);
        }
    }
}
