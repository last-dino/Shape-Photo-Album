package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

/**
 * Represents an action to move a shape in the photo album.
 */
public class Move implements IAction {
  private final String shapeName;
  private final int x;
  private final int y;

  /**
   * Constructs a new Move action with the specified parameters.
   * @param shapeName The name of the shape to be moved.
   * @param x The amount to move the shape along the x-axis.
   * @param y The amount to move the shape along the y-axis.
   */
  public Move(String shapeName, int x, int y) {
    this.shapeName = shapeName;
    this.x = x;
    this.y = y;
  }

  /**
   * Executes the move action by moving the specified shape in the photo.
   * @param model The photo album in which the shape is to be moved.
   */
  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo doesn't exist");

    model.getCurrentState().moveShape(shapeName, x, y);
  }
}
