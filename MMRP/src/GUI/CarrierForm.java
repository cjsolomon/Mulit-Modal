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

public class CarrierForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblEmail, lblContractDate, lblNumber,lblCode,lblName, lblCarrier, lblID;
	JTextField txtEmail,txtContractDate,txtFaxNumber,txtCarrierCode,txtCarrierName, txtID;
	private JButton btnSearch;
	
	private boolean edit = false;
	private JButton btnCancel;
	private JLabel lblAreaCode;
	private JTextField txtAreaCode;
	private JLabel lblAuthorize;
	private JTextField txtAuthorize;
	private JLabel lblSatefyRating;
	private JLabel lblSafetyRatingDate;
	private JTextField txtSafetyRating;
	private JTextField txtRatingDate;
	private JLabel lblInsEndDate;
	private JTextField txtInsEndDate;
	private JLabel lblCostModifiers;
	private JLabel lblTruck;
	private JLabel lblCargo;
	private JLabel lblPlane;
	private JLabel lblRail;
	private JTextField txtTruckMod;
	private JTextField txtCargoMod;
	private JTextField txtPlaneMod;
	private JTextField txtRailMod;
	private JCheckBox chckbxFaxNumber;
	private JCheckBox chckbxEmail;
	private JButton btnEdit;
	private JButton btnNew;
	
	public CarrierForm(Location editLocation) {
		
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
				ColumnSpec.decode("max(49dlu;default):grow"),
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
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		
		lblEmail=new JLabel("Email");
		lblContractDate = new JLabel("Contract Date");
		lblNumber = new JLabel("Fax Number");
		lblCode = new JLabel("Carrier Code");
		lblName = new JLabel("Carrier Name");
		lblCarrier = new JLabel("Carrier");
		lblID = new JLabel("CarrierID");
		
		txtEmail=new JTextField(20);
		txtEmail.setEditable(false);
		txtContractDate = new JTextField(20);
		txtContractDate.setEditable(false);
		txtCarrierCode = new JTextField(20);
		txtCarrierName = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblCarrier, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		chckbxFaxNumber = new JCheckBox("Fax Number");
		chckbxFaxNumber.setEnabled(false);
		add(chckbxFaxNumber, "7, 4");
		
		chckbxEmail = new JCheckBox("Email");
		chckbxEmail.setEnabled(false);
		add(chckbxEmail, "7, 6");
		add(lblNumber,"2,10,right,center");
		add(lblCode,"2,8,right,center");
		add(txtCarrierCode, "4,8,right,center");
		add(lblName,"2,6,right,center");
		add(txtCarrierName, "4,6,right,center");
		txtFaxNumber= new JTextField(20);
		txtFaxNumber.setEditable(false);
		add(txtFaxNumber, "4, 10, right, center");
		
		lblAreaCode = new JLabel("Area Code");
		add(lblAreaCode, "7, 10, right, default");
		
		txtAreaCode = new JTextField();
		txtAreaCode.setEditable(false);
		add(txtAreaCode, "9, 10, fill, default");
		txtAreaCode.setColumns(10);
		add(lblEmail,"2,12,right,center");
		add(txtEmail, "4,12,right,center");
		add(lblContractDate,"2,14,right,center");
		add(txtContractDate, "4,14,right,center");
		
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
		
		lblCostModifiers = new JLabel("Cost Modifiers");
		add(lblCostModifiers, "7, 14, 3, 1, center, default");
		
		lblAuthorize = new JLabel("Authorize");
		add(lblAuthorize, "2, 16, right, top");
		
		txtAuthorize = new JTextField();
		txtAuthorize.setEditable(false);
		add(txtAuthorize, "4, 16, fill, default");
		txtAuthorize.setColumns(10);
		
		lblTruck = new JLabel("Truck");
		add(lblTruck, "7, 16, right, default");
		
		txtTruckMod = new JTextField();
		txtTruckMod.setEditable(false);
		add(txtTruckMod, "9, 16, fill, top");
		txtTruckMod.setColumns(10);
		
		lblSatefyRating = new JLabel("Safety Rating");
		add(lblSatefyRating, "2, 18, right, default");
		
		txtSafetyRating = new JTextField();
		txtSafetyRating.setEditable(false);
		add(txtSafetyRating, "4, 18, fill, default");
		txtSafetyRating.setColumns(10);
		
		lblCargo = new JLabel("Cargo");
		add(lblCargo, "7, 18, right, default");
		
		txtCargoMod = new JTextField();
		txtCargoMod.setEditable(false);
		add(txtCargoMod, "9, 18, fill, default");
		txtCargoMod.setColumns(10);
		
		lblSafetyRatingDate = new JLabel("Rating Date");
		add(lblSafetyRatingDate, "2, 20, right, default");
		
		txtRatingDate = new JTextField();
		txtRatingDate.setEditable(false);
		add(txtRatingDate, "4, 20, fill, default");
		txtRatingDate.setColumns(10);
		
		lblPlane = new JLabel("Plane");
		add(lblPlane, "7, 20, right, default");
		
		txtPlaneMod = new JTextField();
		txtPlaneMod.setEditable(false);
		add(txtPlaneMod, "9, 20, fill, top");
		txtPlaneMod.setColumns(10);
		
		lblInsEndDate = new JLabel("Ins End Date");
		add(lblInsEndDate, "2, 22, right, default");
		
		txtInsEndDate = new JTextField();
		txtInsEndDate.setEditable(false);
		add(txtInsEndDate, "4, 22, fill, default");
		txtInsEndDate.setColumns(10);
		
		lblRail = new JLabel("Rail");
		add(lblRail, "7, 22, right, default");
		
		txtRailMod = new JTextField();
		txtRailMod.setEditable(false);
		add(txtRailMod, "9, 22, fill, default");
		txtRailMod.setColumns(10);
		
		btnNew = new JButton("New");
		add(btnNew, "2, 25");
		add(btnSearch, "4, 25");
		
		txtID.setEditable(false);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		add(btnEdit, "7, 25");
		add(btnCancel, "9, 25");
		
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
