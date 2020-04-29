import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
class DataStream
{
	public static void main(String[] args) throws IOException
	{

		//출력
		//DataOutputStream dos = new DataOutputStream(new FileOutputStream("result.txt"));
		FileOutputStream fos = new FileOutputStream("result.txt"); 
		//출력 시에 만약 파일이 없을 경우에는 파일이 자동적으로 생성된다.
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeUTF("홍길동");
		dos.writeInt(25);
		dos.writeDouble(185.3);
		dos.close();

		//입력
		DataInputStream dis = new DataInputStream(new FileInputStream("result.txt"));
		System.out.println("이름=" +dis.readUTF());
		System.out.println("나이=" +dis.readInt());
		System.out.println("키=" +dis.readDouble());
		dis.close();
	}
}
