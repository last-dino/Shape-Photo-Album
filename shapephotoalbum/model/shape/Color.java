package shapesphotoalbum.model.shape;

import java.util.Objects;

/**
 * Represents a color with RGB components.
 */
public class Color {
  int R;
  int G;
  int B;

  /**
   * Constructs a new Color object with the specified RGB components.
   * @param R The red component of the color.
   * @param G The green component of the color.
   * @param B The blue component of the color.
   */
  public Color(int R, int G, int B) {
    this.R = R;
    this.G = G;
    this.B = B;
  }

  /**
   * Constructs a new Color object by copying another Color object.
   * @param original The original Color object to copy.
   * @throws IllegalArgumentException if the original Color object is null.
   */
  public Color(Color original) {
    if (original == null)
      throw new IllegalArgumentException("No color instance to be copied");

    this.R = original.R;
    this.G = original.G;
    this.B = original.B;
  }

  /**
   * Retrieves the red component of the color.
   * @return The red component of the color.
   */
  public int getR() {
    return R;
  }

  /**
   * Retrieves the green component of the color.
   * @return The green component of the color.
   */
  public int getG() {
    return G;
  }

  /**
   * Retrieves the blue component of the color.
   * @return The blue component of the color.
   */
  public int getB() {
    return B;
  }

  /**
   * Sets the color to the specified RGB components.
   * @param R The red component to set.
   * @param G The green component to set.
   * @param B The blue component to set.
   */
  public void setColor(int R, int G, int B) {
    this.R = R;
    this.G = G;
    this.B = B;
  }

  /**
   * Checks if the color is equal to another object.
   * @param o The object to compare.
   * @return True if the colors are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Color)) return false;
    Color color = (Color) o;
    return Double.compare(getR(), color.getR()) == 0 && Double.compare(getG(), color.getG()) == 0
        && Double.compare(getB(), color.getB()) == 0;
  }

  /**
   * Generates a hash code for the color.
   * @return The hash code of the color.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }

  /**
   * Generates a string representation of the color.
   * @return A string representation of the color in the format "(R,G,B)".
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)",
        (double) R, (double) G, (double) B);
  }
}
