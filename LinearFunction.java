public class LinearFunction {
  private double a;
  private double c;
  
  public LinearFunction(double a,double c) {
    this.a = a;
    this.c = c;
  }
  
  public double[] calculateIntercept(LinearFunction otherFunction) {
    double tempA = a;
    double tempC = c;
    tempA -= otherFunction.getA();
    tempC -= otherFunction.getC();
    if (tempA != 0) {
      tempC *= -1;
      double interceptX = tempC/tempA;
      double interceptY = otherFunction.getA() * interceptX + otherFunction.getC();
      return new double[] {interceptX,interceptY}; 
    } else {
      return null;
    } // end of if-else 
  }
  
  public static LinearFunction calculateAFunction(double a, double pointX, double pointY) {
    double c = pointY - a * pointX;
    return new LinearFunction(a,c); 
  }
      
  public double getA() {
    return a;
  }
      
  public double getC() {
    return c;
  }
  public void setValues(double a, double c) {
    this.a = a;
    this.c = c;
  }
}
