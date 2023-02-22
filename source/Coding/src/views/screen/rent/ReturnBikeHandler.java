package views.screen.rent;

import controller.*;
import entity.bike.Bike;
import entity.bike.BikeHandler;
import entity.costcalculator.Strategy1;
import entity.payment.RentTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.DockReturnScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    @FXML
    private Label rentBikeID;

    @FXML
    private Label rentStartTime;

    @FXML
    private Label rentEndTime;

    @FXML
    private Label rentDeposit;

    @FXML
    private Label rentFee;

    @FXML
    private Label totalAmount;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnCancelReturn;

    private RentTransaction rentTransaction;
    public ReturnBikeHandler(Stage stage, String screenPath, RentTransaction rentTransaction) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.rentTransaction = rentTransaction;
        setUp();
    }

    private void setUp() throws SQLException {
        rentTransaction.endRent();

        LOGGER.info(rentTransaction.getRentTime());
        LOGGER.info(rentTransaction.getReturnTime());

        Configs.bike.SetRentStrategy(new Strategy1());
        rentTransaction.setRentCost((int) Configs.bike.calculateRentCost(rentTransaction.getRentTime(), rentTransaction.getReturnTime()));

        rentBikeID.setText(OutputController.Convert(Integer.toString(rentTransaction.getBikeCode())));

        rentDeposit.setText(Integer.toString(rentTransaction.getDepositeCost()));

        rentStartTime.setText(rentTransaction.getRentTime());

        rentEndTime.setText(rentTransaction.getReturnTime());

        rentFee.setText(Integer.toString(rentTransaction.getRentCost()));

        String method = "";
        int total = -rentTransaction.getRentCost()+rentTransaction.getDepositeCost();

        if(total >= 0) method = "Refund";
        else{
            method = "Pay";
            total = -total;
        }

        totalAmount.setText(method + " " + Integer.toString(total) + " VNĐ");

        btnPayment.setOnMouseClicked(e -> {
            try{
                PaymentScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN, rentTransaction);
                paymentScreen.setBController(new PaymentController());
                paymentScreen.setScreenTitle("Payment");
                paymentScreen.setPreviousScreen(this);
                paymentScreen.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreen.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnCancelReturn.setOnMouseClicked(e -> {
            try{
                this.getPreviousScreen().show();
                LOGGER.info("Cancel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

}
