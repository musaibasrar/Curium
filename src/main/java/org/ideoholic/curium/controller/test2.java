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
				String data="username=" + URLEncoder.encode("curium", "UTF-8");
				data +="&message=" + URLEncoder.encode("Dear Parents Your fees rs 2000 has been received. From-ROSHAN GROUP MHDSHA", "UTF-8");
				data +="&sendername=" + URLEncoder.encode("MHDSHA", "UTF-8");
				data +="&smstype=" + "TRANS";
				data +="&numbers=" + URLEncoder.encode(phonenumbers, "UTF-8");
				data +="&apikey=" + "4dbc3dd6-4baa-4c8e-a330-14659cee2f78";
				data +="&peid=" + "1505165492912832620";
				data +="&templateid=" + "1507165554392812692";
				
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

