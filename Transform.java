public abstract class Transform{
  protected double rotation = 90;
  protected double[] position = new double[2];
  protected double scale = 1;
  
  public void setRotation(double rotation) {
    this.rotation = rotation;
  }
  public void setPosition(double x, double y) {
    position[0] = x;
    position[1] = y;
  }
  public void setScale(double scale) {
    this.scale = scale;
  }
  public double getRotation() {
    return rotation;
  }
  public double getX() {
    return position[0];
  }
  public double getY() {
    return position[1];
  }
  public double[] getPosition() {
    return position;
  }
  public double getScale() {
    return scale;
  }
}
