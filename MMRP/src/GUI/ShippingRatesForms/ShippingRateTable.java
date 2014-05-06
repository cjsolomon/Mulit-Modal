package GUI.ShippingRatesForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShippingRateTable extends JTable {

	ArrayList<ShippingRate> source = new ArrayList<ShippingRate>();


	public ShippingRateTable(final GUI.Main_Source main)
	{
		super();
		this.setModel(new ShippingRateModel(new ArrayList<ShippingRate>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            System.out.println("Double click detected");
		            ArrayList<Carrier> carrier = Carrier.LoadAll("where CarrierName = '" +ShippingRateTable.this.getValueAt(ShippingRateTable.this.getSelectedRow(), 1)+ "'");
		            main.setCarrier(carrier.get(0).getId());
		            main.getCarrierButton().doClick();
		        }
		    }
		});
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
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 1).toString());
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

