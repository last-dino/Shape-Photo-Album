import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.ShapesPhotoAlbumModel;
import shapesphotoalbum.model.command.*;
import shapesphotoalbum.model.shape.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the {@link IModel} interface.
 */
public class IModelTest {
  private IModel album;

  // Mock actions
  IAction createRect = new Create("R", ShapeType.RECTANGLE,
      200, 200, 50, 100, 1, 0, 0);
  IAction createOval = new Create("O", ShapeType.OVAL,
      500, 100, 60, 30, 0, 0, 1);

  IAction moveRect = new Move("R", 100, 300);
  IAction resizeRect = new Resize("R", 25, 100);
  IAction changeColorRect = new ChangeColor("R", 0, 1, 0);

  IAction moveOval = new Move("O", 500, 400);
  IAction removeRect = new Remove("R");

  /**
   * Sets up the photo album instance before each test method.
   */
  @BeforeEach
  public void setUp() {
    album = new ShapesPhotoAlbumModel();
  }

  /**
   * Tests executing commands and getting the current state of the album.
   */
  @Test
  public void testExecuteCommandAndCurrentState() {
    album.executeCommand(createRect);
    album.executeCommand(createOval);

    IShape R = new Rectangle("R",
        new Point2D(200, 200), 50, 100, new Color(1, 0, 0));
    IShape O = new Oval("O",
        new Point2D(500, 100), 60, 30, new Color(0, 0, 1));
    assertEquals(R.toString() + O, album.getCurrentState().toString());

    album.executeCommand(resizeRect);
    album.executeCommand(changeColorRect);

    R = new Rectangle("R", new Point2D(200, 200), 25, 100,
        new Color(0, 1, 0));
    assertEquals(R.toString() + O, album.getCurrentState().toString());

    album.executeCommand(moveOval);

    O = new Oval("O", new Point2D(500, 400), 60, 30, new Color(0, 0, 1));
    assertEquals(R.toString() + O, album.getCurrentState().toString());

    album.executeCommand(removeRect);

    assertEquals(O.toString(), album.getCurrentState().toString());
  }

  /**
   * Tests executing invalid commands on an empty album.
   */
  @Test
  public void testExecuteInvalidCommand() {
    // Try to execute resize on empty album
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            album.executeCommand(resizeRect));

    assertEquals("Shape to be resized doesn't exist", exception1.getMessage());

    // Try to execute move on empty album
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            album.executeCommand(moveRect));

    assertEquals("Shape to be moved doesn't exist", exception2.getMessage());

    // Try to execute change color on empty album
    IllegalArgumentException exception3 =
        assertThrows(IllegalArgumentException.class, () ->
            album.executeCommand(changeColorRect));

    assertEquals("Shape to change color for doesn't exist", exception3.getMessage());

    // Try to execute remove on empty album
    IllegalArgumentException exception4 =
        assertThrows(IllegalArgumentException.class, () ->
            album.executeCommand(removeRect));

    assertEquals("Shape to be removed doesn't exist", exception4.getMessage());

    // Try to add shape with existing name
    album.executeCommand(createRect);
    IllegalArgumentException exception5 =
        assertThrows(IllegalArgumentException.class, () ->
            album.executeCommand(createRect));

    assertEquals("Shape name is already taken, please select another name",
        exception5.getMessage());
  }

  /**
   * Tests getting snapshots of the album.
   */
  @Test
  public void testGetSnapshots() {
    album.executeCommand(createRect);
    album.executeCommand(createOval);

    // Take snapshots
    album.takeSnapshot("");

    List<ISnapshot> snapshots = album.getSnapshots();
    assertEquals(1, snapshots.size());
    List<IShape> content = snapshots.getFirst().getContent();
    IShape R = new Rectangle("R", new Point2D(200, 200), 50, 100, new Color(1, 0, 0));
    IShape O = new Oval("O", new Point2D(500, 100), 60, 30, new Color(0, 0, 1));
    assertEquals(R, content.getFirst());
    assertEquals(O, content.getLast());
  }

  /**
   * Tests getting snapshots from an empty album.
   */
  @Test
  public void testGetSnapshotsEmptyAlbum() {
    IllegalStateException exception1 =
        assertThrows(IllegalStateException.class, () ->
            album.takeSnapshot(""));

    assertEquals("No content to be taken snapshot of", exception1.getMessage());

    List<ISnapshot> snapshots = album.getSnapshots();
    assertEquals(0, snapshots.size());
  }

  /**
   * Tests adding snapshots to the album.
   */
  @Test
  public void testAddSnapshot() {
    album.executeCommand(createRect);
    album.executeCommand(createOval);

    // Take snapshots
    album.takeSnapshot("");

    List<ISnapshot> snapshots = album.getSnapshots();
    assertEquals(1, snapshots.size());

    album.executeCommand(moveRect);
    album.executeCommand(resizeRect);
    album.executeCommand(changeColorRect);

    // Take snapshots
    album.takeSnapshot("");

    album.executeCommand(moveOval);

    // Take snapshots
    album.takeSnapshot("");
    album.executeCommand(removeRect);

    // Take snapshots
    album.takeSnapshot("");

    snapshots = album.getSnapshots();
    assertEquals(4, snapshots.size());

  }

  /**
   * Tests converting the album to a string representation.
   */
  @Test
  public void testToString() {
    album.executeCommand(createRect);
    album.executeCommand(createOval);
    // Take snapshots
    album.takeSnapshot("");
    album.executeCommand(moveRect);
    album.executeCommand(resizeRect);
    album.executeCommand(changeColorRect);
    // Take snapshots
    album.takeSnapshot("");
    album.executeCommand(moveOval);
    // Take snapshots
    album.takeSnapshot("");
    album.executeCommand(removeRect);
    // Take snapshots
    album.takeSnapshot("");

    List<ISnapshot> snapshots = album.getSnapshots();
    StringBuilder expected = new StringBuilder();
    for (ISnapshot s : snapshots)
      expected.append(s.toString()).append("\n");

    assertEquals(expected.toString(), album.toString());
  }
}
