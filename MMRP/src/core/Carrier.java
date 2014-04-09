/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Carrier extends BaseClass 
{
	private int id;
	private String carrierCode;
	private String carrierName;
	private Boolean sendByFax;
	private Boolean sendByEmail;
	private String areaCode;
	private String faxNumber;
	private String emailAddress;
	private int safetyRating;
	private String safetyRateDate;
	private int authorize;
	private String contractDate;
	private String insEndDate;
	private int costModifierTruck;
	private int costModifierBike;
	private int costModifierCargoShip;
	private int costModifierRail;
	private int costModifierPlane;
	
	private final int maxSafetyRating = 100;
	private final int minSafetyRating = 0;
	private final int maxAuthorize = 5;
	private final int minAuthorize = 0;
	private final double minModifier = 0.0;
	private final double maxModifier = 1.0;
	
	/**
	 * Default constructor for a Carrier
	 */
	public Carrier()
	{
		MarkNew();
	}//End of default Carrier constructor
	
	/**
	 * Constructor for Carrier Class when object is loaded from Database
	 * @param id CarrierId in database table
	 */
	public Carrier(int id)
	{
		this.id = id;
	}//End of argumented Carrier constructor
	
	/**
	 * This function returns the id of the Carrier object
	 * @return Returns the Carrier ID
	 */
	public int getId() {
		return id;
	}//End of getId()

	/**
	 * This function returns the carrier code
	 * @return Returns the carrier code
	 */
	public String getCarrierCode() {
		return carrierCode;
	}//End of getCarrierCode()
	
	/**
	 * This function sets the carrier code
	 * @param carrierCode The new Carrier code
	 */
	public void setCarrierCode(String carrierCode) {
		//NEED ERROR CHECKING
		if(this.carrierCode != carrierCode){
			this.carrierCode = carrierCode;
			MarkDirty();
		}
	}//End of setCarrierCode(String carrierCode)
	
	/**
	 * This function will return the Carrier's name
	 * @return Returns the Carrier's name
	 */
	public String getCarrierName() {
		return carrierName;
	}//End of getCarrierName()
	
	/**
	 * This function sets the Carrier's name
	 * @param carrierName The new Carrier name
	 */
	public void setCarrierName(String carrierName) {
		//NEED SOME ERROR CHECKING
		if(this.carrierName != carrierName){
			this.carrierName = carrierName;
			MarkDirty();
		}
	}//End of setCarrierName(String carrierName)
	
	/**
	 * This function returns if the Carrier can be reached by fax
	 * @return Returns a boolean indicating if the Carrier can be reached by fax or not
	 */
	public Boolean isSendByFax() {
		return sendByFax;
	}//End of isSendByFax()
	
	/**
	 * This function sets the Carrier to be reachable by fax
	 */
	public void setSendByFaxTrue() {
		if(!sendByFax){
			this.sendByFax = true;
			MarkDirty();
		}
	}//End of setSendByFaxTrue()
	
	/**
	 * This function sets the Carrier to be unreachable by fax
	 */
	public void setSendByFaxFalse() {
		if(sendByFax){
			this.sendByFax = false;
			MarkDirty();
		}
	}//End of setSendByFaxFalse()
	
	/**
	 * This function will return if the Carrier is reachable by email
	 * @return Returns a boolean indicating if the Carrier can be reached by email
	 */
	public Boolean isSendByEmail() {
		return sendByEmail;
	}//End of isSendByEmail()
	
	/**
	 * This function will set the Carrier to be reachable by email
	 */
	public void setSendByEmailTrue() {
		if(!sendByEmail){
			this.sendByEmail = true;
			MarkDirty();
		}
	}//End of setSendByEmailTrue()
	
	/**
	 * This function will set the Carrier to be unreachable by email
	 */
	public void setSendByEmailFalse() {
		if(sendByEmail){
			this.sendByEmail = false;
			MarkDirty();
		}
	}//End of setSendByEmailFalse()
	
	/**
	 * This function returns the area code of the Carrier
	 * @return Returns the area code of the Carrier
	 */
	public String getAreaCode() {
		return areaCode;
	}//End of getAreaCode()
	
	/**
	 * This function sets the area code for the Carrier
	 * @param areaCode This is the new area code for the Carrier
	 */
	public void setAreaCode(String areaCode) {
		if(FormatChecker.isValidAreaCode(areaCode)){
			if(this.areaCode != areaCode){
				this.areaCode = areaCode;
				MarkDirty();
			}
		}
	}//End of setAreaCode(String areaCode)
	
	/**
	 * This function returns the fax number of the Carrier
	 * @return Returns the Carrier's fax number
	 */
	public String getFaxNumber() {
		return faxNumber;
	}//End of getFaxNumber()
	
	/**
	 * This function sets the fax number for the Carrier
	 * @param faxNumber This is the new fax number for the Carrier
	 */
	public void setFaxNumber(String faxNumber) {
		if(this.faxNumber != faxNumber){
			if(FormatChecker.isValidPhone(faxNumber))
			{
				this.faxNumber = faxNumber;
				MarkDirty();
			}
		}
	}//End of setFaxNumber(String faxNumber)
	
	/**
	 * This function will return the Carrier's email address
	 * @return Returns the Carrier's email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}//End of getEmailAddress()
	
	/**
	 * This function sets the Carrier's email address
	 * @param emailAddress This is the Carrier's new email address
	 */
	public void setEmailAddress(String emailAddress) {
		if(this.emailAddress != emailAddress){
			if(FormatChecker.isValidEmail(emailAddress))
			{
				this.emailAddress = emailAddress;
				MarkDirty();
			}
		}
	}//End of setEmailAddress(String emailAddress)
	
	/**
	 * This function returns the Carrier's safety rating
	 * @return Returns the Carrier's safety rating
	 */
	public int getSafetyRating() {
		return safetyRating;
	}//End of getSafetyRating()
	
	/**
	 * This function sets the safety rating for the Carrier
	 * @param safetyRating This is the new safety rating for the Carrier
	 */
	public void setSafetyRating(int safetyRating) {
		if(this.safetyRating != safetyRating){
			if(FormatChecker.inRange(safetyRating, minSafetyRating, maxSafetyRating))
			{
				this.safetyRating = safetyRating;
			}
			else
			{
				this.safetyRating = 0;
			}
			MarkDirty();
		}
	}//End of setSafetyRating(int safetyRating)
	
	/**
	 * This function will return the authorization status of the Carrier
	 * @return Returns the value of authorization for the Carrier
	 */
	public int getAuthorize() {
		return authorize;
	}//End of getAuthorize()
	
	/**
	 * This function sets the authorization value for the Carrier
	 * @param authorize This is the new authorization value for the Carrier
	 */
	public void setAuthorize(int authorize) {
		if(this.authorize != authorize){
			if(FormatChecker.inRange(authorize, minAuthorize, maxAuthorize))
				this.authorize = authorize;
			else
				this.authorize = 0;
			MarkDirty();
		}
	}//End of setAuthorize(int authorize)
	
	/**
	 * This function returns the Carrier's ins date
	 * @return Returns the ins date of the Carrier
	 */
	public String getInsEndDate() {
		return insEndDate;
	}//End of getInsEndDate()
	
	/**
	 * This function sets the Carrier's ins date
	 * @param insEndDate This is the new Carrier's ins date
	 */
	public void setInsEndDate(String insEndDate) {
		//NEED SOME ERROR CHECKING
		if(this.insEndDate != insEndDate){
			this.insEndDate = insEndDate;
			MarkDirty();
		}
	}//End of setInsEndDate(String insEndDate)
	
	/**
	 * This function returns the Carriers cost modifier for Cargo ships
	 * @return Returns the cost modifier for Cargo ships
	 */
	public int getCostModifierCargoShip() {
		return costModifierCargoShip;
	}//End of getCostModifierCargoShip()
	
	/**
	 * This function sets the cost modifier for cargo ships
	 * @param costModifierCargoShip This is the new cost modifier for cargo ships
	 */
	public void setCostModifierCargoShip(int costModifierCargoShip) {
		//Need some error checking
		if(this.costModifierCargoShip != costModifierCargoShip){
			this.costModifierCargoShip = costModifierCargoShip;
			MarkDirty();
		}
	}//End of setCostModifierCargoShip(int costModifierCargoShip)
	
	/**
	 * This function returns the cost modifier for trucks for the Carrier
	 * @return Returns the cost modifier for trucks
	 */
	public int getCostModifierTruck() {
		return costModifierTruck;
	}//End of getCostModifierTruck()
	
	/**
	 * This function will set the truck cost modifier for the Carrier
	 * @param costModifierTruck The new truck cost modifer for the Carrier
	 */
	public void setCostModifierTruck(int costModifierTruck) {
		if(this.costModifierTruck != costModifierTruck){
			this.costModifierTruck = costModifierTruck;
			MarkDirty();
		}
	}//End of setCostModifierTruck(int costModifierTruck)
	
	/**
	 * This function will return the cost modifier for bikes
	 * @return Returns the bike cost modifier for the Carrier
	 */
	public int getCostModifierBike() {
		return costModifierBike;
	}//End of getCostModifierBike() 
	
	/**
	 * This function will set the bike cost modifier for the Carrier
	 * @param costModifierBike The new bike cost modifier for the Carrier
	 */
	public void setCostModifierBike(int costModifierBike) {
		if(this.costModifierBike != costModifierBike){
			this.costModifierBike = costModifierBike;
			MarkDirty();
		}
	}//End of setCostModifierBike(int costModifierBike)
	
	/**
	 * This function will return the rail cost modifier for the Carrier
	 * @return Returns the rail cost modifier for the Carrier
	 */
	public int getCostModifierRail() {
		return costModifierRail;
	}//End of getCostModifierRail()
	
	/**
	 * This function sets the rail cost modifier for the Carrier
	 * @param costModifierRail The new rail cost modifier for the Carrier
	 */
	public void setCostModifierRail(int costModifierRail) {
		if(this.costModifierRail != costModifierRail){
			this.costModifierRail = costModifierRail;
			MarkDirty();
		}
	}//End of setCostModifierRail(int costModifierRail)
	
	/**
	 * This function returns the plane cost modifier for the Carrier
	 * @return Returns the plane cost modifier for the Carrier
	 */
	public int getCostModifierPlane() {
		return costModifierPlane;
	}//End of getCostModifierPlane()
	
	/**
	 * This function sets the plane cost modifier for the Carrier
	 * @param costModifierPlane The new plane cost modifier for the Carrier
	 */
	public void setCostModifierPlane(int costModifierPlane) {
		//NEED ERROR CHECKING
		if(this.costModifierPlane != costModifierPlane){
			this.costModifierPlane = costModifierPlane;
			MarkDirty();
		}
	}//End of setCostModifierPlane(int costModifierPlane)
	
	/**
	 * This function returns the safety rating of the Carrier
	 * @return Returns the safety rating of the Carrier
	 */
	public String getSafetyRateDate() {
		return safetyRateDate;
	}//End of getSafetyRateDate()
	
	/**
	 * This function sets the Carrier's safety rating
	 * @param safetyRateDate This is the Carrier's new safety rating
	 */
	public void setSafetyRateDate(String safetyRateDate) {
		//Need some error checking
		if(this.safetyRateDate != safetyRateDate){
			this.safetyRateDate = safetyRateDate;
			MarkDirty();
		}
	}//End of setSafetyRateDate(String safetyRateDate)
	
	/**
	 * This function will return the Carrier's contract date
	 * @return Returns the contract date of the Carrier
	 */
	public String getContractDate() {
		return contractDate;
	}//End of getContractDate()
	
	/**
	 * This function sets the Carrier's contract date
	 * @param contractDate This is the Carrier's new contract date
	 */
	public void setContractDate(String contractDate) {
		//Need error checking
		if(this.contractDate != contractDate){
			this.contractDate = contractDate;
			MarkDirty();
		}
	}//End of setContractDate(String contractDate)
	
	/**
	 * This function updates the database entry for this object.
	 * <p>
	 * If the object is new it will be inserted into the database.
	 * If the object is dirty, the database entry will be updated
	 * @return 
	 */
	@Override
	public boolean Update() 
	{
		try
		{
		//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Carrier is new insert it into the database by executing the following
				executeCommand("Insert into Carriers (CarrierCode,CarrierName,CostModifierTruck,CostModifierBike" +
				",CostModifierCargoShip,CostModifierRail,CostModifierPlane,SendByFax,SendByEmail,AreaCode,FaxNumber,EmailAddress" +
				"SafetyRating,SafetyRateDate,Authorize,ContractDate,InsEndDate) Values ('"+ getCarrierCode() + "','" + getCarrierName()+
				"','" +getCostModifierTruck()+"','"+ getCostModifierBike()+"','"+getCostModifierCargoShip()+
				"','"+ getCostModifierRail()+"','"+ getCostModifierPlane()+"','"+isSendByFax()+"','"+isSendByEmail() +
				"','" + getAreaCode() + "','" + getFaxNumber()+ "','"+ getEmailAddress()+"','"+ getSafetyRating()+"','"+getSafetyRateDate()+"','"+getAuthorize()+
				"','"+getContractDate()+"','"+getInsEndDate()+"')");
				
				//Grab this Carrier from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select CarrierID from Carrier where CarrierCode = '" + this.getCarrierCode() + "' AND CarrierName = '"+this.getCarrierName()+
				"' AND CostModifierTruck = '" + this.getCostModifierTruck() + "' AND CostModifierBike = '" + this.getCostModifierBike() + "' AND CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' AND CostModifierRail = '" + this.getCostModifierRail() + "' AND CostModifierPlane = '" + this.getCostModifierPlane() +
				"' AND SendByFax = '" + this.isSendByFax() + "' AND SendByEmail = '" + this.isSendByEmail() + "' AND AreaCode = '" + this.getAreaCode() + 
				"' AND FaxNumber = '" + this.getFaxNumber() + "' AND EmailAddress = '" +this.getEmailAddress() + "' AND SafetyRating = '" + this.getSafetyRating()+
				"' AND SafetyRateDate ='"+ this.getSafetyRateDate() + "' AND Authorize = '" + this.getAuthorize() + "' AND ContractDate = '"+ this.getContractDate()+
				"' AND InsEndDate = '"+ this.getInsEndDate()+"'");
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("CarrierId");				
					MarkClean();												
					MarkOld();													
				}//End of entry found if
				
			}//End of isNew if
			else
			{
				if(isDirty())
				{
				executeQuery("Update Carrier set CarrierCode = '" + this.getCarrierCode() + "' , CarrierName = '"+this.getCarrierName()+
				"' , CostModifierTruck = '" + this.getCostModifierTruck() + "' , CostModifierBike = '" + this.getCostModifierBike() +"' , CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' , CostModifierRail = '" + this.getCostModifierRail() + "' , CostModifierPlane = '" + this.getCostModifierPlane() +
				"' , SendByFax = '" + this.isSendByFax() + "' , SendByEmail = '" + this.isSendByEmail() + "' , AreaCode = '" + this.getAreaCode() + 
				"' , FaxNumber = '" + this.getFaxNumber() + "' , EmailAddress = '" +this.getEmailAddress() + "' , SafetyRating = '" + this.getSafetyRating()+"'"+
				"' , SafetyRateDate = '"+ this.getSafetyRateDate() + " , Authorize = '" + this.getAuthorize() + 
				"', ContractDate = '"+ this.getContractDate() + "' , InsEndDate = '"+ this.getInsEndDate()+"'");
				MarkClean();												
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
			return false;
		}//End of catch block
	
	}//End of overridden Update()
	
	/**
	 * This function is the overridden delete, it will remove this Carrier from the database
	 */
	@Override
	boolean Delete() {
		try
		{
			executeCommand("Delete from Carriers Where CarrierID = " + this.id);	
			return true;
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
			return false;
		}

	}//End of Delete()
	
	/**
	 * This function will load a Carrier object from the database based on the given ID
	 * @param id This is the id of the Carrier to load from the database
	 * @return Returns a new Carrier object populated with the information from the database
	 */
	public static Carrier Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Carriers where CarrierID = " + id);
			if(temp.size()>0)
			{
				Carrier c = BuildFromDataRow(temp.get(0));
				return c;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}//End of Load(int id)
	
	/**
	 * This function will return an ArrayList of Carriers loaded from the database based on the passed in where clause
	 * @param where This is the where clause specifying the Carriers to load from the database
	 * @return Returns an ArrayList of Carrier objects from the database specified in the where clause
	 */
	public static ArrayList<Carrier> LoadAll(String where)
	{
		ArrayList<Carrier> returnList = new ArrayList<Carrier>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Carrier " +  where);
			for(int i = 0 ; i<temp.size();i++)
			{
				Carrier c = BuildFromDataRow(temp.get(i));
				returnList.add(c);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}//End of LoadAll(String where)
	
	/**
	 * This function builds a Carrier object from the passed in data
	 * @param data This is the data used to build a new Carrier
	 * @return Returns the new Carrier object initialized from the passed in data
	 * @throws SQLException
	 */
	public static Carrier BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		Carrier c = new Carrier((Integer)data.get("CarrierID"));
		if((String)data.get("CarrierCode") != null)
		{
			c.setCarrierCode((String)data.get("CarrierCode"));
		}
		if((String)data.get("CarrierName") != null)
		{
			c.setCarrierCode((String)data.get("CarrierName"));
		}
		if((Integer)data.get("CostModifierTruck") != null)
		{
			c.setCostModifierTruck((Integer)data.get("CostModifierTruck"));
		}
		if((Integer)data.get("CostModifierBike") != null)
		{
			c.setCostModifierBike((Integer)data.get("CostModifierBike"));
		}
		if((Integer)data.get("CostModifierCargoShip") != null)
		{
			c.setCostModifierCargoShip((Integer)data.get("CostModifierCargoShip"));
		}
		if((Integer)data.get("CostModifierRail") != null)
		{
			c.setCostModifierRail((Integer)data.get("CostModifierRail"));
		}
		if((Integer)data.get("CostModifierPlane") != null)
		{
			c.setCostModifierPlane((Integer)data.get("CostModifierPlane"));
		}
		if((Boolean)data.get("SendByFax") != null)
		{
			if((Boolean)data.get("SendByFax")){	
				c.setSendByFaxTrue();
			}else
			{
				c.setSendByFaxFalse();
			}
		}
		if((Boolean)data.get("SendByEmail") != null)
		{
			if((Boolean)data.get("SendByEmail")){
				c.setSendByEmailTrue();
			}else{
				c.setSendByEmailFalse();
			}
		}
		if((String)data.get("AreaCode") != null)
		{
			c.setAreaCode((String)data.get("AreaCode"));
		}
		if((String)data.get("FaxNumber") != null)
		{
			c.setFaxNumber((String)data.get("FaxNumber"));
		}
		if((String)data.get("EmailAddress") != null)
		{
			c.setEmailAddress((String)data.get("EmailAddress"));
		}
		if((Integer)data.get("SafetyRating") != null)
		{
			c.setSafetyRating((Integer)data.get("SafetyRating"));
		}
		if((String)data.get("SafetyRateDate") != null)
		{
			c.setSafetyRateDate((String)data.get("SafetyRateDate"));
		}
		if((Integer)data.get("Authorize") != null)
		{
			c.setAuthorize((Integer)data.get("Authorize"));
		}
		if((String)data.get("ContractDate") != null)
		{
			c.setContractDate((String)data.get("ContractDate"));
		}
		if((String)data.get("InsEndDate") != null)
		{
			c.setInsEndDate((String)data.get("InsEndDate"));
		}
		c.MarkClean();
		return c;
		
	}//End of the BuildFromDataRow(Map<String, Object> data)
	
}//End of Carrier Class
