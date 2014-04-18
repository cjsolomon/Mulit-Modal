package GUI;

import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class TruckPanel extends Panel {
	
	JLabel lblName,lblCarrier,lblStatus;
	JTextField txtName;
	JComboBox cmbCarrier,cmbStatus;
	VehicleTable truckTable;
	JTabbedPane jp;
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
		lblName=new JLabel("Name:");
		lblCarrier = new JLabel("Carrier:");
		lblStatus = new JLabel("Status:");
		
		txtName = new JTextField(20);
		cmbCarrier = new JComboBox<core.Carrier>();
		cmbStatus = new JComboBox<core.Vehicle.Status>();
		
		add(lblName,"2,4,right,center");
		add(txtName,"4,4");
		
		add(lblStatus,"2,6,right,center");
		add(cmbStatus,"4,6");
		add(lblCarrier,"2,8,right,center");
		add(cmbCarrier,"4,8");
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(truckTable);
		truckTable.setVisible(false);
		add(sp,"2,2,4,2");
	}
	public void showPanel()
	{
		System.out.println("Called show truck panel");
		truckTable.showPanel();
		this.setVisible(true);
		
	}
	
}
