package shapesphotoalbum.model.shape;

/**
 * Represents a shape in the photo album.
 * Implements the IShape interface.
 */
public abstract class Shape implements IShape {
  private String name;
  private Point2D position;
  private int horizontalDimension;
  private int verticalDimension;
  private Color color;

  /**
   * Constructs a new Shape object with the specified parameters.
   * @param name The name of the shape (must not be null or empty).
   * @param position The position of the shape (must not be null).
   * @param h The horizontal dimension of the shape.
   * @param v The vertical dimension of the shape.
   * @param color The color of the shape (must not be null).
   * @throws IllegalArgumentException if any of the parameters are null or invalid.
   */
  public Shape(String name, Point2D position, int h, int v, Color color) {
    if (name == null || name.isEmpty() || position == null || color == null)
      throw new IllegalArgumentException("One or more invalid inputs");

    this.name = name;
    this.position = position;
    this.horizontalDimension = h;
    this.verticalDimension = v;
    this.color = color;
  }

  /**
   * Constructs a new Shape object by copying another Shape object.
   * @param original The original Shape object to copy.
   * @throws IllegalArgumentException if the original Shape object is null.
   */
  public Shape(Shape original) {
    if (original == null)
      throw new IllegalArgumentException("No shape to be copied");

    this.name = original.name;
    this.position = new Point2D(original.position);
    this.horizontalDimension = original.horizontalDimension;
    this.verticalDimension = original.verticalDimension;
    this.color = new Color(original.color);
  }

  /**
   * Retrieves the name of the shape.
   * @return The name of the shape.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves the position of the shape.
   * @return The position of the shape.
   */
  @Override
  public Point2D getPosition() {
    return this.position;
  }

  /**
   * Retrieves the horizontal dimension of the shape.
   * @return The horizontal dimension of the shape.
   */
  @Override
  public int getHorizontalDimension() {
    return this.horizontalDimension;
  }

  /**
   * Retrieves the vertical dimension of the shape.
   * @return The vertical dimension of the shape.
   */
  @Override
  public int getVerticalDimension() {
    return this.verticalDimension;
  }

  /**
   * Retrieves the color of the shape.
   * @return The color of the shape.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Sets the horizontal dimension of the shape.
   * @param h The horizontal dimension to set.
   */
  @Override
  public void setHorizontalDimension(int h) {
    this.horizontalDimension = h;
  }

  /**
   * Sets the vertical dimension of the shape.
   * @param v The vertical dimension to set.
   */
  @Override
  public void setVerticalDimension(int v) {
    this.verticalDimension = v;
  }

  /**
   * Creates a copy of the shape.
   * @return A copy of the shape.
   */
  @Override
  public abstract IShape copy();

  /**
   * Generates a string representation of the shape.
   * @return A string representation of the shape.
   */
  @Override
  public abstract String toString();
}
