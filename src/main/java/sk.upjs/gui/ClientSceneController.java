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
import sk.upjs.entity.Tour;
import sk.upjs.exeption.EntityUndeletableException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.ClientsDAO;

public class ClientSceneController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adressa_field;
    @FXML
    private TextField meno_field;

    @FXML
    private TableColumn<Clients, String> adressaColumn;

    @FXML
    private Button all;

    @FXML
    private Button bookedtours;

    @FXML
    private TextField cislo_field;

    @FXML
    private TableColumn<Clients, String> cisloCloumn;
    @FXML
    private TextField priezvisko_field;
    @FXML
    private Button clientAdd;

    @FXML
    private Button clientDelete;

    @FXML
    private Button clientEdit;

    @FXML
    private Button clients;

    @FXML
    private TableView<Clients> clientsView;

    @FXML
    private TableColumn<Clients, Date> dat_narColumn;

    @FXML
    private TextField datnar;
    private ClientsDAO clientsDAO = DaoFactory.INSTANCE.getClientsDAO();
    @FXML
    private TextField id;

    @FXML
    private TableColumn<Clients, Long> idColumn;

    @FXML
    private TextField meno;

    @FXML
    private TableColumn<Clients, String> menoColumn;

    @FXML
    private Button oprojekte;

    @FXML
    private Button predaj;


    @FXML
    private TextField priezvisko;

    @FXML
    private TableColumn<Clients, String> priezviskoColumn;

    @FXML
    private Button search;

    @FXML
    private Button tour;

    @FXML
        //delete
    void DeleteClient(ActionEvent event) throws EntityUndeletableException {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Really delete?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            clientsDAO.delete(idfromtext);

        }
        id_field.clear();


    }

    @FXML
        //insert
    void addClient(ActionEvent event) {
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String name = meno_field.getText();
        String surname = priezvisko_field.getText();
        String datnat = datnar.getText();
        Date datee = null;
        try {
            datee = formatter2.parse(datnat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String adressa = adressa_field.getText();
        String cislo = cislo_field.getText();
        Clients clients = new Clients(name, surname, datee, adressa, cislo);
        clientsDAO.save(clients);
        clear();

    }



    @FXML
        //update
    void saveClient(ActionEvent event) {
        String s = id_field.getText();
        Long idfromtext = Long.parseLong(s);
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String name = meno_field.getText();
        String surname = priezvisko_field.getText();
        String datnat = datnar.getText();
        Date datum = null;
        try {
            datum = formatter2.parse(datnat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String adressa = adressa_field.getText();
        String cislo = cislo_field.getText();
        Clients clients = new Clients(idfromtext, name, surname, datum, adressa, cislo);
        clientsDAO.save(clients);
        clear();


    }

    @FXML
    private TextField id_field;

    @FXML
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
            Clients clients = clientsDAO.getById(idfromtext);
            ObservableList<Clients> clientsObservableList = FXCollections.observableArrayList();
            clientsObservableList.add(clients);
            idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Long>("id"));
            menoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("meno"));
            priezviskoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("priezvisko"));
            dat_narColumn.setCellValueFactory(new PropertyValueFactory<Clients, Date>("dat_nar"));
            adressaColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("adressa"));
            cisloCloumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("cislo"));
            clientsView.setItems(clientsObservableList);
            id_field.clear();
        }
    }

    @FXML
    void goClients(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/ClientsFXmodel.fxml", "Clients", new ClientSceneController(), 420, 302, 420, 302);

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
    void goInfo(ActionEvent event) throws IOException {
        loadWindow("/sk.upjs.gui/BookedToursFXmodel.fxml", "Info", new InfoController(), 420, 302, 420, 302);

    }
    @FXML
    void showall(ActionEvent event) {
        //   List<Clients> clientsList = clientsDAO.getAll();
        //  ObservableList<Clients> clientsObservableList = FXCollections.observableList(clientsList);
        List<Clients> clientsList = clientsDAO.getAll();
        ObservableList<Clients> clientsObservableList = FXCollections.observableArrayList(clientsList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Long>("id"));
        menoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("meno"));
        priezviskoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("priezvisko"));
        dat_narColumn.setCellValueFactory(new PropertyValueFactory<Clients, Date>("dat_nar"));
        adressaColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("adressa"));
        cisloCloumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("cislo"));
        clientsView.setItems(clientsObservableList);


    }
    void clear (){
        id_field.clear();
        meno_field.clear();
        priezvisko_field.clear();
        datnar.clear();
        cislo_field.clear();
        adressa_field.clear();
    }

    void getallbyid(Long id) {
        id_field.setText(String.valueOf(id));
        Clients clients = clientsDAO.getById(id);
        meno_field.setText(clients.getMeno());
        priezvisko_field.setText(clients.getPriezvisko());
        datnar.setText(String.valueOf(clients.getDat_nar()));
        cislo_field.setText(clients.getCislo());
        adressa_field.setText(clients.getAdressa());
    }
    @FXML
    void initialize() {
        List<Clients> clientsList = clientsDAO.getAll();
        ObservableList<Clients> clientsObservableList = FXCollections.observableArrayList(clientsList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Long>("id"));
        menoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("meno"));
        priezviskoColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("priezvisko"));
        dat_narColumn.setCellValueFactory(new PropertyValueFactory<Clients, Date>("dat_nar"));
        adressaColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("adressa"));
        cisloCloumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("cislo"));
        clientsView.setItems(clientsObservableList);
        clientsView.setRowFactory(tv -> {
            TableRow<Clients> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Clients rowData = row.getItem();
                    Long idl = rowData.getId();
                    getallbyid(idl);
                    System.out.println(idl);
                }
            });
            return row;
        });

    }

}

