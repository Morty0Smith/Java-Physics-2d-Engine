public class SceneObject extends Transform{
  private GameObject object;
  private ArrowTransformEditor transformArrows;
  
  public SceneObject(GameObject baseObject) {
    //Copy object into new Object, so old object is safe to delete
    this.object = new GameObject(baseObject.getDisplayImage(), baseObject.getStage());
    object.setPosition(baseObject.getX(), baseObject.getY());
    object.addRigidbody(baseObject.getRigidbody());
    object.addBoxCollider(baseObject.getBoxColliderIdex());
    object.addCircleCollider(baseObject.getCircleColliderIndex());
    object.setSceneObject(this);
    
    rotation = object.getDisplayImage().getRotation() + 90;
    position[0] = object.getX();
    position[1] = object.getY();
    
    transformArrows = new ArrowTransformEditor(this);
  }
  
  public void setRotation(double rotation) {
    this.rotation = rotation;
    if (object.getBoxCollider() != null) {
      object.getBoxCollider().setRotation(rotation);
    } // end of if
    object.getDisplayImage().setRotation(rotation - 90);
  }
  public void setPosition(double x, double y) {
    position[0] = x;
    position[1] = y;
    object.setPosition(x,y);
  }
  public GameObject getObject() {
    return object;
  }
  public ArrowTransformEditor getArrowTransformEditor() {
    return transformArrows;
  }
} 
