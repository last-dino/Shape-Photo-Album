import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.model.*;
import shapesphotoalbum.model.command.*;
import shapesphotoalbum.model.shape.ShapeType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for the {@link IAction} interface.
 */
public class IActionTest {
  private IModel model;

  /**
   * Sets up the photo album instance and add a shape to it before each test method.
   */
  @BeforeEach
  void setUp() {
    model = new ShapesPhotoAlbumModel();
    IAction add = new Create("R", ShapeType.RECTANGLE,
        200, 200, 50, 100, 1, 0, 0);
    add.execute(model);
  }

  /**
   * Tests the execution of a Create action.
   * Verifies that the shape is created as expected.
   */
  @Test
  void testExecuteCreate() {
    assertEquals("""
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a ChangeColor action.
   * Verifies that the shape's color is changed as expected.
   */
  @Test
  void testExecuteChangeColor() {
    IAction action = new ChangeColor("R", 255, 255, 255);
    action.execute(model);
    assertEquals("""
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,255.0,255.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Move action.
   * Verifies that the shape is moved as expected.
   */
  @Test
  void testExecuteMove() {
    IAction action = new Move("R", 100, 100);
    action.execute(model);
    assertEquals("""
        Name: R
        Type: rectangle
        Min corner: (100.0,100.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Resize action.
   * Verifies that the shape is resized as expected.
   */
  @Test
  void testExecuteResize() {
    IAction action = new Resize("R", 50, 50);
    action.execute(model);
    assertEquals("""
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 50.0, Color: (1.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Take Snapshot action.
   * Verifies that the snapshot is taken as expected.
   */
  @Test
  void testTakeSnapshot() {
    IAction action = new TakeSnapshot("First Snapshot");
    action.execute(model);
    List<ISnapshot> snapshots = model.getSnapshots();
    assertEquals(1, snapshots.size());
    String string = String.format("""
        Snapshot ID: %s
        Timestamp: %s
        Description: %s
        Shape Information:
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
        
 
        """, snapshots.get(0).getId(),
        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()),
        snapshots.get(0).getDescription());
    assertEquals(string, model.toString());
  }

  /**
   * Tests the execution of a Remove action.
   * Verifies that the shape is removed as expected.
   */
  @Test
  void testExecuteRemove() {
    IAction action = new Remove("R");
    action.execute(model);
    assertEquals("", model.getCurrentState().toString());
  }
}