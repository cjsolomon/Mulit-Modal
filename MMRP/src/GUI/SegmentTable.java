package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import GUI.VehicleTable.VehicleModel;

import core.Cargo;
import core.Plane;
import core.Rail;
import core.Segment;
import core.Truck;
import core.Vehicle;

public class SegmentTable extends JTable {
	
	ArrayList<Segment> segments = new ArrayList<Segment>();
	
	public SegmentTable() {
		super();
		this.setModel(new SegmentModel(new ArrayList<Segment>()));
	}

	public Segment getSelectedSegment() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		int index = -1;
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).getID() == searchID){
				index = i;
				break;
			}
		}
		
		if(index < 0)
			return null;
		else
			return segments.get(index);
	}
	
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
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return segments.size();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int row, int col) {
			String column = getColumnName(col);
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
