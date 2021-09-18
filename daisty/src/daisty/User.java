package daisty;


import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate DOB;
    private String eatingPractice;
    private ObservableList<String> allergies;
    private String calorieIntake;
    private MealList lists;
    private ArrayList<Recipe> favorites;
    private ArrayList<String> favNames;		// contains path name of recipes


    public User(String firstName, String lastName, String username, String password,
    		LocalDate DOB, String eatingPractice, ObservableList<String> allergies, String calorieIntake) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.DOB = DOB;
        this.eatingPractice = eatingPractice;
        this.allergies = allergies;
        this.calorieIntake = calorieIntake;
        this.lists = new MealList();
        this.favorites = new ArrayList<>();
        this.favNames = new ArrayList<>();
    }

    public User()
    {
    	this.lists = new MealList();
        this.favorites = new ArrayList<>();
        this.favNames = new ArrayList<>();
    }
    
    public void setFavorites(ArrayList<Recipe> favList)
    {
    	this.favorites = favList;
    }
    
    public ArrayList<Recipe> getFavorites ()
    {   
    	return this.favorites;
    }
    
    public void setFavNames(ArrayList<String> favList)
    {
    	this.favNames = favList;
    }
    
    public void addFavName(String addMe)
    {
    	favNames.add(addMe);
    }
    
    public void removeFavName(String removeMe)
    {
    	favNames.remove(removeMe);
    }
    
    public ArrayList<String> getFavNames ()
    {   
    	return this.favNames;
    }
    
    public void setLists (MealList list)
    {
    	this.lists = list;
    }
    
    public MealList getLists ()
    {
    	return this.lists;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public void setEatingPractice(String eatingPractice) {
        this.eatingPractice = eatingPractice;
    }

    public void setAllergies(ObservableList<String> allergies) {
        this.allergies = allergies;
    }

    public void setCalorieIntake(String calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public String getEatingPractice() {
        return eatingPractice;
    }

    public ObservableList<String> getAllergies() {
        return allergies;
    }

    public String getCalorieIntake() {
        return calorieIntake;
    }

    public void removeFav(Recipe removeMe) {
    	for (int i = 0; i < favorites.size(); i++)
    	{
    		if (favorites.get(i).equals(removeMe))
    		{
    			favorites.remove(i);
    		}
    	}
    	removeMe.setFavorite(false);
    }

    
    
    public void addFav(Recipe recipe) {
    	this.favorites.add(recipe);
    	recipe.setFavorite(true);
    }
}
