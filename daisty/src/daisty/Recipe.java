package daisty;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int calories;
    private String time;
    private ArrayList<String> ingredients;
    private String mealType;
    private ArrayList<String> cookingInstructions;
    private String source;
    private String path;
    private Boolean isFav = false;
    
    public void setFavorite (Boolean boo)
    {
    	this.isFav = boo;
    }

    public Boolean isFavorite ()
    {
    	return this.isFav;
    }
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getMealType() {
        return mealType;
    }

    public ArrayList<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public String getSource() { return source; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath()
    {
    	return this.path;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setPath(String path)
    {
    	this.path = path;
    }
    
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setCookingInstructions(ArrayList<String> cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
