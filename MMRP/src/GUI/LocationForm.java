package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class LocationForm extends JPanel {
	
	JLabel lblLat, lblLon, lblCity,lblState,lblCountry;
	JTextField txtLat,txtLon,txtCity,txtState,txtCountry;
	
	
	public LocationForm() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		
		lblLat=new JLabel("Latitude:");
		lblLon = new JLabel("Longitute:");
		lblCity = new JLabel("City:");
		lblState = new JLabel("State:");
		lblCountry = new JLabel("Country:");
		
		txtLat=new JTextField(20);
		txtLon = new JTextField(20);
		txtCity= new JTextField(20);
		txtState = new JTextField(20);
		txtCountry = new JTextField(20);
		add(lblCity,"2,2,right,center");
		add(txtCity, "4,2,right,center");
		add(lblState,"2,4,right,center");
		add(txtState, "4,4,right,center");
		add(lblCountry,"2,6,right,center");
		add(txtCountry, "4,6,right,center");
		add(lblLat,"2,8,right,center");
		add(txtLat, "4,8,right,center");
		add(lblLon,"2,10,right,center");
		add(txtLon, "4,10,right,center");
	}

}
