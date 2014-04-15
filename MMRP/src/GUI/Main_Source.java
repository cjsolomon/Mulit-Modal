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



public class Main_Source {

	private JFrame frmMmrp;


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
		frmMmrp.setBounds(100, 100, 941, 739);
		frmMmrp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				FormFactory.DEFAULT_ROWSPEC,}));
		final JButton btnShipments = new JButton("Shipments");
		btnShipments.setToolTipText("Click here to view shipment information");
		btnShipments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShipments.setMnemonic(KeyEvent.VK_S);
		frmMmrp.getContentPane().add(btnShipments, "2, 4, fill, fill");

		final JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreate.setEnabled(false);
		frmMmrp.getContentPane().add(btnCreate, "4, 2, fill, fill");

		final JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDelete.setEnabled(false);
		frmMmrp.getContentPane().add(btnDelete, "6, 2, fill, fill");

		final JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEdit.setEnabled(false);
		frmMmrp.getContentPane().add(btnEdit, "8, 2, fill, fill");

		final JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnImport.setEnabled(false);
		frmMmrp.getContentPane().add(btnImport, "10, 2, fill, fill");
		
		final VehiclePanel vp = new VehiclePanel();
		vp.setVisible(false);
		
		frmMmrp.getContentPane().add(vp, new CellConstraints().xywh(4,4,11,11));//, row, colSpan, rowSpan)"4,4,fill,fill");
		
		final JButton btnLocations = new JButton("Locations");
		frmMmrp.getContentPane().add(btnLocations, "2, 6,fill,fill");
						
		final JButton trucks = new JButton("Trucks");
		frmMmrp.getContentPane().add(trucks, "2, 8,fill,fill");
		final JButton truckType = new JButton("Truck Types");
		frmMmrp.getContentPane().add(truckType,"2,9,right,center");

		final JButton plane = new JButton("Planes");
		frmMmrp.getContentPane().add(plane, "2, 10,fill,fill");
		final JButton planeType = new JButton("Plane Types");
		frmMmrp.getContentPane().add(planeType,"2,11,right,center");

		final JButton rails = new JButton("Rails");
		frmMmrp.getContentPane().add(rails, "2, 12,fill,fill");
		final JButton railType = new JButton("Rail Types");
		frmMmrp.getContentPane().add(railType,"2,13,right,center");
		
		final JButton cargo = new JButton("Cargo Ships");
		final JButton cargoType = new JButton("CargoType");
		frmMmrp.getContentPane().add(cargo,"2,14,fill,fill");
		frmMmrp.getContentPane().add(cargoType,"2,15,right,center");
		
		final JButton btnCarriers = new JButton("Carriers");
		//btnVehicles.setToolTipText("Click here to view vehicle information");
		//btnVehicles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btnVehicles.setMnemonic(KeyEvent.VK_V);
		frmMmrp.getContentPane().add(btnCarriers, "2, 16, fill, fill");
		
		//btnVehicles.addActionListener( new ActionListener()
		//{
		  //  public void actionPerformed(ActionEvent e)
		    //{
		      //  Log.writeLogInfo("Clicked Vehicles, Disabling Vehicle Button - Enabling Top Menu Buttons");
		        //btnVehicles.setEnabled(false);
		        
		       // if (!btnShipments.isEnabled()) {
		        //	btnShipments.setEnabled(true);
		        //}
		        //vp.setVisible(true);
		        //unhide(btnCreate, btnImport, btnEdit,btnDelete);
				
				
				
		    //}
		//});
		
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
		
	//	btnShipments.addActionListener( new ActionListener()
		//{
		  //  public void actionPerformed(ActionEvent e)
		    //{
		     //   Log.writeLogInfo("Clicked Shipments, Disabling Shipment Button - Enabling Top Menu Buttons");
		       // btnShipments.setEnabled(false);
		        
		       // if (!btnVehicles.isEnabled()) {
		        //	btnVehicles.setEnabled(true);
		        //}
		        //unhide(btnCreate, btnImport, btnEdit,btnDelete);
		        
		        
		    //}
		//});
	}
	
	
	/**
	 * Function written to unhide the top buttons when a user has selecetd shipments or vehicles
	 * @param b1 - The first button to be unhidden
	 * @param b2 - The second button to be enabled
	 * @param b3 - The third button to be enabled
	 * @param b4 - The fourth button to be enabled
	 */
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
