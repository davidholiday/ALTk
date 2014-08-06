/**
 * Copyright (c) 2012, Dhilshuk Reddy All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute SwingJD software is freely
 * granted, provided that this notice is preserved.
 */
package com.jd.swing.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author dhilshuk
 * 
 */
public class Colors {

	private Map<Integer, GradientPaint> colorMap = new HashMap<Integer, GradientPaint>();

	private static Colors colorUtils;

	private GradientPaint glossyTopBtnColor;

	private Paint bgColor;

	private Color textColor = Color.WHITE;

	/**
	 * Returns the instance.
	 * 
	 * @return instance of Colors
	 */
	public static Colors getInStance() {
		if (colorUtils == null) {
			colorUtils = new Colors();
		}
		return colorUtils;
	}

	/**
	 * Returns standard color.
	 * 
	 * @param theme
	 *            standard theme
	 * @param startPoint
	 *            start point of the color
	 * @param endPoint
	 *            end point of the color
	 * @return list of standard colors
	 */
	public List getStandardColor(int theme, int startPoint, int endPoint) {
		List colors = new ArrayList();
		GradientPaint bgColor = null;

		switch (theme) {
		case Theme.STANDARD_DARKGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(0, 140, 0), 0,
					endPoint, new Color(0, 85, 0));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_BLUEGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(31, 175, 114),
					0, endPoint, new Color(20, 113, 74));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_GREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(102, 223, 36),
					0, endPoint, new Color(68, 154, 23));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_LIGHTGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(121, 232, 98),
					0, 3 * endPoint / 4, new Color(61, 208, 31));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_OLIVEGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(117, 198, 6),
					0, endPoint, new Color(68, 116, 4));
			textColor = Color.WHITE;
			break;

		case Theme.STANDARD_LIME_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(181, 223, 38),
					0, 3 * endPoint / 4, new Color(137, 170, 26));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_RED_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 100, 100), 0, endPoint, new Color(255, 0, 0));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_DARKRED_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(255, 0, 6), 0,
					endPoint, new Color(181, 0, 0));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_ORANGE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(251, 139, 62),
					0, endPoint, new Color(255, 102, 0));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_LIGHTORANGE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(247, 174, 24),
					0, endPoint, new Color(255, 133, 0));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_DARKYELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(185, 181, 0),
					0, endPoint, new Color(123, 120, 0));
			textColor = Color.WHITE;
			break;

		case Theme.STANDARD_GREENYELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(253, 247, 11),
					0, endPoint, new Color(211, 204, 2));
			textColor = Color.BLACK;
			break;

		case Theme.STANDARD_GOLD_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(255, 233, 18),
					0, endPoint, new Color(255, 213, 0));
			textColor = Color.BLACK;
			break;
		case Theme.STANDARD_YELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 166), 0, endPoint, new Color(255, 255,
							56));
			textColor = Color.BLACK;
			break;

		case Theme.STANDARD_BROWN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(202, 62, 2),
					0, 3 * endPoint / 4, new Color(118, 35, 1));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_LIGHTBROWN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(232, 194, 125), 0, 3 * endPoint / 4, new Color(
							212, 151, 37));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_PALEBROWN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(248, 234, 203), 0, 3 * endPoint / 4, new Color(
							236, 205, 132));
			textColor = Color.BLACK;
			break;
		case Theme.STANDARD_NAVYBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(44, 105, 180),
					0, endPoint, new Color(5, 25, 114));
			textColor = Color.WHITE;
			break;

		case Theme.STANDARD_INDIGO_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(49, 120, 206),
					0, endPoint, new Color(35, 84, 146));
			textColor = Color.WHITE;

			break;

		case Theme.STANDARD_BLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(58, 92, 252),
					0, endPoint, new Color(3, 37, 188));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_SKYBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(17, 136, 255),
					0, endPoint, new Color(0, 96, 194));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_LIGHTBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(51, 191, 238),
					0, endPoint, new Color(17, 160, 208));

			textColor = Color.WHITE;

			break;

		case Theme.STANDARD_DARKPURPLE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(82, 0, 164),
					0, endPoint, new Color(44, 0, 89));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_PURPLE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(203, 64, 239),
					0, endPoint, new Color(186, 0, 255));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_LAVENDER_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(165, 117, 239), 0, endPoint, new Color(107, 60,
							173));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_DARKPINK_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(170, 0, 128),
					0, endPoint, new Color(115, 0, 85));
			textColor = Color.WHITE;

			break;

		case Theme.STANDARD_PINK_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(238, 83, 133),
					0, endPoint, new Color(220, 22, 86));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_PALEPINK_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 174, 235), 0, endPoint, new Color(255, 128,
							223));
			textColor = Color.WHITE;

			break;

		case Theme.STANDARD_BLACK_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(90, 90, 90),
					0, endPoint, new Color(0, 0, 0));
			textColor = Color.WHITE;

			break;

		case Theme.STANDARD_GRAY_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(90, 90, 90),
					0, endPoint, new Color(70, 70, 70));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_LIGHTGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(163, 163, 163), 0, endPoint, new Color(128, 128,
							128));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_METALLICGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(151, 164, 170), 0, 3 * endPoint / 4, new Color(
							120, 137, 145));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_BLUEGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(68, 113, 153),
					0, endPoint, new Color(32, 53, 72));
			textColor = Color.WHITE;

			break;
		case Theme.STANDARD_VOILET_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(148, 148, 255), 0, endPoint, new Color(98, 98,
							255));
			textColor = Color.WHITE;
			break;
		case Theme.STANDARD_SILVER_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(236, 241, 242), 0, endPoint, new Color(206, 220,
							223));
			textColor = Color.BLACK;
			break;
		default:
			bgColor = new GradientPaint(0, startPoint,
					new Color(149, 159, 207), 0, endPoint, new Color(85, 134,
							194));
			textColor = Color.BLACK;
			break;

		}
		;
		colors.add(bgColor);
		colors.add(textColor);
		return colors;
	}

	/**
	 * Returns gradient color.
	 * 
	 * @param theme
	 *            standard theme
	 * @param startPoint
	 *            start point of the color
	 * @param endPoint
	 *            end point of the color
	 * @return list of gradient colors
	 */
	public List getGradientColor(int theme, int startPoint, int endPoint) {
		List colors = new ArrayList();
		GradientPaint bgColor = null;

		switch (theme) {
		case Theme.GRADIENT_DARKGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(136, 255, 136), 0, endPoint, new Color(1, 54, 2));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_BLUEGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(170, 240, 210), 0, endPoint,
					new Color(12, 69, 45));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_GREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(73, 252, 7),
					0, endPoint, new Color(0, 64, 0));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_OLIVEGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(185, 234, 36),
					0, endPoint, new Color(68, 116, 4));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_LIME_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(217, 242, 138), 0, endPoint, new Color(168, 216,
							24));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_LIGHTGREEN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(159, 255, 159), 0, endPoint, new Color(61, 208,
							31));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_RED_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(249, 200, 0),
					0, endPoint, new Color(242, 40, 30));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_DARKRED_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(249, 200, 0),
					0, endPoint, new Color(181, 0, 0));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_ORANGE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(255, 197, 63),
					0, endPoint, new Color(255, 102, 0));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_LIGHTORANGE_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(255, 133,
							0));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_DARKYELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(123, 120,
							0));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_GREENYELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(211, 204,
							2));
			textColor = Color.BLACK;
			break;
		case Theme.GRADIENT_GOLD_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(255, 201,
							14));
			textColor = Color.BLACK;
			break;
		case Theme.GRADIENT_YELLOW_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(255, 255,
							56));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_NAVYBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(71, 232, 252),
					0, endPoint, new Color(5, 25, 114));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_INDIGO_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(34, 85,
							146));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_BLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(71, 232, 252),
					0, endPoint, new Color(3, 37, 188));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_SKYBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(71, 232, 252),
					0, endPoint, new Color(6, 113, 196));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_LIGHTBLUE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(71, 232, 252),
					0, endPoint, new Color(17, 160, 208));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_DARKPURPLE_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(186, 0, 255),
					0, endPoint, new Color(44, 0, 89));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_VOILET_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(170, 170, 255), 0, endPoint, new Color(98, 98,
							255));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_PURPLE_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(186, 60,
							255));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_LAVENDER_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(192, 128,
							255));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_DARKPINK_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint,
					new Color(115, 0, 85));
			textColor = Color.WHITE;
			break;

		case Theme.GRADIENT_PINK_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(220, 22,
							86));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_PALEPINK_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(255, 255, 255), 0, endPoint, new Color(255, 128,
							223));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_BLACK_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(150, 150, 150), 0, endPoint, new Color(0, 0, 0));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_SILVER_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(218, 228, 231), 0, endPoint, new Color(255, 0, 0));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_BROWN_THEME:
			bgColor = new GradientPaint(0, startPoint, new Color(202, 62, 2),
					0, endPoint, new Color(118, 35, 1));
			textColor = Color.WHITE;

			break;
		case Theme.GRADIENT_LIGHTBROWN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(232, 194, 125), 0, endPoint, new Color(212, 151,
							37));
			textColor = Color.WHITE;
			break;
		case Theme.GRADIENT_PALEBROWN_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(248, 234, 203), 0, endPoint, new Color(236, 205,
							132));
			textColor = Color.BLACK;
			break;

		case Theme.GRADIENT_GRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(200, 200, 200), 0, endPoint,
					new Color(70, 70, 70));
			textColor = Color.WHITE;

			break;
		case Theme.GRADIENT_LIGHTGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(183, 183, 183), 0, endPoint, new Color(128, 128,
							128));
			textColor = Color.WHITE;

			break;
		case Theme.GRADIENT_METALLICGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(205, 210, 214), 0, 3 * endPoint / 4, new Color(
							120, 137, 145));
			textColor = Color.WHITE;

			break;
		case Theme.GRADIENT_BLUEGRAY_THEME:
			bgColor = new GradientPaint(0, startPoint,
					new Color(141, 175, 205), 0, endPoint,
					new Color(32, 53, 72));
			textColor = Color.WHITE;

			break;
		default:
			bgColor = new GradientPaint(0, startPoint,
					new Color(149, 159, 207), 0, endPoint, new Color(85, 134,
							194));
			textColor = Color.BLACK;
			break;

		}
		;
		colors.add(bgColor);
		colors.add(textColor);

		return colors;
	}

	/**
	 * Returns Glossy Theme colors.
	 * 
	 * @param theme
	 *            theme
	 * @param startPoint
	 *            start point of Gradient
	 * @param endpoint
	 *            end point of gradient
	 * @return list of glossy colors
	 */
	public List getGlossyColor(int theme, int startPoint, int endpoint) {
		List glossyColors = new ArrayList();
		switch (theme) {
		case Theme.GLOSSY_DARKGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(3,
					167, 7), 0, endpoint, new Color(2, 117, 5, 150));
			bgColor = new Color(1, 54, 2);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_BLUEGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(72,
					223, 159), 0, endpoint, new Color(41, 218, 142, 100));
			bgColor = new Color(20, 113, 74);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_LIGHTGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(219,
					255, 202), 0, endpoint, new Color(219, 255, 187, 100));
			bgColor = new Color(97, 204, 0);
			textColor = Color.BLACK;
			break;

		case Theme.GLOSSY_GREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(211,
					237, 194), 0, endpoint, new Color(109, 176, 71));
			bgColor = new Color(68, 154, 23);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_LIME_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(231,
					247, 183), 0, endpoint, new Color(192, 234, 68));
			bgColor = new Color(168, 216, 24);
			textColor = Color.BLACK;

			break;
		case Theme.GLOSSY_OLIVEGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(138,
					234, 9), 0, endpoint, new Color(128, 216, 7, 100));
			bgColor = new Color(68, 116, 4);
			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_RED_THEME:

			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					233, 232), 0, endpoint, new Color(255, 160, 160));
			bgColor = new Color(255, 0, 0);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_DARKRED_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					191, 191), 0, endpoint, new Color(255, 174, 174, 150));
			bgColor = new Color(181, 0, 0);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_ORANGE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(240,
					240, 240), 0, endpoint, new Color(246, 147, 90, 200));
			bgColor = new Color(255, 102, 0);

			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_LIGHTORANGE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(250,
					250, 250), 0, endpoint, new Color(255, 216, 176, 150));
			bgColor = new Color(255, 153, 0);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_GREENYELLOW_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(253,
					247, 15), 0, endpoint, new Color(253, 247, 15, 150));
			bgColor = new Color(211, 204, 2);
			textColor = Color.BLACK;

			break;

		case Theme.GLOSSY_DARKYELLOW_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(221,
					216, 0), 0, endpoint, new Color(187, 183, 0, 150));
			bgColor = new Color(123, 120, 0);
			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_GOLD_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					255, 255), 0, endpoint, new Color(255, 230, 108));
			bgColor = new Color(255, 213, 0);
			textColor = Color.BLACK;
			break;

		case Theme.GLOSSY_YELLOW_THEME:
			bgColor = new Color(254, 188, 16);
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(254,
					239, 192), 0, startPoint, new Color(254, 227, 147, 150));
			textColor = Color.BLACK;
			break;
		case Theme.GLOSSY_BROWN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(254,
					173, 139), 0, endpoint, new Color(253, 115, 55, 100));
			bgColor = new Color(118, 35, 1);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_LIGHTBROWN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(240,
					215, 166), 0, endpoint, new Color(226, 179, 88));
			bgColor = new Color(212, 151, 37);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_PALEBROWN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(253,
					250, 242), 0, endpoint, new Color(242, 221, 170));
			bgColor = new Color(236, 205, 132);
			textColor = Color.BLACK;
			break;

		case Theme.GLOSSY_NAVYBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(188,
					200, 252), 0, endpoint, new Color(188, 200, 252, 100));
			bgColor = new Color(5, 25, 114);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_BLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(121,
					145, 223), 0, endpoint, new Color(121, 145, 223, 150));
			bgColor = new Color(3, 37, 188);
			textColor = Color.WHITE;
			break;

		case Theme.GLOSSY_INDIGO_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(150,
					177, 211), 0, endpoint, new Color(40, 91, 149));
			bgColor = new Color(0, 59, 127);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_SKYBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(206,
					231, 255), 0, endpoint, new Color(206, 231, 255, 100));
			bgColor = new Color(0, 96, 194);

			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_LIGHTBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(167,
					227, 248), 0, endpoint, new Color(167, 227, 248, 100));
			bgColor = new Color(17, 160, 208);

			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_VOILET_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(206,
					206, 255), 0, endpoint, new Color(170, 170, 255, 100));
			bgColor = new Color(108, 108, 255);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_DARKPURPLE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(202,
					149, 255), 0, endpoint, new Color(135, 15, 255, 100));

			bgColor = new Color(44, 0, 89);

			textColor = Color.WHITE;
			break;

		case Theme.GLOSSY_PURPLE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(238,
					200, 224), 0, endpoint, new Color(222, 152, 198, 150));

			bgColor = new Color(186, 0, 255);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_LAVENDER_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(208,
					190, 233), 0, endpoint, new Color(147, 105, 203, 150));
			bgColor = new Color(107, 60, 173);

			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_DARKPINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					191, 239), 0, endpoint, new Color(255, 191, 239, 100));
			bgColor = new Color(115, 0, 85);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_PINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(251,
					215, 226), 0, endpoint, new Color(251, 215, 226, 100));
			bgColor = new Color(220, 22, 86);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_PALEPINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					236, 251), 0, endpoint, new Color(255, 236, 251, 100));
			bgColor = new Color(255, 128, 223);
			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_SILVER_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(250,
					251, 253), 0, endpoint, new Color(238, 243, 248));
			bgColor = new Color(191, 210, 228);
			textColor = Color.BLACK;
			break;
		case Theme.GLOSSY_BLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(170,
					170, 170), 0, endpoint, new Color(150, 130, 130, 130));
			bgColor = new Color(0, 0, 0);
			textColor = Color.WHITE;
			break;

		case Theme.GLOSSY_GRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(197,
					197, 197), 0, endpoint, new Color(128, 128, 128, 150));
			bgColor = new Color(91, 91, 91);
			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_LIGHTGRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(215,
					215, 215), 0, endpoint, new Color(215, 215, 215, 100));
			bgColor = new Color(159, 159, 159);
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_METALIC_GRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(200,
					205, 209), 0, endpoint, new Color(120, 137, 145, 100));
			bgColor = new Color(73, 92, 105);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_BLUEGRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(200,
					205, 209), 0, endpoint, new Color(120, 137, 145, 100));
			bgColor = new Color(32, 53, 72);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_METALIC_BLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					255, 255), 0, endpoint, new Color(85, 134, 194));
			bgColor = new Color(1, 31, 99);
			bgColor = new GradientPaint(0, startPoint, new Color(1, 31, 99), 0,
					endpoint, new Color(17, 213, 255));

			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					255, 255), 0, endpoint, new Color(85, 134, 194));

			bgColor = new GradientPaint(0, endpoint, new Color(1, 31, 99), 0,
					40, new Color(137, 255, 255));

			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_ORANGERED_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					255, 255), 0, endpoint, new Color(85, 134, 194));
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					197, 63), 0, endpoint, new Color(255, 197, 63, 100));

			bgColor = new Color(255, 0, 0);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_ORANGEBLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					197, 63), 0, endpoint, new Color(255, 0, 0, 100));
			bgColor = new Color(0, 0, 0);
			textColor = Color.WHITE;
			break;

		case Theme.GLOSSY_BLUEBLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(251,
					139, 62), 0, endpoint, new Color(255, 102, 0, 100));
			bgColor = new Color(0, 0, 0);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_GREENBLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(192,
					234, 68), 0, endpoint, new Color(168, 216, 24, 100));
			bgColor = new Color(0, 0, 0);
			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_GOLDBLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					213, 0), 0, endpoint, new Color(255, 213, 0, 100));

			bgColor = new Color(0, 0, 0);
			textColor = Color.WHITE;
			break;

		case Theme.GLOSSY_MULTIBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(71,
					232, 252), 0, endpoint, new Color(71, 232, 252, 50));
			bgColor = new Color(3, 37, 188);
			bgColor = new GradientPaint(0, endpoint, new Color(3, 37, 188), 0,
					2 * endpoint - startPoint, new Color(71, 232, 252));

			textColor = Color.WHITE;
			break;
		case Theme.GLOSSY_MULTIRED_THEME:
			System.out.println("endpoint : " + endpoint);
			System.out.println("startPoint : " + startPoint);

			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					150, 150), 0, endpoint, new Color(255, 53, 53));
			bgColor = new GradientPaint(0, endpoint, new Color(255, 0, 0), 0, 2
					* endpoint - startPoint, new Color(255, 233, 232));
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_MULTIDARKRED_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					191, 191), 0, endpoint, new Color(255, 174, 174, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(181, 0, 0), 0, 2
					* endpoint - startPoint, new Color(255, 191, 191));
			textColor = Color.WHITE;

			break;

		case Theme.GLOSSY_MULTIGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(184,
					226, 156), 0, endpoint, new Color(109, 176, 71));
			bgColor = new GradientPaint(0, endpoint, new Color(68, 154, 23), 0,
					2 * endpoint - startPoint, new Color(211, 237, 194));
			textColor = Color.WHITE;

			break;
		case Theme.GLOSSY_MULTIDARKGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(3,
					167, 7), 0, endpoint, new Color(2, 117, 5, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(1, 54, 2), 0, 2
					* endpoint - startPoint, new Color(3, 167, 7));
			break;

		case Theme.GLOSSY_MULTIBLUEGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(72,
					223, 159), 0, endpoint, new Color(41, 218, 142, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(20, 113, 74), 0,
					2 * endpoint - startPoint, new Color(72, 223, 159));
			break;
		case Theme.GLOSSY_MULTILIGHTGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(72,
					223, 159), 0, endpoint, new Color(41, 218, 142, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(20, 113, 74), 0,
					2 * endpoint - startPoint, new Color(72, 223, 159));
			break;
		case Theme.GLOSSY_MULTIOLIVEGREEN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(138,
					234, 9), 0, endpoint, new Color(128, 216, 7, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(68, 116, 4), 0,
					2 * endpoint - startPoint, new Color(138, 234, 9));
			break;
		case Theme.GLOSSY_MULTIORANGE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(250,
					194, 160), 0, endpoint, new Color(244, 147, 90, 200));
			bgColor = new GradientPaint(0, endpoint, new Color(255, 102, 0), 0,
					2 * endpoint - startPoint, new Color(240, 240, 240));
			break;
		case Theme.GLOSSY_MULTIGOLD_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					248, 223), 0, endpoint, new Color(255, 233, 166));
			bgColor = new GradientPaint(0, endpoint, new Color(255, 211, 4), 0,
					2 * endpoint - startPoint, new Color(240, 240, 240));
			break;
		case Theme.GLOSSY_MULTINAVYBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(188,
					200, 252), 0, endpoint, new Color(188, 200, 252, 130));
			bgColor = new GradientPaint(0, endpoint, new Color(5, 25, 114), 0,
					2 * endpoint - startPoint, new Color(188, 200, 252));
			break;
		case Theme.GLOSSY_MULTIINDIGO_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(150,
					177, 211), 0, endpoint, new Color(40, 91, 149));
			bgColor = new GradientPaint(0, endpoint, new Color(0, 59, 127), 0,
					2 * endpoint - startPoint, new Color(150, 177, 211));
			break;
		case Theme.GLOSSY_MULTISKYBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(206,
					231, 255), 0, endpoint, new Color(206, 231, 255, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(0, 96, 194), 0,
					2 * endpoint - startPoint, new Color(206, 231, 255));
			break;
		case Theme.GLOSSY_MULTILIGHTBLUE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(167,
					227, 248), 0, endpoint, new Color(167, 227, 248, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(17, 160, 208),
					0, 2 * endpoint - startPoint, new Color(255, 255, 255));
			break;
		case Theme.GLOSSY_MULTIDARKPURPLE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(202,
					149, 255), 0, endpoint, new Color(135, 15, 255, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(44, 0, 89), 0, 2
					* endpoint - startPoint, new Color(202, 149, 255));
			textColor = Color.WHITE;
		case Theme.GLOSSY_MULTIPURPLE_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(238,
					200, 224), 0, endpoint, new Color(222, 152, 198, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(186, 0, 255), 0,
					2 * endpoint - startPoint, new Color(238, 200, 224));
			break;
		case Theme.GLOSSY_MULTILAVENDER_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(208,
					190, 233), 0, endpoint, new Color(147, 105, 203, 200));
			bgColor = new GradientPaint(0, endpoint, new Color(107, 60, 173),
					0, 2 * endpoint - startPoint, new Color(208, 190, 233));
			break;
		case Theme.GLOSSY_MULTIVOILET_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(206,
					206, 255), 0, endpoint, new Color(170, 170, 255, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(108, 108, 255),
					0, 2 * endpoint - startPoint, new Color(206, 206, 255));
			break;
		case Theme.GLOSSY_MULTIDARKPINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					191, 239), 0, endpoint, new Color(255, 191, 239, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(115, 0, 85), 0,
					2 * endpoint - startPoint, new Color(255, 191, 239));
			break;
		case Theme.GLOSSY_MULTIPINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(251,
					215, 226), 0, endpoint, new Color(251, 215, 226, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(220, 22, 86), 0,
					2 * endpoint - startPoint, new Color(251, 215, 226));
			break;
		case Theme.GLOSSY_MULTIPALEPINK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					236, 251), 0, endpoint, new Color(255, 236, 251, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(255, 128, 223),
					0, 2 * endpoint - startPoint, new Color(255, 236, 251));
			break;
		case Theme.GLOSSY_MULTIBROWN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(254,
					173, 139), 0, endpoint, new Color(253, 115, 55, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(118, 35, 1), 0,
					2 * endpoint - startPoint, new Color(254, 173, 139));
			break;
		case Theme.GLOSSY_MULTILIGHTBROWN_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(240,
					215, 166), 0, endpoint, new Color(226, 179, 88));
			bgColor = new GradientPaint(0, endpoint, new Color(212, 151, 37),
					0, 2 * endpoint - startPoint, new Color(240, 215, 166));
			break;
		case Theme.GLOSSY_MULTIBLUEGRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(200,
					205, 209), 0, endpoint, new Color(120, 137, 145, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(32, 53, 72), 0,
					2 * endpoint - startPoint, new Color(200, 205, 209));
			break;
		case Theme.GLOSSY_MULTIGRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(197,
					197, 197), 0, endpoint, new Color(128, 128, 128, 150));
			bgColor = new GradientPaint(0, endpoint, new Color(91, 91, 91), 0,
					2 * endpoint - startPoint, new Color(197, 197, 197));
			break;
		case Theme.GLOSSY_MULTILIGHTGRAY_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(215,
					215, 215), 0, endpoint, new Color(215, 215, 215, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(159, 159, 159),
					0, 2 * endpoint - startPoint, new Color(215, 215, 215));
			break;
		case Theme.GLOSSY_MULTIBLACK_THEME:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(130,
					130, 130), 0, endpoint, new Color(100, 100, 100, 100));
			bgColor = new GradientPaint(0, endpoint, new Color(0, 0, 0), 0, 2
					* endpoint - startPoint, new Color(170, 170, 170));
			break;
		case Theme.GLOSSY_MULTIBLUECOLOR_THEME:
			// Blue
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(34,
					144, 255), 0, endpoint, new Color(0, 101, 202));
			bgColor = new GradientPaint(0, endpoint, new Color(0, 82, 164), 0,
					2 * endpoint - startPoint, new Color(206, 231, 255));

			textColor = Color.WHITE;
			break;

		default:
			glossyTopBtnColor = new GradientPaint(0, startPoint, new Color(255,
					255, 255), 0, endpoint, new Color(85, 134, 194));
			bgColor = new GradientPaint(0, 0, new Color(1, 31, 99), 0, 2
					* endpoint - startPoint, new Color(137, 255, 255));

			textColor = Color.WHITE;
			break;
		}
		;
		glossyColors.add(glossyTopBtnColor);
		glossyColors.add(bgColor);
		glossyColors.add(textColor);
		return glossyColors;
	}
}
