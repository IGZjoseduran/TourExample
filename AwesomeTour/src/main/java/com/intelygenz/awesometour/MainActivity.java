package com.intelygenz.awesometour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import com.intelygenz.awesometour.customviews.ParallaxViewPager;
import com.intelygenz.awesometour.fragments.TourFragment;


public class MainActivity extends ActionBarActivity {

	private ParallaxViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ParallaxViewPager) findViewById(R.id.viewPager);

		/**
		 * Needed Fragment Manager to animate fragment content
		 */
		mViewPager.setFragmentManager(getSupportFragmentManager());
		/**
		 * Animate Horizontal ScrollView in parallax effect
		 */
		mViewPager.addLayer((HorizontalScrollView) findViewById(R.id.layer0));

		/**
		 * Animate Horizontal ScrollView in a reverse parallax effect
		 */
		mViewPager.addReverseLayer((HorizontalScrollView) findViewById(R.id.layer1));


		mViewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));

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
