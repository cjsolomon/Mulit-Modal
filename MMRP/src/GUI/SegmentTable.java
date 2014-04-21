package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.*;
import core.Truck;
import core.Vehicle;

public class SegmentTable extends JTable {
	
	
	ArrayList<Segment> segments = new ArrayList<Segment>();
	Vehicle source;
	public SegmentTable() {
		super();
		this.setModel(new SegmentModel(new ArrayList<Segment>()));
		// setData();
	}

	public Segment getSelectedVehicle() {
		int searchID = Integer.parseInt(this.getValueAt(this.getSelectedRow(),
				0).toString());
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).getID() == searchID)
				return segments.get(i);
		}
		return null;
	}

	public void deleteSelectedVehicle() {
		Segment.Load((Integer)(this.getValueAt(this.getSelectedRow(),0))).Delete();
		setData();
	}

	private void setData() {

		segments=source.getSchedule();
		this.setModel(new SegmentModel(segments));
	}

	public void showPanel(Vehicle v) {
		System.out.println("Called vehicle table show");
		source=v;
		setData();
		setVisible(true);
	}
	
	public void showPanel(String sqlWhere) {
		 	setData(sqlWhere);
		 	setVisible(true);
	}

	private void setData(String sqlWhere) {
		 
 		segments= Segment.LoadAll(sqlWhere);
		//segments.addAll(Segment.LoadAll(sqlWhere));
 		this.setModel(new SegmentModel(segments));
 	}
	class SegmentModel extends AbstractTableModel {
		public String[] columnNames = { "ID","Start", "End", "Type" };
		public ArrayList<Segment> trucks;

		public SegmentModel(ArrayList<Segment> temp) {
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