package common;

public class User {
	public String username;
	public int cash; // unsaved, temp points 
	public int deposit; //saved points after muyu game;
	
	public User(String name){
		this.username = name;
		this.cash = 100;
		this.deposit = 0;
	}
}


