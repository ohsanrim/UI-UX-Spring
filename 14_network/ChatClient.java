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
		//���Ϳ� TextArea�����
		output = new JTextArea();
		output.setFont(new Font("���� ���",Font.BOLD,15));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //�׻� ��ũ�ѹٰ� ���η� ������
		
		//�ϴܿ� ��ư�� TextArea�ֱ� 
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout()); 
		input = new JTextField();
		
		sendBtn = new JButton("������");
		
		bottom.add("Center",input);  //���Ϳ� ���̱�
		bottom.add("East",sendBtn);  //���ʿ� ���̱�
		//container�� ���̱�
		Container c = this.getContentPane();
		c.add("Center", scroll);  //���Ϳ� ���̱�
		c.add("South", bottom);  //���ʿ� ���̱�
		//������ â ����
		setBounds(300,300,300,300);
		setVisible(true);
		//������ �̺�Ʈ
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				//System.exit(0);
				writer.println("exit");
				writer.flush();
			}
		});
	}

	public void service(){
		//���� IP �Է¹ޱ�
		//String serverIP = JOptionPane.showInputDialog(this, "����IP�� �Է��ϼ���","����IP",JOptionPane.INFORMATION_MESSAGE);
		String serverIP= JOptionPane.showInputDialog(this,"����IP�� �Է��ϼ���","192.168.0.8");  //�⺻������ ������ ���� �ԷµǾ� ���� ��
		if(serverIP==null || serverIP.length()==0){  //���� ���� �Էµ��� �ʾ��� �� â�� ����
			System.out.println("���� IP�� �Էµ��� �ʾҽ��ϴ�.");
			System.exit(0);
		}
		//�г��� �ޱ�
		String nickName= JOptionPane.showInputDialog(this,"�г����� �Է��ϼ���","�г���" ,JOptionPane.INFORMATION_MESSAGE);
		if(nickName == null || nickName.length()==0){
			nickName="guest";
		}
		try{
			socket = new Socket(serverIP,9500);
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch(UnknownHostException e ){
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("������ ������ �ȵǾ����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}

		//������ �г��� ������
		writer.println(nickName);  //���������� �ʿ䰡 ����
		writer.flush();

		//������ ����
		
		Thread t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this);  //�߼� �̺�Ʈ �߰�
	}
	//������ �������̵� 
	@Override
	public void run(){
		//�����κ��� ������ �ޱ�
		String line="";
		
		while(true){
			try{
				line= reader.readLine();
				if(line==null || line.equals("exit")){  //���������� exit�� ������ �����
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
	
			//������ ���� 
			//JTextField���� �����κ�����
			//���� ����
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
//���� ä���� ���� �����带 �������־�� ��