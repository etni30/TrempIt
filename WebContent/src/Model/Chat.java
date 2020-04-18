package Model;

import java.util.ArrayList;

// maybe not important class

public class Chat {
	ArrayList<Messages> m;
	
	public Chat() {
		this.m = new ArrayList<Messages>();
		this.m.add(new Messages());
		this.m.add(new Messages());
	}
	//get methods
	public ArrayList<Messages> getChat() {
		return this.m;
	}
	
	public int getNumOfMessage() {
		return this.m.size();
	}
	//set method
	public int addMessageToChat(String userName, String type, String data) {
		return 1;
	}
	
}
