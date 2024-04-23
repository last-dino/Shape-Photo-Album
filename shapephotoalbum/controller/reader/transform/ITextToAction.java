package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.IAction;

/**
 * The ITextToAction interface represents a contract for transforming textual input into an action.
 * Implementing classes will provide specific implementations to convert text-based commands into executable actions.
 */
public interface ITextToAction {
  /**
   * Transforms textual input into an action.
   * @return An action object representing the transformed textual command.
   */
  IAction transformToAction();
}
