package views.screen.dock;

import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BikeDockScreenHandler extends FXMLScreenHandler {
    @FXML
    protected ImageView bikeImage;

    @FXML
    protected Label bikeSeat;

    @FXML
    protected Label bikeType;

    @FXML
    protected Label bikeValue;

    @FXML
    protected Button bikeView;

    private static final Logger LOGGER = Utils.getLogger(BikeDockScreenHandler.class.getName());
    private final Bike bike;

    public BikeDockScreenHandler(String screenPath, Bike bike) throws IOException {
        super(screenPath);
        this.bike = bike;
        setBikeInfo();
    }

    private void setBikeInfo() {
        File file = new File(bike.getImageURL());
        Image image = new Image(file.toURI().toString());

        bikeImage.setFitHeight(160);
        bikeImage.setFitWidth(152);
        bikeImage.setImage(image);

        bikeSeat.setText(Integer.toString(bike.getNumOfSeat()));

        bikeType.setText(bike.getType());
        bikeValue.setText(Integer.toString(bike.getValueOfBike()));
    }
}
