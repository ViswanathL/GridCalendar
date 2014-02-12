package exp.viswanath.calendar;

/*
 * Author : Viswanath L
 *
 * viswanath.l@experionglobal.com
 *
 * Dec 29, 2013
 *
 */

public interface OnDateChangeListener {

	/**
	 * 
	 * Month will be returned on the basis of 0 , jan - dec will be represented as 0-11
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public void OnDateSelected(int day, int month, int year);
}

