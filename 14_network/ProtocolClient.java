import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.UnknownHostException;

class ProtocolClient  
{	
	private Socket socket;
	private BufferedReader reader;  //socket������ �Է¹޴� ��
	private BufferedWriter writer;
	private BufferedReader keyboard;  //Ű����κ��� �Է¹޴� ��
	private String msg, line;
	
	public ProtocolClient(){
		try{
			socket = new Socket("192.168.0.8", 9500);  //������ ������ ���̵�� ��Ƶ��ȣ�� �ʿ�!!  ipconfig��� ��ɾ�� ���� �����Ǹ� �� �� �ִ�. ping�̶�� ��ɾ�� ��Ʈ��ũ�� ����� �Ǵ��� �� �� ����
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			keyboard = new BufferedReader(new InputStreamReader(System.in));
		} catch(UnknownHostException e ){
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("������ ������ �ȵǾ����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void service(){
		try{
			while(true){
				//������ ������
				msg =keyboard.readLine();
				//writer.erite(msg); �̷��� ������ ������ ��. �ֳĸ� ������ ���Ͽ��� readLine�� �� �� ���Ͱ��� ã�µ�, �츮�� Ű����κ��� ���� 
				//msg�� ���Ͱ��� ���� �ʴ´�. ������ ������ ����"\n"�� �Է����־���� �������� �о���� �� �ִ�. 
				writer.write(msg+"\n");
				writer.flush();  //�����ִ� ��⸦ ����־�� ��
				
				//�����κ��� �ޱ�
				line = reader.readLine();
				System.out.println(line);
				String [] ar = msg.split(":");
				if(ar[0].equals(Protocol.EXIT)){  //���Ḧ ���� ��, ������ �� ������
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				}
			}//while(Exit�� �� �� ����)
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		new ProtocolClient().service();
	}
}
/*socket.getInputStream()
socket.getOutputStream()
inputStreamReader �� ���� ������ �ޱ�, 
�� ���� BufferedReader�� ������ ���� �޾Ƽ� Ŭ������ ���� ���·� ����
BufferedWriter -> OutputStreamWriter -> getOutputStream() -> getInputStream() -> InputStreamReader -> BufferedReader

���� Ű����� �Է��� �ޱ�� ���� BufferedReade�� ����ؼ� ���� �޾��־�� ��.
Ű����(System.in)-> inputStream->bufferedReader

������ �����ϴ� ���� �����ص� �������� ���� ���� ���۵Ǿ�� �Ѵ�. 
100:angel -> angel���� �����Ͽ����ϴ�.
200:angel:�ȳ� -> [angel] �ȳ�
300:angel -> angel���� �����ϼ̽��ϴ�. 

*/