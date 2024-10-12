import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StatisticsFrame extends JFrame {
    private JLabel numOfSongsLb, maxSongsCategNameLb, minDurLb, maxDurLb;
    String line = null, idTemp = "", catgTemp = "", fileName = "Songs_information.txt", maxNameCateg = "", maxSongofCateg = "", durTemp = "", minTitl, maxTitl, titTemp = "";
    int numSongsRap = 0, numSonsgRnB = 0, numSongsMetal = 0, numSongsRock = 0, numSongsPop = 0, numSongsReggae = 0, numSongAlternative_music = 0,
            numSongsHipHop = 0, numSongsDisco = 0, numCtgMax = -1, id;
    double minDur, maxDur;

    public void StatisticsFrame() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        while (br.ready()) { // Reading the file and saving each line
            line = br.readLine();
            String[] tokens = line.split("\\s+"); // \\s+ means any number of whitespaces between words in text
            if (tokens.length == 6) {
                idTemp = tokens[0];
                titTemp = tokens[1];
                String rlsdatTemp = tokens[2];
                String artTemp = tokens[3];
                catgTemp = tokens[4];
                durTemp = tokens[5];
            }
            caclNumSongCateg(); // Calculating the number of songs that has each song category in order next to use it to function calcMaxSongsCateg to find the name of music category that has the most songs
            minMaxDurSong(); // Finding the song that has  Min/Max duration  from the file
        }
        maxSongsNameCateg(); // Finding the name of music category that has the most songs

        // Creating panels for each component
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();

        // Showing the Number of songs that are saved into the file
        if (!idTemp.equals("")) { // Checking if id has data
            id = Integer.parseInt(idTemp); // Converting id from String type to int type
            numOfSongsLb = new JLabel("Number of songs:\t " + idTemp);
            this.add(numOfSongsLb);
            p1.add(numOfSongsLb);
        }
        this.add(p1);

        // Showing name and songs from song category that has the most songs
        JLabel numOfSongsLb = new JLabel("Song category with most songs:");
        p2.add(numOfSongsLb);
        this.add(p2);
        if (numCtgMax > 0) { // Checking if max has number
            maxSongofCateg = Integer.toString(numCtgMax); // Converting maxSongofCateg from String type to int type
            maxSongsCategNameLb = new JLabel("Category name: " + maxNameCateg + ", Number of songs: " + maxSongofCateg);
            p3.add(maxSongsCategNameLb);
        }
        this.add(p3);

        // Showing name and the number of songs from song category that has the most songs
        JLabel ShrtDurLb = new JLabel("Shortest duration Song:");
        p4.add(ShrtDurLb);
        this.add(p4);
        minDurLb = new JLabel( "Name: " + minTitl + ", Duration: " + "Duration: " + Double.toString(minDur));
        p5.add(minDurLb);
        this.add(p5);

        // Showing
        JLabel longstDurLb = new JLabel("Longest duration Song:");
        maxDurLb = new JLabel("Name: " + maxTitl + ", Duration: " + Double.toString(maxDur));
        p6.add(longstDurLb);
        this.add(p6);
        p7.add(maxDurLb);
        this.add(p7);

        JButton closeBtn = new JButton("Close"); // Setting close button
        p8.add(closeBtn);
        this.add(closeBtn);

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closing about window by clicking close button and return to the main window
            }
        });


        // Creating and showing the frame for the music app
        this.setSize(325, 300); // Setting the size of the frame
        this.setLayout(new FlowLayout(FlowLayout.CENTER)); // Changing the default layout to FlowLayout
        this.setTitle("Songs statistic"); // Setting title to the frame
        this.setResizable(true); // Enabling the frame to be resizable
        this.setVisible(true); // Creating list for music categories
    }

    // Methods that implement the required functionality
    private void caclNumSongCateg() {// Calculating for every song category the number of songs
        if (catgTemp.equals("Rap")) {
            numSongsRap++;
        }
        if (catgTemp.equals("R&B")) {
            numSonsgRnB++;
        }
        if (catgTemp.equals("Metal")) {
            numSongsMetal++;
        }
        if (catgTemp.equals("Rock")) {
            numSongsRock++;
        }
        if (catgTemp.equals("Pop")) {
            numSongsPop++;
        }
        if (catgTemp.equals("Reggae")) {
            numSongsReggae++;
        }
        if (catgTemp.equals("AlternativeMusic")) {
            numSongAlternative_music++;
        }
        if (catgTemp.equals("Hip-hop")) {
            numSongsHipHop++;
        }
        if (catgTemp.equals("Disco")) {
            numSongsDisco++;
        }

    }

    private void maxSongsNameCateg() { // Finding the name, number of songs that music category has the most songs
        if (numSongsRap >= numCtgMax) {
            numCtgMax = numSongsRap;
            maxNameCateg = "Rap";
        }
        if (numSonsgRnB >= numCtgMax) {
            numCtgMax = numSonsgRnB;
            maxNameCateg = "R&B";
        }
        if (numSongsMetal >= numCtgMax) {
            numCtgMax = numSongsMetal;
            maxNameCateg = "Metal";
        }
        if (numSongsRock >= numCtgMax) {
            numCtgMax = numSongsRock;
            maxNameCateg = "Rock";
        }
        if (numSongsPop >= numCtgMax) {
            numCtgMax = numSongsPop;
            maxNameCateg = "Pop";
        }
        if (numSongsReggae >= numCtgMax) {
            numCtgMax = numSongsReggae;
            maxNameCateg = "Reggae";
        }
        if (numSongAlternative_music >= numCtgMax) {
            numCtgMax = numSongAlternative_music;
            maxNameCateg = "AlternativeMusic";
        }
        if (numSongsHipHop >= numCtgMax) {
            numCtgMax = numSongsHipHop;
            maxNameCateg = "Hip-hop";
        }
        if (numSongsDisco >= numCtgMax) {
            numCtgMax = numSongsDisco;
            maxNameCateg = "Disco";
        }
    }

    private void minMaxDurSong() { // Finding which song the min and max duration
        double dur = Double.parseDouble(durTemp);
        if (idTemp.equals("1")) {
            // Ιnitialization  min,max and title with the first elements of the first song information
            minDur = dur;
            maxDur = dur;
            minTitl = titTemp;
            maxTitl = titTemp;
        }

        // Finding max duration and title of song from the file
        if (dur > maxDur) {
            maxDur = dur;
            maxTitl = titTemp;
        }
        // Finding min duration and title of song from the file
        if (dur < minDur) {
            minDur = dur;
            minTitl = titTemp;
        }


    }

}
