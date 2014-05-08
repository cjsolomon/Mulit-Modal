package  GUI.PlaneForms;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Carrier;
import core.Plane;
import core.Vehicle;
import GUI.*;
import javax.swing.JButton;

public class PlaneBasicPanel extends JPanel {
	
	JLabel lblName,lblCarrier,lblStatus;
	JComboBox cmbStatus,cmbCarrier;
	JTextField txtName;
	Plane source;
	private JButton btnEdit;
	private JButton btnCancel;
	private JButton btnSave;
	ArrayList<TableRefreshListener> listener = new ArrayList<TableRefreshListener>();
	ArrayList<TravelTypeSetListener> travelTypeListener = new ArrayList<TravelTypeSetListener>();
	public PlaneBasicPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(68dlu;default)"),
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
		
		
		add(lblName,"2,2,right,center");
		add(txtName,"4, 2, 2, 1");
		
		add(lblStatus,"2,4,right,center");
		add(cmbStatus,"4, 4, 2, 1");
		add(lblCarrier,"2,6,right,center");
		add(cmbCarrier,"4, 6, 2, 1");
		
		btnEdit = new JButton("Edit");
		btnEdit.setToolTipText("Click here to edit Plane's information");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
				btnSave.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		add(btnEdit, "4, 8");
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Click here to save changes");
		btnSave.setVisible(false);
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(source==null)
				{
					update();
					for(TravelTypeSetListener t: travelTypeListener) t.setTravelType();
				}
				else
					update();
				btnSave.setVisible(false);
				readOnly();
				btnEdit.setVisible(true);
			}
		});
		add(btnSave,"4,8");
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Click here to cancel changes");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(btnSave.isVisible() && source!=null)
				{
					readOnly();
					btnSave.setVisible(false);
					btnEdit.setVisible(true);
					
				}
				else
				{
					getParent().setVisible(false);
					setVisible(false);
				}
			}
		});
		add(btnCancel, "5, 8");
		
		//TOOL TIPS
		this.cmbCarrier.setToolTipText("This is the owner of the Plane");
		this.cmbStatus.setToolTipText("This is the current status of the Plane");
		this.lblName.setToolTipText("This is the name of the Plane");
		
		
	}
	public void showPanel()
	{
		source=null;
		clearGUI();
		setEditable();
		this.setVisible(true);
	}
	
	public void showPanel(Plane t)
	{
		
		source=t;
		readOnly();
		this.setVisible(true);
		btnSave.setVisible(false);
		btnEdit.setVisible(true);
	}
	public void addRefreshListener(TableRefreshListener t)
	{
		listener.add(t);
	}
	private void readOnly()
	{
		this.cmbCarrier.setToolTipText("This is the owner of the Plane");
		this.cmbStatus.setToolTipText("This is the current status of the Plane");
		this.lblName.setToolTipText("This is the name of the Plane");
		
		txtName.setEnabled(false);
		cmbCarrier.setEnabled(false);
		cmbStatus.setEnabled(false);
		
		txtName.setText(source.getVehicleName());
		cmbCarrier.setSelectedItem(source.getCarrier());
		cmbStatus.setSelectedItem(source.getStatus());
	}
	private void update()
	{
		if(source==null) source = new Plane();
		
		if(txtName.getText().length() < 46 &&  txtName.getText().length() > 1){
			source.setVehicleName(txtName.getText());
			source.setStatus((Vehicle.Status)cmbStatus.getSelectedItem());
			source.setCarrier((Carrier)cmbCarrier.getSelectedItem());
			source.Update();
		}
		else
			JOptionPane.showMessageDialog(null, "The name you entered was not an appropriate length. Please enter a name between 1 and 45 characters long.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
		for(TableRefreshListener t : listener)
			t.refreshTable();
	}
	private void setEditable()
	{
		this.cmbCarrier.setToolTipText("Select an onwer for this Plane");
		this.cmbStatus.setToolTipText("Select a status for this Plane");
		this.lblName.setToolTipText("Enter a name between 1 and 45 characters");
		txtName.setEnabled(true);
		cmbCarrier.setEnabled(true);
		cmbStatus.setEnabled(true);
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
	}
	private void clearGUI()
	{
		txtName.setText("");
		cmbCarrier.setSelectedIndex(0);
		cmbStatus.setSelectedIndex(0);
	}
	public void addTravelTypeSetListener(TravelTypeSetListener t)
	{
		this.travelTypeListener.add(t);
	}
}
