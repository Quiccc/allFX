package dxBallProject;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sounds {
	public static void playMusic(String locationMusic) {
		try {
			File musicLocation = new File(locationMusic);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void playMusicCont(String locationMusic) {
		try {
			File musicLocation = new File(locationMusic);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
