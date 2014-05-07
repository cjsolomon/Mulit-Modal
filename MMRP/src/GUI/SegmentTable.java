package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import GUI.ShipmentForms.ShipmentTable;

import core.*;

public class SegmentTable extends JTable {
	
	
	ArrayList<Segment> segments = new ArrayList<Segment>();
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();
	Vehicle source;
	public SegmentTable(final GUI.Main_Source main) {
		super();
		this.setModel(new SegmentModel(src));
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            System.out.println("Double click detected");
		            main.setSegment(Integer.parseInt(getValueAt(getSelectedRow(), 0).toString()));
		            main.getSegmentButton().doClick();
		        }
		    }
		});
		// setData();
	}

	public Segment getSelectedSegment() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		return Segment.Load(searchID);
	}


	public void showPanel(Vehicle v) {
		System.out.println("Called vehicle table show");
		source=v;
		if(v!=null)
		{
			try
			{
				src = BaseClass.executeQuery("SELECT sh.SegmentID as ID, s.Name as StartLocation, e.Name as EndLocation, type.VehicleTypeName as Type from Segment sh left join Location s on sh.FromLocationID=s.LocationID left join Location e on sh.ToLocationID = e.LocationID left join TravelTypes type on sh.TravelTypeID = type.VehicleTypeID where sh.Deleted = false and sh.VehicleID ='"+ v.getId() +"' AND sh.ModeType ='"+ v.getTravelMode()+"'");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			this.setModel(new SegmentModel(src));
		}
		else
			this.setModel(new SegmentModel(new ArrayList<Map<String,Object>>()));
		
		setVisible(true);
	}
	
	public void showPanel()
	{
		this.setModel(new SegmentModel(new ArrayList<Map<String,Object>>()));
		setVisible(true);
	}
	//public void showPanel(String sqlWhere) {
		 //	setData(sqlWhere);
		 //	setVisible(true);
	//}

	//private void setData(String sqlWhere) {
		 
 		//segments= Segment.LoadAll(sqlWhere);
		//segments.addAll(Segment.LoadAll(sqlWhere));
 	//	this.setModel(new SegmentModel(segments));
 //	}
	class SegmentModel extends AbstractTableModel {
		public String[] columnNames = { "ID","Start", "End", "Type" };
		public ArrayList<Map<String,Object>> data;

		public SegmentModel(ArrayList<Map<String,Object>> temp) {
			data = temp;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int row, int col) {
			switch(col)
			{
		
			case 0:
			return data.get(row).get("ID");
			case 1:
			return data.get(row).get("StartLocation");
			case 2:
			return data.get(row).get("EndLocation");
			case 3:
			return data.get(row).get("Type");
			default:
				return data.get(row).get("ID");

			}

		}

		@Override
		public Class<?> getColumnClass(int c) {
			switch (c) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;

			}
			return null;
		}
	}
}