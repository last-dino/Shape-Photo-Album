package shapesphotoalbum.model.shape;

/**
 * Represents the types of shapes in the photo album.
 */
public enum ShapeType {
  RECTANGLE("rectangle"),
  OVAL("oval");

  private String name;

  ShapeType(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }
}
