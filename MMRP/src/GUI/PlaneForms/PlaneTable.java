package  GUI.PlaneForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import core.Plane;
public class PlaneTable extends JTable {

	ArrayList<Plane> source;
	public PlaneTable()
	{
		super();
		this.setModel(new PlaneModel(new ArrayList<Plane>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
	}
	public void showPanel()
	{
		source=Plane.LoadAll("");
		this.setModel(new PlaneModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Plane.LoadAll("");
		this.setModel(new PlaneModel(source));
	}
	public Plane getSelectedPlane()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Plane t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class PlaneModel extends AbstractTableModel
{
	ArrayList<Plane> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public PlaneModel(ArrayList<Plane> source)
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
