package GUI.CarrierForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class CarrierTable extends JTable {

	ArrayList<Carrier> source = new ArrayList<Carrier>();


	public CarrierTable()
	{
		super();
		this.setModel(new CarrierModel(new ArrayList<Carrier>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
	}
	public void showPanel()
	{
		source=Carrier.LoadAll("where CarrierID < 10");
		this.setModel(new CarrierModel(source));
		this.setVisible(true);
	}
	public void refresh()
	{
		source=Carrier.LoadAll("");
		this.setModel(new CarrierModel(source));
	}
	public Carrier getSelectedCarrier()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		for(Carrier t : source)
		{
			if(t.getId()==searchID)
				return t;
		}
		return null;
	}
}


class CarrierModel extends AbstractTableModel
{
	ArrayList<Carrier> data;
	String[] columns = {"ID", "Name","Code","Safety Rating"};
	public CarrierModel(ArrayList<Carrier> source)
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
			return data.get(row).getCarrierName();
			case 2:
			return data.get(row).getCarrierCode();
			case 3:
			return data.get(row).getSafetyRating();
			
			default:
				return data.get(row).getId();
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}

