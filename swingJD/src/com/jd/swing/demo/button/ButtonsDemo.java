package com.jd.swing.demo.button;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.jd.swing.custom.component.button.ButtonType;
import com.jd.swing.custom.component.button.CircularGlossyButton;
import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.custom.component.button.GradientButton;
import com.jd.swing.custom.component.button.StandardButton;
import com.jd.swing.util.ThemesList;

public class ButtonsDemo {

	private CardLayout cardLayout = new CardLayout();
	private JPanel cardLayoutPanel = new JPanel();

	public ButtonsDemo() {
		cardLayoutPanel.setLayout(cardLayout);
		cardLayoutPanel.add("Standard", createStandardButtons());
		cardLayoutPanel.add("Gradient", createGradientPanels());
		cardLayoutPanel.add("Glossy", createGlossyButtons());

	}

	public JTabbedPane createStandardButtons() {
		JTabbedPane pane = new JTabbedPane();
		JPanel rectMainPanel = new JPanel();
		JPanel roundRectMainPanel = new JPanel();
		JPanel roundMainPanel = new JPanel();
		JPanel circularMainPanel = new JPanel();
		rectMainPanel.setBackground(new Color(0, 0, 0));
		roundRectMainPanel.setBackground(new Color(0, 0, 0));
		roundMainPanel.setBackground(new Color(0, 0, 0));
		circularMainPanel.setBackground(new Color(0, 0, 0));

		List<Integer> themes = ThemesList.createStandardThemesList();
		for (Integer theme : themes) {
			StandardButton rectBtn = new StandardButton("Standard Button",
					theme, ButtonType.BUTTON_RECTANGULAR);
			StandardButton roundRectBtn = new StandardButton("Standard Button",
					theme, ButtonType.BUTTON_ROUNDED_RECTANGLUR);
			StandardButton roundBtn = new StandardButton("Standard Button",
					theme, ButtonType.BUTTON_ROUNDED);
			StandardButton circularBtn = new StandardButton(
					"<html>Circular<br> Button</html>", theme,
					ButtonType.BUTTON_CIRCULAR);

			rectBtn.setPreferredSize(new Dimension(130, 35));
			roundRectBtn.setPreferredSize(new Dimension(130, 35));
			roundBtn.setPreferredSize(new Dimension(130, 35));
			circularBtn.setPreferredSize(new Dimension(75, 75));
			rectMainPanel.add(rectBtn);
			roundRectMainPanel.add(roundRectBtn);
			roundMainPanel.add(roundBtn);
			circularMainPanel.add(circularBtn);

		}
		circularMainPanel.setPreferredSize(new Dimension(300, 1000));
		pane.add("Rectangular", rectMainPanel);
		pane.add("Rounded Rectangular", roundRectMainPanel);
		pane.add("Rounded", roundMainPanel);
		pane.add("Circular", new JScrollPane(circularMainPanel));

		return pane;
	}

	public JTabbedPane createGradientPanels() {
		JTabbedPane pane = new JTabbedPane();
		JPanel rectMainPanel = new JPanel();
		JPanel roundRectMainPanel = new JPanel();
		JPanel roundMainPanel = new JPanel();
		JPanel circularMainPanel = new JPanel();
		rectMainPanel.setBackground(new Color(0, 0, 0));
		roundRectMainPanel.setBackground(new Color(0, 0, 0));
		roundMainPanel.setBackground(new Color(0, 0, 0));
		circularMainPanel.setBackground(new Color(0, 0, 0));

		List<Integer> themes = ThemesList.createGradientThemesList();
		for (Integer theme : themes) {
			GradientButton rectBtn = new GradientButton("Gradient Button",
					theme, ButtonType.BUTTON_RECTANGULAR);
			GradientButton roundRectBtn = new GradientButton("Gradient Button",
					theme);
			GradientButton roundBtn = new GradientButton("Gradient Button",
					theme, ButtonType.BUTTON_ROUNDED);
			GradientButton circularBtn = new GradientButton(
					"<html>Circular<br> Button</html>", theme,
					ButtonType.BUTTON_CIRCULAR);

			rectBtn.setPreferredSize(new Dimension(130, 35));
			roundRectBtn.setPreferredSize(new Dimension(130, 35));
			roundBtn.setPreferredSize(new Dimension(130, 35));
			circularBtn.setPreferredSize(new Dimension(75, 75));
			rectMainPanel.add(rectBtn);
			roundRectMainPanel.add(roundRectBtn);
			roundMainPanel.add(roundBtn);
			circularMainPanel.add(circularBtn);

		}
		pane.add("Rectangular", rectMainPanel);
		pane.add("Rounded Rectangular", roundRectMainPanel);
		pane.add("Rounded", roundMainPanel);
		pane.add("Circular", circularMainPanel);

		return pane;

	}

	public JTabbedPane createGlossyButtons() {
		JTabbedPane pane = new JTabbedPane();
		JPanel rectMainPanel = new JPanel();
		JPanel roundRectMainPanel = new JPanel();
		JPanel roundMainPanel = new JPanel();
		JPanel circularMainPanel = new JPanel();
		rectMainPanel.setBackground(new Color(0, 0, 0));
		roundRectMainPanel.setBackground(new Color(0, 0, 0));
		roundMainPanel.setBackground(new Color(0, 0, 0));
		circularMainPanel.setBackground(new Color(0, 0, 0));

		List<Integer> themes = ThemesList.createThemesList();
		for (Integer theme : themes) {
			GlossyButton rectBtn = new GlossyButton("Gradient Button", theme,
					ButtonType.BUTTON_RECTANGULAR);
			GlossyButton roundRectBtn = new GlossyButton("Gradient Button",
					theme);
			GlossyButton roundBtn = new GlossyButton("Gradient Button", theme,
					ButtonType.BUTTON_ROUNDED);
			CircularGlossyButton circularBtn = new CircularGlossyButton(
					"<html>Circular<br> Button</html>", theme);

			rectBtn.setPreferredSize(new Dimension(130, 35));
			roundRectBtn.setPreferredSize(new Dimension(130, 35));
			roundBtn.setPreferredSize(new Dimension(130, 35));
			circularBtn.setPreferredSize(new Dimension(75, 75));
			rectMainPanel.add(rectBtn);
			roundRectMainPanel.add(roundRectBtn);
			roundMainPanel.add(roundBtn);
			circularMainPanel.add(circularBtn);

		}
		pane.add("Rectangular", rectMainPanel);
		pane.add("Rounded Rectangular", roundRectMainPanel);
		pane.add("Rounded", roundMainPanel);
		pane.add("Circular", circularMainPanel);

		return pane;
	}

	public JTabbedPane createSimpleGlossyPanels() {
		JTabbedPane pane = new JTabbedPane();
		JPanel rectMainPanel = new JPanel();
		JPanel roundRectMainPanel = new JPanel();

		List<Integer> themes = ThemesList.createThemesList();
		for (Integer theme : themes) {
			JPanel rectPnl = new JPanel();
			JPanel roundRectPnl = new JPanel();
			rectPnl.setPreferredSize(new Dimension(200, 100));
			roundRectPnl.setPreferredSize(new Dimension(200, 100));
			rectMainPanel.add(rectPnl);
			roundRectMainPanel.add(roundRectPnl);
		}
		pane.add("Rectangular", rectMainPanel);
		pane.add("Rounded Rectangular", roundRectMainPanel);

		return pane;
	}

	public JPanel getCardLayoutPanel() {
		return cardLayoutPanel;
	}

	public void setCardLayoutPanel(JPanel cardLayoutPanel) {
		this.cardLayoutPanel = cardLayoutPanel;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		final ButtonsDemo btnDemo = new ButtonsDemo();
		JFrame frame = new JFrame();
		frame.add(btnDemo.getCardLayoutPanel());

		frame.setSize(new Dimension(500, 500));
		frame.setVisible(true);
	}
}
