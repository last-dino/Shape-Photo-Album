package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.shape.*;

/**
 * Represents an action to create a shape and add it to a photo in the photo album.
 */
public class Create implements IAction {
  private final IShape shape;

  /**
   * Constructs a new Create action with the specified parameters.
   * @param name The name of the shape.
   * @param type The type of the shape.
   * @param x The amount to move the shape along the x-axis.
   * @param y The amount to move the shape along the y-axis.
   * @param h The horizontal dimension of the shape.
   * @param v The vertical dimension of the shape.
   * @param R The new red component of the color.
   * @param G The new green component of the color.
   * @param B The new blue component of the color.
   */
  public Create(String name, ShapeType type, int x, int y, int h, int v, int R, int G, int B) {
    Point2D position = new Point2D(x, y);
    Color color = new Color(R, G, B);

    if (type == ShapeType.RECTANGLE)
      this.shape = new Rectangle(name, position, h, v, color);

    else if (type == ShapeType.OVAL)
      this.shape = new Oval(name, position, h, v, color);

    else
      this.shape = null;
  }

  /**
   * Executes the create action by adding the shape to the specified photo.
   * @param model The photo album to which the shape is to be added.
   */
  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo doesn't exist");

    model.getCurrentState().addShape(this.shape);
  }
}
