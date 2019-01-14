/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
         InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
         try {
 			properties.load(inputStream);
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         value = properties.getProperty(key);
         return value;
     }


   
    

}
