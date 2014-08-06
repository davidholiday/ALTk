package com.jd.swing.demo.button;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import com.jd.swing.custom.component.button.ButtonType;
import com.jd.swing.custom.component.button.StandardButton;
import com.jd.swing.util.Theme;

public class StandardButtonDemo {

	/**
	 * 
	 * @param rollOverTheme
	 * @param height
	 */

	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Buttons Demo");
		frame.setLayout(new FlowLayout());
		StandardButton rectBtn = new StandardButton("Standard Button",Theme.STANDARD_RED_THEME,ButtonType.BUTTON_RECTANGULAR);
		rectBtn.setPreferredSize(new Dimension(130, 35));
		StandardButton rrectBtn = new StandardButton("Standard Button",Theme.STANDARD_GREEN_THEME,ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		rrectBtn.setPreferredSize(new Dimension(130, 35));
		StandardButton roundBtn = new StandardButton("Standard Button",Theme.STANDARD_ORANGE_THEME,ButtonType.BUTTON_ROUNDED);
		roundBtn.setPreferredSize(new Dimension(130, 35));
		StandardButton circularBtn = new StandardButton("<HTML>Standard<br> Button<html>",Theme.STANDARD_SKYBLUE_THEME,ButtonType.BUTTON_CIRCULAR);
		circularBtn.setPreferredSize(new Dimension(100, 100));
		
		//getButtonsPanel(frame);
		frame.add(rectBtn);
		frame.add(rrectBtn);
		frame.add(roundBtn);
		frame.add(circularBtn);

		frame.setSize(700, 85);
		frame.setVisible(true);
	}

	public static JFrame getButtonsPanel(JFrame frame) {
		JFrame panel = frame;
		StandardButton brown_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_BROWN_THEME,
				ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		brown_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton lightBrown_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_LIGHTBROWN_THEME,
				ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		lightBrown_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton palebrown_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_PALEBROWN_THEME);
		palebrown_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton silverButton = new StandardButton("Standard Button",
				Theme.STANDARD_SILVER_THEME);
		silverButton.setPreferredSize(new Dimension(130, 35));

		StandardButton redBTN = new StandardButton("Standard Button",
				Theme.STANDARD_RED_THEME);
		redBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton darkRedBtn = new StandardButton("Standard Button",
				Theme.STANDARD_DARKRED_THEME);
		darkRedBtn.setPreferredSize(new Dimension(130, 35));

		StandardButton orangeBTN = new StandardButton("Standard Button",
				Theme.STANDARD_ORANGE_THEME);
		orangeBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton lightOrangeBTN = new StandardButton("Standard Button",
				Theme.STANDARD_LIGHTORANGE_THEME);
		lightOrangeBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton darkgreen_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_DARKGREEN_THEME);
		darkgreen_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton greenBTN = new StandardButton("Standard Button",
				Theme.STANDARD_GREEN_THEME);
		greenBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton lightGreen_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_LIGHTGREEN_THEME);
		lightGreen_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton oliveGreen_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_OLIVEGREEN_THEME);
		oliveGreen_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton limeBTN = new StandardButton("Standard Button",
				Theme.STANDARD_LIME_THEME);
		limeBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton blueGreenBTN = new StandardButton("Standard Button",
				Theme.STANDARD_BLUEGREEN_THEME);
		blueGreenBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton pink = new StandardButton("Standard Button",
				Theme.STANDARD_PINK_THEME);
		pink.setPreferredSize(new Dimension(130, 35));

		StandardButton darkYellow_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_DARKYELLOW_THEME);
		darkYellow_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton greenYellow_BTN = new StandardButton("Standard Button",
				Theme.STANDARD_GREENYELLOW_THEME);
		greenYellow_BTN.setPreferredSize(new Dimension(130, 35));

		StandardButton gold = new StandardButton("Standard Button",
				Theme.STANDARD_GOLD_THEME);
		gold.setPreferredSize(new Dimension(130, 35));

		StandardButton yellowBtn = new StandardButton("Standard Button",
				Theme.STANDARD_YELLOW_THEME);
		yellowBtn.setPreferredSize(new Dimension(130, 35));

		// //StandardButton silver = new StandardButton("Standard Button",
		// ThemeType.STANDARD_SILVER_THEME);
		// silver.setPreferredSize(new Dimension(130, 35));

		StandardButton navyBlueBTN = new StandardButton("Standard Button",
				Theme.STANDARD_NAVYBLUE_THEME);
		navyBlueBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton indigoBTN = new StandardButton("Standard Button",
				Theme.STANDARD_INDIGO_THEME);
		indigoBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton blue = new StandardButton("Standard Button",
				Theme.STANDARD_BLUE_THEME);
		blue.setPreferredSize(new Dimension(130, 35));

		StandardButton skyblueBTN = new StandardButton("Standard Button",
				Theme.STANDARD_SKYBLUE_THEME);
		skyblueBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton lightBlueBTN = new StandardButton("Standard Button",
				Theme.STANDARD_LIGHTBLUE_THEME);
		lightBlueBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton darkpurpleBTN = new StandardButton("Standard Button",
				Theme.STANDARD_DARKPURPLE_THEME);
		darkpurpleBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton purpleBTN = new StandardButton("Standard Button",
				Theme.STANDARD_PURPLE_THEME);
		purpleBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton lavendarBTN = new StandardButton("Standard Button",
				Theme.STANDARD_LAVENDER_THEME);
		lavendarBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton darkPinkBTN = new StandardButton("Standard Button",
				Theme.STANDARD_DARKPINK_THEME);
		darkPinkBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton pinkBTN = new StandardButton("Standard Button",
				Theme.STANDARD_PINK_THEME);
		pinkBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton palePinkBTN = new StandardButton("Standard Button",
				Theme.STANDARD_PALEPINK_THEME);
		palePinkBTN.setPreferredSize(new Dimension(130, 35));

		StandardButton black = new StandardButton("Standard Button",
				Theme.STANDARD_BLACK_THEME);
		black.setPreferredSize(new Dimension(130, 35));

		StandardButton gray = new StandardButton("Standard Button",
				Theme.STANDARD_GRAY_THEME);
		gray.setPreferredSize(new Dimension(130, 35));

		StandardButton lightgray = new StandardButton("Standard Button",
				Theme.STANDARD_LIGHTGRAY_THEME);
		lightgray.setPreferredSize(new Dimension(130, 35));

		StandardButton metallicgray = new StandardButton("Standard Button",
				Theme.STANDARD_METALLICGRAY_THEME);
		metallicgray.setPreferredSize(new Dimension(130, 35));

		StandardButton bluegray = new StandardButton("Standard Button",
				Theme.STANDARD_BLUEGRAY_THEME);
		bluegray.setPreferredSize(new Dimension(130, 35));

		StandardButton voilet = new StandardButton("Standard Button",
				Theme.STANDARD_VOILET_THEME);
		voilet.setPreferredSize(new Dimension(130, 35));

		panel.add(brown_BTN);
		panel.add(lightBrown_BTN);
		panel.add(palebrown_BTN);

		panel.add(redBTN);
		panel.add(darkRedBtn);

		panel.add(orangeBTN);
		panel.add(lightOrangeBTN);
		panel.add(darkYellow_BTN);
		panel.add(greenYellow_BTN);
		panel.add(gold);
		panel.add(yellowBtn);

		panel.add(darkgreen_BTN);
		panel.add(blueGreenBTN);
		panel.add(greenBTN);
		panel.add(lightGreen_BTN);
		panel.add(oliveGreen_BTN);
		panel.add(limeBTN);

		panel.add(navyBlueBTN);
		panel.add(indigoBTN);
		panel.add(blue);
		panel.add(skyblueBTN);
		panel.add(lightBlueBTN);

		panel.add(darkpurpleBTN);
		panel.add(purpleBTN);
		panel.add(lavendarBTN);

		panel.add(darkPinkBTN);
		panel.add(pinkBTN);
		panel.add(palePinkBTN);
		panel.add(voilet);

		panel.add(black);
		panel.add(gray);
		panel.add(lightgray);
		panel.add(metallicgray);
		panel.add(bluegray);
		panel.add(silverButton);

		// panel.add(silver);
		return panel;

	}
}
