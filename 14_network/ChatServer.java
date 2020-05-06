import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
class ChatServer 
{
	private ServerSocket serverSocket;
	private List <ChatHandler> list;
	public ChatServer(){
		try{
			serverSocket= new ServerSocket (9500);
			System.out.println("���� �غ� �Ϸ�");
			list = new  ArrayList<ChatHandler>();

			while(true){
				Socket socket = serverSocket.accept();
				ChatHandler handler = new  ChatHandler(socket,list);  //�����带 ������ ���̶� ������! ������ �������־�� 
				handler.start();  //������ ����- ������ ����
				list.add(handler);  //�ڵ鷯�� ����( �� ����Ʈ�� ������ Ŭ���̾�Ʈ�� ����!!)
			}//while
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) 
	{
		new ChatServer();
	}
}
//Ŭ���̾�Ʈ�� ���ÿ� ä���ϱ����ؼ� �����尡 �ʿ��ϵ��� �� �����带 ���ÿ� �޾Ƶ��� �� �ִ� ���� ���� �����尡 �Ǿ��־�� ��!
