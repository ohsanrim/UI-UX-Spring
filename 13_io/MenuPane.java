import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.*;
import javax.swing.*;
class MenuPane extends JMenuBar  
{
	private JMenu fileM, editM, viewM;
	private JMenuItem newM, openM, saveM, exitM, cutM,copyM, pasteM;
	public MenuPane(){
		fileM = new JMenu("파일");
		newM= new JMenuItem("새로만들기");
		openM= new JMenuItem("열기");
		saveM= new JMenuItem("저장");
		exitM= new JMenuItem("종료");
		fileM.add(newM);
		fileM.add(openM);
		fileM.add(saveM);
		fileM.add(exitM);
		//exitM.addActionListener(TryNotepad);

		editM = new JMenu("편집");
		cutM= new JMenuItem("잘라내기");
		copyM= new JMenuItem("복사");
		pasteM= new JMenuItem("붙여넣기");
		

		editM.add(cutM);
		editM.add(copyM);
		editM.add(pasteM);

		viewM = new JMenu("보기");

		add(fileM);
		add(editM);
		add(viewM);
		//단축키
		cutM.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));
		copyM.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK));
		pasteM.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.ALT_DOWN_MASK));
	}
	public JMenuItem exitM(){
		return exitM;
	}
	public JMenuItem newM(){
		return newM;
	}
	public JMenuItem openM(){
		return openM;
	}
	public JMenuItem saveM(){
		return saveM;
	}
	public JMenuItem cutM(){
		return cutM;
	}
	public JMenuItem copyM(){
		return copyM;
	}
	public JMenuItem pasteM(){
		return pasteM;
	}
	
}
