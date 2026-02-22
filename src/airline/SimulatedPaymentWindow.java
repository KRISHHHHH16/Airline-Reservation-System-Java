package airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimulatedPaymentWindow extends JFrame {

    private JComboBox<String> paymentModeComboBox;
    private JPanel dynamicFieldsPanel;
    private JButton payNowButton;

    private JTextField cardNumberField;
    private JTextField cvvField;

    public SimulatedPaymentWindow() {
        setTitle("Simulated Payment Gateway");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Select Payment Method", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Payment mode label and dropdown
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Payment Mode:"), gbc);

        paymentModeComboBox = new JComboBox<>(new String[] {
                "Card", "UPI", "EMI", "PayLater", "Internet Banking", "Referral Code"
        });
        gbc.gridx = 1;
        centerPanel.add(paymentModeComboBox, gbc);

        // Dynamic input panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        dynamicFieldsPanel = new JPanel(new GridBagLayout());
        centerPanel.add(dynamicFieldsPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Pay button
        payNowButton = new JButton("Pay Now");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(payNowButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        paymentModeComboBox.addActionListener(e -> updateDynamicFields(paymentModeComboBox.getSelectedItem().toString()));
        payNowButton.addActionListener(e -> showConfirmation());

        updateDynamicFields("Card"); // Initial fields
        pack(); // Resize to fit
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDynamicFields(String method) {
        dynamicFieldsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        switch (method) {
            case "Card":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("Card Type:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JComboBox<>(new String[]{"Debit", "Credit"}), gbc);

                gbc.gridx = 0; gbc.gridy++;
                dynamicFieldsPanel.add(new JLabel("Card Number:"), gbc);
                gbc.gridx = 1;
                cardNumberField = new JTextField(16);
                formatCardNumber(cardNumberField);
                dynamicFieldsPanel.add(cardNumberField, gbc);

                gbc.gridx = 0; gbc.gridy++;
                dynamicFieldsPanel.add(new JLabel("CVV:"), gbc);
                gbc.gridx = 1;
                cvvField = new JTextField(3);
                limitCVVInput(cvvField);
                dynamicFieldsPanel.add(cvvField, gbc);
                break;

            case "UPI":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("UPI ID:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JTextField(20), gbc);
                break;

            case "EMI":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("Bank:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JComboBox<>(new String[]{"HDFC", "ICICI", "SBI"}), gbc);

                gbc.gridx = 0; gbc.gridy++;
                dynamicFieldsPanel.add(new JLabel("Card Number:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JTextField(16), gbc);
                break;

            case "PayLater":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("Provider:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JComboBox<>(new String[]{"Simpl", "LazyPay", "ZestMoney"}), gbc);
                break;

            case "Internet Banking":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("Bank:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JComboBox<>(new String[]{"SBI", "HDFC", "Axis", "ICICI"}), gbc);
                break;

            case "Referral Code":
                gbc.gridx = 0; gbc.gridy = 0;
                dynamicFieldsPanel.add(new JLabel("Code:"), gbc);
                gbc.gridx = 1;
                dynamicFieldsPanel.add(new JTextField(15), gbc);
                break;
        }

        dynamicFieldsPanel.revalidate();
        dynamicFieldsPanel.repaint();
        pack(); // Resize the frame based on content
    }

    private void showConfirmation() {
        // Simulate the payment process
        JOptionPane.showMessageDialog(this, "Confirming Transaction...", "Processing", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, "Transaction Approved ✅", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Close the current Payment window
        this.dispose();

        // Redirect to the Boarding Pass page
        redirectToBoardingPass();
    }

    private void redirectToBoardingPass() {
        // Redirect to GenerateBoardingPass.java form
        GenerateBoardingPass boardingPassForm = new GenerateBoardingPass();
        boardingPassForm.setVisible(true);
    }

    private void formatCardNumber(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = field.getText().replaceAll("\\s", "");

                if (!Character.isDigit(c) || text.length() >= 16) {
                    e.consume();
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    String raw = field.getText().replaceAll("\\s", "");
                    StringBuilder formatted = new StringBuilder();
                    for (int i = 0; i < raw.length(); i++) {
                        if (i > 0 && i % 4 == 0) formatted.append(" ");
                        formatted.append(raw.charAt(i));
                    }
                    field.setText(formatted.toString());
                });
            }
        });
    }

    private void limitCVVInput(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || field.getText().length() >= 3) {
                    e.consume();
                }
            }
        });
    }

    public static void main(String[] args) {
        new SimulatedPaymentWindow();
    }
}
