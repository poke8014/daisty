package daisty;

import java.util.ArrayList;

public class MealList {
	private ArrayList<Recipe> breakfastList = new ArrayList<>();
	private ArrayList<Recipe> lunchList = new ArrayList<>();
	private ArrayList<Recipe> dinnerList = new ArrayList<>();
	private ArrayList<Recipe> snackList = new ArrayList<>();
	
	public MealList() 
	{
		
	}
	
	public ArrayList<Recipe> getbreakfastList()
	{
		return this.breakfastList;
	}
	
	public ArrayList<Recipe> getlunchList()
	{
		return this.lunchList;
	}
	public ArrayList<Recipe> getdinnerList()
	{
		return this.dinnerList;
	}
	public ArrayList<Recipe> getsnackList()
	{
		return this.snackList;
	}
	
	public void setbreakfastList(ArrayList<Recipe> list)
	{
		this.breakfastList = list;
	}
	
	public void setlunchList(ArrayList<Recipe> list)
	{
		this.lunchList = list;
	}
	
	public void setdinnerList(ArrayList<Recipe> list)
	{
		this.dinnerList = list;
	}
	
	public void setsnackList(ArrayList<Recipe> list)
	{
		this.snackList = list;
	}
	
	
	
}
