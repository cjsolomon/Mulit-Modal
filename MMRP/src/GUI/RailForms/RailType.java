package GUI.RailForms;

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

public class RailType extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblMinCapacity, lblMaxWeight,lblName, lblRailType, lblID;
	JTextField txtMinCapacity,txtMaxWeight,txtTypeName, txtID;
	
	private boolean edit = false;
	private JButton btnCancel;
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
	private JButton btnSave;
	private TravelType source;
	private ArrayList<TableRefreshListener> refresh;
	public RailType() {
		
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(105dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(63dlu;default):grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		lblName = new JLabel("Type Name");
		lblRailType = new JLabel("Rail Type");
		lblID = new JLabel("TypeID");
		txtTypeName = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblRailType, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		chckbxRadiation = new JCheckBox("Radiation");
		add(chckbxRadiation, "6, 4");
		
		chckbxRefrigeration = new JCheckBox("Refrigeration");
		add(chckbxRefrigeration, "6, 6");
		
		lblServiceType = new JLabel("Service Type");
		add(lblServiceType, "2, 8, right, top");
		
		txtServiceType = new JTextField(10);
		add(txtServiceType, "4, 8, fill, default");
		
		
		chckbxHazardousMaterial = new JCheckBox("Hazardous Material");
		add(chckbxHazardousMaterial, "6, 8");
		add(lblName,"2,6,right,center");
		add(txtTypeName, "4, 6, left, center");
		lblMaxWeight = new JLabel("Max Weight");
		add(lblMaxWeight,"2, 10, right, center");
		
		txtMaxWeight=new JTextField(20);
		add(txtMaxWeight, "4, 10, left, center");
		
		chckbxExplosiveMaterial = new JCheckBox("Explosive Material");

		add(chckbxExplosiveMaterial, "6, 10");
		
		
		lblMinCapacity=new JLabel("Min. Capacity");
		add(lblMinCapacity,"2, 12, right, center");
		txtMinCapacity = new JTextField(20);

		add(txtMinCapacity, "4, 12, left, center");
		
		chckbxTracking = new JCheckBox("Tracking Available");
		add(chckbxTracking, "6, 12");
		
		lblMaxCapacity = new JLabel("Max. Capacity");
		add(lblMaxCapacity, "2, 14, right, default");
		
		txtID.setEditable(false);
		
		txtMaxCapacity = new JTextField(10);
		add(txtMaxCapacity, "4, 14, left, default");
			
			btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					update();
				}
			});
			btnSave.setVisible(false);
			add(btnSave, "4, 16, right, default");
		
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
			add(btnEdit, "4, 16, right, default");
		
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
		add(btnCancel, "6, 16, left, default");
		
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
	}
	private void setReadOnly()
	{
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
		
		
	}
	private void setEditable()
	{
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
				source.setTrailer1("");
				source.setTrailer2("");
				source.setTravelTypeMode(Vehicle.TravelModes.RAIL.toString());
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
