package GUI.CarrierForms;

import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

import javax.swing.JButton;

public class CarrierForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblEmail, lblContractDate, lblNumber,lblCode,lblName, lblLocation, lblID;
	JTextField txtEmail,txtContractDate,txtFaxNumber,txtCode,txtName, txtID;
	private JButton btnSaveEdit;
	
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
	private JTextField txtTruck;
	private JTextField txtCargo;
	private JTextField txtPlane;
	private JTextField txtRail;
	private JCheckBox chckbxFaxNumber;
	private JCheckBox chckbxEmail;
	
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
		
		btnSaveEdit = new JButton("Edit");
		
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
		add(txtRail, "9, 22, fill, default");
		txtRail.setColumns(10);
		add(btnSaveEdit, "4, 25");
		
		txtID.setEditable(false);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnCancel, "7, 25");
		

	
		btnSaveEdit.setVisible(true);
		btnSaveEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEditable();
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSaveEdit.isVisible())
				{
					btnSaveEdit.setVisible(false);
					setReadOnly();
				}
				else
				{
					setVisible(false);
				}
			}
		});
		
		}

		public void showPanel()
		{
			setEditable();
			this.setVisible(true);
			this.btnSaveEdit.setText("Save");
		}

		public void showPanel(Carrier c)
		{
			displayCarrier(c);
			setReadOnly();
			setVisible(true);
		}
	
		
		private void update()
		{
			btnSaveEdit.setVisible(false);
		}
		
		private void displayCarrier(Carrier displayCarrier){
			this.txtID.setText(String.valueOf(displayCarrier.getId()));
			this.txtAreaCode.setText(displayCarrier.getAreaCode());
			this.txtAuthorize.setText(String.valueOf(displayCarrier.getAuthorize()));
			this.txtCargo.setText(String.valueOf(displayCarrier.getCostModifierCargoShip()));
			this.txtCode.setText(displayCarrier.getCarrierCode());
			this.txtContractDate.setText(displayCarrier.getContractDate());
			this.txtEmail.setText(displayCarrier.getEmailAddress());
			this.txtFaxNumber.setText(displayCarrier.getFaxNumber());
			this.txtInsEndDate.setText(displayCarrier.getInsEndDate());
			this.txtName.setText(displayCarrier.getCarrierName());
			this.txtPlane.setText(String.valueOf(displayCarrier.getCostModifierPlane()));
			this.txtRail.setText(String.valueOf(displayCarrier.getCostModifierRail()));
			this.txtRatingDate.setText(displayCarrier.getSafetyRateDate());
			this.txtSafetyRating.setText(String.valueOf(displayCarrier.getSafetyRating()));
			this.txtTruck.setText(String.valueOf(displayCarrier.getCostModifierTruck()));
			
			if(displayCarrier.isSendByEmail())
				this.chckbxEmail.setSelected(true);
			else
				this.chckbxEmail.setSelected(false);
			if(displayCarrier.isSendByFax())
				this.chckbxFaxNumber.setSelected(true);
			else
				this.chckbxFaxNumber.setSelected(true);
			
			this.chckbxEmail.setEnabled(false);
			this.chckbxFaxNumber.setEnabled(false);
			btnSaveEdit.setVisible(true);
		}
		
		private void setEditable()
		{
			this.txtAreaCode.setEditable(true);
			this.txtAuthorize.setEditable(true);
			this.txtCargo.setEditable(true);
			this.txtCode.setEditable(true);
			this.txtContractDate.setEditable(true);
			this.txtEmail.setEditable(true);
			this.txtFaxNumber.setEditable(true);
			this.txtInsEndDate.setEditable(true);
			this.txtName.setEditable(true);
			this.txtPlane.setEditable(true);
			this.txtRail.setEditable(true);
			this.txtRatingDate.setEditable(true);
			this.txtSafetyRating.setEditable(true);
			this.txtTruck.setEditable(true);
			
			this.chckbxEmail.setEnabled(true);
			this.chckbxFaxNumber.setEnabled(true);
			btnSaveEdit.setVisible(true);
		}
		
		private void setReadOnly()
		{
			this.txtAreaCode.setEditable(false);
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
			btnSaveEdit.setVisible(true);
			

		}




	}


