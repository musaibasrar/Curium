/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Musaib
 */
public class DateUtil {

    static SimpleDateFormat simpleDateFormat;
    static Date returnDate;

    /**
     *
     * @param source String Object
     * @return Date Object
     */
    public static Date dateParser1(String source) {
        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm");
        try {
            returnDate = simpleDateFormat.parse(source);

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    /**
     *
     * @param source String Object
     * @return
     */
    public static Date dateParser(String source) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        try {
            returnDate = simpleDateFormat.parse(source);

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    /**
     *
     * @param source
     * @return
     */
    public static Date simpleDateParser(String stringDate) {/*
        //simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); original
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            returnDate = formatter.parse(source);
            System.out.println("the date in the dateutil is "+returnDate);

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    */


		Date datefinal = null;
		try {
			Date date = null;

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
					Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

			date = (Date) formatter.parse(stringDate);
			String secdate = df.format(date);

			datefinal = (Date) dateformatter.parse(secdate);

		} catch (ParseException e) {

			System.out.println("Exception :" + e);

		}
		return datefinal;
    
    }

    
    public static Date datePars(String stringDate) {
    	
		Date datefinal = null;
		try {
			Date date = null;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

			date = (Date) formatter.parse(stringDate);
			String secdate = df.format(date);

			datefinal = (Date) dateformatter.parse(secdate);

		} catch (ParseException e) {

			Date datefinal1 = null;
			try {
				Date date = null;

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
						Locale.ENGLISH);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

				date = (Date) formatter.parse(stringDate);
				String secdate = df.format(date);

				datefinal1 = (Date) dateformatter.parse(secdate);
				datefinal = datefinal1;
			} catch (ParseException e1) {
				System.out.println("Exception :" + e1);
			}
		}
		return datefinal;
    }
    /**
     *
     * @param source
     * @return
     */
    public static Date yesterdayDateParser(String source) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();

        try {
            returnDate = simpleDateFormat.parse(source);
            calender.setTime(returnDate);
            calender.add(Calendar.HOUR_OF_DAY, -24);
            returnDate = calender.getTime();

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    /**
     *
     * @param source
     * @return
     */
    public static Date yesterdayDateParser1(String source) {
        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm");
        Calendar calender = Calendar.getInstance();

        try {
            returnDate = simpleDateFormat.parse(source);
            calender.setTime(returnDate);
            calender.add(Calendar.HOUR_OF_DAY, -24);
            returnDate = calender.getTime();

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    /**
     *
     * @param source
     * @return
     */
    public static Date tomorrowsDateParser(String source) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();

        try {
            returnDate = simpleDateFormat.parse(source);
            calender.setTime(returnDate);
            calender.add(Calendar.HOUR_OF_DAY, 24);
            returnDate = calender.getTime();

        } catch (ParseException ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    /**
     *
     * @param source
     * @return
     */
    public static Date tomorrowsDate(Date source) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();

        try {
            calender.setTime(source);
            calender.add(Calendar.HOUR_OF_DAY, 24);
            returnDate = calender.getTime();

        } catch (Exception ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }

    public static String todaysDateydm() {
    	Format f = new SimpleDateFormat("yyyyMMdd");
	    String strDate = f.format(new Date());
        return strDate;
    }
    
    /**
     *
     * @return
     */
    public static Date todaysDate() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        returnDate = calender.getTime();
        return returnDate;
    }

    /**
     *
     * @return
     */
    public static Date todaysDateTime() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();
        returnDate = calender.getTime();
        return returnDate;
    }

    /**
     *
     * @param hour
     * @param min
     * @return
     */
    public static Date todaysDateTime(int hour, int min) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.MINUTE, min);
        calender.set(Calendar.HOUR_OF_DAY, hour);
        returnDate = calender.getTime();
        return returnDate;
    }

    /**
     *
     * @param src
     * @return
     */
    public static String format(Date src) {
        simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(src);
    }

    /**
     *
     * @param source
     * @param months
     * @return
     */
    public static Date forwardMonths(Date source, int months) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Calendar calender = Calendar.getInstance();
        try {
            calender.setTime(source);
            calender.add(Calendar.MONTH, months);
            returnDate = calender.getTime();

        } catch (Exception ex) {
            ex.printStackTrace();
            returnDate = null;
        } finally {
            return returnDate;
        }

    }
    
public static Date dateParserUpdateStd(String stringDate) {
    	
		Date datefinal = null;
		try {
			Date date = null;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
					Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

			date = (Date) formatter.parse(stringDate);
			String secdate = df.format(date);

			datefinal = (Date) dateformatter.parse(secdate);

		} catch (ParseException e) {
				e.printStackTrace();
		
		}

		return datefinal;
    }


public static Date dateParserdd(String stringDate) {
	
	Date datefinal = null;
	try {
		Date date = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.ENGLISH);

		date = (Date) formatter.parse(stringDate);
		String secdate = formatter.format(date);

		datefinal = (Date) formatter.parse(secdate);

	} catch (ParseException e) {
			e.printStackTrace();
	}
	return datefinal;
}

public static Date dateParserddmmyyyy(String stringDate) {
	
	Date datefinal = null;
	try {
		Date date = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",
				Locale.ENGLISH);
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

		date = (Date) formatter.parse(stringDate);
		String secdate = dateformatter.format(date);

		datefinal = (Date) dateformatter.parse(secdate);

	} catch (ParseException e) {
			e.printStackTrace();
	
	}

	return datefinal;
}

public static String getStringDate(Date dateofbirth) {
	
	String date = null;
	
	if(dateofbirth!=null) {
		date = dateofbirth.toString();
	}
	
	return date;
	}

public static String dateParserddMMYYYY(Date date) {
	
	String datefinal = null;
	try {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
				Locale.ENGLISH);
		datefinal = formatter.format(date);

	} catch (Exception e) {
			e.printStackTrace();
	}
	return datefinal;
}

public static String dateParseryyyymmdd(Date date) {
	
	String datefinal = null;
	try {
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

		datefinal = dateformatter.format(date);
		System.out.println("datefinal "+datefinal);
	} catch (Exception e) {
			e.printStackTrace();
	}
	return datefinal;
}

public static Date indiandateParser(String stringDate) {
	
	Date datefinal = null;
	try {
		Date date = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
				Locale.ENGLISH);
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

		date = (Date) formatter.parse(stringDate);
		String secdate = dateformatter.format(date);

		datefinal = (Date) dateformatter.parse(secdate);

	} catch (ParseException e) {
			e.printStackTrace();
	
	}

	return datefinal;
}


public static String dateFromatConversionSlash(String ddmmyyyy) {
	   String date = null;
	   
	   if(ddmmyyyy!=null) {
		   String[] dateToConvert = ddmmyyyy.split("/");
		   date = dateToConvert[2]+"-"+dateToConvert[1]+"-"+dateToConvert[0];
	   }
	   
	   return date;
}


}
