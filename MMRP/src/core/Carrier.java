/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import GUI.Log;

public class Carrier extends BaseClass 
{
	private static final int DEFAULT_COST_MOD_CARGOSHIP = 0;
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
	
	private final int MAX_SAFETY_RATING = 100;
	private final int MIN_SAFETY_RATING = 0;
	private final int MAX_AUTHORIZE = 5;
	private final int MIN_AUTHORIZE = 0;
	private final int MIN_MODIFIER = 0;
	private final int MAX_MODIFIER = 100;
	
	//DEFAULT VALUES
	private static final String DEFAULT_AREA_CODE = "0";
	private static final int DEFAULT_AUTHORIZE = 0;
	private static final String DEFAULT_CARRIER_CODE = "defaultCarrierCode";
	private static final String DEFAULT_CARRIER_NAME = "defaultCarrierName";
	private static final String DEFAULT_CONTRACT_DATE = "January 1 1970";
	private static final int DEFAULT_COST_MOD_BIKE = 1;
	private static final int DEFAULT_COST_MOD_CARGO = 1;
	private static final int DEFAULT_COST_MOD_PLANE = 1;
	private static final int DEFAULT_COST_MOD_TRUCK = 1;
	private static final int DEFAULT_COST_MOD_RAIL = 1;
	private static final String DEFAULT_EMAIL_ADDRESS = "default@default.com";
	private static final String DEFAULT_FAX_NUMBER = "123-456-7890";
	private static final String DEFAULT_INS_END_DATE = "January 1 1970";
	private static final String DEFAULT_SAFETY_RATE_DATE = "January 1 1970";
	private static final int DEFAULT_SAFETY_RATING = 1;
	
	
	
	/**
	 * Default constructor for a Carrier
	 */
	public Carrier()
	{
		this.areaCode = DEFAULT_AREA_CODE;
		this.authorize = DEFAULT_AUTHORIZE;
		this.carrierCode = DEFAULT_CARRIER_CODE;
		this.carrierName = DEFAULT_CARRIER_NAME;
		this.contractDate = DEFAULT_CONTRACT_DATE;
		this.costModifierBike = DEFAULT_COST_MOD_BIKE;
		this.costModifierCargoShip = DEFAULT_COST_MOD_CARGO;
		this.costModifierPlane = DEFAULT_COST_MOD_PLANE;
		this.costModifierTruck = DEFAULT_COST_MOD_TRUCK;
		this.costModifierRail = DEFAULT_COST_MOD_RAIL;
		this.emailAddress = DEFAULT_EMAIL_ADDRESS;
		this.faxNumber = DEFAULT_FAX_NUMBER;
		this.insEndDate = DEFAULT_INS_END_DATE;
		this.safetyRateDate = DEFAULT_SAFETY_RATE_DATE;
		this.safetyRating = DEFAULT_SAFETY_RATING;
		this.sendByEmail = false;
		this.sendByFax = false;
		this.MarkNew();
		this.MarkClean();
		this.MarkUndeleted();
	}//End of default Carrier constructor
	
	/**
	 * Constructor for Carrier Class when object is loaded from Database
	 * @param id CarrierId in database table
	 */
	public Carrier(int id)
	{
		this.id = id;
		this.areaCode = DEFAULT_AREA_CODE;
		this.authorize = DEFAULT_AUTHORIZE;
		this.carrierCode = DEFAULT_CARRIER_CODE;
		this.carrierName = DEFAULT_CARRIER_NAME;
		this.contractDate = DEFAULT_CONTRACT_DATE;
		this.costModifierBike = DEFAULT_COST_MOD_BIKE;
		this.costModifierCargoShip = DEFAULT_COST_MOD_CARGO;
		this.costModifierPlane = DEFAULT_COST_MOD_PLANE;
		this.costModifierTruck = DEFAULT_COST_MOD_TRUCK;
		this.costModifierRail = DEFAULT_COST_MOD_RAIL;
		this.emailAddress = DEFAULT_EMAIL_ADDRESS;
		this.faxNumber = DEFAULT_FAX_NUMBER;
		this.insEndDate = DEFAULT_INS_END_DATE;
		this.safetyRateDate = DEFAULT_SAFETY_RATE_DATE;
		this.safetyRating = DEFAULT_SAFETY_RATING;
		this.sendByEmail = false;
		this.sendByFax = false;
		this.MarkClean();
	}//End of argumented Carrier constructor
	
	/**
	 * This function returns the default cost modification for the cargo ship
	 * @return Returns the default cost modification for the cargo ship
	 */
	public static int getDefaultCostModCargoship() {
		return DEFAULT_COST_MOD_CARGOSHIP;
	}//End of getDefaultCostModCargoship()

	/**
	 * This function returns the default area code
	 * @return Returns the default area code
	 */
	public static String getDEFAULT_AREA_CODE() {
		return DEFAULT_AREA_CODE;
	}//End of getDEFAULT_AREA_CODE()

	/**
	 * This function returns the default authorize value
	 * @return Returns the default authorize value
	 */
	public static int getDEFAULT_AUTHORIZE() {
		return DEFAULT_AUTHORIZE;
	}//End of getDEFAULT_AUTHORIZE()

	/**
	 * This function will return the default carrier code
	 * @return Returns the default carrier code
	 */
	public static String getDEFAULT_CARRIER_CODE() {
		return DEFAULT_CARRIER_CODE;
	}//End of getDEFAULT_CARRIER_CODE()

	/**
	 * This function returns the default carrier name
	 * @return Returns the default carrier name
	 */
	public static String getDEFAULT_CARRIER_NAME() {
		return DEFAULT_CARRIER_NAME;
	}//End of getDEFAULT_CARRIER_NAME()

	/**
	 * This function returns the default contract date
	 * @return Returns the default contract date
	 */
	public static String getDEFAULT_CONTRACT_DATE() {
		return DEFAULT_CONTRACT_DATE;
	}//End of getDEFAULT_CONTRACT_DATE()

	/**
	 * This function returns the default cost modification for the bike
	 * @return Returns the default cost modification for the bike
	 */
	public static int getDEFAULT_COST_MOD_BIKE() {
		return DEFAULT_COST_MOD_BIKE;
	}//End of getDEFAULT_COST_MOD_BIKE()

	/**
	 * This function returns the default cost modification for the cargo ship
	 * @return Returns the default cost modification for the cargo ship
	 */
	public static int getDEFAULT_COST_MOD_CARGO() {
		return DEFAULT_COST_MOD_CARGO;
	}//End of getDEFAULT_COST_MOD_CARGO()

	/**
	 * This function returns the default cost modification for the plane
	 * @return Returns the default cost modification for the plane
	 */
	public static int getDEFAULT_COST_MOD_PLANE() {
		return DEFAULT_COST_MOD_PLANE;
	}//End of getDEFAULT_COST_MOD_PLANE()

	/**
	 * This function returns the default cost modification for the truck
	 * @return Returns the default cost modification for the truck
	 */
	public static int getDEFAULT_COST_MOD_TRUCK() {
		return DEFAULT_COST_MOD_TRUCK;
	}//End of getDEFAULT_COST_MOD_TRUCK()

	/**
	 * This function returns the default cost modification for the rail
	 * @return Returns the default cost modification for the rail
	 */
	public static int getDEFAULT_COST_MOD_RAIL() {
		return DEFAULT_COST_MOD_RAIL;
	}//End of getDEFAULT_COST_MOD_RAIL()

	/**
	 * This function returns the default email address 
	 * @return Returns the default email address
	 */
	public static String getDEFAULT_EMAIL_ADDRESS() {
		return DEFAULT_EMAIL_ADDRESS;
	}//End of getDEFAULT_EMAIL_ADDRESS()

	/**
	 * This function returns the default fax number
	 * @return Returns the default fax number
	 */
	public static String getDEFAULT_FAX_NUMBER() {
		return DEFAULT_FAX_NUMBER;
	}//End of getDEFAULT_FAX_NUMBER()

	/**
	 * This function returns the default Ins date
	 * @return Returns the default Ins date
	 */
	public static String getDEFAULT_INS_END_DATE() {
		return DEFAULT_INS_END_DATE;
	}//End of getDEFAULT_INS_END_DATE()

	/**
	 * This function returns the default safety rate date
	 * @return Returns the default safety rate date
	 */
	public static String getDEFAULT_SAFETY_RATE_DATE() {
		return DEFAULT_SAFETY_RATE_DATE;
	}//End of getDEFAULT_SAFETY_RATE_DATE()

	/**
	 * This function returns the default safety rating
	 * @return Returns the default safety rating
	 */
	public static int getDEFAULT_SAFETY_RATING() {
		return DEFAULT_SAFETY_RATING;
	}//End of getDEFAULT_SAFETY_RATING()

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
			else 
			{
				Log.writeLogSevere("Invalid area code format. Failed to set area code.");
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
			else 
			{
				//Log.writeLogSevere("Invalid fax number format. Failed to set fax number.");
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
			else 
			{
				Log.writeLogSevere("Invalid email format. Failed to set email address.");
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
			if(FormatChecker.inRange(safetyRating, MIN_SAFETY_RATING, MAX_SAFETY_RATING))
			{
				this.safetyRating = safetyRating;
			}
			else
			{
				Log.writeLogWarning("Invalid safety rating. Setting safety rating to " + DEFAULT_SAFETY_RATING);
				this.safetyRating = DEFAULT_SAFETY_RATING;
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
			if(FormatChecker.inRange(authorize, MIN_AUTHORIZE, MAX_AUTHORIZE))
				this.authorize = authorize;
			else
			{
				this.authorize = DEFAULT_AUTHORIZE;
				Log.writeLogWarning("Invalid authorization code. Setting authorize code to " + DEFAULT_AUTHORIZE);
			}
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
		if(FormatChecker.isValidDate(insEndDate))
		{
			if(this.insEndDate != insEndDate){
				this.insEndDate = insEndDate;
				MarkDirty();
			}
		}
		else
		{
			Log.writeLogWarning("Invalid format for date in Carrier. Setting date to " + DEFAULT_INS_END_DATE);
			this.insEndDate = DEFAULT_INS_END_DATE;
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
		if(this.costModifierCargoShip != costModifierCargoShip){
			if(FormatChecker.inRange(costModifierCargoShip, MIN_MODIFIER, MAX_MODIFIER))
				this.costModifierCargoShip = costModifierCargoShip;
			else
			{
				this.costModifierCargoShip = DEFAULT_COST_MOD_CARGOSHIP;
				Log.writeLogWarning("Invalid modifier value. Setting modifier for Plane to "+ DEFAULT_COST_MOD_CARGOSHIP);
			}
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
			if(FormatChecker.inRange(costModifierTruck, MIN_MODIFIER, MAX_MODIFIER))
				this.costModifierTruck = costModifierTruck;
			else
			{
				this.costModifierTruck = DEFAULT_COST_MOD_TRUCK;
				Log.writeLogWarning("Invalid modifier value. Setting modifier for Plane to "+ DEFAULT_COST_MOD_TRUCK);
			}
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
			if(FormatChecker.inRange(costModifierBike, MIN_MODIFIER, MAX_MODIFIER))
				this.costModifierBike = costModifierBike;
			else
			{
				this.costModifierBike = DEFAULT_COST_MOD_BIKE;
				Log.writeLogWarning("Invalid modifier value. Setting modifier for Plane to "+ DEFAULT_COST_MOD_BIKE);
			}
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
			if(FormatChecker.inRange(costModifierRail, MIN_MODIFIER, MAX_MODIFIER))
				this.costModifierRail = costModifierRail;
			else
			{
				this.costModifierRail = DEFAULT_COST_MOD_RAIL;
				Log.writeLogWarning("Invalid modifier value. Setting modifier for Plane to "+ DEFAULT_COST_MOD_RAIL);
			}
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
		if(this.costModifierPlane != costModifierPlane){
			if(FormatChecker.inRange(costModifierPlane, MIN_MODIFIER, MAX_MODIFIER))
				this.costModifierPlane = costModifierPlane;
			else
			{
				this.costModifierPlane = DEFAULT_COST_MOD_PLANE;
				Log.writeLogWarning("Invalid modifier value. Setting modifier for Plane to "+ DEFAULT_COST_MOD_PLANE);
			}
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
		if(FormatChecker.isValidDate(safetyRateDate))
		{
			if(this.safetyRateDate != safetyRateDate){
				this.safetyRateDate = safetyRateDate;
				MarkDirty();
			}
		}
		else
		{
			Log.writeLogWarning("Invalid date format in Carrier. Setting safety rating date to "+ DEFAULT_SAFETY_RATE_DATE);
			this.safetyRateDate = DEFAULT_SAFETY_RATE_DATE;
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
		if(FormatChecker.isValidDate(contractDate))
		{
			if(this.contractDate != contractDate){
				this.contractDate = contractDate;
				MarkDirty();
			}
		}
		else
		{
			Log.writeLogWarning("Invalid date format in Carrier. Setting contract date to "+ DEFAULT_CONTRACT_DATE);
			this.contractDate = DEFAULT_CONTRACT_DATE;
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
				String sql = "Insert into carriers (CarrierCode," +
													"CarrierName," +
													"CostModifierTruck," +
													"CostModifierBike," +
													"CostModifierCargoShip," +
													"CostModifierRail," +
													"CostModifierPlane," +
													"SendByFax," +
													"SendByEmail," +
													"AreaCode," +
													"FaxNumber," +
													"EmailAddress," +
													"SafetyRating," +
													"SafetyRateDate," +
													"Authorize," +
													"ContractDate," +
													"InsEndDate) " +
													"Values " +
													"('"+this.getCarrierCode()+"'," +
													"'"+this.getCarrierName()+"'," +
													"'"+this.getCostModifierTruck()+"'," +
													"'"+this.getCostModifierBike()+"'," +
													"'"+this.getCostModifierCargoShip()+"'," +
													"'"+this.getCostModifierRail()+"'," +
													"'"+this.getCostModifierPlane()+"'," +
													this.isSendByFax()+"," +
													this.isSendByEmail()+"," +
													"'"+this.getAreaCode()+"'," +
													"'"+this.getFaxNumber()+"'," +
													"'"+this.getEmailAddress()+"'," +
													"'"+this.getSafetyRating()+"'," +
													"'"+this.getSafetyRateDate()+"'," +
													"'"+this.getAuthorize()+"'," +
													"'"+this.getContractDate()+"'," +
													"'"+this.getInsEndDate()+"');";
				
				executeCommand(sql);
				
				//Grab this Carrier from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select CarrierID from Carriers where CarrierCode = '" + this.getCarrierCode() + "' AND CarrierName = '"+this.getCarrierName()+
				"' AND CostModifierTruck = '" + this.getCostModifierTruck() + "' AND CostModifierBike = '" + this.getCostModifierBike() + "' AND CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' AND CostModifierRail = '" + this.getCostModifierRail() + "' AND CostModifierPlane = '" + this.getCostModifierPlane() +
				"' AND SendByFax = '" + this.isSendByFax() + "' AND SendByEmail = '" + this.isSendByEmail() + "' AND AreaCode = '" + this.getAreaCode() + 
				"' AND FaxNumber = '" + this.getFaxNumber() + "' AND EmailAddress = '" +this.getEmailAddress() + "' AND SafetyRating = '" + this.getSafetyRating()+
				"' AND SafetyRateDate ='"+ this.getSafetyRateDate() + "' AND Authorize = '" + this.getAuthorize() + "' AND ContractDate = '"+ this.getContractDate()+
				"' AND InsEndDate = '"+ this.getInsEndDate()+"'");
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(temp.size()-1).get("CarrierID");			
					MarkClean();												
					MarkOld();													
				}//End of entry found if
				
			}//End of isNew if
			else
			{
				if(isDirty())
				{
				executeCommand("Update Carriers set CarrierCode = '" + this.getCarrierCode() + "' , CarrierName = '"+this.getCarrierName()+
				"' , CostModifierTruck = '" + this.getCostModifierTruck() + "' , CostModifierBike = '" + this.getCostModifierBike() +"' , CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' , CostModifierRail = '" + this.getCostModifierRail() + "' , CostModifierPlane = '" + this.getCostModifierPlane() +
				"' , SendByFax = " + this.isSendByFax() + " , SendByEmail = " + this.isSendByEmail() + " , AreaCode = '" + this.getAreaCode() + 
				"' , FaxNumber = '" + this.getFaxNumber() + "' , EmailAddress = '" +this.getEmailAddress() + "' , SafetyRating = '" + this.getSafetyRating()+
				"' , SafetyRateDate = '"+ this.getSafetyRateDate() + "' , Authorize = '" + this.getAuthorize() + 
				"' , ContractDate = '"+ this.getContractDate() + "' , InsEndDate = '"+ this.getInsEndDate()+"' , Deleted = " +this.isDeleted() + " where CarrierID = '" + this.getId() + "'");
				MarkClean();												
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block
	
	}//End of overridden Update()
	
	/**
	 * This function is the overridden delete, it will set the Carrier to deleted status
	 */
	@Override
	public boolean Delete() {
		try
		{
			executeCommand("Update Carriers Set Deleted = true Where CarrierID = " + this.id);	
			//Now delete all the vehicles that used this carrier
			executeCommand("Update Bike Set Deleted = true Where Carrier = "+ this.id);
			executeCommand("Update CargoShip Set Deleted = true Where Carrier = "+ this.id);
			executeCommand("Update Plane Set Deleted = true Where Carrier = "+ this.id);
			executeCommand("Update Rail Set Deleted = true Where Carrier = "+ this.id);
			executeCommand("Update Truck Set Deleted = true Where Carrier = "+ this.id);
			this.MarkDeleted();
			return true;
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
			ex.printStackTrace();
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
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Carriers where CarrierID = " + id  + " AND Deleted = false");
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
			ex.printStackTrace();
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
			ArrayList<Map<String,Object>> temp;
			if(where.isEmpty())
				temp = executeQuery("Select * from Carriers where Deleted = false");
			else
				temp = executeQuery("Select * from Carriers " +  where  + " AND Deleted = false");
			for(int i = 0 ; i<temp.size();i++)
			{
				Carrier c = BuildFromDataRow(temp.get(i));
				returnList.add(c);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
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
			c.setCarrierName((String)data.get("CarrierName"));
		}
		if((Integer)data.get("costModifierTruck") != null)
		{
			c.setCostModifierTruck((Integer)data.get("costModifierTruck"));
		}
		if((Integer)data.get("costModifierBike") != null)
		{
			c.setCostModifierBike((Integer)data.get("costModifierBike"));
		}
		if((Integer)data.get("costModifierCargoShip") != null)
		{
			c.setCostModifierCargoShip((Integer)data.get("costModifierCargoShip"));
		}
		if((Integer)data.get("costModifierRail") != null)
		{
			c.setCostModifierRail((Integer)data.get("costModifierRail"));
		}
		if((Integer)data.get("costModifierPlane") != null)
		{
			c.setCostModifierPlane((Integer)data.get("costModifierPlane"));
		}
		if((Boolean)data.get("SendByFax") != null)
		{
			if(Boolean.getBoolean(data.get("SendByFax").toString())){	
				c.setSendByFaxTrue();
			}else
			{
				c.setSendByFaxFalse();
			}
		}
		if((Boolean)data.get("SendByEmail") != null)
		{
			if(Boolean.getBoolean(data.get("SendByEmail").toString())){
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
		
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			c.MarkDeleted();
		else
			c.MarkUndeleted();
		
		c.MarkClean();
		return c;
		
	}//End of the BuildFromDataRow(Map<String, Object> data)
	@Override
	public String toString()
	{
		return this.getCarrierName();
	}
	
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Carrier))
            return false;

        Carrier test = (Carrier) obj;
        if(test.getId()==this.getId())return true;
        return false;
    }
}//End of Carrier Class
