import java.net.InetAddress;
import java.net.*;
class InetAddressTest  //IP를 알아낼 수 있음
{
	public static void main(String[] args) throws UnknownHostException
	{
		//InetAddress naver = new InetAddress();  ///네이버의 아이피 주소 알아내기 - 접근물가. 왜냐면 생성자가 public이 아님
		InetAddress naver = InetAddress.getByName("www.naver.com");
		System.out.println("NAVER IP ="+naver.getHostAddress());
		System.out.println();

		InetAddress local = InetAddress.getLocalHost();
		System.out.println("localHost IP= "+local.getHostAddress());
		System.out.println();

		InetAddress[] ar = InetAddress.getAllByName("www.daum.net");
		for(InetAddress data: ar){
			System.out.println("Daum IP="+data.getHostAddress());
		}
	}
}
