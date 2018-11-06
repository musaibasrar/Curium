package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.model.sendsms.dao.SmsDAO;

public class test {

	
	public static void main(String[] args) throws SocketException, IOException{

	    
	    String word = "musaibone";
	    if (word.length() == 3) {
	        System.out.println(word);
	      } else if (word.length() > 3) {
	          System.out.println( word.substring(word.length() - 3));
	      } else {
	        // whatever is appropriate in this case
	        throw new IllegalArgumentException("word has less than 3 characters!");
	      }
	    
	/*	Map<Date,Integer> myMap = new HashMap<Date, Integer>();
		myMap.put(new Date(), 1);
		myMap.put(new Date(), 2);
		myMap.put(new Date(), 3);
		myMap.put(new Date(), 4);
		
		for (Map.Entry<Date, Integer> string : myMap.entrySet()) {
			System.out.println("key is "+string.getKey());
		}*/
		
	    double ip=348842;
	            double i2=ip/60000;
	            String numberAsString = String.format ("%.2f", i2);
	            double t = Double.valueOf(numberAsString);
	            System.out.println("double"+i2);
	            System.out.println("double"+t);
	    
	    
	    int number = 10;        
	    String str = String.format("%04d", number);  // 0009      
	    System.out.printf("original number %d, numeric string with padding : %s", 9, str);
		
		
		 SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		 String dateBeforeString = "27 01 2018";
		 String dateAfterString = "30 01 2018";

		 try {
		       Date dateBefore = myFormat.parse(dateBeforeString);
		       Date dateAfter = myFormat.parse(dateAfterString);
		       
		       Calendar start = Calendar.getInstance();
				start.setTime(dateBefore);
				Calendar end = Calendar.getInstance();
				end.setTime(dateAfter);
				end.add(Calendar.DATE, 1);
		       
		       for (Date date = start.getTime(); start.before(end); start.add(Calendar.DAY_OF_MONTH, +1), date = start.getTime()) {
				    // Do your job here with `date`.
				    System.out.println("*******************************"+new SimpleDateFormat("dd-MM-YYYY").format(date) );
				}
		       
		       long difference = dateAfter.getTime() - dateBefore.getTime();
		       float daysBetween = (difference / (1000*60*60*24));
	               /* You can also convert the milliseconds to days using this method
	                * float daysBetween = 
	                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
	                */
		       for(int j =1; j<=(int)daysBetween; j++){
		    	   System.out.println("SUCCESS: ");
				}
		       System.out.println("Number of Days between dates: "+daysBetween);
		 } catch (Exception e) {
		       e.printStackTrace();
		 }
	   
		
		
		
		double totalNumbers = 2;
		Format formatter = new SimpleDateFormat("MMMM"); 
	    String s = formatter.format(new Date());
	    System.out.println(s);

			final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			int count =4;
			StringBuilder builder = new StringBuilder();
			while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			System.out.println(builder.toString());

		
		
		
		
		System.out.println("result "+totalNumbers/100);
		double iterations = (double) Math.ceil(totalNumbers/100);
		System.out.println("number is "+iterations);
		
		URL url = null;
		URLConnection con = null;
		int i;
/*		try {
			InputStream inputStream = null;
			OutputStream outputStream = null;
			// read this file into InputStream
			inputStream = new FileInputStream("D:/personalmusaib/Zeee.xlsx");

			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(new File("D:/personalmusaib/ZeeeMushy.xlsx"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			
			
			
			url = new URL("http://searchmysearch.com/files/Mushy.xlsx");
			con = url.openConnection();
			//File file = new File("D:/personalmusaib/Zeee.xlsx");
			//String fileName = file.getName();
			BufferedInputStream bis = new BufferedInputStream(
					con.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File("D:/personalmusaib/Zeee.xlsx")));
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
			bis.close();
		} catch (MalformedInputException malformedInputException) {
			malformedInputException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}*/
		
		
		 //Read CSV
	           /* String csvFile = "E:\\memberdata.csv";
	                String line = "";
	                String cvsSplitBy = ",";

	                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	                    while ((line = br.readLine()) != null) {

	                        // use comma as separator
	                        String[] rowData = line.split(cvsSplitBy);

	                        System.out.println("First Name= " + rowData[0] + " , Last Name=" + rowData[1] + "");

	                    }

	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	            }*/
	}
		
}

