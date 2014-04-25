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

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.Border;
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
import GUI.CargoForms.CargoTypePanel;
import GUI.PlaneForms.*;
import GUI.PlaneForms.PlaneTypePanel;
import GUI.RailForms.*;
import GUI.TruckForms.*;
import GUI.TruckForms.TruckTypePanel;

import com.jgoodies.forms.layout.Sizes;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class Main_Source {

	private JFrame frmMmrp;
	private LocationForm locationForm;
	private ShipmentPanel shpFrm;
	private TruckPanel truckForm;
	private PlanePanel planeForm;
	private RailPanel railForm;
	private CargoPanel cargoForm;
	private JButton btnShipments,btnCargo,btnTruck,btnRail,btnPlane,btnLocations,btnCarriers,btnTruckType, btnRailType, btnCargoType, btnPlaneType;
	private JLabel lblFormLabel;
	private TruckTypePanel ttp;
	private PlaneTypePanel ptp;
	private CargoTypePanel ctp;
	private RailTypePanel rtp;
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
		frmMmrp.setBounds(100, 100, 1163, 837);
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
		ttp = new TruckTypePanel();
		ttp.setVisible(false);
		ptp = new PlaneTypePanel();
		ptp.setVisible(false);
		rtp = new RailTypePanel();
		rtp.setVisible(false);
		ctp = new CargoTypePanel();
		ctp.setVisible(false);
		/*
		 * Code defined here is for the shipments button
		 */
		frmMmrp.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("117px"),
				ColumnSpec.decode("17px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				ColumnSpec.decode("362px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("31dlu", false), Sizes.constant("40dlu", false)), 0),}));
		
		btnShipments = new JButton("Shipments");
		btnShipments.setToolTipText("Click here to view shipment information");
		btnShipments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShipments.setMnemonic(KeyEvent.VK_S);
		btnShipments.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				hidePanels();
				shpFrm.showPanel();
				enableButtons();
				btnShipments.setEnabled(false);
				lblFormLabel.setText("Shipments");
				
			}
		});
		
		lblFormLabel = new JLabel("");
	
	
		
		lblFormLabel.setForeground(Color.BLACK);
		lblFormLabel.setFont(new Font("Calibri", Font.BOLD, 23));
		frmMmrp.getContentPane().add(lblFormLabel, "6, 2, 3, 2");
		frmMmrp.getContentPane().add(btnShipments, "2, 4, fill, fill");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.foreground"));
		separator.setOrientation(SwingConstants.VERTICAL);
		frmMmrp.getContentPane().add(separator, "5, 1, 1, 30");

		
		frmMmrp.getContentPane().add(shpFrm, "6, 4, 11, 27");//, row, colSpan, rowSpan)"4,4,fill,fill");
		frmMmrp.getContentPane().add(locationForm,"6, 4, 11, 27");
		frmMmrp.getContentPane().add(truckForm,"6, 4, 11, 17");
		frmMmrp.getContentPane().add(railForm,"6, 4, 11, 17");
		frmMmrp.getContentPane().add(planeForm,"6, 4, 11, 17");
		frmMmrp.getContentPane().add(cargoForm,"6, 4, 11, 17");
		frmMmrp.getContentPane().add(ttp,"6,4,11,17");
		frmMmrp.getContentPane().add(rtp,"6,4,11,17");
		frmMmrp.getContentPane().add(ctp,"6,4,11,17");
		frmMmrp.getContentPane().add(ptp,"6,4,11,17");
		btnLocations = new JButton("Locations");
		btnLocations.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hidePanels();
				locationForm.setVisible(true);
				enableButtons();
				btnLocations.setEnabled(false);
				lblFormLabel.setText("Locations");
			}
		});
		frmMmrp.getContentPane().add(btnLocations, "2, 6,fill,fill");
		
						
		btnTruck = new JButton("Trucks");
		btnTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button Clicked");
				hidePanels();
				System.out.println("Hide Panels");
				truckForm.showPanel();
				enableButtons();
				btnTruck.setEnabled(false);
				lblFormLabel.setText("Trucks");
			}
		});
		frmMmrp.getContentPane().add(btnTruck, "2, 8,fill,fill");
		btnTruckType = new JButton("Truck Types");
		btnTruckType.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e)
			 {
				 hidePanels();
				 ttp.showPanel();
				 enableButtons();
				 btnTruckType.setEnabled(false);
				 lblFormLabel.setText("Truck Types");
			 }
		});
		frmMmrp.getContentPane().add(btnTruckType,"2, 10, right, center");

		btnPlane = new JButton("Planes");
		btnPlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				planeForm.showPanel();
				enableButtons();
				btnPlane.setEnabled(false);
				lblFormLabel.setText("Planes");
			}
		});
		frmMmrp.getContentPane().add(btnPlane, "2, 12, fill, fill");
		btnPlaneType = new JButton("Plane Types");
		btnPlaneType.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e)
			 {
				 hidePanels();
				 ptp.showPanel();
				 enableButtons();
				 btnPlaneType.setEnabled(false);
				 lblFormLabel.setText("Plane Types");
			 }
		});
		frmMmrp.getContentPane().add(btnPlaneType,"2, 14, right, center");

		btnRail = new JButton("Rails");
		btnRail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				railForm.showPanel();
				enableButtons();
				btnRail.setEnabled(false);
				lblFormLabel.setText("Rails");
			}
		});
		frmMmrp.getContentPane().add(btnRail, "2, 16, fill, fill");
		btnRailType = new JButton("Rail Types");
		btnRailType.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e)
			 {
				 hidePanels();
				 rtp.showPanel();
				 enableButtons();
				 btnRailType.setEnabled(false);
				 lblFormLabel.setText("Rail Types");
			 }
		});
		frmMmrp.getContentPane().add(btnRailType,"2, 18, right, center");
		
		btnCargo = new JButton("Cargo Ships");
		btnCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				cargoForm.showPanel();
				enableButtons();
				btnCargo.setEnabled(false);
				lblFormLabel.setText("Cargo");
			}
		});
		btnCargoType = new JButton("CargoType");
		frmMmrp.getContentPane().add(btnCargo,"2, 20, fill, fill");
		btnCargoType.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e)
			 {
				 hidePanels();
				 ctp.showPanel();
				 enableButtons();
				 btnCargoType.setEnabled(false);
				 lblFormLabel.setText("Cargo Types");
			 }
		});
		frmMmrp.getContentPane().add(btnCargoType,"2, 22, right, center");
		
		btnCarriers = new JButton("Carriers");
		frmMmrp.getContentPane().add(btnCarriers, "2, 24, fill, fill");
		
		
				final JButton btnExit = new JButton("Exit");
				btnExit.setToolTipText("Exit MMRP");
				frmMmrp.getContentPane().add(btnExit, "2, 26, fill, fill");
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
		ttp.setVisible(false);
		rtp.setVisible(false);
		ctp.setVisible(false);
		ptp.setVisible(false);
	}
	public void enableButtons()
	{
		this.btnCargo.setEnabled(true);
		this.btnCarriers.setEnabled(true);
		this.btnLocations.setEnabled(true);
		this.btnPlane.setEnabled(true);
		this.btnRail.setEnabled(true);
		this.btnShipments.setEnabled(true);
		this.btnTruck.setEnabled(true);
		this.btnPlaneType.setEnabled(true);
		this.btnRailType.setEnabled(true);
		this.btnCargoType.setEnabled(true);
		this.btnTruckType.setEnabled(true);
		
		
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
