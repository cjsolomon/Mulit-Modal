package GUI.RoutingForms;

import javax.swing.JTable;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class RouteTable extends JTable {
	
	ArrayList<Segment> source = new ArrayList<Segment>();


	public RouteTable()
	{
		super();
		this.setModel(new RouteTableModel(new ArrayList<Segment>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel(ArrayList<Segment> segs)
	{
		source=segs;
		this.setModel(new RouteTableModel(source));
		this.setVisible(true);
	}

}


class RouteTableModel extends AbstractTableModel
{
	ArrayList<Segment> data;
	String[] columns = {"ID", "To","From","Time","Cost", "Distance"};
	public RouteTableModel(ArrayList<Segment> source)
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
			return data.get(row).getID();
			case 1:
			return data.get(row).getStartLocation().getName();
			case 2:
			return data.get(row).getEndLocation().getName();
			case 3:
			return Math.abs(data.get(row).getEstimatedArrivalTime() -data.get(row).getEstimatedDepartureTime());
			case 4:
				return data.get(row).getShippingRate().getFlatRate();
			case 5:
				return data.get(row).getDistance();
			default:
				return data.get(row).getID();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

