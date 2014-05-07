package GUI.SegmentForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import GUI.ShipmentForms.ShipmentTable;

//import GUI.PlaneForms.PlaneModel;

import core.*;

public class SegmentTable extends JTable {
	
	
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();
	public SegmentTable()
	{
		super();
		this.setModel(new SegmentModel(new ArrayList<Map<String,Object>>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		
		
	}
	public void showPanel()
	{
		try
		{
			src = BaseClass.executeQuery("SELECT sh.SegmentID as ID, s.Name as StartLocation, e.Name as EndLocation, type.VehicleTypeName as Type from Segment sh left join Location s on sh.FromLocationID=s.LocationID left join Location e on sh.ToLocationID = e.LocationID left join TravelTypes type on sh.TravelTypeID = type.VehicleTypeID where sh.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new SegmentModel(src));
		//source=Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
	
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("SELECT sh.SegmentID as ID, s.Name as StartLocation, e.Name as EndLocation, type.VehicleTypeName as Type from Segment sh left join Location s on sh.FromLocationID=s.LocationID left join Location e on sh.ToLocationID = e.LocationID left join TravelTypes type on sh.TravelTypeID = type.VehicleTypeID where sh.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new SegmentModel(src));
	}
	public Segment getSelectedSegment() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		return Segment.Load(searchID);
	}
}
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