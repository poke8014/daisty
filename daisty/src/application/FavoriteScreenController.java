package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import daisty.Recipe;
import daisty.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FavoriteScreenController {
    User user = new User();
    ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<String> favList;

    @FXML
    private Button getRecipesBtn;

    @FXML
    private Button favoritesBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private GridPane favGrid;
    @FXML
    void initialize(User user)
    {
    	this.user = user;
        favList = user.getFavNames();
        recipes = user.getFavorites();
    	fillFavGrid();
    	
		favoritesBtn.getStyleClass().clear();	// disable favorite button
		favoritesBtn.getStyleClass().add("side-button-on");
    }
    
    @FXML
    private void fillFavGrid() {
    	int imageCol = 1;
    	int imageRow = 1;
    	for (int i = 0; i < favList.size(); i++) {
    		File file = new File("src/recipeInfo/recipeImage/" + favList.get(i) + ".jpeg");	// retrieve image source
    		Image favImage = new Image(file.toURI().toString());	// create javafx Image
    		// place image into ImageView
    		ImageView pic = new ImageView();
    		pic.setFitHeight(160);
    		pic.setFitWidth(160);
    		pic.setImage(favImage);

    		//click on image for detailed recipe
            Recipe r = recipes.get(i);
            pic.setPickOnBounds(true);
            pic.setOnMouseClicked((MouseEvent e) -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BackToFavorite.fxml"));
                Parent mainSceneParent = null;
                try {
                    mainSceneParent = loader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene mainScene = new Scene(mainSceneParent);
                Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                BackToFavoriteController controller = loader.getController();
                controller.initialize(r);
                appStage.setScene(mainScene);
                appStage.show();
            });

    		favGrid.setPadding(new Insets(20));
    		favGrid.setHgap(25);
    		favGrid.setVgap(25);
    		favGrid.add(pic, imageCol, imageRow);	// add image to grid
    		imageCol++;								// move one over in row
    		
    		// makes sure only 3 images per row
    		if (imageCol > 3) {
    			imageCol = 1;
    			imageRow++;
    		}
    	}
    }
    // returns user to recipe screen
    @FXML
    void toRecipes(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        appStage.setUserData(user);
        appStage.setScene(mainScene);
        appStage.show();
    }
    // sends user to settings screen
    @FXML
    void toSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsScreen.fxml"));
        Parent mainSceneParent = loader.load();
        Scene mainScene = new Scene(mainSceneParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        SettingsScreenController controller = loader.getController();
        controller.initialize(user);
        appStage.setScene(mainScene);
        appStage.show();
    }
    // logs user out and returns user to signin screen
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("SignInScreen.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(mainScene);
        appStage.show();
    }

}
