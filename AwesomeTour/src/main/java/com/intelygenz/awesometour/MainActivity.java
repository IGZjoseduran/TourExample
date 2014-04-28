package com.intelygenz.awesometour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.intelygenz.awesometour.fragments.TourFragment;


public class MainActivity extends ActionBarActivity {

	private Button mParallaxButton;
	private Button mBackgroundButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mParallaxButton = (Button) findViewById(R.id.bt_parallax);
		mBackgroundButton = (Button) findViewById(R.id.bt_background);



	}


	@Override
	protected void onResume() {
		super.onResume();
		setListeners();
	}

	private void setListeners(){
		mParallaxButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ParallaxTourActivity.class);
				startActivity(intent);
			}
		});
		mBackgroundButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VideoBackgroundActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	/**
	 * For this example, the adapter will have a hardcoded number of pages. Each
	 * page shows a TextView with the position of the page
	 */
	private class CustomAdapter extends FragmentPagerAdapter {

		private static final int PAGES_COUNT = 5;


		public CustomAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return PAGES_COUNT;
		}


		@Override
		public Fragment getItem(int position) {
			return TourFragment.newInstance(position);
		}


	}


}
