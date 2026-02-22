package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentForm extends JFrame {

    private JComboBox<String> passengerComboBox;
    private JTextField airlineField, flightNumberField, dateField, amountField;
    private JButton payButton;

    // ✅ Constructor used when called from BookingForm
    public PaymentForm(String passengerName, String airline, String flightNo, String travelDate, int amount) {
        setTitle("Make Payment");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Select Passenger:"));
        passengerComboBox = new JComboBox<>();
        passengerComboBox.addItem(passengerName);
        passengerComboBox.setEnabled(false); // ❌ Disable editing
        add(passengerComboBox);

        add(new JLabel("Airline:"));
        airlineField = new JTextField(airline);
        airlineField.setEditable(false);
        add(airlineField);

        add(new JLabel("Flight Number:"));
        flightNumberField = new JTextField(flightNo);
        flightNumberField.setEditable(false);
        add(flightNumberField);

        add(new JLabel("Travel Date:"));
        dateField = new JTextField(travelDate);
        dateField.setEditable(false);
        add(dateField);

        add(new JLabel("Amount:"));
        amountField = new JTextField(String.valueOf(amount));
        amountField.setEditable(false);
        add(amountField);

        payButton = new JButton("Pay");
        add(new JLabel()); // empty label
        add(payButton);

        addListeners(passengerName, amount); // Add payment logic

        setVisible(true);
    }

    // ✅ Default Constructor (for testing only)
    public PaymentForm() {
        setTitle("Make Payment");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Select Passenger:"));
        passengerComboBox = new JComboBox<>();
        add(passengerComboBox);

        add(new JLabel("Airline:"));
        airlineField = new JTextField();
        airlineField.setEditable(false);
        add(airlineField);

        add(new JLabel("Flight Number:"));
        flightNumberField = new JTextField();
        flightNumberField.setEditable(false);
        add(flightNumberField);

        add(new JLabel("Travel Date:"));
        dateField = new JTextField();
        dateField.setEditable(false);
        add(dateField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        amountField.setEditable(false);
        add(amountField);

        payButton = new JButton("Pay");
        add(new JLabel());
        add(payButton);

        loadPassengers(); // Load from booking table
        addDefaultListeners();

        setVisible(true);
    }

    // ✅ Add listener for main constructor
    private void addListeners(String passengerName, int amount) {
        payButton.addActionListener(e -> {
            String paymentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
                String insertQuery = "INSERT INTO payments (passenger_name, amount, payment_date) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, passengerName);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, paymentDate);

                pstmt.executeUpdate();

                // ✅ Show Razorpay/UPI window without early popup
                SimulatedPaymentWindow simulatedPayment = new SimulatedPaymentWindow();
                simulatedPayment.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error processing payment: " + ex.getMessage());
            }
        });
    }

    // ✅ For default constructor only
    private void loadPassengers() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
            String query = "SELECT DISTINCT passenger_name FROM booking";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                passengerComboBox.addItem(rs.getString("passenger_name").trim());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading passengers: " + e.getMessage());
        }
    }

    private void addDefaultListeners() {
        passengerComboBox.addActionListener(e -> loadBookingDetails());

        payButton.addActionListener(e -> {
            String passenger = passengerComboBox.getSelectedItem().toString().trim();
            String amount = amountField.getText();
            String paymentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            if (passenger.isEmpty() || amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a passenger with valid booking.");
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
                String insertQuery = "INSERT INTO payments (passenger_name, amount, payment_date) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, passenger);
                pstmt.setDouble(2, Double.parseDouble(amount));
                pstmt.setString(3, paymentDate);

                pstmt.executeUpdate();

                // ✅ Show Razorpay/UPI window without early popup
                SimulatedPaymentWindow simulatedPayment = new SimulatedPaymentWindow();
                simulatedPayment.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error processing payment: " + ex.getMessage());
            }
        });
    }

    private void loadBookingDetails() {
        String passenger = passengerComboBox.getSelectedItem().toString().trim();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230")) {
            String query = "SELECT * FROM booking WHERE TRIM(LOWER(passenger_name)) = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, passenger.toLowerCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                airlineField.setText(rs.getString("airline"));
                flightNumberField.setText(rs.getString("flight_number"));
                dateField.setText(rs.getString("travel_date"));

                String flightQuery = "SELECT price FROM flights WHERE flight_number = ?";
                PreparedStatement flightStmt = conn.prepareStatement(flightQuery);
                flightStmt.setString(1, rs.getString("flight_number"));
                ResultSet flightRs = flightStmt.executeQuery();

                if (flightRs.next()) {
                    amountField.setText(String.valueOf(flightRs.getDouble("price")));
                } else {
                    amountField.setText("");
                    JOptionPane.showMessageDialog(this, "No flight found for flight number " + rs.getString("flight_number"));
                }

            } else {
                airlineField.setText("");
                flightNumberField.setText("");
                dateField.setText("");
                amountField.setText("");
                JOptionPane.showMessageDialog(this, "No booking found for " + passenger);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading booking: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new PaymentForm(); // for test only
    }
}
