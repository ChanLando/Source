import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files.*;
import java.lang.Object;
import java.nio.file.*;

/* 
This panel is going to read "filename.txt" and split the sentences inside the file into array of questions and answers. Then, one by one quetion-answer-question-answer
card is going to be shown each time you click the next button,
*/

public class moriPlaytheSelectedDeck {
	static int cardindex; //question card has even number cardindex while answer card has odd number cardindex
	static int unplayedcardnumbers;
	String[] listofallcards;
	JLabel QAdisplaylabel;
	JTextArea QAdisplay;
	JButton nextbutton;
	JFrame frame;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem home;
	JMenuItem build;
	JMenuItem list;
	JPanel backgroundpanel;
	JPanel QApanel; 
	//QA=Questions and Answers
	JPanel nextbuttonpanel;

	public moriPlaytheSelectedDeck(String input){
		cardindex =0;
		QAdisplay = new JTextArea(10,20);
		QAdisplay.setEditable(false);
		QAdisplaylabel = new JLabel("Question :");
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Play the Selected Deck");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		menubar = new JMenuBar();
		menu = new JMenu("File");
		home = new JMenuItem("Home");
		build = new JMenuItem("Build");
		list = new JMenuItem("List");
	
		backgroundpanel = new JPanel();
		QApanel = new JPanel();
		QApanel.add(QAdisplaylabel);
		QApanel.add(QAdisplay);
		nextbuttonpanel = new JPanel();
		nextbutton = new JButton("Answer");
		nextbutton.addActionListener(new ShowNextCard());

		/*
		Getting an array of questions and answers from the file as well as showing the first card from the deck as well as counting the cardnumbers.
		Because the first card has been shown, automatically unplayedcardnumbers is decreasing by one while the card index is increasing by one so the next card
		which appear is the next card with the next index card.
		*/
		File filepath = new File(input);
		try{
			Path path = FileSystems.getDefault().getPath(filepath.getAbsolutePath());
     			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String readresult = null;
			String fullreadresult = new String("");
			while((readresult = reader.readLine()) != null) {
				fullreadresult += readresult;
			}
			reader.close();
			listofallcards = fullreadresult.split("/");
		}catch (IOException exc){exc.printStackTrace();}
		QAdisplay.setText(listofallcards[cardindex]);
		cardindex++;
		unplayedcardnumbers = (listofallcards.length) -1;
		

		frame.getContentPane().add(BorderLayout.CENTER, backgroundpanel);
		backgroundpanel.add(QApanel);
		backgroundpanel.add(nextbuttonpanel);
		nextbuttonpanel.add(nextbutton);

		home.addActionListener(new ShowHomepage());
		build.addActionListener(new CreateANewDeck());
		
		menubar.add(menu);
		menu.add(home);
		menu.add(build);

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
			moriHomepage mori = new moriHomepage();
		}
	}
	//creating environment for the next card
	class ShowNextCard implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			//if there is still card unplayed in the deck then this function works, if not show finish page
			if(unplayedcardnumbers >0){
				//if the current cardindex is even number (question) then the next card is answer card
				//if the current cardindex is odd number (answer) then the next card in question card
				if((cardindex%2)==0){
					QAdisplaylabel.setText("Question :");
					nextbutton.setText("Answer");
				}
				if((cardindex%2)!= 0){
					QAdisplaylabel.setText("Answer :");
					nextbutton.setText("Next Question");
				}
				QAdisplay.setText(listofallcards[cardindex]);
				cardindex++;
				unplayedcardnumbers--;
			}
			else {
			QAdisplaylabel.setText("FINISH");
			QAdisplaylabel.setFont(new Font("Arial", Font.PLAIN, 30));
			QAdisplay.setText("");
			QAdisplay.setVisible(false);
			nextbutton.setVisible(false);
			}
		}
	}			
}
