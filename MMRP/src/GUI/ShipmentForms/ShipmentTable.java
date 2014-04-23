package GUI.ShipmentForms;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.*;
import core.Truck;
import core.Vehicle;

public class ShipmentTable extends JTable {

	ArrayList<Shipment> shipments = new ArrayList<Shipment>();

	public ShipmentTable() {
		this.showPanel();
	}

	public void showPanel() {
		System.out.println("Called vehicle table show");
		setVisible(true);
	}

}