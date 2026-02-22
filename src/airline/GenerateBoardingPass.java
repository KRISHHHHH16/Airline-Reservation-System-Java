package airline;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class GenerateBoardingPass extends JFrame {
    private JTable bookingTable;

    public GenerateBoardingPass() {
        setTitle("Boarding Pass Generator");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Generate Boarding Pass", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        bookingTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate Boarding Pass");
        generateButton.addActionListener(e -> generateBoardingPass());
        add(generateButton, BorderLayout.SOUTH);

        loadBookings();
        setVisible(true);
    }

    private void loadBookings() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "191230");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, passenger_name, airline, flight_number, travel_date FROM booking")) {

            ResultSetMetaData meta = rs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = meta.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(meta.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            bookingTable.setModel(new DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage());
        }
    }

    private void generateBoardingPass() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to generate the pass.");
            return;
        }

        String passenger = bookingTable.getValueAt(selectedRow, 1).toString();
        String airline = bookingTable.getValueAt(selectedRow, 2).toString();
        String flight = bookingTable.getValueAt(selectedRow, 3).toString();
        String date = bookingTable.getValueAt(selectedRow, 4).toString();

        String details = "Passenger: " + passenger + "\nAirline: " + airline + "\nFlight: " + flight + "\nDate: " + date;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(details, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);

            BufferedImage logo = ImageIO.read(new File("images/" + airline.toLowerCase().replace(" ", "") + ".png"));

            int width = 500;
            int height = 250;
            BufferedImage boardingPass = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = boardingPass.createGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Boarding Pass", 20, 30);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("Passenger: " + passenger, 20, 60);
            g.drawString("Airline: " + airline, 20, 90);
            g.drawString("Flight No: " + flight, 20, 120);
            g.drawString("Travel Date: " + date, 20, 150);

            g.drawImage(qrImage, 300, 40, null);
            g.drawImage(logo, 20, 170, 100, 50, null);

            g.dispose();

            File output = new File("boarding_pass_" + passenger.replace(" ", "_") + ".png");
            ImageIO.write(boardingPass, "png", output);

            JOptionPane.showMessageDialog(this, "Boarding pass saved: " + output.getAbsolutePath());
            Desktop.getDesktop().open(output);
        } catch (WriterException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error generating QR code or logo: " + e.getMessage());
        }
    }
}
