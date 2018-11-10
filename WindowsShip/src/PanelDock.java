import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelDock extends JPanel {
	private Dock<ITransport> dock;

    public Dock<ITransport> getDock() {
        return dock;
    }

    public PanelDock() {
        dock = new Dock<>(10, 615, 603);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(dock != null) {
			dock.draw(g);
		}
    }
}
