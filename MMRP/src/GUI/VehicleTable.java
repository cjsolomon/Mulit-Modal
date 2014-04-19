package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.*;
import core.Truck;
import core.Vehicle;

public class VehicleTable extends JTable {

	String type;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	public VehicleTable(String type) {
		super();
		this.type = type;
		this.setModel(new VehicleModel(new ArrayList<Vehicle>()));
		// setData();
	}

	public Vehicle getSelectedVehicle() {
		if(this.getSelectedRow()!=-1)
		{
			int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
					0).toString());
			for (int i = 0; i < vehicles.size(); i++) {
				if (vehicles.get(i).getId() == searchID)
					return vehicles.get(i);
			}
		}
		return null;
	}

	public void deleteSelectedVehicle() {
		if (type.equals("Truck")) {
			if (this.getSelectedRow() != -1) {
				Truck.Load(
						Integer.parseInt((this.getValueAt(
								this.getSelectedRow(), 0).toString())))
						.Delete();
			}
		} else {
			if (type.equals("Rail")) {
				if (this.getSelectedRow() != -1) {
					Rail.Load(
							Integer.parseInt((this.getValueAt(
									this.getSelectedRow(), 0).toString())))
							.Delete();
				}
			} else {
				if (type.equals("Plane")) {
					if (this.getSelectedRow() != -1) {
						Plane.Load(
								Integer.parseInt((this.getValueAt(
										this.getSelectedRow(), 0).toString())))
								.Delete();
					}
				} else {
					if (type.equals("Cargo")) {
						if (this.getSelectedRow() != -1) {
							Cargo.Load(
									Integer.parseInt((this.getValueAt(
											this.getSelectedRow(), 0)
											.toString()))).Delete();
						}
					}
				}
			}
		}
		setData();
	}

	private void setData() {

		if (type.equals("Truck")) {
			vehicles.clear();
			vehicles.addAll(Truck.LoadAll("where TruckID < 100"));
		} else {
			if (type.equals("Rail")) {
				vehicles.clear();
				vehicles.addAll(Rail.LoadAll(""));
			} else {
				if (type.equals("Plane")) {
					vehicles.clear();
					vehicles.addAll(Plane.LoadAll(""));
				} else {
					if (type.equals("Cargo")) {
						vehicles.clear();
						vehicles.addAll(Cargo.LoadAll(""));
					} else {
						vehicles.clear();
						vehicles.addAll(Truck.LoadAll(""));
					}
				}
			}
		}
		this.setModel(new VehicleModel(vehicles));
	}

	public void showPanel() {
		System.out.println("Called vehicle table show");
		setData();
		setVisible(true);
	}

	class VehicleModel extends AbstractTableModel {
		public String[] columnNames = { "ID","Name", "Carrier", "Status" };
		public ArrayList<Vehicle> trucks;

		public VehicleModel(ArrayList<Vehicle> temp) {
			trucks = temp;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return trucks.size();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int row, int col) {
			String column = getColumnName(col);
			Vehicle t = trucks.get(row);
			if (column.equals("ID"))
				return t.getId();
			if (column.equals("Carrier"))
				return t.getCarrier().getCarrierCode();
			if (column.equals("Status"))
				return t.getStatus().toString();
			if(column.equals("Name"))
				return t.getVehicleName();
			return null;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			switch (c) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;

			}
			return null;
		}
	}
}
