import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

/*
In this panel, you are going to insert pairs of question and answer and until you press finish, the deck of cards wont be finished.
At the event you press finish, there are two things that is goint to happen. Firstly, your filename (the deck's name you input in moriCreateANewDeck) is going to be
added and written in "ListofDeck.txt". Secondly, a new txt file "filename.txt" is going to be created and your array of pairs of questions and answers is going to
be recorded there
*/
public class moriAddNewCards {
	JButton nextcardbutton;
	JButton finishbutton;
	JMenuBar menubar;
	JMenu menu;
	String filename;
	//QA = Question and Answer
	String QAinput;
	JFrame frame;
	JMenuItem home;
	JMenuItem play;
	//question panel
	JPanel qpanel;
	//answer panel
	JPanel apanel;
	JPanel bottompanel;
	JPanel qlabelpanel;
	JPanel alabelpanel;
	JPanel qtextareapanel;
	JPanel atextareapanel;
	JLabel qlabel;
	JLabel alabel;
	JTextArea qtextarea;
	JTextArea atextarea;
	public moriAddNewCards(String filenameinput){
		QAinput = new String();
		filename = new String();
		filename = filenameinput;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Add New Cards");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menubar = new JMenuBar();
		menu = new JMenu("File");
		home = new JMenuItem("Home");
		play = new JMenuItem("Play");
		qlabel = new JLabel("Question", JLabel.CENTER);
		alabel = new JLabel("Answer", JLabel.CENTER);
		qtextarea = new JTextArea(10,20);
		qtextarea.setLineWrap(true);
		atextarea = new JTextArea(10,20);
		atextarea.setLineWrap(true);
		qlabelpanel = new JPanel();
		qlabelpanel.setOpaque(false);	
		alabelpanel = new JPanel();
		alabelpanel.setOpaque(false);
		qtextareapanel = new JPanel();
		qtextareapanel.setOpaque(false);
		atextareapanel = new JPanel();
		atextareapanel.setOpaque(false);
	
		Background backgroundpanel = new Background();			

		qlabelpanel.add(qlabel);
		qtextareapanel.add(qtextarea);
		alabelpanel.add(alabel);
		atextareapanel.add(atextarea);
		backgroundpanel.setLayout(new BoxLayout(backgroundpanel, BoxLayout.Y_AXIS));
		qpanel = new JPanel();
		qpanel.setLayout(new BoxLayout(qpanel, BoxLayout.Y_AXIS));
		apanel = new JPanel();
		apanel.setLayout(new BoxLayout(apanel, BoxLayout.Y_AXIS));
		bottompanel = new JPanel();
		nextcardbutton = new JButton("Next Card");
		finishbutton = new JButton ("Finish");
		nextcardbutton.addActionListener(new saveaction());
		finishbutton.addActionListener(new finalcard());
	
		qpanel.add(qlabelpanel);
		qpanel.add(qtextareapanel);
		apanel.add(alabelpanel);
		apanel.add(atextareapanel);

		frame.getContentPane().add(BorderLayout.CENTER, backgroundpanel);
		backgroundpanel.add(qpanel);
		qpanel.setOpaque(false);
		backgroundpanel.add(apanel);
		apanel.setOpaque(false);
		backgroundpanel.add(bottompanel);
		bottompanel.setOpaque(false);
		bottompanel.add(nextcardbutton);
		bottompanel.add(finishbutton);

		home.addActionListener(new ShowHomepage());
		play.addActionListener(new ShowListofDecks());
		
		menubar.add(menu);
		menu.add(home);
		menu.add(play);

		frame.setJMenuBar(menubar);
		frame.setSize(screen.width, screen.height);
		frame.setVisible(true);
	}
	
	class saveaction implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			QAinput += qtextarea.getText() + "/" + atextarea.getText() + "/" + "\n";
			qtextarea.setText("");
			atextarea.setText("");
		}
	}
	class finalcard implements ActionListener {
		public void actionPerformed (ActionEvent ev){	
			//finalizing the array of questions and answers	
			QAinput += qtextarea.getText() + "/" + atextarea.getText() ;
			
			//creating a new filename and write the array of questions and answers there. This works for Japanese as well
			File filepath = new File(filename);
			try {
				OutputStream out = new FileOutputStream(filepath);
    				Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
				writer.write(QAinput);
				writer.close();
			} catch(IOException ex) { System.out.print("caught"); ex.printStackTrace(); }

			/*
			adding the filename to "ListofDecks.txt". This is done first by reading all of the data (all of filename that has been recorded previously)
			and then convert it to string and then you added your new filename to the string, and the last, you record the string to the file.
			*/
			try {	
				File targetfile = new File ("");
				if (targetfile.exists()){
					BufferedReader reader = new BufferedReader (new FileReader ("ListofDecks"));
					String message = null;
					String fullmessage = null;
					fullmessage = "";
					while ((message = reader.readLine()) != null ){
						fullmessage += message;
					}	
					reader.close();
					BufferedWriter writer = new BufferedWriter(new FileWriter (new File("ListofDecks")));
					writer.write(fullmessage + filename + "/");
					writer.close();
				}
				else {
					BufferedWriter writer = new BufferedWriter(new FileWriter (new File("ListofDecks")));
					writer.write(filename + "/");
					writer.close();
				}
			} catch (IOException excc) { System.out.print("caught"); excc.printStackTrace(); }
			frame.setVisible(false);
			moriHomepage mori = new moriHomepage();
		}
	}
	class Background extends JPanel {
		public void paintComponent (Graphics g){
			BufferedImage image = null;
			File f = null;
			try {
				f= new File("background.jpeg");
				image = new BufferedImage(1350,740,BufferedImage.TYPE_INT_ARGB);
				image = ImageIO.read(f);
			} catch( IOException e){System.out.print("caught"); e.printStackTrace();}
			g.drawImage(image,0,0,1350,740,null);
		}
	}
	class ShowListofDecks implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriListofDecks deckslist = new moriListofDecks();
		}
	}
	class ShowHomepage implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			frame.dispose();
			moriHomepage mori = new moriHomepage();
		}
	}
}

