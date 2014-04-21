package ChrisGUILogic;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class TruckPanel extends JPanel {
	TruckTable tt;
	JScrollPane sp;
	TruckBasicPanel tbp;
	public TruckPanel()
	{
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(198dlu;default)"),
				ColumnSpec.decode("max(19dlu;default)"),
				ColumnSpec.decode("max(71dlu;default)"),
				ColumnSpec.decode("max(48dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		
		tt=new TruckTable();
		tt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				hideControls();
				if(tt.getSelectedRow()!=-1)
				tbp.showPanel(tt.getSelectedTruck());
			}
		});
		
		tt.setVisible(false);
		sp=new JScrollPane();
		sp.setViewportView(tt);
		sp.setVisible(false);
		add(sp,"2, 2, 3, 2");
		tbp = new TruckBasicPanel();
		tbp.addRefreshListener(new TableRefreshListener(){
			public void refreshTable()
			{
				tt.refresh();
			}
		});
		tbp.setVisible(false);
		add(tbp,"2,4");
	}
	public void showPanel()
	{
		sp.setVisible(true);
		tt.showPanel();
		sp.setViewportView(tt);
		this.setVisible(true);
	}
	
	private void hideControls()
	{
		tbp.setVisible(false);
	}
	

}
