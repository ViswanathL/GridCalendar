package exp.viswanath.calendar;

/*
 *
 * Author: Viswanath L
 *
 * viswanath.l@experionglobal.com
 *
 * 24-Dec-2013
 *
 */

public class DateHelper {

	private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	/**
	 * 
	 * @param index , Starting from 0
	 * @return Name of the month at the specified index
	 */
	
	public static String getMonth(int index)
	{
		return months[index];
	}
	
}
