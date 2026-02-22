package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PassengerForm extends JFrame {

    private JComboBox<String> modeComboBox;
    private JComboBox<String> registeredPassengersComboBox;
    private JTextField nameField, ageField;
    private JComboBox<String> genderComboBox;
    private JButton submitButton;

    public PassengerForm() {
        setTitle("Register Passenger");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Mode selection
        add(new JLabel("Mode:"));
        modeComboBox = new JComboBox<>(new String[]{"New User", "Already Registered"});
        add(modeComboBox);

        // Already Registered Combo
        add(new JLabel("Select Passenger:"));
        registeredPassengersComboBox = new JComboBox<>();
        add(registeredPassengersComboBox);

        // Name
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        // Age
        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        // Gender
        add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        add(genderComboBox);

        // Submit button
        add(new JLabel());
        submitButton = new JButton("Submit");
        add(submitButton);

        // Initial state
        updateFormForMode("New User");
        loadRegisteredPassengers();

        // Events
        modeComboBox.addActionListener(e -> updateFormForMode((String) modeComboBox.getSelectedItem()));
        submitButton.addActionListener(e -> handleSubmit());

        setVisible(true);
    }

    private void updateFormForMode(String mode) {
        boolean isNew = mode.equals("New User");

        registeredPassengersComboBox.setEnabled(!isNew);
        nameField.setEnabled(isNew);
        ageField.setEnabled(isNew);
        genderComboBox.setEnabled(isNew);
    }

    private void loadRegisteredPassengers() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
            String query = "SELECT DISTINCT name FROM passengers"; // ✅ Fixed to avoid duplicates
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredPassengersComboBox.addItem(rs.getString("name").trim());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading passengers: " + e.getMessage());
        }
    }

    private void handleSubmit() {
        String mode = (String) modeComboBox.getSelectedItem();

        if (mode.equals("New User")) {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String gender = (String) genderComboBox.getSelectedItem();

            if (name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all details.");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
                    String insertQuery = "INSERT INTO passengers (name, age, gender) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);
                    pstmt.setString(3, gender);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Passenger registered successfully!");

                    registeredPassengersComboBox.addItem(name);

                    this.dispose();
                    new BookingForm(name);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age must be a valid number.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        } else {
            String selected = (String) registeredPassengersComboBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Welcome back, " + selected + "!");

            this.dispose();
            new BookingForm(selected);
        }
    }

    public static void main(String[] args) {
        new PassengerForm();
    }
}
