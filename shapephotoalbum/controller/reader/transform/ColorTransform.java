package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.ChangeColor;
import shapesphotoalbum.model.command.IAction;

/**
 * The ColorTransform class implements the ITextToAction interface to transform textual
 * instructions into a ChangeColor action object.
 */
public class ColorTransform implements ITextToAction {
  private final int parametersCount = 5;
  private final String[] instruction;

  /**
   * Constructs a new ColorTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for changing color.
   * @throws IllegalArgumentException if the instruction is null or has an invalid length.
   */
  public ColorTransform(String[] instruction) {
    if (instruction == null || instruction.length != parametersCount)
      throw new IllegalArgumentException("Invalid instruction for action Change Color");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a ChangeColor action.
   *
   * @return A ChangeColor action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid parameters.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new ChangeColor action based on the input instruction.
   *
   * @return A ChangeColor action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid or missing parameters.
   */
  private IAction getAction() {
    String name = instruction[1];

    try {
      int R = Integer.parseInt(instruction[2]);
      int G = Integer.parseInt(instruction[3]);
      int B = Integer.parseInt(instruction[4]);

      // Create and return a new ChangeColor action
      return new ChangeColor(name, R, G, B);
    }
    catch (IllegalArgumentException e){
      throw new IllegalArgumentException("Invalid value for action Change Color");
    }
  }
}
