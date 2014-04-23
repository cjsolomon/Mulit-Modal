package zeus;

import java.text.DecimalFormat;

/**
 * Stores all of the information about a Shipment.
 * Title: Shipment
 * Description: This class stores all of the information about a shipment.
 * Copyright: Copyright (c) 2003
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class Shipment
    implements java.io.Serializable, java.lang.Cloneable {
  protected Shipment prev;
  protected Shipment next;
  protected int index;
  protected float demand;
  protected double xCoord; //latitude
  protected double yCoord; //longitude
  boolean locked; //if the shipment is locked, it cannot be moved between trucks

  //protected float serviceTime;
  protected boolean isAssigned; //true if the shipment has been assigned
  protected boolean isSelected; //true if the shipment was selected
  protected String truckTypeNeeded;

  //protected String pickUpPointName;
  //protected int frequency;
  //protected int noComb;
  //protected int[] visitComb; //the visit combination for the depots
  //protected int[][] currentComb; //the binary visit combinations
  
  //Added 3/7/2014
  //earliest and latest pickup date/time for a shipment
  protected String earliestPickupData;
  protected String earliestPickupTime;
  protected String latestPickupData;
  protected String latestPickupTime;
  
  //earliest and latest drop off date/time for shipment
  protected String earliestDropOffData;
  protected String earliestDropOffTime;
  protected String latestDropOffData;
  protected String latestDropOffTime;
  
  //source location of shipment
  protected String sourceCity;
  protected String sourceState;
  protected String sourceZip;
  protected long sourceLat;
  protected long sourceLong;
  
  //destination location of shipment
  protected String destCity;
  protected String destState;
  protected String destZip;
  protected long destLat;
  protected long destLong;
  
  //mileage and time
  protected float travelTime;	//travel time from source to destination
  protected float dist;			//distance from source to destination
  
  //service time
  protected float loadTime;     //load time for shipment
  protected float loadingRate;
  protected float unloadTime;   //unload time for shipment
  protected float unloadingRate;
  
  //commodity information
  protected int shipWeight;				//shipment weight
  protected int shipDimension;			//shipment dimension
  protected String palletDimension;		//pallet information
  protected String palletWeight;	
  protected boolean isPalletStackble;
  
  //vehicle compatibility
  protected String[] trailerType;     // truck or trailer compatibility type 
  
  //preferred/reliable carriers
  protected String[] prefCarriers;
  protected int carrierReliability;
  
  //loading and unloading requirements
  protected String[] loadingType;		//Dock type, lift gate, inside delivery
  protected String[] unloadingType;
  
  //packaging type and commodity constraint
  protected String[] packagingType;
  protected String[] hazmatConstraints;
  
  //preferred lanes
  protected String[] preferredLanes;
  
  //maximum stops
  protected int maxStops;
  
  //toll roads and local congestion
  protected boolean localCongByPass;
  protected boolean takeTollRoads;
  
  public Nodes tempNext;
  public void setTruckTypeNeeded(String truckTypeNeeded) {
	this.truckTypeNeeded = truckTypeNeeded;
}


public int tempDemand;

  protected float serviceTime; 
  protected String pickupPointName;
  protected double polarAngle;
  protected double polarDistance;

  protected int custId;
  protected String roadNo;
  protected String roadName;
  protected double fromLong, fromLat; //from longitude and latitude for the road segment
  protected double toLong, toLat; //from longitude and latitude for the road segment
  protected String fromNodes, toNodes; //Nodes that are connected to the from and to points of the road segment

  /**
   * Default constructor
   */
  /*
  public Shipment(){
       }*/

  /**
   * Constructor
   * @param i index
   * @param x x-coordinate
   * @param y y-coordinate
   * @param q demand
   * @param d service time
   * @param t truck type
   * @param p pick up point name
   */
  /*
  public Shipment(int i, float x, float y, float q, float d, String t,
      String p) {
      index = i;
      xCoord = x;
      yCoord = y;
      demand = q;
      serviceTime = d;
      truckTypeNeeded = t;
      pickupPointName = p;
       }*/

  /**
   * Constructor
   * @param ind index
   * @param x x-coordinate
   * @param y y-coordinate
   * @param q demand
   * @param d service time
   * @param e frequency
   * @param comb number of combination
   * @param vComb list of combinations (vector)
   * @param cuComb number of combinations (matrix)
   */


  /* public Shipment(int ind, float x, float y, int d, int q,  int e, int comb,String t,
                  int[] vComb, int [][]cuComb)
      {

     index   = ind;
     xCoord   = x;
     yCoord  = y;
     serviceTime  = d;
     demand    = q;
     frequency = e;
     noComb   = comb;
     truckTypeNeeded = t;
     visitComb = vComb;
     currentComb = cuComb;
     isAssigned = false;
     next = null;

     //the combinations to be created should not exceed the maximum allowable
     //combination
     for (int i = 0; i < noComb; i++){
        visitComb[i] = vComb[i];
     }
      }
   public Shipment(int ind, int x, int y, int d,int q,  int e, int comb,String t,
                int[] vComb, int [][]cuComb)
    {

   index   = ind;
   xCoord   = x;
   yCoord  = y;
   serviceTime  = d;
   demand    = q;
   frequency = e;
   noComb   = comb;
   truckTypeNeeded = t;
   visitComb = vComb;
   currentComb = cuComb;
   isAssigned = false;
   next = null;

   //the combinations to be created should not exceed the maximum allowable
   //combination
   for (int i = 0; i < noComb; i++){
      visitComb[i] = vComb[i];
   }
    }*/


  /**
   * Returns if the shipment has been assigned or not
   * @return true- assigned, false- not assigned
   */
  public boolean isAssigned() {
    return isAssigned;
  }

  /**
   * Returns if the shipment has been selected or not
   * @return true- assigned, false- not assigned
   */
  public boolean getSelected() {
    return isSelected;
  }

  /**
   * Sets the shipment as selected or not
   * @param is true- assigned, false- not assigned
   */
  public void setSelected(boolean is) {
    isSelected = is;
  }

  /**
   * Sets the shipment to be assigned or not
   * @param is true- assigned, false- not assigned
   */
  public void setAssigned(boolean is) {
    isAssigned = is;
  }

  /**
   * Returns the previous shipment in the shipment linked list
   * @return previous shipment
   */
  public Shipment getPrev() {
    return prev;
  }

  /**
   * Returns the next shipment in the shipment linked list
   * @return next shipment
   */
  public Shipment getNext() {
    return next;
  }

  /**
   * Sets the previous shipment in the shipment linked list
   * @param s new previous shipment
   */
  public void setPrev(Shipment s) {
    prev = s;
  }

  /**
   * Sets the next shipment in the shipment linked list
   * @param s new next shipment
   */
  public void setNext(Shipment s) {
    next = s;
  }

  /**
   * Returns the shipment's index
   * @return shipment index
   */
	public int getIndex() {
    return index;
  }

  /**
   * Returns the shipment's x-coordinate
   * @return x-coordinate
   */
  public double getXCoord() {
    return xCoord;
  }

  /**
   * Returns the shipment's y-coordinate
   * @return y-coordinate
   */
  public double getYCoord() {
    return yCoord;
  }

  /**
   * Returns the demand of this shipment
   * @return demand
   */
  public int getDemand() {
    return (int) demand;
  }

  /**
   * Sets the demand of this shipment
   * @param d new demand
   */
  public void setDemand(int d) {
    demand = d;
  }

  /**
   * Returns the truck type needed for this shipment
   * @return needed truck type
   */
  public String getTruckTypeNeeded() {
    return truckTypeNeeded;
  }

  /**
   * Returns the pickup point name for the shipment
   *
   */
  public String getPickupPointName() {
    return pickupPointName;
  }

  /**
   * Returns the road segments toLat coordinate
   * @return toLat
   */
  public double getToLatCoord() {
    return toLat;
  }

  public void setToLatCoord(double toLatCoord)
  {
	  toLat = toLatCoord;
  }
  
  public double getToLongCoord() {
	    return toLong;
  }
  
  public void setToLongCoord(double toLongCoord)
  {
	  toLong = toLongCoord;
  }
  /**
   * Returns the road segments fromLat coordinate
   * @return toLat
   */
  public double getFromLatCoord() {
    return fromLat;
  }
  
  public void setFromLatCoord(double fromLatCoord)
  {
	  fromLat = fromLatCoord;
  }

  /**
   * Returns the road segments fromLong coordinate
   * @return toLat
   */
  public double getFromLongCoord() {
    return fromLong;
  }
  
  public void setFromLongCoord(double fromLongCoord)
  {
	  fromLong = fromLongCoord;
  }

  /**
   * Returns the nodes connected to the from side of the road segment
   * @return toLat
   */
  public String getFromNodes() {
    return fromNodes;
  }

  /**
  * Returns the nodes connected to the to side of the road segment
  * @return toLat
  */
 public String getToNodes() {
   return toNodes;
 }

  /**
  * Get the customer id
  * @return String
  */
 public int getCustId() {
   return custId;
 }

 /**
  * Get the road no
  * @return String
  */
 public String getRoadNo() {
   return roadNo;
 }

 /**
  * Get the road name
  * @return String
  */
 
 public void setHazmat(String[] haz)
 {
	 hazmatConstraints = haz;
 }
 
 public void setLoadingTime(float load)
 {
	 loadTime = load;
 }
 
 public void setUnLoadingTime(float load)
 {
	 unloadTime = load;
 }
 
 public void setUnloadingRate(float rate)
 {
	 unloadingRate = rate;
 }
 
 public void setLoadingRate(float rate)
 {
	 loadingRate = rate;
 }
 
 public void setMaxStops(int max)
 {
	 maxStops = max;
 }
 
 public void setPrefCarriers(String[] pref)
 {
	 prefCarriers = pref;
 }
 
 public void setTollRoad(boolean tolls)
 {
	 takeTollRoads = tolls;
 }
 
 public void setLocalBypass(boolean bypass)
 {
	 localCongByPass = bypass;
 }
 
 public void setUnloadType(String[] type)
 {
	 unloadingType = type;
 }
 
 public void setLoadType(String[] type)
 {
	 loadingType = type;
 }
 
 public void setTrailerType(String[] type)
 {
	 trailerType = type;
 }
 
 public String getRoadName() {
   return roadName;
 }


  /**
   * Returns a formatted string of the shipment's information
   * @return shipment string
   */
  /*public String toString() {
    DecimalFormat df = new DecimalFormat("000");

    return "#" + df.format(index) + " pup=" + pickUpPointName + " q=" +
        demand + " (" + xCoord + "," + yCoord + ") serv=" + serviceTime +
        " truck=" + truckTypeNeeded + " assigned=" + isAssigned;
     }
   */
  /**
   * This will make a copy of this shipment and return it. It will not create
   * a copy of the next and prev links, this is the responsibility of the
   * shipment linked list clone() method. This will also not copy the temp
   * demand and temp next variables, so the route containing this shipment
   * must be fully expanded.
   * @return Object clone
   */
  public Object clone() {
    Shipment clonedShipment = new Shipment();

    clonedShipment.demand = this.demand;
    clonedShipment.index = this.index;
    clonedShipment.isAssigned = this.isAssigned;
    //clonedShipment.pickUpPointName = this.pickUpPointName;
    //clonedShipment.serviceTime = this.serviceTime;
    clonedShipment.truckTypeNeeded = this.truckTypeNeeded;
    clonedShipment.xCoord = this.xCoord;
    clonedShipment.yCoord = this.yCoord;

    return clonedShipment;
  }

  /**
   * TDisplay the shipment information
   */

  /* public void displayShipment()
   {
      int i;
      String s;

      System.out.println("Shipment number is " + index);
      System.out.println("  Vertex x is  " + xCoord);
      System.out.println("  Vertex Y is  " + yCoord);
      System.out.println("  Duration is  " + serviceTime);
      System.out.println("  Demand is    " + demand);
      System.out.println("  Frequency is " + frequency);
      System.out.println("  Number of Combinations is " + noComb);
      if (noComb > 0)
      {
         System.out.println("  The combinations are: ");

         for (i = 0; i < noComb; i++) {
            System.out.println("  " + visitComb[i]);
         }
      }
      if(noComb > 0 && currentComb!=null)
      {
         System.out.println("  Current visit Comb: ");
         for(int h=0; h<noComb;h++){
            System.out.print("  ");
            for(int k = 0;k < currentComb[h].length; k++){
              s = Integer.toString(currentComb[h][k]);
              System.out.print(s);
            }
            System.out.println(" ");
          }
          System.out.println("");
      }
    }
   */
}
