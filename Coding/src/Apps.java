import entity.bike.Bike;
import entity.bike.BikeHandler;
import entity.payment.RentTransaction;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.SQLException;

public class Apps extends Application {
    @FXML
    ImageView logo;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // initialize the scene
            Parent root = FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SplashScreen");
            primaryStage.show();

            // Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            // Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            // After fade in, start fade out
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            // After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                try {
                    try {
                        RentTransaction rentTransaction = RentTransaction.getRentTransactionUncompleted();
                        Bike bike = null;
                        if(rentTransaction!= null) {
                            bike = BikeHandler.getBikeById(rentTransaction.getBikeCode());
                            Configs.rentTransaction = rentTransaction;
                            if(bike != null)
                                Configs.bike = bike;
                        }

                    }
                    catch(SQLException ee){
                        ee.printStackTrace();
                    }
                    HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
                    homeHandler.setScreenTitle("Ecobike Main Home");
                    homeHandler.setHomeScreenHandler(homeHandler);
                    homeHandler.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
