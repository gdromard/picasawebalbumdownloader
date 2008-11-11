package net.dromard.picasaweb.albumdownloader;

import net.dromard.picasaweb.albumdownloader.controler.PicasaWebAlbumDownloaderControler;
import net.dromard.picasaweb.albumdownloader.gui.JPicasaWebAlbumDownloader;

/**
 * Hello world!
 * 
 */
public class PicasaWebAlbumDownloader {
	public static void main(String[] args) {
		// http://picasaweb.google.fr/data/feed/base/user/laurentetsylvie75/albumid/5264008816855671777?alt=rss&kind=photo&authkey=OJ0rnRRHaLA&hl=fr
		JPicasaWebAlbumDownloader frame = new JPicasaWebAlbumDownloader();
		PicasaWebAlbumDownloaderControler controler = new PicasaWebAlbumDownloaderControler();
		frame.setControler(controler);
		controler.setFrame(frame);
		frame.setVisible(true);
	}
}
