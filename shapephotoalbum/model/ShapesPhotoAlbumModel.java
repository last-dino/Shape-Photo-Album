package shapesphotoalbum.model;

import shapesphotoalbum.model.command.IAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

/**
 * Represents the model for a Shapes Photo Album.
 * Manages the state of the photo album and handles snapshots.
 */
public class ShapesPhotoAlbumModel implements IModel {
  private final IPhoto currentState; // current state of the photo album
  private final Map<String, ISnapshot> snapshots; // snapshots taken for the photo album

  /**
   * Constructs a new ShapesPhotoAlbumModel object.
   * Initializes the current state and the snapshots map.
   */
  public ShapesPhotoAlbumModel() {
    currentState = new Photo();
    snapshots = new LinkedHashMap<>();
  }

  /**
   * Executes a given action on the current state of the photo album.
   * @param action The action to execute.
   * @throws IllegalArgumentException if the action is null.
   */
  @Override
  public void executeCommand(IAction action) {
    if (action == null)
      throw new IllegalArgumentException("Not a valid action");

    action.execute(this);
  }

  /**
   * Retrieves a copy of the current state of the photo album.
   * @return A copy of the current state.
   */
  @Override
  public IPhoto getCurrentState() {
    return currentState;
  }

  /**
   * Retrieves a list of snapshots taken in the photo album.
   * @return A list of snapshots.
   */
  @Override
  public List<ISnapshot> getSnapshots() {
    List<ISnapshot> copy = new ArrayList<>();
    for (ISnapshot s : snapshots.values())
      copy.add(s.copy());

    return copy;
  }

  /**
   * Adds a snapshot of the current state to the photo album.
   */
  @Override
  public void takeSnapshot(String description) {
    ISnapshot snapshot = currentState.takeSnapshot(description);
    snapshots.put(snapshot.getId(), snapshot);
  }

  /**
   * Returns a string representation of the photo album's snapshots.
   * @return A string representation of the snapshots.
   */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    for (ISnapshot s : snapshots.values())
      string.append(s.toString()).append("\n");

    return string.toString();
  }
}
