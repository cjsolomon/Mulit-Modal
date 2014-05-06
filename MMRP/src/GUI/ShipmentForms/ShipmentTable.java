package GUI.ShipmentForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



import core.*;

public class ShipmentTable extends JTable {

	ArrayList<Shipment> source = new ArrayList<Shipment>();

	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();
	public ShipmentTable(final GUI.Main_Source main)
	{
		super();
		this.setModel(new ShipmentModel(new ArrayList<Map<String,Object>>()));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            System.out.println("Double click detected");
		            main.setShipment(Integer.parseInt(ShipmentTable.this.getValueAt(ShipmentTable.this.getSelectedRow(), 0).toString()));
		            main.getShipmentButton().doClick();
		        }
		    }
		});
		
	}
	public void showPanel()
	{
		try
		{
			src = BaseClass.executeQuery("SELECT ShipmentID, s.Name as StartLocation, e.Name as EndLocation from Shipment sh left join Location s on sh.FromLocationID=s.LocationID left join Location e on sh.ToLocationID = e.LocationID");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//source=Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		this.setModel(new ShipmentModel(src));
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("SELECT ShipmentID, s.Name as StartLocation, e.Name as EndLocation from Shipment sh left join Location s on sh.FromLocationID=s.LocationID left join Location e on sh.ToLocationID = e.LocationID");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setModel(new ShipmentModel(src));
	}
	public Shipment getSelectedShipment()
	{
		
		Shipment shipment = Shipment.Load(Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString()));
		return shipment;
		//int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		//for(Shipment t : source)
		//{
		//	if(t.getId()==searchID)
		//		return t;
		//}
		//return null;
	}
}


class ShipmentModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID","From","To"};
	public ShipmentModel(ArrayList<Map<String,Object>> source)
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
			return data.get(row).get("ShipmentID");
			case 1:
			return data.get(row).get("StartLocation");
			case 2:
			return data.get(row).get("EndLocation");
			case 3:
			
			default:
				return data.get(row).get("ShipmentID");
		}
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


}