import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
class URLTest
{
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		URL url = new URL ("https://www.naver.com/index.html");
		System.out.println("프로토콜="+url.getProtocol());
		System.out.println("호스트="+url.getHost());
		System.out.println("포트="+url.getPort());
		System.out.println("기본포트="+url.getDefaultPort());
		System.out.println("파일"+url.getFile());
		System.out.println();

		//------------------------------네이버 소스 읽기
		InputStreamReader isr = new InputStreamReader(url.openStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		br.close();
	}
}
