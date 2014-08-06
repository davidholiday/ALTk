package com.jd.swing.demo.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jd.swing.custom.component.button.ButtonType;
import com.jd.swing.custom.component.button.GradientButton;
import com.jd.swing.custom.component.button.StandardButton;
import com.jd.swing.util.Theme;

public class GradientButtonDemo {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Buttons Demo");
		frame.setLayout(new FlowLayout());
		JScrollPane pane = new JScrollPane();
		//pane.setViewportView(getButtonsPanel());
		GradientButton rectBtn = new GradientButton("Gradient Button");
		rectBtn.setPreferredSize(new Dimension(130, 35));
		GradientButton rrectBtn = new GradientButton("Gradient Button",Theme.GRADIENT_RED_THEME,ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		rrectBtn.setPreferredSize(new Dimension(130, 35));
		GradientButton roundBtn = new GradientButton("Gradient Button",Theme.GRADIENT_BLUE_THEME,ButtonType.BUTTON_ROUNDED);
		roundBtn.setPreferredSize(new Dimension(130, 35));
		GradientButton circularBtn = new GradientButton("<HTML>Gradient<br> Button<html>",Theme.GRADIENT_DARKPURPLE_THEME,ButtonType.BUTTON_CIRCULAR);
		circularBtn.setPreferredSize(new Dimension(100, 100));
	
		frame.add(rectBtn);
		frame.add(rrectBtn);
		frame.add(roundBtn);
		frame.add(circularBtn);

		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(700, 85);
		frame.setVisible(true);
	}

	public static JPanel getButtonsPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(100, 500));
		// GREEN THEME
		GradientButton darkGreen_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_DARKGREEN_THEME,
				ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		darkGreen_Button.setPreferredSize(new Dimension(130, 35));
		GradientButton blueGreen_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_BLUEGREEN_THEME);
		blueGreen_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton green_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_GREEN_THEME);
		green_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton lightGreen_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_LIGHTGREEN_THEME);
		lightGreen_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton oliveGreen_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_OLIVEGREEN_THEME);
		oliveGreen_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton lime_Button = new GradientButton("Gradient Button",

		Theme.GRADIENT_LIME_THEME);
		lime_Button.setPreferredSize(new Dimension(130, 35));

		// BROWN THEME
		GradientButton brown_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_BROWN_THEME);
		brown_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton lightbrown_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_LIGHTBROWN_THEME);
		lightbrown_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton palebrown_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_PALEBROWN_THEME);
		palebrown_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton blueGray_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_BLUEGRAY_THEME);
		blueGray_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton gradientRed_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_RED_THEME);
		gradientRed_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton gradientDarkRed_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_DARKRED_THEME);
		gradientDarkRed_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton orange_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_ORANGE_THEME);
		orange_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton lightOrange_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_LIGHTORANGE_THEME);
		lightOrange_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton darkYellow_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_DARKYELLOW_THEME);
		darkYellow_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton gold = new GradientButton("Gradient Button",
				Theme.GRADIENT_GOLD_THEME);
		gold.setPreferredSize(new Dimension(130, 35));

		GradientButton yellow_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_YELLOW_THEME);
		yellow_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton greenYellow_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_GREENYELLOW_THEME);
		greenYellow_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton darkpurple_Button = new GradientButton(
				"Gradient Button", Theme.GRADIENT_DARKPURPLE_THEME);
		darkpurple_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton purple_Button = new GradientButton("Disable Button",
				Theme.GRADIENT_PURPLE_THEME);
		purple_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton lavender = new GradientButton("Disable Button",
				Theme.GRADIENT_LAVENDER_THEME);
		lavender.setPreferredSize(new Dimension(130, 35));

		GradientButton navyBlue_Btn = new GradientButton("Pressed Button",
				Theme.GRADIENT_NAVYBLUE_THEME);
		navyBlue_Btn.setPreferredSize(new Dimension(130, 35));

		GradientButton indigo = new GradientButton("Pressed Button",
				Theme.GRADIENT_INDIGO_THEME);
		indigo.setPreferredSize(new Dimension(130, 35));

		GradientButton blue = new GradientButton("Gradient Button",
				Theme.GRADIENT_BLUE_THEME);
		blue.setPreferredSize(new Dimension(130, 35));

		GradientButton skyblue_Btn = new GradientButton("Gradient Button",
				Theme.GRADIENT_SKYBLUE_THEME);
		skyblue_Btn.setPreferredSize(new Dimension(130, 35));

		GradientButton lightBlue_Btn = new GradientButton("Gradient Button",
				Theme.GRADIENT_LIGHTBLUE_THEME);
		lightBlue_Btn.setPreferredSize(new Dimension(130, 35));

		GradientButton darkpink_Button = new GradientButton("Gradient Button",
				Theme.GRADIENT_DARKPINK_THEME);
		darkpink_Button.setPreferredSize(new Dimension(130, 35));

		GradientButton pink = new GradientButton("Gradient Button",
				Theme.GRADIENT_PINK_THEME);
		pink.setPreferredSize(new Dimension(130, 35));

		GradientButton palepink = new GradientButton("Gradient Button",
				Theme.GRADIENT_PALEPINK_THEME);
		palepink.setPreferredSize(new Dimension(130, 35));

		GradientButton black = new GradientButton("RollOver Button",
				Theme.GRADIENT_BLACK_THEME);
		black.setPreferredSize(new Dimension(130, 35));

		GradientButton gray = new GradientButton("Disable Button",
				Theme.GRADIENT_GRAY_THEME);
		gray.setPreferredSize(new Dimension(130, 35));

		GradientButton lightgray = new GradientButton("Disable Button",
				Theme.GRADIENT_LIGHTGRAY_THEME);
		lightgray.setPreferredSize(new Dimension(130, 35));

		GradientButton metallicgray = new GradientButton("Disable Button",
				Theme.GRADIENT_METALLICGRAY_THEME);
		metallicgray.setPreferredSize(new Dimension(130, 35));

		GradientButton silver = new GradientButton("Disable Button",
				Theme.GRADIENT_SILVER_THEME);
		silver.setPreferredSize(new Dimension(130, 35));

		GradientButton voilet = new GradientButton("Disable Button",
				Theme.GRADIENT_VOILET_THEME);
		voilet.setPreferredSize(new Dimension(130, 35));

		panel.add(darkGreen_Button);
		panel.add(blueGreen_Button);
		panel.add(green_Button);
		panel.add(lightGreen_Button);
		panel.add(oliveGreen_Button);
		panel.add(lime_Button);

		panel.add(gradientRed_Button);
		panel.add(gradientDarkRed_Button);

		panel.add(brown_Button);
		panel.add(lightbrown_Button);
		panel.add(palebrown_Button);

		panel.add(orange_Button);
		panel.add(lightOrange_Button);
		panel.add(darkYellow_Button);
		panel.add(greenYellow_Button);
		panel.add(gold);
		panel.add(yellow_Button);

		panel.add(navyBlue_Btn);
		panel.add(indigo);
		panel.add(blue);
		panel.add(skyblue_Btn);
		panel.add(lightBlue_Btn);

		// panel.add(brown_Button);

		panel.add(darkpurple_Button);
		panel.add(purple_Button);
		panel.add(lavender);
		panel.add(voilet);

		panel.add(darkpink_Button);
		panel.add(pink);
		panel.add(palepink);

		// panel.add(lavender);
		panel.add(black);
		panel.add(gray);
		panel.add(lightgray);
		panel.add(metallicgray);
		panel.add(blueGray_Button);
		panel.add(silver);

		return panel;

	}
}
