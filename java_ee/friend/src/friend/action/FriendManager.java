package friend.action;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FriendManager extends JFrame {
   private JButton inserTBtn, modifyBtn, deleteBtn, clearBtn;
   private JTextArea textArea;
   private JLabel numL, seeNumL, nameL, poneL, sexL, hobbyL, insertL, listL, InformationL, hyphen1L, hyphen2L;   
   private String poneT[] = {"010", "011", "017", "019"}; 
   private JComboBox<String> poneCombo;
   private JTextField nameT, pone1T, pone2T;
   private JRadioButton manRadio, girlRadio;
   private JCheckBox readingBox, movieBox, musicBox, gmaeBox, shopingBox;
   private JList<String> list;   
   
   public FriendManager() {
      setLayout(null); // ���̾ƿ�����
      // JButton ���
      inserTBtn = new JButton("���");
      modifyBtn = new JButton("����");
      deleteBtn = new JButton("����");
      clearBtn = new JButton("�����");
      
      inserTBtn.setBounds(50, 200, 65, 30);
      inserTBtn.setFont(new Font("���",Font.PLAIN ,13));
      inserTBtn.setEnabled(true);
      modifyBtn.setBounds(120, 200, 65, 30);
      modifyBtn.setFont(new Font("���",Font.PLAIN ,13));
      modifyBtn.setEnabled(false);
      deleteBtn.setBounds(190, 200, 65, 30);
      deleteBtn.setFont(new Font("���",Font.PLAIN ,13));
      deleteBtn.setEnabled(false);
      clearBtn.setBounds(260, 200, 75, 30);
      clearBtn.setFont(new Font("���",Font.PLAIN ,13));
      clearBtn.setEnabled(false);
      
      add(inserTBtn);
      add(modifyBtn);
      add(deleteBtn);
      add(clearBtn);
      
      // JTextArea ���
      textArea = new JTextArea();
      textArea.setBounds(30, 260, 480, 100);
      textArea.setEditable(false);
      
      add(textArea);
      
      // JLabel ���
      numL = new JLabel("��       ȣ:");
      numL.setBounds(10, 30, 60, 20);
      numL.setFont(new Font("���",Font.BOLD ,13));
            
      seeNumL = new JLabel();      
      JPanel seeNumP = new JPanel();
      seeNumP.setBounds(80, 30, 30, 20);
      seeNumP.setBackground(Color.white);      
      seeNumP.add(seeNumL);
      
      nameL = new JLabel("��       ��:");
      nameL.setBounds(10, 60, 60, 20);
      nameL.setFont(new Font("���",Font.BOLD ,13));
       
      poneL = new JLabel("��ȭ��ȣ:");
      poneL.setBounds(10, 90, 60, 20);
      poneL.setFont(new Font("���",Font.BOLD ,13));
      
      sexL = new JLabel("��       ��:");
      sexL.setBounds(10, 120, 60, 20);
      sexL.setFont(new Font("���",Font.BOLD ,13));      
      
      hobbyL = new JLabel("��       ��:");
      hobbyL.setBounds(10, 150, 60, 20);
      hobbyL.setFont(new Font("���",Font.BOLD ,13));      
      
      insertL = new JLabel("���������Է�");
      insertL.setBounds(150, 10, 100, 20);
      insertL.setFont(new Font("���",Font.BOLD ,15));      
      
      listL = new JLabel("��ü���");
      listL.setBounds(400, 10, 100, 20);
      listL.setFont(new Font("���",Font.BOLD ,15));      
      
      InformationL = new JLabel("���������м�");
      InformationL.setBounds(210, 235, 100, 20);
      InformationL.setFont(new Font("���",Font.BOLD ,15));   
      
      hyphen1L = new JLabel("-");
      hyphen1L.setBounds(145, 88, 20, 20);
      hyphen1L.setFont(new Font("���",Font.BOLD ,13));
      
      hyphen2L = new JLabel("-");
      hyphen2L.setBounds(215, 88, 20, 20);
      hyphen2L.setFont(new Font("���",Font.BOLD ,13));
      
      add(numL);
      add(seeNumP);
      add(nameL);
      add(poneL);
      add(sexL);
      add(hobbyL);
      add(insertL);
      add(listL);
      add(InformationL);
      add(hyphen1L);
      add(hyphen2L);

      // JComboBox���
      poneCombo = new JComboBox<String>(poneT);
      poneCombo.setBounds(80, 90, 60, 20);

      add(poneCombo);
      
      // JTextField���
      nameT = new JTextField();
      nameT.setBounds(80, 60, 120, 20);
      
      pone1T = new JTextField();
      pone1T.setBounds(155, 90, 50, 20);
      
      pone2T = new JTextField();
      pone2T.setBounds(225, 90, 50, 20);
      
      add(nameT);
      add(pone1T);
      add(pone2T);
      
      
      //JRadioButton ���      
      manRadio = new JRadioButton("����", true);
      manRadio.setOpaque(false);
      manRadio.setBounds(80, 120, 60, 20);
      
      girlRadio = new JRadioButton("����", false);
      girlRadio.setOpaque(false);
      girlRadio.setBounds(140, 120, 60, 20);
      // �׷�ȭ ���Ѽ� �Ѱ��� ����
      ButtonGroup group = new ButtonGroup();
      group.add(manRadio);
      group.add(girlRadio);
      
      add(manRadio);
      add(girlRadio);
      
      // JCheckBox���
      readingBox = new JCheckBox("����");
      readingBox.setOpaque(false);
      readingBox.setBounds(80, 150, 60, 20);
      readingBox.setFont(new Font("���",Font.BOLD ,13));
      
      movieBox = new JCheckBox("��ȭ");
      movieBox.setOpaque(false);
      movieBox.setBounds(130, 150, 60, 20);
      movieBox.setFont(new Font("���",Font.BOLD ,13));
      
      musicBox = new JCheckBox("����");
      musicBox.setOpaque(false);
      musicBox.setBounds(180, 150, 60, 20);
      musicBox.setFont(new Font("���",Font.BOLD ,13));
      
      gmaeBox = new JCheckBox("����");
      gmaeBox.setOpaque(false);
      gmaeBox.setBounds(230, 150, 60, 20);
      gmaeBox.setFont(new Font("���",Font.BOLD ,13));
      
      shopingBox = new JCheckBox("����");
      shopingBox.setOpaque(false);
      shopingBox.setBounds(280, 150, 60, 20);
      shopingBox.setFont(new Font("���",Font.BOLD ,13));
      
      add(readingBox);
      add(movieBox);
      add(musicBox);
      add(gmaeBox);
      add(shopingBox);
      
      // JList���
      list = new JList<String>(new DefaultListModel());
      DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
      //model.addElement("���");
      //model.addElement("��");
      //model.addElement("����");
      //model.addElement("�ٳ���");
      //list2.setSelectedIndex(1);      
      list.setBounds(350, 30, 170, 200);      
      add(list);
      
      //������ ����
      getContentPane().setBackground(new Color(210, 204, 244));
      setBounds(700, 100, 550, 400);      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      setResizable(false);
      setVisible(true);
      
   }
   
   
   
   
   
   
   
   
   
   public static void main(String[] args) {
      new FriendManager();
   }
}
