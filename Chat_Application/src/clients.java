
import java.io.Serializable;

public class clients implements Serializable{

	private String id;
	private String password;
	private int index;
	
	public clients(String id, String password){
		//register shit here
		this.id =id;
		this.password=password;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
}
