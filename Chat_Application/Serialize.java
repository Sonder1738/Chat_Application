import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;


public class Serialize implements Serializable{

	public void serialize(LinkedList<clients> cList, String fileName){
		try(ObjectOutputStream cListOut = new ObjectOutputStream(new FileOutputStream(fileName))){
			cListOut.writeObject(cList);
		}catch(IOException ex){
			System.out.println("Problem occured during serialization");
			System.out.println(ex.getMessage());
		}
	}
	
	public LinkedList<clients> deserialize(String fileName){
		LinkedList<clients> cList = null;
		try(ObjectInputStream listIn = new ObjectInputStream(new FileInputStream(fileName))){
			cList = (LinkedList<clients>) listIn.readObject();
		}catch(IOException | ClassNotFoundException ex){
			System.out.println("Customer list not found!");
		}
		return cList;
	}
	
}
