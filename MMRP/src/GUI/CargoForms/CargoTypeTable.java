package GUI.CargoForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.TravelType;
import core.Truck;

public class CargoTypeTable extends JTable {

	ArrayList<TravelType> source;
	public CargoTypeTable()
	{
		super();
		this.setModel(new TruckTypeModel(new ArrayList<TravelType>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
	}
	public void showPanel()
	{
		source=TravelType.LoadAll("where VehicleMode='Cargo'");
		this.setModel(new TruckTypeModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=TravelType.LoadAll("where VehicleMode='Cargo'");
		this.setModel(new TruckTypeModel(source));
	}
	public TravelType getSelectedTravelType()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(TravelType t : source)
		{
			if(t.getVehicleTypeID()==searchID)
				return t;
		}
		return null;
	}
}


class TruckTypeModel extends AbstractTableModel
{
	ArrayList<TravelType> data;
	String[] columns = {"ID","Name","Max Capacity","Max Weight"};
	public TruckTypeModel(ArrayList<TravelType> source)
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
			return data.get(row).getVehicleTypeID();
			case 1:
			return data.get(row).getTravelTypeName();
			case 2:
			return data.get(row).getMaxCap();
			case 3:
			return data.get(row).getMaxWeight();
			default:
				return data.get(row).getVehicleTypeID();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

}

