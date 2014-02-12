package exp.viswanath.calendar;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class CalendarActivity extends Activity implements OnDateChangeListener {

	private CalendarView myView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity);

		myView = (CalendarView)findViewById(R.id.calendar);
		myView.setListener(this);
	}

	@Override
	public void OnDateSelected(int day, int month, int year) {
		Toast.makeText(this, day + "-" + month + "-" + year, Toast.LENGTH_SHORT).show();
	}

}
