package GUI.CarrierForms;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GUI.TableRefreshListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Carrier;
import core.Location;
import core.TravelType;
import core.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CarrierForm extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel lblID,lblCarrierCode, lblCarrierName,lblFax,lblEmail, lblContract,lblAuthorize;

	JTextField txtEmail,txtContractDate,txtFaxNumber,txtCarrierCode,txtCarrierName, txtID;

	private boolean edit = false;
	private JTextField txtAuthorize;
	private JLabel lblSatefyRating;
	private JLabel lblSafetyRatingDate;
	private JTextField txtSafetyRating;
	private JTextField txtRatingDate;
	private JLabel lblInsEndDate;
	private JTextField txtInsEndDate;
	private JLabel lblCostModifiers;
	private JLabel lblTruck;
	private JLabel lblCargo;
	private JLabel lblPlane;
	private JLabel lblRail;
	private JTextField txtTruckMod;
	private JTextField txtCargoMod;
	private JTextField txtPlaneMod;
	private JTextField txtRailMod;

	private Carrier source;
	private JButton btnSave,btnEdit,btnCancel;
	private ArrayList<TableRefreshListener> refresh;
	public CarrierForm()
	{


		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		btnSave = new JButton("Save");
		btnSave.setVisible(false);
		btnEdit = new JButton("Edit");
		btnCancel = new JButton("Cancel");

		lblEmail=new JLabel("Email");
		lblContract = new JLabel("Contract Date");
		lblFax = new JLabel("Fax Number");
		lblCarrierCode = new JLabel("Carrier Code");
		lblCarrierName = new JLabel("Carrier Name");

		lblID = new JLabel("CarrierID");

		txtEmail=new JTextField(20);
		txtEmail.setEditable(false);
		txtContractDate = new JTextField(20);
		txtContractDate.setEditable(false);
		txtCarrierCode = new JTextField(20);
		txtCarrierName = new JTextField(20);
		txtID = new JTextField(10);

		add(txtID, "4,4,left,center");
		add(lblID, "2, 4, right, center");
		add(lblFax,"2,10,right,center");
		add(lblCarrierCode,"2,8,right,center");
		add(txtCarrierCode, "4, 8, left, center");
		add(lblCarrierName,"2,6,right,center");
		add(txtCarrierName, "4, 6, left, center");
		txtFaxNumber= new JTextField(20);

		add(txtFaxNumber, "4, 10, left, center");
		add(lblEmail,"2,12,right,center");
		add(txtEmail, "4, 12, left, center");
		add(lblContract,"2,14,right,center");
		add(txtContractDate, "4, 14, left, center");

		lblAuthorize = new JLabel("Authorize");
		add(lblAuthorize, "2, 16, right, top");

		txtAuthorize = new JTextField(10);
		add(txtAuthorize, "4, 16, fill, default");


		lblSatefyRating = new JLabel("Safety Rating");
		add(lblSatefyRating, "2, 18, right, default");

		txtSafetyRating = new JTextField(10);
		add(txtSafetyRating, "4, 18, fill, default");


		lblSafetyRatingDate = new JLabel("Rating Date");
		add(lblSafetyRatingDate, "2, 20, right, default");

		txtRatingDate = new JTextField(10);
		add(txtRatingDate, "4, 20, fill, default");


		lblInsEndDate = new JLabel("Ins End Date");
		add(lblInsEndDate, "2, 22, right, default");

		txtInsEndDate = new JTextField(10);
		add(txtInsEndDate, "4, 22, fill, default");

		txtID.setEditable(false);


		lblCostModifiers = new JLabel("Cost Modifiers");
		add(lblCostModifiers, "2, 26, left, default");

		lblTruck = new JLabel("Truck");
		add(lblTruck, "2, 28, right, default");

		txtTruckMod = new JTextField(10);
		add(txtTruckMod, "4, 28, fill, top");

		lblCargo = new JLabel("Cargo");
		add(lblCargo, "2, 30, right, default");

		txtCargoMod = new JTextField(10);
		add(txtCargoMod, "4, 30, fill, default");


		lblPlane = new JLabel("Plane");
		add(lblPlane, "2, 32, right, default");

		txtPlaneMod = new JTextField(10);
		add(txtPlaneMod, "4, 32, fill, top");

		lblRail = new JLabel("Rail");
		add(lblRail, "2, 34, right, default");

		txtRailMod = new JTextField(10);
		add(txtRailMod, "4, 34, fill, default");

		add(btnEdit ,"2,36");
		add(btnSave, "2,36");
		add(btnCancel,"4,36,left,default");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEdit.isVisible()||source==null)
				{
					setVisible(false);
				}
				else
				{

					btnEdit.setVisible(true);
					btnSave.setVisible(false);
					setReadOnly();
				}

			}});
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				btnEdit.setVisible(false);
				btnSave.setVisible(true);
				setEditable();
			}
		});
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});


	}//End of LocationCreateEdit Constructor


	public void showPanel()
	{
		source = null;
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
		loadNew();
		setEditable();
		setVisible(true);
	}
	public void showPanel(Carrier c)
	{
		if(c!=null)
			source = c;
		loadCarrier();
		setReadOnly();
		btnSave.setVisible(false);
		btnEdit.setVisible(true);
		setVisible(true);
	}
	private void loadNew()
	{
		this.txtAuthorize.setText("");
		this.txtCargoMod.setText("");
		this.txtCarrierCode.setText("");
		this.txtCarrierName.setText("");
		this.txtContractDate.setText("");
		this.txtEmail.setText("");
		this.txtFaxNumber.setText("");
		this.txtID.setText("");
		this.txtInsEndDate.setText("");
		this.txtPlaneMod.setText("");
		this.txtRailMod.setText("");
		this.txtSafetyRating.setText("");
		this.txtTruckMod.setText("");

	}
	private void loadCarrier(){
		this.txtAuthorize.setText(Integer.toString(source.getAuthorize()));
		this.txtCargoMod.setText(Double.toString(source.getCostModifierCargoShip()));
		this.txtCarrierCode.setText(source.getCarrierCode());
		this.txtCarrierName.setText(source.getCarrierName());
		this.txtContractDate.setText(source.getContractDate());
		this.txtEmail.setText(source.getEmailAddress());
		this.txtFaxNumber.setText(source.getFaxNumber());
		this.txtID.setText(Integer.toString(source.getId()));
		this.txtInsEndDate.setText(source.getInsEndDate());
		this.txtPlaneMod.setText(Double.toString(source.getCostModifierPlane()));
		this.txtRailMod.setText(Double.toString(source.getCostModifierRail()));
		this.txtSafetyRating.setText(Integer.toString(source.getSafetyRating()));
		this.txtTruckMod.setText(Double.toString(source.getCostModifierTruck()));

	}
	private void setReadOnly()
	{
		this.txtAuthorize.setEnabled(false);
		this.txtCargoMod.setEnabled(false);
		this.txtCarrierCode.setEnabled(false);
		this.txtCarrierName.setEnabled(false);
		this.txtContractDate.setEnabled(false);
		this.txtEmail.setEnabled(false);
		this.txtFaxNumber.setEnabled(false);
		this.txtID.setEnabled(false);
		this.txtInsEndDate.setEnabled(false);
		this.txtPlaneMod.setEnabled(false);
		this.txtRailMod.setEnabled(false);
		this.txtSafetyRating.setEnabled(false);
		this.txtTruckMod.setEnabled(false);


	}
	private void setEditable()
	{
		this.txtAuthorize.setEnabled(true);
		this.txtCargoMod.setEnabled(true);
		this.txtCarrierCode.setEnabled(true);
		this.txtCarrierName.setEnabled(true);
		this.txtContractDate.setEnabled(true);
		this.txtEmail.setEnabled(true);
		this.txtFaxNumber.setEnabled(true);
		this.txtID.setEnabled(false);
		this.txtInsEndDate.setEnabled(true);
		this.txtPlaneMod.setEnabled(true);
		this.txtRailMod.setEnabled(true);
		this.txtSafetyRating.setEnabled(true);
		this.txtTruckMod.setEnabled(true);
	}
	private void update()
	{

	}
	public void addRefreshListener(TableRefreshListener add)
	{
		if(refresh==null)
			refresh = new ArrayList<TableRefreshListener>();
		refresh.add(add);
	}
}
