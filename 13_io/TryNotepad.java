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

//���� �޸��� �����
class TryNotepad extends JFrame implements ActionListener 
{	
	private JTextArea text;
	private MenuPane menu;
	public TryNotepad(){
		super("���� �޸���");
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		//��ũ�ѹٰ� �׻� �����ʿ� ���η� �ߵ��� �����
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//��ũ�ѹٰ� �׻� ���η� �߰� �����
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		menu= new MenuPane();
		setJMenuBar(menu);  //�ڿ������� �����̳� ���� �޴��� ��ġ�� �ڵ����� �ö󰣴�. 
		add("Center", scroll);
		
		//ActionLisntener�̺�Ʈ
		menu.exitM().addActionListener(this);
		menu.newM().addActionListener(this);
		menu.openM().addActionListener(this);
		menu.saveM().addActionListener(this);
		menu.pasteM().addActionListener(this);
		menu.cutM().addActionListener(this);
		menu.copyM().addActionListener(this);
		//WindowListener �̺�Ʈ
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				int result=JOptionPane.showConfirmDialog(TryNotepad.this,"������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.NO_OPTION) { System.exit(0); }
				else if(result ==JOptionPane.YES_OPTION){
					saveDialog();
					System.exit(0);
				}
			}
		});
		
		//������ ����
		setBounds(300,900,700,500);
		setVisible(true);
	}
	//ActionListener �̺�Ʈ �������̵�
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="����"){
			int result=JOptionPane.showConfirmDialog(this,"������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(result==JOptionPane.NO_OPTION) { System.exit(0); }
			else if(result ==JOptionPane.YES_OPTION){
				saveDialog();
				System.exit(0);
			}
		} else if(e.getActionCommand()=="���θ����"){
			text.setText("");
		} else if(e.getActionCommand()=="����"){
			openDialog();
		} else if(e.getActionCommand()=="����"){
			saveDialog();
		} else if(e.getActionCommand()=="����"){
			text.copy();
		} else if(e.getActionCommand()=="�߶󳻱�"){
			text.cut();
		} else if(e.getActionCommand()=="�ٿ��ֱ�"){
			text.paste();
		}
	}
	//���� �ҷ������ϴ� �˾�â ����
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
				text.append(line+"\n");  //���� �ٷ� �������� �б� ������ ���͵��� �ʰ� ���������� �̾����� ���¤���. ������ ���� ���� �ʿ�
			}	
			br.close();
		} catch(IOException e){
		}	
	}
	//���� �����ϴ� �˾�â �ҷ�����
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
	//���θ޼ҵ� ȣ��
	public static void main(String[] args) 
	{
		new TryNotepad();
	}
}
/*
���� ���̾�α� üũ(x, �޴�-����)
"������ �����ϰڽ��ϱ�?" - ��, �ƴϿ�, ���

MenuPane.java
�����- �߶󳻱�  ALT + X
    - ����    ALT+C
	-�ٿ��ֱ�   ALT+V

ó�� ������ �ؼ� ���� ���̾�α׸� ����� �� ������ ������ �Ⱦ ��� ������ �� ���� �ߴ� ���� ��ġ��(nullpoint)
������ �ƹ��͵� �ȶ� ��
*/