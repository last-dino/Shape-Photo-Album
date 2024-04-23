package shapesphotoalbum.controller.reader.transform;

import shapesphotoalbum.model.command.IAction;
import shapesphotoalbum.model.command.TakeSnapshot;

/**
 * The SnapshotTransform class implements the ITextToAction interface to transform textual
 * instructions into a TakeSnapshot action object.
 */
public class SnapshotTransform implements ITextToAction {
  private final String[] instruction;

  /**
   * Constructs a new SnapshotTransform instance with the given instruction.
   *
   * @param instruction The instruction containing parameters for taking a snapshot.
   * @throws IllegalArgumentException if the instruction is null.
   */
  public SnapshotTransform(String[] instruction) {
    if (instruction == null)
      throw new IllegalArgumentException("Invalid instruction for action Take Snapshot");

    this.instruction = instruction;
  }

  /**
   * Transforms the instruction into a TakeSnapshot action.
   *
   * @return A TakeSnapshot action object representing the transformed textual command.
   */
  @Override
  public IAction transformToAction() {
    return getAction();
  }

  /**
   * Constructs a new TakeSnapshot action based on the input instruction.
   *
   * @return A TakeSnapshot action object representing the transformed textual command.
   */
  private IAction getAction() {
    String[] description = new String[instruction.length - 1];

    for (int i = 1, j = 0; i < instruction.length; i++, j++) {
      description[j] = instruction[i];
    }
    // Create and return a new TakeSnapshot action with the extracted description
    return new TakeSnapshot(String.join(" ", description));
  }
}
