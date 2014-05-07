package GUI.ShipperForms;

import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.TableRefreshListener;

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
	private JButton btnSave,btnEdit;
	
	
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
	private Shipper source;
	private boolean newShipper;
	private ArrayList<TableRefreshListener> refresh = new ArrayList<TableRefreshListener>();
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
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				update();
				btnSave.setVisible(false);
				btnEdit.setVisible(true);
				setReadOnly();
				newShipper=false;
				for(TableRefreshListener t : refresh)t.refreshTable();
			}
		});
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnSave.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		cbPreferredCarrier4 = new JComboBox();
		add(cbPreferredCarrier4, "7, 12, fill, default");
		lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLocation,"4, 14, center, center");
		
		lblCountry = new JLabel("Country");
		add(lblCountry, "2, 16, right, center");
		
		cbCountry = new JComboBox();
		cbCountry.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadStates(cbCountry,cbState);
			}
		});
		add(cbCountry, "4, 16, fill, default");
		
		lblState = new JLabel("State");
		add(lblState, "2, 18, right, default");
		
		cbState = new JComboBox();
		cbState.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadCities(cbCountry,cbState,cbCity);
			}
		});
		add(cbState, "4, 18, fill, default");
		
		lblCity = new JLabel("City");
		add(lblCity, "2, 20, right, default");
		
		cbCity = new JComboBox();
		add(cbCity, "4, 20, fill, default");
		add(btnSave, "4, 25");
		add(btnEdit,"4,25");
		
		txtID.setEditable(false);
		
		btnCancel = new JButton("Cancel");

		add(btnCancel, "7, 25");
		

	
	

		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSave.isVisible() && source!=null)
				{
					btnEdit.setVisible(true);
					btnSave.setVisible(false);
					setReadOnly();
				}
				else
				{
					setVisible(false);
				}
			}
		});
		
		this.loadCountries();

		this.loadPreferredCarriers(this.cbPreferredCarrier1);
		this.loadPreferredCarriers(this.cbPreferredCarrier2);
		this.loadPreferredCarriers(this.cbPreferredCarrier3);
		this.loadPreferredCarriers(this.cbPreferredCarrier4);
		
		
		this.btnCancel.setToolTipText("Click here to cancel any changes");
		this.btnEdit.setToolTipText("Click here to edit the Shipper");
		this.btnSave.setToolTipText("Click here to save changes");
		this.cbCity.setToolTipText("This is the city the Shipper is located in");
		this.cbCountry.setToolTipText("This is the country the Shipper is located in");
		this.cbPreferredCarrier1.setToolTipText("Currently no supported");
		this.cbPreferredCarrier2.setToolTipText("Currently no supported");
		this.cbPreferredCarrier3.setToolTipText("Currently no supported");
		this.cbPreferredCarrier4.setToolTipText("Currently no supported");
		this.cbState.setToolTipText("This si the state the Shipper is located in");
		this.txtCompanyName.setToolTipText("This is the Shipper's name");
		this.txtContactName.setToolTipText("This is the contact's name");
		this.txtEmail.setToolTipText("This is the email address the Shipper can be reached at");
		this.txtNumber.setToolTipText("This is the phone number the Shipper can be reached at");
		
		}

		public void showPanel()
		{
			newShipper=true;
			source=null;
			setEditable();
			setNew();
			btnSave.setVisible(true);
			btnEdit.setVisible(false);
			this.setVisible(true);
		}

		public void showPanel(Shipper s)
		{
			newShipper=false;
			source=s;
			displayShipper();
			setReadOnly();
			btnSave.setVisible(false);
			btnEdit.setVisible(true);
			setVisible(true);
		}
		private void update()
		{
			String errorString = "";
			//Error Checking
			if(this.txtCompanyName.getText().isEmpty() || this.txtCompanyName.getText().length() < 1 ||  this.txtCompanyName.getText().length() > 45)
				errorString += "The Company Name was not a valid entry between 1 and 45 characters.\n";
			if(this.txtContactName.getText().isEmpty() || this.txtContactName.getText().length() < 1 ||  this.txtContactName.getText().length() > 45)
				errorString += "The Contact Name was not a valid entry between 1 and 45 characters.\n";
			if(this.txtEmail.getText().isEmpty() || !core.FormatChecker.isValidEmail(this.txtEmail.getText()))
				errorString += "The email address was not valid. Please enter an email address with the following format,\n\t(any alphanumeric string)@(any alphanumeric string).(2-4 alphabetic characters)\n";
			if(this.txtNumber.getText().isEmpty() || !core.FormatChecker.isValidPhone(this.txtNumber.getText()))
				errorString += "The phone number was not a valid entry, please enter a ten digit number with the following format,\n\t###-###-####.\n";
			if(errorString.isEmpty())
			{
				if(source==null) source=new Shipper();
				source.setCompanyName(this.txtCompanyName.getText());
				source.setContactName(this.txtContactName.getText());
				source.setEmailAddress(this.txtEmail.getText());
				source.setPhoneNumber(this.txtNumber.getText());
				
				ArrayList<Map<String,Object>> location = new ArrayList<Map<String,Object>>();
				try
				{
				location = BaseClass.executeQuery("Select * from Location where Name = '"+ this.cbCity.getSelectedItem().toString() +"' AND State = '" +
				this.cbState.getSelectedItem().toString() + "' AND Country = '" +this.cbCountry.getSelectedItem().toString()+ "'");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				source.setLocationID(Integer.parseInt(location.get(0).get("LocationID").toString()));
				
				source.Update();
				
				this.txtID.setText(String.valueOf(source.getID()));
			}
			else{
				//An error occurred
				JOptionPane.showMessageDialog(null, errorString , "Invalid data entered", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void setNew()
		{
			this.txtID.setText("");
			this.txtCompanyName.setText("");
			this.txtContactName.setText("");
			this.txtEmail.setText("");
			this.txtNumber.setText("");
			
			this.cbCountry.setSelectedIndex(-1);
			this.cbState.setSelectedIndex(-1);
			this.cbCity.setSelectedIndex(-1);
			
			this.cbPreferredCarrier1.setSelectedItem("ANY");
			this.cbPreferredCarrier2.setSelectedItem("ANY");
			this.cbPreferredCarrier3.setSelectedItem("ANY");
			this.cbPreferredCarrier4.setSelectedItem("ANY");
		}
		private void displayShipper(){
			this.txtID.setText(String.valueOf(source.getID()));
			this.txtCompanyName.setText(source.getCompanyName());
			this.txtContactName.setText(source.getContactName());
			this.txtEmail.setText(source.getEmailAddress());
			this.txtNumber.setText(source.getPhoneNumber());
			
			Location shipperLocation = Location.Load(source.getLocationID());
			
			this.cbCountry.setSelectedItem(shipperLocation.getCountry());
			this.cbState.setSelectedItem(shipperLocation.getState());
			this.cbCity.setSelectedItem(shipperLocation.getName());
			
			this.cbPreferredCarrier1.setSelectedItem("ANY");
			this.cbPreferredCarrier2.setSelectedItem("ANY");
			this.cbPreferredCarrier3.setSelectedItem("ANY");
			this.cbPreferredCarrier4.setSelectedItem("ANY");
			
			
		
		}
		
		private void setEditable()
		{
		
			this.txtCompanyName.setEditable(true);
			this.txtContactName.setEditable(true);
			this.txtEmail.setEditable(true);
			this.txtNumber.setEditable(true);
			if(newShipper)
			{
				this.cbCountry.setEnabled(true);
				this.cbState.setEnabled(true);
				this.cbCity.setEnabled(true);
			}
			else
			{
				this.cbCountry.setEnabled(false);
				this.cbState.setEnabled(false);
				this.cbCity.setEnabled(false);
			}
			
			this.cbPreferredCarrier1.setEnabled(false);
			this.cbPreferredCarrier2.setEnabled(false);
			this.cbPreferredCarrier3.setEnabled(false);
			this.cbPreferredCarrier4.setEnabled(false);
		
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
			
			this.cbPreferredCarrier1.setEnabled(false);
			this.cbPreferredCarrier2.setEnabled(false);
			this.cbPreferredCarrier3.setEnabled(false);
			this.cbPreferredCarrier4.setEnabled(false);


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
			}
		}
		protected void loadCountries()
		{
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Country from location");
				for(Map m :tmp)
				{
					cbCountry.addItem(m.get("Country").toString());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			cbCountry.setSelectedIndex(-1);
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
		public void addTableRefreshListener(TableRefreshListener t)
		{
			refresh.add(t);
		}


	}


