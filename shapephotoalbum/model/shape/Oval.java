package shapesphotoalbum.model.shape;

import java.util.Objects;

public class Oval extends Shape {
  public Oval(String name, Point2D position, int h, int v, Color color) {
    super(name, position, h, v, color);
  }

  public Oval(Oval original) {
    super(original);
  }

  /**
   * Retrieves the type of the oval.
   * @return The type of the oval.
   */
  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }

  /**
   * Checks if the shape is equal to another object.
   * @param o The object to compare.
   * @return True if the shapes are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Shape)) return false;
    Shape shape = (Shape) o;
    return getHorizontalDimension() == shape.getHorizontalDimension()
        && getVerticalDimension() == shape.getVerticalDimension()
        && Objects.equals(getName(), shape.getName())&& Objects.equals(getPosition(),
        shape.getPosition()) && Objects.equals(getColor(), shape.getColor());
  }

  /**
   * Generates a hash code for the shape.
   * @return The hash code of the shape.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPosition(),getHorizontalDimension(),
        getVerticalDimension(), getColor());
  }

  /**
   * Creates a copy of the oval.
   * @return A copy of the oval.
   */
  @Override
  public IShape copy() {
    return new Oval(this);
  }

  /**
   * Generates a string representation of the rectangle.
   * @return A string representation of the rectangle.
   */
  @Override
  public String toString() {
    String string = String.format("Name: %s\nType: oval\n", getName());

    string += String.format("Center: %s, X radius: %.1f, Y radius: %.1f, Color: %s\n\n",
        getPosition().toString(), (double)this.getHorizontalDimension(), (double)getVerticalDimension(), getColor().toString());

    return string;
  }
}
