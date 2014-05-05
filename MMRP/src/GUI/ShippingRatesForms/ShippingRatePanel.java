package GUI.ShippingRatesForms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShippingRatePanel extends JPanel {
	
	ShippingRateTable srt;
	ShippingRateForm srf;
	JScrollPane sp;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	public ShippingRatePanel()
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
		
		srt=new ShippingRateTable();
		srf = new ShippingRateForm();
		srf.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				srt.refresh();
			}
		});
		srf.setVisible(false);
		srt.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(srt);
		sp.setVisible(false);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click to view the selected Shipping Rate");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(srt.getSelectedRow()!=-1)
				{
					srf.showPanel(srt.getSelectedShippingRate());
				}
			}
		});
		add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click to create a new Shipping Rate");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click to delete the selected Shipping Rate");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(srt.getSelectedRow()!=-1)
				{
					srt.getSelectedShippingRate().Delete();
					srt.refresh();
				}
			}
		});
		add(btnDelete, "9, 7");
		add(srf,"2, 8, 8, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		srt.showPanel();
		srf.setVisible(false);
		setVisible(true);
		
	}

}
