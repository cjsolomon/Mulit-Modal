package GUI.SegmentForms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SegmentPanel extends JPanel {
	
	SegmentTable st;
	SegmentForm sf;
	JScrollPane sp;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnView;
	public SegmentPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),
				ColumnSpec.decode("max(117dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(63dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("max(84dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		st=new SegmentTable();
		sf = new SegmentForm();
		sf.setVisible(false);
		st.setVisible(false);
		sp = new JScrollPane();
		sp.setViewportView(st);
		sp.setVisible(false);
		add(sp, "2, 2, 8, 5");
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(st.getSelectedRow()!=-1)
				{
					sf.showPanel(st.getSelectedSegment());
				}
			}
		});
		add(btnView, "7, 7");
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.showPanel();
			}
		});
		add(btnNew, "8, 7");
		
		btnDelete = new JButton("Delete");
		add(btnDelete, "9, 7");
		add(sf,"2, 8, 8, 1");
		
	}
	public void showPanel()
	{
		sp.setVisible(true);
		st.showPanel("where SegmentID > 8000");
		sf.setVisible(false);
		setVisible(true);
		
	}

}