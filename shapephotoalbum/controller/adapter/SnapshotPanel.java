package shapesphotoalbum.controller.adaptor;

import shapesphotoalbum.model.ISnapshot;
import shapesphotoalbum.model.shape.Color;
import shapesphotoalbum.model.shape.IShape;
import shapesphotoalbum.model.shape.Point2D;
import shapesphotoalbum.model.shape.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The SnapshotPanel class represents a JPanel used to display shapes from a snapshot.
 * Each shape in the snapshot is drawn on the panel based on its type, position, size, and color.
 */
public class SnapshotPanel extends JPanel {

  private final List<IShape> shapes; // List of shapes to be displayed in the panel

  /**
   * Constructs a SnapshotPanel with the specified snapshot containing shapes to display.
   *
   * @param snapshot the snapshot containing shapes to be displayed on the panel.
   */
  public SnapshotPanel(ISnapshot snapshot) {
    this.shapes = snapshot.getContent();
  }

  /**
   * Overrides the paintComponent method to customize the rendering of the panel.
   *
   * @param g the Graphics context used for painting shapes on the panel.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Iterate through each shape and draw it on the panel
    for (IShape shape : shapes) {
      drawShape(g, shape);
    }
  }

  /**
   * Draws a specific shape on the panel based on its type, position, size, and color.
   *
   * @param g     the Graphics context used for drawing the shape.
   * @param shape the shape to be drawn on the panel.
   */
  private void drawShape(Graphics g, IShape shape) {
    // Get the position and dimensions of the shape
    Point2D position = shape.getPosition();
    int x = position.getX();
    int y = position.getY();
    int width = shape.getHorizontalDimension();
    int height = shape.getVerticalDimension();

    // Get the color of the shape
    Color color = shape.getColor();
    java.awt.Color awtColor = new java.awt.Color(color.getR(), color.getG(), color.getB());

    // Set the color and draw the shape
    g.setColor(awtColor);

    if (shape.getType() == ShapeType.RECTANGLE) {
      g.fillRect(x, y, width, height); // Draw a filled rectangle
    } else if (shape.getType() == ShapeType.OVAL) {
      g.fillOval(x, y, width, height); // Draw a filled oval
    }
  }
}