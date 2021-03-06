package  GUI.CargoForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.BaseClass;
import core.Cargo;
public class CargoTable extends JTable {
	//CargoShip
	ArrayList<Cargo> source;
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();
	public CargoTable(final GUI.Main_Source main)
	{
		super();
		this.setModel(new CargoModel(src));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            System.out.println("Double click detected");
		            CargoModel ttm = (CargoModel)getModel();
		            main.setCarrier(ttm.getCarrierID(getSelectedRow()));
		            main.getCarrierButton().doClick();
		        }
		    }
		});

	}
	public void showPanel()
	{
		try
		{
			src = BaseClass.executeQuery("Select t.ShipID as ID, c.CarrierName as Carrier, t.status as Status ,t.ShipName as Name,c.CarrierID as CID from CargoShip t left join carriers c on t.carrier=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		this.setModel(new CargoModel(src));
		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("Select t.ShipID as ID, c.CarrierName as Carrier, t.status as Status ,t.ShipName as Name,c.CarrierID as CID from CargoShip t left join carriers c on t.carrier=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		this.setModel(new CargoModel(src));
	}
	public Cargo getSelectedCargo()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		return Cargo.Load(searchID);
		
	}
	
}


class CargoModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public CargoModel(ArrayList<Map<String,Object>> source)
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
		return data.get(row).get("Status");
		case 3:
		return data.get(row).get("Name");
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
