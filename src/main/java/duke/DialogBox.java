package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Insets userTextPadding = new Insets(0, 5, 0, 0);
    private static final Insets dukeTextPadding = new Insets(0, 0, 0, 5);
    private static final Background userBackground = new Background(new BackgroundFill(Color.GOLD,
            new CornerRadii(0.8, true), new Insets(3)));
    private static final Background dukeBackground = new Background(new BackgroundFill(Color.HOTPINK,
            new CornerRadii(0.8, true), new Insets(3)));
    private static final double profileCircleX = 28;
    private static final double profileCircleY = 20;
    private static final double profileCircleRadius = 20;


    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(profileCircleX, profileCircleY, profileCircleRadius));
        dialog.setPadding(userTextPadding);
        this.setBackground(userBackground);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        this.setMinHeight(200);
        this.setPrefHeight(200);
        this.dialog.setPadding(dukeTextPadding);
        this.setBackground(dukeBackground);
        this.dialog.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

