package GUI.RoutingForms;

import java.util.ArrayList;

import core.Segment;

public interface SegmentSelectionListener {
	public void refreshTable();
	public void setSegs(ArrayList<Segment> segs);
}
