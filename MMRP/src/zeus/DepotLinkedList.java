package zeus;

import zeus.ProblemInfo;

/**
 * Contains instances of the Depot Class
 * <p>Title: DepotLinkedList</p>
 * <p>Description: This class implements the DepotLinkedList class. The Root class creates
 * an instance of a problem, like MDVRP, class. The problem, like MDVRP, class creates an instance
 * of the DepotLinkedList class. If the problem has only one depot then the DepotLinkedList class
 * will have only one instance of the Depot class, else it will have a linked list of multple
 * instances of the Depot class. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class DepotLinkedList
    implements java.io.Serializable,
    java.lang.Cloneable {
  protected Depot head;
  protected Depot tail;
  public Attributes attributes;

  /**
   * Constructor
   */
  /* public DepotLinkedList() {
        attributes = new Attributes();
    }*/

  /**
   * Returns the first depot in the linked list
   * @return first depot
   */

  public Depot getHead() {
    return head;
  }

  /**
   * Returns the last depot in the linked list
   * @return last depot
   */
  public Depot getTail() {
    return tail;
  }

  /**
   * Attempts to insert the shipment into the depot linked list. Will loop
   * through the available depots until a depot is found that can accomodate
   * the shipment
   * @param theShipment the shipment to route
   * @return true if shipment serviced, false if not.
   */
  /*public boolean insertShipment(Shipment theShipment) {
      boolean status = false;

      Depot depot = this.getHead();

      while (depot != null) {
          status = depot.getMainTrucks().insertShipment(theShipment);

          if (status) {
              break;
          }

          depot = depot.getNext();
      }

      return status;
       }
   */

  /**
   * Inserts depot as last node in the depot linked list
   * @param depot the specified depot to be inserted
   */
//the method was previously called insertLast
  public void insertLastDepot(Depot depot) {
    Depot prevDepot;

    if (head == null) {
      head = depot;
      tail = depot;
      depot.setPrev(null);
      depot.setNext(null);
    }
    else {
      tail.setNext(depot);
      depot.setPrev(tail);
      tail = depot;
      depot.setNext(null);
    }
  }

  /**
   * Inserts a depots after another depot in the linked list
   * @param insertDepot depot to insert
   * @param beforeDepot depot to insert after
   */
  public void insertAfterDepot(Depot insertDepot, Depot beforeDepot) {
    Depot currentDepot = head;

    if (currentDepot == null) {
      head = insertDepot;
      insertDepot.setPrev(null);
      insertDepot.setNext(null);

      return;
    }

    while (currentDepot != null) {
      if (currentDepot == beforeDepot) {
        if (beforeDepot.getNext() != null) {
          beforeDepot.getNext().setPrev(insertDepot);
          insertDepot.setNext(beforeDepot.getNext());
        }
        else {
          insertDepot.setNext(null);
        }

        beforeDepot.setNext(insertDepot);
        insertDepot.setPrev(beforeDepot);

        return;
      }

      currentDepot = currentDepot.getNext();
    }
  }

  /**
   * Removes the specified depot
   * @param depot depot to remove
   * @return removed depot
   */
  public Depot removeDepot(Depot depot) {
    Depot tempDepot = head;
    Depot prevDepot = null;
    Depot nextDepot = null;

    while (tempDepot != null) {
      if (depot.getDepotNum() == tempDepot.getDepotNum()) {
        prevDepot = depot.getNext();
        nextDepot = depot.getNext();

        if (depot == head) {
          head = nextDepot;

          if (nextDepot != null) {
            nextDepot.setPrev(null);
          }

          depot.setNext(null);
          depot.setPrev(null);

          return null;
        }

        if (depot == tail) {
          tail = prevDepot;

          if (prevDepot != null) {
            prevDepot.setNext(null);
          }

          depot.setNext(null);
          depot.setPrev(null);

          return prevDepot;
        }

        if (prevDepot != null) {
          prevDepot.setNext(nextDepot);
        }

        if (nextDepot != null) {
          nextDepot.setPrev(prevDepot);
        }

        depot.setNext(null);
        depot.setPrev(null);

        return prevDepot;
      }

      tempDepot = tempDepot.getNext();
    }

    return null;
  }

  /**
   * This method loops through all depots and removes an empty truck
   */
  public void removeEmptyTrucks() {
    Depot depot = this.getHead();
    while (depot != null) {
      depot.getMainTrucks().removeEmptyTrucks();
      depot = depot.getNext();
    }
  }

  /**
   * Will print the contents of the depot linked list to the screen
   * @param out the stream to print to
   */
  public void printDepotLinkedList(java.io.PrintStream out) {

    //this.expandAllRoutes();

    out.println(ProblemInfo.numDepots);

    Depot depot = head;

    try {
      while (depot != null) {
        out.println(depot.getDepotNum() + " " + depot.getXCoord() + " " +
                    depot.getYCoord() + " " + depot.attributes.totalDemand +
                    " " +
                    depot.attributes.totalDistance + " " +
                    depot.getMainTrucks().getSize());

        Truck truck = depot.getMainTrucks().getHead();

        while (truck != null) {
          out.println(truck.getTruckNum() + " " +
                      truck.getTruckType().getServiceType() + " " +
                      truck.attributes.totalDemand + " " +
                      truck.attributes.totalDistance + " " +
                      truck.getTruckType().getMaxCapacity() + " " +
                      truck.getTruckType().getMaxDuration() + " " +
                      truck.getMainNodes().getSize());

          Nodes cell = truck.getMainNodes().getHead();

          while (cell != null) {
            out.println(cell.getIndex() + " " + cell.getDemand() + " " +
                        cell.getShipment().getXCoord() + " " +
                        cell.getShipment().getYCoord() + " " +
                        cell.getShipment().getTruckTypeNeeded());
            cell = cell.getNext();
          }

          truck = truck.getNext();
        }

        depot = depot.getNext();
      }
    }
    catch (Exception e) {
      System.out.println("Error in printDepotLinkedList"+e);
      e.printStackTrace();
    }
  }

  /**
   * find a Depot in the linked list with Depot number key
   * @param key unique id of the depot
   * @return Depot pointer to the depot with the id, else null
   */
  protected Depot find(int key) {
    Depot current = head;

    while (current.getDepotNum() != key) {
      if (current.getNext() == null) {
        return null;
      }
      else {
        current = current.getNext();
      }
    }

    return current;
  }

  /**
   * Gets the number of buses in the depots
   * @return number of buses
   */
  public int getTotalNumTrucksUsed() {
    Depot currentDepot = head;
    Truck currentTruck;
    int numBusesUsed = 0;

    //loop through all depots
    while (currentDepot != null) {
      currentTruck = currentDepot.getMainTrucks().getHead();

      //loop through all buses
      while (currentTruck != null) {
        //if bus is not empty, then increment number of used buses
        if (!currentTruck.isEmpty()) {
          numBusesUsed++;
        }

        currentTruck = currentTruck.getNext();
      }

      currentDepot = currentDepot.getNext();
    }

    return numBusesUsed;
  }

  /** @todo Need to move out the collapse and expand routes */
  /**
   * Collapse all of the bus routes for each of the depots.
   */
  /*public void collapseAllRoutes() {
    Depot currentDepot = head;
    Truck currentTruck;

    //loop through all depots
    while (currentDepot != null) {
      currentTruck = currentDepot.getMainTrucks().getHead();

      //loop through all buses
      while (currentTruck != null) {
        currentTruck.getMainNodes().collapseRoute(); //collapse bus route
        currentTruck = currentTruck.getNext();
      }

      currentDepot = currentDepot.getNext();
    }
   }*/

  /**
   * Expand all of the bus routes for each of the depots.
   */
  /* Sam public void expandAllRoutes() {
    Depot currentDepot = head;
    Truck currentTruck;

    //loop through all depots
    while (currentDepot != null) {
      currentTruck = currentDepot.getMainTrucks().getHead();

      //loop through all buses
      while (currentTruck != null) {
        currentTruck.getMainNodes().expandRoute(); //expand bus route
        currentTruck = currentTruck.getNext();
      }

      currentDepot = currentDepot.getNext();
    }
   }*/

  /**
   * Returns the number of non-empty trucks used.
   * @return non empty trucks
   */
  public int getNumTrucksUsed() {
    int numT = 0;

    Depot depot = head;

    while (depot != null) {
      Truck truck = depot.getMainTrucks().getHead();

      while (truck != null) {
        numT++;
        truck = truck.getNext();
      }

      depot = depot.getNext();
    }

    return numT;
  }

  /**
   * Returns the string that contains the current solution information for the
   * interface. Is a pipe '|' delimeted string. MUST have a pipe at the end.
   * @return solution string
   */
  public String getSolutionString() {
    return "Trucks Used = " + getTotalNumTrucksUsed() + " | " +
        attributes.toDetailedString();
  }

  /**
   * Returns an exact copy of the depot linked list
   * @return Object depot linked list copy
   */
  public Object clone() {
    DepotLinkedList clonedDepotLinkedList = new DepotLinkedList();

    clonedDepotLinkedList.attributes = (Attributes)this.attributes.clone();
    clonedDepotLinkedList.head = (Depot)this.head.clone();

    //must establish the links at this level to avoid recursing out of control
    clonedDepotLinkedList.head.setPrev(null);

    if (this.head != this.tail) {
      Depot currentDepot = clonedDepotLinkedList.head;
      Depot nextDepot = this.getHead().getNext();

      while (nextDepot != null) {
        currentDepot.setNext( (Depot) nextDepot.clone()); //create the next depot
        currentDepot.getNext().setPrev(currentDepot); //set the next depot's prev
        currentDepot = currentDepot.getNext();
        nextDepot = nextDepot.getNext();

        //once next is null, we have found the tail of the list
        if (nextDepot == null) {
          clonedDepotLinkedList.tail = currentDepot;
          currentDepot.setNext(null);
        }

      }
    }
    else { //only 1 depot
      clonedDepotLinkedList.tail = clonedDepotLinkedList.head;
    }

    return clonedDepotLinkedList;
  }
}
