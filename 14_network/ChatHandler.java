import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

class ChatHandler extends Thread //처리해주는 곳(소켓에 대한 정보가 담겨있는 곳. 소켓을 처리함)
{
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private List <ChatHandler> list;
	//생성자
	public ChatHandler(Socket socket, List <ChatHandler> list) throws IOException {
	
		this.socket = socket;
		this.list = list;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
	}
	public void run(){
		try{
			//클라이언트로부터 받기
			//닉네임이 먼저 옴
			String nickName=reader.readLine();
			//모든 클라이언트에게 입장 메세지를 보낸다.
			broadcast("["+nickName+"]"+"님이 입장하셨습니다");
			String line;
			while(true){
				line=reader.readLine();
				//사용자가 접속을 끊었을 경우. 프로그램을 끝내서는 안되고 남은 사용자들에게 퇴장메세지를 보내줘야 한다. 
				if(line== null || line.equals("exit")){
					//종료하는 클라이언트에게 종료 확인 답변 메세지를보내기(꼭 보내줘야 함. 안그러면 클라이언트는 계속 기다리고 있음)
					writer.println("exit");
					writer.flush();
					//클라이언트 연결 끊기
					reader.close();
					writer.close();
					socket.close();
					//남아있는 클라이언트에게 메세지를 보내기 위해 리스트에서 없애준다. 
					list.remove(this);
					broadcast("["+nickName+"] 이 퇴장하셨습니다.");
					break;
				}

				//모든 사용자에게 메세지 보내기
				broadcast("["+nickName+"]"+line);
			}//while

		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	//다른 클라이언트에게 전체 메세지 보내주기
	public void broadcast(String msg){
		for(ChatHandler handler: list){
			handler.writer.println(msg);  //핸들러 안의 writer에 값을 보내기
			handler.writer.flush();  //핸들러 안의 writer 값 비워주기
		}
	}
}
