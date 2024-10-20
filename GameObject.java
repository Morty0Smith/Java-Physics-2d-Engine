public class GameObject extends Transform{
  private Image displayImage;
  private Rigidbody rb;
  private int boxColliderIndex = -1;
  private int circleColliderIndex = -1;
  private PhysicsPlayground stage;
  private SceneObject sceneObject;
  public void setPosition(double x, double y) {
    position[0] = x;
    position[1] = y;
    if (boxColliderIndex != -1) {
      stage.GetBoxColliders()[boxColliderIndex].setPosition(x,y);;
    } // end of if
    if (circleColliderIndex != -1) {
      stage.getCircleColliders()[circleColliderIndex].setPosition(x,y);
    } // end of if
  }
  
  
  public PhysicsPlayground getStage() {
    return stage;
  }
  
  public GameObject(Image displayImage, PhysicsPlayground stage) {
    setDisplayImage(displayImage);
    this.stage = stage;
    this.sceneObject = sceneObject;
  }
  
  public Image getDisplayImage() {
    return displayImage;
  }
  
  public void addRigidbody(Rigidbody rigidbody) {
    rb = rigidbody;
  }
  
  public Rigidbody getRigidbody() {
    return rb;
  }
  
  public void addBoxCollider(int boxColliderIndex) {
    this.boxColliderIndex = boxColliderIndex;
  }
  
  public void addCircleCollider(int circleColliderIndex) {
    this.circleColliderIndex = circleColliderIndex;
  }
  
  public BoxCollider getBoxCollider() {
    if (boxColliderIndex > -1) {
      return stage.GetBoxColliders()[boxColliderIndex];
    } else {
      return null;
    } // end of if-else
  }
  
  public int getBoxColliderIdex() {
    return boxColliderIndex;
  }
  
  public CircleCollider getCircleCollider() {
    if (circleColliderIndex > -1) {
      return stage.getCircleColliders()[circleColliderIndex];
    } else {
      return null;
    } // end of if-else
  }
  
  public int getCircleColliderIndex() {
    return circleColliderIndex;
  }
  
  public SceneObject getSceneObject() {
    return sceneObject;
  }
  
  public void setSceneObject(SceneObject sceneObject) {
    this.sceneObject = sceneObject;
  }
  
  public void setDisplayImage(Image displayImage) {
    if (displayImage == null) {
      System.out.println("No display image provided");
    } // end of if
    else {
      this.displayImage = displayImage;
    } // end of if-else
  }
  
}
