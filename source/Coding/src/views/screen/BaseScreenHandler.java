package views.screen;

import controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Configs;
import views.screen.home.HomeScreenHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.popup.PopupScreen;
import views.screen.rent.ViewBikeRentHandler;
import views.screen.rent.RentHandler;
import controller.ViewBikeController;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class BaseScreenHandler extends FXMLScreenHandler {

	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;

	@FXML
	protected ImageView capstoneImage;

	@FXML
	protected ImageView home;

	@FXML
	protected ImageView card;

	@FXML
	protected ImageView rentalBike;

	private BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	protected void setMenuImage() {
		home.setOnMouseClicked(e -> {
			try {
				if (this.homeScreenHandler != null) {
					this.homeScreenHandler.show();
				} else {
					PopupScreen.error("This button is not set up");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		rentalBike.setOnMouseClicked(e->{
			try{
				if(Configs.bike==null){
					PopupScreen.error("You have no bike rent!!!");
				}else{
					ViewBikeRentHandler viewBikeRentHandler = new ViewBikeRentHandler(this.stage, Configs.VIEW_BIKE_RENT_SCREEN_PATH, Configs.bike);
					viewBikeRentHandler.setHomeScreenHandler(this.homeScreenHandler);
					viewBikeRentHandler.setScreenTitle("Your Rental Bike");
					viewBikeRentHandler.setPreviousScreen(this);
					viewBikeRentHandler.show();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		card.setOnMouseClicked(e->{
			try{
				RentHandler barCode = new RentHandler(this.stage, Configs.RENT_BIKE_PATH);
                barCode.setBController(new ViewBikeController());
                barCode.setScreenTitle("Bar Code");
                barCode.setPreviousScreen(this);
                barCode.setHomeScreenHandler(this.homeScreenHandler);
                barCode.show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		// fix image path caused by fxml
		File file1 = new File(Configs.IMAGE_PATH + "/" + "HomeIcon.png");
		Image img1 = new Image(file1.toURI().toString());
		home.setImage(img1);

		// fix image path caused by fxml
		File file2 = new File(Configs.IMAGE_PATH + "/" + "InfoIcon.png");
		Image img2 = new Image(file2.toURI().toString());
		rentalBike.setImage(img2);

		// fix image path caused by fxml
		File file3 = new File(Configs.IMAGE_PATH + "/" + "rentBikeIcon.png");
		Image img3 = new Image(file3.toURI().toString());
		card.setImage(img3);
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

	protected void setImage(){
		// fix image path caused by fxml
		File file1 = new File(Configs.IMAGE_PATH + "/" + "Splash.png");
		Image img1 = new Image(file1.toURI().toString());
		capstoneImage.setImage(img1);

	}

}
