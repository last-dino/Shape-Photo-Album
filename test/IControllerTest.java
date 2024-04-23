import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapesphotoalbum.controller.GraphicalController;
import shapesphotoalbum.controller.ICommandDelegate;
import shapesphotoalbum.controller.WebController;
import shapesphotoalbum.model.*;
import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.shape.*;
import shapesphotoalbum.view.IGraphicalView;
import shapesphotoalbum.view.IWebView;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Mock implementation of the {@link IModel} interface for testing purposes.
 */
class MockModel implements IModel {
  private final IPhoto currentState; // current state of the photo album
  private final Map<String, ISnapshot> snapshots; // snapshots taken for the photo album
  private StringBuilder log = new StringBuilder();

  public MockModel() {
    currentState = new Photo();
    snapshots = new LinkedHashMap<>();
    // Define mock snapshots
    IShape R = new Rectangle("R", new Point2D(200, 200), 50, 100, new Color(1, 0, 0));
    IShape O = new Oval("O", new Point2D(500, 100), 60, 30, new Color(0, 0, 1));
    List<IShape> content = new ArrayList<>();
    content.add(R);
    content.add(O);
    ISnapshot s1 = new Snapshot("123", "Timestamp", "First snapshot", content);
    ISnapshot s2 = new Snapshot("456", "Future", "Second snapshot", content);

    snapshots.put(s1.getId(), s1);
    snapshots.put(s2.getId(), s2);
  }

  @Override
  public void executeCommand(IAction action) {
    log.append("Model executes command\n");
  }

  @Override
  public IPhoto getCurrentState() {
    log.append("Get current album state\n");
    return currentState;
  }

  @Override
  public List<ISnapshot> getSnapshots() {
    for (ISnapshot s : snapshots.values())
      log.append("Get snapshot: ").append(s.getDescription()).append("\n");

    List<ISnapshot> copy = new ArrayList<>();
    for (ISnapshot s : snapshots.values())
      copy.add(s.copy());

    return copy;
  }

  @Override
  public void takeSnapshot(String description) {
    log.append("Take snapshot, description: ").append(description).append("\n");
  }

  public String getOutput() {
    return log.toString();
  }
}

/**
 * Mock implementation of the {@link IGraphicalView} interface for testing purposes.
 */
class MockGraphicalView implements IGraphicalView {
  private StringBuilder log = new StringBuilder();

  @Override
  public void showPopUpWindow(String message) {
    log.append("Pop-up window shown: ").append(message).append("\n");
  }

  @Override
  public void addSnapshotToMenu(String id) {
    log.append("Snapshot added to menu\n");
  }

  @Override
  public void addButtonReactors(ICommandDelegate commandDelegate) {
    log.append("Button reactors added to view\n");
  }

  @Override
  public void displaySnapshot(ISnapshot snapshot) {
    log.append("Snapshot displayed: ").append(snapshot.getDescription()).append("\n");
  }

  @Override
  public void displayWindow() {
    log.append("Window displayed\n");
  }

  @Override
  public void displayInfo(String info) {
    log.append("Info displayed\n");
  }

  public String getOutput() {
    return log.toString();
  }
}

/**
 * Mock implementation of the {@link IWebView} interface for testing purposes.
 */
class MockWebView implements IWebView {
  private StringBuilder log = new StringBuilder();

  @Override
  public void displayWindow() {
    log.append("Window displayed\n");
  }

  @Override
  public void addSnapshot(ISnapshot snapshot) {
    log.append("Snapshot added\n");
  }

  public String getOutput() {
    return log.toString();
  }
}

/**
 * Unit tests for the {@link GraphicalController} and {@link WebController} classes.
 */
public class IControllerTest {
  private GraphicalController graphicalController;
  private WebController webController;
  // Create mock instances for model and view
  MockModel mockGraphicModel = new MockModel();
  MockModel mockWebModel = new MockModel();
  MockGraphicalView mockGraphicalView = new MockGraphicalView();
  MockWebView mockWebView = new MockWebView();

  @BeforeEach
  void setUp() throws FileNotFoundException {
    // Set up input data as a mock InputStream
    String inputData = """
        # Make a rectangle and oval. Color red and green
            shape myrect rectangle 200 200 50 100 255 0 0
            shape myoval oval 500 100 60 30 0 255 0
            
        # Take a snapshot. Optional description text follows snapshot command
            snapShot After first selfie
            
            move myrect 300 200
            resize myrect 25 100
        """;

    // Convert the string to an InputStream
    InputStream inputStream1 = new ByteArrayInputStream(inputData.getBytes());
    InputStream inputStream2 = new ByteArrayInputStream(inputData.getBytes());

    // Create instance of GraphicalController
    graphicalController = new GraphicalController(mockGraphicModel, mockGraphicalView, inputStream1);
    webController = new WebController(mockWebModel, mockWebView, inputStream2, "out.html");
  }

  /**
   * Test the behavior of the {@link GraphicalController}.
   */
  @Test
  void testGraphicalControllerBehavior() {
    // Call the controller's 'go' method
    graphicalController.go();

    // Verify expected interactions with the mock view
    String expectedOutput = """
              Model executes command
              Model executes command
              Model executes command
              Model executes command
              Model executes command
              Get snapshot: First snapshot
              Get snapshot: Second snapshot 
              """;
    assertEquals(expectedOutput, mockGraphicModel.getOutput());

    expectedOutput = """
              Snapshot added to menu
              Snapshot added to menu
              Button reactors added to view
              Snapshot displayed: First snapshot
              Window displayed       
              """;
    assertEquals(expectedOutput, mockGraphicalView.getOutput());
  }

  /**
   * Test the behavior of the {@link WebController}.
   */
  @Test
  void testWebControllerBehavior() {
    // Call the controller's 'go' method
    webController.go();

    // Verify expected interactions with the mock view
    String expectedOutput = """
              Model executes command
              Model executes command
              Model executes command
              Model executes command
              Model executes command
              Get snapshot: First snapshot
              Get snapshot: Second snapshot
              """;
    assertEquals(expectedOutput, mockWebModel.getOutput());

    expectedOutput = """
              Snapshot added
              Snapshot added
              Window displayed
              """;
    assertEquals(expectedOutput, mockWebView.getOutput());
  }

  /**
   * Test the behavior of the {@link ICommandDelegate} methods through the
   * {@link GraphicalController}.
   */
  @Test
  void testCommandDelegateBehavior() {
    // Call methods on ICommandDelegate (mockCommandDelegate)
    graphicalController.showInfo();
    graphicalController.pageForward();
    graphicalController.pageForward();
    graphicalController.selectMenu(1);
    graphicalController.showInfo();
    graphicalController.pageBackward();
    graphicalController.pageBackward();

    // Verify expected interactions with ICommandDelegate
    String expectedOutput = """
                Info displayed
                Snapshot displayed: Second snapshot
                Info displayed
                Pop-up window shown: End of the photo album. No snapshots to be shown beyond this one.
                Snapshot displayed: Second snapshot
                Info displayed
                Info displayed
                Snapshot displayed: First snapshot
                Info displayed
                Pop-up window shown: This is the first snapshot of the photo album.
                """;
    assertEquals(expectedOutput, mockGraphicalView.getOutput());
  }
}
