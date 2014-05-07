package GUI.TruckForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;
import GUI.RailForms.RailType;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;

public class TruckTypePanel extends JPanel {
	TruckTypeTable ttt;
	JScrollPane sp;
	TruckType tt;
	JButton btnNew,btnView,btnDelete;
	public TruckTypePanel()
	{
		sp = new JScrollPane();
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
				ColumnSpec.decode("max(74dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(48dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(48dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(48dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
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
				RowSpec.decode("max(229dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		ttt = new TruckTypeTable();
		ttt.setVisible(false);
		sp.setViewportView(ttt);
		sp.setVisible(false);
		add(sp,"2, 2, 21, 12");
		btnView = new JButton("View");
		btnView.setToolTipText("Click to view selected Truck Type");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ttt.getSelectedRow()!=-1)
					tt.showPanel(ttt.getSelectedTravelType());
			}
		});
		add(btnView, "18, 14");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click to create a new Truck Type");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tt.showPanel();
				
			}
		});
		add(btnNew, "20, 14");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click to delete the selected Truck Type");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ttt.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Truck Type may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Truck Type", JOptionPane.YES_NO_OPTION) == 0)
				{
					ttt.getSelectedTravelType().Delete();
					ttt.refresh();
				}
			}
		});
		add(btnDelete, "22, 14");
		
		tt = new TruckType();
		tt.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				ttt.refresh();
			}
		});
		tt.setVisible(false);
		add(tt,"2, 16, 23, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		ttt.showPanel();
		sp.setViewportView(ttt);
		this.setVisible(true);
	}

}
