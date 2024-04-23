package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

/**
 * Represents an action to be executed on a photo in the photo album.
 */
public interface IAction {
  /**
   * Executes the action on the specified photo.
   * @param model The photo album on which the action is to be executed.
   */
  void execute(IModel model);
}
