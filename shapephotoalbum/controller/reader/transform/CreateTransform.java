package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.Create;
import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.shape.ShapeType;

/**
 * The CreateTransform class implements the ITextToAction interface to transform textual
 * instructions into a Create action object.
 */
public class CreateTransform implements ITextToAction {
  private final int parametersCount = 10;
  private final String[] instruction;

  /**
   * Constructs a new CreateTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for creating a shape.
   * @throws IllegalArgumentException if the instruction is null or has an invalid length.
   */
  public CreateTransform(String[] instruction) {
    if (instruction == null || instruction.length != parametersCount)
      throw new IllegalArgumentException("Invalid instruction for action Create");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a Create action.
   *
   * @return A Create action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid parameters.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new Create action based on the input instruction.
   *
   * @return A Create action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid or missing parameters.
   */
  private IAction getAction() {
    String name = instruction[1];
    ShapeType type = switch (instruction[2]) {
      case "oval" -> ShapeType.OVAL;
      case "rectangle" -> ShapeType.RECTANGLE;
      default -> throw new IllegalArgumentException("This shape isn't supported by the application yet");
    };

    try {
      int x = Integer.parseInt(instruction[3]);
      int y = Integer.parseInt(instruction[4]);
      int h = Integer.parseInt(instruction[5]);
      int v = Integer.parseInt(instruction[6]);
      int R = Integer.parseInt(instruction[7]);
      int G = Integer.parseInt(instruction[8]);
      int B = Integer.parseInt(instruction[9]);

      // Create and return a new Create action
      return new Create(name, type, x, y, h, v, R, G, B);
    }
    catch (IllegalArgumentException e){
      throw new IllegalArgumentException("Invalid value for action Create");
    }
  }
}
