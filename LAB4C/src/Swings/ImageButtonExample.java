package Swings;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageButtonExample extends JFrame implements ActionListener {

    JButton btnClock, btnHourGlass;
    JLabel lblMessage;

    public ImageButtonExample() {
        setTitle("Image Button Example");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ✅ Use simple file loading (NO getResource → avoids null error)
        ImageIcon clockIcon = new ImageIcon("clock.png");
        ImageIcon hourGlassIcon = new ImageIcon("hourglass.png");

        // ✅ Resize images for better UI
        Image img1 = clockIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image img2 = hourGlassIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        clockIcon = new ImageIcon(img1);
        hourGlassIcon = new ImageIcon(img2);

        // Buttons with images
        btnClock = new JButton(clockIcon);
        btnHourGlass = new JButton(hourGlassIcon);

        // Label
        lblMessage = new JLabel("Press a button");

        // Add listeners
        btnClock.addActionListener(this);
        btnHourGlass.addActionListener(this);

        // Add components
        add(btnClock);
        add(btnHourGlass);
        add(lblMessage);

        setVisible(true);
    }

    // Event handling
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClock) {
            lblMessage.setText("Digital Clock is pressed");
        } else if (e.getSource() == btnHourGlass) {
            lblMessage.setText("Hour Glass is pressed");
        }
    }

    public static void main(String[] args) {
        new ImageButtonExample();
    }
}