package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.command.Resize;

/**
 * The ResizeTransform class implements the ITextToAction interface to transform textual
 * instructions into a Resize action object.
 */
public class ResizeTransform implements ITextToAction {
  private final int parametersCount = 4;
  private final String[] instruction;

  /**
   * Constructs a new ResizeTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for resizing a shape.
   * @throws IllegalArgumentException if the instruction is null or has an invalid length.
   */
  public ResizeTransform(String[] instruction) {
    if (instruction == null || instruction.length != parametersCount)
      throw new IllegalArgumentException("Invalid instruction for action Resize");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a Resize action.
   *
   * @return A Resize action object representing the transformed textual command.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new Resize action based on the input instruction.
   *
   * @return A Resize action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid numeric values.
   */
  private IAction getAction() {
    String name = instruction[1];

    try {
      int h = Integer.parseInt(instruction[2]);
      int v = Integer.parseInt(instruction[3]);

      // Create and return a new Resize action with the specified parameters
      return new Resize(name, h, v);
    }
    catch (IllegalArgumentException e){
      throw new IllegalArgumentException("Invalid value for action Resize");
    }
  }
}
