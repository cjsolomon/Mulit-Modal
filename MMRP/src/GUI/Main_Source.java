package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;


import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import core.Location;
import GUI.ShipmentForms.ShipmentPanel;
import GUI.CargoForms.*;
import GUI.PlaneForms.*;
import GUI.RailForms.*;
import GUI.TruckForms.*;
import com.jgoodies.forms.layout.Sizes;


public class Main_Source {

	private JFrame frmMmrp;
	private LocationForm locationForm;
	private ShipmentPanel shpFrm;
	private TruckPanel truckForm;
	private PlanePanel planeForm;
	private RailPanel railForm;
	private CargoPanel cargoForm;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Source window = new Main_Source();
					window.frmMmrp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Log.createLogg();
				Log.writeLogInfo("Application Created");

			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Source() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMmrp = new JFrame();
		frmMmrp.setTitle("MMRP");
		frmMmrp.setBackground(new Color(255, 255, 255));
		frmMmrp.setBounds(100, 100, 841, 837);
		frmMmrp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shpFrm=new ShipmentPanel();
		shpFrm.setVisible(false);
		locationForm = new LocationForm();
		locationForm.setVisible(false);
		
		truckForm=new TruckPanel();
		truckForm.setVisible(false);
		railForm = new RailPanel();
		railForm.setVisible(false);
		planeForm = new PlanePanel();
		planeForm.setVisible(false);
		cargoForm = new CargoPanel();
		cargoForm.setVisible(false);
		/*
		 * Code defined here is for the shipments button
		 */
		frmMmrp.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("117px"),
				ColumnSpec.decode("17px"),
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("43dlu", false), Sizes.constant("40dlu", false)), 0),}));
		
		final JButton btnShipments = new JButton("Shipments");
		btnShipments.setToolTipText("Click here to view shipment information");
		btnShipments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShipments.setMnemonic(KeyEvent.VK_S);
		btnShipments.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				hidePanels();
				shpFrm.showPanel();
				
			}
		});
		frmMmrp.getContentPane().add(btnShipments, "2, 4, fill, fill");

		
		frmMmrp.getContentPane().add(shpFrm, "4, 4, 11, 19");//, row, colSpan, rowSpan)"4,4,fill,fill");
		frmMmrp.getContentPane().add(locationForm,new CellConstraints().xywh(4,4,11,11));
		frmMmrp.getContentPane().add(truckForm,new CellConstraints().xywh(4,4,11,11));
		frmMmrp.getContentPane().add(railForm,new CellConstraints().xywh(4,4,11,11));
		frmMmrp.getContentPane().add(planeForm,new CellConstraints().xywh(4,4,11,11));
		frmMmrp.getContentPane().add(cargoForm,new CellConstraints().xywh(4,4,11,11));
		final JButton btnLocations = new JButton("Locations");
		btnLocations.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hidePanels();
				locationForm.setVisible(true);
			}
		});
		frmMmrp.getContentPane().add(btnLocations, "2, 6,fill,fill");
		
						
		final JButton trucks = new JButton("Trucks");
		trucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button Clicked");
				hidePanels();
				System.out.println("Hide Panels");
				truckForm.showPanel();
			}
		});
		frmMmrp.getContentPane().add(trucks, "2, 8,fill,fill");
		final JButton truckType = new JButton("Truck Types");
		frmMmrp.getContentPane().add(truckType,"2,9,right,center");

		final JButton plane = new JButton("Planes");
		plane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				planeForm.showPanel();
			}
		});
		frmMmrp.getContentPane().add(plane, "2, 10,fill,fill");
		final JButton planeType = new JButton("Plane Types");
		frmMmrp.getContentPane().add(planeType,"2,11,right,center");

		final JButton rails = new JButton("Rails");
		rails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				railForm.showPanel();
			}
		});
		frmMmrp.getContentPane().add(rails, "2, 12,fill,fill");
		final JButton railType = new JButton("Rail Types");
		frmMmrp.getContentPane().add(railType,"2,13,right,center");
		
		final JButton cargo = new JButton("Cargo Ships");
		cargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				cargoForm.showPanel();
			}
		});
		final JButton cargoType = new JButton("CargoType");
		frmMmrp.getContentPane().add(cargo,"2,14,fill,fill");
		frmMmrp.getContentPane().add(cargoType,"2,15,right,center");
		
		final JButton btnCarriers = new JButton("Carriers");
		frmMmrp.getContentPane().add(btnCarriers, "2, 16, fill, fill");
		
		
				final JButton btnExit = new JButton("Exit");
				btnExit.setToolTipText("Exit MMRP");
				frmMmrp.getContentPane().add(btnExit, "2, 18, fill, fill");
				btnExit.setMnemonic(KeyEvent.VK_E);
				btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				btnExit.addActionListener( new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						Log.writeLogSevere("User selected to Exit Program");
						System.exit(0);
						
					}
				});
		
	}
	
	
	/**
	 * Function written to unhide the top buttons when a user has selecetd shipments or vehicles
	 * @param b1 - The first button to be unhidden
	 * @param b2 - The second button to be enabled
	 * @param b3 - The third button to be enabled
	 * @param b4 - The fourth button to be enabled
	 */
	
	private void hidePanels()
	{
		shpFrm.setVisible(false);
		this.locationForm.setVisible(false);
		truckForm.setVisible(false);
		railForm.setVisible(false);
		cargoForm.setVisible(false);
		planeForm.setVisible(false);
	}
	
	private static void unhide(JButton b1, JButton b2, JButton b3, JButton b4) {
		Log.writeLogInfo("Unhiding Top Menu Buttons");
		if (!b1.isEnabled())
			b1.setEnabled(true);
		if (!b2.isEnabled())
			b2.setEnabled(true);
		if (!b3.isEnabled())
			b3.setEnabled(true);
		if (!b4.isEnabled())
			b4.setEnabled(true);
	}
	
}
