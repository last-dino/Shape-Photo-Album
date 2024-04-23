import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.model.shape.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Tests for the {@link IShape} interface.
 */
public class IShapeTest {
  private IShape shape;

  /**
   * Sets up a Shape object before each test.
   */
  @BeforeEach
  void setUp() {
    shape = new Rectangle("TestShape", new Point2D(100, 100),
        50, 50, new Color(1, 0, 0));
  }

  /**
   * Tests the getName() method.
   */
  @Test
  void testGetName() {
    assertEquals("TestShape", shape.getName());
  }

  /**
   * Tests the getPosition() method.
   */
  @Test
  void testGetPosition() {
    assertEquals(new Point2D(100, 100), shape.getPosition());
  }

  /**
   * Tests the getColor() method.
   */
  @Test
  void testGetColor() {
    assertEquals(new Color(1, 0, 0), shape.getColor());
  }

  /**
   * Tests the setHorizontalDimension() and getHorizontalDimension() methods.
   */
  @Test
  void testSetAndGetHorizontalDimension() {
    shape.setHorizontalDimension(60);
    assertEquals(60, shape.getHorizontalDimension());
  }

  /**
   * Tests the setVerticalDimension() and getVerticalDimension() methods.
   */
  @Test
  void testSetAndGetVerticalDimension() {
    shape.setVerticalDimension(60);
    assertEquals(60, shape.getVerticalDimension());
  }

  /**
   * Tests the copy() method.
   */
  @Test
  void testCopy() {
    IShape copiedShape = shape.copy();
    assertNotSame(shape, copiedShape);
    assertEquals(shape.getName(), copiedShape.getName());
    assertEquals(shape.getPosition(), copiedShape.getPosition());
    assertEquals(shape.getHorizontalDimension(), copiedShape.getHorizontalDimension());
    assertEquals(shape.getVerticalDimension(), copiedShape.getVerticalDimension());
    assertEquals(shape.getColor(), copiedShape.getColor());
  }
}