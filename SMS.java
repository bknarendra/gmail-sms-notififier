import java.io.*;
import java.net.*;
import java.util.Vector;

public class SMS
{
	public static void send(String uid, String pwd, String phone, String msg) throws IOException {
			uid = URLEncoder.encode(uid, "UTF-8");
			pwd = URLEncoder.encode(pwd, "UTF-8");
			msg = URLEncoder.encode(msg, "UTF-8");
		Vector<Long> numbers = new Vector<Long>();
		String pharr[];
		if (phone.indexOf(';') >= 0)
		{
			pharr = phone.split(";");
			for (String t : pharr) 
			{
				try
				{
					numbers.add(Long.valueOf(t));
				}
				catch (NumberFormatException ex)
				{
					throw new IllegalArgumentException("Give proper phone numbers.");
				}
			}
		} 
		else 
		{
			try
			{
				numbers.add(Long.valueOf(phone));
			}
			catch (NumberFormatException ex)
			{
				throw new IllegalArgumentException("Give proper phone numbers.");
			}
		}

		if (0 == numbers.size())
			throw new IllegalArgumentException("At least one proper phone number should be present to send SMS.");

		String temp = "";
		URL u = new URL("http://www.youmint.com");
		HttpURLConnection uc = (HttpURLConnection) u.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		uc.setRequestProperty("Host", "www.youmint.com");
		uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		uc.setRequestMethod("GET");
		uc.setInstanceFollowRedirects(false);
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ( (temp = br.readLine()) != null ) {}
		String cookie = uc.getHeaderField(4);
		br.close();
		u = null; uc = null;
		String urlyoumint="http://www.youmint.com/LoginVerification.php?name="+uid+"&pass="+pwd+"&agreement=true&checkvalue=false";
		u = new URL(urlyoumint);
		uc = (HttpURLConnection) u.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		uc.setRequestProperty("Host", "www.youmint.com");
		uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setRequestProperty("Referer", "http://www.youmint.com/");
		uc.setRequestProperty("Content-Length", "0");
		uc.setRequestProperty("Origin", "http://www.youmint.com/");
		uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		uc.setRequestProperty("Cookie", cookie);
		uc.setRequestMethod("POST");
		br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ( (temp = br.readLine()) != null ) {}
		String cookie2 = uc.getHeaderField(7);
		cookie=cookie.substring(0,cookie.indexOf(';'))+"; content=no-email; "+cookie2.substring(0,cookie2.indexOf(';'));
		br.close();
		u = null; uc = null;

		urlyoumint="http://www.youmint.com/FreeSmsNri.php";
		u = new URL(urlyoumint);
		uc = (HttpURLConnection) u.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		uc.setRequestProperty("Host", "www.youmint.com");
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setRequestProperty("Referer", "http://www.youmint.com/");
		uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
		uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		uc.setRequestProperty("Cookie", cookie);
		uc.setRequestMethod("GET");
		uc.setInstanceFollowRedirects(false);
		br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ( (temp = br.readLine()) != null ) {}
		br.close();
				
		for (long num : numbers)
		{
			u = null; uc = null;
			urlyoumint="http://www.youmint.com/SendingSms.php?ToMobileNumber="+String.valueOf(num)+"&Message="+msg+"&FromMobileNumber="+uid;
			u = new URL(urlyoumint);
			uc = (HttpURLConnection) u.openConnection();
			uc.setRequestProperty("Host", "www.youmint.com");
			uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
			uc.setRequestProperty("Connection", "keep-alive");
			uc.setRequestProperty("Referer", "http://www.youmint.com/FreeSmsNri.php");
			uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
			uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
			uc.setRequestProperty("Cookie", cookie);
			uc.setRequestMethod("GET");
			uc.setInstanceFollowRedirects(false);
			br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			while ( (temp = br.readLine()) != null ) {}
			br.close();
		}
		u = null; uc = null;
		u = new URL("http://www.youmint.com/Logout.html");
		uc = (HttpURLConnection) u.openConnection();
		uc.setRequestProperty("Host", "www.youmint.com");
		uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setRequestProperty("Referer", "http://www.youmint.com/FreeSmsNri.php");
		uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		uc.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		uc.setRequestProperty("Cookie", cookie);
		uc.setRequestMethod("GET");
		uc.setInstanceFollowRedirects(false);
		br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ( (temp = br.readLine()) != null ) {}
		br.close();
		u = null;
		uc = null;
	}
}