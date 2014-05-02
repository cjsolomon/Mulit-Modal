package GUI.ShipmentForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


import core.Carrier;
import core.Shipment;
import core.ShipmentHistory;

public class ShipmentHistoryTable extends JTable {
		Shipment source;
		ArrayList<ShipmentHistory> data;

	public ShipmentHistoryTable()
	{
		super();
		this.setModel(new ShipmentHistoryModel(new ArrayList<ShipmentHistory>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel(Shipment s)
	{
		source= s;
		
		if(s!=null)
		{
			data= source.getHistory();
			this.setModel(new ShipmentHistoryModel(data));
		}
		else
			this.setModel(new ShipmentHistoryModel(new ArrayList<ShipmentHistory>()));
		this.setVisible(true);
	}
	public void refresh()
	{
		data=source.getHistory();
		this.setModel(new ShipmentHistoryModel(data));
	}
	public ShipmentHistory getSelectedHistory()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(ShipmentHistory t : data)
		{
			if(t.getShipmentHistoryID()==searchID)
				return t;
		}
		return null;
	}
}


class ShipmentHistoryModel extends AbstractTableModel
{
	ArrayList<ShipmentHistory> data;
	String[] columns = {"ID", "Name","Code","Safety Rating"};
	public ShipmentHistoryModel(ArrayList<ShipmentHistory> source)
	{
		data=source;
	}
	public int getColumnCount()
	{
		return columns.length;
	}
	public int getRowCount()
	{
		return data.size();
	}
	@Override
	public Object getValueAt(int row,int col)
	{
		
		
		switch(col)
		{
			case 0:
			return data.get(row).getShipmentHistoryID();
			case 1:
			return data.get(row).getNodeNumber();
			case 2:
			return data.get(row).getSegment().getStartLocation().getName();
			case 3:
			return data.get(row).getSegment().getEndLocation().getName();
			
			default:
				return data.get(row).getShipmentHistoryID();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}
