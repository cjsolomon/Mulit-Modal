package GUI.LocationForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Location;
import core.Truck;
import core.Vehicle;
import core.Vehicle.TravelModes;
import GUI.TableRefreshListener;
import javax.swing.SwingConstants;

public class LocationForm extends JPanel {

	JLabel lblCity,lblState,lblCountry,lblLat,lblLon,lblTruck,lblPlane,lblCargo,lblRail;
	JTextField txtLon,txtLat,txtCountry,txtState,txtCity;
	JCheckBox chkTruck,chkPlane,chkRail,chkCargo;
	private JButton btnEdit,btnSave,btnCancel;
	Location source;
	private ArrayList<TableRefreshListener> refreshList;
	private JLabel lblLocation;
	public LocationForm()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(33dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
		
		lblCity = new JLabel("City:");
		lblState = new JLabel("State:");
		lblCountry = new JLabel("Country:");
		lblLat= new JLabel("Latitude:");
		lblLon = new JLabel("Longitude:");
		lblTruck = new JLabel("Truck:");
		lblPlane = new JLabel("Train:");
		lblCargo = new JLabel("Cargo:");
		lblRail = new JLabel("Rail:");
		chkTruck = new JCheckBox();
		chkRail  = new JCheckBox();
		chkPlane  = new JCheckBox();
		chkCargo  = new JCheckBox();
		txtLat = new JTextField(20);
		txtLon = new JTextField(20);
		txtCity  = new JTextField(20);
		txtState  = new JTextField(20);
		txtCountry  = new JTextField(20);
		
		lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLocation, "4, 2");
		
		add(lblCity, "2, 4, right, center");
		add(txtCity,"4, 4");
		
		add(lblState , "2, 6, right, center");
		add(txtState,"4, 6");
		
		add(lblCountry,"2, 8, right, center");
		add(txtCountry,"4, 8");
		
		add(lblLat,"2, 10, right, center");
		add(txtLat,"4, 10");
		
		add(lblLon,"2, 12, right, center");
		add(txtLon,"4, 12");
		
		add(lblTruck, "8, 4, right, center");
		add(lblRail,"8, 6, right, center");
		add(lblPlane,"8, 8, right, center");
		add(lblCargo,"8, 10, right, center");
		
		add(chkTruck,"10, 4");
		add(chkRail,"10, 6");
		add(chkPlane,"10, 8");
		add(chkCargo,"10, 10");
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Click here to save changes");
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				update();
				setReadOnly();
				btnSave.setVisible(false);
				btnEdit.setVisible(true);
				for(TableRefreshListener rl : refreshList)
				{
					rl.refreshTable();
				}
			}
		});
		add(btnSave,"8, 14");
		
		
		btnEdit = new JButton("Edit");
		btnEdit.setToolTipText("Click here to edit selected Location");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnEdit.setVisible(false);
				btnSave.setVisible(true);
			}
		});
		add(btnEdit,"8, 14");
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Click here to cancel changes");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnEdit.isVisible())
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
		add(btnCancel,"10, 14");
		btnSave.setVisible(false);
		
		
		//Tool tips
		this.chkCargo.setToolTipText("Allows Location to be reached by Cargo Ship");
		this.chkPlane.setToolTipText("Allows Location to be reached by Plane");
		this.chkRail.setToolTipText("Allows Location to be reached by Rail");
		this.chkTruck.setToolTipText("Allows Location to be reached by Truck");
		this.lblCity.setToolTipText("This is the name of this Location's city");
		this.lblCountry.setToolTipText("This is the name of this Location's country");
		this.lblState.setToolTipText("This is the name of this Location's state");
		this.lblLat.setToolTipText("This is the latitude of this Location");
		this.lblLon.setToolTipText("This is the longitude of this Location");
	
	}
	
	public void showPanel(Location l)
	{
		source =l;
		setLocation();
		setReadOnly();
		btnEdit.setVisible(true);
		btnSave.setVisible(false);
		setVisible(true);
	}
	public void showPanel()
	{
		setEditable();
		setNew();
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
		setVisible(true);
	}
	private void setLocation()
	{
		if(source==null)return;
		
		txtCountry.setText(source.getCountry());
		txtState.setText(source.getState());
		txtCity.setText(source.getName());
		txtLon.setText(Double.toString(source.getLongitude()));
		txtLat.setText(Double.toString(source.getLatitude()));
		chkTruck.setSelected(source.travelTypeAvailable(Vehicle.TravelModes.TRUCK));
		chkPlane.setSelected(source.travelTypeAvailable(Vehicle.TravelModes.PLANE));

		chkRail.setSelected(source.travelTypeAvailable(Vehicle.TravelModes.RAIL));

		chkCargo.setSelected(source.travelTypeAvailable(Vehicle.TravelModes.CARGO));
	}
	private void setNew()
	{

		this.txtCity.setToolTipText("Enter a city name between 1 and 45 characters");
		this.txtCountry.setToolTipText("Enter a country name between 1 and 45 characters");
		this.txtState.setToolTipText("Enter a state name between 1 and 45 characters");
		this.txtLat.setToolTipText("Enter a latitude value between -90.0 and 90.0 degrees");
		this.txtLon.setToolTipText("Enter a longitude value between -180.0 and 180.0 degrees");
		
		source=null;
		txtCountry.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtLon.setText("");
		txtLat.setText("");
		chkTruck.setSelected(true);
		
		chkPlane.setSelected(false);	
		chkRail.setSelected(false);
		chkCargo.setSelected(false);
	}
	public void addRefreshListener(TableRefreshListener trl)
	{
		if(refreshList==null)refreshList= new ArrayList<TableRefreshListener>();
		refreshList.add(trl);
	}
	private void setReadOnly()
	{
		
		this.txtCity.setToolTipText(this.lblCity.getToolTipText());
		this.txtCountry.setToolTipText(this.txtCountry.getToolTipText());
		this.txtState.setToolTipText(this.txtState.getToolTipText());
		this.txtLat.setToolTipText(this.txtLat.getToolTipText());
		this.txtLon.setToolTipText(this.txtLon.getToolTipText());
		
		txtCountry.setEnabled(false);
		txtCity.setEnabled(false);
		txtState.setEnabled(false);
		txtLon.setEnabled(false);
		txtLat.setEnabled(false);
		chkTruck.setEnabled(false);
		chkRail.setEnabled(false);
		chkPlane.setEnabled(false);
		chkCargo.setEnabled(false);
	}
	private void setEditable()
	{
		txtCountry.setEnabled(true);
		txtCity.setEnabled(true);
		txtState.setEnabled(true);
		txtLon.setEnabled(true);
		txtLat.setEnabled(true);
		chkRail.setEnabled(true);
		chkPlane.setEnabled(true);
		chkCargo.setEnabled(true);
	}
	
	private void update()
	{
		
		String errorString = "";
		//Error Checking
		if(this.txtCity.getText().isEmpty() || this.txtCity.getText().length() < 1 ||  this.txtCity.getText().length() > 45)
			errorString += "The City was not a valid entry between 1 and 45 characters.\n";
		if(this.txtState.getText().isEmpty() || this.txtState.getText().length() < 1 ||  this.txtState.getText().length() > 45)
			errorString += "The State was not a valid entry between 1 and 45 characters.\n";
		if(this.txtCountry.getText().isEmpty() || this.txtCountry.getText().length() < 1 ||  this.txtCountry.getText().length() > 45)
			errorString += "The Country was not a valid entry between 1 and 45 characters.\n";
		if(this.txtLat.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtLat.getText()),-90.0, 90.0))
			errorString += "The entered latitude value was not valid. Please enter a latitude value between -90.0 degrees and +90.0d degrees.\n";
		if(this.txtLon.getText().isEmpty() || !core.FormatChecker.inRange(Double.valueOf(this.txtLon.getText()),-180.0, 180.0))
			errorString += "The entered longitude value was not valid. Please enter a longitude value between -180.0 degrees and +180.0d degrees.\n";
		
		if(errorString.isEmpty())
		{
			if(source==null)source = new Location();
			for(TravelModes m : Vehicle.TravelModes.values())
				source.removeTravelMode(m);
			source.setCountry(txtCountry.getText());
			source.setState(txtState.getText());
			source.setName(txtCity.getText());
			source.setLatitude(Double.parseDouble(txtLat.getText()));
			source.setLongitude(Double.parseDouble(txtLon.getText()));
			if(chkTruck.isSelected())
				source.addTravelMode(Vehicle.TravelModes.TRUCK);
			if(chkRail.isSelected())
				source.addTravelMode(Vehicle.TravelModes.RAIL);
			if(chkPlane.isSelected())
				source.addTravelMode(Vehicle.TravelModes.PLANE);
			if(chkCargo.isSelected())
				source.addTravelMode(Vehicle.TravelModes.RAIL);
			
			source.Update();
		}
		else{
			//An error occurred
			JOptionPane.showMessageDialog(null, errorString , "Invalid data entered", JOptionPane.ERROR_MESSAGE);
		}
	}

}
