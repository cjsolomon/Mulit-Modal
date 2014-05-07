package GUI.PlaneForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;
import GUI.CargoForms.CargoType;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.TravelType;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlaneTypePanel extends JPanel {
	PlaneTypeTable ptt;
	JScrollPane sp;
	PlaneType pt;
	private JButton btnNew,btnView,btnDelete;
	public PlaneTypePanel()
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
		ptt = new PlaneTypeTable();
		ptt.setVisible(false);
		sp.setViewportView(ptt);
		sp.setVisible(false);
		add(sp,"2, 2, 21, 12");
		ptt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(ptt.getSelectedRow()!=-1)
				{
					pt.showPanel(ptt.getSelectedTravelType());
				}
			}
		});
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Plane Type");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ptt.getSelectedRow()!=-1)
					pt.showPanel(ptt.getSelectedTravelType());
			}
		});
	//	add(btnView, "18, 14");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Plane Type");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pt.showPanel();
				
			}
		});
		add(btnNew, "20, 14");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete the selected Plane Type");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ptt.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Plane Type may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Plane Type", JOptionPane.YES_NO_OPTION) == 0)
				{
					ptt.getSelectedTravelType().Delete();
					ptt.refresh();
				}
			}
		});
		add(btnDelete, "22, 14");
		
		pt = new PlaneType();
		pt.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				ptt.refresh();
			}
		});
		pt.setVisible(false);
		add(pt,"2, 16, 23, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		ptt.showPanel();
		sp.setViewportView(ptt);
		this.setVisible(true);
	}
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			ptt.showPanel();
			pt.setVisible(false);
			setVisible(true);
			main.setTravelTypeID(0);
		}else{
			//btnView.doClick();
			TravelType t = TravelType.Load(id);
			ptt.showPanel();
			//sht.showPanel(st.getSelectedShipment());
			pt.showPanel(t);
			sp.setVisible(true);
			setVisible(true);
			main.setTravelTypeID(0);
		}
	}

}
