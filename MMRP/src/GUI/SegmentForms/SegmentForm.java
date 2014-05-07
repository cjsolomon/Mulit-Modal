package GUI.SegmentForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.TableRefreshListener;
import GUI.TravelTypeSetListener;


import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.BaseClass;
import core.Cargo;
import core.Location;
import core.Plane;
import core.Rail;
import core.Segment;
import core.Shipper;
import core.TravelType;
import core.Truck;
import core.Vehicle;
import core.Vehicle.TravelModes;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.Sizes;

public class SegmentForm extends JPanel {
	
	JLabel lblStartCity,lblStartState,lblStartCountry, lblStartLocation;
	JLabel lblEndCity,lblEndState,lblEndCountry, lblEndLocation;
	JComboBox<String> cmbToCountries, cmbToStates, cmbToCities;
	JComboBox<String> cmbFromCities, cmbFromStates, cmbFromCountries, cbTravelModes;
	private ComboItem[] vehicles;
	private ComboItem[] types;
	private boolean newSegment;
	private Segment source;
	private JLabel lblSegmentID;
	private JTextField txtSegmentID;
	private JLabel lblVehicleID;
	private JComboBox cbVehicleID;
	private JLabel lblMode;
	private JLabel lblDistance;
	private JTextField txtDistance;
	private JLabel lblLane;
	private JLabel lblRateid;
	private JLabel lblActualCapacity;
	private JTextField txtLane;
	private JTextField txtRateID;
	private JTextField txtActualCapacity;
	private JLabel lblLatestDeparture;
	private JLabel lblEarliestDeparture;
	private JLabel lblEstDeparture;
	private JLabel lblEstArrival;
	private JLabel lblEarliestArrival;
	private JLabel lblLatestArrival;
	private JTextField txtEstDeparture;
	private JTextField txtEarlDeparture;
	private JTextField txtLatDeparture;
	private JTextField txtEstArrival;
	private JTextField txtEarlArrival;
	private JTextField txtLatArrival;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnCancel;
	private JLabel lblTravelType;
	private JComboBox cbTravelType;
	private JLabel lblSegments;
	private ArrayList<TableRefreshListener> refresh = new ArrayList<TableRefreshListener>(); 
	private JButton btnGoTo;
	private JButton btnGoToType;

	public SegmentForm(final GUI.Main_Source main) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(58dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("64dlu", true), Sizes.constant("70dlu", true)), 0),
				ColumnSpec.decode("max(53dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(49dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		lblStartCity = new JLabel("City:");
		lblStartState = new JLabel("State:");
		lblStartCountry = new JLabel("Country:");
		lblEndCity = new JLabel("City:");
		lblEndState = new JLabel("State:");
		lblEndCountry = new JLabel("Country:");
		
		
		cmbFromCountries = new JComboBox<String>();
		cmbFromCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbFromStates.removeAllItems();
				if(cmbFromCountries.getSelectedIndex()!=-1)
				{
					loadStates(cmbFromCountries,cmbFromStates);
				}
			}
		});
		
		cmbToCountries = new JComboBox<String>();
		cmbToCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				cmbToStates.removeAllItems();
				if(cmbToCountries.getSelectedIndex()!=-1)
				loadStates(cmbToCountries,cmbToStates);
			}
		});

		cmbFromStates= new JComboBox<String> ();
		cmbFromStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbFromCities.removeAll();
				if(cmbFromStates.getSelectedIndex()!=-1)
				loadCities(cmbFromCountries,cmbFromStates,cmbFromCities);
			}
		});
		cmbToStates = new JComboBox<String>();
		cmbToStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbToCities.removeAll();
				if(cmbToStates.getSelectedIndex()!=-1)
				loadCities(cmbToCountries,cmbToStates,cmbToCities);
			}
		});
		cmbFromCities = new JComboBox<String> ();
		cmbToCities = new JComboBox<String>();
		
		lblSegments = new JLabel("Segments");
		lblSegments.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSegments, "4, 2");
		
		lblSegmentID = new JLabel("SegmentID");
		add(lblSegmentID, "2, 4, right, center");
		
		txtSegmentID = new JTextField();
		txtSegmentID.setEditable(false);
		add(txtSegmentID, "4, 4, left, default");
		txtSegmentID.setColumns(10);
		
		lblEstDeparture = new JLabel("Est. Departure");
		add(lblEstDeparture, "9, 4, right, default");
		
		txtEstDeparture = new JTextField();
		txtEstDeparture.setEditable(false);
		add(txtEstDeparture, "11, 4, fill, default");
		txtEstDeparture.setColumns(10);
		
		lblMode = new JLabel("Mode");
		add(lblMode, "2, 6, right, default");
		cbTravelModes = new JComboBox();
		add(cbTravelModes, "4, 6, fill, top");
		
		
		
		lblEarliestDeparture = new JLabel("Earliest Departure");
		add(lblEarliestDeparture, "9, 6, right, default");
		
		txtEarlDeparture = new JTextField();
		txtEarlDeparture.setEditable(false);
		add(txtEarlDeparture, "11, 6, fill, default");
		txtEarlDeparture.setColumns(10);
		
		lblVehicleID = new JLabel("Vehicle");
		add(lblVehicleID, "2, 8, right, default");
		
		cbVehicleID = new JComboBox();
		cbVehicleID.setEditable(false);
		add(cbVehicleID, "4, 8, fill, default");
		
		btnGoTo = new JButton("Go To");
		btnGoTo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(cbVehicleID.getSelectedIndex()!=-1)
				{
					main.setVehicleID(((ComboItem)cbVehicleID.getSelectedItem()).getID());
					String type = cbTravelModes.getSelectedItem().toString();
					if(type.equals(Vehicle.TravelModes.TRUCK.toString()))
			          {
			          	main.getTruckButton().doClick();
			          }
			          else
			          {
			          	
			          	if(type.equals(Vehicle.TravelModes.RAIL.toString()))
			          	{
			          		main.getRailButton().doClick();
			          	}
			          	else
			          	{
			          		
			          		if(type.equals(Vehicle.TravelModes.CARGO.toString()))
			          		{
			          			main.getCargoButton().doClick();
			          		}
			          		else
			          		{
			          			if(type.equals(Vehicle.TravelModes.PLANE.toString()))
			          			{
			          				main.getPlaneButton().doClick();
			          			}
			          		}
			          	}
			          	
			          }
					
					
					
				}
			}
		});
		add(btnGoTo, "5, 8");
		
		lblLatestDeparture = new JLabel("Latest Departure");
		add(lblLatestDeparture, "9, 8, right, default");
		
		txtLatDeparture = new JTextField();
		txtLatDeparture.setEditable(false);
		add(txtLatDeparture, "11, 8, fill, default");
		txtLatDeparture.setColumns(10);
		
		lblTravelType = new JLabel("Travel Type");
		add(lblTravelType, "2, 10, right, default");
		
		cbTravelType = new JComboBox();
		add(cbTravelType, "4, 10, fill, default");
		
		btnGoToType = new JButton("Go To Type");
		btnGoToType.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(cbTravelType.getSelectedIndex()!=-1)
				{
					main.setTravelTypeID(((ComboItem)cbTravelType.getSelectedItem()).getID());
					String type = cbTravelModes.getSelectedItem().toString();
					if(type.equals(Vehicle.TravelModes.TRUCK.toString()))
			          {
			          	main.getTruckTravelTypeButton().doClick();
			          }
			          else
			          {
			          	
			          	if(type.equals(Vehicle.TravelModes.RAIL.toString()))
			          	{
			          		main.getRailTravelTypeButton().doClick();
			          	}
			          	else
			          	{
			          		
			          		if(type.equals(Vehicle.TravelModes.CARGO.toString()))
			          		{
			          			main.getCargoTravelTypeButton().doClick();
			          		}
			          		else
			          		{
			          			if(type.equals(Vehicle.TravelModes.PLANE.toString()))
			          			{
			          				main.getPlaneTravelTypeButton().doClick();
			          			}
			          		}
			          	}
			          	
			          }
					
					
					
				}
			}
		});
		add(btnGoToType, "5, 10");
		
		lblEstArrival = new JLabel("Est. Arrival");
		add(lblEstArrival, "9, 10, right, default");
		
		txtEstArrival = new JTextField();
		txtEstArrival.setEditable(false);
		add(txtEstArrival, "11, 10, fill, default");
		txtEstArrival.setColumns(10);
		
		lblDistance = new JLabel("Distance");
		add(lblDistance, "2, 12, right, default");
		
		txtDistance = new JTextField();
		txtDistance.setEditable(false);
		add(txtDistance, "4, 12, fill, default");
		txtDistance.setColumns(10);
		
		lblEarliestArrival = new JLabel("Earliest Arrival");
		add(lblEarliestArrival, "9, 12, right, default");
		
		txtEarlArrival = new JTextField();
		txtEarlArrival.setEditable(false);
		add(txtEarlArrival, "11, 12, fill, default");
		txtEarlArrival.setColumns(10);
		
		lblLane = new JLabel("Lane");
		add(lblLane, "2, 14, right, default");
		
		txtLane = new JTextField();
		txtLane.setEditable(false);
		add(txtLane, "4, 14, fill, default");
		txtLane.setColumns(10);
		
		lblLatestArrival = new JLabel("Latest Arrival");
		add(lblLatestArrival, "9, 14, right, default");
		
		txtLatArrival = new JTextField();
		txtLatArrival.setEditable(false);
		add(txtLatArrival, "11, 14, fill, default");
		txtLatArrival.setColumns(10);
		
		lblRateid = new JLabel("Shipping Rate");
		add(lblRateid, "2, 16, right, default");
		
		txtRateID = new JTextField();
		txtRateID.setEditable(false);
		add(txtRateID, "4, 16, fill, default");
		txtRateID.setColumns(10);
		
		lblActualCapacity = new JLabel("Actual Capacity");
		add(lblActualCapacity, "2, 18, right, default");
		
		txtActualCapacity = new JTextField();
		txtActualCapacity.setEditable(false);
		add(txtActualCapacity, "4, 18, fill, default");
		txtActualCapacity.setColumns(10);
		lblStartLocation = new JLabel("Start Location");

		add(lblStartLocation, "4, 20, center, center");
		lblEndLocation = new JLabel("End Location");
		
		add(lblEndLocation, "11, 20, center, center");
		add(this.cmbFromCountries, "4, 22, fill, top");
		
		add(this.cmbToCountries, "11, 22, fill, top");
		add(this.cmbFromStates, "4, 24, fill, top");
		add(cmbToStates, "11, 24, fill, top");
		add(lblStartCity,"2, 26, right, center");
		add(lblStartState,"2, 24, right, center");
		add(lblStartCountry,"2, 22, right, center");
		add(cmbFromCities, "4, 26, fill, top");
		add(lblEndCity,"9, 26, right, center");
		add(lblEndState,"9, 24, right, center");
		add(lblEndCountry,"9, 22, right, center");
		add(cmbToCities, "11, 26, fill, top");

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(source==null)
				{
					update();
					for(TableRefreshListener t : refresh) t.refreshTable();
				}
				else
					update();
				btnSave.setVisible(false);
				setReadOnly();
				btnEdit.setVisible(true);
				newSegment=false;
			}
		});
		add(btnSave, "9, 28");
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnSave.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		add(btnEdit, "9, 28");
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSave.isVisible()&& source!=null)
				{
					setReadOnly();
					btnSave.setVisible(false);
					btnEdit.setVisible(true);
					
				}
				else
				{
					setVisible(false);
				}
			}
		});
		add(btnCancel, "11, 28");
    	
    	
		
		this.cbTravelModes.addItem("Select...");
		for(TravelModes t : Vehicle.TravelModes.values())
			cbTravelModes.addItem(t.toString());
    	
		cbTravelModes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbToCountries.removeAllItems();
				cmbFromCountries.removeAllItems();
				if(cbTravelModes.getSelectedIndex()>0)
				{
					
					loadCountries();
					String mode = cbTravelModes.getSelectedItem().toString();
					ArrayList<Vehicle> temp = new ArrayList<Vehicle>();
					cbVehicleID.removeAllItems();
					loadVehicles(mode);
					cbVehicleID.setSelectedIndex(-1);
				}
				
			}
		});
		
		this.cbVehicleID.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				if(cbVehicleID.getSelectedIndex()!=-1)
				{
					cbTravelType.removeAllItems();
					loadTypes();
					cbTravelType.setSelectedIndex(-1);
					
				}
				
			}
		});

    	//Tool tips
		this.btnCancel.setToolTipText("Click to cancel any changes");
		this.btnSave.setToolTipText("Click here to create a new Segment");
		this.cbTravelModes.setToolTipText("This is the mode of travel over this Segment");
		this.cbTravelType.setToolTipText("This is the travel type used over the Segment");
		this.cbVehicleID.setToolTipText("This is the Vehicle being used over the Segment");
		this.cmbFromCities.setToolTipText("This is the starting city");
		this.cmbFromCountries.setToolTipText("This is the starting country");
		this.cmbFromStates.setToolTipText("This is the starting state");
		this.cmbToCities.setToolTipText("This is the ending city");
		this.cmbToCountries.setToolTipText("This is the ending country");
		this.cmbToStates.setToolTipText("This is the ending state");
		this.txtActualCapacity.setToolTipText("This is the capacity that the vehicle on this Segment is currently filled");
		this.txtDistance.setToolTipText("This is the distance between the starting and ending Locations");
		this.txtEarlArrival.setToolTipText("Currently not supported");
		this.txtEstArrival.setToolTipText("This is the appoximate time the vehicle will arrive at the destination");
		this.txtEstDeparture.setToolTipText("This is approximate time the vehicle will leave the starting location");
		this.txtLane.setToolTipText("Currently not supported");
		this.txtLatArrival.setToolTipText("Currently not supported");
		this.txtLatDeparture.setToolTipText("Currently not supported");
		this.txtRateID.setToolTipText("This is the Shipping Rate being used over this Segment");
	
		
	}//End of LocationForm Constructor
	
	protected void loadStates(JComboBox sourceCountry,JComboBox sourceStates)
	{
		if(sourceStates!=null && sourceCountry.getSelectedIndex()!=-1)
		{
			sourceStates.removeAllItems();
			String mode = this.cbTravelModes.getSelectedItem().toString();
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct State from location where Country = '" + sourceCountry.getSelectedItem().toString()+"' and (TravelType1='"+mode+"' or TravelType2='"+mode+"' or TravelType3='"+mode+
					"' or TravelType4='"+mode+"' or TravelType5='"+mode+"' or TravelType6='"+mode+"')");
				for(Map m :tmp)
				{
					sourceStates.addItem(m.get("State").toString());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			sourceStates.setSelectedIndex(-1);
		}
	}
	protected void loadCountries()
	{
		String mode = this.cbTravelModes.getSelectedItem().toString();
		try
		{
			ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Country from location where TravelType1='"+mode+"' or TravelType2='"+mode+"' or TravelType3='"+mode+
					"' or TravelType4='"+mode+"' or TravelType5='"+mode+"' or TravelType6='"+mode+"'");
			for(Map m :tmp)
			{
				this.cmbFromCountries.addItem(m.get("Country").toString());
				this.cmbToCountries.addItem(m.get("Country").toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		cmbFromCountries.setSelectedIndex(-1);
		cmbToCountries.setSelectedIndex(-1);
		
	}
	protected void loadCities(JComboBox sourceCountry,JComboBox sourceStates,JComboBox sourceCities)
	{
		String mode = cbTravelModes.getSelectedItem().toString();
		if(sourceCities!=null && sourceStates.getSelectedIndex()!=-1)
		{
			sourceCities.removeAllItems();
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Name from location where Country = '" + sourceCountry.getSelectedItem().toString()+"' and State = '" + sourceStates.getSelectedItem().toString()+"' and (TravelType1='"+mode+"' or TravelType2='"+mode+"' or TravelType3='"+mode+
						"' or TravelType4='"+mode+"' or TravelType5='"+mode+"' or TravelType6='"+mode+"')");
				for(Map m :tmp)
				{
					sourceCities.addItem(m.get("Name").toString());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		sourceCities.setSelectedIndex(-1);

	}
	
	
	public void loadTravelModes(Location thisLocation){

		cbTravelModes.removeAllItems();
		ArrayList<Vehicle.TravelModes> theseTravelModes = new ArrayList<Vehicle.TravelModes>();
		theseTravelModes = thisLocation.getTravelModes();
    	for(int i = 0; i < theseTravelModes.size(); i++){
    		if( !(theseTravelModes.get(i) == null) && !(theseTravelModes.get(i).toString() == "NONE"))
    			cbTravelModes.addItem(theseTravelModes.get(i).toString());
    	}
    	if(cbTravelModes.getItemCount() == 0)
    		cbTravelModes.addItem(Vehicle.TravelModes.NONE.toString());
    	
	}//End of loadTravelModes()
	
	public void showPanel()
	{
		newSegment=true;
		source=null;
		setEditable();
		setNew();
		btnSave.setVisible(true);
		btnEdit.setVisible(false);
		this.setVisible(true);
	}

	public void showPanel(Segment s)
	{
		newSegment=false;
		source=s;
		setSegment();
		setReadOnly();
		btnSave.setVisible(false);
		btnEdit.setVisible(true);
		setVisible(true);
	}
	
	public void setNew()
	{
		this.txtSegmentID.setText("");

		cbTravelModes.setSelectedIndex(-1);

		this.cbVehicleID.setSelectedIndex(-1);
		
		this.cbTravelType.setSelectedIndex(-1);
		
		this.txtDistance.setText("");
		this.txtLane.setText("");
		this.txtRateID.setText("");
		this.txtActualCapacity.setText("");
		this.txtEstArrival.setText("");
		this.txtEarlArrival.setText("");
		this.txtLatArrival.setText("");
		this.txtEstDeparture.setText("");
		this.txtEarlDeparture.setText("");
		this.txtLatDeparture.setText("");
		
		this.cmbFromCountries.removeAll();
		this.cmbFromStates.removeAll();
		this.cmbFromCities.removeAll();
		
		
		this.cmbToCountries.removeAll();

		this.cmbToStates.removeAll();

		this.cmbToCities.removeAll();
	
	}
	public void setSegment(){
		if(source!=null)
		{
			
			
			
			this.txtSegmentID.setText(String.valueOf(source.getID()));
			//this.cbTravelModes.setSelectedItem(loadSegment.getTravelType().getTravelTypeMode());
			TravelType t = source.getTravelType();

			cbTravelModes.setSelectedItem(t.getTravelTypeMode());
			
			
			
			Vehicle v = source.getVehicle();
			ComboItem temp = new ComboItem();
			temp.setID(v.getId());
			temp.setName(v.getVehicleName());
			this.cbVehicleID.setSelectedItem(temp);
			
			

			TravelType tt = source.getTravelType();
			temp = new ComboItem();
			temp.setID(tt.getVehicleTypeID());
			temp.setName(tt.getTravelTypeName());
			this.cbTravelType.setSelectedItem(temp);
			
			this.txtDistance.setText(String.valueOf(source.getDistance()));
			this.txtLane.setText(source.getLane());
			//this.txtRateID.setText(String.valueOf(source.getShippingRate().getId() + " - Flat Rate : " + source.getShippingRate().getFlatRate()));
			this.txtActualCapacity.setText(String.valueOf(source.getActualCapacity()));
			this.txtEstArrival.setText(String.valueOf(source.getEstimatedArrivalTime()));
			this.txtEarlArrival.setText(String.valueOf(source.getEarliestArrivalTime()));
			this.txtLatArrival.setText(String.valueOf(source.getLatestArrivalTime()));
			this.txtEstDeparture.setText(String.valueOf(source.getEstimatedDepartureTime()));
			this.txtEarlDeparture.setText(String.valueOf(source.getEarliestDepartureTime()));
			this.txtLatDeparture.setText(String.valueOf(source.getLatestDepartureTime()));
			
			this.cmbFromCountries.setSelectedItem(source.getStartLocation().getCountry());
			this.cmbFromStates.setSelectedItem(source.getStartLocation().getState());
			this.cmbFromCities.setSelectedItem(source.getStartLocation().getName());
			
			
			this.cmbToCountries.setSelectedItem(source.getEndLocation().getCountry());

			this.cmbToStates.setSelectedItem(source.getEndLocation().getState());
	
			this.cmbToCities.setSelectedItem(source.getEndLocation().getName());
		}
	}
	
	public void setReadOnly(){
		this.txtSegmentID.setEditable(false);
		this.cbTravelModes.setEnabled(false);
		this.cbVehicleID.setEnabled(false);
		this.cbTravelType.setEnabled(false);
		this.txtDistance.setEditable(false);
		this.txtLane.setEditable(false);
		this.txtRateID.setEditable(false);
		this.txtActualCapacity.setEditable(false);
		this.txtEstArrival.setEditable(false);
		this.txtEarlArrival.setEditable(false);
		this.txtLatArrival.setEditable(false);
		this.txtEstDeparture.setEditable(false);
		this.txtEarlDeparture.setEditable(false);
		this.txtLatArrival.setEditable(false);
		
		this.cmbFromCountries.setEnabled(false);
		this.cmbFromStates.setEnabled(false);
		this.cmbFromCities.setEnabled(false);
		
		this.cmbToCountries.setEnabled(false);
		this.cmbToStates.setEnabled(false);
		this.cmbToCities.setEnabled(false);
		
	}
	
	public void setEditable(){
		this.cbTravelModes.setEnabled(true);
		this.cbVehicleID.setEnabled(true);
		this.cbTravelType.setEnabled(true);
		this.txtDistance.setEditable(true);
		this.txtLane.setEditable(true);
		this.txtRateID.setEditable(true);
		this.txtActualCapacity.setEditable(true);
		this.txtEstArrival.setEditable(true);
		this.txtEarlArrival.setEditable(true);
		this.txtLatArrival.setEditable(true);
		this.txtEstDeparture.setEditable(true);
		this.txtEarlDeparture.setEditable(true);
		this.txtLatArrival.setEditable(true);
		
		this.cmbFromCountries.setEnabled(true);
		this.cmbFromStates.setEnabled(true);
		this.cmbFromCities.setEnabled(true);
		
		this.cmbToCountries.setEnabled(true);
		this.cmbToStates.setEnabled(true);
		this.cmbToCities.setEnabled(true);
		
	}
	private void update()
	{
		if(source==null) source=new Segment();
		source.setActualCapacity(Double.parseDouble(this.txtActualCapacity.getText()));
		source.setDistance(Double.parseDouble(txtDistance.getText()));
		ArrayList<Map<String,Object>> location = new ArrayList<Map<String,Object>>();
		try
		{
		location = BaseClass.executeQuery("Select * from Location where Name = '"+ this.cmbFromCities.getSelectedItem().toString() +"' AND State = '" +
		this.cmbFromStates.getSelectedItem().toString() + "' AND Country = '" +this.cmbFromCountries.getSelectedItem().toString()+ "'");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		source.setStartLocation(Integer.parseInt(location.get(0).get("LocationID").toString()));
		try
		{
		location = BaseClass.executeQuery("Select * from Location where Name = '"+ this.cmbToCities.getSelectedItem().toString() +"' AND State = '" +
		this.cmbToStates.getSelectedItem().toString() + "' AND Country = '" +this.cmbToCountries.getSelectedItem().toString()+ "'");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		source.setEndLocation(Integer.parseInt(location.get(0).get("LocationID").toString()));
		
		source.setLane(this.txtLane.getText());
		source.setMode(this.cbTravelModes.getSelectedItem().toString());
		source.setTravelType(TravelType.Load(((ComboItem)this.cbTravelType.getSelectedItem()).getID()));
		source.setVehicle(((ComboItem)this.cbVehicleID.getSelectedItem()).getID(), Vehicle.loadMode(cbTravelModes.getSelectedItem().toString()));
		
		source.Update();
		
		this.txtSegmentID.setText(String.valueOf(source.getID()));
	}
	protected void loadVehicles(String type)
	{			
		String table="";
		String namePrefix="";
		  if(type.equals(Vehicle.TravelModes.TRUCK.toString()))
          {
          	table ="Truck";
          	namePrefix="Truck";
          }
          else
          {
          	
          	if(type.equals(Vehicle.TravelModes.RAIL.toString()))
          	{
          		table ="rail";
              	namePrefix="Rail";
          	}
          	else
          	{
          		
          		if(type.equals(Vehicle.TravelModes.CARGO.toString()))
          		{
          			table ="CargoShip";
                  	namePrefix="Ship";
          		}
          		else
          		{
          			if(type.equals(Vehicle.TravelModes.PLANE.toString()))
          			{
          				table ="Plane";
          	          	namePrefix="Plane";
          			}
          		}
          	}
          	
          }
		try
		{
			ArrayList<Map<String,Object>> data  = BaseClass.executeQuery("Select " + namePrefix+"ID as ID, " + namePrefix +"Name as Name from "+ table);
			vehicles = new ComboItem[data.size()];
			for(int i = 0; i<data.size();i++)
			{
				ComboItem temp = new ComboItem();
				temp.setID((Integer)data.get(i).get("ID"));
				temp.setName(data.get(i).get("Name").toString());
				vehicles[i]=temp;
				cbVehicleID.addItem(vehicles[i]);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private void loadTypes()
	{
		if(cbVehicleID.getSelectedIndex()!=-1)
		{
		try
		{
			int id = ((ComboItem)this.cbVehicleID.getSelectedItem()).getID();
			String mode = this.cbTravelModes.getSelectedItem().toString();

			ArrayList<Map<String,Object>> data  = BaseClass.executeQuery("Select VehicleTypeName,VehicleTypeID from TravelTypes where VehicleTypeID In("+
					"Select TravelTypeID from indextable where vehicleID = '" + id + "' AND TravelMode = '" + mode + "'" +  
					" and Deleted=false)" + " AND VehicleMode = '" +mode + "' AND Deleted = false");
			types = new ComboItem[data.size()];
			
			for(int i = 0; i<data.size();i++)
			{
				ComboItem temp = new ComboItem();
				temp.setID((Integer)data.get(i).get("VehicleTypeID"));
				temp.setName(data.get(i).get("VehicleTypeName").toString());
				types[i]=temp;
				cbTravelType.addItem(vehicles[i]);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
	}
	public void addTableRefreshListener(TableRefreshListener t)
	{
		refresh.add(t);
	}
}//End of LocationForm Class
class ComboItem
{
	int id;
	String name;

	public ComboItem()
	{
		id=-1;
		name ="";
	}

	public void setName(String n)
	{
		name = n;
	}
	public void setID(int i)
	{
		id=i;
	}

	public int getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o==null)return false;
		if(o==this)return true;
		if(!(o instanceof ComboItem)) return false;
		ComboItem test = (ComboItem)o;
		if(test.getID()==id)
			return true;
		return false;
	}
}
