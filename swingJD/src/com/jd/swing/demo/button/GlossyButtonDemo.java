package com.jd.swing.demo.button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.util.Theme;

public class GlossyButtonDemo {
	public static void main(String args[]) {
		GlossyButtonDemo demo= new GlossyButtonDemo();
		JFrame frame = new JFrame("Custom Buttons Demo");
		//frame.setLayout(new FlowLayout());

		frame.add(demo.getButtonsPanel());
		// frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setSize(700, 85);
		frame.setVisible(true);
	}

	public JPanel getButtonsPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JScrollPane pane = new JScrollPane();
		pane.setBorder(null);
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(300, 300));

		List<Integer> themesList = createThemesList();
		for (Integer theme : themesList) {
			GlossyButton darkGreen_Btn = new GlossyButton("Glossy Button",
					theme);
			darkGreen_Btn.setPreferredSize(new Dimension(130, 35));
			panel.add(darkGreen_Btn);

		}
		
		/*GlossyButton blueGreen_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_BLUEGREEN_THEME);
		blueGreen_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton green_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_GREEN_THEME);
		green_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton lightGreen_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_LIGHTGREEN_THEME);
		lightGreen_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton standardButton = new GlossyButton("Glossy Button",
				Theme.GLOSSY_LIME_THEME);
		standardButton.setPreferredSize(new Dimension(130, 35));

		GlossyButton olive_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_OLIVEGREEN_THEME);
		olive_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton red_Btn = new GlossyButton("RollOver Button",
				Theme.GLOSSY_RED_THEME);
		red_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton darkRed_Btn = new GlossyButton("RollOver Button",
				Theme.GLOSSY_DARKRED_THEME);
		darkRed_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton orange_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_ORANGE_THEME);
		orange_Btn.setPreferredSize(new Dimension(130, 35));
		orange_Btn.setEnabled(true);

		GlossyButton lightOrange_Btn = new GlossyButton("GlossyButton",
				Theme.GLOSSY_LIGHTORANGE_THEME);
		lightOrange_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton darkYellow_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_DARKYELLOW_THEME);
		darkYellow_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton greenYellow_Btn = new GlossyButton("GlossyButton",
				Theme.GLOSSY_GREENYELLOW_THEME);
		greenYellow_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton goldYellow_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_GOLD_THEME);
		goldYellow_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton yellow_Btn = new GlossyButton("Yellow GlossyButton",
				Theme.GLOSSY_YELLOW_THEME);
		yellow_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton brown_Btn = new GlossyButton("GlossyButton",
				Theme.GLOSSY_BROWN_THEME);
		brown_Btn.setPreferredSize(new Dimension(130, 35));
		GlossyButton lightbrown_Btn = new GlossyButton("GlossyButton",
				Theme.GLOSSY_LIGHTBROWN_THEME);
		lightbrown_Btn.setPreferredSize(new Dimension(130, 35));
		GlossyButton palebrown_Btn = new GlossyButton("GlossyButton",
				Theme.GLOSSY_PALEBROWN_THEME);
		palebrown_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton navyblue_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_NAVYBLUE_THEME);
		navyblue_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton indigo_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_INDIGO_THEME);
		indigo_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton blue_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_BLUE_THEME);
		blue_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton skyBlue_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_SKYBLUE_THEME);
		skyBlue_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton lightBlue_Btn = new GlossyButton("Disable Button",
				Theme.GLOSSY_LIGHTBLUE_THEME);
		lightBlue_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton voilet_Btn = new GlossyButton("Disable Button",
				Theme.GLOSSY_VOILET_THEME);
		voilet_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton darkPurple_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_DARKPURPLE_THEME);
		darkPurple_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton purple_btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_PURPLE_THEME);
		purple_btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton lavendar_Btn = new GlossyButton("Standard Button",
				Theme.GLOSSY_LAVENDER_THEME);
		lavendar_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton darkPink_BTN = new GlossyButton("Glossy Button",
				Theme.GLOSSY_DARKPINK_THEME);
		darkPink_BTN.setPreferredSize(new Dimension(130, 35));

		GlossyButton pink_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_PINK_THEME);
		pink_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton palePink_btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_PALEPINK_THEME);
		palePink_btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton black_Btn = new GlossyButton("Pressed Button",
				Theme.GLOSSY_BLACK_THEME);
		black_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton gray_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_GRAY_THEME);
		gray_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton lightGray_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_LIGHTGRAY_THEME);
		lightGray_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton metallicGray_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_METALIC_GRAY_THEME);
		metallicGray_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton blueGray_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_BLUEGRAY_THEME);
		blueGray_Btn.setPreferredSize(new Dimension(130, 35));

		GlossyButton silver_Btn = new GlossyButton("Glossy Button",
				Theme.GLOSSY_MULTIRED_THEME);
		silver_Btn.setPreferredSize(new Dimension(130, 35));

		panel.add(darkGreen_Btn);
		panel.add(blueGreen_Btn);
		panel.add(green_Btn);
		panel.add(lightGreen_Btn);
		panel.add(olive_Btn);

		panel.add(standardButton);
		panel.add(red_Btn);
		panel.add(darkRed_Btn);
		panel.add(orange_Btn);
		panel.add(lightOrange_Btn);

		panel.add(darkYellow_Btn);
		panel.add(greenYellow_Btn);
		panel.add(goldYellow_Btn);
		panel.add(yellow_Btn);
		panel.add(brown_Btn);
		panel.add(lightbrown_Btn);
		panel.add(palebrown_Btn);

		panel.add(navyblue_Btn);
		panel.add(indigo_Btn);
		panel.add(blue_Btn);
		panel.add(skyBlue_Btn);
		panel.add(lightBlue_Btn);
		panel.add(voilet_Btn);

		panel.add(darkPurple_Btn);
		panel.add(purple_btn);
		panel.add(lavendar_Btn);

		panel.add(darkPink_BTN);
		panel.add(pink_Btn);
		panel.add(palePink_btn);
		panel.add(black_Btn);
		panel.add(gray_Btn);
		panel.add(lightGray_Btn);
		panel.add(metallicGray_Btn);
		panel.add(blueGray_Btn);
		panel.add(silver_Btn);*/
		
		pane.setViewportView(panel);
		mainPanel.add(pane);
		return mainPanel;

	}

	private ArrayList<Integer> createThemesList() {
		ArrayList<Integer> themesList = new ArrayList<Integer>();
		themesList.add(Theme.GLOSSY_SILVER_THEME);
		themesList.add(Theme.GLOSSY_DARKGREEN_THEME);
		themesList.add(Theme.GLOSSY_BLUEGREEN_THEME);
		themesList.add(Theme.GLOSSY_GREEN_THEME);
		themesList.add(Theme.GLOSSY_LIGHTGREEN_THEME);
		themesList.add(Theme.GLOSSY_LIME_THEME);
		themesList.add(Theme.GLOSSY_OLIVEGREEN_THEME);

		// RED THEME
		themesList.add(Theme.GLOSSY_RED_THEME);
		themesList.add(Theme.GLOSSY_DARKRED_THEME);

		// ORANGE THEME
		themesList.add(Theme.GLOSSY_ORANGE_THEME);
		themesList.add(Theme.GLOSSY_LIGHTORANGE_THEME);

		// YELLOW THEME
		themesList.add(Theme.GLOSSY_DARKYELLOW_THEME);
		themesList.add(Theme.GLOSSY_GREENYELLOW_THEME);
		themesList.add(Theme.GLOSSY_GOLD_THEME);
		themesList.add(Theme.GLOSSY_YELLOW_THEME);

		// BROWN THEME
		themesList.add(Theme.GLOSSY_BROWN_THEME);
		themesList.add(Theme.GLOSSY_LIGHTBROWN_THEME);
		themesList.add(Theme.GLOSSY_PALEBROWN_THEME);

		// BLACK THEME
		themesList.add(Theme.GLOSSY_BLACK_THEME);
		themesList.add(Theme.GLOSSY_GRAY_THEME);
		themesList.add(Theme.GLOSSY_LIGHTGRAY_THEME);
		themesList.add(Theme.GLOSSY_METALLICGRAY_THEME);
		themesList.add(Theme.GLOSSY_BLUEGRAY_THEME);

		// BLUE THEME
		themesList.add(Theme.GLOSSY_NAVYBLUE_THEME);
		themesList.add(Theme.GLOSSY_INDIGO_THEME);
		themesList.add(Theme.GLOSSY_BLUE_THEME);
		themesList.add(Theme.GLOSSY_SKYBLUE_THEME);
		themesList.add(Theme.GLOSSY_LIGHTBLUE_THEME);

		// PURPLE THEME
		themesList.add(Theme.GLOSSY_DARKPURPLE_THEME);
		themesList.add(Theme.GLOSSY_PURPLE_THEME);
		themesList.add(Theme.GLOSSY_LAVENDER_THEME);

		// PINK THEME
		themesList.add(Theme.GLOSSY_DARKPINK_THEME);
		themesList.add(Theme.GLOSSY_PINK_THEME);
		themesList.add(Theme.GLOSSY_PALEPINK_THEME);

		themesList.add(Theme.GLOSSY_METALIC_GRAY_THEME);
		themesList.add(Theme.GLOSSY_METALIC_BLUE_THEME);

		themesList.add(Theme.GLOSSY_VOILET_THEME);
		themesList.add(Theme.GLOSSY_ORANGERED_THEME);
		themesList.add(Theme.GLOSSY_BLUEBLACK_THEME);
		themesList.add(Theme.GLOSSY_GREENBLACK_THEME);
		themesList.add(Theme.GLOSSY_GOLDBLACK_THEME);
		themesList.add(Theme.GLOSSY_ORANGEBLACK_THEME);
		themesList.add(Theme.GLOSSY_MULTIBLUE_THEME);
		themesList.add(Theme.GLOSSY_MULTIBLUECOLOR_THEME);

		themesList.add(Theme.GLOSSY_MULTIDARKGREEN_THEME);
		themesList.add(Theme.GLOSSY_MULTIBLUEGREEN_THEME);
		themesList.add(Theme.GLOSSY_MULTIGREEN_THEME);
		themesList.add(Theme.GLOSSY_MULTILIGHTGREEN_THEME);
		themesList.add(Theme.GLOSSY_MULTILIME_THEME);
		themesList.add(Theme.GLOSSY_MULTIOLIVEGREEN_THEME);

		// RED THEME
		themesList.add(Theme.GLOSSY_MULTIRED_THEME);
		themesList.add(Theme.GLOSSY_MULTIDARKRED_THEME);

		// ORANGE THEME
		themesList.add(Theme.GLOSSY_MULTIORANGE_THEME);
		//themesList.add(Theme.GLOSSY_MULTILIGHTORANGE_THEME);
		// YELLOW THEME
		//themesList.add(Theme.GLOSSY_MULTIDARKYELLOW_THEME);
		//themesList.add(Theme.GLOSSY_MULTIGREENYELLOW_THEME);
		themesList.add(Theme.GLOSSY_MULTIGOLD_THEME);
		//themesList.add(Theme.GLOSSY_MULTIYELLOW_THEME);

		// BROWN THEME
		themesList.add(Theme.GLOSSY_MULTIBROWN_THEME);
		themesList.add(Theme.GLOSSY_MULTILIGHTBROWN_THEME);
		themesList.add(Theme.GLOSSY_MULTIPALEBROWN_THEME);

		// BLACK THEME
		themesList.add(Theme.GLOSSY_MULTIBLACK_THEME);
		themesList.add(Theme.GLOSSY_MULTIGRAY_THEME);
		themesList.add(Theme.GLOSSY_MULTILIGHTGRAY_THEME);
		//themesList.add(Theme.GLOSSY_MULTIMETALLICGRAY_THEME);
		themesList.add(Theme.GLOSSY_MULTIBLUEGRAY_THEME);

		// BLUE THEME
		themesList.add(Theme.GLOSSY_MULTINAVYBLUE_THEME);
		themesList.add(Theme.GLOSSY_MULTIINDIGO_THEME);
		themesList.add(Theme.GLOSSY_MULTISKYBLUE_THEME);
		themesList.add(Theme.GLOSSY_MULTILIGHTBLUE_THEME);

		// PURPLE THEME
		themesList.add(Theme.GLOSSY_MULTIDARKPURPLE_THEME);
		themesList.add(Theme.GLOSSY_MULTIPURPLE_THEME);
		themesList.add(Theme.GLOSSY_MULTILAVENDER_THEME);

		// PINK THEME
		themesList.add(Theme.GLOSSY_MULTIDARKPINK_THEME);
		themesList.add(Theme.GLOSSY_MULTIPINK_THEME);
		themesList.add(Theme.GLOSSY_MULTIPALEPINK_THEME);
		themesList.add(Theme.GLOSSY_MULTIVOILET_THEME);
		return themesList;
	}
}
