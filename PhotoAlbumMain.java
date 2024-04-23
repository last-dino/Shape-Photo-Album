import shapesphotoalbum.controller.GraphicalController;
import shapesphotoalbum.controller.IController;
import shapesphotoalbum.controller.WebController;
import shapesphotoalbum.model.IModel;
import shapesphotoalbum.model.ShapesPhotoAlbumModel;
import shapesphotoalbum.view.GraphicalView;
import shapesphotoalbum.view.IGraphicalView;
import shapesphotoalbum.view.IWebView;
import shapesphotoalbum.view.WebView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class serves as the entry point for testing out the Shapes
 * Photo Album functionality.
 */
public class PhotoAlbumMain {
  /**
   * The main method creates a Shapes Photo Album model, performs various
   * actions on it, takes snapshots at different stages, and prints out the snapshots.
   * @param args The command-line arguments (not used in this application).
   */
//    String album = "C:/Users/lunax/Downloads/CS5004/Homework/Homework8/src/hoops.txt";
  public static void main(String[] args) throws IOException {
    if (args == null || args.length < 4 || args.length > 8)
      throw new IOException("Usage: MyProgram -in \"input-file\" -view \"type-of-view\" [-out \"where-output-should-go\"] [xmax] [ymax]");

    String inputFile = null;
    String viewType = null;
    String outputFile = null;
    int maxWidth = 1000;
    int maxHeight = 1000;

    // Process command line arguments
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        if (i + 1 < args.length) {
          inputFile = args[i + 1];
        }
        else {
          System.err.println("Missing input file after -in option.");
          return;
        }
      }

      else if (args[i].equals("-view") || args[i].equals("-v")) {
        if (i + 1 < args.length) {
          viewType = args[i + 1];
        }
        else {
          System.err.println("Missing view type after -view option.");
          return;
        }
      }

      else if (args[i].equals("-out")) {
        if (i + 1 < args.length) {
          outputFile = args[i + 1];
        }
        else {
          System.err.println("Missing output file after -out option.");
          return;
        }
      }

      else if (args[i].matches("\\d+")) {
        // Check for optional xmax and ymax
        // If only one number passed in, assume it's xmax
        maxWidth = Integer.parseInt(args[i]);
        if (args[i + 1].matches("\\d+")) {
          maxHeight = Integer.parseInt(args[i + 1]);
          i += 1;
        }
      }
    }

    // Validate mandatory parameters
    if (inputFile == null || viewType == null) {
      System.err.println("Usage: MyProgram -in \"input-file\" -view \"type-of-view\" [-out \"where-output-should-go\"] [xmax] [ymax]");
      return;
    }

    try {
      InputStream inputStream = new FileInputStream(inputFile);
      IModel model = new ShapesPhotoAlbumModel();

      // Create controller based on view type
      if (viewType.equalsIgnoreCase("graphical")) {
        IGraphicalView view = new GraphicalView("CS5004 Shapes Photo Album Viewer", maxWidth, maxHeight);
        IController controller = new GraphicalController(model, view, inputStream);
        controller.go();
      }
      else if (viewType.equalsIgnoreCase("web")) {
        if (outputFile == null) { // Mandatory output file for web view
          System.err.println("Missing output file.");
          return;
        }
        IWebView view = new WebView(outputFile);
        IController controller = new WebController(model, view, inputStream, outputFile);
        controller.go();
      }
    } catch (FileNotFoundException e) {
      System.err.println("Input file not found: " + inputFile);
    } catch (IOException e) {
      System.err.println("Error reading input file: " + e.getMessage());
    }
  }
}
