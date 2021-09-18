package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import daisty.User;

public class SignInScreenController {
    @FXML
    private TextField usrname;

    @FXML
    private TextField pword;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;
    
    @FXML
    private Label SignInMessage;
    
    private User user;
    private ArrayList<String> favList = new ArrayList<>();

    @FXML
    public void onSignIn(ActionEvent actionEvent) throws IOException {
    	if (usrname.getText().isEmpty() || pword.getText().isEmpty()) {	// checks if username is entered
    		SignInMessage.setText("Fields cannot be left blank!");
    		SignInMessage.setVisible(true);
    		return;
    	}
        boolean isFound = false;
        String u = "", p = "";
        try {
            File theFile = new File("src/application/users.txt");
            Scanner inputFile = new Scanner(theFile);
            while (inputFile.hasNextLine()) {
                u = inputFile.nextLine();
                if (u.equalsIgnoreCase(usrname.getText())) {	// username is correct
                	u.toLowerCase();
                	File file = new File("src/userInfo/" + u +".txt");
                	Scanner input = new Scanner(file);
                	p = input.nextLine();
                	if (p.equals(pword.getText())) {	// password is correct
                		isFound = true;
                		user = new User();	// initialize user info before login
                		user.setUsername(u);
                		user.setPassword(p);
                		user.setFirstName(input.nextLine());
                		user.setLastName(input.nextLine());
                		String temp = input.nextLine();
                		user.setDOB(LocalDate.parse(temp));
                		String q = input.nextLine();
                		if(q.equals("true"))
                			user.setEatingPractice("Vegan");
                		q = input.nextLine();
                		if(q.equals("true"))
                			user.setEatingPractice("Vegetarian");
                		q = input.nextLine();
                		if(q.equals("true"))
                			user.setEatingPractice("Other");
                		String allegies = input.nextLine();
                		allegies = allegies.substring(1, allegies.length()-1);
                		String[] list = allegies.split(",");
                		ObservableList<String> allegiesList = FXCollections.observableArrayList(list);
                		user.setAllergies(allegiesList);
                		user.setCalorieIntake(input.nextLine());
                		
                		while(input.hasNextLine()) {	// adding recipes to fav list
                			String str = input.nextLine().trim();
                			favList.add(str);
                		}
                		user.setFavNames(favList);
                		break;
                	}      
                	input.close();
                }
            }
            inputFile.close();
        } catch(IOException ioe) {
            usrname.setText("error logging in");
        }
        if (isFound) {	// successful login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        	Parent mainSceneParent = loader.load();
        	Scene mainScene = new Scene(mainSceneParent);
        	
    		Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    		HomeScreenController controller = loader.getController(); 
    		controller.setMeal(user);
    		appStage.setUserData(user);
    		appStage.setScene(mainScene);
    		appStage.show();
        } else {
        	SignInMessage.setText("Username or password has been entered incorrectly");
        	SignInMessage.setVisible(true);
        }
    }
    // directs user to sign up screen
    public void onSignUp(ActionEvent actionEvent) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        Scene homeScene = new Scene(homePage);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homeScene);
        appStage.show();
    }
}
