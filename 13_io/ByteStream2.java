import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.IOException;

class  ByteStream2
{
	public static void main(String[] args) throws IOException
	{ 

		//한번에 배열로 값을 받아서 문자로 변환한다. 
		File file = new File("data.txt");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int size = (int)file.length();  //파일의 크기 알아내기
		byte [] b= new byte[size];
		bis.read(b,0,size);  //b배열의 0번쨰 방부터 차례대로 값을 입력받아 저장
		System.out.println(new String (b));  //byte 배열을 문자로 변환하여 출력하기
	}
}
