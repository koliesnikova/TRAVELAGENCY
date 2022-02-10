package sk.upjs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    protected Stage loadWindow(String fxmlFileName, String windowTitle, Object controller, double minWidth,
                               double minHeigth, double maxWidth, double maxHeigth) throws IOException {
        Stage modalStage = null;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        fxmlLoader.setController(controller);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        modalStage = new Stage();
        modalStage.setTitle(windowTitle);
        modalStage.getIcons().add(new Image("/sk.upjs.gui/icon.png"));
        modalStage.setScene(scene);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setMinHeight(minHeigth);
        modalStage.setMinWidth(minWidth);
        modalStage.setMaxHeight(maxHeigth);
        modalStage.setMaxWidth(maxWidth);
        modalStage.showAndWait();

        return modalStage;
    }

    protected Stage loadWindow(String fxmlFileName, String windowTitle) throws IOException {
        return loadWindow(fxmlFileName, windowTitle, null, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    }

    protected void closeWindow(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    protected void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
