package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private static String welcomeMessage = "Wahaha! I've arrived!";

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hahaha.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wahaha.png"));

    /**
     * Creates A MainWindow that will serve as the interface between the
     * given Duke object and the user.
     * @param duke Duke logic running with the MainWindow
     * @param canLoad if Duke cannot load saved tasks and warns user
     * @return MainWindow interfacing Duke and user
     * @throws IOException if .fxml file encounter loading failure
     */
    public static MainWindow createMainWindow(Duke duke, boolean canLoad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
        MainWindow mw = new MainWindow();
        fxmlLoader.setRoot(mw);
        fxmlLoader.load();
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        if (!canLoad) {
            fxmlLoader.<MainWindow>getController().dialogContainer.getChildren()
                    .add(DialogBox.getDukeDialog("Saved tasks cannot be loaded, "
                    + "starting from new file.", mw.dukeImage));
        }
        return mw;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox welcome = DialogBox.getDukeDialog(welcomeMessage, dukeImage);
        dialogContainer.getChildren().addAll(welcome);
    }

    public void setDuke(Duke d) {
        assert d != null;
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void takeUserInput() {
        String input = userInput.getText();
        DialogBox message = DialogBox.getUserDialog(input, userImage);
        String response = duke.getResponse(input);
        DialogBox dukeResponse = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(message, dukeResponse);
        userInput.clear();
    }
}
