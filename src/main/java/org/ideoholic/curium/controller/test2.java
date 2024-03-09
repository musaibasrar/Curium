package org.ideoholic.curium.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class test2 {

	
	 public static void main(String []args) throws IOException{
	     
		 int responseCode = 0;
			try 
			{
				String phonenumbers="9986338332";
				String data="username=" + URLEncoder.encode("bsratlam", "UTF-8");
				data +="&message=" + URLEncoder.encode("Dear Parents, We would like to inform you that the var1 will commence from var2.Best regards, Brainy Stars,CURIUM Software, www.ideoholic.com", "UTF-8");
				data +="&sendername=" + URLEncoder.encode("IDOHLC", "UTF-8");
				data +="&smstype=" + "TRANS";
				data +="&numbers=" + URLEncoder.encode(phonenumbers, "UTF-8");
				data +="&apikey=" + "5f3367a4-210f-4df4-9663-34bb3d7e2898";
				data +="&peid=" + "1001978896612375681";
				data +="&templateid=" + "1007542155026968676";
				
			String POST_URL = "http://sms.bulksmsind.in/sendSMS?"+data;
			System.out.println(": URL "+POST_URL);
	        URL obj = new URL(POST_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write("CURIUM".getBytes());
			os.flush();
			os.close();
			// For POST only - END

			responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}}
			catch (Exception e)
			{
				System.out.println("Error SMS "+e);
			}
			System.out.println("HIIIIIIIII "+responseCode);
	 }
}

