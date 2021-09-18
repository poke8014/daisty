package application;

import java.io.File;
import java.io.IOException;

import daisty.Recipe;
import daisty.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RecipeScreenController {
    private User user = new User();

    @FXML
    private Label recipeName;

    @FXML
    private Label foodIngredients;

    @FXML
    private Label foodInstruction;
    
    @FXML
    private ImageView foodImage;

    @FXML
    private Button backRecipeBtn;
    
    @FXML
    public void initialize(Recipe recipe) {
    	recipeName.setText(recipe.getName());
    	String tempIngredients = "";
		for (int i = 0; i < recipe.getIngredients().size(); i++)	// set ingredients
			tempIngredients += recipe.getIngredients().get(i) + "\n";
		foodIngredients.setText(tempIngredients);
		
		String tempInstructions = "";
		for (int i = 0; i < recipe.getCookingInstructions().size(); i++) {	// set instructions
			String temp = recipe.getCookingInstructions().get(i);
			tempInstructions += temp + "\n";
		}
		foodInstruction.setText(tempInstructions);
		
		File file = new File("src/recipeInfo/recipeImage/" + recipe.getPath() + ".jpeg");	// set image
        Image image = new Image(file.toURI().toString());
        foodImage.setImage(image);
    }
    // return user to recipe page
    @FXML
    void backToRecipe(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        appStage.setUserData(user);
        appStage.setScene(mainScene);
        appStage.show();
    }
}
