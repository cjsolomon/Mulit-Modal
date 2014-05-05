package GUI.ShipmentForms;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
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
import core.Vehicle;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.border.MatteBorder;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.JButton;

public class ShipmentForm extends JPanel 
{
	JLabel lblLocationName,lblCompanyName,lblContactName,lblPhone,lblEmail,lblPrefCarriers;
	JTextField txtContactName,txtPhone,txtEmail,txtPrefCarriers;
	JComboBox<ComboItem> txtCompanyName;
	JTextField cmbFromCountries,cmbFromStates,cmbFromCities;
	JComboBox<String> cmbToCountries,cmbToCities,cmbToStates;
	JComboBox<Integer> cmbPriority;
	JCheckBox chkTolls, chkCongestion;
	JLabel lblToLocation,lblPriority,lblSize,lblWeight,lblEarliestDateTimeArrival,lblEarliestDateTimeDeparture,lblLatestDateTimeArrival,lblLatestDateTimeDeparture,lblTimeToLoad,lblTimeToUnLoad,lblTollRoads,lblCongestionByPass,lblMaxStops;
	JTextField txtSize,txtWeight,txtMaxStops,txtTimeUnLoad,txtTimeLoad;
	JLabel lblTrailerType,lblLoadingType,lblUnloadingType,lblHazmatConstraints;
	JTextField txtTrailerType,txtLoadingType,txtUnloadingType,txtHazmatConstraints;
	Shipment source;

	JPanel shipmentPanel,shipperPanel;
	private JButton btnEdit,btnSave,btnCancel;
	private JTextField txtEarliestDeparture;
	private JTextField txtEarliestArrival;
	private JTextField txtLatestDeparture;
	private Boolean newShipment;

	private Shipper selectedShipper;
	private ComboItem[] shippers;
	/**
	 * @wbp.nonvisual location=-29,429
	 */

	private JTextField txtLatestArrival;

	public ShipmentForm()
	{

		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(71dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(59dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(73dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(43dlu;default)"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(103dlu;default)"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				RowSpec.decode("max(23dlu;default)"),
				RowSpec.decode("max(157dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		//RowSpec.decode("default:grow"),}));

		shipperPanel = new JPanel();
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
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
		lblContactName = new JLabel("Contact:");
		lblPhone = new JLabel("Phone:");
		lblPrefCarriers = new JLabel("Preferred Carriers:");
		lblEmail = new JLabel("Email:");
		txtContactName = new JTextField(25);
		txtContactName.setEditable(false);
		txtPhone=new JTextField(25);
		txtPhone.setEditable(false);
		txtEmail = new JTextField(25);
		txtEmail.setEditable(false);
		txtPrefCarriers = new JTextField(25);
		txtPrefCarriers.setEditable(false);

		cmbToCountries = new JComboBox<String>();
		cmbToCountries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadStates(cmbToCountries,cmbToStates);
			}
		});
		loadCountries();
		cmbToStates = new JComboBox<String>();
		cmbToStates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				loadCities(cmbToCountries,cmbToStates,cmbToCities);
			}
		});
		cmbToCities = new JComboBox<String>();
		lblCompanyName = new JLabel("Company:");
		shipperPanel.add(lblCompanyName,"2, 2, right, center");

		txtCompanyName = new JComboBox<ComboItem>();
		txtCompanyName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(txtCompanyName.getSelectedIndex()!=-1)
				{
					setShipper((ComboItem)txtCompanyName.getSelectedItem());
				}
			}
		});

		shipperPanel.add(txtCompanyName,"4, 2, 5, 1");

		lblLocationName=new JLabel("Location:");
		shipperPanel.add(lblLocationName,"2, 4, right, center");

		cmbFromCountries = new JTextField();
		cmbFromCountries.setEditable(false);

		shipperPanel.add(cmbFromCountries,"4, 4");
		//loadCountries(this.cmbToCountries);

		cmbFromStates= new JTextField ();
		cmbFromStates.setEditable(false);
		shipperPanel.add(cmbFromStates,"6, 4");
		cmbFromCities = new JTextField ();
		cmbFromCities.setEditable(false);
		shipperPanel.add(cmbFromCities,"8, 4, fill, default");
		loadShippers();
		shipperPanel.add(lblContactName,"2,6,right,center");
		shipperPanel.add(txtContactName,"4, 6, 5, 1");
		shipperPanel.add(lblPhone,"2,8,right,center");
		shipperPanel.add(txtPhone,"4, 8, 5, 1");
		shipperPanel.add(lblEmail,"2,10,right,center");
		shipperPanel.add(txtEmail,"4, 10, 5, 1");
		shipperPanel.add(lblPrefCarriers,"2,12,right,center");
		shipperPanel.add(txtPrefCarriers,"4, 12, 5, 1");

		add(shipperPanel, "2, 2, 14, 2");

		shipmentPanel = new JPanel();
		shipmentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("50dlu", true), Sizes.constant("55dlu", true)), 1),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", true), Sizes.constant("25dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		shipmentPanel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));

		this.lblToLocation = new JLabel("To:");
		//	cmbToLocations=new JComboBox();

		this.lblSize=new JLabel("Size:");
		txtSize = new JTextField(20);

		this.lblPriority=new JLabel("Priority:");
		cmbPriority= new JComboBox<Integer>();
		for(int i=0;i<11;i++)
			cmbPriority.addItem(i);

		this.lblWeight=new JLabel("Weight:");
		txtWeight=new JTextField(20);

		this.lblEarliestDateTimeArrival=new JLabel("Earliest Arrival:");
		this.lblEarliestDateTimeDeparture=new JLabel("Earliest Departure:");

		this.lblTimeToLoad = new JLabel("Time to Load:");
		txtTimeLoad = new JTextField(20);
		txtTimeLoad.setText("0");
		txtTimeLoad.setEditable(false);

		this.lblTimeToUnLoad = new JLabel("Time to UnLoad:");
		txtTimeUnLoad=new JTextField(20);
		txtTimeUnLoad.setText("0");
		txtTimeUnLoad.setEditable(false);

		this.lblCongestionByPass = new JLabel("Congestion By-Pass:");
		chkCongestion=new JCheckBox();
		chkCongestion.setEnabled(false);
		this.lblMaxStops=new JLabel("Max Stops:");
		txtMaxStops=new JTextField(20);

		this.lblLatestDateTimeDeparture= new JLabel("Latest Departure:");
		this.lblLatestDateTimeArrival=new JLabel("Latest Arrival:");

		this.lblTollRoads=new JLabel("Toll Roads:");
		chkTolls = new JCheckBox();
		chkTolls.setEnabled(false);

		this.lblUnloadingType = new JLabel("Unloading Type:");
		this.lblLoadingType = new JLabel("Loading Type:");
		this.lblHazmatConstraints = new JLabel("Hazmat:");
		this.lblTrailerType = new JLabel("Trailer Type Required:");

		this.txtUnloadingType = new JTextField(25);
		txtUnloadingType.setEditable(false);
		txtUnloadingType.setText("NONE");
		this.txtLoadingType = new JTextField(25);
		txtLoadingType.setEditable(false);
		txtLoadingType.setText("NONE");
		this.txtTrailerType = new JTextField(25);
		txtTrailerType.setEditable(false);
		txtTrailerType.setText("NONE");
		this.txtHazmatConstraints = new JTextField(25);
		txtHazmatConstraints.setEditable(false);
		txtHazmatConstraints.setText("NONE");


		shipmentPanel.add(lblToLocation,"2,2,right,center");
		shipmentPanel.add(cmbToCountries,"4,2");
		shipmentPanel.add(cmbToStates,"6,2");
		shipmentPanel.add(cmbToCities,"8, 2, 3, 1");
		//shipmentPanel.add(cmbToLocations,"4,2");

		shipmentPanel.add(lblSize,"2,4,right,center");
		shipmentPanel.add(txtSize,"4, 4, 3, 1");


		shipmentPanel.add(this.lblTollRoads,"8, 4, right, center");
		shipmentPanel.add(this.chkTolls,"10, 4, right, center");

		shipmentPanel.add(lblWeight,"2,6,right,center");
		shipmentPanel.add(txtWeight,"4, 6, 3, 1");

		shipmentPanel.add(lblPriority,"2,8,right,center");
		shipmentPanel.add(cmbPriority,"4, 8, 3, 1");

		shipmentPanel.add(this.lblEarliestDateTimeDeparture,"2,10,right,center");

		txtEarliestDeparture = new JTextField();
		shipmentPanel.add(txtEarliestDeparture, "4, 10, fill, default");
		txtEarliestDeparture.setColumns(10);

		txtLatestDeparture = new JTextField();
		shipmentPanel.add(txtLatestDeparture, "10, 10, fill, default");
		txtLatestDeparture.setColumns(10);

		txtEarliestArrival = new JTextField();
		shipmentPanel.add(txtEarliestArrival, "4, 12, fill, top");
		txtEarliestArrival.setColumns(10);
		shipmentPanel.add(this.lblLatestDateTimeArrival,"8, 12, right, center");
		shipmentPanel.add(this.lblLatestDateTimeDeparture,"8, 10, right, center");
		shipmentPanel.add(this.lblEarliestDateTimeArrival,"2,12,right,center");

		shipmentPanel.add(this.lblCongestionByPass,"8,6,right,center");
		shipmentPanel.add(this.chkCongestion,"10,6,right,center");

		txtLatestArrival = new JTextField();
		shipmentPanel.add(txtLatestArrival, "10, 12, fill, default");
		txtLatestArrival.setColumns(10);

		shipmentPanel.add(this.lblTimeToLoad,"2,14,right,center");
		shipmentPanel.add(this.txtTimeLoad,"4, 14, 3, 1, fill, center");
		shipmentPanel.add(this.lblTimeToUnLoad,"2,16,right,center");
		shipmentPanel.add(this.txtTimeUnLoad,"4, 16, 3, 1, fill, center");

		shipmentPanel.add(this.lblMaxStops,"8,14,right,center");
		shipmentPanel.add(this.txtMaxStops,"10,14");
		shipmentPanel.add(this.lblHazmatConstraints,"2, 18, right, default");
		shipmentPanel.add(this.txtHazmatConstraints,"4, 18, 3, 1");

		shipmentPanel.add(this.lblLoadingType,"2, 20, right, default");
		shipmentPanel.add(this.txtLoadingType,"4, 20, 3, 1");
		shipmentPanel.add(this.lblUnloadingType,"2, 22, right, default");
		shipmentPanel.add(this.txtUnloadingType,"4, 22, 3, 1");
		shipmentPanel.add(this.lblTrailerType,"2, 24, right, default");
		shipmentPanel.add(this.txtTrailerType,"4, 24, 3, 1");
		add(shipmentPanel,"2, 5, 14, 5");

		btnSave = new JButton("Save");
		shipmentPanel.add(btnSave, "8, 26");
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				update();
				newShipment=false;
				setReadOnly();
				btnSave.setToolTipText("Click here to save changes");
			}
		});
		btnSave.setVisible(false);
		btnCancel = new JButton("Cancel");
		shipmentPanel.add(btnCancel, "10, 26");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				btnCancel.setToolTipText("Click here to cancel any changes");
				if(btnSave.isVisible()&&!newShipment)
				{
					btnSave.setVisible(false);
					btnEdit.setVisible(true);
					setReadOnly();
				}
				else
				{
					getParent().setVisible(false);
					setVisible(false);
				}
			}
		});

		btnEdit = new JButton("Edit");
		shipmentPanel.add(btnEdit, "8, 26");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEditable();
				btnEdit.setToolTipText("Click here to edit Shipment information");
			}
		});

	}

	public void showPanel()
	{
		newShipment=true;		
		setNew();
		setEditable();
		this.setVisible(true);
	}

	public void showPanel(Shipment s)
	{
		newShipment=false;
		source=s;
		setReadOnly();
		setShipment();
		setVisible(true);
	}
	private void update()
	{
		Shipper s;
		if(source==null)
		{
			source = new Shipment();
		}
		
		if(this.chkCongestion.isSelected())
			source.setCongestionByPassTrue();
		else
			source.setCongestionByPassFalse();
		
	
		source.setFromLocationID(this.selectedShipper.getLocationID());
		source.setHazmat(this.txtHazmatConstraints.getText());

		source.setMaxStops(Integer.parseInt(this.txtMaxStops.getText()));
		source.setPrefCarrier(this.txtPrefCarriers.getText());
		source.setPriority((int)this.cmbPriority.getSelectedItem());
		source.setShipperID(selectedShipper.getID());
		source.setSize(Double.parseDouble(this.txtSize.getText()));
		if(this.chkTolls.isSelected())
			source.setTollRoadsTrue();
		else
			source.setTollRoadsFalse();
		
		ArrayList<Map<String,Object>> eLocation = new ArrayList<Map<String,Object>>();
		try
		{
		eLocation = BaseClass.executeQuery("Select * from Location where Name = '"+ this.cmbToCities.getSelectedItem().toString() +"' AND State = '" +
		this.cmbToStates.getSelectedItem().toString() + "' AND Country = '" +this.cmbToCountries.getSelectedItem().toString()+ "'");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		source.setToLocationID(Integer.parseInt(eLocation.get(0).get("LocationID").toString()));
		source.setWeight(Double.parseDouble(this.txtWeight.getText().toString()));
		
		
		source.Update();
		btnSave.setVisible(false);
		btnEdit.setVisible(true);
	}

	private void setNew()
	{
		
		
		txtCompanyName.setToolTipText("Select a new Shipper");
		txtContactName.setToolTipText("This is the contact for the Shipper");
		txtPhone.setToolTipText("The Shipper can be reached at this number");
		txtEmail.setToolTipText("The Shipper can be reached at this email address");
		txtPrefCarriers.setToolTipText("Given a choice, these are the Carriers the Shipper will use");
		txtMaxStops.setToolTipText("This is the maximum number of stops this Shipment can make.");
		txtTimeLoad.setToolTipText("Currently not supported");
		txtTimeUnLoad.setToolTipText("Currently not supported");
		txtLoadingType.setToolTipText("Currently not supported");
		txtUnloadingType.setToolTipText("Currently not supported");
		txtTrailerType.setToolTipText("Currently not supported");
		txtSize.setToolTipText("This is the volumetric size of the Shipment");
		cmbFromCountries.setToolTipText("This is the country of origin");
		cmbFromStates.setToolTipText("This is the state of origin");
		cmbFromCities.setToolTipText("This is the city of origin");
		cmbToCountries.setToolTipText("This is the destination country");
		cmbToStates.setToolTipText("This is the destination state");
		cmbToCities.setToolTipText("This is the destination city");
		txtWeight.setToolTipText("This is the weight of the shipement");
		txtEarliestArrival.setToolTipText("This is earliest the shipment can arrive");
		txtEarliestDeparture.setToolTipText("This is earliest the shipment can depart");
		txtLatestArrival.setToolTipText("This is latest the shipment can arrive");
		txtLatestDeparture.setToolTipText("This is latest the shipment can depart");
		chkCongestion.setToolTipText("Currently not supported");
		chkTolls.setToolTipText("Currently not supported");
		cmbPriority.setToolTipText("This is the importance of the shipment");
		
		source = null;
		txtCompanyName.setSelectedIndex(-1);
		txtContactName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtPrefCarriers.setText("");
		this.txtCompanyName.setEnabled(true);

		this.cmbFromCountries.setText("");
		this.cmbFromStates.setText("");
		this.cmbFromCities.setText("");

		this.txtLoadingType.setText("NONE");
		this.txtMaxStops.setText("");
		this.txtSize.setText("");
		this.txtTimeLoad.setText("NONE");
		this.txtTimeUnLoad.setText("NONE");
		this.txtTrailerType.setText("NONE");
		this.txtUnloadingType.setText("NONE");
		this.txtWeight.setText("");
		this.txtHazmatConstraints.setText("NONE");
		this.txtEarliestArrival.setText("");
		this.txtEarliestDeparture.setText("");
		this.txtLatestArrival.setText("");
		this.txtLatestDeparture.setText("");


		this.cmbToCountries.setSelectedIndex(-1);
		this.cmbToStates.setSelectedIndex(-1);
		this.cmbToCities.setSelectedIndex(-1);

		this.chkCongestion.setSelected(false);
		this.chkTolls.setSelected(false);

		this.cmbPriority.setSelectedIndex(-1);
		
	}

	private void setShipment()
	{
		if(source!=null)
		{
			Shipper s = source.getShipper();
			ComboItem temp = new ComboItem();
			temp.setID(s.getID());
			temp.setName(s.getCompanyName());
			txtCompanyName.setSelectedItem(temp);
			txtContactName.setText(s.getContactName());
			txtPhone.setText(s.getPhoneNumber());
			txtEmail.setText(s.getEmailAddress());
			txtPrefCarriers.setText(s.getPrefferedCarriers());

			Location l = Location.Load(s.getLocationID());
			this.cmbFromCountries.setText(l.getCountry());
			this.cmbFromStates.setText(l.getState());
			this.cmbFromCities.setText(l.getName());

			//this.txtLoadingType.setText(source.getLoadingType());
			this.txtMaxStops.setText(((Integer)source.getMaxStops()).toString());
			this.txtSize.setText(((Double)source.getSize()).toString());
			//this.txtTimeLoad.setText(((Integer)source.getTimeToLoad()).toString());
			//this.txtTimeUnLoad.setText(((Integer)source.getTimeToUnload()).toString());
			//this.txtTrailerType.setText(source.getTrailerType());
			//this.txtUnloadingType.setText(source.getUnloadType());
			this.txtWeight.setText(((Double)source.getWeight()).toString());
			//this.txtHazmatConstraints.setText(source.getHazmat());
			
			
			//Found this date converter at 
			//http://stackoverflow.com/questions/10477714/converting-integer-time-stamp-into-java-date
			String dateAsText = new SimpleDateFormat("HH:mm MM/dd/YYYY")
            .format(new Date(source.getEarliestArrivalTime() * 1000L));
			this.txtEarliestArrival.setText(dateAsText);
			
			dateAsText = new SimpleDateFormat("HH:mm MM/dd/YYYY")
            .format(new Date(source.getEarliestDepartureTime() * 1000L));
			this.txtEarliestDeparture.setText(dateAsText);
			
			dateAsText = new SimpleDateFormat("HH:mm MM/dd/YYYY")
            .format(new Date(source.getLatestArrivalTime() * 1000L));
			this.txtLatestArrival.setText(dateAsText);

			dateAsText = new SimpleDateFormat("HH:mm MM/dd/YYYY")
            .format(new Date(source.getLatestDepartureTime() * 1000L));
			this.txtLatestDeparture.setText(dateAsText);
			
			Location e = Location.Load(source.getToLocationID());
			this.cmbToCountries.setSelectedItem(e.getCountry());
			this.cmbToStates.setSelectedItem(e.getState());
			this.cmbToCities.setSelectedItem(e.getName());

			this.chkCongestion.setSelected(source.getCongestionByPass());
			this.chkTolls.setSelected(source.getTollRoads());


			this.cmbPriority.setSelectedItem(source.getPriority());
		}
	}
	private void setEditable()
	{
		//if(newShipment)
		//	txtCompanyName.setEnabled(true);

		txtContactName.setEditable(false);
		txtPhone.setEditable(false);
		txtEmail.setEditable(false);
		txtPrefCarriers.setEditable(false);
		this.cmbFromCities.setEnabled(false);
		this.cmbFromCountries.setEnabled(false);
		this.cmbFromStates.setEnabled(false);

		
		this.txtMaxStops.setEditable(true);
		this.txtSize.setEditable(true);
		
		
		this.txtWeight.setEditable(true);
		this.cmbPriority.setEnabled(true);
		this.cmbToCities.setEnabled(true);
		this.cmbToCountries.setEnabled(true);
		this.cmbToStates.setEnabled(true);
		this.txtEarliestArrival.setEditable(true);
		this.txtEarliestDeparture.setEditable(true);
		this.txtLatestArrival.setEditable(true);
		this.txtLatestDeparture.setEditable(true);

		btnEdit.setVisible(false);
		btnSave.setVisible(true);
		
		this.txtHazmatConstraints.setEditable(false);
		this.txtLoadingType.setEditable(false);
		this.txtTimeLoad.setEditable(false);
		this.txtTimeUnLoad.setEditable(false);
		this.txtTrailerType.setEditable(false);
		this.txtUnloadingType.setEditable(false);
	}

	private void setReadOnly()
	{

		txtCompanyName.setEnabled(false);
		txtContactName.setEditable(false);
		txtPhone.setEditable(false);
		txtEmail.setEditable(false);
		txtPrefCarriers.setEditable(false);
		this.cmbFromCities.setEnabled(false);
		this.cmbFromCountries.setEnabled(false);
		this.cmbFromStates.setEnabled(false);



		this.txtLoadingType.setEditable(false);
		this.txtMaxStops.setEditable(false);
		this.txtSize.setEditable(false);
		this.txtTimeLoad.setEditable(false);
		this.txtTimeUnLoad.setEditable(false);
		this.txtTrailerType.setEditable(false);
		this.txtUnloadingType.setEditable(false);
		this.txtWeight.setEditable(false);
		this.txtHazmatConstraints.setEditable(false);
		this.txtEarliestArrival.setEditable(false);
		this.txtLatestArrival.setEditable(false);
		this.txtEarliestDeparture.setEditable(false);
		this.txtLatestDeparture.setEditable(false);



		this.cmbToCities.setEnabled(false);
		this.cmbToCountries.setEnabled(false);
		this.cmbToStates.setEnabled(false);

		this.chkCongestion.setEnabled(false);
		this.chkTolls.setEnabled(false);

		this.cmbPriority.setEnabled(false);	
		btnEdit.setVisible(true);
		btnSave.setVisible(false);
		
		
		txtCompanyName.setToolTipText("This is the Shipment's Shipper");
		txtContactName.setToolTipText("This is the contact for the Shipper");
		txtPhone.setToolTipText("The Shipper can be reached at this number");
		txtEmail.setToolTipText("The Shipper can be reached at this email address");
		txtPrefCarriers.setToolTipText("Given a choice, these are the Carriers the Shipper will use");
		txtMaxStops.setToolTipText("This is the maximum number of stops this Shipment can make.");
		txtTimeLoad.setToolTipText("Currently not supported");
		txtTimeUnLoad.setToolTipText("Currently not supported");
		txtLoadingType.setToolTipText("Currently not supported");
		txtUnloadingType.setToolTipText("Currently not supported");
		txtTrailerType.setToolTipText("Currently not supported");
		txtSize.setToolTipText("This is the volumetric size of the Shipment");
		cmbFromCountries.setToolTipText("This is the country of origin");
		cmbFromStates.setToolTipText("This is the state of origin");
		cmbFromCities.setToolTipText("This is the city of origin");
		cmbToCountries.setToolTipText("This is the destination country");
		cmbToStates.setToolTipText("This is the destination state");
		cmbToCities.setToolTipText("This is the destination city");
		txtWeight.setToolTipText("This is the weight of the shipement");
		txtEarliestArrival.setToolTipText("This is earliest the shipment can arrive");
		txtEarliestDeparture.setToolTipText("This is earliest the shipment can depart");
		txtLatestArrival.setToolTipText("This is latest the shipment can arrive");
		txtLatestDeparture.setToolTipText("This is latest the shipment can depart");
		chkCongestion.setToolTipText("Currently not supported");
		chkTolls.setToolTipText("Currently not supported");
		cmbPriority.setToolTipText("This is the importance of the shipment");
		
		
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
				cmbToCountries.addItem(m.get("Country").toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		cmbToCountries.setSelectedIndex(-1);
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

	protected void loadShippers()
	{			
		try
		{
			ArrayList<Map<String,Object>> data  = BaseClass.executeQuery("Select ShipperID, CompanyName from shipper order by CompanyName");
			shippers = new ComboItem[data.size()];
			for(int i = 0; i<data.size();i++)
			{
				ComboItem temp = new ComboItem();
				temp.setID((Integer)data.get(i).get("ShipperID"));
				temp.setName(data.get(i).get("CompanyName").toString());
				shippers[i]=temp;
				txtCompanyName.addItem(shippers[i]);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private void setShipper(ComboItem input)
	{
		selectedShipper = Shipper.Load(input.getID());
		txtContactName.setText(selectedShipper.getContactName());
		txtPhone.setText(selectedShipper.getPhoneNumber());
		txtEmail.setText(selectedShipper.getEmailAddress());
		txtPrefCarriers.setText(selectedShipper.getPrefferedCarriers());

		Location l = Location.Load(selectedShipper.getLocationID());
		this.cmbFromCountries.setText(l.getCountry());
		this.cmbFromStates.setText(l.getState());
		this.cmbFromCities.setText(l.getName());
	}
}



class ComboItem
{
	int id;
	String name;

	public ComboItem()
	{
		id=-1;
		name ="";
	}

	public void setName(String n)
	{
		name = n;
	}
	public void setID(int i)
	{
		id=i;
	}

	public int getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o==null)return false;
		if(o==this)return true;
		if(!(o instanceof ComboItem)) return false;
		ComboItem test = (ComboItem)o;
		if(test.getID()==id)
			return true;
		return false;
	}
}





