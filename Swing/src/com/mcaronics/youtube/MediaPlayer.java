package com.mcaronics.youtube;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MediaPlayer extends JPanel {

	public MediaPlayer(URL mediauUrl) {

		setLayout(new BorderLayout());

		try {

			Player mediaPlayer = Manager.createRealizedPlayer(new MediaLocator(mediauUrl));

			Component video = mediaPlayer.getVisualComponent();

			Component control = mediaPlayer.getControlPanelComponent();

			if (video != null) {

				add(video, BorderLayout.CENTER); // place the video component in
													// the panel

			}

			add(control, BorderLayout.SOUTH); // place the control in panel

			mediaPlayer.start();

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {

		URL mediaUrl = null;
		File file = new File("src/media/Jellyfish.mpg");
		try {

			mediaUrl = file.toURL();

		} catch (MalformedURLException ex) {

			System.out.println(ex);

		}

		JFrame mediaTest = new JFrame("Movie Player");

		mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MediaPlayer mediaPanel = new MediaPlayer(mediaUrl);

		mediaTest.add(mediaPanel);

		mediaTest.setSize(800, 700); // set the size of the player

		mediaTest.setLocationRelativeTo(null);

		mediaTest.setVisible(true);

	}

}