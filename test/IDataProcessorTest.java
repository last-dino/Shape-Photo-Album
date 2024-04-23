import org.junit.jupiter.api.Test;
import shapesphotoalbum.controller.reader.FileProcessor;
import shapesphotoalbum.controller.reader.IDataProcessor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Data processor.
 */
public class IDataProcessorTest {
  /**
   * Tests the file processor.
   * Verifies that the file is turned in to an array of String array.
   */
  @Test
  void testFileProcessor() {
    // Sample input data as a string
    List<String[]> content = getMock();

    // Assert the expected size
    assertEquals(9, content.size());

    // Test the first line
    assertEquals(10, content.get(0).length);
    assertEquals("#", content.get(0)[0]);
    assertEquals("Make", content.get(0)[1]);
    assertEquals("green", content.get(0)[9]);

    // Test the second line
    assertEquals(10, content.get(1).length);
    assertEquals("shape", content.get(1)[0]);
    assertEquals("myrect", content.get(1)[1]);
    assertEquals("0", content.get(1)[9]);

    // Test an empty line
    assertEquals(1, content.get(3).length);
    assertEquals("", content.get(3)[0]);

    // Test the last line
    assertEquals(4, content.get(8).length);
    assertEquals("resize", content.get(8)[0]);
    assertEquals("myrect", content.get(8)[1]);
    assertEquals("25", content.get(8)[2]);
    assertEquals("100", content.get(8)[3]);
  }

  /**
   * Provides mock input data as a list of string arrays for testing purposes.
   */
  private static List<String[]> getMock() {
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
    InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

    // Instantiate FileProcessor with the mock Scanner
    IDataProcessor dataProcessor = new FileProcessor(inputStream);
    return dataProcessor.process();
  }
}
