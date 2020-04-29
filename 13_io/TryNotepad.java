import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//간단 메모장 만들기
class TryNotepad extends JFrame implements ActionListener 
{	
	private JTextArea text;
	private MenuPane menu;
	public TryNotepad(){
		super("간단 메모장");
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		//스크롤바가 항상 오른쪽에 세로로 뜨도록 만들기
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//스크롤바가 항상 가로로 뜨게 만들기
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		menu= new MenuPane();
		setJMenuBar(menu);  //자연스럽게 컨테이너 위의 메뉴바 위치에 자동으로 올라간다. 
		add("Center", scroll);
		
		//ActionLisntener이벤트
		menu.exitM().addActionListener(this);
		menu.newM().addActionListener(this);
		menu.openM().addActionListener(this);
		menu.saveM().addActionListener(this);
		menu.pasteM().addActionListener(this);
		menu.cutM().addActionListener(this);
		menu.copyM().addActionListener(this);
		//WindowListener 이벤트
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				int result=JOptionPane.showConfirmDialog(TryNotepad.this,"파일을 저장하시겠습니까?", "종료", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.NO_OPTION) { System.exit(0); }
				else if(result ==JOptionPane.YES_OPTION){
					saveDialog();
					System.exit(0);
				}
			}
		});
		
		//윈도우 설정
		setBounds(300,900,700,500);
		setVisible(true);
	}
	//ActionListener 이벤트 오버라이드
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="종료"){
			int result=JOptionPane.showConfirmDialog(this,"파일을 저장하시겠습니까?", "종료", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(result==JOptionPane.NO_OPTION) { System.exit(0); }
			else if(result ==JOptionPane.YES_OPTION){
				saveDialog();
				System.exit(0);
			}
		} else if(e.getActionCommand()=="새로만들기"){
			text.setText("");
		} else if(e.getActionCommand()=="열기"){
			openDialog();
		} else if(e.getActionCommand()=="저장"){
			saveDialog();
		} else if(e.getActionCommand()=="복사"){
			text.copy();
		} else if(e.getActionCommand()=="잘라내기"){
			text.cut();
		} else if(e.getActionCommand()=="붙여넣기"){
			text.paste();
		}
	}
	//파일 불러오기하는 팝업창 열기
	public void openDialog(){
		JFileChooser c = new JFileChooser();
		int result=c.showOpenDialog(this);
		File file= null;
		if(result==JFileChooser.APPROVE_OPTION){
			file = c.getSelectedFile();
			fileRead(file);
		}
	}
	public void fileRead(File file){
		try{
			text.setText("");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine())!=null){
				text.append(line+"\n");  //엔터 바로 전까지만 읽기 때문에 엔터되지 않고 연속적으로 이어져서 나온ㄴ다. 때문에 강제 엔터 필요
			}	
			br.close();
		} catch(IOException e){
		}	
	}
	//파일 저장하는 팝업창 불러오기
	public void saveDialog(){
		JFileChooser c1 = new JFileChooser();
		int result= c1.showSaveDialog(this);
		File file= null;
		if(result==JFileChooser.APPROVE_OPTION){
			file = c1.getSelectedFile();
		}
		fileWrite(file);
	}
	public void fileWrite(File file){
		try{
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String line=text.getText();
		bw.write(line);
		bw.flush();
		bw.close();
		} catch(IOException e){ }
	}
	//메인메소드 호출
	public static void main(String[] args) 
	{
		new TryNotepad();
	}
}
/*
종료 다이얼로그 체크(x, 메뉴-종료)
"파일을 저장하겠습니까?" - 예, 아니오, 취소

MenuPane.java
단축기- 잘라내기  ALT + X
    - 복사    ALT+C
	-붙여넣기   ALT+V

처음 실행을 해서 열기 다이얼로그를 띄웠을 때 파일을 누르기 싫어서 취소 눌렀을 때 에러 뜨는 현상 고치기(nullpoint)
에러가 아무것도 안뜰 때
*/