package views.screen.home;

import controller.DockReturnController;
import controller.HomeController;
import controller.ReturnBikeController;
import controller.ViewDockController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rent.ReturnBikeHandler;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DockReturnScreenHandler extends BaseScreenHandler implements Initializable{
    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
    @FXML
    private VBox vboxDock;
    @FXML
    private HBox hboxDock;
    @FXML
    private Text dockTitle;
    @FXML
    private Button historyBtn;

    private List homeItems;
    public DockReturnScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setMenuImage();
        setImage();
    }

    public DockReturnController getBController(){
        return (DockReturnController) super.getBController();
    }

    @Override
    public void show() {
        setupScreen();
        super.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new DockReturnController());
    }

    public void setupScreen(){
        try{
            dockTitle.setText("Danh sách bãi xe còn trống");
            List medium = getBController().getAllDock();
            this.homeItems = new ArrayList<>();
            for (Object object : medium){
                Dock dock = (Dock) object;
                DockHandler d = new DockHandler(Configs.HOME_DOCK_PATH, dock, this.homeScreenHandler, this.stage);
                this.homeItems.add(d);
            }
        } catch (SQLException|IOException e) {
            LOGGER.info("Errors occured when get home items");
            e.printStackTrace();
        }

        historyBtn.setOnMouseClicked(e -> {
            try {
                displayTransactionScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addDockReturn();
    }
    private void addDockReturn() {
        vboxDock.getChildren().clear();
        for(Object object: this.homeItems){
            DockHandler dock = (DockHandler) object;
            dock.dockView.setText("Choose");
            dock.dockView.setOnMouseClicked(e -> {
                try {
                    Configs.indexDockReturnBike = dock.getDock().getDockId();
                    ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(this.stage, Configs.RETURN_BIKE_SCREEN_PATH, Configs.rentTransaction);
                    returnBikeHandler.setBController(new ReturnBikeController());
                    returnBikeHandler.setHomeScreenHandler(this.homeScreenHandler);
                    returnBikeHandler.setScreenTitle("Return Bike");
                    returnBikeHandler.setPreviousScreen(this);
                    returnBikeHandler.show();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            vboxDock.getChildren().add(dock.getContent());
        }
        return;
    }
    public void displayTransactionScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Configs.TRANSACTION_SCREEN_PATH));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Transaction History");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
