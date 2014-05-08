package GUI.CarrierForms;

import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.TableRefreshListener;
import GUI.TravelTypeSetListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.border.MatteBorder;
import com.jgoodies.forms.layout.Sizes;

import core.Carrier;
import core.TravelType;
import core.Vehicle;

import javax.swing.JButton;

public class CarrierForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblEmail, lblContractDate, lblNumber,lblCode,lblName, lblLocation, lblID;
	JTextField txtEmail,txtContractDate,txtFaxNumber,txtCode,txtName, txtID;
	private JButton btnEdit,btnSave;
	
	private Carrier source;
	private JButton btnCancel;
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
	private JTextField txtTruck;
	private JTextField txtCargo;
	private JTextField txtPlane;
	private JTextField txtRail;
	private JCheckBox chckbxFaxNumber;
	private JCheckBox chckbxEmail;
	private ArrayList<TableRefreshListener> refresh = new ArrayList<TableRefreshListener>();
	public CarrierForm() {
		
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
		
		txtEmail=new JTextField(20);
		txtEmail.setEditable(false);
		txtContractDate = new JTextField(20);
		txtCode = new JTextField(20);
		txtName = new JTextField(20);
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
		add(txtCode, "4,8,right,center");
		add(lblName,"2,6,right,center");
		add(txtName, "4,6,right,center");
		txtFaxNumber= new JTextField(20);
		txtFaxNumber.setEditable(false);
		add(txtFaxNumber, "4, 10, right, center");
		add(lblEmail,"2,12,right,center");
		add(txtEmail, "4,12,right,center");
		add(lblContractDate,"2,14,right,center");
		add(txtContractDate, "4,14,right,center");
		
		btnEdit = new JButton("Edit");
		btnSave = new JButton("Save");
		lblCostModifiers = new JLabel("Cost Modifiers");
		add(lblCostModifiers, "7, 14, 3, 1, center, default");
		
		lblAuthorize = new JLabel("Authorize");
		add(lblAuthorize, "2, 16, right, top");
		
		txtAuthorize = new JTextField();
		add(txtAuthorize, "4, 16, fill, top");
		txtAuthorize.setColumns(10);
		
		lblTruck = new JLabel("Truck");
		add(lblTruck, "7, 16, right, default");
		
		txtTruck = new JTextField();
		txtTruck.setEditable(false);
		add(txtTruck, "9, 16, fill, default");
		txtTruck.setColumns(10);
		
		lblSatefyRating = new JLabel("Safety Rating");
		add(lblSatefyRating, "2, 18, right, default");
		
		txtSafetyRating = new JTextField();
		add(txtSafetyRating, "4, 18, fill, default");
		txtSafetyRating.setColumns(10);
		
		lblCargo = new JLabel("Cargo");
		add(lblCargo, "7, 18, right, default");
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		add(txtCargo, "9, 18, fill, default");
		txtCargo.setColumns(10);
		
		lblSafetyRatingDate = new JLabel("Rating Date");
		add(lblSafetyRatingDate, "2, 20, right, default");
		
		txtRatingDate = new JTextField();
		add(txtRatingDate, "4, 20, fill, default");
		txtRatingDate.setColumns(10);
		
		lblPlane = new JLabel("Plane");
		add(lblPlane, "7, 20, right, default");
		
		txtPlane = new JTextField();
		txtPlane.setEditable(false);
		add(txtPlane, "9, 20, fill, default");
		txtPlane.setColumns(10);
		
		lblInsEndDate = new JLabel("Ins End Date");
		add(lblInsEndDate, "2, 22, right, default");
		
		txtInsEndDate = new JTextField();
		add(txtInsEndDate, "4, 22, fill, default");
		txtInsEndDate.setColumns(10);
		
		lblRail = new JLabel("Rail");
		add(lblRail, "7, 22, right, default");
		
		txtRail = new JTextField();
		txtRail.setEditable(false);
		add(txtRail, "9, 22, fill, default");
		txtRail.setColumns(10);
		add(btnEdit, "4, 25");
		add(btnSave,"4,25");
		txtID.setEditable(false);
		
		btnCancel = new JButton("Cancel");

		add(btnCancel, "7, 25");
		
		btnSave.setToolTipText("Save changes made");
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				update();
				btnSave.setVisible(false);
				setReadOnly();
				btnEdit.setVisible(true);
			}
		});
	
		btnEdit.setToolTipText("Edit the selected Carrier");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnSave.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		
		btnCancel.setToolTipText("Cancel changes made");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSave.isVisible()&& source!=null)
				{
					setReadOnly();
					btnSave.setVisible(false);
					btnEdit.setVisible(true);
					
				}
				else
				{
					setVisible(false);
				}
			}
		});
		
		
		lblAuthorize.setToolTipText("Currently not supported");
		lblCargo.setToolTipText("Currently not supported");
		lblPlane.setToolTipText("Currently not supported");
		lblRail.setToolTipText("Currently not supported");
		lblTruck.setToolTipText("Currently not supported");
		lblCode.setToolTipText("This is the short code for the Carrier");
		lblContractDate.setToolTipText("Currently not supported");
		lblEmail.setToolTipText("This is the email address the Carrier can be reached at");
		lblNumber.setToolTipText("This is the fax number the Carrier can be reached at");
		lblInsEndDate.setToolTipText("Currently not supported");
		lblName.setToolTipText("This is the name of the Carrier");
		lblSafetyRatingDate.setToolTipText("Currently not supported");
		lblSatefyRating.setToolTipText("This is the safety rating of the Carrier");
		chckbxEmail.setToolTipText("Allows the Carrier to be reached by email");
		chckbxFaxNumber.setToolTipText("Allows the Carrier to be reached by fax");
		
		}

		public void showPanel()
		{
			source=null;
			setEditable();
			setNew();
			
			this.setVisible(true);
		}

		public void showPanel(Carrier c)
		{
			source = c;
			displayCarrier();
			setReadOnly();
			setVisible(true);
		}
	
		
		private void update()
		{
			
			String errorString = "";
			//Error Checking
			if(this.txtName.getText().isEmpty() || this.txtName.getText().length() < 1 ||  this.txtName.getText().length() > 45)
				errorString += "The Carrier Name was not a valid entry between 1 and 45 characters.\n";
			if(this.txtCode.getText().isEmpty() || this.txtCode.getText().length() < 1 ||  this.txtCode.getText().length() > 4)
				errorString += "The Carrier Code was not a valid entry between 1 and 4 characters.\n";
			if(this.txtEmail.getText().isEmpty() || !core.FormatChecker.isValidEmail(this.txtEmail.getText()))
				errorString += "The email address was not valid. Please enter an email address with the following format,\n\t(any alphanumeric string)@(any alphanumeric string).(2-4 alphabetic characters)\n";
			if(this.txtFaxNumber.getText().isEmpty() || !core.FormatChecker.isValidPhone(this.txtFaxNumber.getText()))
				errorString += "The phone number was not a valid entry, please enter a ten digit number with the following format,\n\t###-###-####.\n";
			if(this.txtSafetyRating.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtSafetyRating.getText()),0, 100))
				errorString += "The safety rating entered was not a valid entry, please enter a digit between 0 and 100 inclusive.\n\tNote: the higher the safety rating, the more safe the Carrier.\n";
			if(errorString.isEmpty())
			{
				if(source==null)
				{
					source = new Carrier();
				}
				source.setAuthorize(Integer.parseInt(this.txtAuthorize.getText()));
				source.setCarrierCode(this.txtCode.getText());
				source.setCarrierName(this.txtName.getText());
				source.setContractDate(this.txtContractDate.getText());
				source.setEmailAddress(this.txtEmail.getText());
				source.setFaxNumber(this.txtFaxNumber.getText());
				source.setInsEndDate(this.txtInsEndDate.getText());
				source.setSafetyRateDate(this.txtRatingDate.getText());
				source.setSafetyRating(Integer.parseInt(this.txtSafetyRating.getText()));
				if(this.chckbxEmail.isSelected())
					source.setSendByEmailTrue();
				else
					source.setSendByEmailFalse();
				
				if(this.chckbxFaxNumber.isSelected())
					source.setSendByFaxTrue();
				else
					source.setSendByFaxFalse();
				
				
				source.Update();
				this.txtID.setText(((Integer)source.getId()).toString());
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
		
		private void displayCarrier(){
			this.txtID.setText(String.valueOf(source.getId()));
			this.txtAuthorize.setText(String.valueOf(source.getAuthorize()));
			this.txtCargo.setText(String.valueOf(source.getCostModifierCargoShip()));
			this.txtCode.setText(source.getCarrierCode());
			this.txtContractDate.setText(source.getContractDate());
			this.txtEmail.setText(source.getEmailAddress());
			this.txtFaxNumber.setText(source.getFaxNumber());
			this.txtInsEndDate.setText(source.getInsEndDate());
			this.txtName.setText(source.getCarrierName());
			this.txtPlane.setText(String.valueOf(source.getCostModifierPlane()));
			this.txtRail.setText(String.valueOf(source.getCostModifierRail()));
			this.txtRatingDate.setText(source.getSafetyRateDate());
			this.txtSafetyRating.setText(String.valueOf(source.getSafetyRating()));
			this.txtTruck.setText(String.valueOf(source.getCostModifierTruck()));
			
			if(source.isSendByEmail())
				this.chckbxEmail.setSelected(true);
			else
				this.chckbxEmail.setSelected(false);
			if(source.isSendByFax())
				this.chckbxFaxNumber.setSelected(true);
			else
				this.chckbxFaxNumber.setSelected(true);
			
			this.chckbxEmail.setEnabled(false);
			this.chckbxFaxNumber.setEnabled(false);
			btnEdit.setVisible(true);
			btnSave.setVisible(false);
		}
		
		private void setEditable()
		{
			txtAuthorize.setToolTipText("Currently not supported");
			txtCargo.setToolTipText("Currently not supported");
			txtPlane.setToolTipText("Currently not supported");
			txtRail.setToolTipText("Currently not supported");
			txtTruck.setToolTipText("Currently not supported");
			txtCode.setToolTipText("Enter a code between 1 and 4 character");
			txtContractDate.setToolTipText("Currently not supported");
			txtEmail.setToolTipText("Format :(any alphanumeric string)@(any alphanumeric string).(2-4 alphabetic characters)");
			txtFaxNumber.setToolTipText("Format : ###-###-####");
			txtInsEndDate.setToolTipText("Currently not supported");
			txtName.setToolTipText("Enter a character name between 1 and 45 characters.");
			txtRatingDate.setToolTipText("Currently not supported");
			txtSafetyRating.setToolTipText("Enter a safety rating value between 0 and 100");
			chckbxEmail.setToolTipText("Allows the Carrier to be reached by email");
			chckbxFaxNumber.setToolTipText("Allows the Carrier to be reached by fax");
			
			this.txtAuthorize.setEditable(false);
			this.txtCargo.setEditable(false);
			this.txtCode.setEditable(true);
			this.txtContractDate.setEditable(false);
			this.txtEmail.setEditable(true);
			this.txtFaxNumber.setEditable(true);
			this.txtInsEndDate.setEditable(false);
			this.txtName.setEditable(true);
			this.txtPlane.setEditable(false);
			this.txtRail.setEditable(false);
			this.txtRatingDate.setEditable(false);
			this.txtSafetyRating.setEditable(true);
			this.txtTruck.setEditable(false);
			
			this.chckbxEmail.setEnabled(true);
			this.chckbxFaxNumber.setEnabled(true);
			btnSave.setVisible(true);
			btnEdit.setVisible(false);
		
			
		}
		
		private void setReadOnly()
		{
			txtAuthorize.setToolTipText("Currently not supported");
			txtCargo.setToolTipText("Currently not supported");
			txtPlane.setToolTipText("Currently not supported");
			txtRail.setToolTipText("Currently not supported");
			txtTruck.setToolTipText("Currently not supported");
			txtCode.setToolTipText(this.lblCode.getToolTipText());
			txtContractDate.setToolTipText("Currently not supported");
			txtEmail.setToolTipText(this.lblEmail.getToolTipText());
			txtFaxNumber.setToolTipText(this.lblNumber.getToolTipText());
			txtInsEndDate.setToolTipText("Currently not supported");
			txtName.setToolTipText(this.lblName.getToolTipText());
			txtRatingDate.setToolTipText("Currently not supported");
			txtSafetyRating.setToolTipText(this.lblSatefyRating.getToolTipText());
			chckbxEmail.setToolTipText("Allows the Carrier to be reached by email");
			chckbxFaxNumber.setToolTipText("Allows the Carrier to be reached by fax");
			
			this.txtAuthorize.setEditable(false);
			this.txtCargo.setEditable(false);
			this.txtCode.setEditable(false);
			this.txtContractDate.setEditable(false);
			this.txtEmail.setEditable(false);
			this.txtFaxNumber.setEditable(false);
			this.txtInsEndDate.setEditable(false);
			this.txtName.setEditable(false);
			this.txtPlane.setEditable(false);
			this.txtRail.setEditable(false);
			this.txtRatingDate.setEditable(false);
			this.txtSafetyRating.setEditable(false);
			this.txtTruck.setEditable(false);
			
			this.chckbxEmail.setEnabled(false);
			this.chckbxFaxNumber.setEnabled(false);
			btnSave.setVisible(false);
			btnEdit.setVisible(true);
			

		}
		
		private void setNew()
		{
			
			this.txtID.setText("");
			this.txtAuthorize.setText("1");
			this.txtCargo.setText("");
			this.txtCode.setText("");
			this.txtContractDate.setText("01/01/1970");
			this.txtEmail.setText("");
			this.txtFaxNumber.setText("");
			this.txtInsEndDate.setText("01/01/1970");
			this.txtName.setText("");
			this.txtPlane.setText("");
			this.txtRail.setText("");
			this.txtRatingDate.setText("01/01/1970");
			this.txtSafetyRating.setText("");
			this.txtTruck.setText("");
			

			this.chckbxEmail.setSelected(false);
			this.chckbxFaxNumber.setSelected(false);	
			this.chckbxEmail.setEnabled(false);
			this.chckbxFaxNumber.setEnabled(false);
			btnEdit.setVisible(false);
			btnSave.setVisible(true);
		}


		public void addTableRefreshListener(TableRefreshListener add)
		{
			if(refresh==null)
				refresh = new ArrayList<TableRefreshListener>();
			refresh.add(add);
		}
	}


