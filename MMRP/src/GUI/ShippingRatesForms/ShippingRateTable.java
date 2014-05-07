package GUI.ShippingRatesForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShippingRateTable extends JTable {

	ArrayList<ShippingRate> source = new ArrayList<ShippingRate>();
	ArrayList<Map<String,Object>> src =new ArrayList<Map<String,Object>>();

	public ShippingRateTable(final GUI.Main_Source main)
	{
		super();
		this.setModel(new ShippingRateModel(src));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            ShippingRateModel srm = (ShippingRateModel)getModel();
		            main.setCarrier(srm.getCarrierID(getSelectedRow()));
		            main.getCarrierButton().doClick();
		        }
		    }
		});
	}
	public void showPanel()
	{
		//shippingrates
		//ShippingRateID
		//CarrierID
		//MileRate
		//FlatRate
		try
		{
			src = BaseClass.executeQuery("Select t.ShippingRateID as ID, c.CarrierName as Carrier, t.MileRate as MileRate ,t.FlatRate as FlatRate,c.CarrierID as CID from shippingrates t left join carriers c on t.CarrierID=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
		this.setModel(new ShippingRateModel(src));
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("Select t.ShippingRateID as ID, c.CarrierName as Carrier, t.MileRate as MileRate ,t.FlatRate as FlatRate,c.CarrierID as CID from shippingrates t left join carriers c on t.CarrierID=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new ShippingRateModel(src));
	}
	public ShippingRate getSelectedShippingRate()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		return ShippingRate.Load(searchID);
	}
}


class ShippingRateModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID", "Carrier","Mile Rate", "Flat Rate"};
	public ShippingRateModel(ArrayList<Map<String,Object>> source)
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
		return data.get(row).get("ID");
		case 1:
		return data.get(row).get("Carrier");
		case 2:
		return data.get(row).get("MileRate");
		case 3:
		return data.get(row).get("FlatRate");
		default:
			return data.get(row).get("ID");

		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public int getCarrierID(int row)
	{
		return Integer.parseInt(data.get(row).get("CID").toString());
	}

}

