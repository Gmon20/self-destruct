import java.awt.*;
import java.awt.event.*;  
import javax.swing.*; 
import java.util.Random;
import javax.swing.Timer;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.lang.NullPointerException;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//Making a window that "self descructs" because why not

public class selfDes extends JFrame {
	
	Timer timer;
	ImageIcon icon;
	JLabel label;
	JPanel panel;
	
	String name2;
	AudioInputStream ais2;
	Clip c2;
	
	public selfDes() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException {
		super("Notice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 200);
		//setLayout(null);
		
		//image
		icon = new ImageIcon(new ImageIcon("symbol.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		//jpanel
		panel = new JPanel();
		panel.setSize(500, 100);
		
		//jlabel. Text and image will be put together here
		label = new JLabel("This window will self destruct in 5 seconds.", icon, SwingConstants.HORIZONTAL);
		panel.add(label);
		add(panel);
		
		//audio
		name2 = "explosion.wav"; //for more "fun," replace with explosionLoud.wav
		ais2 = AudioSystem.getAudioInputStream(new File(name2).getAbsoluteFile());
		c2 = AudioSystem.getClip();
		
		//timer
		timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println("Playing clip");
					c2.start();
					c2.open(ais2);
					c2.loop(Clip.LOOP_CONTINUOUSLY);
					Thread.sleep(c2.getMicrosecondLength()/1000); //this makes sure the thread is still going so that the clip can actually play
					c2.close();
					//System.out.println("Done");
				}
				catch(Exception ex) {
					System.out.println(ex);
					System.exit(0);
				}
				dispose(); //close window
			}
		});
		timer.setRepeats(false);
		timer.start();
		
		//setVisible needs to be at the end, otherwide the panel wouldn't show up until I resized the window
		setVisible(true);
		pack();
	}
	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException {
		new selfDes();
	}
}