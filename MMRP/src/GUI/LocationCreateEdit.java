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
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Location;
import core.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocationCreateEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblLat, lblLon, lblCity,lblState,lblCountry, lblLocation, lblID, lblTravelModes;
	JTextField txtLat,txtLon,txtCity,txtState,txtCountry, txtID;
	private JComboBox<Vehicle.TravelModes> cbTravelMode1;
	private JComboBox<Vehicle.TravelModes> cbTravelMode2;
	private JComboBox<Vehicle.TravelModes> cbTravelMode3;
	private JComboBox<Vehicle.TravelModes> cbTravelMode4;
	private JComboBox<Vehicle.TravelModes> cbTravelMode5;
	private JComboBox<Vehicle.TravelModes> cbTravelMode6;
	private JButton btnSave;
	
	private boolean edit = false;
	private JButton btnCancel;
	
	public LocationCreateEdit(Location editLocation) {
		
		if(editLocation != null){
			edit = true;
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(63dlu;default):grow"),
				ColumnSpec.decode("max(49dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
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
		
		
		lblLat=new JLabel("Latitude:");
		lblLon = new JLabel("Longitute:");
		lblCity = new JLabel("City:");
		lblState = new JLabel("State:");
		lblCountry = new JLabel("Country:");
		lblLocation = new JLabel("Location");
		lblID = new JLabel("LocationID");
		
		txtLat=new JTextField(20);
		txtLon = new JTextField(20);
		txtCity= new JTextField(20);
		txtState = new JTextField(20);
		txtCountry = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblLocation, "4, 2, center, center");
		lblTravelModes = new JLabel("Travel Modes");
		add(lblTravelModes,"7, 2, right, top");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		cbTravelMode1 = new JComboBox();
		add(cbTravelMode1, "7, 4, fill, default");
		
		cbTravelMode2 = new JComboBox();
		add(cbTravelMode2, "7, 6, fill, default");
		
		cbTravelMode3 = new JComboBox();
		add(cbTravelMode3, "7, 8, fill, default");
		add(lblCity,"2,10,right,center");
		add(txtCity, "4,10,right,center");
		add(lblState,"2,8,right,center");
		add(txtState, "4,8,right,center");
		add(lblCountry,"2,6,right,center");
		add(txtCountry, "4,6,right,center");
		
		cbTravelMode4 = new JComboBox();
		add(cbTravelMode4, "7, 10, fill, default");
		add(lblLat,"2,12,right,center");
		add(txtLat, "4,12,right,center");
		
		cbTravelMode5 = new JComboBox();
		add(cbTravelMode5, "7, 12, fill, default");
		add(lblLon,"2,14,right,center");
		add(txtLon, "4,14,right,center");
		
		cbTravelMode6 = new JComboBox();
		add(cbTravelMode6, "7, 14, fill, default");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//This button will attempt to save the Location to the database
				Location newLocation = new Location();
				//Grab the travelLocations
				if(cbTravelMode1.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode1.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode1.getSelectedItem());
				}
				if(cbTravelMode2.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode2.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode2.getSelectedItem());
				}
				if(cbTravelMode3.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode3.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode3.getSelectedItem());
				}
				if(cbTravelMode4.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode4.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode4.getSelectedItem());
				}
				if(cbTravelMode5.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode5.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode5.getSelectedItem());
				}
				if(cbTravelMode6.getSelectedItem() != Vehicle.TravelModes.NONE){
					if(!newLocation.getTravelModes().contains(cbTravelMode6.getSelectedItem()))
						newLocation.addTravelMode((Vehicle.TravelModes)cbTravelMode6.getSelectedItem());
				}
				
				//Add the rest of the travelModes in as NONE
				for(int i = newLocation.getTravelModes().size(); i < 6; i++){
					newLocation.addTravelMode(Vehicle.TravelModes.NONE);
				}
				
				newLocation.setCountry(txtCountry.getText());
				newLocation.setState(txtState.getText());
				newLocation.setName(txtCity.getText());
				newLocation.setLatitude(Double.valueOf(txtLat.getText()));
				newLocation.setLongitude(Double.valueOf(txtLon.getText()));
				
				if(edit){
					newLocation.MarkDirty();
					newLocation.MarkOld();
					newLocation.setID(Integer.valueOf(txtID.getText()));
				}else{
					newLocation.MarkNew();
				}
				
				newLocation.Update();
				
			}
		});
		add(btnSave, "4, 17");
		
		txtID.setEditable(false);
		
		loadTravelComboBox(cbTravelMode1);
		cbTravelMode1.setSelectedItem(Vehicle.TravelModes.NONE);
		loadTravelComboBox(cbTravelMode2);
		cbTravelMode2.setSelectedItem(Vehicle.TravelModes.NONE);
		loadTravelComboBox(cbTravelMode3);
		cbTravelMode3.setSelectedItem(Vehicle.TravelModes.NONE);
		loadTravelComboBox(cbTravelMode4);
		cbTravelMode4.setSelectedItem(Vehicle.TravelModes.NONE);
		loadTravelComboBox(cbTravelMode5);
		cbTravelMode5.setSelectedItem(Vehicle.TravelModes.NONE);
		loadTravelComboBox(cbTravelMode6);
		cbTravelMode6.setSelectedItem(Vehicle.TravelModes.NONE);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnCancel, "7, 17");
		
		if(editLocation != null){
			loadLocation(editLocation);
		}else{
			
		}

	}//End of LocationCreateEdit Constructor
	
	public void loadLocation(Location edit){
		
		txtCity.setText(edit.getName());
    	txtState.setText(edit.getState());
    	txtCountry.setText(edit.getCountry());
    	txtLat.setText(String.valueOf(edit.getLatitude()));
    	txtLon.setText(String.valueOf(edit.getLongitude()));
    	txtID.setText(String.valueOf(edit.getID()));
    	
    	ArrayList<Vehicle.TravelModes> locationTravelTypes = edit.getTravelModes();
    	
    	if(locationTravelTypes.size() > 0)
    		cbTravelMode1.setSelectedItem(locationTravelTypes.get(0));
    	if(locationTravelTypes.size() > 1)
    		cbTravelMode2.setSelectedItem(locationTravelTypes.get(1));
    	if(locationTravelTypes.size() > 2)
    		cbTravelMode3.setSelectedItem(locationTravelTypes.get(2));
    	if(locationTravelTypes.size() > 3)
    		cbTravelMode4.setSelectedItem(locationTravelTypes.get(3));
    	if(locationTravelTypes.size() > 4)
    		cbTravelMode5.setSelectedItem(locationTravelTypes.get(4));
    	if(locationTravelTypes.size() > 5)
    		cbTravelMode6.setSelectedItem(locationTravelTypes.get(5));
    	
    	
    
		
	}//End of loadLocation(Location start)
	
	private void loadTravelComboBox(JComboBox<Vehicle.TravelModes> cbTravelMode){
		cbTravelMode.addItem(Vehicle.TravelModes.TRUCK);
		cbTravelMode.addItem(Vehicle.TravelModes.PLANE);
		cbTravelMode.addItem(Vehicle.TravelModes.RAIL);
		cbTravelMode.addItem(Vehicle.TravelModes.CARGO);
		cbTravelMode.addItem(Vehicle.TravelModes.BIKE);
		cbTravelMode.addItem(Vehicle.TravelModes.NONE);
		
	}
}
