package GUI.LocationForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import GUI.TableRefreshListener;
import GUI.ShipmentForms.ShipmentForm;
import GUI.ShipmentForms.ShipmentHistoryTable;
import GUI.ShipmentForms.ShipmentTable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LocationPanel extends JPanel {
	LocationTable lt;
	LocationForm lf;
	JScrollPane sp;
	JButton btnView,btnNew,btnDelete;
	public LocationPanel()
	{
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),
				ColumnSpec.decode("max(117dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(71dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lt=new LocationTable();
		lf = new LocationForm();
		
		lt.setVisible(false);
		lf.setVisible(false);
		lf.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				lt.refresh();
			}
		});
		sp=new JScrollPane();
		sp.setViewportView(lt);
		sp.setVisible(false);

		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Location");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lt.getSelectedRow()!=-1)
				{
					lf.showPanel(lt.getSelectedLocation());
				}
			}
		});
		add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Location");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete selected Location");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(lt.getSelectedRow()!=-1)
				{
					lt.getSelectedLocation().Delete();
					lt.refresh();
				}
			}
		});
		add(btnDelete, "9, 7");
		add(lf,"2, 8, 8, 1");
		
		
	}
	
	public void showPanel()
	{
		sp.setVisible(true);
		lt.showPanel();
		lf.setVisible(false);
		setVisible(true);
	}

}
