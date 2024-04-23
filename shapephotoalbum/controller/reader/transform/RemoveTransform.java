package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.command.Remove;

/**
 * The RemoveTransform class implements the ITextToAction interface to transform textual
 * instructions into a Remove action object.
 */
public class RemoveTransform implements ITextToAction {
  private final int parametersCount = 2;
  private final String[] instruction;

  /**
   * Constructs a new RemoveTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for removing a shape.
   * @throws IllegalArgumentException if the instruction is null or has an invalid length.
   */
  public RemoveTransform(String[] instruction) {
    if (instruction == null || instruction.length != parametersCount)
      throw new IllegalArgumentException("Invalid instruction for action Remove");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a Remove action.
   *
   * @return A Remove action object representing the transformed textual command.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new Remove action based on the input instruction.
   *
   * @return A Remove action object representing the transformed textual command.
   */
  private IAction getAction() {
    // Create and return a new Remove action with the specified shape name
    return new Remove(instruction[1]);
  }
}
