package shapesphotoalbum.model.shape;

import java.util.Objects;

/**
 * Represents a two-dimensional point with coordinates (x, y).
 */
public class Point2D {
  private int x, y;

  /**
   * Constructs a new Point2D object with the specified coordinates.
   * @param x The x-coordinate of the point.
   * @param y The y-coordinate of the point.
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Constructs a new Point2D object by copying another Point2D object.
   * @param original The original Point2D object to copy.
   */
  public Point2D(Point2D original) {
    if (original == null)
      throw new IllegalArgumentException("No point to be copied");

    this.x = original.x;
    this.y = original.y;
  }

  /**
   * Retrieves the x-coordinate of the point.
   *
   * @return The x-coordinate of the point.
   */
  public int getX() {
    return x;
  }

  /**
   * Retrieves the y-coordinate of the point.
   *
   * @return The y-coordinate of the point.
   */
  public int getY() {
    return y;
  }

  /**
   * Sets the position of the point to the specified coordinates.
   * @param x The x-coordinate to set.
   * @param y The y-coordinate to set.
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Checks if the point is equal to another object.
   * @param o The object to compare.
   * @return True if the points are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point2D)) return false;
    Point2D point2D = (Point2D) o;
    return getX() == point2D.getX() && getY() == point2D.getY();
  }

  /**
   * Generates a hash code for the point.
   * @return The hash code of the point.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }

  /**
   * Generates a string representation of the point.
   * @return A string representation of the point in the format "(x,y)".
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", (double) x, (double) y);
  }
}