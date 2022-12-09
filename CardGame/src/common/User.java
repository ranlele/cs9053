package common;

public class User {
	String username;
	int cash; // unsaved, temp points 
	int deposit; //saved points after muyu game;
	
	User(String name){
		this.username = name;
		this.cash = 0;
		this.deposit = 0;
	}
}


