public class CircleCollider {            
  private double radius;
  private int arrayIndex;
  private double[] position = {0,0};
  private PhysicsPlayground stage; 
  public CircleCollider(double radius, double xPosition, double yPosition, int arrayIndex, PhysicsPlayground stage) {
    this.radius = radius;
    position = new double[] {xPosition, yPosition};
    this.stage = stage;
    this.arrayIndex = arrayIndex;
  }
  
  public CircleCollider checkCircleOnCircleCollision() {
    CircleCollider[] allCircleColliders = stage.getCircleColliders();
    for (int i = 0; i < allCircleColliders.length; i++) {
      if (arrayIndex != i) {
        double[] otherPosition = {allCircleColliders[i].getX(),allCircleColliders[i].getY()};
        double xDistance = Math.abs(otherPosition[0] - getX());
        double yDistance = Math.abs(otherPosition[1] - getY());
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        double addedRadius = getRadius() + allCircleColliders[i].getRadius();
        if (addedRadius >= distance) {
          return allCircleColliders[i];
        }
      } // end of if
    }
    return null;
  }
  
  public double calulateCircleColliderOverlapp(CircleCollider otherCollider) {
    double[] otherPosition = {otherCollider.getX(),otherCollider.getY()};
    double xDistance = Math.abs(otherPosition[0] - getX());
    double yDistance = Math.abs(otherPosition[1] - getY());
    double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    double addedRadius = getRadius() + otherCollider.getRadius();
    return addedRadius - distance;
  }
  
  
  public void setRadius(double radius) {
    this.radius = radius;
  }
  public double getRadius() {
    return radius;
  }
  public double getX() {
    return position[0];
  }
  public double getY() {
    return position[1];
  }
  public void setPosition(double x, double y) {
    position[0] = x;
    position[1] = y;
  }
  public int getArrayIndex() {
    return arrayIndex;
  } 
}
