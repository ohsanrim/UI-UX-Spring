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
	private BufferedReader reader;  //socket을통해 입력받는 것
	private BufferedWriter writer;
	private BufferedReader keyboard;  //키보드로부터 입력받는 것
	private String msg, line;
	
	public ProtocolClient(){
		try{
			socket = new Socket("192.168.0.8", 9500);  //서버와 연결할 아이디와 포틉번호가 필요!!  ipconfig라는 명령어로 나의 아이피를 알 수 있다. ping이라는 명령어는 네트워크가 제대로 되는지 알 수 있음
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			keyboard = new BufferedReader(new InputStreamReader(System.in));
		} catch(UnknownHostException e ){
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void service(){
		try{
			while(true){
				//서버로 보내기
				msg =keyboard.readLine();
				//writer.erite(msg); 이렇게 보내면 먹통이 됨. 왜냐면 서버의 소켓에서 readLine을 할 때 엔터값을 찾는데, 우리가 키보드로부터 받은 
				//msg는 엔터값을 읽지 않는다. 때문에 강제로 엔터"\n"를 입력해주어야지 서버에서 읽어들일 수 있다. 
				writer.write(msg+"\n");
				writer.flush();  //남아있는 찌꺼기를 비워주어야 함
				
				//서버로부터 받기
				line = reader.readLine();
				System.out.println(line);
				String [] ar = msg.split(":");
				if(ar[0].equals(Protocol.EXIT)){  //종료를 원할 때, 연결을 다 끊어줌
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				}
			}//while(Exit가 올 때 까지)
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
inputStreamReader 를 만들어서 소텟을 받기, 
그 다음 BufferedReader을 ㅜ통해 값을 받아서 클래스에 최종 형태로 도착
BufferedWriter -> OutputStreamWriter -> getOutputStream() -> getInputStream() -> InputStreamReader -> BufferedReader

또한 키보드로 입력을 받기ㅣ 위해 BufferedReade를 사용해서 값을 받아주어야 함.
키보드(System.in)-> inputStream->bufferedReader

서버로 전달하는 값은 저장해둔 프로토콜 값과 같이 전송되어야 한다. 
100:angel -> angel님이 입장하였습니다.
200:angel:안녕 -> [angel] 안녕
300:angel -> angel님이 종료하셨습니다. 

*/