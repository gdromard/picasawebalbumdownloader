package net.dromard.picasaweb.albumdownloader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.dromard.picasaweb.albumdownloader.resources.Messages;


/**
 * @author Gabriel Dromard
 */
public class JPicasaWebAlbumDownloader extends JFrame {
	private static final long serialVersionUID = -7822874756818008107L;
	public static final String DOWNLOAD_ACTION = Messages.getString("JPicasaWebAlbumDownloader.actions.download");
	private JTextField textField;
	private JButton btnDownload;

	/**
	 * @param title The frame title.
	 * @param actionListener The action listener of the frame.
	 */
	public JPicasaWebAlbumDownloader() {
		super(Messages.getString("JPicasaWebAlbumDownloader.title"));

		// Initialization
		JLabel label = new JLabel(Messages.getString("JPicasaWebAlbumDownloader.label.rssurl"));
		textField = new JTextField();
		btnDownload = new JButton(Messages.getString("JPicasaWebAlbumDownloader.button.download"));
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		// Customization of components
		textField.setPreferredSize(new Dimension(500, 25));
		setLayout(new BorderLayout(5, 5));
		((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// GUI
		getContentPane().add(label, BorderLayout.NORTH);
		getContentPane().add(textField, BorderLayout.CENTER);
		btnPanel.add(btnDownload);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setControler(ActionListener actionListener) {
		// Behavior
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnDownload.setActionCommand(DOWNLOAD_ACTION);
		btnDownload.addActionListener(actionListener);
	}

	public String getURLFeed() {
		return textField.getText();
	}

	public void setUrlFeed(String url) {
		textField.setText(url);
	}
}
