package GUI.RoutingForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Shipment;
import core.Vehicle;

import GUI.TableRefreshListener;
import GUI.ShipmentForms.ShipmentTable;
import Routing.NodeCrawler;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

public class RoutingForm extends JPanel{
	
	JButton btnRoute;
	JCheckBox chkNodeCrawler, chkBestFind, chkAStar,chkTravelByType,chkNextAvailVehicle;
	JLabel lblNC, lblBF, lblAS, lblTT, lblNAV;
	JSpinner  spinNCT,spinNCC,spinNCD,
		spinBFT,spinBFC,spinBFD,
		spinAST,spinASC,spinASD,
		spinTTT,spinTTC,spinTTD,
		spinNAVT,spinNAVC,spinNAVD;
	
	JComboBox cmbNCMode,cmbBFMode, cmbASMode,cmbTTMode,cmbNAVMode;
	private JSpinner spinner;
	private JLabel lblMode;
	private JLabel lblTime;
	private JLabel lblLost;
	private JLabel lblDistance;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private SegmentSelectionListener refresh;
	private Shipment source;
	public RoutingForm()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		this.spinASC = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinASD  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinAST  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		this.spinBFC  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinBFD  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinBFT  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		this.spinNAVC  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinNAVD  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinNAVT  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		this.spinNCC  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinNCD  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinNCT  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		this.spinTTC  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinTTD  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		this.spinTTT  = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		lblMode = new JLabel("Mode");
		add(lblMode, "10, 2");
		
		lblTime = new JLabel("Time");
		add(lblTime, "12, 2");
		
		lblLost = new JLabel("Cost");
		add(lblLost, "14, 2");
		
		lblDistance = new JLabel("Distance");
		add(lblDistance, "16, 2");
		chkNodeCrawler = new JCheckBox();
		
		
		add(chkNodeCrawler, "4, 4");
		this.lblNC = new JLabel("Node Crawler");
		add(this.lblNC,"6, 4");
		add(this.spinNCC,"14, 4");
		add(this.spinNCD,"16, 4");
		add(this.spinNCT,"12, 4");
		
		separator = new JSeparator();
		add(separator, "2, 5, 17, 1");
		this.chkAStar  = new JCheckBox();
		//spinner.setModel();
		//add(spinner, "8, 2");
		
		add(this.chkAStar , "4, 6");
		
		this.lblAS = new JLabel("AStar");
		add(this.lblAS ,"6, 6");
		add(this.spinAST,"12, 6");
		add(this.spinASC, "14, 6");
		add(this.spinASD,"16, 6");
		
		separator_1 = new JSeparator();
		add(separator_1, "2, 7, 17, 1");
		
		this.chkBestFind  = new JCheckBox();
		add(this.chkBestFind , "4, 8");
		this.lblBF = new JLabel("Best Find");
		add(this.lblBF, "6, 8");
		add(this.spinBFC,"14, 8");
		add(this.spinBFD,"16, 8");
		add(this.spinBFT,"12, 8");
		
		separator_2 = new JSeparator();
		add(separator_2, "2, 9, 17, 1");
		
		this.chkNextAvailVehicle  = new JCheckBox();
		add(this.chkNextAvailVehicle , "4, 10");
		this.lblNAV = new JLabel("Next Available Vehicle");
		add(this.lblNAV , "6, 10");
		add(this.spinNAVC ,"14, 10");
		add(this.spinNAVD , "16, 10");
		add(this.spinNAVT , "12, 10");
		
		separator_3 = new JSeparator();
		add(separator_3, "2, 11, 17, 1");
		
		this.chkTravelByType  = new JCheckBox();
		add(this.chkTravelByType ,"4, 12");
		this.lblTT = new JLabel("Travel by Type");
		add(this.lblTT , "6, 12");
		add(this.spinTTC,"14, 12");
		add(this.spinTTD,"16, 12");
		add(this.spinTTT,"12, 12");
		
		
		
		this.cmbASMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbASMode.setSelectedItem(Vehicle.TravelModes.ALL);
		this.cmbBFMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbNAVMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbNCMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbNCMode.setSelectedItem(Vehicle.TravelModes.ALL);
		this.cmbTTMode = new JComboBox(Vehicle.TravelModes.values());
		
		this.cmbBFMode.removeItem(Vehicle.TravelModes.ALL);
		this.cmbBFMode.removeItem(Vehicle.TravelModes.NONE);
		
		
		this.cmbNAVMode.removeItem(Vehicle.TravelModes.ALL);
		this.cmbNAVMode.removeItem(Vehicle.TravelModes.NONE);
		
		this.cmbTTMode.removeItem(Vehicle.TravelModes.ALL);
		this.cmbTTMode.removeItem(Vehicle.TravelModes.NONE);
		
		this.cmbASMode.setEnabled(false);
		this.cmbNCMode.setEnabled(false);
		
		add(this.cmbNCMode,"10,4");
		add(this.cmbBFMode ,"10,8");
		add(this.cmbNAVMode,"10,10");
		add(this.cmbASMode,"10,6");
		add(this.cmbTTMode ,"10,12");
		
		btnRoute = new JButton("Route");
		btnRoute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(source!=null)
				{
					NodeCrawler nc = new NodeCrawler(source);
					refresh.setSegs(nc.getPath());
					refresh.refreshTable();
				}
				
			}
		});
		add(btnRoute,"6, 14");
	}
	public void showPanel()
	{
		this.setVisible(true);
	}
	
	public void addTableRefreshListener(SegmentSelectionListener r)
	{
		refresh=r;
	}
	public void setShipment(Shipment s)
	{
		source=s;
	}
}
