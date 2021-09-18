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
import javafx.event.ActionEvent;
import java.io.*;
import java.util.Scanner;
import daisty.User;

public class SignUpScreenController {
    @FXML
    private TextField usrname;

    @FXML
    private TextField pword;

    @FXML
    private TextField confirmPW;

    @FXML
    private Button signUpButton;

    @FXML
    private Label usernameCheck;
    
    @FXML
    private Button cancelButton;

    @FXML
    public void onSignUp(ActionEvent actionEvent) throws IOException {
    	if (usrname.getText().isEmpty() || pword.getText().isEmpty() ||
    		confirmPW.getText().isEmpty()) {	// checks if text fields are empty
    		usernameCheck.setText("Fields cannot be left blank!");
    		usernameCheck.setVisible(true);
    		return;
    	}
    	if (!usrname.getText().matches("^[a-zA-Z0-9._-]{3,}$")) {	// checks if username is sufficient
    		usernameCheck.setText("Username can contain at least 3 alphanumeric characters," +
    				"and the following special characters: dot (.), hyphen (-), and underscore (_)");
    		usernameCheck.setVisible(true);
    		return;
    	}
        boolean usernameExisted = false;
        boolean passwordMismatched = false;
        String inputUsername = "";
        try {
            File theFile = new File("src/application/users.txt");
            Scanner inputFile = new Scanner(theFile);
            while (inputFile.hasNextLine()) {
                inputUsername = inputFile.nextLine();
                if (inputUsername.equalsIgnoreCase(usrname.getText()) && !inputUsername.equals("")) {	// checks if username exists
                    usernameExisted = true;
                    usernameCheck.setText("Username already existed!");
                    usernameCheck.setVisible(true);
                    break;
                }
            }
            inputFile.close();
        } catch(IOException ioe) {
            usrname.setText("error signing up");
        }

        if (!confirmPW.getText().equals(pword.getText())) {	// checks if passwords match
            passwordMismatched = true;
            usernameCheck.setText("Passwords do not match. Please try again!");
            usernameCheck.setVisible(true);
        }

        if (!usernameExisted && !passwordMismatched) {	// user info is added to a new file
            FileWriter fwriter = new FileWriter("src/application/users.txt", true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            String user =usrname.getText();
            outputFile.println(user);
            outputFile.close();
        	user.toLowerCase();
        	FileWriter file = new FileWriter ("src/userInfo/" + user + ".txt");
        	PrintWriter outputFile1 = new PrintWriter (file);
        	outputFile1.println(pword.getText());
        	outputFile1.close();
        	
        	User userObj = new User();
        	userObj.setUsername(usrname.getText());
        	userObj.setPassword(pword.getText());
        	
            Parent homePage = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));	// directs user to complete settings
            Scene homeScene = new Scene(homePage);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setUserData(userObj);
            appStage.setScene(homeScene);
            appStage.show();
        }
    }
    // returns user to sign in screen
    @FXML
    public void onCancel (ActionEvent actionEvent) throws IOException {
    	Parent homePage = FXMLLoader.load(getClass().getResource("SignInScreen.fxml"));
        Scene homeScene = new Scene(homePage);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homeScene);
        appStage.show();
    }
    
}
