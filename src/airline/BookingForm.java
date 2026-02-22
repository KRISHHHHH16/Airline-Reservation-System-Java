package airline;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class BookingForm extends JFrame {
    private JComboBox<String> sourceCombo, destinationCombo;
    private JDateChooser dateChooser;
    private JButton searchButton, showAllButton;
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private Connection conn;
    private String passengerName; // ✅ Added field to store selected passenger name

    // Default constructor
    public BookingForm() {
        setTitle("Search Flights");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(null);

        JLabel title = new JLabel("Find Flights", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(250, 10, 300, 30);
        add(title);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(50, 60, 100, 25);
        add(sourceLabel);

        sourceCombo = new JComboBox<>(getCities());
        sourceCombo.setBounds(120, 60, 150, 25);
        add(sourceCombo);

        JLabel destLabel = new JLabel("Destination:");
        destLabel.setBounds(300, 60, 100, 25);
        add(destLabel);

        destinationCombo = new JComboBox<>(getCities());
        destinationCombo.setBounds(400, 60, 150, 25);
        add(destinationCombo);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(580, 60, 50, 25);
        add(dateLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(630, 60, 150, 25);
        add(dateChooser);

        searchButton = new JButton("Search Flights");
        searchButton.setBounds(250, 100, 150, 30);
        searchButton.setBackground(new Color(0, 123, 255));
        searchButton.setForeground(Color.WHITE);
        add(searchButton);

        showAllButton = new JButton("Show All Flights on Date");
        showAllButton.setBounds(430, 100, 200, 30);
        showAllButton.setBackground(new Color(40, 167, 69));
        showAllButton.setForeground(Color.WHITE);
        add(showAllButton);

        String[] columns = {"Airline", "Logo", "Flight No", "Departure", "Arrival", "Price", "Book"};
        tableModel = new DefaultTableModel(null, columns) {
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }

            public Class<?> getColumnClass(int column) {
                return (column == 1) ? Icon.class : Object.class;
            }
        };

        flightTable = new JTable(tableModel);
        flightTable.setRowHeight(60);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(30, 150, 820, 320);
        add(scrollPane);

        setupDB();

        searchButton.addActionListener(e -> searchFlights());
        showAllButton.addActionListener(e -> showAllFlightsByDate());

        // BOOK button click handler
        flightTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = flightTable.rowAtPoint(e.getPoint());
                int col = flightTable.columnAtPoint(e.getPoint());

                if (col == 6) { // Book button
                    String airline = (String) tableModel.getValueAt(row, 0);
                    String flightNo = (String) tableModel.getValueAt(row, 2);
                    String travelDate = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
                    String priceStr = tableModel.getValueAt(row, 5).toString().replace("₹", "").trim();
                    int price = Integer.parseInt(priceStr);

                    // ✅ Use selected passenger name if available, else fallback
                    if (passengerName == null || passengerName.trim().isEmpty()) {
                        try {
                            PreparedStatement ps = conn.prepareStatement("SELECT name FROM passengers ORDER BY id DESC LIMIT 1");
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                passengerName = rs.getString("name");
                            } else {
                                JOptionPane.showMessageDialog(null, "No registered passengers found.");
                                return;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error fetching passenger.");
                            return;
                        }
                    }

                    try {
                        // Insert booking
                        String insert = "INSERT INTO booking (passenger_name, airline, flight_number, travel_date) VALUES (?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(insert);
                        ps.setString(1, passengerName.trim());
                        ps.setString(2, airline);
                        ps.setString(3, flightNo);
                        ps.setString(4, travelDate);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Flight booked successfully!");

                        // Redirect to PaymentForm
                        new PaymentForm(passengerName.trim(), airline, flightNo, travelDate, price);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Booking failed: " + ex.getMessage());
                    }
                }
            }
        });

        setVisible(true);
    }

    // ✅ New constructor to receive passenger name
    public BookingForm(String passengerName) {
        this(); // call default constructor
        this.passengerName = passengerName;
    }

    private String[] getCities() {
        return new String[]{"Delhi", "Mumbai", "Bangalore", "Hyderabad", "Kolkata", "Chennai", "Ahmedabad", "Pune"};
    }

    private void setupDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database not connected!");
        }
    }

    private void searchFlights() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = (dateChooser.getDate() != null) ? sdf.format(dateChooser.getDate()) : "";

        if (selectedDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a date.");
            return;
        }

        try {
            String sql = "SELECT * FROM flights WHERE source=? AND destination=? AND travel_date=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, (String) sourceCombo.getSelectedItem());
            ps.setString(2, (String) destinationCombo.getSelectedItem());
            ps.setString(3, selectedDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                addFlightToTable(rs);
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No flights found for the selected criteria.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching flights.");
        }
    }

    private void showAllFlightsByDate() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = (dateChooser.getDate() != null) ? sdf.format(dateChooser.getDate()) : "";

        if (selectedDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a date.");
            return;
        }

        try {
            String sql = "SELECT * FROM flights WHERE travel_date=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, selectedDate);
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                addFlightToTable(rs);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this, "No flights available on this date.");
            } else {
                JOptionPane.showMessageDialog(this, count + " flight(s) available on " + selectedDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching flights.");
        }
    }

    private void addFlightToTable(ResultSet rs) throws SQLException {
        String airline = rs.getString("airline");
        String flightNo = rs.getString("flight_number");
        String departure = rs.getString("departure_time");
        String arrival = rs.getString("arrival_time");
        String price = "₹" + rs.getInt("price");

        String logoPath = "images/" + airline.toLowerCase().replaceAll(" ", "") + ".png";
        ImageIcon logoIcon = new ImageIcon(new ImageIcon(logoPath).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        Object[] row = new Object[]{airline, logoIcon, flightNo, departure, arrival, price, "Book"};
        tableModel.addRow(row);
    }

    public static void main(String[] args) {
        new BookingForm(); // Default launch without passenger name
    }
}
