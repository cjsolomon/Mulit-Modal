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
	
	public LocationForm() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(49dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		
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
		
		//Start Location
		
		add(lblStartLocation, "4, 2, center, center");
		add(txtStartID, "4,4,left,center");
		add(lblStartID, "2, 4, right, center");
		
		btnEditStartLocation = new JButton("Edit");
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
				lce.setVisible(true);
				setVisible(false);
			}
		});
		add(btnEditStartLocation, "5, 4");
		
		btnEditEndLocation = new JButton("Edit");
		btnEditEndLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location editLocation = Location.Load(Integer.valueOf(txtEndID.getText()));
				LocationCreateEdit lce = new LocationCreateEdit(editLocation);
				add(lce,"2, 2, center, center");
				lce.setVisible(true);
				setVisible(false);
			}
		});
		add(btnEditEndLocation, "11, 4");
		add(lblStartCity,"2,10,right,center");
		add(txtStartCity, "4,10,right,center");
		add(lblStartState,"2,8,right,center");
		add(txtStartState, "4,8,right,center");
		add(lblStartCountry,"2,6,right,center");
		add(txtStartCountry, "4,6,right,center");
		add(lblStartLat,"2,12,right,center");
		add(txtStartLat, "4,12,right,center");
		add(lblStartLon,"2,14,right,center");
		add(txtStartLon, "4,14,right,center");
		add(cbStartCountry, "5,6, left, top");
		add(cbStartState, "5,8, left, top");
		add(cbStartCity, "5,10, left, top");
		add(lblTravelModes,"5,12, right, top");
		add(cbTravelModes, "5,14, right, top");
		
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
		cbEndState.setSelectedItem(1);
		cbEndCity.setSelectedItem(1);
		loadStartLocation(allLocations.get(0));
    	loadEndLocation(allLocations.get(1));
    	//End Location
    	
    	add(lblEndLocation, "10, 2, center, center");
		add(txtEndID, "10, 4, left, center");
		add(lblEndID, "8, 4, right, center");
		add(lblEndCity,"8, 10, right, center");
		add(txtEndCity, "10, 10, right, center");
		add(lblEndState,"8, 8, right, center");
		add(txtEndState, "10, 8, right, center");
		add(lblEndCountry,"8, 6, right, center");
		add(txtEndCountry, "10, 6, right, center");
		add(lblEndLat,"8, 12, right, center");
		add(txtEndLat, "10, 12, right, center");
		add(lblEndLon,"8, 14, right, center");
		add(txtEndLon, "10, 14, right, center");
		add(cbEndCountry, "11, 6, left, top");
		add(cbEndState, "11, 8, left, top");
		add(cbEndCity, "11, 10, left, top");
    	
    	
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
		add(btnCreateLocation, "4, 17");
		lblSegments = new JLabel("Possible Segments");
		add(lblSegments,"4, 19, center, center");
		add(sp,"2, 20, 10, 1");
    	
		
		cbStartCountry.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	
		        	System.out.println("The country has been changed");
		        	
		        	//Clear out the states and the cities
		        	cbStartState.removeAllItems();
		        	cbStartCity.removeAllItems();
		        	
		        	//Load the new states according to the country selected
		        	loadStates(cbStartState, cbStartCountry);
		        	cbStartState.setSelectedIndex(0);
		        
		        }//End of stateChange if
		    }//End of itemStateChanged(ItemEvent e)
		});
		
		cbStartState.addItemListener(new ItemListener()
		{            
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	
		        	System.out.println("The state has been changed");
		        	
		   
		        	loadCities(cbStartCity, cbStartState, cbStartCountry);
		        	cbStartCity.setSelectedIndex(0);
		            
		        }
		    }
		});
		
		cbStartCity.addItemListener(new ItemListener()
		{            
			
		    @Override
		    public void itemStateChanged(ItemEvent e)
		    {
		        if (e.getStateChange() == ItemEvent.SELECTED){
		        	
		        	System.out.println("The city has been changed");
		        	
		        	
		        	Location thisLocation = findLocation(cbStartCountry.getSelectedItem().toString(), cbStartState.getSelectedItem().toString(), cbStartCity.getSelectedItem().toString());
		        	
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
		        	
		        	System.out.println("The country has been changed");
		        	
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
		        	
		        	System.out.println("The state has been changed");
		        	
		   
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
		        	
		        	System.out.println("The city has been changed");
		        	
		        	
		        	Location thisLocation = findLocation(cbEndCountry.getSelectedItem().toString(), cbEndState.getSelectedItem().toString(), cbEndCity.getSelectedItem().toString());
		        	
		        	loadEndLocation(thisLocation);
		        	
		        	loadTravelModes(thisLocation);
		        	
		        	loadSegments();
		            
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
    	cbStartCountry.setSelectedItem(start.getCountry());
    	cbStartState.setSelectedItem(start.getState());
    	cbStartCity.setSelectedItem(start.getName());
		
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
		
		System.out.println("Load segments has been called");
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
		System.out.println("Load countries has been called");
		
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
		
		System.out.println("Load states has been called");

		stateComboBox.removeAllItems();
		states.clear();
		for(int i = 0; i < allLocations.size(); i++){
			if(countryComboBox.getSelectedItem() == allLocations.get(i).getCountry() && !states.contains(allLocations.get(i).getState()))
				states.add(allLocations.get(i).getState());
		}
		
		for(int i = 0; i < states.size(); i++){
			stateComboBox.addItem(states.get(i));
		}
	
	}//End of loadStates()
	
	public void loadCities(JComboBox<String> cityComboBox,JComboBox<String> stateComboBox, JComboBox<String> countryComboBox){
		
		System.out.println("Load cities has been called");
		cityComboBox.removeAllItems();
		/*cities.clear();
		for(int i = 0; i < allLocations.size(); i++){
			System.out.println(countryComboBox.getSelectedItem());
			System.out.println(allLocations.get(i).getCountry());
			if(countryComboBox.getSelectedItem() == allLocations.get(i).getCountry()){
				System.out.println(stateComboBox.getSelectedItem());
				System.out.println(allLocations.get(i).getState());
				if(stateComboBox.getSelectedItem() == allLocations.get(i).getState()){
					System.out.println(allLocations.get(i).getName());
					if(!cities.contains(allLocations.get(i).getName())){
						cities.add(allLocations.get(i).getName());
					}
				}
			}
		}*/
		
		for(int i = 0; i < cities.size(); i++){
			cityComboBox.addItem(cities.get(i));
		}
		
		String sql = " where Country = '" + countryComboBox.getSelectedItem().toString() + "' AND State = '" +stateComboBox.getSelectedItem().toString()+ "'";
		ArrayList<Location> cityLocations = Location.LoadAll(sql);
		
		for(int i = 0; i < cityLocations.size(); i++){
			cityComboBox.addItem(cityLocations.get(i).getName());
		}
		
		
	}//End of loadCities()
	
	public Location findLocation(String country, String state, String city){
		Location foundLocation = new Location();
		for(int i = 0; i < allLocations.size(); i++){
			if(allLocations.get(i).getCountry() == country && allLocations.get(i).getState() == state && allLocations.get(i).getName() == city){
				foundLocation = allLocations.get(i);
				break;
			}
		}
		return foundLocation;
	}//End of findLocation(String country, String state, String city)
	
	public void loadTravelModes(Location thisLocation){
		
		System.out.println("Load travel modes has been called");
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
