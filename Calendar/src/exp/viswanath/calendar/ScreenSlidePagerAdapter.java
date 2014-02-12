package exp.viswanath.calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/*
 *
 * Author: Viswanath L
 *
 * viswanath.l@experionglobal.com
 *
 * 11-Dec-2013
 *
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

	private static int COUNT = 100000; 
	private static final int MONTHS = 12;

	private int minYear;

	private int year;
	private int month;

	private DateChangeListener listener;
	
	public ScreenSlidePagerAdapter(FragmentManager fm, int mYear, int mMonth, int totalCount, int minYear, DateChangeListener listener) {
		super(fm);
		
		COUNT = totalCount;

		this.year = mYear;
		this.month = mMonth;
		
		this.listener = listener;
		
		setMinYear(minYear);
	}

	@Override
	public Fragment getItem(int position) {

		int[] date = new int[2];
		configureDetails(position, date);
		
		ScreenSlidePageFragment fragment = ScreenSlidePageFragment.create(year, month);
		// Set the listener to be notified on date selection
		fragment.setListener(listener);
		return fragment;
	}

	/**
	 * Gets the year and month associated with the specified position 
	 * @param position
	 * @param date
	 */
	public void configureDetails(int position, int[] date) {

		month = position % MONTHS;
		year = minYear + position / MONTHS;
		
		date[0] = year;
		date[1] = month;
	}

	/**
	 * Set the minimum value for year
	 * @param year
	 */
	private void setMinYear(int year)
	{
		minYear = year;
	}

	@Override
	public int getCount() {
		return COUNT;
	}

}

