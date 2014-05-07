package GUI.ShipperForms;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.Shipper;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShipperPanel extends JPanel {
	
	ShipperTable st;
	ShipperForm sf;
	JScrollPane sp;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	public ShipperPanel()
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
				ColumnSpec.decode("max(63dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		st=new ShipperTable();
		sf = new ShipperForm();
		sf.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				st.refresh();
			}
		});
		sf.setVisible(false);
		st.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(st);
		sp.setVisible(false);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click to view selected Shipper");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(st.getSelectedRow()!=-1)
				{
					sf.showPanel(st.getSelectedShipper());
				}
			}
		});
		st.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(st.getSelectedRow()!=-1)
				{
					sf.showPanel(st.getSelectedShipper());
				}
			}
		});

		//add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click to create a new Shipper");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click to delete selected Shipper");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(st.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Shipper may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Shipper", JOptionPane.YES_NO_OPTION) == 0)
				{
					st.getSelectedShipper().Delete();
					st.refresh();
				}
			}
		});
		add(btnDelete, "9, 7");
		add(sf,"2, 8, 8, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		st.showPanel();
		sf.setVisible(false);
		setVisible(true);
		
	}
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			st.showPanel();
			sf.setVisible(false);
			setVisible(true);
			main.setShipper(0);
		}else{
			//btnView.doClick();
			Shipper shipment = Shipper.Load(id);
			st.showPanel();
			//sht.showPanel(st.getSelectedShipment());
			sf.showPanel(shipment);
			sp.setVisible(true);
			setVisible(true);
			main.setShipper(0);
		}
	}
}
