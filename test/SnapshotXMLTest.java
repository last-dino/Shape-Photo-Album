import org.junit.jupiter.api.Test;
import shapesphotoalbum.controller.adaptor.SnapshotXML;
import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.Snapshot;
import shapesphotoalbum.model.shape.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for SnapshotXML.
 */
public class SnapshotXMLTest {
  /**
   * Test the getSnapshotXML() method.
   */
  @Test
  void testGetSnapshotXML() {
    // Create shapes
    IShape rectangle = new Rectangle("myrect", new Point2D(100, 100), 50, 30, new Color(255, 0, 0));
    IShape oval = new Oval("myoval", new Point2D(200, 200), 40, 20, new Color(0, 0, 255));

    // Create snapshot with the shapes
    List<IShape> content = new ArrayList<>();
    content.add(rectangle);
    content.add(oval);
    ISnapshot snapshot = new Snapshot("123", "2022-04-08 12:00:00", "Snapshot description", content);

    // Create SnapshotXML instance with the mock snapshot
    SnapshotXML snapshotXML = new SnapshotXML(snapshot);

    // Generate the XML (SVG) representation
    String xml = snapshotXML.getSnapshotXML();

    // Assert on the generated XML
    String expectedXml = String.format("""
                <p>Snapshot ID: %s</p>
                <p>Snapshot Timestamp: 2022-04-08 12:00:00</p>
                <p>Snapshot Description: Snapshot description</p>

                <svg width='1000' height='1000'>
                <g transform="translate(0, 0)">
                <rect x='100' y='100' width='50' height='30' fill='rgb(255,0,0)' />
                <ellipse cx='200' cy='200' rx='40' ry='20' fill='rgb(0,0,255)' />
                </g>
                </svg>""", snapshot.getId());
    assertEquals(expectedXml, xml.trim());
  }
}
