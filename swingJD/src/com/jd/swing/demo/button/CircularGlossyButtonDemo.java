package com.jd.swing.demo.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import com.jd.swing.custom.component.button.CircularGlossyButton;
import com.jd.swing.util.Theme;

public class CircularGlossyButtonDemo {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Buttons Demo");
		frame.setLayout(new FlowLayout());
		CircularGlossyButton standardButton = new CircularGlossyButton(
				"Standard Button");
		getButtonsPanel(frame);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(700, 85);
		frame.setVisible(true);
	}

	public static JFrame getButtonsPanel(JFrame frame) {
		JFrame panel = frame;
		panel.setBackground(Color.WHITE);

		CircularGlossyButton lightGreen = new CircularGlossyButton("Button",
				Theme.GLOSSY_LIGHTGREEN_THEME);
		lightGreen.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton rollOverButton = new CircularGlossyButton(
				"Button", Theme.GLOSSY_RED_THEME);
		rollOverButton.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton green = new CircularGlossyButton("Button",
				Theme.GLOSSY_GREEN_THEME);
		green.setPreferredSize(new Dimension(75, 75));
		green.setEnabled(true);

		CircularGlossyButton lightOrange = new CircularGlossyButton("Button",
				Theme.GLOSSY_LIGHTORANGE_THEME);
		lightOrange.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton voilet = new CircularGlossyButton("Button",
				Theme.GLOSSY_PURPLE_THEME);
		voilet.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton pink = new CircularGlossyButton("Button",
				Theme.GLOSSY_PINK_THEME);
		pink.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton lightBlue = new CircularGlossyButton("Button",
				Theme.GLOSSY_LIGHTBLUE_THEME);
		lightBlue.setPreferredSize(new Dimension(75, 75));
		lightBlue.setEnabled(true);

		CircularGlossyButton black = new CircularGlossyButton("Button",
				Theme.GLOSSY_BLACK_THEME);
		black.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton blueButton = new CircularGlossyButton("Button",
				Theme.GLOSSY_BLUE_THEME);
		blueButton.setPreferredSize(new Dimension(75, 75));
		blueButton.setEnabled(true);

		CircularGlossyButton grayButton = new CircularGlossyButton("Button",
				Theme.GLOSSY_GRAY_THEME);
		grayButton.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton metallicBlue = new CircularGlossyButton("Button",
				Theme.GLOSSY_METALIC_BLUE_THEME);
		metallicBlue.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton metallicGray = new CircularGlossyButton("Button",
				Theme.GLOSSY_METALIC_GRAY_THEME);
		metallicGray.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton orange = new CircularGlossyButton("Button",
				Theme.GLOSSY_ORANGE_THEME);
		orange.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton yellow = new CircularGlossyButton("Button",
				Theme.GLOSSY_YELLOW_THEME);
		yellow.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton darkyellow = new CircularGlossyButton("Button",
				Theme.GLOSSY_DARKYELLOW_THEME);
		darkyellow.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton brightBlue = new CircularGlossyButton("Button",
				Theme.GLOSSY_SKYBLUE_THEME);
		brightBlue.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossyLime = new CircularGlossyButton("Button",
				Theme.GLOSSY_LIME_THEME);
		glossyLime.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossyDarkGreen = new CircularGlossyButton(
				"Button", Theme.GLOSSY_DARKGREEN_THEME);
		glossyDarkGreen.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossyDarkRED = new CircularGlossyButton("Button",
				Theme.GLOSSY_DARKRED_THEME);
		glossyDarkRED.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossyNavyBlue = new CircularGlossyButton(
				"Button", Theme.GLOSSY_NAVYBLUE_THEME);
		glossyNavyBlue.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossyIndigo = new CircularGlossyButton("Button",
				Theme.GLOSSY_INDIGO_THEME);
		glossyIndigo.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossygold = new CircularGlossyButton("Button",
				Theme.GLOSSY_GOLD_THEME);
		glossygold.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton glossygeernyellow = new CircularGlossyButton(
				"Button", Theme.GLOSSY_GREENYELLOW_THEME);
		glossygeernyellow.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton darkPurple = new CircularGlossyButton("Button",
				Theme.GLOSSY_DARKPURPLE_THEME);
		darkPurple.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton lavender = new CircularGlossyButton("Button",
				Theme.GLOSSY_LAVENDER_THEME);
		lavender.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton darkPink = new CircularGlossyButton("Button",
				Theme.GLOSSY_DARKPINK_THEME);
		darkPink.setPreferredSize(new Dimension(75, 75));

		CircularGlossyButton palePink = new CircularGlossyButton("Button",
				Theme.GLOSSY_PALEPINK_THEME);
		palePink.setPreferredSize(new Dimension(75, 75));
		CircularGlossyButton brown = new CircularGlossyButton("Button",
				Theme.GLOSSY_BROWN_THEME);
		brown.setPreferredSize(new Dimension(75, 75));
		CircularGlossyButton lightBrown = new CircularGlossyButton("Button",
				Theme.GLOSSY_LIGHTBROWN_THEME);
		lightBrown.setPreferredSize(new Dimension(75, 75));
		CircularGlossyButton paleBrown = new CircularGlossyButton("Button",
				Theme.GLOSSY_PALEBROWN_THEME);
		paleBrown.setPreferredSize(new Dimension(75, 75));

		panel.add(glossyDarkGreen);
		panel.add(green);
		panel.add(lightGreen);
		panel.add(glossyLime);
		panel.add(darkyellow);
		panel.add(glossygeernyellow);

		panel.add(rollOverButton);
		panel.add(glossyDarkRED);
		panel.add(orange);
		panel.add(lightOrange);
		panel.add(glossygold);
		panel.add(yellow);
		panel.add(brown);
		panel.add(lightBrown);
		panel.add(paleBrown);

		panel.add(black);
		panel.add(grayButton);
		panel.add(metallicGray);

		panel.add(glossyNavyBlue);
		panel.add(glossyIndigo);
		panel.add(blueButton);
		panel.add(metallicBlue);
		panel.add(lightBlue);
		panel.add(brightBlue);

		panel.add(darkPurple);
		panel.add(voilet);
		panel.add(lavender);
		panel.add(pink);
		panel.add(darkPink);
		panel.add(palePink);

		return panel;

	}
}
