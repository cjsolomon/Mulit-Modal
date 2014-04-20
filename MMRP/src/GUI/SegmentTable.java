package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

<<<<<<< HEAD
import GUI.VehicleTable.VehicleModel;

import core.Cargo;
import core.Plane;
import core.Rail;
import core.Segment;
import core.Truck;
import core.Vehicle;

public class SegmentTable extends JTable{

	ArrayList<Segment> segments = new ArrayList<Segment>();

=======
import core.*;
import core.Truck;
import core.Vehicle;

public class SegmentTable extends JTable {
	
	
	ArrayList<Segment> vehicles = new ArrayList<Segment>();
	Vehicle source;
>>>>>>> 38cd440587f276ad4cdafd815f1619885643696c
	public SegmentTable() {
		super();
		this.setModel(new SegmentModel(new ArrayList<Segment>()));
		// setData();
	}

<<<<<<< HEAD
	public Segment getSelectedSegment() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).getID() == searchID)
				return segments.get(i);
=======
	public Segment getSelectedVehicle() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getID() == searchID)
				return vehicles.get(i);
>>>>>>> 38cd440587f276ad4cdafd815f1619885643696c
		}
		return null;
	}

<<<<<<< HEAD
	private void setData(String sqlWhere) {

		segments.clear();
		segments.addAll(Segment.LoadAll(sqlWhere));
		this.setModel(new SegmentModel(segments));
	}

	public void showPanel(String sqlWhere) {
		setData(sqlWhere);
		setVisible(true);
	}
	
	
	class SegmentModel extends AbstractTableModel {
		public String[] columnNames = { "ID","Start Location", "End Location", "Travel Mode", "VehicleID", "Departure", "Arrival"};
		public ArrayList<Segment> segments;

		public SegmentModel(ArrayList<Segment> temp) {
			segments = temp;
=======
	public void deleteSelectedVehicle() {
		Segment.Load((Integer)(this.getValueAt(this.getSelectedRow(),0))).Delete();
		setData();
	}

	private void setData() {

		
		this.setModel(new SegmentModel(source.getSchedule()));
	}

	public void showPanel(Vehicle v) {
		System.out.println("Called vehicle table show");
		source=v;
		setData();
		setVisible(true);
	}

	class SegmentModel extends AbstractTableModel {
		public String[] columnNames = { "ID","Start", "End", "Type" };
		public ArrayList<Segment> trucks;

		public SegmentModel(ArrayList<Segment> temp) {
			trucks = temp;
>>>>>>> 38cd440587f276ad4cdafd815f1619885643696c
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
<<<<<<< HEAD
			return segments.size();
=======
			return trucks.size();
>>>>>>> 38cd440587f276ad4cdafd815f1619885643696c
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int row, int col) {
			String column = getColumnName(col);
<<<<<<< HEAD
			Segment s = segments.get(row);
			if (column.equals("ID"))
				return s.getID();
			if (column.equals("Start Location"))
				return s.getStartLocation().getName();
			if (column.equals("End Location"))
				return s.getEndLocation().getName();
			if(column.equals("Travel Mode"))
				return s.getMode();
			if(column.equals("VehicleID"))
				return s.getVehicle().getId();
			if(column.equals("Departure"))
				return s.getEstimatedDepartureTime();
			if(column.equals("Arrival"))
				return s.getEstimatedArrivalTime();
			return null;
		}
	}
}
=======
			Segment t = trucks.get(row);
			if (column.equals("ID"))
				return t.getID();
			if (column.equals("Start"))
				return t.getStartLocation().getName();
			if (column.equals("End"))
				return t.getEndLocation().getName();
			if(column.equals("Type"))
				return "EMPTY";
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
>>>>>>> 38cd440587f276ad4cdafd815f1619885643696c
