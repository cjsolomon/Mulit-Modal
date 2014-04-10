package GUI;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Carrier;
import core.Plane;
import core.Rail;
import core.Vehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleRailForm extends JPanel {
	JLabel contractorLabel,nameLabel,statusLabel;
	JComboBox<String> contractorDropDown;
	JTextField nameText;
	VehicleSegmentTable vst;
	//private Vehicle t;
	private Rail r;
	private ArrayList<Carrier> carriers = Carrier.LoadAll("");
	private JTabbedPane tabbedPane;
	private JPanel basic;
	private JComboBox<String> status;
	public VehicleRailForm()
	{
		String[] contractor = new String[carriers.size()];
		for(int i = 0; i < carriers.size(); i++){
			contractor[i] = carriers.get(i).getCarrierName();
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("48px"),
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
		
		add(tabbedPane, "2, 2, 7, 17, fill, fill");
		basic.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("103px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("58px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("10px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("37px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),},
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
		basic.add(nameLabel, "2, 2, left, center");
		//nameText.setSize(69, Integer.parseInt(FormFactory.LINE_GAP_ROWSPEC.toString()));
		//add(nameLabel,"2, 22, center, center");
		contractorLabel = new JLabel();
		contractorLabel.setText("Contractor");
		basic.add(contractorLabel, "2, 4, left, center");
		tabbedPane.addTab("Basic", basic);
		//add(contractorLabel, "2, 20, center, center");
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
		basic.add(contractorDropDown, "4, 4, left, top");
			this.statusLabel=new JLabel("Status");
			//	add(nameText,"4, 22, left, top");
				basic.add(statusLabel, "2, 6, left, center");
			this.status=new JComboBox(Vehicle.Status.values());
			basic.add(status, "4, 6, left, top");
		nameText = new JTextField(20);
		nameText.setText(setName());
		basic.add(nameText, "4, 2, left, top");
		this.setTypeForm();
	}
	private void loadVehicle(int id)
	{
		clearGUI();
		if(id>0)
		{
			r=Rail.Load(id);
			vst.ShowTable(r);
			setRail();
		}			
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
	private void setRail()
	{
		this.contractorDropDown.setSelectedItem(r.getCarrier().getCarrierName());
		this.nameText.setText(r.getVehicleName());
	}
	private String setName(){
		int randomInt;
		boolean goodNumber = false;
		String name = "";
		ArrayList<Rail> rails = new ArrayList<Rail>();
		int trycounter=0;
		while(!goodNumber){
			randomInt = (int)Math.floor(Math.random()*10000);
			//name = this.contractorDropDown.getSelectedItem().toString() + "Rail" + randomInt;
			name = "Rail";
			rails = Rail.LoadAll("Where RailName ='" +name + "'");
			trycounter++;
			if(!(rails.size() > 0)){
				goodNumber = true;
			}
			else
			{
				if(trycounter==300)
				{
					name="NEWRAIL";
					goodNumber=true;
				}
			}
			
		}
		return name;
	}
	
	private void setTypeForm()
	{
		final JPanel segments = new JPanel();
		vst = new VehicleSegmentTable();
		segments.add(new JScrollPane(vst));
		tabbedPane.addTab("Segments",segments);
		
		
	}
}
