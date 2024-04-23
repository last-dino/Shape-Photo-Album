package shapesphotoalbum.model;

import shapesphotoalbum.model.command.IAction;

import java.util.List;

/**
 * Represents the interface for a Shapes Photo Album.
 * Defines methods to execute commands, retrieve the current state,
 * get snapshots, and take snapshots.
 */
public interface IModel {
  /**
   * Executes a given action on the photo album.
   * @param action The action to execute.
   */
  void executeCommand(IAction action);

  /**
   * Retrieves the current state of the photo album.
   * @return The current state.
   */
  IPhoto getCurrentState();

  /**
   * Retrieves a list of snapshots taken in the photo album.
   * @return A list of snapshots.
   */
  List<ISnapshot> getSnapshots();

  /**
   * Takes a snapshot of the current state of the photo album.
   */
  void takeSnapshot(String description);
}
