package GUI;
import java.util.ArrayList;

import core.Segment;
public interface TableRefreshListener {
	public void refreshTable();
	public void setSegs(ArrayList<Segment> segs);
}
