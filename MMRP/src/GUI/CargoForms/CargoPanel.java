package  GUI.CargoForms;

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
import GUI.RailForms.RailTable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;

public class CargoPanel extends JPanel {
	CargoTable cargoTable;
	JScrollPane sp,sp2;
	CargoBasicPanel cargoBasicInfo;
	private JButton btnDelete;
	private JButton btnView;
	private JTabbedPane cargoInfo;
	private JButton btnNew;
	private TravelTypeSelector tts;
	private GUI.SegmentTable segments;
	public CargoPanel(final GUI.Main_Source main)
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
		
		
		cargoTable=new CargoTable(main);
		cargoTable.setVisible(false);
		sp=new JScrollPane();
		sp.setViewportView(cargoTable);
		sp.setVisible(false);
		add(sp,"2, 2, 7, 2");
		cargoBasicInfo = new CargoBasicPanel();
		cargoBasicInfo.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				cargoTable.refresh();
			}
		});
		cargoBasicInfo.addTravelTypeSetListener(new TravelTypeSetListener(){
			public void setTravelType()
			{
				tts.showPanel(cargoBasicInfo.source);
			}
		});
		cargoBasicInfo.setVisible(false);
		tts= new TravelTypeSelector(main);
		tts.setVisible(false);
		sp2 = new JScrollPane();
		segments = new SegmentTable(main);
		segments.setVisible(false);
		sp2.setViewportView(segments);
		cargoInfo = new JTabbedPane();
		cargoInfo.addTab("Basic", cargoBasicInfo);
		cargoInfo.addTab("Segments",sp2);
		cargoInfo.addTab("Types",tts);
		cargoInfo.setVisible(false);
		cargoInfo.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				if(cargoInfo.getSelectedIndex()==0)
				{
					cargoBasicInfo.setVisible(true);
					sp2.setVisible(false);
					tts.setVisible(false);
				}
				else
				{
					if(cargoInfo.getSelectedIndex()==1)
					{
						cargoBasicInfo.setVisible(false);
						sp2.setVisible(true);
						tts.setVisible(false);
					}
					else
					{
						cargoBasicInfo.setVisible(false);
						sp2.setVisible(false);
						tts.setVisible(true);
					}
				}
				
			}
		});

		
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("Click here to create a new Cargo Ship");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cargoInfo.setVisible(true);
				cargoBasicInfo.showPanel();
				segments.showPanel();
				sp2.setVisible(true);
				tts.showPanel();
				cargoInfo.setSelectedIndex(0);
			}
		});
		cargoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(cargoTable.getSelectedRow()!=-1)
				{
					cargoInfo.setVisible(true);
					cargoBasicInfo.showPanel(cargoTable.getSelectedCargo());
					segments.showPanel(cargoTable.getSelectedCargo());
					sp2.setVisible(true);
					tts.showPanel(cargoTable.getSelectedCargo());
					tts.setVisible(false);
					cargoInfo.setSelectedComponent(cargoBasicInfo);
				}
			}
		});


		btnView = new JButton("View");
		btnView.setToolTipText("Click here to view the selected Cargo Ship");
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(cargoTable.getSelectedRow()!=-1)
				{
					cargoInfo.setVisible(true);
					cargoBasicInfo.showPanel(cargoTable.getSelectedCargo());
					segments.showPanel(cargoTable.getSelectedCargo());
					sp2.setVisible(true);
					tts.showPanel(cargoTable.getSelectedCargo());
					cargoInfo.setSelectedIndex(0);
				}
			}
		});
		//add(btnView, "5, 4");
		add(btnNew, "7, 4");
		
		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Click here to delete selected Cargo Ship");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				if(cargoTable.getSelectedRow()!=-1 && JOptionPane.showConfirmDialog(null,"Deleting this Cargo Ship may makes its future recovery from the database impossible. Are you sure you want to delete it?", "Deleting Cargo Ship", JOptionPane.YES_NO_OPTION) == 0)
				{
					cargoTable.getSelectedCargo().Delete();
				}
			}
		});
		add(btnDelete, "8, 4");
		add(cargoInfo,"2, 5, 7, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		cargoTable.showPanel();
		sp.setViewportView(cargoTable);
		this.setVisible(true);
	}
	
	private void hideControls()
	{
		cargoBasicInfo.setVisible(false);
	}
	

}
