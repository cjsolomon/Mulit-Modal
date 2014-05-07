package  GUI.PlaneForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.SegmentTable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import GUI.*;
public class PlanePanel extends JPanel {
	PlaneTable planeTable;
	JScrollPane sp,sp2;
	PlaneBasicPanel planeBasicInfo;
	private JButton btnDelete;
	private JButton btnView;
	private JTabbedPane planeInfo;
	private JButton btnNew;
	private TravelTypeSelector tts;
	private GUI.SegmentTable segments;
	public PlanePanel(final GUI.Main_Source main)
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
		
		
		planeTable=new PlaneTable(main);
		planeTable.setVisible(false);
		sp=new JScrollPane();
		sp.setViewportView(planeTable);
		sp.setVisible(false);
		add(sp,"2, 2, 7, 2");
		planeBasicInfo = new PlaneBasicPanel();
		planeBasicInfo.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				planeTable.refresh();
			}
		});
		planeBasicInfo.addTravelTypeSetListener(new TravelTypeSetListener(){
			public void setTravelType()
			{
				tts.showPanel(planeBasicInfo.source);
			}
		});
		planeBasicInfo.setVisible(false);
		tts= new TravelTypeSelector();
		tts.setVisible(false);
		sp2 = new JScrollPane();
		segments = new SegmentTable(main);
		segments.setVisible(false);
		sp2.setViewportView(segments);
		planeInfo = new JTabbedPane();
		planeInfo.addTab("Basic", planeBasicInfo);
		planeInfo.addTab("Segments",sp2);
		planeInfo.addTab("Types",tts);
		planeInfo.setVisible(false);
		planeInfo.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				if(planeInfo.getSelectedIndex()==0)
				{
					planeBasicInfo.setVisible(true);
					sp2.setVisible(false);
					tts.setVisible(false);
				}
				else
				{
					if(planeInfo.getSelectedIndex()==1)
					{
						planeBasicInfo.setVisible(false);
						sp2.setVisible(true);
						tts.setVisible(false);
					}
					else
					{
						planeBasicInfo.setVisible(false);
						sp2.setVisible(false);
						tts.setVisible(true);
					}
				}
				
			}
		});

		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Plane");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				planeInfo.setVisible(true);
				planeBasicInfo.showPanel();
				segments.showPanel();
				sp2.setVisible(true);
				tts.showPanel();
				planeInfo.setSelectedIndex(0);
			}
		});
		planeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(planeTable.getSelectedRow()!=-1)
				{
					planeInfo.setVisible(true);
					planeBasicInfo.showPanel(planeTable.getSelectedPlane());
					segments.showPanel(planeTable.getSelectedPlane());
					sp2.setVisible(true);
					tts.showPanel(planeTable.getSelectedPlane());
					tts.setVisible(false);
					planeInfo.setSelectedComponent(planeBasicInfo);
				}
			}
		});

		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Plane");
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(planeTable.getSelectedRow()!=-1)
				{
					planeInfo.setVisible(true);
					planeBasicInfo.showPanel(planeTable.getSelectedPlane());
					segments.showPanel(planeTable.getSelectedPlane());
					sp2.setVisible(true);
					tts.showPanel(planeTable.getSelectedPlane());
					planeInfo.setSelectedIndex(0);
				}
			}
		});
		//add(btnView, "5, 4");
		add(btnNew, "7, 4");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete selected Plane");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(planeTable.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Plane may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Plane", JOptionPane.YES_NO_OPTION) == 0)
				{
					planeTable.getSelectedPlane().Delete();
					planeTable.refresh();
				}
			}
		});
		add(btnDelete, "8, 4");
		add(planeInfo,"2, 5, 7, 1");
		
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		planeTable.showPanel();
		sp.setViewportView(planeTable);
		this.setVisible(true);
	}
	
	private void hideControls()
	{
		planeBasicInfo.setVisible(false);
	}
	

}
