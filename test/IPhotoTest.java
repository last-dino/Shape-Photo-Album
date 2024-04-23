import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shapesphotoalbum.model.IPhoto;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.Photo;
import shapesphotoalbum.model.shape.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the {@link IPhoto} interface.
 */
public class IPhotoTest {
  private IPhoto photo;

  // Mock shape
  IShape mock = new Rectangle("TestShape",
      new Point2D(100, 100), 50, 50, new Color(1, 0, 0));

  /**
   * Sets up the photo instance before each test method.
   */
  @BeforeEach
  void setUp() {
    photo = new Photo();
  }

  /**
   * Tests adding invalid shapes to the photo.
   */
  @Test
  void testInvalidAddShape() {
    // Try to add a null object
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.addShape(null));

    assertEquals("No shape to be added", exception1.getMessage());

    // Try to add a shape with a name that is already taken
    photo.addShape(mock);
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.addShape(mock));

    assertEquals("Shape name is already taken, please select another name",
        exception2.getMessage());
  }

  /**
   * Tests moving invalid shapes in the photo.
   */
  @Test
  void testInvalidMoveShape() {
    // Try to move a null object
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.moveShape(null, 800, 0));

    assertEquals("Shape to be moved doesn't exist", exception1.getMessage());

    // Try to move shape that doesn't exist in the photo
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.moveShape(mock.getName(), 700, 600));

    assertEquals("Shape to be moved doesn't exist", exception2.getMessage());
  }

  /**
   * Tests resizing invalid shapes in the photo.
   */
  @Test
  void testResizeShape() {
    // Try to resize a null object
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.resizeShape(null, 800, 0));

    assertEquals("Shape to be resized doesn't exist", exception1.getMessage());

    // Try to resize shape that doesn't exist in the photo
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.resizeShape(mock.getName(), 700, 600));

    assertEquals("Shape to be resized doesn't exist", exception2.getMessage());
  }

  /**
   * Tests changing color for invalid shapes in the photo.
   */
  @Test
  void testChangeColor() {
    // Try to change color for a null object
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.changeColor(null, 0, 0, 0));

    assertEquals("Shape to change color for doesn't exist", exception1.getMessage());

    // Try to change color for shape that doesn't exist in the photo
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.changeColor(mock.getName(), 0, 0, 0));

    assertEquals("Shape to change color for doesn't exist", exception2.getMessage());

    // Try to set color with invalid color value
    photo.addShape(mock);
    IllegalArgumentException exception3 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.changeColor(mock.getName(), -1, 0, 4));

    assertEquals("Color value is out of bound", exception3.getMessage());

    IllegalArgumentException exception4 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.changeColor(mock.getName(), 55, 766, 255));

    assertEquals("Color value is out of bound", exception4.getMessage());
  }

  /**
   * Tests removing invalid shapes from the photo.
   */
  @Test
  void testInvalidRemoveShape() {
    // Try to remove a null object
    IllegalArgumentException exception1 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.removeShape(null));

    assertEquals("Shape to be removed doesn't exist", exception1.getMessage());

    // Try to remove shape that doesn't exist in the photo
    IllegalArgumentException exception2 =
        assertThrows(IllegalArgumentException.class, () ->
            photo.removeShape(mock.getName()));

    assertEquals("Shape to be removed doesn't exist", exception2.getMessage());
  }

  /**
   * Tests taking a snapshot of the photo.
   */
  @Test
  void testTakeSnapshot() {
    photo.addShape(mock);

    ISnapshot snapshot = photo.takeSnapshot("");

    assertNotNull(snapshot);
    List<IShape> content = snapshot.getContent();
    assertEquals(mock, content.getFirst());
  }

  /**
   * Tests taking a snapshot of an empty photo.
   */
  @Test
  void testTakeSnapshotOnEmptyPhoto() {
    IllegalStateException exception1 =
        assertThrows(IllegalStateException.class, () ->
            photo.takeSnapshot(""));

    assertEquals("No content to be taken snapshot of", exception1.getMessage());
  }

  /**
   * Tests copying the photo.
   */
  @Test
  void testCopy() {
    photo.addShape(mock);
    IPhoto copy = photo.copy();

    assertNotSame(photo, copy);
    assertEquals(photo, copy);
  }

  /**
   * Tests the equality of two photos.
   */
  @Test
  void testEqualsAndHashCode() {
    IPhoto photo1 = new Photo();
    IPhoto photo2 = new Photo();

    assertEquals(photo1, photo2);

    IShape shape1 = new Rectangle("TestShape1",
        new Point2D(100, 100), 50, 50, new Color(1, 0, 0));
    IShape shape2 = new Oval("TestShape2", new Point2D(200, 200),
        60, 40, new Color(0, 1, 0));

    photo1.addShape(shape1);
    photo2.addShape(shape1);

    assertEquals(photo1, photo2);
    assertEquals(photo1.hashCode(), photo2.hashCode());


    photo2.addShape(shape2);

    assertNotEquals(photo1, photo2);
    assertNotEquals(photo1.hashCode(), photo2.hashCode());
  }

  /**
   * Tests the string representation of a photo.
   */
  @Test
  void testToString() {
    // Add shapes to the photo
    IShape shape1 = new Rectangle("TestShape1",
        new Point2D(100, 100), 50, 50, new Color(1, 0, 0));
    IShape shape2 = new Oval("TestShape2",
        new Point2D(200, 200), 60, 40, new Color(0, 1, 0));

    photo.addShape(shape1);
    photo.addShape(shape2);

    assertEquals(shape1.toString() + shape2, photo.toString());
  }
}