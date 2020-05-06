import java.util.*;
import java.io.*;
/*
final class Info {   //����� �� ����. extends�Ұ� 
	public static final String JOIN ="100";
	public static final String EXIT ="200";
	public static final String SEND="300";
}
*/
enum Info {
	JOIN, EXIT, SEND
}

class InfoDTO implements Serializable{
	private String nickName;
	private String message;
	private Info command;
	
	public String getNickName(){
		return nickName;
	}
	public Info getCommand(){
		return command;
	}
	public String getMessage(){
		return message;
	}
	public void setNickName(String nickName){
		this.nickName= nickName;
	}
	public void setCommand(Info command){
		this.command= command;
	}
	public void setMessage(String message){
		this.message= message; 
	}
}
/*
ChatClient.java ---> ChatClientObject.java
ChatServer.java ---> ChatServerObject.java
CahtHandler.java ---> ChatHandlerObject.java

BufferedReader�� ����ߴٸ� �̹����� ��ü�� �ѱ�
��絥���͸� String�� �ƴ� InfoDTO�� ������ �޴� ��!
ObjectInputStream�� ObjectOutputStream�� ����ؾ� ��!!


*/