package lab8;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class DialogConfig extends JDialog {

	ITransport ship = null;
	PanelConfig shipPanel;
	boolean succes;
	
	public DialogConfig(JFrame parent) {
		super(parent, true);
		initialize();
	}

	public boolean isSuccessful() {
        setVisible(true);
        return succes;
    }
	
	private void initialize() {
		this.getContentPane().setBackground(SystemColor.controlHighlight);
        this.setBounds(100, 100, 565, 418);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        JLabel labelSimpleShip = new JLabel("�������");
        labelSimpleShip.setHorizontalAlignment(SwingConstants.CENTER);
        labelSimpleShip.setBounds(10, 29, 133, 84);
        labelSimpleShip.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(labelSimpleShip);

        JLabel labelShip = new JLabel("������� �������");
        labelShip.setHorizontalAlignment(SwingConstants.CENTER);
        labelShip.setBounds(10, 135, 133, 84);
        labelShip.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(labelShip);

        JLabel labelMainColor = new JLabel("�������� ����");
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelMainColor.setBounds(211, 223, 133, 50);
        labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(labelMainColor);

        JLabel labelSecondColor = new JLabel("�������������� ����");
        labelSecondColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelSecondColor.setBounds(211, 286, 133, 50);
        labelSecondColor.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(labelSecondColor);

        shipPanel = new PanelConfig();
        shipPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        FlowLayout flowLayout = (FlowLayout) shipPanel.getLayout();
        shipPanel.setBounds(160, 29, 230, 178);
        this.getContentPane().add(shipPanel);

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent) e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }
        };

        labelSimpleShip.addMouseListener(ml);
        labelShip.addMouseListener(ml);
        labelShip.setTransferHandler(new TransferHandler("text"));
        labelSimpleShip.setTransferHandler(new TransferHandler("text"));

        shipPanel.setDropTarget(new DropTarget() {

            public void drop(DropTargetDropEvent e) {

                try {
                    for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                        if (e.getTransferable().getTransferData(df) == "�������") {
                            ship = new Cruiser(10, 10, Color.GRAY);
                            shipPanel.setShip(ship);
                            ship.SetPosition(25, 50, shipPanel.getWidth(), shipPanel.getHeight());
                        } else if (e.getTransferable().getTransferData(df) == "������� �������") {
                        	ship = new WarShip(30, 2, Color.GRAY, Color.BLUE, true, true);
                            shipPanel.setShip(ship);
                            ship.SetPosition(25, 50, shipPanel.getWidth(), shipPanel.getHeight());
                        }
                        shipPanel.repaint();
                    }
                } catch (Exception ex) {
                }

            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JPanel panelCyne = new JPanel();
        panelCyne.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelCyne.setName("cyan");
        panelCyne.setBackground(Color.CYAN);
        panelCyne.setBounds(458, 92, 50, 50);
        this.getContentPane().add(panelCyne);

        JPanel panelMagenta = new JPanel();
        panelMagenta.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelMagenta.setName("magenta");
        panelMagenta.setBackground(Color.MAGENTA);
        panelMagenta.setBounds(396, 29, 50, 50);
        this.getContentPane().add(panelMagenta);

        JPanel panelBlue = new JPanel();
        panelBlue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelBlue.setName("blue");
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(458, 29, 50, 50);
        this.getContentPane().add(panelBlue);

        JPanel panelPink = new JPanel();
        panelPink.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelPink.setName("pink");
        panelPink.setBackground(Color.PINK);
        panelPink.setBounds(396, 92, 50, 50);
        this.getContentPane().add(panelPink);

        JPanel panelGreen = new JPanel();
        panelGreen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelGreen.setName("green");
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(396, 223, 50, 50);
        this.getContentPane().add(panelGreen);

        JPanel panelYellow = new JPanel();
        panelYellow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelYellow.setName("yellow");
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBounds(396, 160, 50, 50);
        this.getContentPane().add(panelYellow);

        JPanel panelBlack = new JPanel();
        panelBlack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelBlack.setName("black");
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBounds(458, 223, 50, 50);
        this.getContentPane().add(panelBlack);

        JPanel panelOrange = new JPanel();
        panelOrange.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelOrange.setName("orange");
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBounds(458, 160, 50, 50);
        this.getContentPane().add(panelOrange);

        panelMagenta.addMouseListener(ml);
        panelMagenta.setTransferHandler(new TransferHandler("name"));

        panelBlue.addMouseListener(ml);
        panelBlue.setTransferHandler(new TransferHandler("name"));

        panelPink.addMouseListener(ml);
        panelPink.setTransferHandler(new TransferHandler("name"));

        panelYellow.addMouseListener(ml);
        panelYellow.setTransferHandler(new TransferHandler("name"));

        panelBlack.addMouseListener(ml);
        panelBlack.setTransferHandler(new TransferHandler("name"));

        panelOrange.addMouseListener(ml);
        panelOrange.setTransferHandler(new TransferHandler("name"));

        panelCyne.addMouseListener(ml);
        panelCyne.setTransferHandler(new TransferHandler("name"));

        panelGreen.addMouseListener(ml);
        panelGreen.setTransferHandler(new TransferHandler("name"));

        JButton btnAdd = new JButton("��������");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                succes = true;
                dispose();
            }
        });
        btnAdd.setBounds(29, 250, 106, 23);
        this.getContentPane().add(btnAdd);

        JButton btnCancell = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
        btnCancell.setBounds(29, 290, 106, 23);
        this.getContentPane().add(btnCancell);
        btnCancell.addActionListener((ActionEvent e) -> {
            succes = false;
            dispose();
        });

        labelMainColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (ship != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                        	ship.setMainColor(e.getTransferable().getTransferData(df).toString());
                        	shipPanel.setShip(ship);
                        	shipPanel.repaint();
                        }
                    } catch (Exception ex) {
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        labelSecondColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (ship != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            ((WarShip) ship).setDopColor(e.getTransferable().getTransferData(df).toString());
                            shipPanel.setShip(ship);
                            shipPanel.repaint();
                        }
                    } catch (Exception ex) {
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
	}
	
	public ITransport getShip() {
        return ship;
    }
}