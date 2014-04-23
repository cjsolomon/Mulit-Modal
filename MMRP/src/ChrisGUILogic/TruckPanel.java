package ChrisGUILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.SegmentTable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;

public class TruckPanel extends JPanel {
	TruckTable tt;
	JScrollPane sp,sp2;
	TruckBasicPanel tbp;
	private JButton btnDelete;
	private JButton btnView;
	private JTabbedPane truckInfo;
	private JButton btnNew;
	private TravelTypeSelector tts;
	private GUI.SegmentTable segments;
	public TruckPanel()
	{
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),
				ColumnSpec.decode("max(133dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(12dlu;default)"),
				ColumnSpec.decode("max(12dlu;default)"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		
		tt=new TruckTable();
		tt.setVisible(false);
		sp=new JScrollPane();
		sp.setViewportView(tt);
		sp.setVisible(false);
		add(sp,"2, 2, 7, 2");
		tbp = new TruckBasicPanel();
		tbp.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				tt.refresh();
			}
		});
		tbp.setVisible(false);
		tts= new TravelTypeSelector();
		tts.setVisible(false);
		sp2 = new JScrollPane();
		segments = new SegmentTable();
		segments.setVisible(false);
		sp2.setViewportView(segments);
		truckInfo = new JTabbedPane();
		truckInfo.addTab("Basic", tbp);
		truckInfo.addTab("Segments",sp2);
		truckInfo.addTab("Types",tts);
		truckInfo.setVisible(false);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				truckInfo.setVisible(true);
				tbp.showPanel();
			}
		});
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(tt.getSelectedRow()!=-1)
				{
					truckInfo.setVisible(true);
					tbp.showPanel(tt.getSelectedTruck());
					segments.showPanel(tt.getSelectedTruck());
					sp2.setVisible(true);
					tts.showPanel(tt.getSelectedTruck());
				}
			}
		});
		add(btnView, "5, 4");
		add(btnNew, "7, 4");
		
		btnDelete = new JButton("Delete");
		add(btnDelete, "8, 4");
		add(truckInfo,"2, 5, 7, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		tt.showPanel();
		sp.setViewportView(tt);
		this.setVisible(true);
	}
	
	private void hideControls()
	{
		tbp.setVisible(false);
	}
	

}
