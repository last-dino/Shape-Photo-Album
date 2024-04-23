package shapesphotoalbum.view;

import shapesphotoalbum.controller.adaptor.SnapshotXML;
import shapesphotoalbum.model.ISnapshot;

import java.io.FileNotFoundException;

/**
 * The WebView class implements the IWebView interface to represent
 * a view for web-based interactions in the photo album application.
 * It generates an HTML file containing snapshots in XML format.
 */
public class WebView implements IWebView {
  private final String outputFile;
  private final StringBuilder snapshotsXML;

  /**
   * Constructs a WebView instance with the specified output file path.
   *
   * @param outputFile the path to the output HTML file.
   */
  public WebView(String outputFile) {
    this.outputFile = outputFile;
    snapshotsXML = new StringBuilder();
  }

  /**
   * Displays the window by generating the output HTML file.
   */
  @Override
  public void displayWindow() {
    getOutputFile();
  }

  /**
   * Generates the output HTML file containing snapshots.
   */
  private void getOutputFile() {
    java.io.File file = new java.io.File(outputFile);
    if (file.exists()) {
      System.out.println("File already exists");
      System.exit(0);
    }

    try (java.io.PrintWriter output = new java.io.PrintWriter(file);){
      output.append(builtFile());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Builds the content of the output HTML file.
   *
   * @return the content of the HTML file as a string.
   */
  private String builtFile() {
    StringBuilder html = new StringBuilder();

    html.append("<!DOCTYPE html>\n");
    html.append("<html>\n");
    html.append("<head>\n");
    html.append("<title>CS5004 Shapes Photo Album</title>\n");

    html.append("<style>\n");
    html.append(".snapshot {\n");
    html.append("    border: 2px solid black;\n");
    html.append("    padding: 10px;\n");
    html.append("    margin-bottom: 20px;\n");
    html.append("    background-color: rgb(255, 255, 153);\n");
    html.append("}\n");

    html.append("h1 {\n");
    html.append("  font-family: 'Comic Sans MS', cursive;\n");
    html.append("  color: orange;\n");
        html.append("}\n");
    html.append("</style>\n");

    html.append("</head>\n");
    html.append("<body>\n");

    html.append("<h1>Welcome To The Album!</h1>\n");
    html.append(snapshotsXML);

    html.append("</body>\n");
    html.append("</html>\n");

    return html.toString();
  }

  /**
   * Adds a snapshot to the HTML content.
   *
   * @param snapshot the snapshot to add to the HTML.
   */
  @Override
  public void addSnapshot(ISnapshot snapshot) {
    snapshotsXML.append("<div class='snapshot'>\n");
    snapshotsXML.append(new SnapshotXML(snapshot).getSnapshotXML());
    snapshotsXML.append("</div>\n");
  }
}
