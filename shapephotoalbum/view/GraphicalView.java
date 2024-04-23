package shapesphotoalbum.view;

import shapesphotoalbum.controller.adaptor.SnapshotPanel;
import shapesphotoalbum.controller.ICommandDelegate;
import shapesphotoalbum.model.ISnapshot;

import java.awt.*;
import javax.swing.*;

/**
 * The {@code GraphicalView} class represents a graphical view for the
 * Shapes Photo Album application.
 * It extends {@link JFrame} and implements {@link IGraphicalView}.
 */
public class GraphicalView extends JFrame implements IGraphicalView {
  private JButton btnInfo;
  private JButton btnForward;
  private JButton btnBackward;
  private JMenu btnJump;
  private JButton btnQuit;
  private JPanel snapshotContainer;
  private JPanel infoPanel;
  private JPanel buttonPanel;

  /**
   * Constructs a new GraphicalView with the specified caption, width, and height.
   *
   * @param caption the caption of the window.
   * @param width   the width of the window.
   * @param height  the height of the window.
   */
  public GraphicalView(String caption, int width, int height) {
    super(caption);
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    addPanels();
    addButtons();
  }

  /**
   * Adds panels to the main content panel of the graphical view.
   * Includes the infoPanel, snapshotContainer, and buttonPanel.
   */
  private void addPanels() {
    // Create the main content panel
    JPanel mainPanel = new JPanel(new BorderLayout());
    setContentPane(mainPanel);

    int bannerHeight = 60; // Set banner height
    infoPanel = new JPanel();
    infoPanel.setPreferredSize(new Dimension(getWidth(), bannerHeight));
    infoPanel.setBackground(Color.LIGHT_GRAY);
    infoPanel.setVisible(false); // Initially hidden
    mainPanel.add(infoPanel, BorderLayout.NORTH);

    // Create the snapshot container panel (centered)
    snapshotContainer = new JPanel(new BorderLayout());
    mainPanel.add(snapshotContainer, BorderLayout.CENTER);

    // Create the button panel (aligned to the bottom)
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Adds buttons to the button panel of the graphical view.
   * Includes btnInfo, btnBackward, btnForward, btnQuit, and btnJump.
   */
  private void addButtons() {
    JMenuBar menuBar = new JMenuBar();
    btnJump = new JMenu(" ☰ Menu ");
    menuBar.add(btnJump);
    buttonPanel.add(menuBar);

    btnInfo = new JButton(" Show Info ");
    buttonPanel.add(btnInfo);

    btnBackward = new JButton(" << Prev ");
    buttonPanel.add(btnBackward);

    btnForward = new JButton(" Next >> ");
    buttonPanel.add(btnForward);

    btnQuit = new JButton(" X Quit ");
    buttonPanel.add(btnQuit);
  }

  /**
   * Adds a snapshot ID to the menu in the graphical view.
   *
   * @param snapshotId the ID of the snapshot to add to the menu.
   */
  public void addSnapshotToMenu(String snapshotId) {
    JMenuItem menuItem = new JMenuItem(snapshotId);
    btnJump.add(menuItem);
  }

  /**
   * Displays the graphical view window.
   */
  @Override
  public void displayWindow() {
    pack(); // Sizes the frame to fit its contents
    this.setVisible(true); // Makes the frame visible
  }

  /**
   * Displays information about the current snapshot in the info panel.
   *
   * @param info the information to display.
   */
  @Override
  public void displayInfo(String info) {
    JLabel snapshotInfo = new JLabel(info);
    infoPanel.removeAll();
    infoPanel.add(snapshotInfo);
    infoPanel.revalidate();
    infoPanel.repaint();
  }

  /**
   * Displays a snapshot in the graphical view.
   *
   * @param snapshot the snapshot to display.
   */
  @Override
  public void displaySnapshot(ISnapshot snapshot) {
    SnapshotPanel snapshotPanel = new SnapshotPanel(snapshot);
    snapshotPanel.setPreferredSize(new Dimension(1000, 800));
    // Clear existing content from snapshotContainer
    snapshotContainer.removeAll();

    // Add snapshotPanel to snapshotContainer
    snapshotContainer.add(snapshotPanel, BorderLayout.CENTER);

    // Revalidate and repaint snapshotContainer to update layout
    snapshotContainer.revalidate();
    snapshotContainer.repaint();
  }

  /**
   * Adds button event listeners to the buttons in the graphical view.
   *
   * @param delegate the command delegate used to handle button actions.
   */
  @Override
  public void addButtonReactors(ICommandDelegate delegate) {
    btnInfo.addActionListener(event -> {
      delegate.showInfo();
      infoPanel.setVisible(!infoPanel.isVisible()); // Show or hide info
    });

    for (int i = 0 ; i < btnJump.getItemCount(); i++) {
      JMenuItem item = btnJump.getItem(i);
      int index = i;
      item.addActionListener(event -> {
        delegate.selectMenu(index); // Handle menu item selection
      });
    }

    btnForward.addActionListener(event -> delegate.pageForward());
    btnBackward.addActionListener(event -> delegate.pageBackward());

    btnQuit.addActionListener(event ->delegate.quit());
  }

  /**
   * Displays a pop-up window with the specified message.
   *
   * @param message the message to display in the pop-up window.
   */
  @Override
  public void showPopUpWindow(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
