package views.screen.rent;

import controller.OutputController;
import controller.PaymentController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.costcalculator.DepositCostCalculator;
import entity.costcalculator.DepositStrategy;
import entity.payment.RentTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.BikeDockScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class RentInfoHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(RentInfoHandler.class.getName());
    @FXML
    private Label barCode;

    @FXML
    private Label type;

    @FXML
    private Label saddle;

    @FXML
    private  Label pedal;

    @FXML
    private Label dock;

    @FXML
    private Label licensePlates;

    @FXML
    private Label value;

    @FXML
    private Label deposit;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnCancel;

    private final Bike bike;

    private DepositCostCalculator depositCostCalculator = new DepositStrategy();
    public RentInfoHandler(Stage stage, String screenPath, Bike bike) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.bike = bike;
        bike.updateStatus();

        // set logo
        setImage();

        // set info
        setInvoiceInfo();
    }

    private void setInvoiceInfo() {
        barCode.setText(OutputController.Convert(Integer.toString(bike.getId())));

        saddle.setText(Integer.toString(bike.getNumOfSaddle()));

        dock.setText(Integer.toString(bike.getDockID()));

        pedal.setText(bike.getNumOfPedal()+" pedals");

        licensePlates.setText(bike.getLicensePlate());

        type.setText(bike.getType());

        value.setText(bike.getValueOfBike()+" VND");

        deposit.setText(depositCostCalculator.calculateDepositCost(bike) +" VND");

        btnPayment.setOnMouseClicked(e -> {
            try{
                // Thanh toán
                RentTransaction rentTransaction = new RentTransaction();
                rentTransaction.setBikeCode(bike.getId());
                rentTransaction.setDepositeCost(bike);

                PaymentScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN, rentTransaction);
                paymentScreen.setBController(new PaymentController());
                paymentScreen.setScreenTitle("Payment");
                paymentScreen.setPreviousScreen(this);
                paymentScreen.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreen.show();

            }catch (Exception ex){
                LOGGER.info("Payment failed!");
                ex.printStackTrace();
            }
        });

        btnCancel.setOnMouseClicked(e -> {
            try{
                this.getPreviousScreen().show();
                LOGGER.info("Cancel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public Bike getBike() {
        return bike;
    }
}
