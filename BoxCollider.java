import java.util.*;
public class BoxCollider extends Transform{
  private double width;
  private double height;
  private PhysicsPlayground stage;
  private GameObject parentObject;
  public BoxCollider(double width, double height, double x, double y, GameObject parentObject, PhysicsPlayground playground) {
    this.width = width;
    this.height = height;
    position[0] = x;
    position[1] = y;
    this.parentObject = parentObject;
    stage = playground;
  }
  
  public BoxCollider[] calculateBoxCollisions(int colliderInstance) {
    ArrayList<BoxCollider> collidingColliders = new ArrayList<>();
    
    if (stage.GetBoxColliders().length > 1) {
      for (int x = 0; x < stage.GetBoxColliders().length; x++) {
        if (x != colliderInstance) {
          BoxCollider collider = null;
          if (stage.GetBoxColliders()[x].getRotation() == 90 && 
          stage.GetBoxColliders()[colliderInstance].getRotation() == 90) {
            collider = calculateBoxCollisionNoRotation(stage.GetBoxColliders()[colliderInstance], stage.GetBoxColliders()[x]);
          } else {
            collider = calculateBoxCollisionWithRotation(stage.GetBoxColliders()[colliderInstance], stage.GetBoxColliders()[x]);
          }
          
          if (collider != null) {
            collidingColliders.add(collider);
          }
        }
      }
    }
    
    return collidingColliders.toArray(new BoxCollider[0]);
  }
  
  public static boolean calculateBoxCollision(BoxCollider collider1, BoxCollider collider2) {
    if (collider1.getRotation() == 90 && collider2.getRotation() == 90) {
      if (calculateBoxCollisionNoRotation(collider1,collider2) != null) {
        return true;
      } // end of if
    } // end of if
    else {
      if (calculateBoxCollisionWithRotation(collider1,collider2) != null) {
        return true;
      } // end of if
    } // end of if-else
    return false;
  }
  
  public static BoxCollider calculateBoxCollisionNoRotation(BoxCollider Collider1, BoxCollider Collider2) {
    double[] positon1 = {Collider1.getX(), Collider1.getY()};
    double[] half1Dimentions = {Collider1.getWidth() / 2.0, Collider1.getHeight()/ 2.0};
    double[] xVerts1 = {positon1[0] - half1Dimentions[0],positon1[0] + half1Dimentions[0],positon1[0] + half1Dimentions[0],positon1[0] - half1Dimentions[0]};
    double[] yVerts1 = {positon1[1] + half1Dimentions[1],positon1[1] + half1Dimentions[1],positon1[1] - half1Dimentions[1],positon1[1] - half1Dimentions[0]};
    double[] positon2 = {Collider2.getX(), Collider2.getY()};
    double[] half2Dimentions = {Collider2.getWidth() / 2.0, Collider2.getHeight()/ 2.0};
    double[] xVerts2 = {positon2[0] - half2Dimentions[0],positon2[0] + half2Dimentions[0],positon2[0] + half2Dimentions[0],positon2[0] - half2Dimentions[0]};
    double[] yVerts2 = {positon2[1] + half2Dimentions[1],positon2[1] + half2Dimentions[1],positon2[1] - half2Dimentions[1],positon2[1] - half2Dimentions[1]};
    if (xVerts2[2] > xVerts1[0] && xVerts2[2] < xVerts1[1] && yVerts2[2] < yVerts1[0] && yVerts2[2] > yVerts1[3] ||
    xVerts2[3] > xVerts1[0] && xVerts2[3] < xVerts1[1] && yVerts2[3] < yVerts1[1] && yVerts2[3] > yVerts1[2] ||
    xVerts2[0] > xVerts1[3] && xVerts2[0] < xVerts1[2] && yVerts2[0] < yVerts1[1] && yVerts2[0] > yVerts1[2] ||
    xVerts2[1] > xVerts1[3] && xVerts2[1] < xVerts1[2] && yVerts2[1] < yVerts1[0] && yVerts2[1] > yVerts1[3]) {
      //System.out.println("Fall 1");
      return Collider2;
    } // end of if
    if (yVerts2[3] < yVerts1[2] && yVerts2[0] > yVerts1[1]) {
      if (xVerts2[3] > xVerts1[3] && xVerts2[3] < xVerts1[2]) {
        //System.out.println("Fall 2");
        return Collider2;
      } // end of if
        else if (xVerts2[2] > xVerts1[3] && xVerts2[2] < xVerts1[2]) {
          //System.out.println("Fall 3");
          return Collider2;
        }
    } // end of if
    if (yVerts2[3] > yVerts1[2] && yVerts2[0] < yVerts1[1]) {
      if (xVerts2[3] > xVerts1[3] && xVerts2[3] < xVerts1[2]) {
        //System.out.println("Fall 4");
        return Collider2;
      } // end of if
        else if (xVerts2[2] > xVerts1[3] && xVerts2[2] < xVerts1[2]) {
          //System.out.println("Fall 5");
          return Collider2;
        }
    } // end of if
    if (xVerts2[3] < xVerts1[0] && xVerts2[2] > xVerts1[1]) {
      if (yVerts2[3] < yVerts1[0] && yVerts2[0] > yVerts1[0]) {
        //System.out.println("Fall 6");
        return Collider2;
      } // end of if
        else if (yVerts2[0] > yVerts1[3] && yVerts2[3] < yVerts1[3]) {
          //System.out.println("Fall 7");
          return Collider2;
        }
    } // end of if
    if (xVerts2[0] > xVerts1[3] && xVerts2[1] < xVerts1[2]) {
      if (yVerts2[3] < yVerts1[0] && yVerts2[0] > yVerts1[0]) {
        //System.out.println("Fall 8");
        return Collider2;
      } // end of if
        else if (yVerts2[0] < yVerts1[0] && yVerts2[3] < yVerts1[0]) {
          //System.out.println("Fall 9");
          return Collider2;
        }
    } // end of if
   
    return null;
  }
  
  public static BoxCollider calculateBoxCollisionWithRotation(BoxCollider Collider1, BoxCollider Collider2) {
    double[] position1 = {Collider1.getX(), Collider1.getY()};
    double[] half1Dimentions = {Collider1.getWidth() / 2.0, Collider1.getHeight()/ 2.0};
    double rotation1 = Collider1.getRotation() + 90;
    if((rotation1 % 90) == 0) {
      rotation1 += 0.1;
    }
    double[] widthVector1 = {half1Dimentions[0] * Math.cos(getRadian(rotation1 + 180)), half1Dimentions[0] * Math.sin(getRadian(rotation1 + 180))};
    double[] heightVector1 = {half1Dimentions[1] * Math.cos(getRadian(rotation1 -90)), half1Dimentions[1] * Math.sin(getRadian(rotation1-90))}; 
    double[] xVerts1 = {position1[0] - widthVector1[0] + heightVector1[0], position1[0] - widthVector1[0] - heightVector1[0],position1[0] + widthVector1[0] - heightVector1[0],position1[0] + widthVector1[0] + heightVector1[0]};
    double[] yVerts1 = {position1[1] - widthVector1[1] + heightVector1[1], position1[1] - widthVector1[1] - heightVector1[1],position1[1] + widthVector1[1] - heightVector1[1],position1[1] + widthVector1[1] + heightVector1[1]};
    
    double[] position2 = {Collider2.getX(), Collider2.getY()};
    double[] half2Dimentions = {Collider2.getWidth() / 2.0, Collider2.getHeight()/ 2.0};
    double rotation2 = Collider2.getRotation() + 90;
    if((rotation2 % 90) == 0) {
      rotation2 += 0.1;
    }
    double[] widthVector2 = {half2Dimentions[0] * Math.cos(getRadian(rotation2 + 180)), half2Dimentions[0] * Math.sin(getRadian(rotation2 + 180))};
    double[] heightVector2 = {half2Dimentions[1] * Math.cos(getRadian(rotation2 -90)), half2Dimentions[1] * Math.sin(getRadian(rotation2-90))}; 
    double[] xVerts2 = {position2[0] - widthVector2[0] + heightVector2[0], position2[0] - widthVector2[0] - heightVector2[0],position2[0] + widthVector2[0] - heightVector2[0],position2[0] + widthVector2[0] + heightVector2[0]};
    double[] yVerts2 = {position2[1] - widthVector2[1] + heightVector2[1], position2[1] - widthVector2[1] - heightVector2[1],position2[1] + widthVector2[1] - heightVector2[1],position2[1] + widthVector2[1] + heightVector2[1]};
    
    double widthSlope1 = (yVerts1[1] - yVerts1[2]) / (xVerts1[1] - xVerts1[2]);
    double heightSlope1 = (yVerts1[2] - yVerts1[3]) / (xVerts1[2] - xVerts1[3]);
    double widthSlope2 = (yVerts2[1] - yVerts2[2]) / (xVerts2[1] - xVerts2[2]);
    double heightSlope2 = (yVerts2[2] - yVerts2[3]) / (xVerts2[2] - xVerts2[3]);
    
    LinearFunction[] fsRec1 = {LinearFunction.calculateAFunction(widthSlope1,xVerts1[0],yVerts1[0]),
    LinearFunction.calculateAFunction(heightSlope1,xVerts1[1],yVerts1[1]),
    LinearFunction.calculateAFunction(widthSlope1,xVerts1[2],yVerts1[2]),
    LinearFunction.calculateAFunction(heightSlope1,xVerts1[3],yVerts1[3])};
    
    LinearFunction[] fsRec2 = {LinearFunction.calculateAFunction(widthSlope2,xVerts2[0],yVerts2[0]),
    LinearFunction.calculateAFunction(heightSlope2,xVerts2[1],yVerts2[1]),
    LinearFunction.calculateAFunction(widthSlope2,xVerts2[2],yVerts2[2]),
    LinearFunction.calculateAFunction(heightSlope2,xVerts2[3],yVerts2[3])};
    
    for (int x = 0; x < fsRec1.length; x++) {
      for (int y = 0; y < fsRec2.length; y++) {
        double[] interceptCoordinates = fsRec1[x].calculateIntercept(fsRec2[y]);
        if (interceptCoordinates != null) {
          int beforeIndex = x - 1;
          int afterIndex = x;
          if (beforeIndex < 0) {
            beforeIndex = 3;
          } // end of if
          if (interceptCoordinates[0] > xVerts1[afterIndex] && interceptCoordinates[0] < xVerts1[beforeIndex]) {
            //System.out.println("After :" + xVerts1[afterIndex] + ", Value: " + interceptCoordinates[0] + ", Before: " + xVerts1[beforeIndex] + ", y: " + interceptCoordinates[1]);
            return Collider2;
          } // end of if
          if (interceptCoordinates[0] < xVerts1[afterIndex] && interceptCoordinates[0] > xVerts1[beforeIndex]) {
            //System.out.println("Before :" + xVerts1[beforeIndex] + ", Value: " + interceptCoordinates[0] + ", After: " + xVerts1[afterIndex]+ ", y: " + interceptCoordinates[1]);
            return Collider2;
          } // end of if
        } // end of if
      }
    }
    return null;
  }
  
  public static double getRadian(double degrees) {
    return degrees * (Math.PI / 180);
  }
  
  public void setDimensions(double width, double height) {
    this.width = width;
    this.height = height;
  }
  
  public void setParent(GameObject parentObject) {
    this.parentObject = parentObject;
  }
  
  public double getWidth() {
    return width;
  }
  public double getHeight() {
    return height;
  }
  
  public GameObject getParentObject() {
    return parentObject;
  }
  
}
