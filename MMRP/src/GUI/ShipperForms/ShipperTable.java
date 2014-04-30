package GUI.ShipperForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShipperTable extends JTable {

	ArrayList<Shipper> source = new ArrayList<Shipper>();


	public ShipperTable()
	{
		super();
		this.setModel(new ShipperModel(new ArrayList<Shipper>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		source=Shipper.LoadAll("where ShipperID < 10");
		this.setModel(new ShipperModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Shipper.LoadAll("where ShipperID < 10");
		this.setModel(new ShipperModel(source));
	}
	public Shipper getSelectedShipper()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Shipper t : source)
		{
			if(t.getID()==searchID)
				return t;
		}
		return null;
	}
}


class ShipperModel extends AbstractTableModel
{
	ArrayList<Shipper> data;
	String[] columns = {"ID", "Company Name","Contact Name","Phone Number"};
	public ShipperModel(ArrayList<Shipper> source)
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
			return data.get(row).getCompanyName();
			case 2:
			return data.get(row).getContactName();
			case 3:
			return data.get(row).getPhoneNumber();
			
			default:
				return data.get(row).getID();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

