import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class ByteStream 
{
	public static void main(String[] args) throws IOException
	{
		FileInputStream fis = new FileInputStream("data.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		System.out.println();
		int data;
		while((data=bis.read())!=-1){  //���̻� ����� ���� ���� �� -1�� ���� ����
			System.out.print((char)data);
		}//while
	}
}
