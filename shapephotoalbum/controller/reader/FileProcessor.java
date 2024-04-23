package shapesphotoalbum.controller.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for processing data from a file.
 */
public class FileProcessor implements IDataProcessor {
  private final InputStream inputStream;
  private final List<String[]> processed;

  /**
   * Constructs a FileProcessor with the specified input filename.
   * @param inputStream The InputStream containing the input data.
   */
  public FileProcessor(InputStream inputStream) {
    this.inputStream = inputStream;
    this.processed = new ArrayList<>();
  }

  /**
   * Processes the input file and returns a list of parsed commands.
   * Each line from the input file is split into an array of strings,
   * representing individual commands.
   * @return A list of parsed commands from the input file.
   * @throws RuntimeException if an I/O error occurs while reading the file.
   */
  public List<String[]> process() {
    try {
      Scanner scanner = new Scanner(inputStream);

      while (scanner.hasNextLine()) {
        String[] command = scanner.nextLine().replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
        processed.add(command);
      }

      scanner.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return processed;
  }
}
