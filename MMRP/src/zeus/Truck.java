package zeus;

/**
 * Maintains information about the trucks
 * <p>Title: Truck</p>
 * <p>Description: Maintains information about the trucks</p>
 * <p>Truck Operations:
 * - truck1.equals(truck2); // will compare truck types to see if the same
 * - truck1 == truck2; // will do bit comparason to see if trucks are identical
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class Truck
    implements java.io.Serializable, java.lang.Cloneable {
  private Truck prev;
  private Truck next;
  protected int truckNum;
  protected TruckType truckType;
  protected double depotX;
  protected double depotY;
  protected NodesLinkedList mainNodes;
  //protected RoadSegmentsLinkedList mainRoadSegments;
  public Attributes attributes;

  /**
   * Default Constructor
   */
  public Truck() {

  }

  /**
   * Constructor
   * @param tt truck type
   * @param depotX depot's x coordinate
   * @param depotY depot's y coordinate
   */
  public Truck(TruckType tt, float depX, float depY) {
    attributes = new Attributes();
    depotX = depX;
    depotY = depY;
    //truckNum = ProblemInfo.numTrucks++;
    truckType = tt;
    //mainNodes = new NodesLinkedList(truckType, depotX, depotY,truckNum);
  }

  /**
   * Returns the previous truck in the linked list
   * @return previous truck
   */
  public Truck getPrev() {
    return prev;
  }

  /**
   * Returns the next truck in the linked list
   * @return next truck
   */
  public Truck getNext() {
    return next;
  }

  /**
   * Sets the previous truck in the linked list
   * @param t new previous truck
   */
  public void setPrev(Truck t) {
    prev = t;
  }

  /**
   * Sets the next truck in the linked list
   * @param t new next truck
   */
  public void setNext(Truck t) {
    next = t;
  }

  /**
   * Returns the visit nodes linked list (route)
   * @return route
   */
  public NodesLinkedList getMainNodes() {
    return mainNodes;
  }

  /**
   * Restores the node linked list
   * @param nll NodesLinkedList new nodes linked list
   */
  public void setMainNodes(NodesLinkedList nll) {
    mainNodes = nll;
  }

  /*
 public RoadSegmentsLinkedList getMainRoadSegments() {
   return mainRoadSegments;
 }

 public void setMainRoadSegments (RoadSegmentsLinkedList rsll) {
   mainRoadSegments = rsll;
 }

 */
  
  /**
   * Returns this truck's number
   * @return truck number
   */
  public int getTruckNum() {
    return truckNum;
  }

  /**
   * Returns this truck's type
   * @return truck type
   */
  public TruckType getTruckType() {
    return truckType;
  }

  public void setTruckType(TruckType newType)
  {
	  truckType = newType;
  }
  /**
   * This will return if the two trucks are of the same type
   * @param otherTruck truck to compare to this type
   * @return true - same type, false - different types
   *
   * @todo  --- this method needs implemented --- in the inheriting class
   */
  public boolean equals(Truck otherTruck) {
    return otherTruck.getTruckType().getServiceType().equals(this.getTruckType()
        .getServiceType());
  }

  /**
   * Returns whether a truck is compatable with a shipment
   * @param aShip shipment to compare
   * @return true- ship is ok, false- truck can't service this
   */
  public boolean compatableWith(Shipment aShip) {
    return aShip.getTruckTypeNeeded().equals(truckType.getServiceType());
  }

  /**
   * Returns whether or not the bus is empty
   * @return true - the bus is empty, false - the bus is not empty
   */
  public boolean isEmpty() {
    if ( (mainNodes == null) || (mainNodes.getSize() <= 2)) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * String evaluation of truck
   * @return truck's string
   */
  public String toString() {
    //String s = "#" + truckNum + " " + truckType.getServiceType() +
    //    " Max Q=";
    String s = "#" + truckNum + " " +
        " Max Q=";
  /*  if (truckType.getMaxCapacity() == Integer.MAX_VALUE) {
      s += " \u221E"; //univode for infinity
    }
    else {
      s += truckType.getMaxCapacity();
    }

    s += " Max D=";

    if (truckType.getMaxDuration() == Integer.MAX_VALUE) {
      s += " \u221E"; //unicode for infinity
    }
    else {
      s += truckType.getMaxDuration();
    }

    s += (" " + attributes);*/

    return s;
  }

  /**
   * Creates a clone of the current trucks. Does not create the next and prev
   * links, that is the responsibility of the truck linked list clone() function
   * @return Object truck clone
   */
  public Object clone() {
    Truck clonedTruck = new Truck();

    clonedTruck.attributes = (Attributes)this.attributes.clone();
    clonedTruck.depotX = this.depotX;
    clonedTruck.depotY = this.depotY;
    clonedTruck.mainNodes = (NodesLinkedList)this.mainNodes.clone();
    clonedTruck.truckNum = this.truckNum;
    clonedTruck.truckType = (TruckType)this.truckType.clone();

    return clonedTruck;
  }

  /**
   *
   * @param depotXVal double
   */
  public void setDepotX(double depotXVal) {
    depotX = depotXVal;
  }

  /**
   *
   * @return double
   */
    public double getDepotX() {
    return depotX;
  }

  /**
   *
   * @param depotYVal double
   */
    public void setDepotY(double depotYVal) {
    depotY = depotYVal;
  }

  /**
   *
   * @return double
   */
    public double getDepotY() {
    return depotY;
  }


}
