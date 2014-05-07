package GUI.SegmentForms;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TableRefreshListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.Segment;
import core.Shipment;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SegmentPanel extends JPanel {
	
	SegmentTable st;
	SegmentForm sf;
	JScrollPane sp;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	public SegmentPanel(final GUI.Main_Source main)
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
				ColumnSpec.decode("max(75dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		st=new SegmentTable();
		sf = new SegmentForm(main);
		sf.setVisible(false);
		sf.addTableRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				st.refresh();
			}
		});
		st.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(st);
		sp.setVisible(false);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click to view selected Segment");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(st.getSelectedRow()!=-1)
				{
					sf.showPanel(st.getSelectedSegment());
				}
			}
		});
		st.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(st.getSelectedRow()!=-1)
				{
					sf.showPanel(st.getSelectedSegment());
				}
			}
		});

		//add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click to create a new Segment");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click to delete the selected Segment");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(st.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Segment may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Segment", JOptionPane.YES_NO_OPTION) == 0)
				{
					st.getSelectedSegment().Delete();
					//Refresh not set up yet
					//st.refresh();
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
			main.setSegment(0);
		}else{
			//btnView.doClick();
			Segment shipment = Segment.Load(id);

			st.showPanel();
			//sht.showPanel(st.getSelectedShipment());
			sf.showPanel(shipment);
			sp.setVisible(true);
			setVisible(true);
			main.setSegment(0);
		}
	}

}