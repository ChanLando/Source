import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.border.*;

/*
In this panel, all deck of cards ever created is going to be shown based on "ListofDecks.txt". We use reader to split filenames (names of decks) saved in "ListofDecks.txt"
into array of String and give them a play button each from the array of buttons. The play button is linked to moriPlaytheSelectedDeck, which also going to send the filename that the button connected.
*/

public class moriListofDecks {	
	JMenuBar menubar;
	JMenu menu;
	JMenuItem build;
	JMenuItem home;
	ArrayList<JButton> playbuttonslist;
	JFrame frame;
	JScrollPane scroller;
	JPanel mainpanel;
	JPanel backgroundpanel;
	JPanel leftpanel;
	JPanel rightpanel;
	JLabel decknamelabel;
	LineBorder borderline;
	JButton playbutton;
	ArrayList<String> decksnamelist;
	public moriListofDecks(){
		menubar = new JMenuBar();
		menu = new JMenu("File");
		build = new JMenuItem("build");
		home = new JMenuItem("home");
		menubar.add(menu);
		menu.add(home);
		menu.add(build);
		home.addActionListener(new ShowHomepage());
		build.addActionListener(new CreateANewDeck());

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame= new JFrame("List of Decks");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playbuttonslist = new ArrayList<JButton>();
		leftpanel = new JPanel();
		rightpanel = new JPanel();
		backgroundpanel = new JPanel();

		leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
		rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));

		//dechiper "ListofDecks.txt" and create an Array of filenames / deck's name
		decksnamelist = new ArrayList<String>();
		try { 	BufferedReader reader = new BufferedReader(new FileReader(new File("ListofDecks")));
			String readresult = null;
			while((readresult = reader.readLine())!= null){
				String[] fullreadresult = readresult.split("/");
				for(int i=0; i<fullreadresult.length ; i++){
					decksnamelist.add(fullreadresult[i]);
				}
			}
		} catch (IOException ex){System.out.println("caught you"); ex.printStackTrace();}

		GridLayout grid = new GridLayout(decksnamelist.size(), 2);
		mainpanel = new JPanel(grid);
		borderline = new LineBorder(Color.white);

		mainpanel.setBorder(borderline);
		backgroundpanel.setBorder(borderline);
		frame.getContentPane().add(BorderLayout.NORTH, backgroundpanel);
		
		frame.getContentPane().setBackground(Color.white);
		mainpanel.setBackground(Color.white);
		backgroundpanel.setBackground(Color.white);
		backgroundpanel.setBorder(BorderFactory.createEmptyBorder(0,300,0,300));
		
		
		scroller = new JScrollPane(mainpanel);
		scroller.setBorder(borderline);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		backgroundpanel.add(scroller);
		
		//appoint one button linked to moriPlattheSelectedDeck to each filename while also send that filename as an output
		for (int i=0; i< decksnamelist.size(); i++) {
			decknamelabel = new JLabel(decksnamelist.get(i), JLabel.CENTER);
			mainpanel.add(decknamelabel);
			decknamelabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,40));
			playbutton = new JButton("PLAY");
			String deckname = new String();
			deckname = decksnamelist.get(i);
			playbuttonslist.add(playbutton);
			//This action is connected to moriPlaytheSelectedDeck
			playbuttonaction play = new playbuttonaction();
			play.setfilename(deckname);
			playbuttonslist.get(i).addActionListener(play);
			mainpanel.add(playbuttonslist.get(i));
		}
		frame.setJMenuBar(menubar);
		frame.setSize(screen.width, screen.height);
		frame.setVisible(true);		
	}		
	class CreateANewDeck implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriCreateANewDeck CreateANewDeck = new moriCreateANewDeck ();
		}
	}
	class ShowHomepage implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriHomepage homepage = new moriHomepage();
		}
	}
	//when you click the button, this is what happens. It is going to create a new moriPlaytheSelectedDick with a selected filename as its input
	class playbuttonaction implements ActionListener {
		private String result;
		public void setfilename (String input) {
			result = input;
		}
		public void actionPerformed(ActionEvent ev) {
			frame.dispose();
			moriPlaytheSelectedDeck play = new moriPlaytheSelectedDeck(result);
		}
	}
}			

		
		
		
		
