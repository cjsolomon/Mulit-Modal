package GUI.RoutingForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Segment;
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
	private JLabel label;
	private JLabel lblRouting;
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
		cmbBFMode.setEnabled(false);
		this.cmbNAVMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbNCMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbNCMode.setSelectedItem(Vehicle.TravelModes.ALL);
		this.cmbTTMode = new JComboBox(Vehicle.TravelModes.values());
		this.cmbBFMode.setSelectedItem(Vehicle.TravelModes.ALL);
		
		this.cmbNAVMode.removeItem(Vehicle.TravelModes.ALL);
		this.cmbNAVMode.removeItem(Vehicle.TravelModes.NONE);
		
		this.cmbTTMode.removeItem(Vehicle.TravelModes.ALL);
		this.cmbTTMode.removeItem(Vehicle.TravelModes.NONE);
		
		this.cmbASMode.setEnabled(false);
		this.cmbNCMode.setEnabled(false);
		this.cmbBFMode.setEnabled(false);
		
		add(this.cmbNCMode,"10,4");
		add(this.cmbBFMode ,"10,8");
		add(this.cmbNAVMode,"10,10");
		add(this.cmbASMode,"10,6");
		add(this.cmbTTMode ,"10,12");
		
		btnRoute = new JButton("Route");
		btnRoute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				//javax.swing.JPopupMenu pu = new javax.swing.JPopupMenu("Hello");
				//pu.setVisible(true);
				if(source!=null && (chkNodeCrawler.isSelected() || chkBestFind.isSelected() || chkTravelByType.isSelected() || chkAStar.isSelected() || chkNextAvailVehicle.isSelected()))
				{
					ArrayList<Segment> bestRoute = new ArrayList<Segment>();
					double cost = 10000000;
					
					if(chkNodeCrawler.isSelected()){
						if((Integer)spinNCD.getValue() + (Integer)spinNCT.getValue() + (Integer)spinNCC.getValue() < 1){
							JOptionPane.showMessageDialog(null, "The Node Crawler weights must atleast have a value of 1", "Invalid Weights", JOptionPane.ERROR_MESSAGE);
						}else{
							Routing.NodeCrawler NC = new Routing.NodeCrawler(source);
							NC.setMetric(new Routing.WeightedMetric((Integer)spinNCD.getValue(),(Integer)spinNCT.getValue(), (Integer)spinNCC.getValue()));
							bestRoute = NC.getPath();
							cost = NC.getTotalRouteWeightedCost(bestRoute);
						}
					}
					if(chkBestFind.isSelected()){
						
						if((Integer)spinBFD.getValue() + (Integer)spinBFT.getValue() + (Integer)spinBFC.getValue() < 1){
							JOptionPane.showMessageDialog(null, "The Best Find weights must atleast have a value of 1", "Invalid Weights", JOptionPane.ERROR_MESSAGE);
						}else{
							Routing.BestFirstFind BFF  = new Routing.BestFirstFind(new Routing.WeightedMetric((Integer)spinBFD.getValue(),(Integer)spinBFT.getValue(), (Integer)spinBFC.getValue()), source);
							ArrayList<Segment> newPath = BFF.getPath();
							if(BFF.getTotalRouteWeightedCost(newPath) < cost){
								cost = BFF.getTotalRouteWeightedCost(newPath);
								bestRoute = newPath;
							}
						}
					}	
					if(chkTravelByType.isSelected()){
						if((Integer)spinTTD.getValue() + (Integer)spinTTT.getValue() + (Integer)spinTTC.getValue() < 1){
							JOptionPane.showMessageDialog(null, "The Travel By Type weights must atleast have a value of 1", "Invalid Weights", JOptionPane.ERROR_MESSAGE);
						}else{
							Routing.TravelByType TBT  = new Routing.TravelByType((Vehicle.TravelModes)cmbTTMode.getSelectedItem(), new Routing.WeightedMetric((Integer)spinTTD.getValue(),(Integer)spinTTT.getValue(), (Integer)spinTTC.getValue()), source);
							ArrayList<Segment> newPath = TBT.getPath();
							if(TBT.getTotalRouteWeightedCost(newPath) < cost){
								cost = TBT.getTotalRouteWeightedCost(newPath);
								bestRoute = newPath;
							}
						}
						
					}
					if(chkAStar.isSelected()){
						if((Integer)spinASD.getValue() + (Integer)spinAST.getValue() + (Integer)spinASC.getValue() < 1){
							JOptionPane.showMessageDialog(null, "The A Star weights must atleast have a value of 1", "Invalid Weights", JOptionPane.ERROR_MESSAGE);
						}else{
							Routing.AStarAlg AS  = new Routing.AStarAlg(source,new Routing.WeightedMetric((Integer)spinASD.getValue(),(Integer)spinAST.getValue(), (Integer)spinASC.getValue()));
							ArrayList<Segment> newPath = AS.getPath();
							if(AS.getTotalRouteWeightedCost(newPath) < cost){
								cost = AS.getTotalRouteWeightedCost(newPath);
								bestRoute = newPath;
							}
						}
					}
					if(chkNextAvailVehicle.isSelected()){
						if((Integer)spinNAVD.getValue() + (Integer)spinNAVT.getValue() + (Integer)spinNAVC.getValue() < 1){
							JOptionPane.showMessageDialog(null, "The Noext Avialable Vehicle weights must atleast have a value of 1", "Invalid Weights", JOptionPane.ERROR_MESSAGE);
						}else{
							Routing.NextAvailableVehicle NAV  = new Routing.NextAvailableVehicle((Vehicle.TravelModes)cmbNAVMode.getSelectedItem(), new Routing.WeightedMetric((Integer)spinNAVD.getValue(),(Integer)spinNAVT.getValue(), (Integer)spinNAVC.getValue()), source);
							ArrayList<Segment> newPath = NAV.getPath();
							if(NAV.getTotalRouteWeightedCost(newPath) < cost){
								cost = NAV.getTotalRouteWeightedCost(newPath);
								bestRoute = newPath;
							}
						}
					}
					
					refresh.setSegs(bestRoute);
					refresh.refreshTable();
				}
				
			}
		});
		add(btnRoute,"6, 14");
		
		label = new JLabel("");
		add(label, "6, 16");
		
		this.btnRoute.setToolTipText("Click here to route the selected shipment");
		this.chkAStar.setToolTipText("Use A Star algorithm to route shipment");
		this.chkBestFind.setToolTipText("Use Best Find algorithm to route shipment");
		this.chkNextAvailVehicle.setToolTipText("Use Next Available Vehicle algorithm to route shipment");
		this.chkNodeCrawler.setToolTipText("Use Node Crawler algorithm to route shipment");
		this.chkTravelByType.setToolTipText("Use Travel By Type algorithm to route shipment");
		this.cmbASMode.setToolTipText("Not used for A Star");
		this.cmbBFMode.setToolTipText("Not used for Best Find");
		this.cmbNAVMode.setToolTipText("Select the Vehicle Mode to use");
		this.cmbNCMode.setToolTipText("Not used for Node Crawler");
		this.cmbTTMode.setToolTipText("Select the Vehicle Mode to use");
		this.spinASC.setToolTipText("Set the importance of low fiscal cost");
		this.spinASD.setToolTipText("Set the importance of low distance");
		this.spinAST.setToolTipText("Set the importance of low time");
		this.spinBFC.setToolTipText("Set the importance of low fiscal cost");
		this.spinBFD.setToolTipText("Set the importance of low distance");
		this.spinBFT.setToolTipText("Set the importance of low time");
		this.spinNAVC.setToolTipText("Set the importance of low fiscal cost");
		this.spinNAVD.setToolTipText("Set the importance of low distance");
		this.spinNAVT.setToolTipText("Set the importance of low time");
		this.spinNCC.setToolTipText("Set the importance of low fiscal cost");
		this.spinNCD.setToolTipText("Set the importance of low distance");
		this.spinNCT.setToolTipText("Set the importance of low time");
		this.spinTTC.setToolTipText("Set the importance of low fiscal cost");
		this.spinTTD.setToolTipText("Set the importance of low distance");
		this.spinTTT.setToolTipText("Set the importance of low time");

		
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
