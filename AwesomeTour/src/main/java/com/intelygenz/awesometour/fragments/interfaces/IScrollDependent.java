package com.intelygenz.awesometour.fragments.interfaces;

/**
 * Created by jose.duran on 25/04/2014.
 */
public interface IScrollDependent {

	public static enum MoveType {
		ON_EXIT,
		ON_ENTER;
	}

	public void onScroll(float offset, MoveType moveType);

}
