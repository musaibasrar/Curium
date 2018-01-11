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

import org.apache.commons.net.ftp.FTPClient;

import com.model.sendsms.dao.SmsDAO;

public class test {

	
	public static void main(String[] args) throws SocketException, IOException{
		double totalNumbers = 2;
		 
		
		System.out.println("result "+totalNumbers/100);
		double iterations = (double) Math.ceil(totalNumbers/100);
		System.out.println("number is "+iterations);
		
		URL url = null;
		URLConnection con = null;
		int i;
		try {
			/*InputStream inputStream = null;
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
			*/
			
			
			
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
		}
	}
		
}

