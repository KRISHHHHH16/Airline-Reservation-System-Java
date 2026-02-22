package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    MainMenu() {
        setTitle("Airline Reservation System");
        setSize(400, 350);
        setLayout(new GridLayout(5, 1, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Main Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JButton btnRegister = new JButton("Register Passenger");
        JButton btnBook = new JButton("Book Flight");
        JButton btnPayment = new JButton("Make Payment");
        JButton btnGeneratePass = new JButton("Generate Boarding Pass");

        add(btnRegister);
        add(btnBook);
        add(btnPayment);
        add(btnGeneratePass);

        // ✅ Fixed button actions
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PassengerForm(); // Opening your register form
            }
        });

        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingForm(); // Flight booking form
            }
        });

        btnPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PaymentForm(); // Make payment form
            }
        });

        btnGeneratePass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GenerateBoardingPass(); // Generate boarding pass
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
