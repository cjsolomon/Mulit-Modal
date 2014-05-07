package GUI.CarrierForms;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class CarrierTable extends JTable {

	ArrayList<Carrier> source = new ArrayList<Carrier>();
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();

	public CarrierTable()
	{
		super();
		this.setModel(new CarrierModel(src));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		//CarrierID,CarrierCode,CarrierName,SafetyRating
		try
		{
			src = BaseClass.executeQuery("Select CarrierID,CarrierCode,CarrierName,SafetyRating from Carriers where Deleted = false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//source=Carrier.LoadAll("where CarrierID > 10");
		this.setModel(new CarrierModel(src));
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("Select CarrierID,CarrierCode,CarrierName,SafetyRating from Carriers where Deleted = false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new CarrierModel(src));
	}
	public Carrier getSelectedCarrier()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		return Carrier.Load(searchID);
	}
}


class CarrierModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID", "Name","Code","Safety Rating"};
	public CarrierModel(ArrayList<Map<String,Object>> source)
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
			return data.get(row).get("CarrierID");
			case 1:
			return data.get(row).get("CarrierName");
			case 2:
			return data.get(row).get("CarrierCode");
			case 3:
			return data.get(row).get("CarrierID");
			
			default:
				return data.get(row).get("SafetyRating");
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

