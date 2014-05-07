package  GUI.PlaneForms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.BaseClass;
import core.Plane;
import core.Truck;
public class PlaneTable extends JTable {

	ArrayList<Plane> source;
	ArrayList<Map<String,Object>> src = new ArrayList<Map<String,Object>>();
	public PlaneTable(final GUI.Main_Source main)
	{
		super();
		this.setModel(new PlaneModel(src));
		this.getColumnModel().getColumn(0).setWidth(10);
		this.getColumnModel().getColumn(1).setWidth(10);
		this.getColumnModel().getColumn(2).setWidth(10);
		this.getColumnModel().getColumn(3).setWidth(10);
		this.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	System.out.println("Mouse click detected");
		        if(e.getClickCount()==2){
		            System.out.println("Double click detected");
		            PlaneModel ttm = (PlaneModel)getModel();
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
			src = BaseClass.executeQuery("Select t.PlaneID as ID, c.CarrierName as Carrier, t.status as Status ,t.PlaneName as Name,c.CarrierID as CID from plane t left join carriers c on t.carrier=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//source=Truck.LoadAll("where TruckID < 100");
		this.setModel(new PlaneModel(src));

		this.setVisible(true);
	}
	public void refresh()
	{
		try
		{
			src = BaseClass.executeQuery("Select t.PlaneID as ID, c.CarrierName as Carrier, t.status as Status ,t.PlaneName as Name,c.CarrierID as CID from plane t left join carriers c on t.carrier=c.CarrierID where t.Deleted=false");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//source=Truck.LoadAll("where TruckID < 100");
		this.setModel(new PlaneModel(src));
	}
	public Plane getSelectedPlane()
	{
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(), 0).toString());
		return Plane.Load(searchID);
	}
}


class PlaneModel extends AbstractTableModel
{
	ArrayList<Map<String,Object>> data;
	String[] columns = {"ID","Carrier","Status","Name"};
	public PlaneModel(ArrayList<Map<String,Object>> source)
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
