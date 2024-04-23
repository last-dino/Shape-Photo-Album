package shapesphotoalbum.view;

import shapesphotoalbum.controller.ICommandDelegate;
import shapesphotoalbum.model.ISnapshot;

/**
 * The interface extends {@link IView} and represents a graphical view
 * for the Shapes Photo Album application.
 * It defines methods specific to graphical views.
 */
public interface IGraphicalView extends IView {
  /**
   * Displays information about the current snapshot in the graphical view.
   *
   * @param info the information to display.
   */
  void displayInfo(String info);

  /**
   * Displays a snapshot in the graphical view.
   *
   * @param snapshot the snapshot to display.
   */
  void displaySnapshot(ISnapshot snapshot);

  /**
   * Adds button reactors to the graphical view based on the specified
   * command delegate.
   *
   * @param delegate the command delegate used to handle button actions.
   */
  void addButtonReactors(ICommandDelegate delegate);

  /**
   * Shows a pop-up window with the specified message.
   *
   * @param message the message to display in the pop-up window.
   */
  void showPopUpWindow(String message);

  /**
   * Adds a snapshot to the graphical view's menu.
   *
   * @param snapshotId the ID of the snapshot to add to the menu.
   */
  void addSnapshotToMenu(String snapshotId);
}
