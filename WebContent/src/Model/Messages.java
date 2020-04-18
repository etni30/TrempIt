package Model;

//maybe not important class

public class Messages {
	protected String userName;
	protected String type;
	protected String data;
	
	public Messages() {
		this.userName = "ilad";
		this.type = "driver";
		this.data = "Lorem ipsum dolor sit amet. Praesentium magnam consectetur vel in deserunt aspernatur est reprehenderit sunt hic. Nulla tempora soluta ea et odio, unde doloremque repellendus iure, iste.";
	}
	
	public String getName() {
		return this.userName;
	}
	public String getType() {
		return this.type;
	}
	public String getData() {
		return this.data;
	}
	
	
}
