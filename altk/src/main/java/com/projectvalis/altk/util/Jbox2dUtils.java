package com.projectvalis.altk.util;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * two utilities to convert pixel-world to box2d-world coordinates and back.
 * see the below for more details
 * 
 * http://www.box2d.org/forum/viewtopic.php?f=3&t=8198 
 * http://box2d.org/2011/12/pixels/
 * 
 * TODO test them
 * 
 * @author snerd
 *
 */
public class Jbox2dUtils {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(Jbox2dUtils.class.getName());
	
	public static final int NUM_PIXELS_TO_METER = 50;
	
	
	/**
	 * 
	 * @param box2dCoordinate
	 * @param screenCenterInPixel
	 * @return
	 */
	public static Vec2 box2dToPixelCoordinate(Vec2 box2dCoordinate,
			                                  Vec2 screenCenterInPixel) {
		
		float offsetX = screenCenterInPixel.x / NUM_PIXELS_TO_METER;
		float offsetY = screenCenterInPixel.y / NUM_PIXELS_TO_METER;
		
		Vec2 pixelCoordinate = new Vec2();
		pixelCoordinate.x = box2dCoordinate.x + offsetX;
		pixelCoordinate.y = -box2dCoordinate.y + offsetY;
		
		return pixelCoordinate;
	}
	
	

	/**
	 * 
	 * @param pixelCoordinate
	 * @param screenCenterInPixel
	 * @return
	 */
	public static Vec2 pixelToBox2dCoordinate(Vec2 pixelCoordinate,
			                                  Vec2 screenCenterInPixel) {
		
		float offsetX = screenCenterInPixel.x;
		float offsetY = screenCenterInPixel.y;
		
		Vec2 box2dCoordinate = new Vec2();
		box2dCoordinate.x = pixelCoordinate.x - offsetX / NUM_PIXELS_TO_METER;
		box2dCoordinate.y = -pixelCoordinate.y + offsetY / NUM_PIXELS_TO_METER;
		
		return box2dCoordinate;
	}
	
	
}
