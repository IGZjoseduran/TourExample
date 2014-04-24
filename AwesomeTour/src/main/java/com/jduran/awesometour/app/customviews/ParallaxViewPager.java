package com.jduran.awesometour.app.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose.duran on 24/04/2014.
 */
public class ParallaxViewPager extends ViewPager {

	private List<HorizontalScrollView> mLayers;
	private List<Boolean> mReverse;


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
		layer.fullScroll(FOCUS_RIGHT);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		super.onPageScrolled(position, positionOffset, positionOffsetPixels);

		if (mLayers != null && getAdapter() != null) {
			final int pageWidth = getWidth();
			final int viewpagerSwipeLength = pageWidth * (getAdapter().getCount() - 1);
			final int viewpagerOffset = (position * pageWidth) + positionOffsetPixels;

			final double viewpagerSwipeLengthRatio = (double) viewpagerOffset / viewpagerSwipeLength;

			for (int i=0;i<mLayers.size();i++) {
				HorizontalScrollView layer = mLayers.get(i);
				boolean reverse = mReverse.get(i);
				setOffset(layer, viewpagerSwipeLengthRatio, reverse);
			}
		}
	}

	private void setOffset(HorizontalScrollView layer, double viewpagerSwipeLengthRatio, boolean reverse) {
		if (layer.getChildCount() > 0) {
			int layerWidth = layer.getWidth();
			int layerContentWidth = layer.getChildAt(0).getWidth();
			int layerSwipeLength = layerContentWidth - layerWidth;

			double pageOffset = layerSwipeLength * viewpagerSwipeLengthRatio;
			if(!reverse) {
				layer.scrollTo((int) pageOffset, 0);
			} else {
				layer.scrollTo(layerSwipeLength - (int) pageOffset, 0);
			}
		}
	}

}