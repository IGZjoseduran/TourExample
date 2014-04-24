package com.jduran.awesometour.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import com.jduran.awesometour.app.customviews.ParallaxViewPager;


public class MainActivity extends ActionBarActivity {

	private ParallaxViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    mViewPager = (ParallaxViewPager) findViewById(R.id.viewPager);

	    mViewPager.addLayer((HorizontalScrollView) findViewById(R.id.layer0));
	    mViewPager.addReverseLayer((HorizontalScrollView) findViewById(R.id.layer1));

	    mViewPager.setAdapter(new CustomAdapter());
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
	 *
	 */
	private class CustomAdapter extends PagerAdapter {

		private static final int PAGES_COUNT = 3;

		@Override
		public int getCount() {
			return PAGES_COUNT;
		}



		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((View) object);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			// Inflate and populate the page
			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.page, (ViewPager) collection, false);
			((ViewPager) collection).addView(view);

			((TextView) view.findViewById(R.id.text)).setText(position + 1 + "/" + getCount());

			return view;

		}

	}


}
