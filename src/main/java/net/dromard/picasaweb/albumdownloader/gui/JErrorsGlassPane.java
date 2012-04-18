package net.dromard.picasaweb.albumdownloader.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.dromard.picasaweb.albumdownloader.controler.PicasaWebAlbumDownloaderControler;
import net.dromard.picasaweb.albumdownloader.resources.Messages;

public class JErrorsGlassPane extends JPanel {
	private static final long serialVersionUID = -8261025270374424072L;
	private RenderingHints hints = null;
	private JLabel label;
	private JButton okBtn;
	
	public JErrorsGlassPane() {
		super(new BorderLayout(10, 10));
		this.hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		JScrollPane scrollpane = new JScrollPane();
		label = new JLabel();
		scrollpane.getViewport().add(label);
		this.add(scrollpane, BorderLayout.CENTER);

		JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		okBtn = new JButton(Messages.getString("JErrorsGlassPane.okBtn.text"));
		okBtn.setActionCommand(JPicasaWebAlbumDownloader.HIDE_GLASSPANE);
		okBtn.setAlignmentX(RIGHT_ALIGNMENT);
		btns.add(okBtn);
		this.add(btns, BorderLayout.SOUTH);
	}

	public void setString(String string) {
		label.setText(string);
	}

	public String getString() {
		return this.label.getText();
	}
	
	public void setControler(PicasaWebAlbumDownloaderControler controler) {
		okBtn.addActionListener(controler);
	}
}
