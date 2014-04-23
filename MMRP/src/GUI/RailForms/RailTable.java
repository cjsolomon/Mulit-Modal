package  GUI.RailForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import core.Rail;
public class RailTable extends JTable {

	ArrayList<Rail> source;
	public RailTable()
	{
		super();
		this.setModel(new RailModel(new ArrayList<Rail>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
	}
	public void showPanel()
	{
		source=Rail.LoadAll("");
		this.setModel(new RailModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Rail.LoadAll("");
		this.setModel(new RailModel(source));
	}
	public Rail getSelectedTruck()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Rail t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class RailModel extends AbstractTableModel
{
	ArrayList<Rail> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public RailModel(ArrayList<Rail> source)
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
