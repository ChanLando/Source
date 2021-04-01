import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
/*
In this panel, you could insert a new filename (deck's name) and when you press the createdeck button, it will link you to moriAddNewCards and also going to send the 
filename there. If the cards is created and you click finish at moriAddNewCards, then your filename is going to be written in "ListofDecks.txt" but if not, nothing
is going to be happening. That is why there is nothing much in here.
*/
public class moriCreateANewDeck {
	JLabel insertfilenamelabel;
	JTextField filename;
	JFrame frame;
	JPanel filepanel;
	JPanel inputpanel;
	JPanel buttonpanel;
	JButton createdeck;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem home;
	JMenuItem play;
	
	public moriCreateANewDeck() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Create A New Deck");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insertfilenamelabel = new JLabel("Insert the name for this class of cards you are going to create");
		menubar = new JMenuBar();
		menu = new JMenu("File");
		home = new JMenuItem("Home");
		play = new JMenuItem("Play");
		home.addActionListener(new ShowHomepage());
		play.addActionListener(new ShowListofDecks());
		menubar.add(menu);
		menu.add(home);
		menu.add(play);

		frame.setJMenuBar(menubar);
		createdeck = new JButton("Create A Deck");
		createdeck.addActionListener(new AddNewCards());
		filename = new JTextField(20);
		filepanel = new JPanel();
		inputpanel= new JPanel();
		inputpanel.setBorder(BorderFactory.createEmptyBorder(270,0,0,0));
		buttonpanel = new JPanel();
		buttonpanel.setBorder(BorderFactory.createEmptyBorder(0,0,270,0));
		filepanel.setLayout(new BoxLayout(filepanel, BoxLayout.Y_AXIS));
		filepanel.add(inputpanel);
		filepanel.add(buttonpanel);
		frame.getContentPane().add(BorderLayout.CENTER, filepanel);
		inputpanel.add(insertfilenamelabel);
		inputpanel.add(filename);
		buttonpanel.add(createdeck);
		frame.setSize(screen.width, screen.height);
		frame.setVisible(true);

	}
	class AddNewCards implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			String filenameoutput = filename.getText();
			moriAddNewCards AddNewCards = new moriAddNewCards(filenameoutput);
		}
	}
	class ShowListofDecks implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriListofDecks decklists = new moriListofDecks();
		}
	}
	class ShowHomepage implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriHomepage mori = new moriHomepage();
		}
	}
}
