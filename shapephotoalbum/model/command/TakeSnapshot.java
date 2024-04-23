package shapesphotoalbum.model.command;

import shapesphotoalbum.model.IModel;

public class TakeSnapshot implements IAction {
  private final String description;

  public TakeSnapshot(String description) {
    this.description = description;
  }

  @Override
  public void execute(IModel model) {
    if (model == null || model.getCurrentState() == null)
      throw new IllegalArgumentException("Photo album doesn't exist");

    model.takeSnapshot(description);
  }
}
