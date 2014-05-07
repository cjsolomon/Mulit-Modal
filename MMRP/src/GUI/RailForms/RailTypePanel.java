package GUI.RailForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;
import GUI.PlaneForms.PlaneType;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;

public class RailTypePanel extends JPanel {
	RailTypeTable rtt;
	JScrollPane sp;
	RailType rt;
	JButton btnView,btnNew,btnDelete;
	public RailTypePanel()
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
		rtt = new RailTypeTable();
		rtt.setVisible(false);
		sp.setViewportView(rtt);
		sp.setVisible(false);
		add(sp,"2, 2, 21, 12");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Rail Type");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rtt.getSelectedRow()!=-1)
					rt.showPanel(rtt.getSelectedTravelType());
			}
		});
		add(btnView, "18, 14");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Rail Type");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rt.showPanel();
				
			}
		});
		add(btnNew, "20, 14");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete the selected Rail Type");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rtt.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Rail Type may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Rail Type", JOptionPane.YES_NO_OPTION) == 0)
				{
					rtt.getSelectedTravelType().Delete();
					rtt.refresh();
				}
			}
		});
		add(btnDelete, "22, 14");
		
		rt = new RailType();
		rt.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				rtt.refresh();
			}
		});
		rt.setVisible(false);
		add(rt,"2, 16, 23, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		rtt.showPanel();
		sp.setViewportView(rtt);
		this.setVisible(true);
	}

}
