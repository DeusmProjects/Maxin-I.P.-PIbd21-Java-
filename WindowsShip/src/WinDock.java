import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WinDock {

	private JFrame frame;
	private JTextField textFieldPlace;
	MultiDocks dock; 
	private PanelDock panelDock;
	private PanelShip panelTakeShip;
	private JList listBoxDocks;
	private DefaultListModel model;
	private final int countDocks = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinDock window = new WinDock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WinDock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 927, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelDock = new PanelDock();
		panelDock.setBounds(10, 11, 615, 603);
		frame.getContentPane().add(panelDock);
		
		dock = panelDock.getDock();
		
		JButton buttonParkingCruiser = new JButton("\u041A\u0440\u0435\u0439\u0441\u0435\u0440");
		buttonParkingCruiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				Cruiser ship = new Cruiser(100, 1000, mainColor);
				int place = dock.getDock(listBoxDocks.getSelectedIndex()).addShip(ship);			
				if(place != -1){
					panelDock.repaint();
				}
			}
		});
		buttonParkingCruiser.setBounds(800, 65, 89, 23);
		frame.getContentPane().add(buttonParkingCruiser);
		
		JButton buttonParkingWar = new JButton("\u0412\u043E\u0435\u043D\u043D\u044B\u0439");
		buttonParkingWar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				Color dopColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				WarShip ship = new WarShip(100, 1000, mainColor, dopColor, true, true);
				int place = dock.getDock(listBoxDocks.getSelectedIndex()).addShip(ship);			
				if(place != -1){
					panelDock.repaint();
				}
			}
		});
		buttonParkingWar.setBounds(800, 102, 89, 23);
		frame.getContentPane().add(buttonParkingWar);
		
		JLabel labelTake = new JLabel("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		labelTake.setBounds(754, 149, 78, 14);
		frame.getContentPane().add(labelTake);
		
		JLabel labelPlace = new JLabel("\u041C\u0435\u0441\u0442\u043E");
		labelPlace.setBounds(716, 174, 46, 14);
		frame.getContentPane().add(labelPlace);
			
		
		textFieldPlace = new JTextField();
		textFieldPlace.setBounds(772, 174, 38, 20);
		frame.getContentPane().add(textFieldPlace);
		textFieldPlace.setColumns(10);
		
		panelTakeShip = new PanelShip();
		panelTakeShip.setBounds(660, 364, 224, 161);
		frame.getContentPane().add(panelTakeShip);
		
		JButton buttonTake = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		buttonTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldPlace.getText().equals("")) {
                    ITransport ship = dock.getDock(listBoxDocks.getSelectedIndex()).removeShip(Integer.parseInt(textFieldPlace.getText()));
                    if (ship != null) {
                        ship.SetPosition(5, 5, panelTakeShip.getWidth(), panelTakeShip.getHeight());
                        panelTakeShip.setShip(ship);
                        panelTakeShip.repaint();
                        panelDock.repaint();
                    } else {
                        panelTakeShip.setShip(null);
                        panelTakeShip.repaint();
                    }
                }
			}
		});
		buttonTake.setBounds(731, 221, 89, 23);
		frame.getContentPane().add(buttonTake);
		
		listBoxDocks = new JList(); 
		listBoxDocks.setBounds(660, 20, 118, 118); 
		frame.getContentPane().add(listBoxDocks);
		
		model = new DefaultListModel();
		for(int i = 0; i < countDocks; i++) {
			model.addElement("Док " + (i+1));
		}
		listBoxDocks.setModel(model); 
		listBoxDocks.setSelectedIndex(0); 
		panelDock.setListDocks(listBoxDocks);
		
		listBoxDocks.addListSelectionListener(new ListSelectionListener() { 
			@Override 
			public void valueChanged(ListSelectionEvent e) { 
				panelDock.repaint(); 
			} 
		});
		
	}
	private void RedrawUI() {
		panelDock.updateUI();
	}
}
