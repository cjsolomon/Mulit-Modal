package ChrisGUILogic;

import java.util.ArrayList;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.TravelType;
import core.Vehicle;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

public class TravelTypeSelector extends JPanel {
	
	ArrayList<TravelType> used;
	ArrayList<TravelType> available;
	Vehicle sourceVehicle;
	
	JLabel lblUsed,lblAvailable;
	JList lstInUse, lstAvail;
	DefaultListModel dlmInUse,dlmAvail;
	
	public TravelTypeSelector()
	{
		dlmInUse = new DefaultListModel();
		dlmAvail = new DefaultListModel();
		used = new ArrayList<TravelType>();
		available = new ArrayList<TravelType>();
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
		
		 lblUsed = new JLabel("Used");
		add(lblUsed, "8, 4");
		
		lblAvailable = new JLabel("Available");
		add(lblAvailable, "22, 4");
		
		lstInUse = new JList(dlmInUse);
		add(lstInUse, "4, 6, 9, 11, fill, fill");
		
		lstAvail = new JList(dlmAvail);
		add(lstAvail, "18, 6, 9, 11, fill, fill");
		
		JButton btnToAvailable = new JButton("->");
		add(btnToAvailable, "14, 10, 3, 1");
		
		JButton btnToUsed = new JButton("<-");
		add(btnToUsed, "14, 14, 3, 1");
		
	}
	
	public void showPanel(Vehicle v)
	{
		sourceVehicle = v;
		
		used = v.getAvailableTypes();
		
		dlmInUse.clear();
		for(TravelType t : used)
		{
			dlmInUse.addElement(t);
		}
		
		available = TravelType.LoadNotInVehilce(v); 
		dlmAvail.clear();
		for(TravelType t : available)
		{
			dlmAvail.addElement(t);
		}
		
		setVisible(true);
	}

}
