package shapesphotoalbum.controller.reader;

import java.util.List;

/**
 * The IDataProcessor interface defines a contract for processing data.
 * Implementing classes are responsible for processing data and returning results.
 */
public interface IDataProcessor {
  /**
   * Processes data and returns a list of parsed commands.
   * Each element in the list represents a command or data record.
   * @return A list of String arrays containing parsed data or commands.
   *         Each String array represents a record or command from the processed data.
   */
  List<String[]> process();
}
