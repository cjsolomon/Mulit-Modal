package GUI.ShippingRatesForms;

import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.TableRefreshListener;
import GUI.TravelTypeSetListener;

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
	private JButton btnSave,btnEdit;
	

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
	private JLabel lblRank;
	private ShippingRate source;
	private boolean newShippingRate;
	private ArrayList<TableRefreshListener> refresh = new ArrayList<TableRefreshListener>();
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
		lblCarrier = new JLabel("Carrier");
		lblShippingRate = new JLabel("Shipping Rate");
		lblID = new JLabel("Shipping Rate ID");
		
		txtMileRate=new JTextField(20);
		txtWeight1 = new JTextField(20);
		txtID = new JTextField(10);
		
		add(lblShippingRate, "4, 2, center, center");
		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		
		cbCarriers = new JComboBox(Carrier.LoadAll("").toArray());
		add(cbCarriers, "4, 6, fill, default");
		
		add(lblFlatRate,"2,10,right,center");
		add(lblCarrier,"2,6,right,center");
		txtFlatRate= new JTextField(20);
		add(txtFlatRate, "4, 10, right, center");
		add(lblMileRate,"2,12,right,center");
		add(txtMileRate, "4,12,right,center");
		add(lblWeight1,"2,14,right,center");
		add(txtWeight1, "4,14,right,center");
		
		btnEdit = new JButton("Edit");
		btnSave = new JButton("Save");
		
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
		add(btnSave, "4, 25");
		add(btnEdit,"4,25");
		
		txtID.setEditable(false);
		
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Click to cancel any changes");
		add(btnCancel, "7, 25");
		btnEdit.setToolTipText("Click to edit the Shipping Rate");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnSave.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		btnSave.setToolTipText("Click to save changes");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				update();
				btnSave.setVisible(false);
				setReadOnly();
				btnEdit.setVisible(true);
 				for(TableRefreshListener t : refresh) t.refreshTable();
 				newShippingRate=false;
			}
		});
		btnCancel.setToolTipText("Click to cancel any changes");
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
		
		this.txtFlatRate.setToolTipText("This is the base rate");
		this.txtMileRate.setToolTipText("This is the cost charges per mile");
		this.txtRank.setToolTipText("Currently not supported");
		this.txtRate1.setToolTipText("This is the cost charged when the shipment is between Weight 1 and Weight 2");
		this.txtRate2.setToolTipText("This is the cost charged when the shipment is between Weight 2 and Weight 3");
		this.txtRate3.setToolTipText("This is the cost charged when the shipment is above Weight 3");
		this.txtWeight1.setToolTipText("This is the first category of shipment weight");
		this.txtWeight2.setToolTipText("This is the second category of shipment weight");
		this.txtWeight3.setToolTipText("This is the third category of shipment weight");
		
		this.lblFlatRate.setToolTipText("This is the base rate");
		this.lblMileRate.setToolTipText("This is the cost charges per mile");
		this.lblRank.setToolTipText("Currently not supported");
		this.lblRate1.setToolTipText("This is the cost charged when the shipment is between Weight 1 and Weight 2");
		this.lblRate2.setToolTipText("This is the cost charged when the shipment is between Weight 2 and Weight 3");
		this.lblRate3.setToolTipText("This is the cost charged when the shipment is above Weight 3");
		this.lblWeight1.setToolTipText("This is the first category of shipment weight");
		this.lblWeight2.setToolTipText("This is the second category of shipment weight");
		this.lblWeight3.setToolTipText("This is the third category of shipment weight");
		
	
		}

		public void showPanel()
		{
			newShippingRate = true;
			source=null;
			setEditable();
			setNew();
			this.setVisible(true);
			this.btnSave.setVisible(true);
			this.btnEdit.setVisible(false);
		}

		public void showPanel(ShippingRate sr)
		{
			newShippingRate =false;
			source=sr;
			displayShippingRate();
			setReadOnly();
			setVisible(true);
			this.btnEdit.setVisible(true);
			this.btnSave.setVisible(false);
		}
		private void update()
		{
			String errorString = "";
			//Error Checking
			if(this.txtFlatRate.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtFlatRate.getText()), 0))
				errorString += "The flat rate entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtMileRate.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtMileRate.getText()), 0))
				errorString += "The mile rate entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtRate1.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtRate1.getText()), 0))
				errorString += "The rate 1 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtRate2.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtRate2.getText()), 0))
				errorString += "The rate 2 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtRate3.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtRate3.getText()), 0))
				errorString += "The rate 3 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtWeight1.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtWeight1.getText()), 0))
				errorString += "The weight 1 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtWeight2.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtWeight2.getText()), 0))
				errorString += "The weight 2 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtWeight3.getText().isEmpty() || !core.FormatChecker.checkLowerBound(Double.valueOf(this.txtWeight3.getText()), 0))
				errorString += "The weight 3 entered was not greater than 0. Please enter a numeric value greater than 0.\n";
			if(this.txtRank.getText().isEmpty() || !core.FormatChecker.inRange(Integer.parseInt(this.txtRank.getText()), 0, 10))
				errorString += "The rank value entered was not a value between 0 and 10 inclusive.\n";
			if(errorString.isEmpty())
			{
				if(source == null)source = new ShippingRate();
				
				source.setCarrier((Carrier)this.cbCarriers.getSelectedItem());
				source.setFlatRate(Double.parseDouble(this.txtFlatRate.getText()));
				source.setMileRate(Double.parseDouble(this.txtMileRate.getText()));
				source.setRank(Integer.parseInt(this.txtRank.getText()));
				source.setRate1(Double.parseDouble(this.txtRate1.getText()));
				source.setRate2(Double.parseDouble(this.txtRate2.getText()));
				source.setRate3(Double.parseDouble(this.txtRate3.getText()));
				source.setWeight1(Double.parseDouble(this.txtWeight1.getText()));
				source.setWeight2(Double.parseDouble(this.txtWeight2.getText()));
				source.setWeight3(Double.parseDouble(this.txtWeight3.getText()));
				
				source.Update();
				
				this.txtID.setText(String.valueOf(source.getId()));
			}
			else{
				//An error occurred
				JOptionPane.showMessageDialog(null, errorString , "Invalid data entered", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void displayShippingRate(){
			this.txtID.setText(String.valueOf(source.getId()));
			this.txtFlatRate.setText(String.valueOf(source.getFlatRate()));
			this.txtMileRate.setText(String.valueOf(source.getMileRate()));
			this.txtRank.setText(String.valueOf(source.getRank()));
			this.txtRate1.setText(String.valueOf(source.getRate1()));
			this.txtRate2.setText(String.valueOf(source.getRate2()));
			this.txtRate3.setText(String.valueOf(source.getRate3()));
			this.txtWeight1.setText(String.valueOf(source.getWeight1()));
			this.txtWeight2.setText(String.valueOf(source.getWeight2()));
			this.txtWeight3.setText(String.valueOf(source.getWeight3()));
			this.cbCarriers.setSelectedItem(source.getCarrier());
		}
		
		private void setNew()
		{
			this.txtID.setText("");
			this.txtFlatRate.setText("");
			this.txtMileRate.setText("");
			this.txtRank.setText("");
			this.txtRate1.setText("");
			this.txtRate2.setText("");
			this.txtRate3.setText("");
			this.txtWeight1.setText("");
			this.txtWeight2.setText("");
			this.txtWeight3.setText("");
			this.cbCarriers.setSelectedIndex(-1);
		}
		private void setEditable()
		{
		
			this.txtFlatRate.setToolTipText("Enter a flat rate greater than 0");
			this.txtMileRate.setToolTipText("Enter a mile rate greater than 0");
			this.txtRank.setToolTipText("Enter a rank between 0 and 10");
			this.txtRate1.setToolTipText("Enter a rate value greater than 0");
			this.txtRate2.setToolTipText("Enter a rate value greater than 0");
			this.txtRate3.setToolTipText("Enter a rate value greater than 0");
			this.txtWeight1.setToolTipText("Enter a weight value greater than 0");
			this.txtWeight2.setToolTipText("Enter a weight value greater than 0");
			this.txtWeight3.setToolTipText("Enter a weight value greater than 0");
			
			if(!newShippingRate)
				this.cbCarriers.setEnabled(false);
			else
				this.cbCarriers.setEnabled(true);
			this.txtFlatRate.setEditable(true);
			this.txtMileRate.setEditable(true);
			this.txtRank.setEditable(true);
			this.txtRate1.setEditable(true);
			this.txtRate2.setEditable(true);
			this.txtRate3.setEditable(true);
			this.txtWeight1.setEditable(true);
			this.txtWeight2.setEditable(true);
			this.txtWeight3.setEditable(true);
			
			btnEdit.setVisible(false);
			btnSave.setVisible(true);
		}
		
		private void setReadOnly()
		{
			this.txtFlatRate.setToolTipText("This is the base rate");
			this.txtMileRate.setToolTipText("This is the cost charges per mile");
			this.txtRank.setToolTipText("Currently not supported");
			this.txtRate1.setToolTipText("This is the cost charged when the shipment is between Weight 1 and Weight 2");
			this.txtRate2.setToolTipText("This is the cost charged when the shipment is between Weight 2 and Weight 3");
			this.txtRate3.setToolTipText("This is the cost charged when the shipment is above Weight 3");
			this.txtWeight1.setToolTipText("This is the first category of shipment weight");
			this.txtWeight2.setToolTipText("This is the second category of shipment weight");
			this.txtWeight3.setToolTipText("This is the third category of shipment weight");
			
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
			btnEdit.setVisible(true);
			btnSave.setVisible(false);
			
		}
	
		public void addTableRefreshListener(TableRefreshListener t)
		{
			refresh.add(t);
		}


	}


