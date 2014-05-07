package GUI.ShipperForms;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShipperTable extends JTable {

	ArrayList<Shipper> source = new ArrayList<Shipper>();
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();

	public ShipperTable()
	{
		super();
		this.setModel(new ShipperModel(src));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		//Shipper,CompanyName,ContactName,phone,ShipperID
		try
		{
			src = BaseClass.executeQuery("Select CompanyName,ContactName,phone,ShipperID from Shipper where Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		this.setModel(new ShipperModel(src));
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("Select CompanyName,ContactName,phone,ShipperID from Shipper where Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new ShipperModel(src));
	}
	public Shipper getSelectedShipper()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		return Shipper.Load(searchID);
	}
}


class ShipperModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID", "Company Name","Contact Name","Phone Number"};
	public ShipperModel(ArrayList<Map<String,Object>> source)
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
		return data.get(row).get("ShipperID");
		case 1:
		return data.get(row).get("CompanyName");
		case 2:
		return data.get(row).get("ContactName");
		case 3:
		return data.get(row).get("phone");
		default:
			return data.get(row).get("ShipperID");

		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

