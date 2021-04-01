import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

/*
This app is an app where you could create decks of cards (divided to question cards and answer cards) and then you can play it
In this panel, we are going to create a panel with a menu and two buttons linked to moriCreateANewDeck and moriListofDecks
*/ 

public class moriHomepage {
	JFrame frame;
	JButton buildbutton; //build = create a new deck of cards
	JButton playbutton; //play = show list of all decks
	JMenuBar menubar;
	JMenu menu;
	JMenuItem build;
	JMenuItem play;
	JPanel playbuttonpanel;
	JPanel buildbuttonpanel;
	public static void main (String[] args){
		moriHomepage home = new moriHomepage();

	}
	
	public moriHomepage(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("MORI by LANDO CHAN");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar = new JMenuBar();
		menu = new JMenu("File");
		build = new JMenuItem("Build");
		buildbutton = new JButton("Build");
		buildbutton.setBackground(Color.red);
		buildbutton.setForeground(Color.white);
		buildbutton.setFont(new Font("Arial", Font.PLAIN, 20));
		play = new JMenuItem("Play");
		playbutton = new JButton("Play");
		playbutton.setBackground(Color.red);
		playbutton.setForeground(Color.white);
		playbutton.setFont(new Font("Arial", Font.PLAIN, 20));
		Background backgroundpanel = new Background();

		buildbuttonpanel = new JPanel();
		buildbuttonpanel.setOpaque(false);
		buildbuttonpanel.setBorder(BorderFactory.createEmptyBorder(150,0,0,0));
		playbuttonpanel = new JPanel();
		playbuttonpanel.setOpaque(false);
		playbuttonpanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		build.addActionListener(new CreateANewDeck());
		buildbutton.addActionListener(new CreateANewDeck());
		play.addActionListener(new ShowListofDecks());
		playbutton.addActionListener(new ShowListofDecks());
		
		backgroundpanel.add(buildbuttonpanel);
		backgroundpanel.add(playbuttonpanel);
		
		backgroundpanel.setLayout(new BoxLayout(backgroundpanel, BoxLayout.Y_AXIS));

		menubar.add(menu);
		menu.add(build);
		menu.add(play);

		frame.setJMenuBar(menubar);
		
		frame.getContentPane().add(BorderLayout.CENTER, backgroundpanel);
		buildbuttonpanel.add(buildbutton);
		playbuttonpanel.add(playbutton);
		
		frame.setSize(screen.width, screen.height);
		frame.setVisible(true);
	}

	class Background extends JPanel {
		public void paintComponent (Graphics g){
			BufferedImage image = null;
			File f = null;
			try{
				f = new File ("background.jpeg");
				image = new BufferedImage(1350, 740, BufferedImage.TYPE_INT_ARGB);
				image = ImageIO.read(f);
			} catch (IOException ex){System.out.print("caught"); ex.printStackTrace();}
			g.drawImage(image,0,0,1350,740,null);
		}
	}
	class CreateANewDeck implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriCreateANewDeck CreateANewDeck = new moriCreateANewDeck();
		}
	}
	class ShowListofDecks implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriListofDecks listofdecks = new moriListofDecks();
		}
	}
		
}

		
