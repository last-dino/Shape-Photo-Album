package shapesphotoalbum.model.shape;

import java.util.Objects;

public class Rectangle extends Shape {
  public Rectangle(String name, Point2D position, int h, int v, Color color) {
    super(name, position, h, v, color);
  }

  public Rectangle(Rectangle original) {
    super(original);
  }

  /**
   * Retrieves the type of the rectangle.
   * @return The type of the rectangle.
   */
  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
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
   * Creates a copy of the rectangle.
   * @return A copy of the rectangle.
   */
  @Override
  public IShape copy() {
    return new Rectangle(this);
  }

  /**
   * Generates a string representation of the rectangle.
   * @return A string representation of the rectangle.
   */
  @Override
  public String toString() {
    String string = String.format("Name: %s\nType: rectangle\n", getName());

    string += String.format("Min corner: %s, Width: %.1f, Height: %.1f, Color: %s\n\n",
        getPosition().toString(), (double)getHorizontalDimension(),
        (double)getVerticalDimension(), getColor().toString());

    return string;
  }
}
