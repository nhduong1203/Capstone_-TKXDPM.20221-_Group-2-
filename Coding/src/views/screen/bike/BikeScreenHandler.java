package views.screen.bike;

import controller.OutputController;
import controller.ViewBikeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.BikeDockScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rent.RentHandler;
import views.screen.rent.RentInfoHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BikeScreenHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());
    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField bikeID;

    @FXML
    private TextField bikeType;

    @FXML
    private  TextField bikeValue;

    @FXML
    private TextField bikeSeats;

    @FXML
    private TextField bikeStatus;

    @FXML
    private TextArea advancedInfo;

    @FXML
    private Button btnRentBike;

    private final Bike bike;
    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.bike = bike;
        bike.updateStatus();

        // set logo
        setImage();

        // set info
        setBikeInfo();
    }

    private void setBikeInfo() {
        File file = new File(bike.getImageURL());
        Image image = new Image(file.toURI().toString());
        bikeImage.setFitHeight(169);
        bikeImage.setFitWidth(186);
        bikeImage.setImage(image);

        bikeID.setText(OutputController.Convert(Integer.toString(bike.getId())));
        bikeID.setEditable(false);

        bikeSeats.setText(Integer.toString(bike.getNumOfSeat()));
        bikeSeats.setEditable(false);

        bikeType.setText(bike.getType());
        bikeType.setEditable(false);

        bikeValue.setText(bike.getValueOfBike()+" VND");
        bikeValue.setEditable(false);

        bikeStatus.setText(bike.getBikeStatus());
        bikeStatus.setEditable(false);

        advancedInfo.setText(bike.getAdvancedInfo());
        advancedInfo.setEditable(false);

        btnRentBike.setOnMouseClicked(e -> {
            try{
                //bike.setStatus(-1);
                //bike.rentBike();
                //setBikeInfo();
//                LOGGER.info("User click to view barcode");

                // Open BarCode Screen

//                RentHandler barCode = new RentHandler(this.stage, Configs.RENT_BIKE_PATH, bike);
//                barCode.setBController(new ViewBikeController());
//                barCode.setScreenTitle("Bar Code");
//                barCode.setPreviousScreen(this);
//                barCode.setHomeScreenHandler(this.homeScreenHandler);
//                barCode.show();

                LOGGER.info("User click to see invoice payment");
                if(Configs.rentTransaction!=null){
                    PopupScreen.error("You already rent a bike!!!");
                }else {
                    RentInfoHandler invoice = new RentInfoHandler(this.stage, Configs.INVOICE_PATH, bike);
                    invoice.setBController(new ViewBikeController());
                    invoice.setScreenTitle("Invoice");
                    invoice.setPreviousScreen(this);
                    invoice.setHomeScreenHandler(this.homeScreenHandler);
                    invoice.show();
                }

            }catch (Exception ex){
                LOGGER.info("Rent bike failed!");
                ex.printStackTrace();
            }
        });
    }
}
