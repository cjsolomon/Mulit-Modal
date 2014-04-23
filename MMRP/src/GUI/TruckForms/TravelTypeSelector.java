package  GUI.TruckForms;

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
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TravelTypeSelector extends JPanel {
	
	ArrayList<TravelType> used;
	ArrayList<TravelType> available;
	Vehicle sourceVehicle;
	
	JLabel lblUsed,lblAvailable;
	JList lstInUse, lstAvail;
	DefaultListModel dlmInUse,dlmAvail;
	JScrollPane sp1,sp2;

	public TravelTypeSelector()
	{
		dlmInUse = new DefaultListModel();
	
		dlmAvail = new DefaultListModel();

		used = new ArrayList<TravelType>();
		available = new ArrayList<TravelType>();
		sp1=new JScrollPane();
		sp2=new JScrollPane();
		
		
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
				ColumnSpec.decode("max(18dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(19dlu;default)"),
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
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default):grow"),
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
		sp1.setViewportView(lstInUse);
		add(sp1, "4, 6, 9, 11, fill, fill");
		
		lstAvail = new JList(dlmAvail);
		sp2.setViewportView(lstAvail);
		add(sp2, "18, 6, 9, 11, fill, fill");
		
		JButton btnToAvailable = new JButton("->");
		btnToAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lstInUse.getSelectedIndex()!=-1)
				{
					TravelType t = (TravelType)lstInUse.getSelectedValue();
					t.removeFromVehilce(sourceVehicle);
					dlmAvail.addElement(t);
					dlmInUse.removeElement(t);
				}
			}
		});
		add(btnToAvailable, "14, 10, 3, 1");
		
		JButton btnToUsed = new JButton("<-");
		btnToUsed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lstAvail.getSelectedIndex()!=-1)
				{
					TravelType t = (TravelType)lstAvail.getSelectedValue();
					t.addToVehilce(sourceVehicle);
					dlmAvail.removeElement(t);
					dlmInUse.addElement(t);
				}
			}
		});
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

		lstAvail.updateUI();
		sp2.updateUI();
		setVisible(true);
	}

}
