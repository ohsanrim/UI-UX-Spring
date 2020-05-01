import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
class URLTest2 
{
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		URL url = new URL("http://www.liebli.com/");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String line=null;
		int idx;
		int count=0;
		while((line=br.readLine())!=null){
			idx=0;
			line=line.toLowerCase();
			while((idx=line.indexOf("14k",idx))!=-1){
				count++;
				idx=idx+("14k".length());
			}
		}
		System.out.println("14KÀÇ °³¼ö"+count);
	}
}
