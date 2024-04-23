package shapesphotoalbum.controller;

import java.io.IOException;

/**
 * The IController interface represents the base interface for controllers in the shapes photo album application.
 * It defines methods that are common to all controller implementations.
 */
public interface IController {
  /**
   * Starts the controller's main operation.
   *
   * @throws IOException if an IO error occurs during execution.
   */
  void go() throws IOException;

  /**
   * Adds snapshots to the view associated with this controller.
   * This method is responsible for populating the view with snapshots.
   */
  void addSnapshotsToView();
}
