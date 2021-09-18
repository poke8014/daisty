package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import daisty.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserInfoController {
	final ObservableList<String> allergiesList = FXCollections.observableArrayList("N/A");
	final ObservableList<String> calList =
			FXCollections.observableArrayList("1500", "2000", "2500", "3000");

	@FXML
	private CheckBox vgnBox;

	@FXML
	private CheckBox vgtrn;

	@FXML
	private CheckBox isOther;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Label fnLbl;

	@FXML
	private TextField boxFn;

	@FXML
	private TextField boxLn;

	@FXML
	private ComboBox<String> calorieCount;

	@FXML
	private Label fnResponse;

	@FXML
	private Label lnResponse;

	@FXML
	private Label dobResponse;

	@FXML
	private Label calResponse;
	
	@FXML
	private Label epResponse;

	@FXML
	private TextField boxAllergy;

	@FXML
	private Button addAllergy;

	@FXML
	private ComboBox<String> viewAllergies;

	@FXML
	private Button updateUser;

	@FXML
	private Button edit;

	@FXML
	private void handleAddAllergy(ActionEvent event) {
		if (allergiesList.contains("N/A"))
			allergiesList.clear();
		String addMe = boxAllergy.getText();
		if (addMe.equals("") || allergiesList.contains(addMe)) {
			return;
		}
		allergiesList.add(addMe);
		boxAllergy.clear();
	}

	@FXML
	private void initialize() {
		viewAllergies.setItems(allergiesList);
		calorieCount.setItems(calList);
	}

	/*
	 * will disable editable fields if all fields are filled out otherwise a prompt
	 * will appear
	 */
	@FXML
	void checkFields(ActionEvent event) throws IOException {
		if (boxFn.getText().isEmpty()) { 		// checks first name field
			fnResponse.setVisible(true);		// show alert
		} else {
			fnResponse.setVisible(false);
			boxFn.setDisable(true); 			// lock further user input
		}
		if (boxLn.getText().isEmpty()) {		// checks last name field
			lnResponse.setVisible(true);
		} else {
			lnResponse.setVisible(false);
			boxLn.setDisable(true);
		}
		if (datePicker.getValue() == null) {		// checks date of birth field
			dobResponse.setVisible(true);
		} else {
			dobResponse.setVisible(false);
			datePicker.setDisable(true);
		}
		if (!vgnBox.isSelected() && !vgtrn.isSelected()
				&& !isOther.isSelected()) {
			epResponse.setVisible(true);
		} else {
			epResponse.setVisible(false);
			vgnBox.setDisable(true);
			vgtrn.setDisable(true);
			isOther.setDisable(true);
		}
		if (calorieCount.getValue() == null) {	 // checks calorie count field
			calResponse.setVisible(true);
		} else {
			calResponse.setVisible(false);
			calorieCount.setDisable(true);
		}

		if (!boxFn.getText().isEmpty() && !boxLn.getText().isEmpty() &&
				datePicker.getValue() != null && calorieCount.getValue() != null && !epResponse.isVisible()) {
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			User user = (User) appStage.getUserData();
			user.setFirstName(boxFn.getText());
			user.setLastName(boxLn.getText());
			user.setDOB(datePicker.getValue());
			if (vgnBox.isSelected())
				user.setEatingPractice("Vegan");
			if (vgtrn.isSelected())
				user.setEatingPractice("Vegetarian");
			if (isOther.isSelected())
				user.setEatingPractice("Other");
			user.setAllergies(allergiesList);
			user.setCalorieIntake(calorieCount.getValue());
			
			FileWriter file = new FileWriter ("src/userInfo/" + user.getUsername().toLowerCase() + ".txt", true);
	    	PrintWriter outputFile1 = new PrintWriter (file);
	    	outputFile1.println(boxFn.getText());
	    	outputFile1.println(boxLn.getText());
	    	outputFile1.println(datePicker.getValue());
	    	outputFile1.println(vgnBox.isSelected());
	    	outputFile1.println(vgtrn.isSelected());
	    	outputFile1.println(isOther.isSelected());
	    	outputFile1.println(allergiesList);
	    	outputFile1.println(calorieCount.getValue());
	    	outputFile1.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
	        Parent mainSceneParent = loader.load();
	        Scene mainScene = new Scene(mainSceneParent);
	    	HomeScreenController controller = loader.getController(); 
	    	controller.setMeal(user);
	    	appStage.setUserData(user);
	    	appStage.setScene(mainScene);
	    	appStage.show();
		}
	}

	/*
	 * re-enables fields and changes submit button label to update
	 */
	@FXML
	void enableEdit(ActionEvent event) {
		updateUser.setText("Update");
		boxFn.setDisable(false);
		boxLn.setDisable(false);
		datePicker.setDisable(false);
		calorieCount.setDisable(false);
 	}

	@FXML
	void updateCal(ActionEvent event) {
	}

	public ObservableList<String> getAllergies() {
		return allergiesList;
	}

	public void setAllergies(ObservableList<String> addMe) {
		addMe = allergiesList;
	}
}
