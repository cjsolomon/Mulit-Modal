package  GUI.RailForms;

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

import core.Rail;
import core.Truck;

import javax.swing.JButton;
import GUI.*;
public class RailPanel extends JPanel {
	RailTable railTable;
	JScrollPane sp,sp2;
	RailBasicPanel railBasicInfo;
	private JButton btnDelete;
	private JButton btnView;
	private JTabbedPane railInfo;
	private JButton btnNew;
	private TravelTypeSelector tts;
	private GUI.SegmentTable segments;
	public RailPanel(final GUI.Main_Source main)
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
		
		
		railTable=new RailTable(main);
		railTable.setVisible(false);
		sp=new JScrollPane();
		sp.setViewportView(railTable);
		sp.setVisible(false);
		add(sp,"2, 2, 7, 2");
		railBasicInfo = new RailBasicPanel();
		railBasicInfo.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				railTable.refresh();
			}
		});
		railBasicInfo.addTravelTypeSetListner(new TravelTypeSetListener(){
			public void setTravelType()
			{
				tts.showPanel(railBasicInfo.source);
			}
		});
		railBasicInfo.setVisible(false);
		tts= new TravelTypeSelector(main);
		tts.setVisible(false);
		sp2 = new JScrollPane();
		segments = new SegmentTable(main);
		segments.setVisible(false);
		sp2.setViewportView(segments);
		railInfo = new JTabbedPane();
		railInfo.addTab("Basic", railBasicInfo);
		railInfo.addTab("Segments",sp2);
		railInfo.addTab("Types",tts);
		railInfo.setVisible(false);
		railInfo.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				if(railInfo.getSelectedIndex()==0)
				{
					railBasicInfo.setVisible(true);
					sp2.setVisible(false);
					tts.setVisible(false);
				}
				else
				{
					if(railInfo.getSelectedIndex()==1)
					{
						railBasicInfo.setVisible(false);
						sp2.setVisible(true);
						tts.setVisible(false);
					}
					else
					{
						railBasicInfo.setVisible(false);
						sp2.setVisible(false);
						tts.setVisible(true);
					}
				}
				
			}
		});

		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Rail");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				railInfo.setVisible(true);
				railBasicInfo.showPanel();
				segments.showPanel();
				sp2.setVisible(true);
				tts.showPanel();
				railInfo.setSelectedIndex(0);
			}
		});
		
		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view selected Rail");
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(railTable.getSelectedRow()!=-1)
				{
					railInfo.setVisible(true);
					railBasicInfo.showPanel(railTable.getSelectedRail());
					segments.showPanel(railTable.getSelectedRail());
					sp2.setVisible(true);
					tts.showPanel(railTable.getSelectedRail());
					railInfo.setSelectedIndex(0);
				}
			}
		});
		railTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(railTable.getSelectedRow()!=-1)
				{
					railInfo.setVisible(true);
					railBasicInfo.showPanel(railTable.getSelectedRail());
					segments.showPanel(railTable.getSelectedRail());
					sp2.setVisible(true);
					tts.showPanel(railTable.getSelectedRail());
					tts.setVisible(false);
					railInfo.setSelectedComponent(railBasicInfo);
				}
			}
		});

		//add(btnView, "5, 4");
		add(btnNew, "7, 4");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete selected Rail");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(railTable.getSelectedRow() != -1 && JOptionPane.showConfirmDialog(null,"Deleting this Rail may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Rail", JOptionPane.YES_NO_OPTION) == 0)
				{
					railTable.getSelectedRail().Delete();
					railTable.refresh();
				}
			}
		});
		add(btnDelete, "8, 4");
		add(railInfo,"2, 5, 7, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		railTable.showPanel();
		sp.setViewportView(railTable);
		this.setVisible(true);
	}
	
	private void hideControls()
	{
		railBasicInfo.setVisible(false);
	}
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			railTable.showPanel();
			sp.setViewportView(railTable);
			this.setVisible(true);
			main.setVehicleID(0);
		}else{
			//btnView.doClick();
			Rail t = Rail.Load(id);

			railTable.showPanel();
			railInfo.setVisible(true);
			railBasicInfo.showPanel(t);
			segments.showPanel(t);
			sp2.setVisible(true);
			tts.showPanel(t);
			tts.setVisible(false);
			railInfo.setSelectedComponent(railBasicInfo);
			main.setVehicleID(0);
			this.setVisible(true);
		}
	}

}
