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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

import com.jd.swing.util.ColorUtils;
import com.jd.swing.util.Theme;

/**
 * 
 * @author Dhilshuk Reddy
 * 
 */
public class GradientButton extends JButton {

	private int buttonTheme = Theme.GRADIENT_SILVER_THEME;
	private int rollOverTheme = Theme.GRADIENT_RED_THEME;
	private int selectedTheme = Theme.GRADIENT_BLACK_THEME;;
	private String buttonType = ButtonType.BUTTON_ROUNDED_RECTANGLUR;
	private GradientPaint buttonColor = null;

	/**
	 * Constructor which sets label of the button.
	 * 
	 * @param text
	 *            label on the button
	 */
	public GradientButton(String text) {
		super(text);
		init();
	}

	/**
	 * Constructor which sets the label and theme for the button.
	 * 
	 * @param text
	 *            label on the button
	 * @param buttonTheme
	 *            button theme.
	 */
	public GradientButton(String text, int buttonTheme) {
		super(text);
		this.buttonTheme = buttonTheme;
		init();
	}

	/**
	 * Constructor which sets the label and type for the button.
	 * 
	 * @param text
	 *            label on the button
	 * @param buttonType
	 *            shape of the button
	 */
	public GradientButton(String text, String buttonType) {
		super(text);
		this.buttonType = buttonType;
		init();
	}

	/**
	 * Constructor which sets the label,type and theme for the button.
	 * 
	 * @param text
	 *            label on the button
	 * @param buttonType
	 *            shape of the button
	 * @param buttonTheme
	 *            theme of the button
	 */
	public GradientButton(String text, int buttonTheme, String buttonType) {
		super(text);
		this.buttonTheme = buttonTheme;
		this.buttonType = buttonType;
		init();
	}

	/**
	 * Constructor which sets the label,type,theme and roll-over theme for the
	 * button.
	 * 
	 * @param text
	 *            label on the button
	 * @param buttonType
	 *            shape of the button
	 * @param buttonTheme
	 *            theme of the button
	 * @param rolloverTheme
	 *            roll-over theme
	 */
	public GradientButton(String text, String buttonType, int buttonTheme,
			int rolloverTheme) {
		super(text);
		this.buttonType = buttonType;
		this.buttonTheme = buttonTheme;
		this.rollOverTheme = rolloverTheme;
		init();
	}

	/**
	 * Constructor which sets the label,type,theme ,roll-over and selected theme
	 * for the button.
	 * 
	 * @param text
	 *            label on the button
	 * @param buttonType
	 *            shape of the button
	 * @param buttonTheme
	 *            theme of the button
	 * @param rolloverTheme
	 *            roll-over theme
	 * @param selectedTheme
	 *            selected theme
	 */
	public GradientButton(String text, String buttonType, int buttonTheme,
			int rolloverTheme, int selectedTheme) {
		super(text);
		this.buttonTheme = buttonTheme;
		this.buttonType = buttonType;
		this.rollOverTheme = rolloverTheme;
		this.selectedTheme = selectedTheme;
		init();
	}

	/**
	 * Initializes
	 */
	private void init() {
		setFont(new Font("Thoma", Font.BOLD, 12));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight();
		int w = getWidth();
		int height = getHeight();
		ButtonModel model = getModel();
		if (model.isRollover()) {
			buttonColor = ColorUtils.getInStance().getGradientColor(
					this.rollOverTheme, height, this);

		} else {

			buttonColor = ColorUtils.getInStance().getGradientColor(
					this.buttonTheme, height, this);

		}
		if (model.isSelected() || model.isPressed()) {
			buttonColor = ColorUtils.getInStance().getGradientColor(
					this.selectedTheme, height, this);

		}
		drawShape(g2d, w, h);
		super.paintComponent(g);
		g2d.dispose();
	}

	/**
	 * Draws the shape.
	 * 
	 * @param g2d
	 *            2D Graphics object.
	 * @param w
	 *            width of the button
	 * @param h
	 *            height of the Button
	 */
	private void drawShape(Graphics2D g2d, int w, int h) {
		if (buttonType == ButtonType.BUTTON_ROUNDED_RECTANGLUR) {

			g2d.setPaint(buttonColor);
			g2d.fillRoundRect(0, 0, w, h, 10, 10);
			g2d.setPaint(new Color(100, 100, 100, 100));
			g2d.drawRoundRect(0, 0, w - 1, h - 1, 10, 10);
			g2d.setPaint(new Color(255, 255, 255, 50));
			g2d.drawRoundRect(1, 1, w - 3, h - 3, 10, 10);
		} else if (buttonType == ButtonType.BUTTON_RECTANGULAR) {
			g2d.setPaint(buttonColor);
			g2d.fillRect(1, 1, w - 2, h - 2);
			g2d.setPaint(new Color(100, 100, 100, 100));
			g2d.drawRect(0, 0, w - 1, h - 1);
		} else if (buttonType == ButtonType.BUTTON_OVAL) {
			g2d.setPaint(buttonColor);
			g2d.fillOval(1, 1, w - 20, h - 2);
			g2d.setPaint(new Color(100, 100, 100, 100));
			g2d.drawOval(0, 0, w - 20, h - 1);
		} else if (buttonType == ButtonType.BUTTON_ELLIPSE) {
			g2d.setPaint(buttonColor);
			Shape shape = new Ellipse2D.Double(1, 1, w - 2, h - 2);
			g2d.fill(shape);
			g2d.setPaint(new Color(100, 100, 100, 100));
			shape = new Ellipse2D.Double(0, 0, w - 1, h - 1);
			g2d.draw(shape);
		} else if (buttonType == ButtonType.BUTTON_CIRCULAR) {
			int size = Math.min(getWidth(), getHeight() - 2);
			g2d.setPaint(buttonColor);
			g2d.fillOval(2, 2, (size - 2 * 2), (size - 2 * 2));
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(new Color(100, 100, 100, 100));
			g2d.drawOval(2, 2, (size - 2 * 2), (size - 2 * 2));
		} else if (buttonType == ButtonType.BUTTON_ROUNDED) {
			g2d.setPaint(buttonColor);
			g2d.fillRoundRect(1, 1, w - 2, h - 2, h - 5, h - 5);
			g2d.setPaint(new Color(100, 100, 100, 100));
			g2d.drawRoundRect(0, 0, w - 1, h - 1, h - 3, h - 3);
		}
	}

	/**
	 * Draws Shadow.
	 * 
	 * @param g2
	 *            2D Graphics Object
	 * @param shadowWidth
	 *            width of shadow
	 * @param clipShape
	 *            shape
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
	 * Returns the Color.
	 * 
	 * @param c1
	 *            Color
	 * @param pct1
	 *            float value
	 * @param c2
	 *            Color
	 * @param pct2
	 *            float value
	 * @return Color
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
	 * Returns the Selected Theme.
	 * 
	 * @return selected theme
	 */
	public int getSelectedTheme() {
		return selectedTheme;
	}

	/**
	 * Sets the selected theme.
	 * 
	 * @param selectedTheme
	 *            theme when the button is selected
	 */
	public void setSelectedTheme(int selectedTheme) {
		this.selectedTheme = selectedTheme;
	}

}
