import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.IOException;

class  ByteStream2
{
	public static void main(String[] args) throws IOException
	{ 

		//�ѹ��� �迭�� ���� �޾Ƽ� ���ڷ� ��ȯ�Ѵ�. 
		File file = new File("data.txt");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int size = (int)file.length();  //������ ũ�� �˾Ƴ���
		byte [] b= new byte[size];
		bis.read(b,0,size);  //b�迭�� 0���� ����� ���ʴ�� ���� �Է¹޾� ����
		System.out.println(new String (b));  //byte �迭�� ���ڷ� ��ȯ�Ͽ� ����ϱ�
	}
}
