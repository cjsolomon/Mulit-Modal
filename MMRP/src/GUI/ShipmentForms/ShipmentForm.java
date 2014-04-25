package GUI.ShipmentForms;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.BaseClass;
import core.Bike;
import core.Location;
import core.Shipment;
import core.Shipper;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.border.MatteBorder;
import com.jgoodies.forms.layout.Sizes;

public class ShipmentForm extends JPanel {
	JLabel lblLocationName,lblCompanyName,lblContactName,lblPhone,lblEmail,lblPrefCarriers;
	JTextField txtCompanyName,txtContactName,txtPhone,txtEmail,txtPrefCarriers;
	JComboBox<String> cmbFromCountries,cmbFromStates,cmbFromCities,cmbToCountries,cmbToCities,cmbToStates,cmbPriority;
	JCheckBox chkTolls, chkCongestion;
	JLabel lblToLocation,lblPriority,lblSize,lblWeight,lblEarliestDateTimeArrival,lblEarliestDateTimeDeparture,lblLatestDateTimeArrival,lblLatestDateTimeDeparture,lblTimeToLoad,lblTimeToUnLoad,lblTollRoads,lblCongestionByPass,lblMaxStops;
	JTextField txtSize,txtWeight,txtMaxStops,txtTimeUnLoad,txtTimeLoad;
	JLabel lblTrailerType,lblLoadingType,lblUnloadingType,lblHazmatConstraints;
	JTextField txtTrailerType,txtLoadingType,txtUnloadingType,txtHazmatConstraints;
	Shipment source;
	
	public ShipmentForm() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(71dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(59dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(93dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(103dlu;default)"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				RowSpec.decode("max(23dlu;default)"),
				RowSpec.decode("max(91dlu;default)"),}));
				//RowSpec.decode("default:grow"),}));
		
		JPanel shipperPanel = new JPanel();
		shipperPanel.setBorder(null);
		
		shipperPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.PREFERRED, Sizes.constant("50dlu", true), Sizes.constant("55dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.PREFERRED, Sizes.constant("20dlu", true), Sizes.constant("25dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("55dlu", true), Sizes.constant("55dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblLocationName=new JLabel("Location:");
		lblCompanyName = new JLabel("Company:");
		lblContactName = new JLabel("Contact:");
		lblPhone = new JLabel("Phone:");
		lblPrefCarriers = new JLabel("Preffered Carriers:");
		lblEmail = new JLabel("Email:");
		
		txtCompanyName = new JTextField(25);
		txtContactName = new JTextField(25);
		txtPhone=new JTextField(25);
		txtEmail = new JTextField(25);
		txtPrefCarriers = new JTextField(25);
	
		cmbFromCountries = new JComboBox<String>();
		cmbFromCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadStates(cmbFromCountries,cmbFromStates);
			}
		});
		
		cmbToCountries = new JComboBox<String>();
		cmbToCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadStates(cmbToCountries,cmbToStates);
			}
		});
		
		loadCountries(this.cmbFromCountries);
		loadCountries(this.cmbToCountries);
		
		cmbFromStates= new JComboBox<String> ();
		cmbFromStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
		});
		cmbFromCities = new JComboBox<String> ();
		shipperPanel.add(lblLocationName,"2,2,right,center");
		shipperPanel.add(cmbFromCountries,"4, 2");
		shipperPanel.add(cmbFromStates,"6,2");
		shipperPanel.add(cmbFromCities,"8, 2, fill, default");
		shipperPanel.add(lblCompanyName,"2,4,right,center");
		shipperPanel.add(txtCompanyName,"4, 4, 5, 1");
		shipperPanel.add(lblContactName,"2,6,right,center");
		shipperPanel.add(txtContactName,"4, 6, 5, 1");
		shipperPanel.add(lblPhone,"2,8,right,center");
		shipperPanel.add(txtPhone,"4, 8, 5, 1");
		shipperPanel.add(lblEmail,"2,10,right,center");
		shipperPanel.add(txtEmail,"4, 10, 5, 1");
		shipperPanel.add(lblPrefCarriers,"2,12,right,center");
		shipperPanel.add(txtPrefCarriers,"4, 12, 5, 1");
		
		add(shipperPanel, "2, 2, 13, 2");
		
		JPanel shipmentPanel = new JPanel();
		shipmentPanel.setLayout(new FormLayout(new ColumnSpec[]{
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
			},new RowSpec[]{
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
		}));
		shipmentPanel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		
		this.lblToLocation = new JLabel("To:");
	//	cmbToLocations=new JComboBox();
		
		this.lblSize=new JLabel("Size:");
		txtSize = new JTextField(20);
		
		this.lblPriority=new JLabel("Priority:");
		cmbPriority= new JComboBox();
		
		this.lblWeight=new JLabel("Weight:");
		txtWeight=new JTextField(20);
		
		this.lblEarliestDateTimeArrival=new JLabel("Earliest Arrival:");
		this.lblEarliestDateTimeDeparture=new JLabel("Earliest Departure:");
		
		this.lblTimeToLoad = new JLabel("Time to Load:");
		txtTimeLoad = new JTextField(20);
		
		this.lblTimeToUnLoad = new JLabel("Time to UnLoad:");
		txtTimeUnLoad=new JTextField(20);
		
		this.lblCongestionByPass = new JLabel("Congestion By-Pass:");
		chkCongestion=new JCheckBox();
		this.lblMaxStops=new JLabel("Max Stops:");
		txtMaxStops=new JTextField(20);
		
		this.lblLatestDateTimeDeparture= new JLabel("Latest Departure:");
		this.lblLatestDateTimeArrival=new JLabel("Latest Arrival:");
		
		this.lblTollRoads=new JLabel("Toll Roads:");
		chkTolls = new JCheckBox();
		
		this.lblUnloadingType = new JLabel("Unloading Type:");
		this.lblLoadingType = new JLabel("Loading Type:");
		this.lblHazmatConstraints = new JLabel("Hazmat:");
		this.lblTrailerType = new JLabel("Trailer Type Required:");
		
		this.txtUnloadingType = new JTextField(25);
		this.txtLoadingType = new JTextField(25);
		this.txtTrailerType = new JTextField(25);
		this.txtHazmatConstraints = new JTextField(25);
		
		
		shipmentPanel.add(lblToLocation,"2,2,right,center");
		//shipmentPanel.add(cmbToLocations,"4,2");
		
		shipmentPanel.add(lblSize,"2,4,right,center");
		shipmentPanel.add(txtSize,"4,4");

		
		shipmentPanel.add(this.lblTollRoads,"8, 4, right, center");
		shipmentPanel.add(this.chkTolls,"10, 4, right, center");
		
		shipmentPanel.add(lblWeight,"2,6,right,center");
		shipmentPanel.add(txtWeight,"4,6");
		
		shipmentPanel.add(lblPriority,"2,8,right,center");
		shipmentPanel.add(cmbPriority,"4,8");
		
		shipmentPanel.add(this.lblEarliestDateTimeDeparture,"2,10,right,center");
		shipmentPanel.add(this.lblLatestDateTimeArrival,"8, 12, right, center");
		shipmentPanel.add(this.lblLatestDateTimeDeparture,"8, 10, right, center");
		shipmentPanel.add(this.lblEarliestDateTimeArrival,"2,12,right,center");
		
		shipmentPanel.add(this.lblCongestionByPass,"8,6,right,center");
		shipmentPanel.add(this.chkCongestion,"10,6,right,center");
		
		shipmentPanel.add(this.lblTimeToLoad,"2,14,right,center");
		shipmentPanel.add(this.txtTimeLoad,"4,14,right,center");
		shipmentPanel.add(this.lblTimeToUnLoad,"2,16,right,center");
		shipmentPanel.add(this.txtTimeUnLoad,"4,16,right,center");
		
		add(shipmentPanel,"2, 5, 14, 3");
		
		
	}
	
	public void showPanel()
	{
		this.setVisible(true);
	}

	public void showPanel(Shipment s)
	{
		source=s;
		setReadOnly();
		setVisible(true);
	}
	
	private void setReadOnly()
	{
		Shipper s = source.getShipper();
		txtCompanyName.setText(s.getCompanyName());
		txtContactName.setText(s.getContactName());
		txtPhone.setText(s.getPhoneNumber());
		txtEmail.setText(s.getEmailAddress());
		txtPrefCarriers.setText(s.getPrefferedCarriers());
		
		Location l = Location.Load(s.getLocationID());
		this.cmbFromCountries.setSelectedItem(l.getCountry());
		this.cmbFromStates.setSelectedItem(l.getState());
		this.cmbFromCities.setSelectedItem(l.getName());
		
		txtCompanyName.setEditable(false);
		txtContactName.setEditable(false);
		txtPhone.setEditable(false);
		txtEmail.setEditable(false);
		txtPrefCarriers.setEditable(false);
		this.cmbFromCities.setEnabled(false);
		this.cmbFromCountries.setEnabled(false);
		this.cmbFromStates.setEnabled(false);
		//txtCompanyName.setText(source.get)
	}
	
	private void loadStates(JComboBox sourceCountry,JComboBox sourceStates)
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
	private void loadCountries(JComboBox source)
	{
		try
		{
			ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct Country from location");
			for(Map m :tmp)
			{
				source.addItem(m.get("Country").toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		source.setSelectedIndex(-1);
	}
	private void loadCitites(JComboBox sourceCountry,JComboBox sourceStates,JComboBox sourceCities)
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
	
}

