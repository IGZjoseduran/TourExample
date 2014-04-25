package com.intelygenz.awesometour.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.intelygenz.awesometour.R;
import com.intelygenz.awesometour.fragments.interfaces.IScrollDependent;
import com.nineoldandroids.view.ViewHelper;


public class TourFragment extends Fragment implements IScrollDependent {
	private static final String BUNDLE_EXTRA_POSITION = "position";
	private ImageView mMainImage;
	private ImageView mFirstImage;
	private ImageView mSecondImage;
	private ImageView mThirdImage;

	private static int fromXMain = 200;
	private static int fromXFirst = 500;
	private static int fromXSecond = 1500;
	private static int fromXThird = 1000;

	private int mPosition;

	public static TourFragment newInstance(int position) {
		TourFragment fragment = new TourFragment();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_EXTRA_POSITION, position);
		fragment.setArguments(args);
		return fragment;
	}

	public TourFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;

		switch (mPosition){
			case 2:
				view = inflater.inflate(R.layout.page2, container, false);
				break;
			case 1:
				view = inflater.inflate(R.layout.page1, container, false);
				break;
			case 0:
			default:
				view = inflater.inflate(R.layout.page, container, false);
		}


		mMainImage = (ImageView) view.findViewById(R.id.iv_main);
		mFirstImage = (ImageView) view.findViewById(R.id.iv_first_image);
		mSecondImage = (ImageView) view.findViewById(R.id.iv_second_image);
		mThirdImage = (ImageView) view.findViewById(R.id.iv_third_image);


		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mPosition = getArguments().getInt(BUNDLE_EXTRA_POSITION, 0);
	}


	/**
	 * To animate views
	 * @param offset 1 to completed Animation | 0 to startedAnimation
	 */
	@Override
	public void onScroll(float offset, MoveType moveType) {
		Log.d("OnScroll", "" + offset);
		if(moveType == MoveType.ON_ENTER) {
			enterImage(offset, mMainImage, fromXMain);
			enterImage(offset, mFirstImage, fromXFirst);
			enterImage(offset, mSecondImage, fromXSecond);
			enterImage(offset, mThirdImage, fromXThird);
		} else {
			exitImage(offset, mMainImage, -fromXMain);
			exitImage(offset, mFirstImage, -fromXFirst);
			exitImage(offset, mSecondImage, -fromXSecond);
			exitImage(offset, mThirdImage, -fromXThird);
		}
	}


	/**
	 * Entering in scene. make a translation
	 * @param offset
	 * @param view
	 * @param fromX
	 */
	private void enterImage(float offset, View view,  int fromX){
		ViewHelper.setTranslationX(view, (1-offset) * fromX);
	}

	/**
	 * Exiting the scene. make translation
	 * @param offset,
	 * @param view
	 * @param toX
	 */
	private void exitImage(float offset, View view, int toX){
		ViewHelper.setTranslationX(view, (offset) * toX);
	}

}
