package com.projectvalis.altk.util;

import java.awt.event.KeyEvent;


/**
 * snip from the constants file in the processing library
 * 
 * https://github.com/processing/processing/blob/master/core/src/processing/core/PConstants.java
 * 
 * @author snerd
 *
 */
public class KeyConstants {
	
	  // key constants

	  // only including the most-used of these guys
	  // if people need more esoteric keys, they can learn about
	  // the esoteric java KeyEvent api and of virtual keys

	  // both key and keyCode will equal these values
	  // for 0125, these were changed to 'char' values, because they
	  // can be upgraded to ints automatically by Java, but having them
	  // as ints prevented split(blah, TAB) from working
	  public static final char BACKSPACE = 8;
	  public static final char TAB       = 9;
	  public static final char ENTER     = 10;
	  public static final char RETURN    = 13;
	  public static final char ESC       = 27;
	  public static final char DELETE    = 127;

	  // i.e. if ((key == CODED) && (keyCode == UP))
	  public static final int CODED     = 0xffff;

	  // key will be CODED and keyCode will be this value
	  public static final int UP        = KeyEvent.VK_UP;
	  public static final int DOWN      = KeyEvent.VK_DOWN;
	  public static final int LEFT      = KeyEvent.VK_LEFT;
	  public static final int RIGHT     = KeyEvent.VK_RIGHT;

	  // key will be CODED and keyCode will be this value
	  public static final int ALT       = KeyEvent.VK_ALT;
	  public static final int CONTROL   = KeyEvent.VK_CONTROL;
	  public static final int SHIFT = KeyEvent.VK_SHIFT;
}
