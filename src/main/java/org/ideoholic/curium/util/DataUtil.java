/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ideoholic.curium.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Musaib
 */
public class DataUtil {
    
     static SimpleDateFormat simpleDateFormat;
    static Date returnDate;
    /**
     *
     * @param source String Object
     * @return String Object
     */
    public static String emptyString(String source){
        String target="";
        if(source==null || source.equalsIgnoreCase("")){
            target="";

        }
        else{
            target=source;
        }
        return  target;
    }
    /**
     *
     * @param src
     * @return
     */
    public static short parseShort(String src){
        if(src==null || src.equalsIgnoreCase("")){
            return 0;
        }
        else{
            return Short.parseShort(src);
        }
        
    }
    /**
     *
     * @param src
     * @return
     */
    public static long parseLong(String src){
        if(src==null || src.equalsIgnoreCase("")){
            return 0l;
        }
        else{
            return Long.parseLong(src);
        }

    }
    /**
     *
     * @param src
     * @return
     */
    public static double parseDouble(String src){
        if(src==null || src.equalsIgnoreCase("")){
            return 0.0;
        }
        else{
            return Double.parseDouble(src);
        }

    }

     public static int parseInt(String src){
        if(src==null || src.equalsIgnoreCase("")){
            return 0;
        }
        else{
            return Integer.parseInt(src);
        }
        
    }
     
     
     public String getPropertiesValue(String key){
    	 String value= null;
    	 Properties properties = new Properties();
         InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
         try {
 			properties.load(inputStream);
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         value = properties.getProperty(key);
         return value;
     }


   public static String dateFromatConversionDash(String ddmmyyyy) {
	   String date = null;
	   
	   if(ddmmyyyy!=null) {
		   String[] dateToConvert = ddmmyyyy.split("-");
		   date = dateToConvert[2]+"-"+dateToConvert[1]+"-"+dateToConvert[0];
	   }
	   
	   return date;
   }
   
   
   public static String dateFromatConversionSlash(String ddmmyyyy) {
	   String date = null;
	   
	   if(ddmmyyyy!=null) {
		   String[] dateToConvert = ddmmyyyy.split("/");
		   date = dateToConvert[2]+"/"+dateToConvert[1]+"/"+dateToConvert[0];
	   }
	   
	   return date;
   }
   
   public static String dateFromatConversionSlashToNoSlash(String ddmmyyyy) {
	   String date = null;
	   
	   if(ddmmyyyy!=null) {
		   String[] dateToConvert = ddmmyyyy.split("/");
		   date = dateToConvert[2]+""+dateToConvert[1]+""+dateToConvert[0];
	   }
	   
	   return date;
   }
   
   public static String generateString(int lengthh) {

       // ||, -, *, /, <>, <, >, ,(comma), =, <=, >=, ~=, !=, ^=, (, )
       String alphaNumeric = new String("123456abcdefghi7890jklmnopqrstuvwxyz");
       int alphaNumericLength = alphaNumeric.length();

       String generatedRandomNumber = new String();

       for (int i = 0; i < lengthh; i++) {
           int index = generateIndex(alphaNumericLength);
           generatedRandomNumber = generatedRandomNumber + alphaNumeric.charAt(index);
       }
       System.out.println("RANDOMNUMBER IS ::: "+generatedRandomNumber);
       return generatedRandomNumber;

   }

   private static int generateIndex(int alphaNumericLength) {
       int newRandomNumber = returnRandom(alphaNumericLength);
       return newRandomNumber;
   }

   private static int returnRandom(int alphaNumericLength) {
       Random random = new Random();
       int mathrandom = random.nextInt(59245);

       do {
           double randomDouble = Math.random();
           if (randomDouble < 0.01) continue;
           mathrandom = (int) (mathrandom * randomDouble);
       } while (mathrandom >= alphaNumericLength);
       return mathrandom;
   }
     
    

}
