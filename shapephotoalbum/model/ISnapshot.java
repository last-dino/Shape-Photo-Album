package shapesphotoalbum.model;

import shapesphotoalbum.model.shape.IShape;

import java.util.List;

/**
 * Represents a snapshot of the photo album at a specific moment in time.
 */
public interface ISnapshot {
  /**
   * Gets the ID of the snapshot.
   * @return The ID of the snapshot.
   */
  String getId();

  /**
   * Gets the timestamp of the snapshot.
   * @return The timestamp of the snapshot.
   */
  String getTimestamp();

  /**
   * Gets the description of the snapshot.
   * @return The description of the snapshot.
   */
  String getDescription();

  /**
   * Gets the content of the snapshot.
   * @return The content of the snapshot.
   */
  List<IShape> getContent();

  /**
   * Creates a copy of the snapshot.
   * @return A copy of the snapshot.
   */
  ISnapshot copy();
}
