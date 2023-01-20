package views.screen.rent;

import controller.DockReturnController;
import controller.OutputController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.DockReturnScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ViewBikeRentHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(ViewBikeRentHandler.class.getName());

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
    private Button btnReturnBike;

    @FXML
    private TextArea advancedInfo;

    private Bike bike;

    public ViewBikeRentHandler(Stage stage, String screenPath, Bike bike) throws IOException {
        super(stage, screenPath);

        setMenuImage();
        setImage();

        this.bike = bike;

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

        bikeValue.setText(Configs.rentTransaction.getDepositeCost()+" VND");
        bikeValue.setEditable(false);

        bikeStatus.setText(Configs.rentTransaction.getRentTime());
        bikeStatus.setEditable(false);

        advancedInfo.setText(bike.getAdvancedInfo());
        advancedInfo.setEditable(false);

        btnReturnBike.setOnMouseClicked(e -> {
            try{
                DockReturnScreenHandler dockReturnScreen = new DockReturnScreenHandler(this.stage, Configs.HOME_PATH);
                dockReturnScreen.setBController(new DockReturnController());
                dockReturnScreen.setScreenTitle("dockReturnScreen");
                dockReturnScreen.setPreviousScreen(this);
                dockReturnScreen.setHomeScreenHandler(this.homeScreenHandler);
                dockReturnScreen.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
