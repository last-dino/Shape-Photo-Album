package shapesphotoalbum.controller.reader;

import shapesphotoalbum.controller.reader.transform.*;
import shapesphotoalbum.model.command.IAction;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The ActionReader class is responsible for reading input data and converting it into a list of actions.
 * It utilizes different transform classes to parse input data into specific action types.
 */
public class ActionReader {
  /**
   * Reads input data from a file, processes it, and converts it into a list of actions.
   * @param inputStream The InputStream containing the input data.
   * @return A list of parsed actions based on the input data.
   */
  public static List<IAction> readInput(InputStream inputStream) {
    // Create a data processor for the specified input file
    IDataProcessor dataProcessor = new FileProcessor(inputStream);
    // Process the content of the input file
    List<String[]> content = dataProcessor.process();
    List<IAction> actions = new ArrayList<>();

    // Parse each line of content into specific action types
    for (String[] line : content) {
      // Determine the command type (first element of the line)
      String commandType = line[0].toLowerCase();

      // Use different transform classes based on the command type
      switch (commandType) {
        case "shape":
          ITextToAction create = new CreateTransform(line);
          actions.add(create.transformToAction());
          break;
        case "move":
          ITextToAction move = new MoveTransform(line);
          actions.add(move.transformToAction());
          break;
        case "resize":
          ITextToAction resize = new ResizeTransform(line);
          actions.add(resize.transformToAction());
          break;
        case "color":
          ITextToAction color = new ColorTransform(line);
          actions.add(color.transformToAction());
          break;
        case "snapshot":
          ITextToAction snapShot = new SnapshotTransform(line);
          actions.add(snapShot.transformToAction());
          break;
        case "remove":
          ITextToAction remove = new RemoveTransform(line);
          actions.add(remove.transformToAction());
          break;
        default:
          break;
      }
    }
    return actions;
  }
}
