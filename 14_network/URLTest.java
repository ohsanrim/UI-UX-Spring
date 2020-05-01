import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
class URLTest
{
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		URL url = new URL ("https://www.naver.com/index.html");
		System.out.println("��������="+url.getProtocol());
		System.out.println("ȣ��Ʈ="+url.getHost());
		System.out.println("��Ʈ="+url.getPort());
		System.out.println("�⺻��Ʈ="+url.getDefaultPort());
		System.out.println("����"+url.getFile());
		System.out.println();

		//------------------------------���̹� �ҽ� �б�
		InputStreamReader isr = new InputStreamReader(url.openStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		br.close();
	}
}
