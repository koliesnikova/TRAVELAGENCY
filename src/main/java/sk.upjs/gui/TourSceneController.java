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
import javafx.scene.layout.AnchorPane;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Tour;
import sk.upjs.entity.Type_tour;
import sk.upjs.exeption.EntityUndeletableException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.Druh_jedlaDAO;
import sk.upjs.storage.dao.HotelDAO;
import sk.upjs.storage.dao.TourDAO;
import sk.upjs.storage.dao.Type_tourDAO;

public class TourSceneController extends Controller {
    private TourDAO tourDAO = DaoFactory.INSTANCE.getTourDAO();

    private Type_tourDAO type_tourDAO = DaoFactory.INSTANCE.getType_tourDAO();
    private Druh_jedlaDAO druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
    private HotelDAO hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
    List<Type_tour> tt = type_tourDAO.getAll();
    ObservableList<Type_tour> type_tourObservableList = FXCollections.observableArrayList(tt);
    List<Druh_jedla> dj = druh_jedlaDAO.getAll();
    ObservableList<Druh_jedla> druh_jedlaObservableList = FXCollections.observableArrayList(dj);
    List<Hotel> ht = hotelDAO.getAllHotels();
    ObservableList<Hotel> hotelObservableList = FXCollections.observableList(ht);
    @FXML
    private ResourceBundle resources;
    @FXML
    private ComboBox<Type_tour> tt_box;
    @FXML
    private URL location;

    @FXML
    private Button TourAdd;

    @FXML
    private TableColumn<Tour, Object> djColumn;
    @FXML
    private ComboBox<Druh_jedla> dj_box;

    @FXML
    private ComboBox<Hotel> h_box;
    @FXML
    private Button all;

    @FXML
    private TableColumn<Tour, Object> hotelCloumn;

    @FXML
    private TableView<Tour> toursView;

    @FXML
    private Label d;

    @FXML
    private TableColumn<Tour, Date> deColumn;

    @FXML
    private TextField de_field;

    @FXML
    private AnchorPane deleteTour;

    @FXML
    private TextField dj_field;

    @FXML
    private TextField hotel_field;

    @FXML
    private TableColumn<Tour, Long> idColumn;

    @FXML
    private TextField id_field;

    @FXML
    private TableColumn<Tour, Object> ttColumn;

    @FXML
    private TableColumn<Tour, Date> dbColumn;

    @FXML
    private TextField db_field;

    @FXML
    private Button search;

    @FXML
    private Button tourDelete;

    @FXML
    private Button tourUpdate;

    @FXML
    private TextField tt_field;

    @FXML
    void DeleteClient(ActionEvent event) throws EntityUndeletableException {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Really delete?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            tourDAO.delete(idfromtext);

        }
        updatetable();
        id_field.clear();
        clear();


    }

    @FXML
        //insert
    void addClient(ActionEvent event) {

        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String tt = tt_field.getText();
        Long ttlong = Long.parseLong(tt);
        String db = db_field.getText();
        String de = de_field.getText();
        Date datebegin = null;
        Date dateend = null;
        try {
            datebegin = formatter2.parse(db);
            dateend = formatter2.parse(de);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Type_tourDAO type_tourDAO = DaoFactory.INSTANCE.getType_tourDAO();
        HotelDAO hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
        Druh_jedlaDAO druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
        String dj = dj_field.getText();
        Long djlong = Long.parseLong(dj);
        String hotel = hotel_field.getText();
        Long hotelLONG = Long.parseLong(hotel);
        Tour tour = new Tour(type_tourDAO.getById(ttlong), datebegin, dateend, druh_jedlaDAO.getById(djlong), hotelDAO.getById(hotelLONG));
        tourDAO.save(tour);
        clear();
        updatetable();


    }

    private void clear() {
        tt_field.clear();
        db_field.clear();
        de_field.clear();
        dj_field.clear();
        hotel_field.clear();
        tt_box.setValue(null);
        ObservableList os = FXCollections.observableArrayList();
        tt_box.setItems(os);
        dj_box.setItems(os);
        h_box.setItems(os);
    }

    //update
    @FXML
    void saveClient(ActionEvent event) {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String tt = tt_field.getText();
        Long ttlong = Long.parseLong(tt);
        String db = db_field.getText();
        String de = de_field.getText();
        System.out.println(ttlong);
        Date datebegin = null;
        Date dateend = null;
        try {
            datebegin = formatter2.parse(db);
            dateend = formatter2.parse(de);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Type_tourDAO type_tourDAO = DaoFactory.INSTANCE.getType_tourDAO();
        HotelDAO hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
        Druh_jedlaDAO druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
        String dj = dj_field.getText();
        Long djlong = Long.parseLong(dj);
        String hotel = hotel_field.getText();
        Long hotelLONG = Long.parseLong(hotel);
        Tour tour = new Tour(idfromtext, type_tourDAO.getById(ttlong), datebegin, dateend, druh_jedlaDAO.getById(djlong), hotelDAO.getById(hotelLONG));
        tourDAO.save(tour);
        id_field.clear();
        clear();

    }

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
    private Button Info;

    @FXML
    private Button Oprojekte;

    @FXML
    private Button Predaj;

    @FXML
    private Button Tour;

    @FXML
    private Button Clients;

    @FXML
        //by id
    void search(ActionEvent event) {
        String s = id_field.getText();

        if (s.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no id");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get() == ButtonType.OK) {
                alert.close();
            }

        } else {
            Long idfromtext = Long.parseLong(s);
            Tour tour = tourDAO.getById(idfromtext);

            ObservableList<Tour> tourObservableList = FXCollections.observableArrayList();
            tourObservableList.add(tour);


            idColumn.setCellValueFactory(new PropertyValueFactory<Tour, Long>("id"));
            ttColumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("type_tour"));
            dbColumn.setCellValueFactory(new PropertyValueFactory<Tour, Date>("date_begin"));
            deColumn.setCellValueFactory(new PropertyValueFactory<Tour, Date>("date_end"));
            djColumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("druh_jedla"));
            hotelCloumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("hotel"));
            toursView.setItems(tourObservableList);
            id_field.clear();

        }
    }

    //
    void getallbyid(Long id) {
        Tour tour = tourDAO.getById(id);
        String tt = String.valueOf(tour.getType_tour().getId());
        String dj = String.valueOf(tour.getDruh_jedla().getId());
        String ht = String.valueOf(tour.getHotel().getId());
        Long idtt = tour.getType_tour().getId();
        Long idt= tour.getDruh_jedla().getId();
        Long idh = tour.getHotel().getId();
        tt_box.setValue(type_tourDAO.getById(idtt));
        dj_box.setValue(druh_jedlaDAO.getById(idt));
        h_box.setValue(hotelDAO.getById(idh));
        id_field.setText(String.valueOf(id));
        tt_field.setText(tt);
        dj_field.setText(dj);
        hotel_field.setText(ht);
        Date date_begin = tour.getDate_begin();
        Date date_end = tour.getDate_end();
        db_field.setText(String.valueOf(date_begin));
        de_field.setText(String.valueOf(date_end));
        String drj = String.valueOf(tour.getDruh_jedla().getId());
        dj_field.setText(dj);
        String hotel = String.valueOf(tour.getHotel().getId());
        hotel_field.setText(hotel);
    }

    @FXML
    void showall(ActionEvent event) {
        updatetable();
    }

    public void updatetable() {
        List<Tour> tours = tourDAO.getAll();
        ObservableList<Tour> tourObservableList = FXCollections.observableArrayList(tours);
        idColumn.setCellValueFactory(new PropertyValueFactory<Tour, Long>("id"));
        ttColumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("type_tour"));
        dbColumn.setCellValueFactory(new PropertyValueFactory<Tour, Date>("date_begin"));
        deColumn.setCellValueFactory(new PropertyValueFactory<Tour, Date>("date_end"));
        djColumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("druh_jedla"));
        hotelCloumn.setCellValueFactory(new PropertyValueFactory<Tour, Object>("hotel"));
        toursView.setItems(tourObservableList);
        tt_box.setItems(type_tourObservableList);
        dj_box.setItems(druh_jedlaObservableList);
        h_box.setItems(hotelObservableList);
        tt_box.setOnAction(event -> {
            String s = String.valueOf(tt_box.getSelectionModel().getSelectedItem());
            Type_tour idBy = (type_tourDAO.idByName(s));
            tt_field.setText(String.valueOf(idBy));
        });
        dj_box.setOnAction(event -> {
            String s1 = String.valueOf(dj_box.getSelectionModel().getSelectedItem());
            Druh_jedla iddj = (druh_jedlaDAO.idByName(s1));
            dj_field.setText(String.valueOf(iddj));
        });
        h_box.setOnAction(event -> {
            String s2 = String.valueOf(h_box.getSelectionModel().getSelectedItem());
            Hotel idh = (hotelDAO.idByName(s2));
            System.out.println(idh);
            hotel_field.setText(String.valueOf(idh));
        });
    }


    @FXML
    void initialize() {
        updatetable();
        toursView.setRowFactory(tv -> {
            TableRow<Tour> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Tour rowData = row.getItem();
                    Long idl = rowData.getId();
                    getallbyid(idl);
                    System.out.println(idl);
                }
            });
            return row;
        });

    }


}

