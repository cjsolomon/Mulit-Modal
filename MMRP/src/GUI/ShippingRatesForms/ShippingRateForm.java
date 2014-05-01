package GUI.ShippingRatesForms;

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
import core.ShippingRate;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ShippingRateForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel lblMileRate, lblWeight1, lblFlatRate,lblTravelType,lblCarrier, lblShippingRate, lblID;
	JTextField txtMileRate,txtWeight1,txtFlatRate, txtID;
	private JButton btnSaveEdit;
	
	private boolean edit = false;
	private JButton btnCancel;
	private JLabel lblWeight2;
	private JTextField txtWeight2;
	private JLabel lblWeight3;
	private JTextField txtWeight3;
	private JTextField txtRank;
	private JLabel lblRate1;
	private JLabel lblRate2;
	private JLabel lblRate3;
	private JTextField txtRate1;
	private JTextField txtRate2;
	private JTextField txtRate3;
	private JComboBox cbCarriers;
	private JComboBox cbTravelType;
	private JLabel lblRank;
	
	public ShippingRateForm() {
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(88dlu;default):grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(29dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(77dlu;default):grow"),
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
		
		
		lblMileRate=new JLabel("Mile Rate");
		lblWeight1 = new JLabel("Weight 1");
		lblFlatRate = new JLabel("Flat Rate");
		lblTravelType = new JLabel("Travel Type");
		lblCarrier = new JLabel("Carrier");
		lblShippingRate = new JLabel("Shipping Rate");
		lblID = new JLabel("Shipping Rate ID");
		
		txtMileRate=new JTextField(20);
		txtWeight1 = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblShippingRate, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		cbCarriers = new JComboBox();
		add(cbCarriers, "4, 6, fill, default");
		
		cbTravelType = new JComboBox();
		add(cbTravelType, "4, 8, fill, default");
		add(lblFlatRate,"2,10,right,center");
		add(lblTravelType,"2,8,right,center");
		add(lblCarrier,"2,6,right,center");
		txtFlatRate= new JTextField(20);
		add(txtFlatRate, "4, 10, right, center");
		add(lblMileRate,"2,12,right,center");
		add(txtMileRate, "4,12,right,center");
		add(lblWeight1,"2,14,right,center");
		add(txtWeight1, "4,14,right,center");
		
		btnSaveEdit = new JButton("Edit");
		
		lblRate1 = new JLabel("Rate 1");
		add(lblRate1, "7, 14, right, default");
		
		txtRate1 = new JTextField();
		add(txtRate1, "9, 14, fill, default");
		txtRate1.setColumns(10);
		
		lblWeight2 = new JLabel("Weight 2");
		add(lblWeight2, "2, 16, right, top");
		
		txtWeight2 = new JTextField();
		add(txtWeight2, "4, 16, fill, top");
		txtWeight2.setColumns(10);
		
		lblRate2 = new JLabel("Rate 2");
		add(lblRate2, "7, 16, right, default");
		
		txtRate2 = new JTextField();
		add(txtRate2, "9, 16, fill, default");
		txtRate2.setColumns(10);
		
		lblWeight3 = new JLabel("Weight 3");
		add(lblWeight3, "2, 18, right, default");
		
		txtWeight3 = new JTextField();
		add(txtWeight3, "4, 18, fill, default");
		txtWeight3.setColumns(10);
		
		lblRate3 = new JLabel("Rate 3");
		add(lblRate3, "7, 18, right, default");
		
		txtRate3 = new JTextField();
		add(txtRate3, "9, 18, fill, default");
		txtRate3.setColumns(10);
		
		lblRank = new JLabel("Rank");
		add(lblRank, "2, 22, right, default");
		
		txtRank = new JTextField();
		add(txtRank, "4, 22, fill, default");
		txtRank.setColumns(10);
		add(btnSaveEdit, "4, 25");
		
		txtID.setEditable(false);
		
		this.populateCarrierComboBox();
		this.populateTTComboBox();
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnCancel, "7, 25");
		

	
		btnSaveEdit.setVisible(true);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSaveEdit.isVisible())
				{
					btnSaveEdit.setVisible(true);
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

		public void showPanel(ShippingRate sr)
		{
			displayShippingRate(sr);
			setReadOnly();
			setVisible(true);
			this.btnSaveEdit.setText("Edit");
		}
		private void update()
		{
			btnSaveEdit.setVisible(false);
		}
		
		private void displayShippingRate(ShippingRate displaySR){
			this.txtID.setText(String.valueOf(displaySR.getId()));
			this.txtFlatRate.setText(String.valueOf(displaySR.getFlatRate()));
			this.txtMileRate.setText(String.valueOf(displaySR.getMileRate()));
			this.txtRank.setText(String.valueOf(displaySR.getRank()));
			this.txtRate1.setText(String.valueOf(displaySR.getRate1()));
			this.txtRate2.setText(String.valueOf(displaySR.getRate2()));
			this.txtRate3.setText(String.valueOf(displaySR.getRate3()));
			this.txtWeight1.setText(String.valueOf(displaySR.getWeight1()));
			this.txtWeight2.setText(String.valueOf(displaySR.getWeight2()));
			this.txtWeight3.setText(String.valueOf(displaySR.getWeight3()));
			this.cbCarriers.setSelectedItem(displaySR.getCarrier().getCarrierName());
			this.cbTravelType.setSelectedItem(displaySR.getType().getTravelTypeName());
			
			btnSaveEdit.setVisible(true);
		}
		
		private void setEditable()
		{
		
			this.txtFlatRate.setEditable(true);
			this.txtMileRate.setEditable(true);
			this.txtRank.setEditable(true);
			this.txtRate1.setEditable(true);
			this.txtRate2.setEditable(true);
			this.txtRate3.setEditable(true);
			this.txtWeight1.setEditable(true);
			this.txtWeight2.setEditable(true);
			this.txtWeight3.setEditable(true);
			this.cbCarriers.setEnabled(true);
			this.cbTravelType.setEnabled(true);
			btnSaveEdit.setVisible(true);
		}
		
		private void setReadOnly()
		{
			this.txtFlatRate.setEditable(false);
			this.txtMileRate.setEditable(false);
			this.txtRank.setEditable(false);
			this.txtRate1.setEditable(false);
			this.txtRate2.setEditable(false);
			this.txtRate3.setEditable(false);
			this.txtWeight1.setEditable(false);
			this.txtWeight2.setEditable(false);
			this.txtWeight3.setEditable(false);
			this.cbCarriers.setEnabled(false);
			this.cbTravelType.setEnabled(false);
			btnSaveEdit.setVisible(true);
			
		}
		
		private void populateCarrierComboBox(){
			
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct CarrierName from Carriers");
				for(Map m :tmp)
				{
					this.cbCarriers.addItem(m.get("CarrierName").toString());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			this.cbCarriers.setSelectedIndex(-1);
		}
		
		private void populateTTComboBox(){
			
			try
			{
				ArrayList<Map<String,Object>> tmp = BaseClass.executeQuery("Select Distinct VehicleTypeName from TravelTypes");
				for(Map m :tmp)
				{
					this.cbTravelType.addItem(m.get("VehicleTypeName").toString());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			this.cbTravelType.setSelectedIndex(-1);
		}




	}


