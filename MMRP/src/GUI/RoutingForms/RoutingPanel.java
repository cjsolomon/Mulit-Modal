package GUI.RoutingForms;

import javax.swing.*;

import GUI.TableRefreshListener;
import GUI.ShipmentForms.ShipmentForm;
import GUI.ShipmentForms.ShipmentHistoryTable;
import GUI.ShipmentForms.ShipmentTable;
import Routing.NodeCrawler;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import core.Segment;
import core.Shipment;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class RoutingPanel extends JPanel 
{
	
	ShipmentTable st;
	RoutingForm sf;
	JScrollPane sp,sp2;
	RouteTable rt;
	private JButton btnView;
	private JLabel lblShipmentsTo;
	private JLabel lblSelectTheAlgorithms;
	private JLabel lblBestRouteFound;
	public RoutingPanel(final GUI.Main_Source main)
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default):grow"),
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
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(16dlu;default)"),}));
		
		st=new ShipmentTable(main);
		st.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(st);
		sp.setVisible(false);
		rt = new RouteTable(main);
		rt.setVisible(false);

		st.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void  valueChanged(ListSelectionEvent e)
			{
				if(st.getSelectedRow()!=-1)
					sf.setShipment(st.getSelectedShipment());
				else
					sf.setShipment(null);
			}
		});
		
		lblShipmentsTo = new JLabel("Select a shipment to route");
		add(lblShipmentsTo, "2, 2");

		add(sp, "2, 4, 8, 5");
		


		
		
		
		sf = new RoutingForm();
		sf.addTableRefreshListener(new SegmentSelectionListener()
		{
			private ArrayList<Segment> segs;
			public void refreshTable()
			{
				sp2.setVisible(true);
				
			//	NodeCrawler nc = new NodeCrawler();
				
				
				lblBestRouteFound.setVisible(true);
				rt.showPanel(segs);
			}
			public void setSegs(ArrayList<Segment> temp)
			{
				segs=temp;
			}
		});
		
		
		//sf.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		sf.setVisible(false);
		
		lblSelectTheAlgorithms = new JLabel("Select the algorithms to use");
		add(lblSelectTheAlgorithms, "2, 9");
		add(sf,"2, 10, 8, 1, default, top");
		sp2 = new JScrollPane();
		sp2.setViewportView(rt);
		sp2.setVisible(false);
		
		lblBestRouteFound = new JLabel("Best route found");
		add(lblBestRouteFound, "2, 12");
		add(sp2,"2, 14, 8, 1");
		lblBestRouteFound.setVisible(false);
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		st.showPanel();
		sf.showPanel();
		setVisible(true);
		rt.setVisible(false);
		sp2.setVisible(false);
	}

}
