package GUI.ShipmentForms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class ShipmentPanel extends JPanel {
	
	ShipmentTable st;
	ShipmentForm sf;
	JScrollPane sp,sp2;
	JTabbedPane jp;
	ShipmentHistoryTable sht;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	
	public ShipmentPanel()
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
		
		st=new ShipmentTable();
		sf = new ShipmentForm();
		jp = new JTabbedPane();
		sht = new ShipmentHistoryTable();
		sht.setVisible(false);
		jp.setVisible(false);
		
		//sf.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		sf.setVisible(false);
		st.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(st);
		sp.setVisible(false);
		sp2 = new JScrollPane();
		sp2.setViewportView(sht);
		sp2.setVisible(false);
		jp.addTab("Info", sf);
		jp.addTab("History",sp2);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(st.getSelectedRow()!=-1)
				{
					jp.setVisible(true);
					sp2.setVisible(true);
					sht.showPanel(st.getSelectedShipment());
					sf.showPanel(st.getSelectedShipment());
					jp.setSelectedIndex(0);
				}
			}
		});
		add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jp.setVisible(true);
				sp2.setVisible(true);
				sht.showPanel(null);
				sf.showPanel();
				jp.setSelectedIndex(0);
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(st.getSelectedRow()!=-1)
					st.getSelectedShipment().Delete();
			}
		});
		add(btnDelete, "9, 7");
		add(jp,"2, 8, 8, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		st.showPanel();
		sf.setVisible(false);
		setVisible(true);
	}

}
