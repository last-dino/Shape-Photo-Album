package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

/**
 * Represents an action to remove a shape from the photo album.
 */
public class Remove implements IAction {
  private final String shapeName;

  /**
   * Constructs a new Remove action with the specified parameters.
   * @param shapeName The name of the shape to be removed.
   */
  public Remove(String shapeName) {
    this.shapeName = shapeName;
  }

  /**
   * Executes the remove action by removing the specified shape from the photo.
   * @param model The photo album from which the shape is to be removed.
   */
  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo doesn't exist");

    model.getCurrentState().removeShape(shapeName);
  }
}
