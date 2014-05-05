package GUI.ShippingRatesForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShippingRateTable extends JTable {

	ArrayList<ShippingRate> source = new ArrayList<ShippingRate>();


	public ShippingRateTable()
	{
		super();
		this.setModel(new ShippingRateModel(new ArrayList<ShippingRate>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		source=ShippingRate.LoadAll("where ShippingRateID > 10");
		this.setModel(new ShippingRateModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=ShippingRate.LoadAll("where ShippingRateID > 10");
		this.setModel(new ShippingRateModel(source));
	}
	public ShippingRate getSelectedShippingRate()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(ShippingRate t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class ShippingRateModel extends AbstractTableModel
{
	ArrayList<ShippingRate> data;
	String[] columns = {"ID", "Carrier","Mile Rate", "Flat Rate"};
	public ShippingRateModel(ArrayList<ShippingRate> source)
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
			return data.get(row).getId();
			case 1:
			return data.get(row).getCarrier().getCarrierName();
			case 2:
			return data.get(row).getMileRate();
			case 3:
			return data.get(row).getFlatRate();
			
			default:
				return data.get(row).getId();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

