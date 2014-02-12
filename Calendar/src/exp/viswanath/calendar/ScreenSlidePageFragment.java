package exp.viswanath.calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/*
 * Author : Viswanath L
 *
 * viswanath.l@experionglobal.com
 *
 * Dec 11, 2013
 * 
 */

public class ScreenSlidePageFragment extends Fragment implements OnItemClickListener{

	public static final String ARG_YEAR = "year";
	public static final String ARG_MONTH = "month";
	
	private int mYear;
	private int mMonth;

	private MonthAdapter adapter;
	
	private DateChangeListener listener;
	
	/**
	 * Factory method for this fragment class. Constructs a new fragment for the given year and month.
	 */
	public static ScreenSlidePageFragment create(int year, int month) {

		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_YEAR, year);
		args.putInt(ARG_MONTH, month);
		fragment.setArguments(args);
		return fragment;
	}

	public ScreenSlidePageFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mYear = getArguments().getInt(ARG_YEAR);
		mMonth = getArguments().getInt(ARG_MONTH);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout containing the imageView
		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.grid_layout, container, false);

		GridView gridView = (GridView) rootView.findViewById(R.id.gridView1);
		
		final DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

		adapter = new MonthAdapter(getActivity(), mMonth, mYear, metrics);
		
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		
		return rootView;
	}

	public void setListener(DateChangeListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		// Give call to internal listener on date selection
		listener.OnDateSelected(adapter.getSelectedDay(position));
	}
}
