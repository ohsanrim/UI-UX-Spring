import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;


class  ChatClient extends JFrame implements ActionListener,Runnable
{
	private JTextArea output; 
	private JTextField input; 
	private JButton sendBtn;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer; 
	private String msg;
	public ChatClient(){
		//센터에 TextArea만들기
		output = new JTextArea();
		output.setFont(new Font("맑은 고딕",Font.BOLD,15));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //항상 스크롤바가 세로로 떠있음
		
		//하단에 버튼과 TextArea넣기 
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout()); 
		input = new JTextField();
		
		sendBtn = new JButton("보내기");
		
		bottom.add("Center",input);  //센터에 붙이기
		bottom.add("East",sendBtn);  //동쪽에 붙이기
		//container에 붙이기
		Container c = this.getContentPane();
		c.add("Center", scroll);  //센터에 붙이기
		c.add("South", bottom);  //남쪽에 붙이기
		//윈도우 창 설정
		setBounds(300,300,300,300);
		setVisible(true);
		//윈도우 이벤트
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				//System.exit(0);
				writer.println("exit");
				writer.flush();
			}
		});
	}

	public void service(){
		//서버 IP 입력받기
		//String serverIP = JOptionPane.showInputDialog(this, "서버IP를 입력하세요","서버IP",JOptionPane.INFORMATION_MESSAGE);
		String serverIP= JOptionPane.showInputDialog(this,"서버IP를 입력하세요","192.168.0.8");  //기본적으로 아이피 값이 입력되어 들어가게 됨
		if(serverIP==null || serverIP.length()==0){  //만약 값이 입력되지 않았을 때 창이 꺼짐
			System.out.println("서버 IP가 입력되지 않았습니다.");
			System.exit(0);
		}
		//닉네임 받기
		String nickName= JOptionPane.showInputDialog(this,"닉네임을 입력하세요","닉네임" ,JOptionPane.INFORMATION_MESSAGE);
		if(nickName == null || nickName.length()==0){
			nickName="guest";
		}
		try{
			socket = new Socket(serverIP,9500);
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch(UnknownHostException e ){
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}

		//서버로 닉네임 보내기
		writer.println(nickName);  //역슬러쉬가 필요가 없음
		writer.flush();

		//스레드 생성
		
		Thread t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this);  //멕션 이벤트 추가
	}
	//스레드 오버라이드 
	@Override
	public void run(){
		//서버로부터 데이터 받기
		String line="";
		
		while(true){
			try{
				line= reader.readLine();
				if(line==null || line.equals("exit")){  //서버로쿠터 exit를 받으면 종료됨
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				} 
				output.append(line+"\n");
				
				int pos=output.getText().length();
				output.setCaretPosition(pos);
			}catch(IOException e){
				e.printStackTrace();
			}	
		}
	}
	//ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e){
	
			//서버로 보냄 
			//JTextField값을 서버로보내기
			//버퍼 비우기
			msg=input.getText();
			writer.println(msg);
			writer.flush();
			input.setText("");
	}

	public static void main(String[] args) 
	{
		new ChatClient().service();
	}
}
//동시 채팅을 위해 쓰레드를 생성해주어야 함