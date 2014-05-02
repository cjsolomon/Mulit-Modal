package GUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import core.BaseClass;
import core.Location;
import core.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SegmentCreateEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblStartCity,lblStartState,lblStartCountry, lblStartLocation;
	JLabel lblEndCity,lblEndState,lblEndCountry, lblEndLocation;
	JComboBox<String> cmbToCountries, cmbToStates, cmbToCities;
	JComboBox<String> cmbFromCities, cmbFromStates, cmbFromCountries, cbTravelModes;
	
	final ArrayList<Location> allLocations;
	final ArrayList<String> countries;
	final ArrayList<String> states;
	final ArrayList<String> cities;
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
	
	public SegmentCreateEdit() {
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
				loadStates(cmbFromCountries,cmbFromStates);
			}
		});
		
		cmbToCountries = new JComboBox<String>();
		cmbToCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadStates(cmbToCountries,cmbToStates);
			}
		});

		loadCountries(this.cmbFromCountries,this.cmbToCountries);
		//loadCountries(this.cmbToCountries);

		cmbFromStates= new JComboBox<String> ();
		cmbFromStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadCities(cmbFromCountries,cmbFromStates,cmbFromCities);
			}
		});
		cmbToStates = new JComboBox<String>();
		cmbToStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadCities(cmbToCountries,cmbToStates,cmbToCities);
			}
		});
		cmbFromCities = new JComboBox<String> ();
		cmbToCities = new JComboBox<String>();
		
		lblSegmentID = new JLabel("SegmentID");
		add(lblSegmentID, "2, 2, right, center");
		
		txtSegmentID = new JTextField();
		txtSegmentID.setEditable(false);
		add(txtSegmentID, "4, 2, left, default");
		txtSegmentID.setColumns(10);
		
		lblEstDeparture = new JLabel("Est. Departure");
		add(lblEstDeparture, "9, 2, right, default");
		
		txtEstDeparture = new JTextField();
		txtEstDeparture.setEditable(false);
		add(txtEstDeparture, "11, 2, fill, default");
		txtEstDeparture.setColumns(10);
		
		lblMode = new JLabel("Mode");
		add(lblMode, "2, 4, right, default");
		cbTravelModes = new JComboBox<String>();
		add(cbTravelModes, "4, 4, right, top");
		
		
		
		lblEarliestDeparture = new JLabel("Earliest Departure");
		add(lblEarliestDeparture, "9, 4, right, default");
		
		txtEarlDeparture = new JTextField();
		txtEarlDeparture.setEditable(false);
		add(txtEarlDeparture, "11, 4, fill, default");
		txtEarlDeparture.setColumns(10);
		
		lblVehicleID = new JLabel("Vehicle");
		add(lblVehicleID, "2, 6, right, default");
		
		cbVehicleID = new JComboBox();
		cbVehicleID.setEditable(false);
		add(cbVehicleID, "4, 6, right, default");
		
		lblLatestDeparture = new JLabel("Latest Departure");
		add(lblLatestDeparture, "9, 6, right, default");
		
		txtLatDeparture = new JTextField();
		txtLatDeparture.setEditable(false);
		add(txtLatDeparture, "11, 6, fill, default");
		txtLatDeparture.setColumns(10);
		
		lblTravelType = new JLabel("Travel Type");
		add(lblTravelType, "2, 8, right, default");
		
		cbTravelType = new JComboBox();
		add(cbTravelType, "4, 8, fill, default");
		
		lblEstArrival = new JLabel("Est. Arrival");
		add(lblEstArrival, "9, 8, right, default");
		
		txtEstArrival = new JTextField();
		txtEstArrival.setEditable(false);
		add(txtEstArrival, "11, 8, fill, default");
		txtEstArrival.setColumns(10);
		
		lblDistance = new JLabel("Distance");
		add(lblDistance, "2, 10, right, default");
		
		txtDistance = new JTextField();
		txtDistance.setEditable(false);
		add(txtDistance, "4, 10, fill, default");
		txtDistance.setColumns(10);
		
		lblEarliestArrival = new JLabel("Earliest Arrival");
		add(lblEarliestArrival, "9, 10, right, default");
		
		txtEarlArrival = new JTextField();
		txtEarlArrival.setEditable(false);
		add(txtEarlArrival, "11, 10, fill, default");
		txtEarlArrival.setColumns(10);
		
		lblLane = new JLabel("Lane");
		add(lblLane, "2, 12, right, default");
		
		txtLane = new JTextField();
		txtLane.setEditable(false);
		add(txtLane, "4, 12, fill, default");
		txtLane.setColumns(10);
		
		lblLatestArrival = new JLabel("Latest Arrival");
		add(lblLatestArrival, "9, 12, right, default");
		
		txtLatArrival = new JTextField();
		txtLatArrival.setEditable(false);
		add(txtLatArrival, "11, 12, fill, default");
		txtLatArrival.setColumns(10);
		
		lblRateid = new JLabel("Shipping Rate");
		add(lblRateid, "2, 14, right, default");
		
		txtRateID = new JTextField();
		txtRateID.setEditable(false);
		add(txtRateID, "4, 14, fill, default");
		txtRateID.setColumns(10);
		
		lblActualCapacity = new JLabel("Actual Capacity");
		add(lblActualCapacity, "2, 16, right, default");
		
		txtActualCapacity = new JTextField();
		txtActualCapacity.setEditable(false);
		add(txtActualCapacity, "4, 16, fill, default");
		txtActualCapacity.setColumns(10);
		lblStartLocation = new JLabel("Start Location");
		
		//Start Location
		
		add(lblStartLocation, "4, 18, center, center");
		lblEndLocation = new JLabel("End Location");
		//End Location
		
		add(lblEndLocation, "11, 18, center, center");
		//this.cmbf = new JComboBox<String>();
		add(this.cmbFromCountries, "4, 20, left, top");
		
		
		//Set up the countries
		
	
		loadCountries(this.cmbFromCountries, this.cmbToCountries);
		
		//Initialize the fields
		

		add(this.cmbToCountries, "11, 20, left, top");
	

		add(this.cmbFromStates, "4, 22, left, top");
		
	
		


		add(cmbToStates, "11, 22, left, top");
		
	
		
		add(lblStartCity,"2, 24, right, center");
		add(lblStartState,"2, 22, right, center");
		add(lblStartCountry,"2, 20, right, center");
		
		//Get the countries
		allLocations = core.Location.LoadAll("");
		countries = new ArrayList<String>();
		states = new ArrayList<String>();
		cities = new ArrayList<String>();
		//Set up the travel modes
		loadTravelModes(allLocations.get(0));

		//Set up the cities

		add(cmbFromCities, "4, 24, left, top");
		
		
		add(lblEndCity,"9, 24, right, center");
		add(lblEndState,"9, 22, right, center");
		add(lblEndCountry,"9, 20, right, center");

		add(cmbToCities, "11, 24, left, top");
		
		
		
		btnCreateNewShipment = new JButton("Save");
		add(btnCreateNewShipment, "9, 26");
		
		btnCancel = new JButton("Cancel");
		add(btnCancel, "11, 26");
    	
    	
    	//Initialize the Segment table
    	

    	
		
	}//End of LocationForm Constructor
	
	protected void loadStates(JComboBox sourceCountry,JComboBox sourceStates)
	{
		if(sourceStates!=null && sourceCountry.getSelectedIndex()!=-1)
		{
			sourceStates.removeAllItems();
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct State from location where Country = '" + sourceCountry.getSelectedItem().toString()+"'");
				for(Map m :tmp)
				{
					sourceStates.addItem(m.get("State").toString());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	protected void loadCountries(JComboBox source1,JComboBox source2)
	{
		try
		{
			ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Country from location");
			for(Map m :tmp)
			{
				source1.addItem(m.get("Country").toString());
				source2.addItem(m.get("Country").toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		source1.setSelectedIndex(-1);
		source2.setSelectedIndex(-1);
	}
	protected void loadCities(JComboBox sourceCountry,JComboBox sourceStates,JComboBox sourceCities)
	{
		if(sourceCities!=null && sourceStates.getSelectedIndex()!=-1)
		{
			sourceCities.removeAllItems();
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Name from location where Country = '" + sourceCountry.getSelectedItem().toString()+"' and State = '" + sourceStates.getSelectedItem().toString()+"'");
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

}//End of LocationForm Class
