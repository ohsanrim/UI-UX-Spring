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
		fileM = new JMenu("����");
		newM= new JMenuItem("���θ����");
		openM= new JMenuItem("����");
		saveM= new JMenuItem("����");
		exitM= new JMenuItem("����");
		fileM.add(newM);
		fileM.add(openM);
		fileM.add(saveM);
		fileM.add(exitM);
		//exitM.addActionListener(TryNotepad);

		editM = new JMenu("����");
		cutM= new JMenuItem("�߶󳻱�");
		copyM= new JMenuItem("����");
		pasteM= new JMenuItem("�ٿ��ֱ�");
		

		editM.add(cutM);
		editM.add(copyM);
		editM.add(pasteM);

		viewM = new JMenu("����");

		add(fileM);
		add(editM);
		add(viewM);
		//����Ű
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
