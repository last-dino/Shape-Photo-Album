package shapesphotoalbum.model.shape;

/**
 * Represents the interface for a shape in the photo album.
 * Defines methods to retrieve the name, position, color, dimensions, and create a copy of the shape.
 */
public interface IShape {
  /**
   * Retrieves the name of the shape.
   * @return The name of the shape.
   */
  String getName();

  /**
   * Retrieves the type of the shape.
   * @return The type of the shape.
   */
  ShapeType getType();

  /**
   * Retrieves the position of the shape.
   * @return The position of the shape.
   */
  Point2D getPosition();

  /**
   * Retrieves the horizontal dimension of the shape.
   * @return The horizontal dimension of the shape.
   */
  int getHorizontalDimension();

  /**
   * Retrieves the vertical dimension of the shape.
   * @return The vertical dimension of the shape.
   */
  int getVerticalDimension();

  /**
   * Retrieves the color of the shape.
   * @return The color of the shape.
   */
  Color getColor();

  /**
   * Sets the horizontal dimension of the shape.
   * @param h The horizontal dimension to set.
   */
  void setHorizontalDimension(int h);

  /**
   * Sets the vertical dimension of the shape.
   * @param v The vertical dimension to set.
   */
  void setVerticalDimension(int v);

  /**
   * Creates a copy of the shape.
   * @return A copy of the shape.
   */
  IShape copy();
}
