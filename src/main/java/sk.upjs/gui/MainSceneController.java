package sk.upjs.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class MainSceneController extends Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button bookedTours;
    @FXML
    private Button clients;
    @FXML
    private Button oProjekte;
    @FXML
    private Button predaj;
    @FXML
    private Button tour;

        @FXML
        void initialize() {
          clients.setOnAction(actionEvent ->  {
              try {
                  loadWindow("/sk.upjs.gui/ClientsFXmodel.fxml", "Clients", new ClientSceneController(), 420, 302, 420, 302);
              } catch (IOException e) {
                  e.printStackTrace();
              }

          });
          bookedTours.setOnAction(actionEvent ->  {
              try {
                  loadWindow("/sk.upjs.gui/BookedToursFXmodel.fxml", "Info", new InfoController(), 420, 302, 420, 302);
              } catch (IOException e) {
                  e.printStackTrace();
              }

          }
          );
          tour.setOnAction(actionEvent ->{
                      try {
                          loadWindow("/sk.upjs.gui/ToursFXmodel.fxml", "Tours", new TourSceneController(), 420, 302, 420, 302);
                      } catch (IOException e) {
                          e.printStackTrace();
                      }

                  }
          );
            predaj.setOnAction(actionEvent ->{
                        try {
                            loadWindow("/sk.upjs.gui/PredajFXmodel.fxml", "Predaj", new PredajSceneController(), 420, 302, 420, 302);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            );


        }



}



