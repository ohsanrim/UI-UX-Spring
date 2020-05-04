import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.net.Socket;

class ProtocolServer   //�׻� ������ ������ ���� ����Ǿ�� �۾��� �����ϴ�. 
{
	private ServerSocket serverSocket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket socket;
	private String msg, line;

	public ProtocolServer(){
		try{
			serverSocket = new ServerSocket(9500);  //9500�� ��Ʈ�� ��� ��ٸ�(���� �濡 ���� �� �ְ� ����� ����)
			System.out.println("���� �غ� �Ϸ�..");
			socket = serverSocket.accept();
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch(IOException e){
			System.out.println("Ŭ���̾�Ʈ�� ������ �ȵǾ����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void service(){
		try{
			while(true){
				//Ŭ���̾�Ʈ�κ��� ���� msg�ޱ�
				line = reader.readLine();
				String [] ar = line.split(":");
				//Ŭ���̾�Ʈ�� ������
				if(ar[0].equals(Protocol.ENTER)){
					writer.write(ar[1]+"���� �����Ͽ����ϴ�.\n");
					writer.flush();
				} else if(ar[0].equals(Protocol.SEND_MESSAGE)){
					writer.write(ar[1]+"���� �����Ͽ����ϴ�.\n");
					writer.flush();
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				} else if(ar[0].equals("300")){
					writer.write("["+ar[1]+"]"+ar[ 2]+"\n");
					writer.flush();
				}
			}//while
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new ProtocolServer().service();
	}
}
/*socket.getInputStream()
socket.getOutputStream()
inputStreamReader �� ���� ������ �ޱ�, 
�� ���� BufferedReader�� ������ ���� �޾Ƽ� Ŭ������ ���� ���·� ����
BufferedWriter -> OutputStreamWriter -> getOutputStream() -> getInputStream() -> InputStreamReader -> BufferedReader
*/