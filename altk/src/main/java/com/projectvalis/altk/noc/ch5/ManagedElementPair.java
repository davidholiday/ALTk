package com.projectvalis.altk.noc.ch5;

import com.projectvalis.altk.util.Pair;

/**
 * convenience class to create a spcialized pair containing an element's 
 * model and view manager
 * 
 * @author snerd
 *
 */
public class ManagedElementPair 
    extends Pair<ManagedElementModel, ManagedElementView> {

	public ManagedElementPair(
			ManagedElementModel left, ManagedElementView right) {
		
		super(left, right);
	}

}
