package core;

import java.util.ArrayList;
import java.util.Map;

public class Shipper extends BaseClass {
	
	private int id;
	private int locationID;
	private String prefCarriers;
	private String companyName;
	private String contactName;
	private String phone;
	private String email;
	
	/**
	 * This is the default Shipper constructor
	 */
	public Shipper()
	{
		MarkNew();
	}//End of Shipper()
	
	/**
	 * This is the argumented Shipper constructor
	 * @param id This is the id of the new Shipper constructor
	 */
	public Shipper(int id)
	{
		this.id=id;
		MarkOld();
	}//End of Shipper(int id)
	
	//Setters & getters
	/**
	 * This function returns the id of the Shipper
	 */
	public int getID()
	{
		return id;
	}//End of id()
	
	/**
	 * This function sets the location id for the Shipper
	 * @param id This is the new id for the Shipper
	 */
	public void setLocationID(int id)
	{
		//NEEDS ERROR CHECK
		if(this.locationID!=id)
		{
			this.locationID=id;
			MarkDirty();
		}
	}//End of LocationID(int id)
	
	/**
	 * This function returns the Shipper location id
	 * @return Returns the Shipper location id
	 */
	public int getLocationID()
	{
		return this.locationID;
	}//End of LocationID()
	
	/** 
	 * This function will set preferred carriers for the Shipper
	 * @param newPreferredCarriers This is the new preferred carriers for the Shipper
	 */
	public void setPrefferedCarriers(String newPreferredCarriers)
	{
		//NEEDS SOME ERROR CHECKING
		if(this.prefCarriers==null || !this.prefCarriers.equals(newPreferredCarriers))
		{
			this.prefCarriers=newPreferredCarriers;
			MarkDirty();
		}
	}//End of PrefferedCarriers(String newPreferredCarriers)
	
	/**
	 * This function returns the preferred carriers for the Shipper
	 * @return Returns the preferred carriers for the Shipper
	 */
	public String getPrefferedCarriers()
	{
		return this.prefCarriers;
	}//End of PrefferedCarriers()
	
	/**
	 * This function sets the company name for the Shipper
	 * @param newCompanyName This is the new company name for the Shipper
	 */
	public void setCompanyName(String newCompanyName)
	{
		//NEEDS ERROR CHECKING
		if(this.companyName==null || !this.companyName.equals(newCompanyName))
		{
			this.companyName=newCompanyName;
			MarkDirty();
		}
	}//End of CompanyName(String newCompanyName)
	
	/**
	 * This function returns the company name for the Shipper 
	 * @return Returns the company name for the Shipper
	 */
	public String getCompanyName()
	{
		return this.companyName;
	}//End of CompanyName()
	
	/**
	 * This function sets the contact name for the Shipper
	 * @param newContactName This is the new contact name for the Shipper
	 */
	public void setContactName(String newContactName)
	{
		//NEED SOME ERROR CHECKING
		if(this.contactName==null || !this.contactName.equals(newContactName))
		{
			this.contactName=newContactName;
			MarkDirty();
		}
	}//End of ContactName(String newContactName)
	
	/**
	 * This function returns the contact name for the Shipper
	 * @return Returns the contact name for the Shipper
	 */
	public String getContactName()
	{
		return this.contactName;
	}//End of ContactName()
	
	/**
	 * This function sets the phone number for the Shipper 
	 * @param newPhoneNumber This is the new phone number for the Shipper
	 */
	public void setPhoneNumber(String newPhoneNumber)
	{
		//NEED ERROR CHECKING
		if(this.phone==null|| !this.phone.equals(newPhoneNumber))
		{
			this.phone=newPhoneNumber;
			MarkDirty();
		}
	}//End of Phone(String newPhoneNumber)
	
	/**
	 * This function returns the phone number for the Shipper
	 * @return Returns the phone number for the Shipper
	 */
	public String getPhoneNumber()
	{
		return this.phone;
	}//End of Phone()
	
	/**
	 * This function sets the email address for the Shipper
	 * @param newEmailAddress This is the new email address for the Shipper
	 */
	public void setEmailAddress(String newEmailAddress)
	{
		//NEED ERROR CHECKING
		if(this.email==null || ! this.email.equals(newEmailAddress))
		{
			this.email=newEmailAddress;
			MarkDirty();
		}
	}//End of Email(String newEmailAddress)
	
	/**
	 * This function returns the email address of the Shipper
	 * @return Returns the email address of the Shipper
	 */
	public String getEmailAddress()
	{
		return this.email;
	}//End of getEmailAddress()
	
	/**
	 * This function loads a Shipper from the database
	 * @param id This is the id of the Shipper to load from the database
	 * @return Returns a Shipper from the database based on the given id
	 */
	public static Shipper Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * From Shipper where ShipperID = '" + id+"'");
			if(temp.size()>0)
				return BuildFromDataRow(temp.get(0));
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return null;
		}
	}//End of Load(int id)
	
	/**
	 * This function will return an ArrayList of Shippers from the database based on the given where clause
	 * @param where This is the clause that determines which Shippers to load from the database
	 * @return Returns an ArrayList of Shippers loaded from the database based on the given where clause
	 */
	public static ArrayList<Shipper> LoadAll(String where)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Shipper "+where);
			ArrayList<Shipper> returnList=new ArrayList<Shipper>();
			for(int i = 0;i<temp.size();i++ )
			{
				returnList.add(BuildFromDataRow(temp.get(i)));
			}
			return returnList;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return null;
		}
	}//End of  LoadAll(String where)
	
	/**
	 * This function will build a new Shipper object from the passed in data
	 * @param data This is the data that will be used to build the new Shipper object
	 * @return Returns the Shipper object built from the given data
	 */
	public static Shipper BuildFromDataRow(Map<String,Object> data)
	{
		Shipper s = new Shipper((Integer)data.get("ShipperID"));
		s.setLocationID((Integer)data.get("locationID"));
		s.setPrefferedCarriers((String)data.get("prefCarriers"));
		s.setCompanyName((String)data.get("CompanyName"));
		s.setContactName((String)data.get("ContactName"));
		s.setPhoneNumber((String)data.get("phone"));
		s.setEmailAddress((String)data.get("email"));
		s.MarkClean();
		return s;
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	/**
	 * This function will update the Shipper in the database
	 */
	@Override
	boolean Update() {
		if(isNew())
		{
			//If the Segment is new insert it into the database by executing the following
			try
			{
				executeCommand("Insert into Shipper (locationID,prefCarriers,CompanyName,ContactName,phone,email) Values ('"+
						this.locationID+"','"+this.prefCarriers+"','"+this.companyName+"','"+this.contactName+"','"+this.phone+"','"+
						this.email+"')");
				//Grab this Segment from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipperID from Shipper where locationID ='"+ this.locationID+"' "+
						"AND prefCarriers ='" + this.prefCarriers +"' "+
						"AND CompanyName ='" + this.companyName+"' "+
						"And ContactName='" + this.contactName+"' "+
						"And phone='"+this.phone+"' "+
						"And email ='"+this.email+"'");
						
				//If this Segment exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("ShipperID");				//Set the Segment id to the id from the database
					MarkClean();													//Mark the Segment as clean
					MarkOld();														//Mark the Segment as old
				}//End of found something if
				return true;
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
				return false;
			}
			
		}//End of isNew if
		else
		{
			if(isDirty())
			{
				try
				{
				executeCommand("Update Shipper Set locationID ='"+ this.locationID+"' "+
						"AND prefCarriers ='" + this.prefCarriers +"' "+
						"AND CompanyName ='" + this.companyName+"' "+
						"And ContactName='" + this.contactName+"' "+
						"And phone='"+this.phone+"' "+
						"And email ='"+this.email+"' "+
						"Where ShipperID = '" +this.id +"'");
				
				
				//If the Segment is not new, but is dirty then it needs to be updated by the following SQL command
		
				MarkClean();													//Mark the Segment as clean
				
				}
				catch(Exception ex)
				{
					System.out.println("Error " +ex);
					return false;
				}
			}//End of isDirty if
		}//End of isOld else
		return true;
	}//End of Update()
	

	/**
	 * This function will delete the Shipper from the database
	 */
	@Override
	boolean Delete() {
		try
		{
			executeCommand("Delete from Shipper where ShipperID = '" + this.id+"'");
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error " +ex);
			return false;
		}

	}//End of Delete()

}//End of Shipper Class
