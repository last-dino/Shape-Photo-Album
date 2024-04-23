package shapesphotoalbum.controller;

import shapesphotoalbum.controller.reader.ActionReader;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.view.*;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * The GraphicalController class implements the controller logic for managing the graphical view of the photo album application.
 * It interacts with the model and view components to display snapshots and handle user interactions.
 */
public class GraphicalController implements IController, ICommandDelegate {
  IModel model;
  IGraphicalView view;
  InputStream in;
  int currentPage = 0;
  List<ISnapshot> snapshots;

  /**
   * Constructs a GraphicalController with the specified model, graphical view, and input file.
   *
   * @param model the model component of the photo album application.
   * @param view the graphical view component of the photo album application.
   * @param in the input file containing commands to execute.
   * @throws FileNotFoundException if the specified input file is not found.
   */
  public GraphicalController(IModel model, IGraphicalView view, InputStream in) throws FileNotFoundException {
    this.model = model;
    this.view = view;
    this.in = in;

    // Read input file and execute corresponding actions
    List<IAction> actions = ActionReader.readInput(in);
    for (IAction action : actions)
      model.executeCommand(action);
    this.snapshots = model.getSnapshots();
  }

  /**
   * Starts the controller's main operation.
   * Displays snapshots in the graphical view and handles user interactions.
   */
  @Override
  public void go() {
    if (snapshots.isEmpty()) {
      this.view.showPopUpWindow("No snapshots in this album.");
      System.exit(0);
    }

    this.addSnapshotsToView();
    view.addButtonReactors(this);
    this.view.displaySnapshot(snapshots.get(currentPage));
    this.view.displayWindow();
  }

  /**
   * Adds snapshots to the graphical view's menu.
   */
  @Override
  public void addSnapshotsToView() {
    for (ISnapshot snapshot : snapshots) {
      this.view.addSnapshotToMenu(snapshot.getId());
    }
  }

  /**
   * Displays information about the current snapshot.
   */
  @Override
  public void showInfo() {
    ISnapshot currentSnapshot = snapshots.get(currentPage);
    String info = String.format("<html>ID: %s<br>Timestamp: %s<br>Description: %s</html>",
        currentSnapshot.getId(), currentSnapshot.getTimestamp(), currentSnapshot.getDescription());
    this.view.displayInfo(info);
  }

  /**
   * Displays the next snapshot in the album.
   */
  @Override
  public void pageForward() {
    if (snapshots.size() <= currentPage + 1) {
      this.view.showPopUpWindow("End of the photo album. No snapshots to be shown beyond this one.");
      return;
    }

    this.currentPage += 1;
    this.view.displaySnapshot(snapshots.get(currentPage));
    this.showInfo();
  }

  /**
   * Displays the snapshot corresponding to the selected menu item.
   *
   * @param index the index of the selected snapshot.
   */
  @Override
  public void selectMenu(int index) {
    this.currentPage = index;
    this.view.displaySnapshot(snapshots.get(currentPage));
    this.showInfo();
  }

  /**
   * Displays the previous snapshot in the album.
   */
  @Override
  public void pageBackward() {
    if (currentPage - 1 < 0) {
      this.view.showPopUpWindow("This is the first snapshot of the photo album.");
      return;
    }

    this.currentPage -= 1;
    this.view.displaySnapshot(snapshots.get(currentPage));
    this.showInfo();
  }

  /**
   * Exits the application.
   */
  @Override
  public void quit() {
    this.view.showPopUpWindow("Goodbye! Thank you for viewing this album.");
    System.exit(0);
  }
}
