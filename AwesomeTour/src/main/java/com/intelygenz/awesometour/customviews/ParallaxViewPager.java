package com.intelygenz.awesometour.customviews;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import com.intelygenz.awesometour.fragments.interfaces.IScrollDependent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose.duran on 24/04/2014.
 */
public class ParallaxViewPager extends ViewPager {
	//Layers
	private List<HorizontalScrollView> mLayers;
	//Layer orientation
	private List<Boolean> mReverse;

	private FragmentManager mFragmentManager;


	public void setFragmentManager(FragmentManager fragmentManager) {
		mFragmentManager = fragmentManager;
	}

	public ParallaxViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ParallaxViewPager(Context context) {
		super(context);
		init();
	}

	private void init() {
		mLayers = new ArrayList<HorizontalScrollView>();
		mReverse = new ArrayList<Boolean>();
	}

	public void addLayer(HorizontalScrollView layer) {
		mLayers.add(layer);
		mReverse.add(false);
	}

	public void addReverseLayer(HorizontalScrollView layer) {
		mLayers.add(layer);
		mReverse.add(true);
		/**
		 * Reverse layers start focused on right
		 */
		layer.fullScroll(FOCUS_RIGHT);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		super.onPageScrolled(position, positionOffset, positionOffsetPixels);
		/**
		 * To Animate fragments
		 */
		animateFragmentContent(position, positionOffset);

		/**
		 * If there is any layer make the parallax effect
		 */
		if (mLayers != null && getAdapter() != null) {
			final int pageWidth = getWidth();
			final int viewpagerSwipeLength = pageWidth * (getAdapter().getCount() - 1);
			final int viewpagerOffset = (position * pageWidth) + positionOffsetPixels;

			final double viewpagerSwipeLengthRatio = (double) viewpagerOffset / viewpagerSwipeLength;

			for (int i = 0; i < mLayers.size(); i++) {
				HorizontalScrollView layer = mLayers.get(i);
				boolean reverse = mReverse.get(i);
				setOffset(layer, viewpagerSwipeLengthRatio, reverse);
			}
		}
	}


	private void animateFragmentContent(int position, float positionOffset) {
		if (mFragmentManager != null) {
			//Current Fragment is always exiting
			IScrollDependent f = (IScrollDependent) mFragmentManager.findFragmentByTag(makeFragmentName(this.getId(), position));
			if (f != null) {
				f.onScroll(positionOffset, IScrollDependent.MoveType.ON_EXIT);
			}

			//Next Fragment is always entering
			IScrollDependent nextFragment = (IScrollDependent) mFragmentManager.findFragmentByTag(makeFragmentName(this.getId(), position + 1));
			if (nextFragment != null) {
				nextFragment.onScroll(positionOffset, IScrollDependent.MoveType.ON_ENTER);
			}
		}
	}

	private void setOffset(HorizontalScrollView layer, double viewpagerSwipeLengthRatio, boolean reverse) {
		if (layer.getChildCount() > 0) {
			int layerWidth = layer.getWidth();
			int layerContentWidth = layer.getChildAt(0).getWidth();
			int layerSwipeLength = layerContentWidth - layerWidth;

			double pageOffset = layerSwipeLength * viewpagerSwipeLengthRatio;
			if (!reverse) {
				layer.scrollTo((int) pageOffset, 0);
			} else {
				layer.scrollTo(layerSwipeLength - (int) pageOffset, 0);
			}
		}
	}

	/**
	 * FragmentViewPagerAdapter attach fragments to Fragment Manager in this way
	 *
	 * @param viewId, id of parent view
	 * @param id,     position of fragment
	 * @return, Tag of fragment
	 */
	private String makeFragmentName(int viewId, long id) {
		return "android:switcher:" + viewId + ":" + id;
	}

}