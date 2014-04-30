package GUI.CarrierForms;

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

public class CarrierCreateEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblEmail, lblContractDate, lblNumber,lblCode,lblName, lblLocation, lblID;
	JTextField txtLat,txtLon,txtCity,txtState,txtCountry, txtID;
	private JButton btnSave;
	
	private boolean edit = false;
	private JButton btnCancel;
	private JLabel lblAreaCode;
	private JTextField textField;
	private JLabel lblAuthorize;
	private JTextField textField_1;
	private JLabel lblSatefyRating;
	private JLabel lblSafetyRatingDate;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblInsEndDate;
	private JTextField textField_4;
	private JLabel lblCostModifiers;
	private JLabel lblTruck;
	private JLabel lblCargo;
	private JLabel lblPlane;
	private JLabel lblRail;
	private JTextField textField_6;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JCheckBox chckbxFaxNumber;
	private JCheckBox chckbxEmail;
	
	public CarrierCreateEdit(Location editLocation) {
		
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
		lblLocation = new JLabel("Carrier");
		lblID = new JLabel("CarrierID");
		
		txtLat=new JTextField(20);
		txtLat.setEditable(false);
		txtLon = new JTextField(20);
		txtState = new JTextField(20);
		txtCountry = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblLocation, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		chckbxFaxNumber = new JCheckBox("Fax Number");
		add(chckbxFaxNumber, "7, 4");
		
		chckbxEmail = new JCheckBox("Email");
		add(chckbxEmail, "7, 6");
		add(lblNumber,"2,10,right,center");
		add(lblCode,"2,8,right,center");
		add(txtState, "4,8,right,center");
		add(lblName,"2,6,right,center");
		add(txtCountry, "4,6,right,center");
		txtCity= new JTextField(20);
		txtCity.setEditable(false);
		add(txtCity, "4, 10, right, center");
		
		lblAreaCode = new JLabel("Area Code");
		add(lblAreaCode, "7, 10, right, default");
		
		textField = new JTextField();
		textField.setEditable(false);
		add(textField, "9, 10, fill, default");
		textField.setColumns(10);
		add(lblEmail,"2,12,right,center");
		add(txtLat, "4,12,right,center");
		add(lblContractDate,"2,14,right,center");
		add(txtLon, "4,14,right,center");
		
		btnSave = new JButton("Save");
		
		lblCostModifiers = new JLabel("Cost Modifiers");
		add(lblCostModifiers, "7, 14, 3, 1, center, default");
		
		lblAuthorize = new JLabel("Authorize");
		add(lblAuthorize, "2, 16, right, top");
		
		textField_1 = new JTextField();
		add(textField_1, "4, 16, fill, default");
		textField_1.setColumns(10);
		
		lblTruck = new JLabel("Truck");
		add(lblTruck, "7, 16, right, default");
		
		textField_6 = new JTextField();
		add(textField_6, "9, 16, fill, default");
		textField_6.setColumns(10);
		
		lblSatefyRating = new JLabel("Safety Rating");
		add(lblSatefyRating, "2, 18, right, default");
		
		textField_2 = new JTextField();
		add(textField_2, "4, 18, fill, default");
		textField_2.setColumns(10);
		
		lblCargo = new JLabel("Cargo");
		add(lblCargo, "7, 18, right, default");
		
		textField_5 = new JTextField();
		add(textField_5, "9, 18, fill, default");
		textField_5.setColumns(10);
		
		lblSafetyRatingDate = new JLabel("Rating Date");
		add(lblSafetyRatingDate, "2, 20, right, default");
		
		textField_3 = new JTextField();
		add(textField_3, "4, 20, fill, default");
		textField_3.setColumns(10);
		
		lblPlane = new JLabel("Plane");
		add(lblPlane, "7, 20, right, default");
		
		textField_7 = new JTextField();
		add(textField_7, "9, 20, fill, default");
		textField_7.setColumns(10);
		
		lblInsEndDate = new JLabel("Ins End Date");
		add(lblInsEndDate, "2, 22, right, default");
		
		textField_4 = new JTextField();
		add(textField_4, "4, 22, fill, default");
		textField_4.setColumns(10);
		
		lblRail = new JLabel("Rail");
		add(lblRail, "7, 22, right, default");
		
		textField_8 = new JTextField();
		add(textField_8, "9, 22, fill, default");
		textField_8.setColumns(10);
		add(btnSave, "4, 25");
		
		txtID.setEditable(false);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnCancel, "7, 25");
		
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
    	
		
	}//End of loadLocation(Location start)
	
	private void loadTravelComboBox(JComboBox<Vehicle.TravelModes> cbTravelMode){
		
	}
}
