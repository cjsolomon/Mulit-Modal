package  GUI.TruckForms;

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

import GUI.*;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Segment;
import core.Truck;

import javax.swing.JButton;
import GUI.TravelTypeSetListener;

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
	public TruckPanel(final GUI.Main_Source main)
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
		
		
		tt=new TruckTable(main);
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
		tts= new TravelTypeSelector(main);
		tts.setVisible(false);
		sp2 = new JScrollPane();
		segments = new SegmentTable(main);
		segments.setVisible(false);
		sp2.setViewportView(segments);
		truckInfo = new JTabbedPane();
		truckInfo.addTab("Basic", tbp);
		truckInfo.addTab("Segments",sp2);
		truckInfo.addTab("Types",tts);
		truckInfo.setVisible(false);
		tbp.addTravelTypeSetListener(new TravelTypeSetListener(){
			public void setTravelType()
			{
				tts.showPanel(tbp.source);
			}
		});
		truckInfo.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				if(truckInfo.getSelectedIndex()==0)
				{
					tbp.setVisible(true);
					sp2.setVisible(false);
					tts.setVisible(false);
				}
				else
				{
					if(truckInfo.getSelectedIndex()==1)
					{
						tbp.setVisible(false);
						sp2.setVisible(true);
						tts.setVisible(false);
					}
					else
					{
						tbp.setVisible(false);
						sp2.setVisible(false);
						tts.setVisible(true);
					}
				}
				
			}
		});
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click to create a new Truck");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				truckInfo.setVisible(true);
				tbp.showPanel();
				segments.showPanel();
				sp2.setVisible(true);
				tts.showPanel();
				truckInfo.setSelectedIndex(0);
				tt.refresh();
			}
		});
		tt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(tt.getSelectedRow()!=-1)
				{
					truckInfo.setVisible(true);
					tbp.showPanel(tt.getSelectedTruck());
					segments.showPanel(tt.getSelectedTruck());
					sp2.setVisible(true);
					tts.showPanel(tt.getSelectedTruck());
					tts.setVisible(false);
					truckInfo.setSelectedComponent(tbp);
				}
			}
		});
		btnView = new JButton("View");
		btnView.setToolTipText("Click to view the selected Truck");
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
					truckInfo.setSelectedIndex(0);
					
				}
			}
		});
		//add(btnView, "5, 4");
		add(btnNew, "7, 4");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click to delete the selected Truck");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(tt.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Truck may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Truck", JOptionPane.YES_NO_OPTION) == 0)
				{
					tt.getSelectedTruck().Delete();
					tt.refresh();
				}
			}
		});
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
	
	public void refreshTable()
	{
		tt.refresh();
		
	}
	public void showPanel(int id, final GUI.Main_Source main)
	{
		
		if(id == 0){
			sp.setVisible(true);
			tt.showPanel();
			sp.setViewportView(tt);
			this.setVisible(true);
			main.setVehicleID(0);
		}else{
			//btnView.doClick();
			Truck t = Truck.Load(id);

			tt.showPanel();
			truckInfo.setVisible(true);
			tbp.showPanel(t);
			segments.showPanel(t);
			sp2.setVisible(true);
			tts.showPanel(t);
			tts.setVisible(false);
			truckInfo.setSelectedComponent(tbp);
			main.setVehicleID(0);
			this.setVisible(true);
		}
	}
}
