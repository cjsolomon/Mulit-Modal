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
	 JScrollPane sp;
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
		 sp = new JScrollPane();
		sp.setViewportView(truckTable);
		truckTable.setVisible(false);
		jp.setVisible(false);
		add(sp,"2,2,4,2");
		add(jp,"2,6");
	}
	public void showPanel()
	{
		System.out.println("Called show truck panel");
		truckTable.setData();
		jp.setVisible(false);
		this.sp.setViewportView(truckTable);
		truckTable.setVisible(true);
		this.setVisible(true);
		
	}
	public void setReadOnly()
	{
		this.txtName.setText(t.getVehicleName());
		this.cmbCarrier.setSelectedItem(t.getCarrier());
		this.cmbStatus.setSelectedItem(t.getStatus());
	}


	
}
