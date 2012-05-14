import java.net.*;
import java.io.*;
import java.util.*;

public class GmailN
{
	
	public static void main(String args[])
	{
		int i,inf=1;
		String message="";
		String temp,temp1="";
		String email="",passwd="",mailc="",youmintuser="",youmintpass="",number="",msg="",delay="",cmd="";

		try {
            //System.out.println("Loading configuration file...");
            System.getProperties().load(new FileInputStream("input.ini"));
			email = System.getProperty("email");
			passwd= System.getProperty("password");
			mailc= System.getProperty("mailc");
			youmintuser=System.getProperty("youmintuser");
			youmintpass=System.getProperty("youmintpass");
			number=System.getProperty("number");
			msg=System.getProperty("msg");
			delay=System.getProperty("delay");
        }
        catch (Exception e) {
            System.err.println("couldn't find configuration file input.ini");
        }

		while(inf==1)
		{
		try{
		//System.out.println("checking mail for new msg from tc");
		cmd="curl.exe https://"+email+":"+passwd+"@mail.google.com/mail/feed/atom -k";
		message=cmdExec(cmd);
		int t;
		//System.out.println(message);
		if(message.indexOf(mailc)!=-1)
		{
			SMS.send(youmintuser,youmintpass,number,msg);
			break;
		}
		//System.out.println("No new msg from tc sleeping");
		Thread.sleep(Integer.parseInt(delay));
		}
		catch(Exception e){System.out.println(e);}
		}
	}

	public static String cmdExec(String cmdLine) 
	{
		String line;
	    String output = "";
		try {
	        Process p = Runtime.getRuntime().exec(cmdLine);
//System.out.println("executing");
			BufferedReader input = new BufferedReader
		        (new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
			    output += (line + '\n');
		    }
	       input.close();
			}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return output;
	}
}