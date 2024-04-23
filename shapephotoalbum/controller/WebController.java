package shapesphotoalbum.controller;

import shapesphotoalbum.controller.reader.ActionReader;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.view.IWebView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * The WebController class is responsible for controlling the web-based view of the photo album.
 * It initializes the model, view, input file, and output file to display snapshots on a web page.
 */
public class WebController implements IController {
  IModel model;
  IWebView view;
  InputStream in;
  String out;
  List<ISnapshot> snapshots;

  /**
   * Constructs a WebController with the specified model, web view, input file, and output file.
   *
   * @param model the model to be used for processing commands and managing snapshots.
   * @param view the web view interface used to display the photo album.
   * @param in the input file containing commands to initialize the photo album.
   * @param out the output file path for storing results or logs.
   * @throws FileNotFoundException if the specified input file is not found.
   */
  public WebController(IModel model, IWebView view, InputStream in, String out) throws FileNotFoundException {
    this.model = model;
    this.view = view;
    this.in = in;
    this.out = out;

    // Read input commands and execute them on the model
    List<IAction> actions = ActionReader.readInput(in);
    for (IAction action : actions)
      model.executeCommand(action);

    // Retrieve snapshots from the model after executing commands
    this.snapshots = model.getSnapshots();
  }

  /**
   * Starts the photo album display on the web page.
   * Displays snapshots in the view if available.
   */
  @Override
  public void go() {
    if (snapshots.isEmpty()) {
      System.out.println("No snapshots in this album.");
      System.exit(0);
    }

    // Add snapshots to the web view
    this.addSnapshotsToView();
    // Display the web view window
    this.view.displayWindow();
  }

  /**
   * Adds snapshots to the web view.
   * Iterates through the list of snapshots and adds each snapshot to the view.
   */
  @Override
  public void addSnapshotsToView() {
    for (ISnapshot snapshot : snapshots) {
      this.view.addSnapshot(snapshot);
    }
  }
}
