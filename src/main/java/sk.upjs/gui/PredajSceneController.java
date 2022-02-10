package sk.upjs.gui;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.upjs.entity.Clients;
import sk.upjs.entity.Predaj;
import sk.upjs.entity.Tour;
import sk.upjs.exeption.EntityUndeletableException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.ClientsDAO;
import sk.upjs.storage.dao.PredajDAO;
import sk.upjs.storage.dao.TourDAO;

public class PredajSceneController extends Controller {
    private PredajDAO predajDAO = DaoFactory.INSTANCE.getPredajDAO();
    private ClientsDAO clientsDAO = DaoFactory.INSTANCE.getClientsDAO();
    private TourDAO tourDAO = DaoFactory.INSTANCE.getTourDAO();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button all;

    @FXML
    private TableColumn<Predaj, Object> clientColumn;

    @FXML
    private TextField client_field;

    @FXML
    private TableColumn<Predaj, Date> dateColumn;

    @FXML
    private TextField date_field;
    @FXML
    private ComboBox<Clients> client_combo;
    @FXML
    private ComboBox<Tour> tour_combo;
    @FXML
    private Button delete;

    @FXML
    private TableColumn<Predaj, Long> idColumn;

    @FXML
    private TextField id_field;
    @FXML
    private TableView<Predaj> predajTour;

    @FXML
    private TableColumn<Predaj, Float> priceColumn;

    @FXML
    private TextField price_field;

    @FXML
    private Button search;

    @FXML
    private TableColumn<Predaj, Object> tourColmn;
    @FXML
    private Button clients;
    @FXML
    private Button info;

    @FXML
    private Button oProjekte;

    @FXML
    private Button predaj;
    @FXML
    private Button tour;
    @FXML
    private TextField tour_field;

    @FXML
    private Button update;
    @FXML
    void goClients(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/ClientsFXmodel.fxml", "Clients", new ClientSceneController(), 420, 302, 420, 302);

    }

    @FXML
    void goInfo(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/BookedToursFXmodel.fxml", "Info", new InfoController(), 420, 302, 420, 302);

    }

    @FXML
    void goOprojekte(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/BookedToursFXmodel.fxml", "O projekte", new MainSceneController(), 420, 302, 420, 302);

    }

    @FXML
    void goPredaj(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/PredajFXmodel.fxml", "Predaj", new PredajSceneController(), 420, 302, 420, 302);

    }

    @FXML
    void goTour(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/ToursFXmodel.fxml", "Tours", new TourSceneController(), 420, 302, 420, 302);

    }

    @FXML
    void DeletePredaj(ActionEvent event) throws EntityUndeletableException {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Really delete?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            predajDAO.delete(idfromtext);

        }
        id_field.clear();

    }

    @FXML
        //insert
    void addPredaj(ActionEvent event) throws ParseException {
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String tt = client_field.getText();
        Long clientLong = Long.parseLong(tt);
        String db = date_field.getText();
        Date dated = null;
        dated = formatter2.parse(db);
        String cena = price_field.getText();
        Float cenaprice = Float.parseFloat(cena);
        String tour = tour_field.getText();
        Long tourlong = Long.parseLong(tour);
        Predaj predaj = new Predaj(clientsDAO.getById(clientLong), dated, cenaprice, tourDAO.getById(tourlong));
        predajDAO.save(predaj);
        client_field.clear();
        date_field.clear();
        price_field.clear();
        tour_field.clear();

    }


    @FXML
    void byid(ActionEvent event) {
        String s = id_field.getText();

        if (s.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no id");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get() == ButtonType.OK) {
                alert.close();
            }

        } else {
            Long id = Long.parseLong(s);
            Predaj predaj = predajDAO.getById(id);

            ObservableList<Predaj> predajObservableList = FXCollections.observableArrayList(predaj);
            idColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Long>("id"));
            clientColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("client"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Date>("date"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Float>("price"));
            tourColmn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("tour"));
            predajTour.setItems(predajObservableList);
            id_field.clear();
        }
    }

    @FXML
    void showall(ActionEvent event) {
        List<Predaj> predaj = predajDAO.getAll();
        ObservableList<Predaj> predajObservableList = FXCollections.observableArrayList(predaj);
        idColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Long>("id"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("client"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Date>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Float>("price"));
        tourColmn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("tour"));
        predajTour.setItems(predajObservableList);

    }

    @FXML
    void updatePredaj(ActionEvent event) {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String tt = client_field.getText();
        Long clientLong = Long.parseLong(tt);
        String db = date_field.getText();
        Date dated = null;
        try {
            dated = formatter2.parse(db);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String cena = price_field.getText();
        Float cenaprice = Float.parseFloat(cena);
        String tour = tour_field.getText();
        Long tourlong = Long.parseLong(tour);
        Predaj predaj = new Predaj(idfromtext, clientsDAO.getById(clientLong), dated, cenaprice, tourDAO.getById(tourlong));
        predajDAO.save(predaj);
        client_field.clear();
        date_field.clear();
        price_field.clear();
        tour_field.clear();
        id_field.clear();
    }
    void getallbyid(Long id) {
        Predaj predaj = predajDAO.getById(id);

        id_field.setText(String.valueOf(id));
        String cl = String.valueOf(predaj.getClient().getId());
        System.out.println(cl);
        Long clientid = predaj.getClient().getId();
        client_combo.setValue(clientsDAO.getById(clientid));
        client_field.setText(cl);
        String date = String.valueOf(predaj.getDate());
        date_field.setText(date);
        String pr = String.valueOf(predaj.getPrice());
        price_field.setText(pr);
        String tour = String.valueOf(predaj.getTour().getId());

        tour_field.setText(tour);
        Long tourid = predaj.getTour().getId();
        tour_combo.setValue(tourDAO.getById(tourid));


    }

    @FXML
    void initialize() {
        List<Clients>clientsl = clientsDAO.getAll();
        ObservableList<Clients>clientsObservableList = FXCollections.observableArrayList(clientsl);
        List<Tour> predajs = tourDAO.getAll();
        ObservableList<Tour>tourObservableList = FXCollections.observableList(predajs);
        List<Predaj> predaj = predajDAO.getAll();
        ObservableList<Predaj> predajObservableList = FXCollections.observableArrayList(predaj);
        idColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Long>("id"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("client"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Date>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Predaj, Float>("price"));
        tourColmn.setCellValueFactory(new PropertyValueFactory<Predaj, Object>("tour"));
        predajTour.setItems(predajObservableList);
        client_combo.setItems(clientsObservableList);
        tour_combo.setItems(tourObservableList);
        predajTour.setRowFactory(tv -> {
            TableRow<Predaj> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Predaj rowData = row.getItem();
                    Long idl = rowData.getId();
                    getallbyid(idl);
                    System.out.println(idl);
                }
            });
            return row;
        });

    }

}

