package lab8;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class WinShip {

	private JFrame frame;
	private JPanel panel;
	private JButton buttonUp;
	private JButton buttonDown;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonCreateWar;
	private JButton buttonCreateCruiser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinShip window = new WinShip();
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

	public WinShip() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 901, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new PanelShip();
		panel.setBounds(10, 11, 864, 439);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		buttonDown = new JButton("↓");
		buttonDown.setBounds(735, 351, 62, 60);
		panel.add(buttonDown);
		buttonDown.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PanelShip.initialization) {
					PanelShip.ship.MoveTransport(Direction.Down);
				}
				RedrawUI();
			}
		});
		buttonDown.setIcon(null);
		buttonRight = new JButton("→");
		buttonRight.setBounds(802, 351, 62, 60);
		panel.add(buttonRight);
		buttonRight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PanelShip.initialization) {
					PanelShip.ship.MoveTransport(Direction.Right);
				}
				RedrawUI();
			}
		});
		buttonRight.setIcon(null);
		buttonLeft = new JButton("←");
		buttonLeft.setBounds(670, 351, 62, 60);
		panel.add(buttonLeft);
		buttonLeft.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PanelShip.initialization) {
					PanelShip.ship.MoveTransport(Direction.Left);
				}
				RedrawUI();
			}
		});
		buttonLeft.setIcon(null);
		buttonUp = new JButton("↑");
		buttonUp.setBounds(735, 286, 62, 60);
		panel.add(buttonUp);
		buttonUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PanelShip.initialization) {
					PanelShip.ship.MoveTransport(Direction.Up);
				}
				RedrawUI();
			}
		});
		buttonUp.setIcon(null);
		
		buttonCreateWar = new JButton("Создать военный корабль");
		buttonCreateWar.setBounds(10, 11, 198, 29);
		panel.add(buttonCreateWar);
		buttonCreateWar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rnd = new Random();
				PanelShip.ship = new WarShip(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.GRAY, Color.BLUE, true, true);
				PanelShip.initialization = true;
				PanelShip.ship.SetPosition(rnd.nextInt(90) + 40, rnd.nextInt(90) + 40, panel.getWidth(), panel.getHeight());
				RedrawUI();
			}
		});
		
		buttonCreateCruiser = new JButton("Создать крейсер");
		buttonCreateCruiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rnd = new Random();
				PanelShip.ship = new Cruiser(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.GRAY);
				PanelShip.initialization = true;
				PanelShip.ship.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, panel.getWidth(), panel.getHeight());
				RedrawUI();
			}
		});
		buttonCreateCruiser.setBounds(220, 11, 198, 29);
		panel.add(buttonCreateCruiser);
	}

	private void RedrawUI() {
		panel.updateUI();
	}
}
