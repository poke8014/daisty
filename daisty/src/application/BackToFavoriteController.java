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

public class BackToFavoriteController {
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
        // assign ingredients
        String tempIngredients = "";
        for (int i = 0; i < recipe.getIngredients().size(); i++)
            tempIngredients += recipe.getIngredients().get(i) + "\n";
        foodIngredients.setText(tempIngredients);
        // assign instructions
        String tempInstructions = "";
        for (int i = 0; i < recipe.getCookingInstructions().size(); i++) {
            String temp = recipe.getCookingInstructions().get(i);
            tempInstructions += temp + "\n";
        }
        foodInstruction.setText(tempInstructions);
        //retrieve image
        File file = new File("src/recipeInfo/recipeImage/" + recipe.getPath() + ".jpeg");
        Image image = new Image(file.toURI().toString());
        foodImage.setImage(image);
    }
    
    // returns user to favorite screen
    @FXML
    void backToRecipe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FavoriteScreen.fxml"));
        Parent mainSceneParent = loader.load();
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        FavoriteScreenController controller = loader.getController();
        controller.initialize(user);
        appStage.setUserData(user);
        appStage.setScene(mainScene);
        appStage.show();
    }
}
