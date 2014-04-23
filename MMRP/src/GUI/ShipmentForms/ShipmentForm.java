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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class ShipmentForm extends JPanel {
	JLabel lblLocationName,lblCompanyName,lblContactName,lblPhone,lblEmail,lblPrefCarriers;
	JTextField txtCompanyName,txtContactName,txtPhone,txtEmail,txtPrefCarriers;
	JComboBox cmbFromLocations,cmbToLocations,cmbPriority;
	JCheckBox chkTolls, chkCongestion;
	JLabel lblToLocation,lblPriority,lblSize,lblWeight,lblEarliestDateTimeArrival,lblEarliestDateTimeDeparture,lblLatestDateTimeArrival,lblLatestDateTimeDeparture,lblTimeToLoad,lblTimeToUnLoad,lblTollRoads,lblCongestionByPass,lblMaxStops;
	JTextField txtSize,txtWeight,txtMaxStops,txtTimeUnLoad,txtTimeLoad;

	
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
		
		shipperPanel.setLayout(new FormLayout(new ColumnSpec[]{
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
		}));
		
		lblLocationName=new JLabel("Location:");
		lblCompanyName = new JLabel("Company:");
		lblContactName = new JLabel("Contact:");
		lblPhone = new JLabel("Phone:");
		lblPrefCarriers = new JLabel("Preffered Carriers:");
		lblEmail = new JLabel("Email:");
		
		txtCompanyName = new JTextField(20);
		txtContactName = new JTextField(20);
		txtPhone=new JTextField(20);
		txtEmail = new JTextField(20);
		txtPrefCarriers = new JTextField(20);
		cmbFromLocations=new JComboBox();
		
		
		shipperPanel.add(lblLocationName,"2,2,right,center");
		shipperPanel.add(cmbFromLocations,"4,2");
		shipperPanel.add(lblCompanyName,"2,4,right,center");
		shipperPanel.add(txtCompanyName,"4,4");
		shipperPanel.add(lblContactName,"2,6,right,center");
		shipperPanel.add(txtContactName,"4,6");
		shipperPanel.add(lblPhone,"2,8,right,center");
		shipperPanel.add(txtPhone,"4,8");
		shipperPanel.add(lblEmail,"2,10,right,center");
		shipperPanel.add(txtEmail,"4,10");
		shipperPanel.add(lblPrefCarriers,"2,12,right,center");
		shipperPanel.add(txtPrefCarriers,"4,12");
		
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
		cmbToLocations=new JComboBox();
		
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
		
		shipmentPanel.add(lblToLocation,"2,2,right,center");
		shipmentPanel.add(cmbToLocations,"4,2");
		
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
	

}
