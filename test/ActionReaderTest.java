import org.junit.jupiter.api.Test;
import shapesphotoalbum.controller.reader.ActionReader;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.ShapesPhotoAlbumModel;
import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.shape.Color;
import shapesphotoalbum.model.shape.IShape;
import shapesphotoalbum.model.shape.Point2D;
import shapesphotoalbum.model.shape.ShapeType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for ActionReader.
 */
public class ActionReaderTest {
  IModel model = new ShapesPhotoAlbumModel();

  /**
   * Test the readInput() method of ActionReader.
   */
  @Test
  void testReadInput() {
    // Define mock input data
    String inputData = """
                shape myrect rectangle 200 200 50 100 255 0 0
                move myrect 300 200
                resize myrect 25 100
                snapshot After first selfie
                color myrect 0 0 255
                remove myrect
                """;

    // Create a mock InputStream from input data
    InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

    // Invoke readInput() with the mock InputStream
    List<IAction> actions = ActionReader.readInput(inputStream);

    // Verify the number of actions generated
    assertEquals(6, actions.size());

    // Execute each action on the model
    for (IAction action : actions)
      action.execute(model);

    // Verify the number of snapshots created
    List<ISnapshot> snapshots = model.getSnapshots();
    assertEquals(1, snapshots.size());
    // Check the details of the generated snapshot
    assertEquals("After first selfie", snapshots.get(0).getDescription());

    // Check the content of the snapshot
    List<IShape> content = snapshots.get(0).getContent();
    assertEquals(1, content.size());
    // Check the properties of the shape in the snapshot
    assertEquals("myrect", content.get(0).getName());
    assertEquals(ShapeType.RECTANGLE, content.get(0).getType());
    assertEquals(new Point2D(300, 200), content.get(0).getPosition());
    assertEquals(25, content.get(0).getHorizontalDimension());
    assertEquals(new Color(255, 0, 0), content.get(0).getColor());
  }
}
