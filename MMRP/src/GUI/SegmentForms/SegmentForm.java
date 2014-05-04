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
import core.TravelType;
import core.Truck;
import core.Vehicle;
import core.Vehicle.TravelModes;
import javax.swing.SwingConstants;

public class SegmentForm extends JPanel {
	
	JLabel lblStartCity,lblStartState,lblStartCountry, lblStartLocation;
	JLabel lblEndCity,lblEndState,lblEndCountry, lblEndLocation;
	JComboBox<String> cmbToCountries, cmbToStates, cmbToCities;
	JComboBox<String> cmbFromCities, cmbFromStates, cmbFromCountries, cbTravelModes;
	
	boolean editing = false;

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
	private JButton btnCreateNewShipment;
	private JButton btnCancel;
	private JLabel lblTravelType;
	private JComboBox<String> cbTravelType;
	private JLabel lblSegments;
	
	public SegmentForm() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(58dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("max(44dlu;default)"),
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
				loadStates(cmbFromCountries,cmbFromStates);
			}
		});
		
		cmbToCountries = new JComboBox<String>();
		cmbToCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbToStates.removeAllItems();
				loadStates(cmbToCountries,cmbToStates);
			}
		});

		cmbFromStates= new JComboBox<String> ();
		cmbFromStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbFromCities.removeAll();
				loadCities(cmbFromCountries,cmbFromStates,cmbFromCities);
			}
		});
		cmbToStates = new JComboBox<String>();
		cmbToStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cmbToCities.removeAll();
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
		cbTravelModes = new JComboBox<String>();
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

		btnCreateNewShipment = new JButton("Save");
		btnCreateNewShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnCreateNewShipment, "9, 28");
		
		btnCancel = new JButton("Cancel");
		add(btnCancel, "11, 28");
    	
    	
		this.cbTravelModes.removeAllItems();
		this.cbTravelModes.addItem("Select...");
		for(TravelModes t : Vehicle.TravelModes.values())
			cbTravelModes.addItem(t.toString());
    	
		cbTravelModes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(cbTravelModes.getSelectedIndex()>0 && !editing)
				{
					cmbToCountries.removeAllItems();
					cmbFromCountries.removeAllItems();
					loadCountries();
					String mode = cbTravelModes.getSelectedItem().toString();
					ArrayList<Vehicle> temp = new ArrayList<Vehicle>();
					cbVehicleID.removeAllItems();
					if(mode.equals(Vehicle.TravelModes.TRUCK.toString()))
						temp.addAll(Truck.LoadAll("where TruckID < 10"));
					if(mode.equals(Vehicle.TravelModes.CARGO.toString()))
						temp.addAll(Cargo.LoadAll("where CargoID < 10"));
					if(mode.equals(Vehicle.TravelModes.PLANE))
						temp.addAll(Plane.LoadAll("where PlaneID < 10"));
					if(mode.equals(Vehicle.TravelModes.RAIL))
						temp.addAll(Rail.LoadAll("where RailID < 10"));
					
					for(Vehicle v : temp)
					{
						cbVehicleID.addItem(v);
					}
					
					
				}
				if(editing)
					editing = !editing;
				
			}
		});
		
		this.cbVehicleID.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(cbVehicleID.getSelectedIndex()>0)
				{
					ArrayList<TravelType> types = new ArrayList<TravelType>();
					types = ((Vehicle)cbVehicleID.getSelectedItem()).getAvailableTypes();
					for(int i =0; i <types.size(); i++){
						cbTravelType.addItem(types.get(i).getTravelTypeName());
					}
					
				}
				
			}
		});

    	
		
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
	
	public void showPanel(){
		this.setVisible(true);
	}
	
	public void showPanel(Segment loadSegment){
		
		setSegment(loadSegment);
		setReadOnly();
		btnCreateNewShipment.setVisible(true);
		btnCreateNewShipment.setText("Edit");
		setVisible(true);
	}
	
	
	public void setSegment(Segment loadSegment){
		this.txtSegmentID.setText(String.valueOf(loadSegment.getID()));
		editing = true;
		this.cbTravelModes.addItem(loadSegment.getTravelType().getTravelTypeMode());
		this.cbTravelModes.setSelectedItem(loadSegment.getTravelType().getTravelTypeMode());
		this.cbVehicleID.addItem(loadSegment.getVehicle().getId());
		this.cbVehicleID.setSelectedItem(loadSegment.getVehicle().getId());
		this.cbTravelType.addItem(String.valueOf(loadSegment.getTravelType().getVehicleTypeID()));
		this.cbTravelType.setSelectedItem(loadSegment.getTravelType().getVehicleTypeID());
		this.txtDistance.setText(String.valueOf(loadSegment.getDistance()));
		this.txtLane.setText(loadSegment.getLane());
		this.txtRateID.setText(String.valueOf(loadSegment.getShippingRate().getId() + " - Flat Rate : " + loadSegment.getShippingRate().getFlatRate()));
		this.txtActualCapacity.setText(String.valueOf(loadSegment.getActualCapacity()));
		this.txtEstArrival.setText(String.valueOf(loadSegment.getEstimatedArrivalTime()));
		this.txtEarlArrival.setText(String.valueOf(loadSegment.getEarliestArrivalTime()));
		this.txtLatArrival.setText(String.valueOf(loadSegment.getLatestArrivalTime()));
		this.txtEstDeparture.setText(String.valueOf(loadSegment.getEstimatedDepartureTime()));
		this.txtEarlDeparture.setText(String.valueOf(loadSegment.getEarliestDepartureTime()));
		this.txtLatDeparture.setText(String.valueOf(loadSegment.getLatestDepartureTime()));
		
		this.cmbFromCountries.addItem(loadSegment.getStartLocation().getCountry());
		this.cmbFromCountries.setSelectedItem(loadSegment.getStartLocation().getCountry());
		this.cmbFromStates.addItem(loadSegment.getStartLocation().getState());
		this.cmbFromStates.setSelectedItem(loadSegment.getStartLocation().getState());
		this.cmbFromCities.addItem(loadSegment.getStartLocation().getName());
		this.cmbFromCities.setSelectedItem(loadSegment.getStartLocation().getName());
		
		this.cmbToCountries.addItem(loadSegment.getEndLocation().getCountry());
		this.cmbToCountries.setSelectedItem(loadSegment.getEndLocation().getCountry());
		this.cmbToStates.addItem(loadSegment.getEndLocation().getState());
		this.cmbToStates.setSelectedItem(loadSegment.getEndLocation().getState());
		this.cmbToCities.addItem(loadSegment.getEndLocation().getName());
		this.cmbToCities.setSelectedItem(loadSegment.getEndLocation().getName());
		
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
	
	

}//End of LocationForm Class
