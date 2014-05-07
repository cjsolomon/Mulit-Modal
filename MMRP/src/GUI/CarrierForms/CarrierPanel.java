package GUI.CarrierForms;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.Carrier;
import core.Shipment;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarrierPanel extends JPanel {
	
	CarrierTable ct;
	CarrierForm cf;
	JScrollPane sp;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	public CarrierPanel(final GUI.Main_Source main)
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
		
		ct=new CarrierTable();
		cf = new CarrierForm();
		cf.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				ct.refresh();
			}
		});
		cf.setVisible(false);
		ct.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(ct);
		sp.setVisible(false);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Carrier");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ct.getSelectedRow()!=-1)
				{
					main.setCarrier(0);
					cf.showPanel(ct.getSelectedCarrier());
				}
			}
		});
		add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Carrier");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete the selected Carrier");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(ct.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Carrier may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Carrier", JOptionPane.YES_NO_OPTION) == 0)
				{
					ct.getSelectedCarrier().Delete();
					ct.refresh();
				}
			}
		});
		add(btnDelete, "9, 7");
		add(cf,"2, 8, 8, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		ct.showPanel();
		cf.setVisible(false);
		setVisible(true);
		
	}
	
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			ct.showPanel();
			cf.setVisible(false);
			setVisible(true);
			main.setCarrier(0);
		}else{
			//btnView.doClick();
			Carrier carrier = Carrier.Load(id);
			ct.showPanel();
			//sht.showPanel(st.getSelectedShipment());
			cf.showPanel(carrier);
			sp.setVisible(true);
			setVisible(true);
			main.setCarrier(0);
		}
	}
	
	

}
