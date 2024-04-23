package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

/**
 * Represents an action to change the color of a shape in the photo album.
 */
public class ChangeColor implements IAction {
  private final String shapeName;
  private final int R;
  private final int G;
  private final int B;

  /**
   * Constructs a new ChangeColor action with the specified parameters.
   * @param shapeName The name of the shape whose color is to be changed.
   * @param R The new red component of the color.
   * @param G The new green component of the color.
   * @param B The new blue component of the color.
   */
  public ChangeColor(String shapeName, int R, int G, int B) {
    this.shapeName = shapeName;
    this.R = R;
    this.G = G;
    this.B = B;
  }

  /**
   * Executes the change color action by changing the color of
   * the specified shape in the photo.
   * @param model The photo album in which the color of the shape is to be changed.
   */
  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo doesn't exist");

    model.getCurrentState().changeColor(shapeName, R, G, B);
  }
}
