/**
 * Copyright (c) 2012, Dhilshuk Reddy All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute SwingJD software is freely
 * granted, provided that this notice is preserved.
 */
package com.jd.swing.custom.component.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

import com.jd.swing.util.ColorStyles;
import com.jd.swing.util.Theme;

/**
 * 
 * @author Dhilshuk Reddy
 * 
 */
public class CircularGlossyButton extends JButton {

	private int theme;
	private int selectedTheme;

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            label on the button
	 */
	public CircularGlossyButton(String text) {
		super();
		setText(text);
		init();
	}

	/**
	 * Sets the text and theme of the button.
	 * 
	 * @param text
	 *            label on the button
	 * @param theme
	 *            theme of the button
	 */
	public CircularGlossyButton(String text, int theme) {
		super(text);
		this.theme = theme;
		this.selectedTheme = theme;
		init();
	}

	/**
	 * Intializes
	 */
	private void init() {
		setFont(new Font("Thoma", Font.BOLD, 12));
		setFocusable(false);
		setContentAreaFilled(false);
		setBorderPainted(false);

	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight();
		int w = getWidth();
		int height = getHeight();

		Color textColor = null;

		ButtonModel model = getModel();
		GradientPaint topGradient = null;
		GradientPaint bottomGradient = null;
		Color outerColor = new Color(0, 0, 0);
		if (model.isSelected() || model.isPressed()) {
			if (theme == selectedTheme) {
				switch (this.theme) {
				case ColorStyles.SILVER_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(150, 177,
							211), 0, h / 2, new Color(40, 91, 149));
					bottomGradient = new GradientPaint(0, h / 2, new Color(0,
							59, 127), 0, h, new Color(34, 85, 146));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.BLACK_METAL_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(253, 235,
							213), 0, h / 2, new Color(253, 226, 196));
					bottomGradient = new GradientPaint(0, h / 2, new Color(255,
							206, 105), 0, h, new Color(255, 243, 148));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.GREEN_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(253, 235,
							213), 0, h / 2, new Color(253, 226, 196));
					bottomGradient = new GradientPaint(0, h / 2, new Color(255,
							206, 105), 0, h, new Color(255, 243, 148));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.BLUE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(250, 251,
							253), 0, h / 2, new Color(238, 243, 248));
					bottomGradient = new GradientPaint(0, h / 2, new Color(209,
							223, 237), 0, h, new Color(191, 210, 228));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.LIGHT_BLUE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(250, 251,
							253), 0, h / 2, new Color(238, 243, 248));
					bottomGradient = new GradientPaint(0, h / 2, new Color(209,
							223, 237), 0, h, new Color(191, 210, 228));
					setForeground(Color.BLACK);
					break;

				default:
					topGradient = new GradientPaint(0, 0, new Color(253, 235,
							213), 0, h / 2, new Color(253, 226, 196));
					bottomGradient = new GradientPaint(0, h / 2, new Color(255,
							206, 105), 0, h, new Color(255, 243, 148));
					break;
				}
				;
			} else {
				switch (this.selectedTheme) {
				case ColorStyles.SILVER_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(250, 251,
							253), 0, h / 2, new Color(238, 243, 248));
					bottomGradient = new GradientPaint(0, h / 2, new Color(209,
							223, 237), 0, h, new Color(191, 210, 228));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.BLACK_METAL_STYLE:
					topGradient = new GradientPaint(0, 0,
							new Color(93, 93, 93), 0, h / 2, new Color(53, 53,
									53));
					bottomGradient = new GradientPaint(0, h / 2, new Color(1,
							6, 10), 0, h, new Color(50, 50, 50));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.BLUE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(150, 177,
							211), 0, h / 2, new Color(40, 91, 149));
					bottomGradient = new GradientPaint(0, h / 2, new Color(0,
							59, 127), 0, h, new Color(34, 85, 146));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.GREEN_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(211, 237,
							194), 0, h / 2, new Color(109, 176, 71));
					bottomGradient = new GradientPaint(0, h / 2, new Color(68,
							154, 23), 0, h, new Color(122, 212, 65));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.ORANGE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(253, 235,
							213), 0, h / 2, new Color(253, 226, 196));
					bottomGradient = new GradientPaint(0, h / 2, new Color(255,
							206, 105), 0, h, new Color(255, 243, 148));
					setForeground(Color.BLACK);
					break;
				case ColorStyles.LIGHT_BLUE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(107, 178,
							232), 0, h / 2, new Color(72, 155, 218));
					bottomGradient = new GradientPaint(0, h / 2, new Color(40,
							125, 202), 0, h, new Color(9, 101, 187));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.DARK_GRAY_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(159, 159,
							159), 0, h / 2, new Color(91, 91, 91));
					bottomGradient = new GradientPaint(0, h / 2, new Color(47,
							47, 47), 0, h, new Color(41, 41, 41));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.RED_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(247, 166,
							166), 0, h / 2, new Color(242, 107, 107));
					bottomGradient = new GradientPaint(0, h / 2, new Color(233,
							0, 0), 0, h, new Color(233, 0, 0));
					setForeground(Color.WHITE);
					break;
				case ColorStyles.METALIC_BLUE_STYLE:
					topGradient = new GradientPaint(0, 0, new Color(255, 255,
							255), 0, h / 2, new Color(85, 134, 194));
					bottomGradient = new GradientPaint(0, h / 2, new Color(1,
							31, 99), 0, h, new Color(137, 255, 255));
					setForeground(Color.WHITE);

				default:
					topGradient = new GradientPaint(0, 0, new Color(149, 159,
							207), 0, h / 2, new Color(85, 134, 194));
					bottomGradient = new GradientPaint(0, h / 2, new Color(1,
							31, 99), 0, h, new Color(17, 213, 255));
					setForeground(Color.WHITE);
					break;
				}
				;
			}
		} else {
			switch (this.theme) {
			case Theme.SILVER_THEME:
				topGradient = new GradientPaint(0, 0, new Color(250, 251, 253),
						0, height / 2, new Color(238, 243, 248, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						209, 223, 237), 0, height, new Color(191, 210, 228));
				setForeground(Color.BLACK);
				textColor = Color.BLACK;
				outerColor = new Color(0, 0, 0);
				break;
			case Theme.GLOSSY_BLACK_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(100, 100, 100, 0));
				bottomGradient = new GradientPaint(0, 0, new Color(1, 6, 10),
						0, height, new Color(1, 6, 10));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				outerColor = new Color(0, 0, 0);
				break;
			case Theme.GLOSSY_BLUE_THEME:
				topGradient = new GradientPaint(1, 1, new Color(255, 255, 255),
						0, height / 2, new Color(40, 91, 149, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(0,
						59, 127), 0, height, new Color(34, 85, 146));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;

			case Theme.GLOSSY_ORANGE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(253, 235, 213),
						0, height / 2, new Color(253, 226, 196, 20));
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(246, 147, 90, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						255, 206, 105), 0, height, new Color(255, 243, 148));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						255, 102, 0), 0, height, new Color(255, 102, 0));

				setForeground(Color.WHITE);
				textColor = Color.BLACK;

				break;
			case Theme.GLOSSY_LIGHTORANGE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(255, 216, 176, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						255, 153, 0), 0, height, new Color(255, 153, 0));
				outerColor = new Color(145, 72, 0);
				setForeground(Color.WHITE);
				textColor = Color.BLACK;

				break;

			case Theme.GLOSSY_LIGHTGRAY_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(121, 139, 149, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						128, 128, 128), 0, height, new Color(128, 128, 128));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;
			case Theme.GLOSSY_METALIC_GRAY_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(120, 137, 145, 20));
				bottomGradient = new GradientPaint(0, 0,
						new Color(73, 92, 105), 0, height, new Color(73, 92,
								105));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;
			case Theme.GLOSSY_PINK_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(242, 134, 169, 0));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						220, 22, 86), 0, height, new Color(220, 22, 86));
				outerColor = new Color(166, 17, 66);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;

			case Theme.GLOSSY_SKYBLUE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(166, 210, 255),
						0, height / 2, new Color(166, 210, 255, 0));
				bottomGradient = new GradientPaint(0, height / 2, new Color(0,
						96, 194), 0, height, new Color(0, 193, 226));
				bottomGradient = new GradientPaint(0, height / 2, new Color(0,
						96, 194), 0, height, new Color(0, 96, 194));
				outerColor = new Color(0, 16, 119);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;
			case Theme.GLOSSY_GRAY_THEME:
				topGradient = new GradientPaint(0, 0, new Color(159, 159, 159),
						0, height / 2, new Color(91, 91, 91, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(47,
						47, 47), 0, height, new Color(41, 41, 41));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_RED_THEME:
				topGradient = new GradientPaint(0, 0, new Color(247, 166, 166),
						0, height / 2, new Color(242, 107, 107));
				topGradient = new GradientPaint(0, 0, new Color(255, 233, 232),
						0, height / 2, new Color(255, 160, 160, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						233, 0, 0), 0, height, new Color(233, 0, 0));
				bottomGradient = new GradientPaint(0, 0, new Color(255, 0, 0),
						0, height, new Color(255, 0, 0));
				outerColor = new Color(124, 14, 7);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_METALIC_BLUE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(85, 134, 194, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(1,
						31, 99), 0, height, new Color(137, 255, 255));
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_DARKGREEN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(2, 108, 5, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(1, 54, 2),
						0, height / 2, new Color(1, 54, 2));

				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_BLUEGREEN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(41, 218, 142, 0));
				bottomGradient = new GradientPaint(0, height / 2, new Color(20,
						113, 74), 0, height, new Color(20, 113, 74));
				outerColor = new Color(15, 85, 55);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_GREEN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(109, 176, 71, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(68,
						154, 23), 0, height, new Color(68, 154, 23));
				outerColor = new Color(23, 72, 19);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;

				break;
			case Theme.GLOSSY_LIGHTGREEN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(204, 254, 141, 20));
				bottomGradient = new GradientPaint(0, 0,
						new Color(117, 202, 2), 0, height / 2, new Color(117,
								202, 2));

				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_LIME_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(255, 255, 255, 40));
				bottomGradient = new GradientPaint(0, 0,
						new Color(119, 245, 0), 0, height, new Color(191, 245,
								0));

				setForeground(Color.BLACK);
				break;
			case Theme.GLOSSY_DARKYELLOW_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(166, 162, 4, 20));
				setForeground(Color.WHITE);
				bottomGradient = new GradientPaint(0, 0,
						new Color(123, 120, 3), 0, height / 2, new Color(123,
								120, 3));
				outerColor = new Color(70, 68, 2);
				break;
			case Theme.GLOSSY_GREENYELLOW_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(238, 232, 2, 20));
				setForeground(Color.WHITE);
				bottomGradient = new GradientPaint(0, 0,
						new Color(211, 204, 2), 0, height / 2, new Color(211,
								204, 2));
				outerColor = new Color(70, 68, 2);
				break;
			case Theme.GLOSSY_YELLOW_THEME:
				bottomGradient = new GradientPaint(0, 0,
						new Color(255, 255, 0), 0, height / 2, new Color(255,
								255, 56));
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height, new Color(254, 227, 147, 20));
				setForeground(Color.BLACK);
				break;

			case Theme.GLOSSY_PURPLE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(193, 32, 255, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						186, 0, 255), 0, height, new Color(186, 0, 255));

				outerColor = new Color(105, 33, 80);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_DARKPURPLE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(65, 0, 130, 0));
				bottomGradient = new GradientPaint(0, 0, new Color(44, 0, 89),
						0, height, new Color(44, 0, 89));

				outerColor = new Color(17, 0, 34);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_LAVENDER_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(211, 168, 255, 0));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						192, 128, 255), 0, height, new Color(192, 128, 255));

				outerColor = new Color(105, 33, 80);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_DARKPINK_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(155, 0, 117, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						115, 0, 85), 0, height, new Color(115, 0, 85));

				outerColor = new Color(105, 33, 80);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_PALEPINK_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(255, 159, 232, 20));
				bottomGradient = new GradientPaint(0, height / 2, new Color(
						255, 128, 223), 0, height, new Color(255, 128, 223));

				outerColor = new Color(255, 51, 204);
				setForeground(Color.WHITE);
				textColor = Color.WHITE;
				break;
			case Theme.GLOSSY_LIGHTBLUE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(177, 225, 248),
						0, height / 2, new Color(105, 208, 243, 20));
				bottomGradient = new GradientPaint(0, 0,
						new Color(17, 160, 208), 0, height / 2, new Color(17,
								160, 208));

				setForeground(Color.WHITE);
				break;

			case Theme.GLOSSY_DARKRED_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(210, 0, 0, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(181, 0, 0),
						0, height / 2, new Color(181, 0, 0));
				outerColor = new Color(79, 0, 0);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_NAVYBLUE_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(6, 31, 49, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(5, 25, 114),
						0, height / 2, new Color(5, 25, 114));
				// outerColor=new Color(79,0,0);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_INDIGO_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(4, 43, 225, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(3, 37, 188),
						0, height / 2, new Color(3, 37, 188));
				// outerColor=new Color(79,0,0);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_GOLD_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(255, 217, 36, 20));
				bottomGradient = new GradientPaint(0, 0,
						new Color(255, 213, 0), 0, height / 2, new Color(255,
								213, 0));
				// outerColor=new Color(79,0,0);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_BROWN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(174, 104, 0, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(128, 77, 0),
						0, height / 2, new Color(128, 77, 0));
				outerColor = new Color(89, 53, 0);
				setForeground(Color.WHITE);
				break;
			case Theme.GLOSSY_LIGHTBROWN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(223, 169, 68, 20));
				bottomGradient = new GradientPaint(0, 0,
						new Color(212, 151, 37), 0, height / 2, new Color(212,
								151, 37));
				outerColor = new Color(188, 134, 33);
				setForeground(Color.WHITE);
				break;

			case Theme.GLOSSY_PALEBROWN_THEME:
				topGradient = new GradientPaint(0, 0, new Color(255, 255, 255),
						0, height / 2, new Color(240, 216, 149, 20));
				bottomGradient = new GradientPaint(0, 0, new Color(236, 205,
						132), 0, height / 2, new Color(236, 205, 132));
				outerColor = new Color(89, 53, 0);
				setForeground(Color.WHITE);
				break;

			default:
				topGradient = new GradientPaint(0, 0, new Color(149, 159, 207),
						0, h / 2, new Color(85, 134, 194));
				bottomGradient = new GradientPaint(0, h / 2, new Color(1, 31,
						99), 0, h, new Color(17, 213, 255));
				setForeground(Color.WHITE);
				break;
			}
			;
		}

		int size = Math.min(getWidth(), getHeight() - 2);
		 //Shape shape = new Arc2D.Double(1.0, 4.0, (double) (size - 2 * 2) + 2,
		//(double) (size - 2 * 2) + 2, 180.0, 180.0, Arc2D.CHORD);
		//paintBorderShadow(g2d, 8, shape);
		g2d.setPaint(bottomGradient);
		g2d.fillOval(2, 2, (size - 2 * 2), (size - 2 * 2));
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(255, 255, 255, 100));
		// g2d.drawOval(3, 3,size-2 * 2, size-2 * 2);
		g2d.setPaint(outerColor);
		g2d.drawOval(2, 2, (size - 2 * 2), (size - 2 * 2));
		g2d.setPaint(topGradient);
		g2d.fillOval(getWidth() / 4, 6, getWidth() / 2, getHeight() / 3);
		RadialGradientPaint p = new RadialGradientPaint(new Point2D.Double(
				getWidth() / 2.0, getHeight() * 1.5), getWidth() / 2.3f,
				new Point2D.Double(getWidth() / 2.0, getHeight() * 1.75 + 6),
				new float[] { 0.0f, 0.8f },
				new Color[] { new Color(255, 233, 232),
						new Color(255, 233, 232, 0) },
				RadialGradientPaint.CycleMethod.NO_CYCLE,
				RadialGradientPaint.ColorSpaceType.SRGB, AffineTransform
						.getScaleInstance(1.0, 0.5));

		g2d.setPaint(p);

		g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);

		super.paintComponent(g);
	}

	/**
	 * 
	 * @param g2
	 * @param shadowWidth
	 * @param clipShape
	 */
	private void paintBorderShadow(Graphics2D g2, int shadowWidth,
			Shape clipShape) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int sw = shadowWidth * 2;
		for (int i = sw; i >= 2; i -= 2) {
			float pct = (float) (sw - i) / (sw - 1);
			g2.setColor(getMixedColor(Color.LIGHT_GRAY, pct, new Color(255,
					255, 255, 100), 1.0f - pct));
			g2.setStroke(new BasicStroke(5));
			g2.fill(clipShape);
		}
	}

	/**
	 * 
	 * @param c1
	 * @param pct1
	 * @param c2
	 * @param pct2
	 * @return
	 */
	private static Color getMixedColor(Color c1, float pct1, Color c2,
			float pct2) {
		float[] clr1 = c1.getComponents(null);
		float[] clr2 = c2.getComponents(null);
		for (int i = 0; i < clr1.length; i++) {
			clr1[i] = (clr1[i] * pct1) + (clr2[i] * pct2);
		}
		return new Color(clr1[0], clr1[1], clr1[2], clr1[3]);
	}

	/**
	 * Returns the button theme.
	 * 
	 * @return theme
	 */
	public int getTheme() {
		return theme;
	}

	/**
	 * Sets the button theme.
	 * 
	 * @param theme
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * Returns the selected theme.
	 * 
	 * @return selectedTheme
	 */
	public int getSelectedTheme() {
		return selectedTheme;
	}

	/**
	 * Set the selected theme.
	 * 
	 * @param selectedTheme
	 */
	public void setSelectedTheme(int selectedTheme) {
		this.selectedTheme = selectedTheme;
	}

}
