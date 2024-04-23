package shapesphotoalbum.controller;

/**
 * The ICommandDelegate interface defines methods that handle user commands from the graphical view.
 * Implementations of this interface delegate specific actions based on user interactions.
 */
public interface ICommandDelegate {
  /**
   * Displays information about the current snapshot.
   */
  void showInfo();

  /**
   * Displays the next snapshot in the album.
   */
  void pageForward();

  /**
   * Displays the snapshot corresponding to the selected menu item.
   *
   * @param index the index of the selected snapshot.
   */
  void selectMenu(int index);

  /**
   * Displays the previous snapshot in the album.
   */
  void pageBackward();

  /**
   * Quits the application.
   */
  void quit();
}
