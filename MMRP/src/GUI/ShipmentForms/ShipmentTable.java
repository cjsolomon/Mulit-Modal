package GUI.ShipmentForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShipmentTable extends JTable {

	ArrayList<Shipment> source = new ArrayList<Shipment>();


	public ShipmentTable()
	{
		super();
		this.setModel(new ShipmentModel(new ArrayList<Shipment>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		source=Shipment.LoadAll("");
		this.setModel(new ShipmentModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Shipment.LoadAll("");
		this.setModel(new ShipmentModel(source));
	}
	public Shipment getSelectedShipment()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Shipment t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class ShipmentModel extends AbstractTableModel
{
	ArrayList<Shipment> data;
	String[] columns = {"ID","From","To"};
	public ShipmentModel(ArrayList<Shipment> source)
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
			return Location.Load(data.get(row).getFromLocationID()).getName();
			case 2:
			return Location.Load(data.get(row).getToLocationID()).getName();
			case 3:
			
			default:
				return data.get(row).getId();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}