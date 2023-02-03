package controller;

import com.sun.jdi.IntegerValue;
import entity.payment.RentTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransactionController extends BaseController implements Initializable {

    @FXML
    private TableView<RentTransaction> table;
    @FXML
    private TableColumn<RentTransaction, Integer> idColumn;
    @FXML
    private TableColumn<RentTransaction, String> barCodeColumn;
    @FXML
    private TableColumn<RentTransaction, String> rentTimeColumn;
    @FXML
    private TableColumn<RentTransaction, String> returnTimeColumn;
    @FXML
    private TableColumn<RentTransaction, Integer> rentCostColumn;
    @FXML
    private TableColumn<RentTransaction, Integer> depositColumn;

    private ObservableList<RentTransaction> transactionList;

    /**
     *
     * Setup lich su giao dich
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            transactionList = FXCollections.observableArrayList(RentTransaction.getAllCompletedTransaction());
            idColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, Integer>("rentalCode"));
            barCodeColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, String>("barcode"));
            rentTimeColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, String>("rentTime"));
            returnTimeColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, String>("returnTime"));
            rentCostColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, Integer>("rentCost"));
            depositColumn.setCellValueFactory(new PropertyValueFactory<RentTransaction, Integer>("depositeCost"));
            table.setItems(transactionList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
