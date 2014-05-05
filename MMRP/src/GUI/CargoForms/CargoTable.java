package  GUI.CargoForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import core.Cargo;
public class CargoTable extends JTable {

	ArrayList<Cargo> source;
	public CargoTable()
	{
		super();
		this.setModel(new CargoModel(new ArrayList<Cargo>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
	}
	public void showPanel()
	{
		source=Cargo.LoadAll("");
		this.setModel(new CargoModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Cargo.LoadAll("");
		this.setModel(new CargoModel(source));
	}
	public Cargo getSelectedCargo()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Cargo t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class CargoModel extends AbstractTableModel
{
	ArrayList<Cargo> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public CargoModel(ArrayList<Cargo> source)
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
