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
import javax.swing.JCheckBox;

public class TruckType extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblMinCapacity, lblMaxWeight, lblTrailer2,lblTrailer1,lblName, lblTruckType, lblID;
	JTextField txtEmail,txtContractDate,txtFaxNumber,txtCarrierCode,txtCarrierName, txtID;
	private JButton btnSearch;
	
	private boolean edit = false;
	private JButton btnCancel;
	private JLabel lblServiceType;
	private JTextField txtAuthorize;
	private JCheckBox chckbxRadiation;
	private JCheckBox chckbxRefrigeration;
	private JButton btnEdit;
	private JButton btnNew;
	private JLabel lblMaxCapacity;
	private JTextField textField;
	private JCheckBox chckbxHazardousMaterial;
	private JCheckBox chckbxExplosiveMaterial;
	private JCheckBox chckbxTracking;
	
	public TruckType(Location editLocation) {
		
		if(editLocation != null){
			edit = true;
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default):grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(63dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		lblTrailer2 = new JLabel("Trailer 2");
		lblTrailer1 = new JLabel("Trailer 1");
		lblName = new JLabel("Type Name");
		lblTruckType = new JLabel("Truck Type");
		lblID = new JLabel("TypeID");
		txtCarrierCode = new JTextField(20);
		txtCarrierCode.setEditable(false);
		txtCarrierName = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblTruckType, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		chckbxRadiation = new JCheckBox("Radiation");
		chckbxRadiation.setEnabled(false);
		add(chckbxRadiation, "7, 4");
		
		chckbxRefrigeration = new JCheckBox("Refrigeration");
		chckbxRefrigeration.setEnabled(false);
		add(chckbxRefrigeration, "7, 6");
		
		chckbxHazardousMaterial = new JCheckBox("Hazardous Material");
		chckbxHazardousMaterial.setEnabled(false);
		add(chckbxHazardousMaterial, "7, 8");
		add(lblTrailer2,"2,10,right,center");
		add(lblTrailer1,"2,8,right,center");
		add(txtCarrierCode, "4,8,right,center");
		add(lblName,"2,6,right,center");
		add(txtCarrierName, "4,6,right,center");
		txtFaxNumber= new JTextField(20);
		txtFaxNumber.setEditable(false);
		add(txtFaxNumber, "4, 10, right, center");
		
		chckbxExplosiveMaterial = new JCheckBox("Explosive Material");
		chckbxExplosiveMaterial.setEnabled(false);
		add(chckbxExplosiveMaterial, "7, 10");
		
		lblServiceType = new JLabel("Service Type");
		add(lblServiceType, "2, 12, right, top");
		
		txtAuthorize = new JTextField();
		txtAuthorize.setEditable(false);
		add(txtAuthorize, "4, 12, fill, default");
		txtAuthorize.setColumns(10);
		
		chckbxTracking = new JCheckBox("Tracking Available");
		chckbxTracking.setEnabled(false);
		add(chckbxTracking, "7, 12");
		
		
		lblMinCapacity=new JLabel("Min. Capacity");
		add(lblMinCapacity,"2, 14, right, center");
		
		txtEmail=new JTextField(20);
		txtEmail.setEditable(false);
		add(txtEmail, "4, 14, right, center");
		
		lblMaxCapacity = new JLabel("Max. Capacity");
		add(lblMaxCapacity, "7, 14, right, default");
		
		textField = new JTextField();
		textField.setEditable(false);
		add(textField, "9, 14, fill, default");
		textField.setColumns(10);
		lblMaxWeight = new JLabel("Max Weight");
		add(lblMaxWeight,"2, 16, right, center");
		txtContractDate = new JTextField(20);
		txtContractDate.setEditable(false);
		add(txtContractDate, "4, 16, right, center");
		
		btnNew = new JButton("New");
		add(btnNew, "2, 20");
		
		txtID.setEditable(false);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//This button will attempt to save the Location to the database
				Location newLocation = new Location();
				//Grab the travelLocations

				
				//Add the rest of the travelModes in as NONE
				for(int i = newLocation.getTravelModes().size(); i < 6; i++){
					newLocation.addTravelMode(Vehicle.TravelModes.NONE);
				}
				
				newLocation.setCountry(txtCarrierName.getText());
				newLocation.setState(txtCarrierCode.getText());
				newLocation.setName(txtFaxNumber.getText());
				newLocation.setLatitude(Double.valueOf(txtEmail.getText()));
				newLocation.setLongitude(Double.valueOf(txtContractDate.getText()));
				
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
		add(btnSearch, "4, 20");
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		add(btnEdit, "7, 20");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnCancel, "9, 20");
		
		if(editLocation != null){
			loadLocation(editLocation);
		}else{
			
		}

	}//End of LocationCreateEdit Constructor
	
	public void loadLocation(Location edit){
		
		txtFaxNumber.setText(edit.getName());
    	txtCarrierCode.setText(edit.getState());
    	txtCarrierName.setText(edit.getCountry());
    	txtEmail.setText(String.valueOf(edit.getLatitude()));
    	txtContractDate.setText(String.valueOf(edit.getLongitude()));
    	txtID.setText(String.valueOf(edit.getID()));
    	
		
	}//End of loadLocation(Location start)
	
	private void loadTravelComboBox(JComboBox<Vehicle.TravelModes> cbTravelMode){
		
	}
}
