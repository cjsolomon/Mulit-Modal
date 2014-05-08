package GUI.TruckForms;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GUI.TableRefreshListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Location;
import core.TravelType;
import core.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class TruckType extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblMinCapacity, lblMaxWeight,lblName, lblTruckType, lblID,lblTrailerOne,lblTrailerTwo;
	JTextField txtMinCapacity,txtMaxWeight,txtTypeName, txtID,txtTrailerOne,txtTrailerTwo;
	
	private boolean edit = false;
	private JLabel lblServiceType;
	private JTextField txtServiceType;
	private JCheckBox chckbxRadiation;
	private JCheckBox chckbxRefrigeration;
	private JButton btnEdit;
	private JLabel lblMaxCapacity;
	private JTextField txtMaxCapacity;
	private JCheckBox chckbxHazardousMaterial;
	private JCheckBox chckbxExplosiveMaterial;
	private JCheckBox chckbxTracking;
	private JButton btnSave,btnCancel;
	private TravelType source;
	private ArrayList<TableRefreshListener> refresh;
	public TruckType() {
		
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default):grow"),
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
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		lblTrailerTwo = new JLabel("Trailer 2");
		lblTrailerOne = new JLabel("Trailer 1");
		lblName = new JLabel("Type Name");
		lblTruckType = new JLabel("Truck Type");
		lblID = new JLabel("TypeID");
		txtTrailerOne = new JTextField(20);

		txtTypeName = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblTruckType, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		chckbxRadiation = new JCheckBox("Radiation");
		
				add(chckbxRadiation, "6, 6");
		
		chckbxRefrigeration = new JCheckBox("Refrigeration");
		
				add(chckbxRefrigeration, "6, 8");
		add(lblTrailerTwo,"2,10,right,center");
		add(lblTrailerOne,"2,8,right,center");
		add(txtTrailerOne, "4,8,right,center");
		add(lblName,"2,6,right,center");
		add(txtTypeName, "4,6,right,center");
		txtTrailerTwo= new JTextField(20);
		add(txtTrailerTwo, "4, 10, right, center");
		
		chckbxHazardousMaterial = new JCheckBox("Hazardous Material");
		
				add(chckbxHazardousMaterial, "6, 10");
		
		lblServiceType = new JLabel("Service Type");
		add(lblServiceType, "2, 12, right, top");
		
		this.txtServiceType = new JTextField(10);

		add(txtServiceType, "4, 12, fill, default");
		
		chckbxExplosiveMaterial = new JCheckBox("Explosive Material");
		
				add(chckbxExplosiveMaterial, "6, 12");
		
		
		lblMinCapacity=new JLabel("Min. Capacity");
		add(lblMinCapacity,"2, 14, right, center");
		
		this.txtMinCapacity=new JTextField(20);

		add(txtMinCapacity, "4, 14, right, center");
		
		txtID.setEditable(false);
					
							
							chckbxTracking = new JCheckBox("Tracking Available");
							
									add(chckbxTracking, "6, 14");
					
					lblMaxCapacity = new JLabel("Max. Capacity");
					add(lblMaxCapacity, "2, 16, right, default");
					
					this.txtMaxCapacity = new JTextField(10);
					add(txtMaxCapacity, "4, 16, fill, default");
			
					lblMaxWeight = new JLabel("Max Weight");
					add(lblMaxWeight,"2, 18, right, center");
					this.txtMaxWeight = new JTextField(20);
					add(txtMaxWeight, "4, 18, right, center");
				
					btnEdit = new JButton("Edit");
					btnEdit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							btnEdit.setVisible(false);
							btnSave.setVisible(true);
							setEditable();
						}
					});
					btnEdit.setEnabled(true);
					add(btnEdit, "4,20,right, default");
					
							
					
					btnSave = new JButton("Save");
					btnSave.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							update();
						}
					});
					btnSave.setVisible(false);
					add(btnSave, "4, 20, right, default");
					
					btnCancel = new JButton("Cancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(btnEdit.isVisible()||source==null)
							{
								setVisible(false);
							}
							else
							{
								
								btnEdit.setVisible(true);
								btnSave.setVisible(false);
								setReadOnly();
							}
							
						}
					});
					add(btnCancel, "6, 20, left, default");
		
					//Tool tips
					this.btnCancel.setToolTipText("Click here to cancel changes");
					this.btnEdit.setToolTipText("Click here to edit this type");
					this.btnSave.setToolTipText("Click here to save changes");
					this.chckbxExplosiveMaterial.setToolTipText("Allows this type to carry explosive shipments");
					this.chckbxHazardousMaterial.setToolTipText("Allows this type to carry hazardous shipments");
					this.chckbxRadiation.setToolTipText("Allows this type to carry radioactive shipments");
					this.chckbxRefrigeration.setToolTipText("Allows this type to carry refrigerated shipments");
					this.chckbxTracking.setToolTipText("Allows this type to be tracked");
					this.txtMaxCapacity.setToolTipText("This is the maximum capacity this type can carry");
					this.txtMaxWeight.setToolTipText("This is the maximum weight this type can carry");
					this.txtMinCapacity.setToolTipText("This is the minimum capacity this type must have to be used");
					this.txtServiceType.setToolTipText("Currently not supported");
					this.txtTypeName.setToolTipText("This is the name of the type");
					
					this.lblMaxCapacity.setToolTipText("This is the maximum capacity this type can carry");
					this.lblMaxWeight.setToolTipText("This is the maximum weight this type can carry");
					this.lblMinCapacity.setToolTipText("This is the minimum capacity this type must have to be used");
					this.lblServiceType.setToolTipText("Currently not supported");
					this.lblName.setToolTipText("This is the name of the type");
					
					
	}//End of LocationCreateEdit Constructor
	
	
	
	public void showPanel()
	{
		source = null;
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
		loadNew();
		setEditable();
		setVisible(true);
	}
	public void showPanel(TravelType t)
	{
		if(t!=null)
			source = t;
		loadType();
		setReadOnly();
		btnSave.setVisible(false);
		btnEdit.setVisible(true);
		setVisible(true);
	}
	private void loadNew()
	{
		this.txtServiceType.setText("");
		this.txtMaxCapacity.setText("");
		this.txtMaxWeight.setText("");
		this.txtMinCapacity.setText("");
		this.txtTypeName.setText("");
		this.txtID.setText("");
		this.chckbxExplosiveMaterial.setSelected(false);
		this.chckbxHazardousMaterial.setSelected(false);
		this.chckbxRadiation.setSelected(false);
		this.chckbxRefrigeration.setSelected(false);
		this.chckbxTracking.setSelected(false);
		this.txtTrailerOne.setText("");
		this.txtTrailerTwo.setText("");
	}
	private void loadType(){
		this.txtServiceType.setText(source.getServiceType());
		this.txtMaxCapacity.setText(((Double)source.getMaxCap()).toString());
		this.txtMaxWeight.setText(((Double)source.getMaxWeight()).toString());
		this.txtMinCapacity.setText(((Double)source.getMinCap()).toString());
		this.txtTypeName.setText(source.getTravelTypeName());
		this.txtID.setText(((Integer)source.getVehicleTypeID()).toString());
		this.chckbxExplosiveMaterial.setSelected(source.getExplosives());
		this.chckbxHazardousMaterial.setSelected(source.getHazmat());
		this.chckbxRadiation.setSelected(source.getRadiation());
		this.chckbxRefrigeration.setSelected(source.getRefridgeration());
		this.chckbxTracking.setSelected(source.getTracking());
		this.txtTrailerOne.setText(source.getTrailer1());
		this.txtTrailerTwo.setText(source.getTrailer2());
	}
	private void setReadOnly()
	{
		
		this.txtMaxCapacity.setToolTipText("This is the maximum capacity this type can carry");
		this.txtMaxWeight.setToolTipText("This is the maximum weight this type can carry");
		this.txtMinCapacity.setToolTipText("This is the minimum capacity this type must have to be used");
		this.txtServiceType.setToolTipText("Currently not supported");
		this.txtTypeName.setToolTipText("This is the name of the type");
		
		this.txtServiceType.setEnabled(false);
		this.txtMaxCapacity.setEnabled(false);
		this.txtMaxWeight.setEnabled(false);
		this.txtMinCapacity.setEnabled(false);
		this.txtTypeName.setEnabled(false);
		
		this.chckbxExplosiveMaterial.setEnabled(false);
		this.chckbxHazardousMaterial.setEnabled(false);
		this.chckbxRadiation.setEnabled(false);
		this.chckbxRefrigeration.setEnabled(false);
		this.chckbxTracking.setEnabled(false);
		this.txtTrailerOne.setEnabled(false);
		this.txtTrailerTwo.setEnabled(false);
		
		
	}
	private void setEditable()
	{
		this.txtMaxCapacity.setToolTipText("Enter a maximum capacity greater than 0");
		this.txtMaxWeight.setToolTipText("Enter a maximum weight greater than 0");
		this.txtMinCapacity.setToolTipText("Enter a minimum capacity greater than or equal to 0");
		this.txtServiceType.setToolTipText("Currently not supported");
		this.txtTypeName.setToolTipText("Enter a Truck Type name between 1 and 45 characters");
		
		this.txtServiceType.setEnabled(true);
		this.txtMaxCapacity.setEnabled(true);
		this.txtMaxWeight.setEnabled(true);
		this.txtMinCapacity.setEnabled(true);
		this.txtTypeName.setEnabled(true);
		
		this.chckbxExplosiveMaterial.setEnabled(true);
		this.chckbxHazardousMaterial.setEnabled(true);
		this.chckbxRadiation.setEnabled(true);
		this.chckbxRefrigeration.setEnabled(true);
		this.chckbxTracking.setEnabled(true);
		this.txtTrailerOne.setEnabled(true);
		this.txtTrailerTwo.setEnabled(true);
	}
	private void update()
	{
		String errorString = "";
		//Error Checking
		if(this.txtTypeName.getText().isEmpty() || this.txtTypeName.getText().length() < 1 ||  this.txtTypeName.getText().length() > 45)
			errorString += "The Type Name was not a valid entry between 1 and 45 characters.\n";
		if(this.txtMaxWeight.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtMaxWeight.getText()),0, core.TravelType.getDefaultMaximumWeight()))
			errorString += "The Maximum Weight was not in the range of " + 0 + " and " + core.TravelType.getDefaultMaximumWeight() + ".\n";
		if(this.txtMinCapacity.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtMinCapacity.getText()),0, core.TravelType.getDefaultMaximumCapacity()))
			errorString += "The Minimum Capacity was not in the range of " + core.TravelType.getDefaultMinimumCapacity() + " and " + core.TravelType.getDefaultMaximumCapacity() + ".\n";
		if(this.txtMaxCapacity.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtMaxCapacity.getText()),core.TravelType.getDefaultMinimumCapacity(), core.TravelType.getDefaultMaximumCapacity()))
			errorString += "The Maximum Capacity was not in the range of " + core.TravelType.getDefaultMinimumCapacity() + " and " + core.TravelType.getDefaultMaximumCapacity() + ".\n";
		if(errorString.isEmpty())
		{
			if(source==null)
			{
				source = new TravelType();
				source.setTravelTypeMode(Vehicle.TravelModes.TRUCK.toString());
			}
			source.setExp(this.chckbxExplosiveMaterial.isSelected());
			source.setHaz(this.chckbxHazardousMaterial.isSelected());
			source.setMaxCap(Double.parseDouble(this.txtMaxCapacity.getText()));
			source.setMaxWeight(Double.parseDouble(this.txtMaxWeight.getText()));
			source.setMinCap(Double.parseDouble(this.txtMinCapacity.getText()));
			source.setRad(this.chckbxRadiation.isSelected());
			source.setRef(this.chckbxRefrigeration.isSelected());
			source.setServiceType(this.txtServiceType.getText());
			source.setTracking(this.chckbxTracking.isSelected());
			source.setTravelTypeName(this.txtTypeName.getText());
			source.setTrailer1(this.txtTrailerOne.getText());
			source.setTrailer2(this.txtTrailerTwo.getText());
			source.Update();
			this.txtID.setText(((Integer)source.getVehicleTypeID()).toString());
			//this.txtID.setText(((Integer)source.getVehicleTypeID()).toString());
			for(TableRefreshListener r: refresh)
				r.refreshTable();
			setReadOnly();
			btnEdit.setVisible(true);
			btnSave.setVisible(false);
		}
		else{
			//An error occurred
			JOptionPane.showMessageDialog(null, errorString , "Invalid data entered", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void addTableRefreshListener(TableRefreshListener add)
	{
		if(refresh==null)
			refresh = new ArrayList<TableRefreshListener>();
		refresh.add(add);
	}
}
