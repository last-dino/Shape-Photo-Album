package shapesphotoalbum.model;

import shapesphotoalbum.model.shape.IShape;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a snapshot of the photo album at a specific moment in time.
 */
public class Snapshot implements ISnapshot {
  private final String id;
  private final String timestamp;
  private List<IShape> content = new ArrayList<>();
  private final String description;

  /**
   * Constructs a snapshot with the specified ID, timestamp, content, and a description.
   * @param id The ID of the snapshot.
   * @param timestamp The timestamp of the snapshot.
   * @param content The content of the snapshot.
   * @param description The text description of the snapshot.
   * @throws IllegalArgumentException If one or more inputs are invalid.
   */
  public Snapshot(String id, String timestamp, String description, List<IShape> content) {
    if (id == null || id.isEmpty() || timestamp == null
        || timestamp.isEmpty() || content == null)
      throw new IllegalArgumentException("Invalid input for constructing a snapshot");

    // make sure snapshot is distinct in case two snapshots are created at same timestamp
    Random rand = new Random();
    this.id = id + rand.nextInt(100);
    this.timestamp = timestamp;
    this.description = description;
    this.content = content;
  }

  /**
   * Constructs a copy of the given snapshot.
   * @param original The snapshot to be copied.
   * @throws IllegalArgumentException If the original snapshot is null.
   */
  public Snapshot(Snapshot original) {
    if (original != null) {
      this.id = original.id;
      this.timestamp = original.timestamp;
      this.description = original.description;
      for(IShape s : original.content) {
        this.content.add(s.copy());
      }
    }
    else
      throw new IllegalArgumentException("No snapshot to be copied");
  }

  /**
   * Gets the ID of the snapshot.
   * @return The ID of the snapshot.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the timestamp of the snapshot.
   * @return The timestamp of the snapshot.
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Gets the description of the snapshot.
   * @return The description of the snapshot.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the content of the snapshot.
   * @return The content of the snapshot.
   */
  @Override
  public List<IShape> getContent() {
    List<IShape> copy = new ArrayList<>();

    for (IShape s : content)
      copy.add(s.copy());
    return copy;
  }

  /**
   * Creates a copy of the snapshot.
   * @return A copy of the snapshot.
   */
  @Override
  public ISnapshot copy() {
    return new Snapshot(this);
  }

  /**
   * Compares the snapshot to another object.
   * @param o The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Snapshot)) return false;
    Snapshot snapshot = (Snapshot) o;
    return Objects.equals(getId(), snapshot.getId())
        && Objects.equals(timestamp, snapshot.timestamp)
        && Objects.equals(getContent(), snapshot.getContent());
  }

  /**
   * Generates the hash code for the snapshot.
   * @return The hash code for the snapshot.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId(), timestamp, getContent());
  }

  /**
   * Converts the snapshot to a string representation.
   * @return The string representation of the snapshot.
   */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder(String.format("""
        Snapshot ID: %s
        Timestamp: %s
        Description: %s
        Shape Information:
        """, id, timestamp, description));
    for (IShape s : content)
      string.append(s.toString());

    return string.toString();
  }
}
