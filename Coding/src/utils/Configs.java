package utils;

import entity.bike.Bike;
import entity.payment.RentTransaction;
import javafx.scene.paint.Color;

import java.util.Map;

/**
 * @author nhom2 configs file
 */
public class Configs {
	public static final Map<String, String> PAYMENT_RESULT_IMAGE = Map.of(
			"PAYMENT SUCCESSFUL!", "tickgreen.png",
			"PAYMENT FAILED!", "tickerror.jpg"
	);
	public static final Map<String, Color> PAYMENT_RESULT_COLOR = Map.of(
			"PAYMENT SUCCESSFUL!", Color.GREEN,
			"PAYMENT FAILED!", Color.RED
	);
	// User info
	// User rental Bike
	public static Bike bike = null;
	// User transaction
	public static RentTransaction rentTransaction = null;
	// Index dock return bike
	public static int indexDockReturnBike = 0;
	// static resource
	public static final String IMAGE_PATH = "assets/images";
	public static final String DOCK_BIKE_PATH = "/views/fxml/bike_dock.fxml";
	public static final String DOCK_SCREEN_PATH = "/views/fxml/viewdock.fxml";
	public static final String RESULT_SCREEN_PATH = "/views/fxml/success_payment.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
	public static final String TRANSACTION_SCREEN_PATH = "/views/fxml/transactionHistoryScreen.fxml";
	public static final String BIKE_SCREEN_PATH = "/views/fxml/viewbike.fxml";
	public static final String INVOICE_PATH = "/views/fxml/invoice.fxml";
	public static final String RENT_BIKE_PATH = "/views/fxml/rentbike.fxml";
	public static final String PAYMENT_SCREEN = "/views/fxml/payment.fxml";
	public static final String HOME_PATH = "/views/fxml/home.fxml";
	public static final String HOME_DOCK_PATH = "/views/fxml/dock_home.fxml";
	public static final String POPUP_PATH = "/views/fxml/popup.fxml";
	public static final String VIEW_BIKE_RENT_SCREEN_PATH = "/views/fxml/viewbikerent.fxml";
	public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/returnbike.fxml";

}