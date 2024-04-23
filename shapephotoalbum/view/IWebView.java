package shapesphotoalbum.view;

import shapesphotoalbum.model.ISnapshot;

/**
 * The IWebView interface represents a view for web-based interactions
 * in the photo album application. It extends the IView interface and
 * defines additional methods specific to web views.
 */
public interface IWebView extends IView {
  /**
   * Adds a snapshot to the web view.
   *
   * @param snapshot the snapshot to add to the web view.
   */
  void addSnapshot(ISnapshot snapshot);
}
