package views.screen.rent;

import controller.InputController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.BikeHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.dock.BikeDockScreenHandler;
import views.screen.popup.PopupScreen;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class RentHandler extends BaseScreenHandler{
    public static Logger LOGGER = Utils.getLogger(RentHandler.class.getName());
    @FXML
    private TextField barCode;

    @FXML
    private Button btnRent;

    @FXML
    private Button btnCancel;

    private Bike bike;

    public RentHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        // set logo
        setImage();

        // set info
        setBarCode();

    }

    private void setBarCode() {

        barCode.setEditable(true);

        btnRent.setOnMouseClicked(e -> {
            try {
                LOGGER.info("User click to see bike info");
                if(Configs.rentTransaction!=null){
                    PopupScreen.error("You already rent a bike! \n Please return bike first!");
                }else {
                    Bike _bike = BikeHandler.getBikeById(InputController.Convert(barCode.getText()));
                    if(_bike == null){
                        PopupScreen.error("Bike not exist!");
                        return;
                    }
                    this.bike = _bike;
                    _bike.updateStatus();


                    try {
                        BikeScreenHandler b1 = new BikeScreenHandler(this.stage, Configs.BIKE_SCREEN_PATH, bike);
                        b1.setHomeScreenHandler(this.homeScreenHandler);
                        b1.setBController(new ViewBikeController());
                        b1.setScreenTitle("View Bike");
                        b1.setPreviousScreen(this);
                        b1.show();
                    }catch (Exception ex){
                        LOGGER.info("Error occur when setup bike Screen");
                        ex.printStackTrace();
                    }
                }

            } catch (Exception ex) {
                LOGGER.info("Rent bike failed!");
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
}
