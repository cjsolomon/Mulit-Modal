package GUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import core.Location;
import core.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.Sizes;

public class LocationForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblStartLat, lblStartLon, lblStartCity,lblStartState,lblStartCountry, lblStartLocation, lblStartID, lblTravelModes;
	JLabel lblEndLat, lblEndLon, lblEndCity,lblEndState,lblEndCountry, lblEndLocation, lblEndID;
	JLabel lblSegments;
	JTextField txtEndLat,txtEndLon,txtEndCity,txtEndState,txtEndCountry, txtEndID;
	JTextField txtStartLat,txtStartLon,txtStartCity,txtStartState,txtStartCountry, txtStartID;
	JComboBox<String> cbEndCountry, cbEndState, cbEndCity;
	JComboBox<String> cbStartCountry, cbStartState, cbStartCity, cbTravelModes;
	
	SegmentTable sgmtTable;
	
	final ArrayList<Location> allLocations;
	final ArrayList<String> countries;
	final ArrayList<String> states;
	final ArrayList<String> cities;
	private JButton btnEditStartLocation;
	private JButton btnEditEndLocation;
	private JButton btnCreateLocation;
	private JLabel lblSegmentID;
	private JTextField txtSegmentID;
	private JLabel lblVehicleID;
	private JTextField txtVehicleID;
	private JLabel lblMode;
	private JTextField txtMode;
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
	private JButton btnEdit;
	private JButton btnCreateNewShipment;
	
	public LocationForm() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(49dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("50dlu", true), Sizes.constant("60dlu", true)), 0),
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
		
		
		lblStartLat=new JLabel("Latitude:");
		lblStartLon = new JLabel("Longitute:");
		lblStartCity = new JLabel("City:");
		lblStartState = new JLabel("State:");
		lblStartCountry = new JLabel("Country:");
		lblStartLocation = new JLabel("Start Location");
		lblStartID = new JLabel("LocationID");
		lblTravelModes = new JLabel("Travel Modes");
		cbStartCountry = new JComboBox<String>();
		cbStartState = new JComboBox<String>();
		cbStartCity = new JComboBox<String>();
		cbTravelModes = new JComboBox<String>();
		
		txtStartLat=new JTextField(20);
		txtStartLat.setEditable(false);
		txtStartLon = new JTextField(20);
		txtStartLon.setEditable(false);
		txtStartCity= new JTextField(20);
		txtStartCity.setEditable(false);
		txtStartState = new JTextField(20);
		txtStartState.setEditable(false);
		txtStartCountry = new JTextField(20);
		txtStartCountry.setEditable(false);
		txtStartID = new JTextField(10);
		txtStartID.setEditable(false);
		
		lblEndLat=new JLabel("Latitude:");
		lblEndLon = new JLabel("Longitute:");
		lblEndCity = new JLabel("City:");
		lblEndState = new JLabel("State:");
		lblEndCountry = new JLabel("Country:");
		lblEndLocation = new JLabel("End Location");
		lblEndID = new JLabel("LocationID");
		cbEndCountry = new JComboBox<String>();
		cbEndState = new JComboBox<String>();
		cbEndCity = new JComboBox<String>();
		
		txtEndLat=new JTextField(20);
		txtEndLat.setEditable(false);
		txtEndLon = new JTextField(20);
		txtEndLon.setEditable(false);
		txtEndCity= new JTextField(20);
		txtEndCity.setEditable(false);
		txtEndState = new JTextField(20);
		txtEndState.setEditable(false);
		txtEndCountry = new JTextField(20);
		txtEndCountry.setEditable(false);
		txtEndID = new JTextField(10);
		txtEndID.setEditable(false);
		
		
		sgmtTable = new SegmentTable();
		
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(sgmtTable);
		
		lblSegmentID = new JLabel("SegmentID");
		add(lblSegmentID, "2, 2, right, center");
		
		txtSegmentID = new JTextField();
		txtSegmentID.setEditable(false);
		add(txtSegmentID, "4, 2, left, default");
		txtSegmentID.setColumns(10);
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		add(btnEdit, "5, 2");
		
		lblEstDeparture = new JLabel("Est. Departure");
		add(lblEstDeparture, "9, 2, right, default");
		
		txtEstDeparture = new JTextField();
		txtEstDeparture.setEditable(false);
		add(txtEstDeparture, "11, 2, fill, default");
		txtEstDeparture.setColumns(10);
		
		lblVehicleID = new JLabel("VehicleID");
		add(lblVehicleID, "2, 4, right, default");
		
		txtVehicleID = new JTextField();
		txtVehicleID.setEditable(false);
		add(txtVehicleID, "4, 4, left, default");
		txtVehicleID.setColumns(10);
		
		lblEarliestDeparture = new JLabel("Earliest Departure");
		add(lblEarliestDeparture, "9, 4, right, default");
		
		txtEarlDeparture = new JTextField();
		txtEarlDeparture.setEditable(false);
		add(txtEarlDeparture, "11, 4, fill, default");
		txtEarlDeparture.setColumns(10);
		
		lblMode = new JLabel("Mode");
		add(lblMode, "2, 6, right, default");
		
		txtMode = new JTextField();
		txtMode.setEditable(false);
		add(txtMode, "4, 6, fill, default");
		txtMode.setColumns(10);
		
		lblLatestDeparture = new JLabel("Latest Departure");
		add(lblLatestDeparture, "9, 6, right, default");
		
		txtLatDeparture = new JTextField();
		txtLatDeparture.setEditable(false);
		add(txtLatDeparture, "11, 6, fill, default");
		txtLatDeparture.setColumns(10);
		
		lblDistance = new JLabel("Distance");
		add(lblDistance, "2, 8, right, default");
		
		txtDistance = new JTextField();
		txtDistance.setEditable(false);
		add(txtDistance, "4, 8, fill, default");
		txtDistance.setColumns(10);
		
		lblEstArrival = new JLabel("Est. Arrival");
		add(lblEstArrival, "9, 8, right, default");
		
		txtEstArrival = new JTextField();
		txtEstArrival.setEditable(false);
		add(txtEstArrival, "11, 8, fill, default");
		txtEstArrival.setColumns(10);
		
		lblLane = new JLabel("Lane");
		add(lblLane, "2, 10, right, default");
		
		txtLane = new JTextField();
		txtLane.setEditable(false);
		add(txtLane, "4, 10, fill, default");
		txtLane.setColumns(10);
		
		lblEarliestArrival = new JLabel("Earliest Arrival");
		add(lblEarliestArrival, "9, 10, right, default");
		
		txtEarlArrival = new JTextField();
		txtEarlArrival.setEditable(false);
		add(txtEarlArrival, "11, 10, fill, default");
		txtEarlArrival.setColumns(10);
		
		lblRateid = new JLabel("RateID");
		add(lblRateid, "2, 12, right, default");
		
		txtRateID = new JTextField();
		txtRateID.setEditable(false);
		add(txtRateID, "4, 12, fill, default");
		txtRateID.setColumns(10);
		
		lblLatestArrival = new JLabel("Latest Arrival");
		add(lblLatestArrival, "9, 12, right, default");
		
		txtLatArrival = new JTextField();
		txtLatArrival.setEditable(false);
		add(txtLatArrival, "11, 12, fill, default");
		txtLatArrival.setColumns(10);
		
		lblActualCapacity = new JLabel("Actual Capacity");
		add(lblActualCapacity, "2, 14, right, default");
		
		txtActualCapacity = new JTextField();
		txtActualCapacity.setEditable(false);
		add(txtActualCapacity, "4, 14, fill, default");
		txtActualCapacity.setColumns(10);
		
		btnCreateNewShipment = new JButton("Create New Shipment");
		btnCreateNewShipment.setEnabled(false);
		add(btnCreateNewShipment, "11, 14");
		
		//Start Location
		
		add(lblStartLocation, "4, 16, center, center");
		add(txtStartID, "4, 18, left, center");
		add(lblStartID, "2, 18, right, center");
		
		btnEditStartLocation = new JButton("Edit");
		btnEditStartLocation.setEnabled(false);
		btnEditStartLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Location editLocation = new Location();
				editLocation.setCountry(txtStartCountry.getText());
				editLocation.setState(txtStartState.getText());
				editLocation.setName(txtStartCity.getText());
				editLocation.setID(Integer.valueOf(txtStartID.getText()));
				editLocation.setLatitude(Double.valueOf(txtStartLat.getText()));
				editLocation.setLongitude(Double.valueOf(txtStartLon.getText()));*/
				Location editLocation = Location.Load(Integer.valueOf(txtStartID.getText()));
				LocationCreateEdit lce = new LocationCreateEdit(editLocation);
				add(lce,"2, 2, center, center");
				setVisible(false);
				lce.setVisible(true);
			}
		});
		add(btnEditStartLocation, "5, 18");
		
		btnEditEndLocation = new JButton("Edit");
		btnEditEndLocation.setEnabled(false);
		btnEditEndLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location editLocation = Location.Load(Integer.valueOf(txtEndID.getText()));
				LocationCreateEdit lce = new LocationCreateEdit(editLocation);
				add(lce,"2, 2, center, center");
				lce.setVisible(true);
				setVisible(false);
			}
		});
		add(btnEditEndLocation, "12, 18");
		add(lblStartCity,"2, 24, right, center");
		add(txtStartCity, "4, 24, right, center");
		add(lblStartState,"2, 22, right, center");
		add(txtStartState, "4, 22, right, center");
		add(lblStartCountry,"2, 20, right, center");
		add(txtStartCountry, "4, 20, right, center");
		add(lblStartLat,"2, 26, right, center");
		add(txtStartLat, "4, 26, right, center");
		add(lblStartLon,"2, 28, right, center");
		add(txtStartLon, "4, 28, right, center");
		add(cbStartCountry, "5, 20, left, top");
		add(cbStartState, "5, 22, left, top");
		add(cbStartCity, "5, 24, left, top");
		add(lblTravelModes,"5, 26, right, top");
		add(cbTravelModes, "5, 28, right, top");
		
		//Get the countries
		allLocations = core.Location.LoadAll("");
		countries = new ArrayList<String>();
		states = new ArrayList<String>();
		cities = new ArrayList<String>();
		
		
		//Set up the countries
		loadCountries(cbStartCountry);
		loadCountries(cbEndCountry);
		//Set up the states
		loadStates(cbStartState, cbStartCountry);
		loadStates(cbEndState, cbEndCountry);
		//Set up the cities
		loadCities(cbStartCity, cbStartState, cbStartCountry);
		loadCities(cbEndCity, cbEndState, cbEndCountry);
		//Set up the travel modes
		loadTravelModes(allLocations.get(0));
		
		
		//Initialize the fields
		
		cbEndCountry.setSelectedIndex(1);
		loadStartLocation(allLocations.get(0));
    	loadEndLocation(allLocations.get(1));
    	//End Location
    	
    	add(lblEndLocation, "11, 16, center, center");
		add(txtEndID, "11, 18, left, center");
		add(lblEndID, "9, 18, right, center");
		add(lblEndCity,"9, 24, right, center");
		add(txtEndCity, "11, 24, right, center");
		add(lblEndState,"9, 22, right, center");
		add(txtEndState, "11, 22, right, center");
		add(lblEndCountry,"9, 20, right, center");
		add(txtEndCountry, "11, 20, right, center");
		add(lblEndLat,"9, 26, right, center");
		add(txtEndLat, "11, 26, right, center");
		add(lblEndLon,"9, 28, right, center");
		add(txtEndLon, "11, 28, right, center");
		add(cbEndCountry, "12, 20, left, top");
		add(cbEndState, "12, 22, left, top");
		add(cbEndCity, "12, 24, left, top");
    	
    	
    	//Initialize the Segment table
    	
    	loadSegments();
		
		btnCreateLocation = new JButton("Create New Location");
		btnCreateLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationCreateEdit lce = new LocationCreateEdit(null);
				add(lce,"2, 2, center, center");
				lce.setVisible(true);
				setVisible(false);
			}
		});
		add(btnCreateLocation, "4, 31");
		lblSegments = new JLabel("Possible Segments");
		add(lblSegments,"4, 33, center, center");
		add(sp,"2, 34, 11, 1");
    	
		
		cbStartCountry.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	if(cbStartCountry.getSelectedIndex()!=-1)
		        	{
		        		//	Clear out the states and the cities
		        		//cbStartState.removeAllItems();
		        		//cbStartCity.removeAllItems();
		        	
		        		//Load the new states according to the country selected
		        		loadStates(cbStartState, cbStartCountry);
		        		//cbStartState.setSelectedIndex(0);
		        	}
		        
		        }//End of stateChange if
		    }//End of itemStateChanged(ItemEvent e)
		});
		
		cbStartState.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		    	if(cbStartState.getSelectedIndex()!=-1)
		    	{
			        if (e.getStateChange() == ItemEvent.SELECTED){
			
			        	loadCities(cbStartCity, cbStartState, cbStartCountry);
			        	cbStartCity.setSelectedIndex(0);
			            
			        }
		    	}
		    }
		});
		
		cbStartCity.addItemListener(new ItemListener()
		{            
			
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	
		        	Location thisLocation = findLocation(cbStartCountry.getSelectedItem().toString(), cbStartState.getSelectedItem().toString(), e.getItem().toString());
		        	
		        	loadStartLocation(thisLocation);
		        	
		        	loadTravelModes(thisLocation);
		        	
		        	loadSegments();
		            
		        }
		    }
		});
		
		cbEndCountry.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){

		        	//Clear out the states and the cities
		        	cbEndState.removeAllItems();
		        	cbEndCity.removeAllItems();
		        	
		        	//Load the new states according to the country selected
		        	loadStates(cbEndState, cbEndCountry);
		        	cbEndState.setSelectedIndex(0);

		        }//End of stateChange if
		    }//End of itemStateChanged(ItemEvent e)
		});
		
		cbEndState.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){

		        	loadCities(cbEndCity, cbEndState, cbEndCountry);
		        	cbEndCity.setSelectedIndex(0);

		        }
		    }
		});
		
		cbEndCity.addItemListener(new ItemListener()
		{            
			
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){

		        	Location thisLocation = findLocation(cbEndCountry.getSelectedItem().toString(), cbEndState.getSelectedItem().toString(), e.getItem().toString());
		        	
		        	loadEndLocation(thisLocation);
		        	
		        	loadTravelModes(thisLocation);
		        	
		        	//loadSegments();
		            
		        }
		    }
		});
		
		
		cbTravelModes.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	loadSegments();
		        }
		    }
		});
    	
		
	}//End of LocationForm Constructor
	
	public void loadStartLocation(Location start){
		
		System.out.println("Load start location has been called");
    	
		txtStartCity.setText(start.getName());
    	txtStartState.setText(start.getState());
    	txtStartCountry.setText(start.getCountry());
    	txtStartLat.setText(String.valueOf(start.getLatitude()));
    	txtStartLon.setText(String.valueOf(start.getLongitude()));
    	txtStartID.setText(String.valueOf(start.getID()));
    	//cbStartCountry.setSelectedItem(start.getCountry());
    	//cbStartState.setSelectedItem(start.getState());
    	//cbStartCity.setSelectedItem(start.getName());
		
	}//End of loadStartLocation(Location start)
	
	public void loadEndLocation(Location end){
		
		System.out.println("Load end location has been called");
		
		txtEndCity.setText(end.getName());
    	txtEndState.setText(end.getState());
    	txtEndCountry.setText(end.getCountry());
    	txtEndLat.setText(String.valueOf(end.getLatitude()));
    	txtEndLon.setText(String.valueOf(end.getLongitude()));
    	txtEndID.setText(String.valueOf(end.getID()));
    	cbEndCountry.setSelectedItem(end.getCountry());
    	cbEndState.setSelectedItem(end.getState());
    	cbEndCity.setSelectedItem(end.getName());
		
	}//End of loadEndLocation(Location end)
	
	public void loadSegments(){
		
		sgmtTable.removeAll();
		String sqlWhere;
		if(!txtEndID.getText().equals("") && !(cbTravelModes.getSelectedItem() == null) && !txtStartID.getText().equals("")){
			sqlWhere ="where FromLocationID = '" + txtStartID.getText() +"' AND ToLocationID = '"+ txtEndID.getText() +"' AND ModeType = '" + cbTravelModes.getSelectedItem().toString() + "'";
		}
		else if(!txtStartID.getText().equals("") && !(cbTravelModes.getSelectedItem() == null)){
			sqlWhere ="where FromLocationID = '" + txtStartID.getText() +"' AND ModeType = '" + cbTravelModes.getSelectedItem().toString() + "'";
		}
		else if(!txtStartID.getText().equals("")){
			sqlWhere ="where FromLocationID = '" + txtStartID.getText() +"'";
		}
		else{
			sqlWhere = "";
		}
		sgmtTable.showPanel(sqlWhere);
		
	}//End of loadSegments()
	
	public void loadCountries(JComboBox<String> countryComboBox){
		
		
		boolean addLocation = true;
		countries.clear();
		for(int i = 0; i < allLocations.size(); i++){
			for(int j = 0; j < countries.size(); j++){
				if(countries.get(j) == allLocations.get(i).getCountry()){
					addLocation = false;
					break;
				}
			}
			if(addLocation){
				countries.add(allLocations.get(i).getCountry());
			}
			addLocation = true;
		}
		
		for(int i = 0; i < countries.size(); i++){
			countryComboBox.addItem(countries.get(i));
		}
		
	}//End of loadCountries()
	
	public void loadStates(JComboBox<String> stateComboBox, JComboBox<String> countryComboBox){

		stateComboBox.removeAllItems();
		states.clear();
		
		String sql = " where Country = '" + countryComboBox.getSelectedItem().toString() +"'";
		ArrayList<Location> stateLocations = Location.LoadAll(sql);
		
		for(int i=0; i < stateLocations.size(); i++){
			if(!states.contains(stateLocations.get(i).getState()))
				states.add(stateLocations.get(i).getState());
		}
		
		for(int i = 0; i < states.size(); i++){
			stateComboBox.addItem(states.get(i));
		}
	
	}//End of loadStates()
	
	public void loadCities(JComboBox<String> cityComboBox,JComboBox<String> stateComboBox, JComboBox<String> countryComboBox){

		cityComboBox.removeAllItems();
		cities.clear();
		String sql = " where Country = '" + countryComboBox.getSelectedItem().toString() + "' AND State = '" +stateComboBox.getSelectedItem().toString()+ "'";
		ArrayList<Location> cityLocations = Location.LoadAll(sql);
		
		for(int i=0; i < cityLocations.size(); i++){
			if(!cities.contains(cityLocations.get(i).getName()))
				cities.add(cityLocations.get(i).getName());
		}
		
		for(int i = 0; i < cities.size(); i++){
			cityComboBox.addItem(cities.get(i));
		}
		
		
	}//End of loadCities()
	
	public Location findLocation(String country, String state, String city){
		Location foundLocation = new Location();
		foundLocation = Location.LoadAll(" where Country = '" + country + "' AND State = '" + state + "' AND Name = '" + city + "'").get(0);

		return foundLocation;
	}//End of findLocation(String country, String state, String city)
	
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

}//End of LocationForm Class
