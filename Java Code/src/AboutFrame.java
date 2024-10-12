import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutFrame extends JFrame {
    public AboutFrame() {
        JLabel aboutLbl = new JLabel();
        JButton closeBtn;

        JLabel iconLb = new JLabel();
        iconLb.setIcon(new ImageIcon("Music_Icon_For_About_ Frame.png")); // Setting image for the app to the label
        JPanel topPanel = new JPanel();
        topPanel.add(iconLb);
        this.add(topPanel, BorderLayout.PAGE_START);


        JPanel centerPanel = new JPanel();
        // Setting text to the about frame
        aboutLbl.setText("<html><h3>Songs information App</h3>" +
                "By Christos Bitas</h3><br>"
                + "A.M.:19390158<br> " +
                "<strong>Development date period: 02/06/2022 - 09/06/2022<br>" +
                "Version: 1.0<br>" +
                "Copyrights (c) 2022</strong></html>");
        centerPanel.add(aboutLbl);
        this.add(centerPanel, BorderLayout.CENTER);

        // Creating close button and action listener
        closeBtn = new JButton("Close");
        JPanel closebtnPanel = new JPanel();
        closebtnPanel.add(closeBtn);
        this.add(closebtnPanel, BorderLayout.PAGE_END);

        closeBtn.addActionListener(new ActionListener() { // Action Listener and Action Event for close button
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closing about window by clicking close button and return to the main window
            }
        });

        //setup the about frame
        this.setSize(350, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
