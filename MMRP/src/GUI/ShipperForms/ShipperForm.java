package GUI.ShipperForms;

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

import core.BaseClass;
import core.Carrier;
import core.Location;
import core.Shipper;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ShipperForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblEmail, lblLocation, lblNumber,lblContactName,lblCompanyName, lblShipper, lblID;
	JTextField txtEmail,txtNumber,txtContactName,txtCompanyName, txtID;
	private JButton btnSaveEdit;
	
	private boolean edit = false;
	private JButton btnCancel;
	private JLabel lblPreferredCarriers;
	private JLabel lblCountry;
	private JLabel lblState;
	private JLabel lblCity;
	private JComboBox cbCountry;
	private JComboBox cbState;
	private JComboBox cbCity;
	private JComboBox cbPreferredCarrier1;
	private JComboBox cbPreferredCarrier2;
	private JComboBox cbPreferredCarrier3;
	private JComboBox cbPreferredCarrier4;
	
	public ShipperForm() {
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(57dlu;default)"),
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
				RowSpec.decode("max(15dlu;default)"),
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
		lblNumber = new JLabel("Phone Number");
		lblContactName = new JLabel("Contact Name");
		lblCompanyName = new JLabel("Company Name");
		lblShipper = new JLabel("Shipper");
		lblID = new JLabel("Shipper ID");
		
		txtEmail=new JTextField(20);
		txtContactName = new JTextField(20);
		txtCompanyName = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblShipper, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		lblPreferredCarriers = new JLabel("Preferred Carriers");
		add(lblPreferredCarriers, "7, 4, right, default");
		
		cbPreferredCarrier1 = new JComboBox();
		add(cbPreferredCarrier1, "7, 6, fill, default");
		
		cbPreferredCarrier2 = new JComboBox();
		add(cbPreferredCarrier2, "7, 8, fill, default");
		add(lblNumber,"2,10,right,center");
		add(lblContactName,"2,8,right,center");
		add(txtContactName, "4,8,right,center");
		add(lblCompanyName,"2,6,right,center");
		add(txtCompanyName, "4,6,right,center");
		txtNumber= new JTextField(20);
		add(txtNumber, "4, 10, right, center");
		
		cbPreferredCarrier3 = new JComboBox();
		add(cbPreferredCarrier3, "7, 10, fill, default");
		add(lblEmail,"2,12,right,center");
		add(txtEmail, "4,12,right,center");
		
		btnSaveEdit = new JButton("Edit");
		
		cbPreferredCarrier4 = new JComboBox();
		add(cbPreferredCarrier4, "7, 12, fill, default");
		lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLocation,"4, 14, center, center");
		
		lblCountry = new JLabel("Country");
		add(lblCountry, "2, 16, right, center");
		
		cbCountry = new JComboBox();
		add(cbCountry, "4, 16, fill, default");
		
		lblState = new JLabel("State");
		add(lblState, "2, 18, right, default");
		
		cbState = new JComboBox();
		add(cbState, "4, 18, fill, default");
		
		lblCity = new JLabel("City");
		add(lblCity, "2, 20, right, default");
		
		cbCity = new JComboBox();
		add(cbCity, "4, 20, fill, default");
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
		
		this.loadCountries();
		this.loadStates(this.cbCountry, this.cbState);
		this.loadCities(this.cbCountry, this.cbState, this.cbCity);
		this.loadPreferredCarriers(this.cbPreferredCarrier1);
		this.loadPreferredCarriers(this.cbPreferredCarrier2);
		this.loadPreferredCarriers(this.cbPreferredCarrier3);
		this.loadPreferredCarriers(this.cbPreferredCarrier4);
		}

		public void showPanel()
		{
			setEditable();
			this.setVisible(true);
		}

		public void showPanel(Shipper s)
		{
			displayShipper(s);
			setReadOnly();
			setVisible(true);
		}
		private void update()
		{
			btnSaveEdit.setVisible(false);
		}
		
		private void displayShipper(Shipper displayShipper){
			this.txtID.setText(String.valueOf(displayShipper.getID()));
			this.txtCompanyName.setText(displayShipper.getCompanyName());
			this.txtContactName.setText(displayShipper.getContactName());
			this.txtEmail.setText(displayShipper.getEmailAddress());
			this.txtNumber.setText(displayShipper.getPhoneNumber());
			
			Location shipperLocation = Location.Load(displayShipper.getLocationID());
			
			this.cbCountry.setSelectedItem(shipperLocation.getCountry());
			this.cbState.removeAll();
			this.loadStates(cbCountry, cbState);
			this.cbState.setSelectedItem(shipperLocation.getState());
			this.cbCity.removeAll();
			this.loadCities(cbCountry, cbState, cbCity);
			this.cbCity.setSelectedItem(shipperLocation.getName());
			
			this.cbPreferredCarrier1.setSelectedItem("ANY");
			this.cbPreferredCarrier2.setSelectedItem("ANY");
			this.cbPreferredCarrier3.setSelectedItem("ANY");
			this.cbPreferredCarrier4.setSelectedItem("ANY");
			
			
			btnSaveEdit.setVisible(true);
		}
		
		private void setEditable()
		{
		
			this.txtCompanyName.setEditable(true);
			this.txtContactName.setEditable(true);
			this.txtEmail.setEditable(true);
			this.txtNumber.setEditable(true);
			
			this.cbCountry.setEnabled(true);
			this.cbState.setEnabled(true);
			this.cbCity.setEnabled(true);
			
			this.cbPreferredCarrier1.setSelectedItem("ANY");
			this.cbPreferredCarrier2.setSelectedItem("ANY");
			this.cbPreferredCarrier3.setSelectedItem("ANY");
			this.cbPreferredCarrier4.setSelectedItem("ANY");
			btnSaveEdit.setVisible(true);
		}
		
		private void setReadOnly()
		{
			this.txtCompanyName.setEditable(false);
			this.txtContactName.setEditable(false);
			this.txtEmail.setEditable(false);
			this.txtNumber.setEditable(false);
			
			this.cbCountry.setEnabled(false);
			this.cbState.setEnabled(false);
			this.cbCity.setEnabled(false);
			
			this.cbPreferredCarrier1.setSelectedItem("NONE");
			this.cbPreferredCarrier2.setSelectedItem("NONE");
			this.cbPreferredCarrier3.setSelectedItem("NONE");
			this.cbPreferredCarrier4.setSelectedItem("NONE");
			btnSaveEdit.setVisible(true);

		}


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
				sourceStates.setSelectedIndex(0);
			}
		}
		protected void loadCountries()
		{
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Country from location");
				for(Map m :tmp)
				{
					this.cbCountry.addItem(m.get("Country").toString());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			cbCountry.setSelectedIndex(0);
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
		
		protected void loadPreferredCarriers(JComboBox prefCarriers){
			prefCarriers.addItem("ANY");
			prefCarriers.setSelectedIndex(0);
			
		}
		


	}


