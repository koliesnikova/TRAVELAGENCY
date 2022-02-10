package sk.upjs.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Type_tour;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.Druh_jedlaDAO;
import sk.upjs.storage.dao.HotelDAO;
import sk.upjs.storage.dao.Type_tourDAO;
import sk.upjs.storage.dao.Type_umiestneniaDAO;

public class InfoController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Druh_jedla> allDruhJedla;

    @FXML
    private ListView<Hotel> allHotels;
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
    private ListView<Type_tour> allTypeTour;

    @FXML
    private ListView<Type_umiestnenia> allTypeumiest;
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
    void initialize() {
        // hotel
        HotelDAO hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
        List<Hotel> hotels = hotelDAO.getAllHotels();
        ObservableList<Hotel> items =FXCollections.observableArrayList ();
        items.addAll(hotels);
        allHotels.setItems(items);
        //druh jedla
        Druh_jedlaDAO druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
        List<Druh_jedla> druh_jedlas = druh_jedlaDAO.getAll();
        ObservableList<Druh_jedla> itemsdj =FXCollections.observableArrayList ();
        itemsdj.addAll(druh_jedlas);
        allDruhJedla.setItems(itemsdj);
        //type tour
        Type_tourDAO type_tourDAO = DaoFactory.INSTANCE.getType_tourDAO();
        List<Type_tour> type_tours = type_tourDAO.getAll();
        ObservableList<Type_tour> tt =FXCollections.observableArrayList ();
        tt.addAll(type_tours);
        allTypeTour.setItems(tt);
        // type umiest
        Type_umiestneniaDAO type_umiestneniaDAO = DaoFactory.INSTANCE.getType_umiestneniaDAO();
        List<Type_umiestnenia> type_umiestnenias = type_umiestneniaDAO.getAll();
        ObservableList<Type_umiestnenia> tu =FXCollections.observableArrayList ();
        tu.addAll(type_umiestnenias);
        allTypeumiest.setItems(tu);



    }

}

