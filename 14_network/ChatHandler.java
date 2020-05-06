import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

class ChatHandler extends Thread //ó�����ִ� ��(���Ͽ� ���� ������ ����ִ� ��. ������ ó����)
{
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private List <ChatHandler> list;
	//������
	public ChatHandler(Socket socket, List <ChatHandler> list) throws IOException {
	
		this.socket = socket;
		this.list = list;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
	}
	public void run(){
		try{
			//Ŭ���̾�Ʈ�κ��� �ޱ�
			//�г����� ���� ��
			String nickName=reader.readLine();
			//��� Ŭ���̾�Ʈ���� ���� �޼����� ������.
			broadcast("["+nickName+"]"+"���� �����ϼ̽��ϴ�");
			String line;
			while(true){
				line=reader.readLine();
				//����ڰ� ������ ������ ���. ���α׷��� �������� �ȵǰ� ���� ����ڵ鿡�� ����޼����� ������� �Ѵ�. 
				if(line== null || line.equals("exit")){
					//�����ϴ� Ŭ���̾�Ʈ���� ���� Ȯ�� �亯 �޼�����������(�� ������� ��. �ȱ׷��� Ŭ���̾�Ʈ�� ��� ��ٸ��� ����)
					writer.println("exit");
					writer.flush();
					//Ŭ���̾�Ʈ ���� ����
					reader.close();
					writer.close();
					socket.close();
					//�����ִ� Ŭ���̾�Ʈ���� �޼����� ������ ���� ����Ʈ���� �����ش�. 
					list.remove(this);
					broadcast("["+nickName+"] �� �����ϼ̽��ϴ�.");
					break;
				}

				//��� ����ڿ��� �޼��� ������
				broadcast("["+nickName+"]"+line);
			}//while

		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	//�ٸ� Ŭ���̾�Ʈ���� ��ü �޼��� �����ֱ�
	public void broadcast(String msg){
		for(ChatHandler handler: list){
			handler.writer.println(msg);  //�ڵ鷯 ���� writer�� ���� ������
			handler.writer.flush();  //�ڵ鷯 ���� writer �� ����ֱ�
		}
	}
}
