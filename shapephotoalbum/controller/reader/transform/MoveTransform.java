package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.command.Move;

/**
 * The MoveTransform class implements the ITextToAction interface to transform textual
 * instructions into a Move action object.
 */
public class MoveTransform implements ITextToAction {
  private final int parametersCount = 4;
  private final String[] instruction;

  /**
   * Constructs a new MoveTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for moving a shape.
   * @throws IllegalArgumentException if the instruction is null or has an invalid length.
   */
  public MoveTransform(String[] instruction) {
    if (instruction == null || instruction.length != parametersCount)
      throw new IllegalArgumentException("Invalid instruction for action Move");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a Move action.
   *
   * @return A Move action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid parameters.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new Move action based on the input instruction.
   *
   * @return A Move action object representing the transformed textual command.
   * @throws IllegalArgumentException if the instruction contains invalid or missing parameters.
   */
  private IAction getAction() {
    String name = instruction[1];

    try {
      int x = Integer.parseInt(instruction[2]);
      int y = Integer.parseInt(instruction[3]);

      // Create and return a new Move action
      return new Move(name, x, y);
    }
    catch (IllegalArgumentException e){
      throw new IllegalArgumentException("Invalid value for action Move");
    }
  }
}