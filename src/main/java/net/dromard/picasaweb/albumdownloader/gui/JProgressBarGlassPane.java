package net.dromard.picasaweb.albumdownloader.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JProgressBarGlassPane extends JPanel {
	private static final long serialVersionUID = 5100711800950519529L;
	private RenderingHints hints = null;
	private JProgressBar progressBar;
	private JLabel label;
	
	public JProgressBarGlassPane() {
		super(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		label = new JLabel();
		this.add(label);
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(300, 20));
		this.add(progressBar);
	}

	/**
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setString(String string) {
		label.setText(string);
	}

	public String setString() {
		return this.label.getText();
	}
}
