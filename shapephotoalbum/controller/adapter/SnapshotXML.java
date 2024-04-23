package shapesphotoalbum.controller.adaptor;

import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.shape.Color;
import shapesphotoalbum.model.shape.IShape;
import shapesphotoalbum.model.shape.Point2D;
import shapesphotoalbum.model.shape.ShapeType;
import java.util.List;

/**
 * The SnapshotXML class is responsible for generating an XML representation (SVG) of a snapshot.
 */
public class SnapshotXML {
  private final ISnapshot snapshot;

  /**
   * Constructs a SnapshotXML instance with the specified snapshot.
   *
   * @param snapshot the snapshot to generate XML for.
   */
  public SnapshotXML(ISnapshot snapshot) {
    this.snapshot = snapshot;
  }

  /**
   * Generates an XML (SVG) representation of the snapshot, including snapshot metadata and shapes.
   *
   * @return the XML (SVG) representation of the snapshot.
   */
  public String getSnapshotXML() {
    StringBuilder html = new StringBuilder();

    // Append snapshot metadata to the XML
    html.append("<p>Snapshot ID: ").append(snapshot.getId()).append("</p>\n");
    html.append("<p>Snapshot Timestamp: ").append(snapshot.getTimestamp()).append("</p>\n");
    html.append("<p>Snapshot Description: ").append(snapshot.getDescription()).append("</p>\n\n");

    // Start SVG content
    html.append("<svg width='1000' height='1000'>\n");
    html.append("<g transform=\"translate(0, 0)\">\n");
    // Draw each shape in the snapshot
    List<IShape> content = snapshot.getContent();
    for (IShape shape : content) {
      html.append(drawShape(shape));
    }
    // End SVG content
    html.append("</g>\n");
    html.append("</svg>\n");

    return html.toString();
  }

  /**
   * Generates an SVG representation of a specific shape.
   *
   * @param shape the shape to draw as SVG.
   * @return the SVG representation of the shape.
   */
  private String drawShape(IShape shape) {
    String svgShape = "";

    // Extract shape properties
    Point2D position = shape.getPosition();
    int x = position.getX();
    int y = position.getY();
    int width = shape.getHorizontalDimension();
    int height = shape.getVerticalDimension();
    Color color = shape.getColor();
    int r = color.getR();
    int g = color.getG();
    int b = color.getB();

    // Generate SVG shape based on its type
    if (shape.getType() == ShapeType.RECTANGLE) {
      svgShape = String.format("<rect x='%d' y='%d' width='%d' height='%d' fill='rgb(%d,%d,%d)' />\n",
          x, y, width, height, r, g, b);
    }
    else if (shape.getType() == ShapeType.OVAL) {
      svgShape = String.format("<ellipse cx='%d' cy='%d' rx='%d' ry='%d' fill='rgb(%d,%d,%d)' />\n",
          x, y, width, height, r, g, b);
    }

    return svgShape;
  }
}
