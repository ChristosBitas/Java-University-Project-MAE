import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class SongInsertFrame extends JFrame {

    String songTitle, releaseDate, artist, musicCategory, durationStr, songIdstr, idt = "0";
    boolean appendFlag;  // appendFlag is a flag to see if file has songs or not in order to append the song information or not
    boolean fileIsEmpty; // fileIsEmpty is a flag to see if file is empty or not in order to show to user the statistics or not
    int songId = 1;
    private JLabel idLb;
    private JLabel songTitleLb;
    private JLabel releaseDateLb;
    private JLabel durationLb;
    private JLabel artistLb;
    private JLabel musicCategoryLb;
    private JTextField songTitleTf;
    private JTextField releaseDateTf;
    private JTextField durationTf;
    private JTextField artistTf;
    private JComboBox musCategCmbox = new JComboBox();
    private JButton saveBtn;
    private JButton statisticsBtn;
    private JButton aboutBtn;
    private JButton exitBtn;
    private JMenuBar topMenuBar;
    private JMenu optionsMenu;
    private JMenuItem saveItem;
    private JMenuItem statisticsItem;
    private JMenuItem aboutItem;
    private JMenuItem exitItem;
    private ArrayList<Song> songsList = new ArrayList();


    public void SongInsertFrame() throws IOException {

        // Creating components

        // Creating menu bar with save,statistics,about and exit and adding it to the frame
        optionsMenu = new JMenu("Options");
        topMenuBar = new JMenuBar();
        saveItem = new JMenuItem("Save");
        statisticsItem = new JMenuItem("Statistics");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");
        optionsMenu.add(saveItem);
        optionsMenu.add(statisticsItem);
        optionsMenu.add(aboutItem);
        optionsMenu.add(exitItem);
        topMenuBar.add(optionsMenu);
        this.setJMenuBar(topMenuBar); // Setting menu bar to the frame

        String fileName = "Songs_information.txt"; // Setting file name to a variable

        // Reading the file in order to see if it has songs to get the lasted id song and start from it
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line = null;
        while (br.ready()) {
            line = br.readLine();
            String[] tokens = line.split("\\s+"); // \\s+ means any number of whitespaces between words in text
            if (tokens.length == 6) {
                String idTemp = tokens[0];
                idt = idTemp; // Setting latest id song from the file
                String titTemp = tokens[1];
                String rlsdatTemp = tokens[2];
                String artTemp = tokens[3];
                String catgTemp = tokens[4];
                String durTemp = tokens[5];
            }
        }
        // Checking to set to the frame the latest id of a song or the first
        if (idt.equals("0")) {
            idLb = new JLabel("Id:" + "1"); // Creating id label
            fileIsEmpty=true;
        } else {
            fileIsEmpty=false;
            songId = Integer.parseInt(idt); // Converting songId from str type to int
            songId++; // Auto increment the song id
            songIdstr = Integer.toString(songId); // Converting songId from int type to str type in order to print it to the frame
            idLb = new JLabel("Id:" + songIdstr); // Creating id label
        }

        // Creating label and textField for song Title
        songTitleLb = new JLabel("Song title:\t");
        songTitleTf = new JTextField(10);

        // Creating label and textField for release date of the song
        releaseDateLb = new JLabel("Release date:\t");
        releaseDateTf = new JTextField(10);

        // Creating label and textField for duration of the song
        durationLb = new JLabel("Duration:\t");
        durationTf = new JTextField(10);

        // Creating label and textField for artist name from the song
        artistLb = new JLabel("Artist:\t");
        artistTf = new JTextField(10);

        // Creating list for music categories
        musicCategoryLb = new JLabel("Category:\t");
        // Setting music category list with data
        musCategCmbox.addItem("Select  category");
        musCategCmbox.addItem("Rap");
        musCategCmbox.addItem("R&B");
        musCategCmbox.addItem("Metal");
        musCategCmbox.addItem("Rock");
        musCategCmbox.addItem("Pop");
        musCategCmbox.addItem("Reggae");
        musCategCmbox.addItem("AlternativeMusic ");
        musCategCmbox.addItem("Hip-hop");
        musCategCmbox.addItem("Disco");

        // Creating buttons for save,statistics,about,exit
        saveBtn = new JButton("Save");
        statisticsBtn = new JButton("Statistics");
        aboutBtn = new JButton("About");
        exitBtn = new JButton("Exit");

        // Creating panels and adding components
        JPanel topsongInfPanel = new JPanel();
        topsongInfPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topsongInfPanel.add(idLb);
        topsongInfPanel.add(songTitleLb);
        topsongInfPanel.add(songTitleTf);
        topsongInfPanel.add(releaseDateLb);
        topsongInfPanel.add(releaseDateTf);
        this.add(topsongInfPanel); // Adding panel to the frame

        JPanel centrSongInfPanel = new JPanel();
        centrSongInfPanel.add(artistLb);
        centrSongInfPanel.add(artistTf);
        centrSongInfPanel.add(musicCategoryLb);
        centrSongInfPanel.add(musCategCmbox);
        centrSongInfPanel.add(durationLb);
        centrSongInfPanel.add(durationTf);
        this.add(centrSongInfPanel); // Adding panel to the frame

        JPanel btnBotPanel = new JPanel();
        btnBotPanel.add(saveBtn);
        btnBotPanel.add(statisticsBtn);
        btnBotPanel.add(aboutBtn);
        btnBotPanel.add(exitBtn);
        this.add(btnBotPanel); // Adding panel to the frame

        // Creating listeners
        saveBtn.addActionListener(new ActionListener() { // Action Listener and Action Event for save button
            @Override
            public void actionPerformed(ActionEvent e) {
                savingSongConditions(); // Checking all conditions in order to save the song to the file
            }
        });
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingSongConditions(); // Checking all conditions in order to save the song to the file and then saving it or not
            }
        });
        statisticsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcStatistics(); // Calculating the statistics
            }
        });
        statisticsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcStatistics(); // Calculating the statistics

            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame af = new AboutFrame();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame af = new AboutFrame();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                exitApp();
            }
        });


        // Creating and showing the frame for the music app
        this.setSize(600, 200); // Setting the size of the frame
        this.setLayout(new FlowLayout(FlowLayout.CENTER)); // Changing the default layout to FlowLayout
        this.setTitle("Songs information app"); // Setting title to the frame
        this.setResizable(true); // Enabling the frame to be resizable
        this.setVisible(true); // Creating list for music categories

    }

    // Methods that implement the required functionality
    private void savingSongConditions() { // Checking all conditions in order to save the song to the file

        // Getting and saving text-fields and list value for the song information
        songTitle = songTitleTf.getText().trim();
        releaseDate = releaseDateTf.getText().trim();
        durationStr = durationTf.getText().trim();
        artist = artistTf.getText().trim();
        musicCategory = (String) musCategCmbox.getSelectedItem();

        String msg = ""; // Message to print which field or fields are empty
        // Checking if there were fields or field that was empty and if  nothing selected on the list
        if ((songTitle == null || songTitle.isEmpty()) || (releaseDate == null || releaseDate.isEmpty()) || (artist == null || artist.isEmpty()) ||
                (durationStr == null || durationStr.isEmpty()) || (musicCategory == null || musCategCmbox.getSelectedItem().equals("Select  category"))) {
            // Checking if all fields are empty and printing message with window
            if ((songTitle == null || songTitle.isEmpty()) && (releaseDate == null || releaseDate.isEmpty()) && (artist == null || artist.isEmpty()) &&
                    (durationStr == null || durationStr.isEmpty()) && (musicCategory == null || musCategCmbox.getSelectedItem().equals("Select  category"))) {
                msg = "All fields are empty";
            } else {  // Checking and printing the fields or field that is empty

                if (songTitle == null || songTitle.isEmpty()) {
                    msg = msg + "Title";
                }
                if (releaseDate == null || releaseDate.isEmpty()) {
                    if (!msg.equals("")) {
                        msg = msg + ", Release Date ";
                    } else {
                        msg = msg + "Release Date";
                    }
                }
                if (artist == null || artist.isEmpty()) {
                    if (!msg.equals("")) {
                        msg = msg + ", Artist";
                    } else {
                        msg = "Artist";
                    }
                }
                if (musicCategory == null || musCategCmbox.getSelectedItem().equals("Select  category")) {
                    if (!msg.equals("")) {
                        msg = msg + ", Music category";
                    } else {
                        msg = "Music category";
                    }
                }
                if (durationStr == null || durationStr.isEmpty()) {
                    if (!msg.equals("")) {
                        msg = msg + ", Duration";
                    } else {
                        msg = "Duration field";
                    }
                }
            }
            emptyFieldMsg(msg + " field(s) are empty");  // Showing window error message for empty fields or field to user
        } else { // User has filled all the fields and now is saving the song information to file name "Songs_information.txt"
            Song newSong = new Song(Integer.toString(songId), songTitle, artist, musicCategory, releaseDate, durationStr); // Creating object for song information
            songsList.add(newSong); // Saving all songs to the array list in order to hava a songs list with information for all songs
            try {
                saveSongToFile(); // Saving song information to file "Songs_information.txt"
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            clearFields(); // Clearing all fields after saving song information to the file
        }
    }

    private void clearFields() { // Clearing all fields after saving a song
        songTitleTf.setText("");
        releaseDateTf.setText("");
        durationTf.setText("");
        artistTf.setText("");
        musCategCmbox.setSelectedIndex(0);
    }

    private void emptyFieldMsg(String message) { // Window message to show to the user which field or fields are empty
        JOptionPane.showMessageDialog(
                SongInsertFrame.this,
                message,
                "Empty field(s) error",
                JOptionPane.WARNING_MESSAGE);
    }

    private void saveSongToFile() throws IOException { // Saving song information to file "Songs_information.txt"
        String fileName = "Songs_information.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line = null;
        boolean songExist = false, songFirst;

        while (br.ready()) { // Reading the file and saving each line
            line = br.readLine();
            String[] tokens = line.split("\\s+"); // \\s+ means any number of whitespaces between words in text
            if (tokens.length == 6) {
                String idTemp = tokens[0];
                idt = idTemp; // Getting the latest song id from the file
                String titTemp = tokens[1];
                String rlsdatTemp = tokens[2];
                String artTemp = tokens[3];
                String catgTemp = tokens[4];
                String durTemp = tokens[5];

                if (songTitle.equals(titTemp) && releaseDate.equals(rlsdatTemp) && artist.equals(artTemp)) { // Checking if title,release date and artist exists in the file in order not to save it
                    songExist = true; // Using a flag to see if  title,release date and artist exists in the file
                    String msg = "Title: " + songTitle + ", Release date: " + releaseDate + ", Artist: " + artist + " exists on file";
                    songExitMsg(msg); // Showing message dialog to the user which title,release date and artist exists on file
                }
            }
        }

        if (songExist == true) { // Checking if song information exist in file  to remove from song array list and not to be saved in the file
            songsList.remove(songsList.size() - 1); // If song information exist in file it will be removed from the songs array list with -1 which is for the last element
        } else { // Writing the first song or append the song to the file if it has already songs

            if (songId == 1) { // Checking if song id is 1 in order not to append the song information to the file
                appendFlag = false;
            } else {
                appendFlag = true;
            }

            FileWriter fileWrit = new FileWriter(fileName, appendFlag);
            BufferedWriter buffWrit = new BufferedWriter(fileWrit);
            buffWrit.write(songsList.get(songsList.size() - 1).toString()); // Writing the latest song information tha entered from user to the file
            buffWrit.newLine();
            buffWrit.close();
            // Checking to set the latest id song
            if (idt.equals("0")) {
                songId++; // Id for the song is auto implement by the program when saving new song
                songIdstr = Integer.toString(songId); // Converting songId from int type to str type in order to print it to the frame
                idLb.setText("Id:" + songIdstr); // Setting id of the song to the frame
            } else {
                songId++;
                songIdstr = Integer.toString(songId); // Converting songId from int type to str type in order to print it to the frame
                idLb.setText("Id:" + songIdstr); // Setting id of the song to the frame
            }
            JOptionPane.showMessageDialog( // Showing window message to the user of number of songs that has been saved to the file
                    SongInsertFrame.this,
                    songsList.size() + "  song(s) saved to " + fileName,
                    "Save completed",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calcStatistics() { // Calculating the statistics
        if (fileIsEmpty == true) { // Checking if file  is empty or not in order to show the statistics
            JOptionPane.showMessageDialog(
                    SongInsertFrame.this,
                    "               File is empty\nPlease insert data to the file ",
                    "Statistics error",
                    JOptionPane.WARNING_MESSAGE);
        } else { // If file is empty printing message to the user
            StatisticsFrame statFr = new StatisticsFrame();
            try {
                statFr.StatisticsFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void songExitMsg(String msg) { // Showing window message to the user if song title,release date and name of the artist exist in line of the file
        JOptionPane.showMessageDialog(
                SongInsertFrame.this,
                msg,
                "Song exists message",
                JOptionPane.WARNING_MESSAGE);
    }

    private void exitApp() { // Showing closing options and message if user want to close the app or if something from the fields hadn't been saved
        String msg;
        songTitle = songTitleTf.getText().trim();
        releaseDate = releaseDateTf.getText().trim();
        durationStr = durationTf.getText().trim();
        artist = artistTf.getText().trim();
        musicCategory = (String) musCategCmbox.getSelectedItem();
        // Checking if user has entered all song information and show him a message if he didn't save it or not
        if (!songTitle.isEmpty() || !releaseDate.isEmpty() || !durationStr.isEmpty() || !artist.isEmpty() && !songTitle.isEmpty() ||
                !musCategCmbox.getSelectedItem().equals("Select  category")) {
            msg = "Do you want to exit the app?\nSong information that you entered wasn't saved!";
        } else { // sho
            msg = "Do you want to exit the app?";
        }
        int i = JOptionPane.showConfirmDialog(null, msg);

        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            SongInsertFrame.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
}