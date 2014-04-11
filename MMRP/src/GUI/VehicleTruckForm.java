package GUI;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Carrier;
import core.Truck;
import core.Vehicle;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class VehicleTruckForm extends JPanel {
	JLabel contractorLabel,nameLabel,statusLabel;
	JComboBox<String> contractorDropDown;
	JTextField nameText;
	VehicleSegmentTable vst;
	//private Vehicle t;
	private ArrayList<Carrier> carriers = Carrier.LoadAll(";");
	private Truck t;
	private JTabbedPane tabbedPane;
	private JPanel basic;
	private JComboBox<String> status;
	private JTextField nameTextReadOnly;
	private JTextField contractorTextReadOnly;
	private JTextField statusTextReadOnly;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnCancel;
	public VehicleTruckForm()
	{
		String[] contractor = new String[carriers.size()];
		for(int i = 0; i < carriers.size(); i++){
			contractor[i] = carriers.get(i).getCarrierName();
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("69px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),
				ColumnSpec.decode("48px"),
				ColumnSpec.decode("69px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		basic = new JPanel();
		
		add(tabbedPane, "1, 2, 11, 19, fill, fill");
		basic.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("103px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		//add(contractorDropDown, "4, 20, left, top");
		nameLabel = new JLabel("Name");
		basic.add(nameLabel, "2, 2, right, center");
		
		nameTextReadOnly = new JTextField();
		nameTextReadOnly.setEditable(false);
		basic.add(nameTextReadOnly, "4, 2, fill, default");
		nameTextReadOnly.setColumns(10);
		//nameText.setSize(69, Integer.parseInt(FormFactory.LINE_GAP_ROWSPEC.toString()));
		//add(nameLabel,"2, 22, center, center");
		nameText = new JTextField(10);
		nameText.setText(setName());
		basic.add(nameText, "6, 2, left, top");
		contractorLabel = new JLabel();
		contractorLabel.setText("Contractor");
		basic.add(contractorLabel, "2, 4, right, center");
		tabbedPane.addTab("Basic", basic);
		contractorDropDown = new JComboBox();
		for(int i = 0; i < carriers.size(); i++){
			contractorDropDown.addItem(carriers.get(i).getCarrierName());
		}
			contractorDropDown.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String name = setName();
					if(nameText!=null)
						nameText.setText(name);
				}
			});
			
			contractorTextReadOnly = new JTextField();
			contractorTextReadOnly.setEditable(false);
			basic.add(contractorTextReadOnly, "4, 4, fill, default");
			contractorTextReadOnly.setColumns(10);
			basic.add(contractorDropDown, "6, 4, left, top");
			this.statusLabel=new JLabel("Status");
			//	add(nameText,"4, 22, left, top");
				basic.add(statusLabel, "2, 6, right, center");
			
			statusTextReadOnly = new JTextField();
			statusTextReadOnly.setEditable(false);
			basic.add(statusTextReadOnly, "4, 6, fill, default");
			statusTextReadOnly.setColumns(10);
			this.status=new JComboBox(Vehicle.Status.values());
			basic.add(status, "6, 6, left, top");
			
			btnEdit = new JButton("Edit");
			basic.add(btnEdit, "4, 8");
			btnEdit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					setEditable();
				}
			});
			btnSave = new JButton("Save");
			basic.add(btnSave, "6, 8");
			
			btnCancel = new JButton("Cancel");
			basic.add(btnCancel, "8, 8");
			this.nameText.setVisible(false);
			this.contractorDropDown.setVisible(false);
			this.status.setVisible(false);
		this.setTypeForm();
		this.btnCancel.setVisible(false);
		this.btnSave.setVisible(false);
	}
	private void loadVehicle(int id)
	{
		clearGUI();
		if(id>0)
		{
			t=Truck.Load(id);
			setTruck();
		}			
		vst.ShowTable(t);
	}
	
	public void showPanel()
	{
		clearGUI();
		loadNew();
		this.setVisible(true);
	}
	public void showPanel(int id)
	{
		loadVehicle(id);
		this.setVisible(true);
	}
	public void showPanel(Truck temp)
	{
		t=temp;
		setReadOnly();
		vst.ShowTable(t);
		this.setVisible(true);
	}
	public void hidePanel()
	{
		clearGUI();
		this.setVisible(false);
	}
	private void clearGUI()
	{
		this.contractorDropDown.setSelectedIndex(0);
		this.nameText.setText(setName());
	}
	private void loadNew()
	{

	}
	private void setTruck()
	{
		this.nameTextReadOnly.setText(t.getVehicleName());
		this.statusTextReadOnly.setText(t.getStatus());
		this.contractorTextReadOnly.setText(t.getCarrier().getCarrierName());
		//this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(t.getContractor()));
		//this.nameText.setText(t.getTruckName());
	}
	private void setReadOnly()
	{
		setTruck();
		
		this.btnCancel.setVisible(false);
		this.btnSave.setVisible(false);
		this.btnEdit.setVisible(true);
		this.statusTextReadOnly.setVisible(true);
		this.nameTextReadOnly.setVisible(true);
		this.contractorTextReadOnly.setVisible(true);
		this.contractorDropDown.setVisible(false);
		this.nameText.setVisible(false);
		this.status.setVisible(false);
	}
	private void setEditable()
	{
		this.status.setSelectedItem(Vehicle.loadStatus(t.getStatus()));
		this.contractorDropDown.setSelectedItem(t.getCarrier().getCarrierName());
		this.nameText.setText(t.getVehicleName());
		
		this.btnCancel.setVisible(true);
		this.btnSave.setVisible(true);
		this.btnEdit.setVisible(false);
		this.statusTextReadOnly.setVisible(false);
		this.nameTextReadOnly.setVisible(false);
		this.contractorTextReadOnly.setVisible(false);
		this.contractorDropDown.setVisible(true);
		this.nameText.setVisible(true);
		this.status.setVisible(true);
	}
	private String setName(){
		int randomInt;
		boolean goodNumber = false;
		String name = "NEWTRUCK";
		ArrayList<Truck> trucks = new ArrayList<Truck>();
		int trycounter=0;
		if(contractorDropDown!=null)
		{
		while(!goodNumber)
		{
			randomInt = (int)Math.floor(Math.random()*10000);
			//name = this.contractorDropDown.getSelectedItem().toString() + "Truck" + randomInt;
			name = "Truck";
			trucks = Truck.LoadAll("Where TruckName ='" +name + "'");
			trycounter++;
			if(!(trucks.size() > 0)){
				goodNumber = true;
			}
			else
			{
				if(trycounter==300)
				{
					name="NEWPLANE";
					goodNumber=true;
				}
			}
		}
		}
		return name;
	}
	private void setTypeForm()
	{
		final JPanel segments = new JPanel();
		segments.setLayout(null);
		vst = new VehicleSegmentTable();
		JScrollPane s = new JScrollPane();
		s.setBounds(0, 0, 572, 427);
		s.setViewportView(vst);
		segments.add(s);
		tabbedPane.addTab("Segments",segments);
		
		
	}
}
