package zeus;

//import the parent class
import zeus.TruckType;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class VRPTruckType
    extends TruckType
    implements java.io.Serializable, java.lang.Cloneable {
  public VRPTruckType() {
  }

  /**
   * Constructor
   * @param N type number
   * @param D max duration
   * @param Q max capacity
   * @param s type of customers the truck can service
   */
  public VRPTruckType(int N, float D, float Q, String s) {
    truckNo = N;
    serviceType = s;

    if (D == 0) {
      maxDuration = Integer.MAX_VALUE;
    }
    else {
      maxDuration = D;
    }

    if (Q == 0) {
      maxCapacity = Integer.MAX_VALUE;
    }
    else {
      maxCapacity = Q;
    }

    fixedCost = maxCapacity;
    variableCost = (double) maxCapacity / 1000;
  }

}
