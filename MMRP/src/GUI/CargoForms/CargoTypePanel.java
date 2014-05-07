package GUI.CargoForms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import GUI.TableRefreshListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.Carrier;
import core.TravelType;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargoTypePanel extends JPanel {
	CargoTypeTable ctt;
	JScrollPane sp;
	CargoType ct;
	private JButton btnNew;
	private JButton btnView;
	private JButton btnDelete;
	public CargoTypePanel()
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
		ctt = new CargoTypeTable();
		ctt.setVisible(false);
		sp.setViewportView(ctt);
		sp.setVisible(false);
		add(sp,"2, 2, 21, 12");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view the selected Cargo Type");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctt.getSelectedRow()!=-1)
					ct.showPanel(ctt.getSelectedTravelType());
			}
		});
		ctt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(ctt.getSelectedRow()!=-1)
				{
					ct.showPanel(ctt.getSelectedTravelType());
				}
			}
		});

		//add(btnView, "18, 14");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Cargo Type");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.showPanel();
				
			}
		});
		add(btnNew, "20, 14");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete the selected Cargo Type");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctt.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Cargo Type may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Cargo Type", JOptionPane.YES_NO_OPTION) == 0)
				{
					ctt.getSelectedTravelType().Delete();
					ctt.refresh();
				}
			}
		});
		add(btnDelete, "22, 14");
		ct = new CargoType();
		ct.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				ctt.refresh();
			}
		});
		ct.setVisible(false);
		add(ct,"2, 16, 23, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		ctt.showPanel();
		sp.setViewportView(ctt);
		this.setVisible(true);
	}
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			ctt.showPanel();
			ct.setVisible(false);
			setVisible(true);
			main.setCarrier(0);
		}else{
			//btnView.doClick();
			TravelType t = TravelType.Load(id);
			ctt.showPanel();
			//sht.showPanel(st.getSelectedShipment());
			ct.showPanel(t);
			sp.setVisible(true);
			setVisible(true);
			main.setCarrier(0);
		}
	}

}
