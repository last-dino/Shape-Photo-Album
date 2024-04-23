import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.controller.reader.FileProcessor;
import shapesphotoalbum.controller.reader.IDataProcessor;
import shapesphotoalbum.controller.reader.transform.*;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.ShapesPhotoAlbumModel;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ITextToActionTest {
  private IModel model;

  /**
   * Sets up the photo album instance and add a shape to it before each test method.
   */
  @BeforeEach
  void setUp() {
    model = new ShapesPhotoAlbumModel();
    String[] instruction = {"shape", "myrect", "rectangle", "200", "200", "50", "100", "255", "0", "0"};
    ITextToAction create = new CreateTransform(instruction);
    model.executeCommand(create.transformToAction());
  }

  /**
   * Tests the execution of a Create action.
   * Verifies that the shape is created as expected.
   */
  @Test
  void testExecuteCreate() {
    assertEquals("""
        Name: myrect
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a ChangeColor action.
   * Verifies that the shape's color is changed as expected.
   */
  @Test
  void testExecuteChangeColor() {
    String[] instruction = {"color", "myrect", "0", "0", "255"};
    ITextToAction color = new ColorTransform(instruction);
    model.executeCommand(color.transformToAction());
    assertEquals("""
        Name: myrect
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (0.0,0.0,255.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Move action.
   * Verifies that the shape is moved as expected.
   */
  @Test
  void testExecuteMove() {
    String[] instruction = {"move", "myrect", "300", "200"};
    ITextToAction move = new MoveTransform(instruction);
    model.executeCommand(move.transformToAction());
    assertEquals("""
        Name: myrect
        Type: rectangle
        Min corner: (300.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Resize action.
   * Verifies that the shape is resized as expected.
   */
  @Test
  void testExecuteResize() {
    String[] instruction = {"resize", "myrect", "25", "100"};
    ITextToAction resize = new ResizeTransform(instruction);
    model.executeCommand(resize.transformToAction());
    assertEquals("""
        Name: myrect
        Type: rectangle
        Min corner: (200.0,200.0), Width: 25.0, Height: 100.0, Color: (255.0,0.0,0.0)
         
        """, model.getCurrentState().toString());
  }

  /**
   * Tests the execution of a Take Snapshot action.
   * Verifies that the snapshot is taken as expected.
   */
  @Test
  void testTakeSnapshot() {
    String[] instruction = {"snapShot", "After", "first", "selfie"};
    ITextToAction snapshot = new SnapshotTransform(instruction);
    model.executeCommand(snapshot.transformToAction());

    List<ISnapshot> snapshots = model.getSnapshots();
    assertEquals(1, snapshots.size());
    String string = String.format("""
        Snapshot ID: %s
        Timestamp: %s
        Description: After first selfie
        Shape Information:
        Name: myrect
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)
        
 
        """, snapshots.get(0).getId(),
        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
    assertEquals(string, model.toString());
  }

  /**
   * Tests the execution of a Remove action.
   * Verifies that the shape is removed as expected.
   */
  @Test
  void testExecuteRemove() {
    String[] instruction = {"remove", "myrect"};
    ITextToAction remove = new RemoveTransform(instruction);
    model.executeCommand(remove.transformToAction());
    assertEquals("", model.getCurrentState().toString());
  }
}
