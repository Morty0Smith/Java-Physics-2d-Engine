import javafx.scene.paint.Color;   
public class Arrow extends Transform{
  private Image arrowImage;
  private boolean isHighlighted = false;
  private int lenght;
  private Color baseColor;
  
  public Arrow(double posX, double posY, double rotation, int lenght, Color baseColor) {
    setPosition(posX,posY);
    this.baseColor = baseColor;
    Color[][] arrowColor = new Color[lenght * 2][16];
    //Create arrow image
    boolean[][] arrowTransparency = new boolean[arrowColor.length][arrowColor[0].length];
    int yMid = (arrowColor[0].length / 2);
    int xLen = arrowColor.length;
    int yLen = arrowColor[0].length;
    for (int x = 0; x < xLen; x++) {
      for (int y = 0; y < yLen; y++) {
        arrowTransparency[x][y] = false;
        arrowColor[x][y] = Color.WHITE;
      }
    }
    for (int x = lenght - 3; x < xLen - yMid; x++) {
      for (int y = (yMid / 2) + ((int)(yLen) / 10) + 1 - 2; y < yLen - (yMid - 2) + 2; y++) {
        arrowTransparency[x][y] = true;
        arrowColor[x][y] = baseColor;
      }
    }
    for (int x = xLen - yMid; x < xLen; x++) {
      for (int y = 0; y < yLen; y++) {
        int transX = x - (xLen - yMid);
        if (y <= (yLen - 1 - transX) && y >= transX) {
          arrowTransparency[x][y] = true;
          arrowColor[x][y] = baseColor;
        } // end of if
      }
    }
    arrowImage = new Image(arrowColor,arrowTransparency);
    this.arrowImage = arrowImage;
    setRotation(rotation);
  }
  
  public void setHighlighted(boolean isHighlighted) {
    this.isHighlighted = isHighlighted;
  }
  
  public void setRotation(double rotation) {
    this.rotation = rotation;
    arrowImage.setRotation(rotation - 90);
  }
  
  public Image getArrowImage() { 
    if (isHighlighted) {
      return arrowImage;
    } // end of if
    else {
      return arrowImage;
    } // end of if-else
  }
  
}
