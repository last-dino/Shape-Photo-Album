import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.Snapshot;
import shapesphotoalbum.model.shape.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link ISnapshot} interface.
 */
public class ISnapshotTest {
  private ISnapshot s1;
  private ISnapshot s2;

  // Mock content
  List<IShape> content;
  IShape R = new Rectangle("R", new Point2D(200, 200), 50, 100, new Color(1, 0, 0));
  IShape O = new Oval("O", new Point2D(500, 100), 60, 30, new Color(0, 0, 1));

  /**
   * Sets up the snapshot instance before each test method.
   */
  @BeforeEach
  void setUp() {
    content = new ArrayList<>();
    content.add(R);
    content.add(O);
    s1 = new Snapshot("123", "Timestamp", "", content);
    s2 = new Snapshot("456", "Future", "snapshot with description", content);
  }

  /**
   * Tests invalid constructor inputs.
   */
  @Test
  void testInvalidConstructor() {
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot("", "Timestamp", "", content));

    assertEquals("Invalid input for constructing a snapshot", exception1.getMessage());

    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot(null, "Timestamp", "", content));

    assertEquals("Invalid input for constructing a snapshot", exception2.getMessage());

    IllegalArgumentException exception3 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot("123", "", "", content));

    assertEquals("Invalid input for constructing a snapshot", exception3.getMessage());

    IllegalArgumentException exception4 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot("123", null, "description", content));

    assertEquals("Invalid input for constructing a snapshot", exception4.getMessage());

    IllegalArgumentException exception5 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot("123", "Timestamp", "description", null));

    assertEquals("Invalid input for constructing a snapshot", exception5.getMessage());

    // Copy constructor pass in null
    IllegalArgumentException exception6 =
        assertThrows(IllegalArgumentException.class, () ->
            new Snapshot(null));

    assertEquals("No snapshot to be copied", exception6.getMessage());
  }

  /**
   * Tests copying a snapshot.
   */
  @Test
  void testCopyAndCopyConstructor() {
    ISnapshot copy = s1.copy();
    assertEquals(copy, s1);

    copy = s2.copy();
    assertEquals(copy, s2);
  }

  /**
   * Tests getting the content of a snapshot.
   */
  @Test
  void testGetContent() {
    List<IShape> copy = s1.getContent();
    assertEquals(copy, s1.getContent());
    assertNotSame(copy, s1.getContent()); // test the returned is a copy
  }

  /**
   * Tests equals and hashcode methods.
   */
  @Test
  void testEqualsAndHashCode() {
    // Create a snapshot with content
    List<IShape> content1 = new ArrayList<>();
    content1.add(new Rectangle("Shape1", new Point2D(100, 100), 50, 50, new Color(1, 0, 0)));
    content1.add(new Oval("Shape2", new Point2D(200, 200), 60, 40, new Color(0, 1, 0)));

    Snapshot snapshot1 = new Snapshot("123", "Timestamp", "", content1);

    // Create a snapshot with the same content
    List<IShape> content2 = new ArrayList<>();
    content2.add(new Rectangle("Shape1", new Point2D(100, 100), 50, 50, new Color(1, 0, 0)));
    content2.add(new Oval("Shape2", new Point2D(200, 200), 60, 40, new Color(0, 1, 0)));

    Snapshot snapshot2 = new Snapshot("123", "Timestamp", "", content2);

    // even though they have same information, they are created at different time
    assertNotEquals(snapshot1, snapshot2);
    assertNotEquals(snapshot1.hashCode(), snapshot2.hashCode());

    assertEquals(snapshot1, snapshot1);
    assertEquals(snapshot1.hashCode(), snapshot1.hashCode());
  }

  /**
   * Tests getting the ID and converting the snapshot to a string.
   */
  @Test
  void testGetIdAndToString() {
    String string = String.format("""
        Snapshot ID: %s
        Timestamp: Timestamp
        Description: \nShape Information:
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
                
        Name: O
        Type: oval
        Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
        
        """, s1.getId());
    assertEquals(string, s1.toString());

    String s = String.format("""
        Snapshot ID: %s
        Timestamp: Future
        Description: snapshot with description
        Shape Information:
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
                
        Name: O
        Type: oval
        Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
        
        """, s2.getId());
    assertEquals(s, s2.toString());
  }
}