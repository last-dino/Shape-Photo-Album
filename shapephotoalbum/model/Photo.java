package shapesphotoalbum.model;

import shapesphotoalbum.model.shape.IShape;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a photo in the photo album containing shapes.
 */
public class Photo implements IPhoto {
  Map<String, IShape> content;

  /**
   * Constructs a new photo.
   */
  public Photo() {
    content= new LinkedHashMap<>();
  }

  /**
   * Constructs a copy of the given photo.
   * @param original The original photo to copy.
   */
  public Photo(Photo original) {
    if (original == null)
      this.content = new LinkedHashMap<>();
    else
      this.content = new LinkedHashMap<>(original.content);
  }

  /**
   * Adds a shape to the photo.
   * @param shape The shape to add.
   * @throws IllegalArgumentException If the shape name is already taken.
   */
  @Override
  public void addShape(IShape shape) {
    if (shape == null)
      throw new IllegalArgumentException("No shape to be added");
    if (content.containsKey(shape.getName()))
      throw new IllegalArgumentException("Shape name is already taken, please select another name");

    content.put(shape.getName(), shape);
  }

  /**
   * Moves a shape to the specified position.
   * @param shapeName The name of the shape to move.
   * @param x The x-coordinate of the new position.
   * @param y The y-coordinate of the new position.
   * @throws IllegalArgumentException If the shape to be moved doesn't exist.
   */
  @Override
  public void moveShape(String shapeName, int x, int y) {
    if (shapeName == null || !content.containsKey(shapeName))
      throw new IllegalArgumentException("Shape to be moved doesn't exist");

    content.get(shapeName).getPosition().setPosition(x, y);
  }

  /**
   * Changes the color of a shape.
   * @param shapeName The name of the shape to change color for.
   * @param R The red component of the new color.
   * @param G The green component of the new color.
   * @param B The blue component of the new color.
   * @throws IllegalArgumentException If the shape to change color for doesn't exist.
   */
  @Override
  public void changeColor(String shapeName, int R, int G, int B) {
    int colorMinValue = 0;
    int colorMaxValue = 255;

    if (shapeName == null || !content.containsKey(shapeName))
      throw new IllegalArgumentException("Shape to change color for doesn't exist");

    if (R < colorMinValue || R > colorMaxValue || G < colorMinValue || G > colorMaxValue
        || B < colorMinValue || B > colorMaxValue)
      throw new IllegalArgumentException("Color value is out of bound");

    content.get(shapeName).getColor().setColor(R, G, B);
  }

  /**
   * Resizes a shape.
   * @param shapeName The name of the shape to be resized.
   * @param h The horizontal dimension of the new size.
   * @param v The vertical dimension of the new size.
   * @throws IllegalArgumentException If the shape to be resized doesn't exist.
   */
  @Override
  public void resizeShape(String shapeName, int h, int v) {
    if (shapeName == null || !content.containsKey(shapeName))
      throw new IllegalArgumentException("Shape to be resized doesn't exist");
    content.get(shapeName).setHorizontalDimension(h);
    content.get(shapeName).setVerticalDimension(v);
  }

  /**
   * Removes a shape from the photo.
   * @param shapeName The name of the shape to be removed.
   * @throws IllegalArgumentException If the shape to be removed doesn't exist.
   */
  @Override
  public void removeShape(String shapeName) {
    if (shapeName == null || !content.containsKey(shapeName))
      throw new IllegalArgumentException("Shape to be removed doesn't exist");
    content.remove(shapeName);
  }

  /**
   * Takes a snapshot of the current state of the photo.
   * @return A snapshot of the photo.
   * @throws IllegalStateException If there is no content to be taken snapshot of.
   */
  @Override
  public ISnapshot takeSnapshot(String description) {
    if (content.isEmpty())
      throw new IllegalStateException("No content to be taken snapshot of");

    Date currentDate = new Date();
    SimpleDateFormat idFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    String id = idFormat.format(currentDate);

    SimpleDateFormat timestampFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timestamp = timestampFormat.format(currentDate);

    List<IShape> record = new ArrayList<>();
    for (IShape s : content.values())
      record.add(s.copy());

    return new Snapshot(id, timestamp, description, record);
  }

  /**
   * Creates a copy of the photo.
   * @return A copy of the photo.
   */
  @Override
  public IPhoto copy() {
    return new Photo(this);
  }

  /**
   * Checks if this photo is equal to another object.
   * @param o The object to compare.
   * @return True if the photos are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Photo)) return false;
    Photo  photo = (Photo) o;
    return Objects.equals(content.values().toString(), photo.content.values().toString());
  }

  /**
   * Generates a hash code for this photo.
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(content.values().toString());
  }

  /**
   * Returns a string representation of the photo.
   * @return A string representation of the photo.
   */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    for (IShape s : content.values())
      string.append(s.toString());
    return string.toString();
  }
}
