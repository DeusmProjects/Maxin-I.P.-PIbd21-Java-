package lab7;
import java.awt.Graphics;

import javax.swing.JList;
import javax.swing.JPanel;

public class PanelDock extends JPanel {
	private MultiDocks dock;
	private JList listBoxDocks;
	private final int countDocks = 5;

    public MultiDocks getDock() {
        return dock;
    }

    public PanelDock() {
        dock = new MultiDocks(countDocks, 615, 603);
    }
    
    public void setListDocks(JList listBoxDocks) {
    	this.listBoxDocks = listBoxDocks;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int selectedDock = listBoxDocks.getSelectedIndex();
        dock.getDock(selectedDock).draw(g);
        if(selectedDock != -1) {
	        if(dock != null) {
				dock.getDock(selectedDock).draw(g);
			}
        }
    }
}
