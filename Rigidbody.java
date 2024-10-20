public class Rigidbody {
  private GameObject parentObject;
  private double lastV = 0;
  private double dampening;
  private double g = 9.81;
  private int fps;
  
  public Rigidbody(GameObject parentObject, double dampening, int fps) {
    this.parentObject = parentObject;
    this.dampening = dampening;
    this.fps = fps;
  }
  
  public void CalculatePhysicsTick() {
    double deltaTime = (double) 1 / (double) fps;
    double v = lastV + g * deltaTime;
    lastV = v;
    
    if (parentObject.getBoxCollider() != null) {
      BoxCollider[] collidingColliders = parentObject.getBoxCollider()
      .calculateBoxCollisions(parentObject.getBoxColliderIdex());
      
      // Handle all collisions
      for (BoxCollider collidingCollider : collidingColliders) {
        if (collidingCollider != null) {
          if (parentObject.getBoxCollider().getRotation() != 90 || 
          collidingCollider.getRotation() != 90) {
            v = -v * dampening * 4;
          } else {
            v = -v * dampening * calcuateOverlapp(
            parentObject.getBoxCollider(), 
            collidingCollider
            );
          }
          lastV = v;
          if (Math.abs(v) < 0.005) {
            v = 0;
          }
        }
      }
    }
    
    parentObject.setPosition(parentObject.getX(), 
    parentObject.getY() + v * deltaTime * 100);
  }
  
  public double calcuateOverlapp(BoxCollider collider1, BoxCollider collider2) {
    double absouteOverlapp = Double.MAX_VALUE;
    double col1Y1 = ((collider1.getHeight() / (double) 2)) + collider1.getY();
    double col1Y2 = collider1.getY() - ((collider1.getHeight() / (double) 2));
    double col2Y1 = ((collider2.getHeight() / (double) 2)) + collider2.getY();
    double col2Y2 = collider2.getY() - ((collider2.getHeight() / (double) 2));
    double[] allYs = { col1Y1, col1Y2, col2Y1, col2Y2 };
    for (int x = 0; x < 3; x++) {
      for (int y = x + 1; y < 4; y++) {
        if (Math.abs(allYs[x] - allYs[y]) < absouteOverlapp) {
          absouteOverlapp = Math.abs(allYs[x] - allYs[y]);
        } // end of if
      }
    }
    if (collider1.getY() > collider2.getY()) {
      return -absouteOverlapp;
    } else {
      return absouteOverlapp;
    } // end of if-else
  }
  
  public void setParentObject(GameObject parentObject) {
    this.parentObject = parentObject;
  }
  
  public Object getParentObject() {
    return parentObject;
  }
  
  public void setDampening(double dampening) {
    this.dampening = dampening;
  }
  
  public double getDampening() {
    return dampening;
  }
  
}
