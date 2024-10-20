import javafx.scene.paint.Color;

public class ArrowTransformEditor extends Transform{
  private Arrow upArrow;
  private Arrow rightArrow;
  private double cursorDiffX = -100000;
  private double cursorDiffY = -100000;
  private BoxCollider upCollider;
  private BoxCollider rightCollider;
  private boolean isVisible = false;
  private SceneObject parent;
  private int arrowLength = 120;
  private int arrowHeight = 16;
  
  public ArrowTransformEditor(SceneObject parent) {
    upArrow = new Arrow(parent.getX(),parent.getY(), 180, arrowLength,Color.rgb(242,193,46));
    rightArrow = new Arrow(parent.getX(),parent.getY(), 90, arrowLength,Color.rgb(2,73,89));
    this.parent = parent;
    setPosition(parent.getX(), parent.getY());
    setRotation(parent.getRotation());
    int halfLen = arrowLength / 2; 
    upCollider = new BoxCollider(arrowHeight,arrowLength, parent.getX(), parent.getY() - halfLen, parent.getObject(), parent.getObject().getStage());
    rightCollider = new BoxCollider(arrowLength,arrowHeight, parent.getX() + halfLen, parent.getY(), parent.getObject(), parent.getObject().getStage());
  }
  
  public void setPosition(double x, double y) {
    parent.setPosition(x,y);
    this.position[0] = x;
    this.position[1] = y;
  }
  
  public boolean checkArrowCollisions(BoxCollider cursorCollider) {
    resetDragValues();
    if (BoxCollider.calculateBoxCollision(cursorCollider,upCollider)) {
      //upArrow.setHighlighted(true);
      cursorDiffY = cursorCollider.getY() - position[1];
      return true;
    } // end of if
    else {
      //upArrow.setHighlighted(false);
    } // end of if-else
    if (BoxCollider.calculateBoxCollision(cursorCollider,rightCollider)) {
      //rightArrow.setHighlighted(true);
      cursorDiffX = cursorCollider.getX() - position[0];
      return true;
    } // end of if
    else {
      //rightArrow.setHighlighted(false);
    } // end of if-else
    return false;
  }
  
  public void handleDrag(double cursorX, double cursorY) {
    if (cursorDiffY != -100000) {
      setPosition(position[0],cursorY - cursorDiffY);
      upArrow.setPosition(position[0],cursorY - cursorDiffY);
      rightArrow.setPosition(position[0],cursorY - cursorDiffY);
    } // end of if
    if (cursorDiffX != -100000) {
      setPosition(cursorX - cursorDiffX,position[1]);
      upArrow.setPosition(cursorX- cursorDiffX,position[1]);
      rightArrow.setPosition(cursorX- cursorDiffX,position[1]);
    } // end of if
  }
  
  public void resetDragValues() {
    cursorDiffX = -100000;
    cursorDiffY = -100000;
  }
  
  public Arrow[] getArrows() {
    Arrow[] arrows = new Arrow[2];
    arrows[0] = rightArrow;
    arrows[1] = upArrow;
    return arrows;
  }
  
  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }
  
  public boolean getVisibility() {
    return isVisible;
  }
  
}
