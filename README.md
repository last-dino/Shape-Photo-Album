# Shapes Photo Album README

## Overview
The Shapes Photo Album application is designed using the Model-View-Controller (MVC) 
architectural pattern. This pattern separates the application into three interconnected 
components: Model, View, and Controller. Each component has its specific 
responsibilities and interacts with the others to achieve a modular and scalable 
design.

## Usage

To run the Shapes Photo Album application, follow the steps outlined below:

1. **Compile the Code**
  - Ensure that you have Java installed on your system.
  - Compile the application using the following command:
    ```bash
    javac PhotoAlbumMain.java
    ```

2. **Run the Application**
  - Use the `java` command to execute the `PhotoAlbumMain` class with appropriate command-line arguments:
    ```bash
    java PhotoAlbumMain -in "input-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax]
    ```
   Replace the placeholders as follows:
  - `"input-file"`: Specify the path to the input file containing commands (e.g., `"input.txt"`).
  - `"type-of-view"`: Choose the type of view (`graphical` or `web`) to interact with the application.
  - `"where-output-should-go"` (optional): Specify the path to save the output file (applicable for web view).
  - `xmax` and `ymax` (optional): Specify the maximum width and height for graphical views (default is 1000x1000).

3. **Example Usage**
  - For a graphical view:
    ```bash
    java PhotoAlbumMain -in "input.txt" -view graphical 1200 800
    ```
    This command reads commands from `input.txt`, displays a graphical view with a maximum size of 1200x800.
  - For a web view:
    ```bash
    java PhotoAlbumMain -in "input.txt" -view web -out "output.html"
    ```
    This command reads commands from `input.txt`, generates an HTML file (`output.html`) containing snapshots in 
XML format for web-based display.

4. **Viewing Output**
  - After running the application, follow the specific instructions based on the chosen view:
    - **Graphical View:** A graphical window will display the photo album with interactive controls.
    - **Web View:** Open the generated HTML file (`output.html`) in a web browser to view the album.

Ensure that the input file (`input-file`) contains valid commands as per the application's requirements. 
If any errors occur during execution (e.g., file not found, invalid arguments), appropriate error messages 
will be displayed in the console.


## Model
The Model component represents the application's data and business logic. 
It encapsulates the state of the photo album and provides methods to manipulate and 
query this state. It stores all snapshots of the photo album and provide methods to add 
and query the snapshots. In the Shapes Photo Album application, the Model is implemented 
by the `ShapesPhotoAlbumModel` class.

**Key Responsibilities:**
- Manages the current state of the photo album (IPhoto).
- Stores and manages snapshots (ISnapshot) of the photo album.
- Executes commands (IAction) on the photo album state.
- Provides an interface (IModel) for interacting with the photo album.

**Design Patterns Used:**
- Double Dispatch: Encapsulates commands (IAction) as objects, allowing for parameterization 
and queuing of actions.

### Classes Overview

**IModel**
- **Purpose:** Defines the contract for the ShapesPhotoAlbumModel.
- **Description:** Contains methods for executing commands, retrieving snapshots, and adding 
snapshots to the model.
- **Implementations:** `ShapesPhotoAlbumModel`

**IAction**
- **Purpose:** Defines the structure for actions that can be executed on the photo album.
- **Description:** Contains a single method `execute(IPhoto photo)` that defines the contract for 
executing an action on the photo album.
- **Implementations:** `Create`, `ChangeColor`, `Move`, `Resize`, `Remove`

**ISnapshot**
- **Purpose:** Represents a snapshot of the photo album.
- **Description:** Includes `getId()` to get the snapshot's unique identifier and `getContent()` 
to retrieve the shapes captured in the snapshot.
- **Implementations:** `Snapshot`

**IPhoto**
- **Purpose:** Represents the latest state of the photo album content.
- **Description:** Contains methods for adding shapes, moving shapes, changing colors, 
resizing shapes, removing shapes, and taking snapshots.
- **Implementations:** `Photo`

**IShape**
- **Purpose:** Represents a shape in the photo album.
- **Description:** Provides methods to retrieve information about a shape, such as its name, 
position, color, and dimensions. It also includes methods to set the color, position, horizontal 
and vertical dimensions of the shape and to create a copy of the shape.
- **Implementations:** Abstract Class `Shape` - `Rectangle`, `Oval`

## Controller

The Controller component acts as an intermediary between the model and the view. It interprets 
user inputs from the view, invokes corresponding operations on the model, and updates the view 
accordingly. In the Shapes Photo Album application, there are GraphicalController and WebController 
classes.

**Key Responsibilities:**
- Receives and processes user inputs from the view (ICommandDelegate).
- Orchestrates actions on the model based on user interactions.
- Updates the view with changes in the model's state.

**Design Patterns:**
- Command Pattern: Encapsulates user actions (ICommandDelegate) as commands, execute the corresponding
interaction using data retrieved from the model.
- Adapter Pattern: The Adapter Pattern allows objects with incompatible interfaces to work together 
by providing a wrapper that translates one interface into another. 
  - Adapts textual input (ActionReader) into executable actions (IAction) for the model.
  - Adapts snapshots (ISnapshot) into a structured XML format suitable for external consumption or storage 
  (SnapshotXML), or graphical rendering (Graphics) for Swing components (SnapshotPanel).

### Classes Overview

**GraphicalController - implements IController**
  - **Purpose:** Manages the graphical view of the photo album, including navigation and interaction.
  - **Description:** Implements the Controller in the Model-View-Controller (MVC) architecture. 
Utilizes `ActionReader` and interacts with the model and graphical view.

**WebController - implements IController**
  - **Purpose:** Controls the web-based view of the photo album.
  - **Description:** Similar to `GraphicalController` but tailored for web-based static view rendering.

**ICommandDelegate**
  - **Purpose:** Defines the interface for handling user commands and interactions.
  - **Description:** Facilitates decoupling between the controller and view components, supporting 
flexibility and extensibility.
  - **Implementations:** `GraphicalController`

**IDataProcessor**
- **Purpose:** Processes data from an input stream to parse commands.
- **Description:** Reads lines from an input stream, splits each line into individual commands based 
on whitespace, and stores the parsed commands in a list of string arrays (`List<String[]>`). This 
class is typically used to process input data from a file for command execution.
- **Implementations:** `FileProcessor`

**ITextToAction**
- **Purpose:** Represents a contract for transforming textual input into an executable action.
- **Description:** Implementing classes of this interface provide specific implementations to convert 
text-based commands into executable actions (`IAction` objects). The `transformToAction()` method 
is responsible for parsing textual input and producing an action object that can be executed on the 
Shapes Photo Album model. This interface facilitates the conversion process, allowing flexibility 
and extensibility in handling different types of textual commands.
- **Implementations:** `CreateTransform`, `ColorTransform`, `MoveTransform`, `ResizeTransform`, 
`RemoveTransform`, `SnapshotTransform`

**ActionReader**
- **Purpose:** Reads input data and converts it into a list of executable actions for the model
through `ITextToAction` instances.
- **Description:** Utilizes the Adapter Pattern to transform textual input into specific action
  types (`IAction`) using various transform classes.

**SnapshotPanel**
  - **Purpose:** Renders snapshot content in a Swing `JPanel`.
  - **Description:** Acts as an adapter between `ISnapshot` and graphical rendering (`Graphics`) 
for Swing components.

**SnapshotXML**
  - **Purpose:** Generates an XML (SVG) representation of snapshot content.
  - **Description:** Adapts `ISnapshot` and `IShape` into a structured XML format suitable for 
external consumption or storage.

## View
The View component represents the user interface (UI) of the application. It sends user commands 
to the controller and displays data from the model through the controller. In the Shapes Photo Album 
application, there are graphical (IGraphicalView) and web-based (IWebView) views.

**Key Responsibilities:**
- Renders the photo album to the user.
- Captures user inputs and forwards them to the controller for processing.

**Design Patterns Used:**
- Strategy Pattern: View supports rendering snapshots differently based on the view type 
(graphical vs. web-based).

### Classes Overview

**IView**
- **Purpose:** Represents the base interface for views in the Shapes Photo Album application.
- **Description:** Implementing classes that extend this interface are responsible for displaying 
windows associated with specific views in the application. The displayWindow() method is a common 
feature across all views to show their respective windows.

**GraphicalView - implements `IGraphicalView` which extends `IView`**
- **Purpose:** Represents a graphical view implementation using JFrame for the Shapes Photo Album 
application.
- **Description:** This class extends JFrame and implements IGraphicalView. It provides functionality 
to display graphical components including buttons, panels, and menus. Methods allow for displaying 
snapshots, showing information, adding snapshot IDs to menus, and handling button events using a 
command delegate.

**WebView - implements `IWebView` which extends `IView`**
- **Purpose:** Implements the IWebView interface to represent a static web-based view within the photo 
album application.
- **Description:** The WebView class generates an HTML output file containing snapshots in XML format. 
The addSnapshot method is utilized to incorporate individual snapshots into the HTML content, 
leveraging the `SnapshotXML` class to convert snapshots into XML representations suitable for embedding into the 
HTML structure. The generated HTML file serves as the visual output of the photo album for web-based 
presentation.

## Summary
The MVC architecture of the Shapes Photo Album application promotes separation of concerns and modularity:

Model: Manages data and business logic independently of the UI.\
View: Presents the model's data to the user and captures user interactions.\
Controller: Mediates interactions between the model and view, handling user inputs and updating the model.

By using design patterns such as Command, Strategy, and Adapter, the application achieves flexibility, 
extensibility, and maintainability. Changes in one component (e.g., model updates) can be reflected 
seamlessly in others (e.g., UI updates) without tightly coupling their implementations. This 
architectural approach supports scalability and facilitates easier testing and maintenance of the 
application.






