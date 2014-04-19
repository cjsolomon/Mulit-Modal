package GUI;

import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Carrier;
import core.Truck;

public class TruckPanel extends Panel {
	
	JLabel lblName,lblCarrier,lblStatus;
	JTextField txtName;
	JComboBox cmbCarrier,cmbStatus;
	VehicleTable truckTable;
	JTabbedPane jp;
	SegmentTable schedule;
	Truck t;
	public TruckPanel()
	{
		
		
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(103dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(67dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(61dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		truckTable= new VehicleTable("Truck");
		truckTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				t = (Truck)truckTable.getSelectedVehicle();
				if(t!=null)
				{
					schedule.showPanel(t);
					setReadOnly();
					jp.setVisible(true);
				}
			}
		});
		
		jp=new JTabbedPane();
		jp.addTab("Basic", getBasicPanel());
		schedule=new SegmentTable();
		jp.add("Schedule",schedule);
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(truckTable);
		truckTable.setVisible(false);
		jp.setVisible(false);
		add(sp,"2,2,4,2");
		add(jp,"2,6");
	}
	public void showPanel()
	{
		System.out.println("Called show truck panel");
		truckTable.showPanel();
		jp.setVisible(false);
		this.setVisible(true);
		
	}
	public void setReadOnly()
	{
		this.txtName.setText(t.getVehicleName());
		this.cmbCarrier.setSelectedItem(t.getCarrier());
		this.cmbStatus.setSelectedItem(t.getStatus());
	}
	private JPanel getBasicPanel()
	{
		JPanel basicInfo = new JPanel();
		basicInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(67dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblName=new JLabel("Name:");
		lblCarrier = new JLabel("Carrier:");
		lblStatus = new JLabel("Status:");
		
		txtName = new JTextField(20);

		cmbStatus = new JComboBox(core.Vehicle.Status.values());
		cmbCarrier = new JComboBox(Carrier.LoadAll("").toArray());
		
		
		basicInfo.add(lblName,"2,4,right,center");
		basicInfo.add(txtName,"4,4");
		
		basicInfo.add(lblStatus,"2,6,right,center");
		basicInfo.add(cmbStatus,"4,6");
		basicInfo.add(lblCarrier,"2,8,right,center");
		basicInfo.add(cmbCarrier,"4,8");
		return basicInfo;
		
	}

	
}
