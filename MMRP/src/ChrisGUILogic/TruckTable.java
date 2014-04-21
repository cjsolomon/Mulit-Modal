package ChrisGUILogic;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import core.Truck;
public class TruckTable extends JTable {

	ArrayList<Truck> source;
	public TruckTable()
	{
		super();
		this.setModel(new TruckModel(new ArrayList<Truck>()));
	}
	public void showPanel()
	{
		source=Truck.LoadAll("where TruckID < 100");
		this.setModel(new TruckModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Truck.LoadAll("where TruckID < 100");
		this.setModel(new TruckModel(source));
	}
	public Truck getSelectedTruck()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Truck t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class TruckModel extends AbstractTableModel
{
	ArrayList<Truck> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public TruckModel(ArrayList<Truck> source)
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
			return data.get(row).getStatus();
			case 3:
			return data.get(row).getVehicleName();
			default:
				return data.get(row).getVehicleName();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

}
