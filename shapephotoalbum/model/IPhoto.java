package shapesphotoalbum.model;

import shapesphotoalbum.model.shape.IShape;

/**
 * Represents a photo in the photo album.
 */
public interface IPhoto {
  /**
   * Adds a shape to the photo.
   * @param shape The shape to add.
   */
  void addShape(IShape shape);

  /**
   * Moves a shape to the specified position.
   * @param shapeName The name of the shape to move.
   * @param x The x-coordinate of the new position.
   * @param y The y-coordinate of the new position.
   */
  void moveShape(String shapeName, int x, int y);

  /**
   * Changes the color of a shape.
   * @param shapeName The name of the shape to change color for.
   * @param R The red component of the new color.
   * @param G The green component of the new color.
   * @param B The blue component of the new color.
   */
  void changeColor(String shapeName, int R, int G, int B);

  /**
   * Resizes a shape.
   * @param shapeName The name of the shape to be resized.
   * @param h The horizontal dimension of the new size.
   * @param v The vertical dimension of the new size.
   */
  void resizeShape(String shapeName, int h, int v);

  /**
   * Removes a shape from the photo.
   * @param shapeName The name of the shape to be removed.
   */
  void removeShape(String shapeName);

  /**
   * Takes a snapshot of the current state of the photo.
   * @return A snapshot of the photo.
   */
  ISnapshot takeSnapshot(String description);

  /**
   * Creates a copy of the photo.
   * @return A copy of the photo.
   */
  IPhoto copy();
}
