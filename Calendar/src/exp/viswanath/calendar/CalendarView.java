package exp.viswanath.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Author : Viswanath L
 *
 * viswanath.l@experionglobal.com
 *
 * Dec 28, 2013
 *
 */

public class CalendarView extends LinearLayout implements OnPageChangeListener, OnItemSelectedListener, DateChangeListener, OnClickListener {

	private static final int STARTING_YEAR = 2010;
	private static final int ENDING_YEAR = 2050;

	private Button next;
	private Button previous;

	private ViewPager pager;
	private ScreenSlidePagerAdapter mPagerAdapter;
	private TextView display;
	private Spinner yearSpinner;
	private Spinner monthSpinner;

	private ArrayList<Integer> yearList;
	private ArrayList<Integer> monthList;
	private ArrayAdapter<Integer> yearAdapter;
	private ArrayAdapter<Integer> monthAdapter;

	private OnDateChangeListener listener;

	private int[] date;

	private int currentPage;

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		View rowView = LayoutInflater.from(context).inflate(R.layout.activity_calendar, null);

		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentYear = calendar.get(Calendar.YEAR);

		date = new int[2];	

		display = (TextView)rowView.findViewById(R.id.display);
		yearSpinner = (Spinner)rowView.findViewById(R.id.spinner_year);
		monthSpinner = (Spinner)rowView.findViewById(R.id.spinner_month);
		pager = (ViewPager)rowView.findViewById(R.id.pager);

		next = (Button)rowView.findViewById(R.id.right);
		next.setOnClickListener(this);
		previous = (Button)rowView.findViewById(R.id.left);
		previous.setOnClickListener(this);

		yearList = new ArrayList<Integer>();
		monthList = new ArrayList<Integer>();

		// Initialize the years to be displayed
		for(int i = STARTING_YEAR; i < ENDING_YEAR; i++)
			yearList.add(i);

		pager.setOnPageChangeListener(this);

		for(int i = 1; i <= 12; i++)
			monthList.add(i);	

		int totalMonths = (ENDING_YEAR - STARTING_YEAR) * 12;

		Activity activity = (Activity)context;

		// Initialize and set the ViewPager adapter
		mPagerAdapter = new ScreenSlidePagerAdapter(activity.getFragmentManager(), currentYear, currentMonth, totalMonths, STARTING_YEAR, this);
		pager.setAdapter(mPagerAdapter);

		changeDate(currentYear ,currentMonth);

		yearAdapter = new ArrayAdapter<Integer>(context, R.layout.spinner_element, yearList);
		yearSpinner.setAdapter(yearAdapter);

		monthAdapter = new ArrayAdapter<Integer>(context, R.layout.spinner_element, monthList);
		monthSpinner.setAdapter(monthAdapter);

		// Set the listener
		yearSpinner.setOnItemSelectedListener(this);
		monthSpinner.setOnItemSelectedListener(this);

		this.addView(rowView);
	}

	/**
	 * Set the corresponding month in adapter
	 * @param year
	 * @param month
	 */
	private void changeDate(int year, int month) {

		currentPage = getCurrentIndex(year, month);

		pager.setCurrentItem(currentPage);

		// Display the header on initialization
		mPagerAdapter.configureDetails(currentPage, date);
		display.setText(DateHelper.getMonth(date[1]) + " , " + date[0]);
	}

	/**
	 * Returns the position of the adapter
	 * @param year
	 * @param month
	 * @return
	 */
	private int getCurrentIndex(int year, int month)
	{
		int index = 0;

		int yearDiff = year - STARTING_YEAR;
		index = yearDiff * 12;

		index += month;

		return index;
	}

	@Override
	public void onPageScrolled(int pageNumber, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int currentPosition) {
		Log.d("", "pageSelected" + currentPosition);

		// Change the header on page change
		mPagerAdapter.configureDetails(currentPosition, date);
		display.setText(DateHelper.getMonth(date[1]) + " , " + date[0]);

		monthSpinner.setSelection(date[1]);
		yearSpinner.setSelection(date[0] - STARTING_YEAR);
	}

	@Override
	public void onPageScrollStateChanged (int state) {

	}

	// OnItemSelected listener methods for spinner
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		if(parent.getId() == R.id.spinner_year)
		{
			int year = yearList.get(position);
			int month = date[1];
			changeDate(year, month);
		}
		else if(parent.getId() == R.id.spinner_month)
		{
			int year = date[0];
			int month = monthList.get(position) - 1;
			changeDate(year, month);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 *  Set the listener to be notified on date selection
	 *  
	 * @param uiListener
	 */
	public void setListener(OnDateChangeListener uiListener)
	{
		listener = uiListener;
	}

	/*
	 * Pass the listener values to Ui listener
	 * 
	 * (non-Javadoc)
	 * @see exp.viswanath.calendar.DateChangeListener#OnDateSelected(int)
	 */
	@Override
	public void OnDateSelected(int day) {
		if(listener != null)
			listener.OnDateSelected(day, date[1] + 1, date[0]);
		else
			Log.d("","Date listener null");
	}

	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
		case R.id.right:
		{
			int totalMonths = ((ENDING_YEAR - STARTING_YEAR) * 12) - 1;
			if(currentPage < totalMonths)
				pager.setCurrentItem(currentPage + 1);
			break;
		}
		case R.id.left:
		{
			if(currentPage > 0)
				pager.setCurrentItem(currentPage - 1);
			break;
		}
		}

	}

}

