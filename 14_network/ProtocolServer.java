import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.net.Socket;

class ProtocolServer   //항상 실행은 서버가 먼저 실행되어야 작업이 가능하다. 
{
	private ServerSocket serverSocket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket socket;
	private String msg, line;

	public ProtocolServer(){
		try{
			serverSocket = new ServerSocket(9500);  //9500번 포트를 잡고 기다림(같은 방에 들어올 수 있게 만들기 위해)
			System.out.println("서버 준비 완료..");
			socket = serverSocket.accept();
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch(IOException e){
			System.out.println("클라이언트와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void service(){
		try{
			while(true){
				//클라이언트로부터 오는 msg받기
				line = reader.readLine();
				String [] ar = line.split(":");
				//클라이언트로 보내기
				if(ar[0].equals(Protocol.ENTER)){
					writer.write(ar[1]+"님이 입장하였습니다.\n");
					writer.flush();
				} else if(ar[0].equals(Protocol.SEND_MESSAGE)){
					writer.write(ar[1]+"님이 종료하였습니다.\n");
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
inputStreamReader 를 만들어서 소텟을 받기, 
그 다음 BufferedReader을 ㅜ통해 값을 받아서 클래스에 최종 형태로 도착
BufferedWriter -> OutputStreamWriter -> getOutputStream() -> getInputStream() -> InputStreamReader -> BufferedReader
*/