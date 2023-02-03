package views.screen.home;

import controller.HomeController;
import controller.ViewDockController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable{
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
    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setMenuImage();
        setImage();
    }

    public HomeController getBController(){
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        setupScreen();
        super.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new HomeController());
    }

    public void setupScreen(){
        try{
            dockTitle.setText("Danh sách bãi xe");
            List medium = getBController().getAllDock();
            this.homeItems = new ArrayList<>();
            for (Object object : medium){
                Dock dock = (Dock) object;
                DockHandler d = new DockHandler(Configs.HOME_DOCK_PATH, dock, this, this.stage);
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

        addDockHome();
    }
    private void addDockHome() {
        vboxDock.getChildren().clear();
        for(Object object: this.homeItems){
            DockHandler dock = (DockHandler) object;
            dock.dockView.setText("View");
            dock.dockView.setOnMouseClicked(e -> {
                try{
                    LOGGER.info("User click to view dock");
                    DockScreenHandler dockScreen = new DockScreenHandler(this.stage, Configs.DOCK_SCREEN_PATH, dock.getDock());
                    dockScreen.setHomeScreenHandler(this);
                    dockScreen.setBController(new ViewDockController());
                    dockScreen.setScreenTitle("Dock View");
                    dockScreen.setPreviousScreen(this);
                    dockScreen.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
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
