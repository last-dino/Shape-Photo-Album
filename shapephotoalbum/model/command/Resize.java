package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

/**
 * Represents an action to resize a shape in the photo album.
 */
public class Resize implements IAction {
  private final String shapeName;
  private final int horizontalDimension;
  private final int verticalDimension;

  /**
   * Constructs a new Resize action with the specified parameters.
   * @param shapeName The name of the shape to be resized.
   * @param h The new horizontal dimension of the shape.
   * @param v The new vertical dimension of the shape.
   */
  public Resize(String shapeName, int h, int v) {
    this.shapeName = shapeName;
    this.horizontalDimension = h;
    this.verticalDimension = v;
  }

  /**
   * Executes the resize action by resizing the specified shape in the photo.
   * @param model The photo album in which the shape is to be resized.
   */
  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo doesn't exist");

    model.getCurrentState().resizeShape(shapeName, horizontalDimension, verticalDimension);
  }
}
