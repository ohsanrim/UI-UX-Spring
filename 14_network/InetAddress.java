import java.net.InetAddress;
import java.net.*;
class  InetAddressTest
{
	public static void main(String[] args) throws UnknownHostException
	{
		//InetAddress naver = new InetAddress();  ///���̹��� ������ �ּ� �˾Ƴ��� - ���ٹ���. �ֳĸ� �����ڰ� public�� �ƴ�
		InetAddress naver = InetAddress.getByName("www.naver.com");
		System.out.println("NAVER IP ="+naver.getHostAddress());
		System.out.println();

		InetAddress local = InetAddress.getLocalHost();
		System.out.println("localHost IP= "+local.getHostAddress());
		System.out.println();
	}
}
