package GUI.LocationForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


import core.Carrier;
import core.Location;

public class LocationTable extends JTable {
	ArrayList<Location> source = new ArrayList<Location>();


	public LocationTable()
	{
		super();
		this.setModel(new LocationModel(new ArrayList<Location>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		source=Location.LoadAll("");
		this.setModel(new LocationModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Location.LoadAll("");
		this.setModel(new LocationModel(source));
	}
	public Location getSelectedLocation()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Location t : source)
		{
			if(t.getID()==searchID)
				return t;
		}
		return null;
	}
}


class LocationModel extends AbstractTableModel
{
	ArrayList<Location> data;
	String[] columns = {"ID", "City","State","Country"};
	public LocationModel(ArrayList<Location> source)
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
			return data.get(row).getName();
			case 2:
			return data.get(row).getState();
			case 3:
			return data.get(row).getCountry();
			
			default:
				return data.get(row).getID();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}



}
