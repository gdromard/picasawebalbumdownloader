package net.dromard.picasaweb.albumdownloader.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import net.dromard.common.rss.RSSFeedReader;
import net.dromard.common.rss.feed.Enclosure;
import net.dromard.common.rss.feed.Item;
import net.dromard.common.rss.feed.RSS;
import net.dromard.picasaweb.albumdownloader.gui.JPicasaWebAlbumDownloader;
import net.dromard.picasaweb.albumdownloader.gui.JProgressBarGlassPane;
import net.dromard.picasaweb.albumdownloader.resources.Messages;

import org.xml.sax.SAXException;


public class PicasaWebAlbumDownloaderControler implements ActionListener, Runnable {
	private JPicasaWebAlbumDownloader frame;
	private JProgressBarGlassPane glassPane;
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(JPicasaWebAlbumDownloader.DOWNLOAD_ACTION)) {
			setGlassPane();
			new Thread(this).start();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			setGlassPane();
			glassPane.setString(Messages.getString("PicasaWebAlbumDownloaderControler.loading.feed"));
			glassPane.getProgressBar().setIndeterminate(true);
			RSSFeedReader reader = new RSSFeedReader();
			RSS rss = reader.load(new URL(frame.getURLFeed()));
			glassPane.setString(Messages.getString("PicasaWebAlbumDownloaderControler.downloading"));
			glassPane.getProgressBar().setStringPainted(true);
			glassPane.getProgressBar().setIndeterminate(false);
			glassPane.getProgressBar().setMaximum(rss.getChannel().getItems().size() + 1);
			glassPane.getProgressBar().setValue(0);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				for (Item item: rss.getChannel().getItems()) {
					glassPane.getProgressBar().setString(glassPane.getProgressBar().getValue() + "/" + rss.getChannel().getItems().size());
					glassPane.getProgressBar().setValue(glassPane.getProgressBar().getValue() + 1);
					Enclosure enclosure = item.getEnclosure();
					if (enclosure != null && enclosure.getType().equalsIgnoreCase("image/jpeg")) {
						download(enclosure.getUrl(), fileChooser.getSelectedFile() + File.separator + item.getTitle()); 
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			frame.setUrlFeed("");
			removeGlassPane();
		}
	}

	private void download(String url, String destinationFile) throws MalformedURLException, IOException {
		FileOutputStream outputStream = new FileOutputStream(destinationFile);
		InputStream inputStream = null;
		try {
			inputStream = new URL(url).openStream();
			streamCopier(inputStream, outputStream);
		} catch (IOException ex) {
			if (outputStream != null) outputStream.close();
			if (inputStream != null) inputStream.close();
			throw ex;
		}
	}

	public void setFrame(JPicasaWebAlbumDownloader frame) {
		this.frame = frame;
		glassPane = new JProgressBarGlassPane();
		glassPane.setSize(frame.getContentPane().getSize());
		glassPane.getProgressBar().setMinimum(0);
		frame.setGlassPane(glassPane);
	}
	
	private void setGlassPane() {
		frame.getGlassPane().setVisible(true);
	}
	
	private void removeGlassPane() {
		frame.getGlassPane().setVisible(false);
	}

    /** Buffer size. */
    private static final int BUFFER_SIZE = 1024;
    /**
     * This static method copy the input stream into the output stream.
     * @param in The InputStream, where to read data
     * @param out The Output stream, where to write data
     * @throws IOException Occurred if you did a mistake in the given parameters ...
     */
    private static void streamCopier(final InputStream in, final OutputStream out) throws IOException {
        int len;
        byte[] b = new byte[BUFFER_SIZE];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }
}
