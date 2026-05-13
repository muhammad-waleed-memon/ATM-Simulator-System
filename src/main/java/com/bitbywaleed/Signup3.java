package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Signup3 extends JFrame implements ActionListener {
    private JRadioButton r1, r2, r3, r4;
    private JCheckBox c1, c2, c3, c4, c5, c6;
    private JPasswordField pfPin, pfConfirmPin;  // Added PIN fields
    private JTextField tfCardno;
    private JButton b1, b2;
    private String formno;
    private Color primaryColor = new Color(0, 102, 204);

    Signup3(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBounds(0, 0, 900, 80);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("ACCOUNT & PIN SETUP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 25, 300, 30);
        headerPanel.add(titleLabel);

        JLabel formLabel = new JLabel("Form No: " + formno);
        formLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formLabel.setForeground(Color.WHITE);
        formLabel.setBounds(700, 30, 150, 30);
        headerPanel.add(formLabel);

        mainPanel.add(headerPanel);

        int y = 100;

        // Account Type Section
        JLabel l1 = new JLabel("Account Type:");
        l1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l1.setBounds(100, y, 150, 30);
        mainPanel.add(l1);
        y += 35;

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        r1.setBounds(100, y, 150, 25);
        r2.setBounds(280, y, 180, 25);
        r3.setBounds(500, y, 150, 25);
        r4.setBounds(680, y, 180, 25);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        mainPanel.add(r1);
        mainPanel.add(r2);
        mainPanel.add(r3);
        mainPanel.add(r4);

        y += 60;

        // Card Number (Auto-generated but shown to user)
        JLabel l3 = new JLabel("Card Number:");
        l3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l3.setBounds(100, y, 150, 30);
        mainPanel.add(l3);

        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
        String cardno = "" + Math.abs(first7);

        tfCardno = new JTextField(cardno);
        tfCardno.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tfCardno.setBounds(250, y, 250, 35);
        tfCardno.setEditable(false);  // User can't edit card number
        tfCardno.setBackground(new Color(240, 240, 240));
        mainPanel.add(tfCardno);

        y += 60;

        // PIN Section - USER ENTERS THEIR OWN PIN
        JLabel l6 = new JLabel("Set Your PIN (4 digits):");
        l6.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l6.setForeground(Color.RED);
        l6.setBounds(100, y, 200, 30);
        mainPanel.add(l6);

        pfPin = new JPasswordField();
        pfPin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        pfPin.setBounds(300, y, 150, 35);
        pfPin.setDocument(new JTextFieldLimit(4));  // Limit to 4 digits
        mainPanel.add(pfPin);

        y += 50;

        JLabel l7 = new JLabel("Confirm PIN:");
        l7.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l7.setBounds(100, y, 150, 30);
        mainPanel.add(l7);

        pfConfirmPin = new JPasswordField();
        pfConfirmPin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        pfConfirmPin.setBounds(300, y, 150, 35);
        pfConfirmPin.setDocument(new JTextFieldLimit(4));
        mainPanel.add(pfConfirmPin);

        y += 60;

        // Services Section
        JLabel l9 = new JLabel("Services Required:");
        l9.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l9.setBounds(100, y, 200, 30);
        mainPanel.add(l9);
        y += 35;

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("EMAIL Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");

        c1.setBounds(100, y, 120, 25);
        c2.setBounds(250, y, 150, 25);
        c3.setBounds(430, y, 150, 25);
        c4.setBounds(610, y, 120, 25);
        y += 35;
        c5.setBounds(100, y, 120, 25);
        c6.setBounds(250, y, 120, 25);

        mainPanel.add(c1);
        mainPanel.add(c2);
        mainPanel.add(c3);
        mainPanel.add(c4);
        mainPanel.add(c5);
        mainPanel.add(c6);

        y += 60;

        // Declaration
        JCheckBox c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        c7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        c7.setBackground(Color.WHITE);
        c7.setBounds(100, y, 700, 30);
        mainPanel.add(c7);

        y += 60;

        // Buttons
        b1 = new JButton("SUBMIT");
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setBackground(new Color(76, 175, 80));
        b1.setForeground(Color.WHITE);
        b1.setBounds(250, y, 150, 40);

        b2 = new JButton("CANCEL");
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        b2.setBounds(450, y, 150, 40);

        mainPanel.add(b1);
        mainPanel.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inner class to limit text field length
    class JTextFieldLimit extends javax.swing.text.PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }
        public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
            if (str == null) return;
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {  // SUBMIT button

                // Check account type
                String atype = null;
                if (r1.isSelected()) atype = "Saving Account";
                else if (r2.isSelected()) atype = "Fixed Deposit Account";
                else if (r3.isSelected()) atype = "Current Account";
                else if (r4.isSelected()) atype = "Recurring Deposit Account";

                if (atype == null) {
                    JOptionPane.showMessageDialog(this, "Please select an account type");
                    return;
                }

                // Get PIN from user
                String pin = new String(pfPin.getPassword()).trim();
                String confirmPin = new String(pfConfirmPin.getPassword()).trim();

                // Validate PIN
                if (pin.isEmpty() || confirmPin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your PIN");
                    return;
                }

                if (pin.length() != 4) {
                    JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits");
                    return;
                }

                if (!pin.equals(confirmPin)) {
                    JOptionPane.showMessageDialog(this, "PINs do not match!");
                    return;
                }

                // Check if PIN contains only digits
                if (!pin.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "PIN must contain only digits (0-9)");
                    return;
                }

                String cardno = tfCardno.getText();

                // Get services
                StringBuilder facility = new StringBuilder();
                if (c1.isSelected()) facility.append(" ATM Card");
                if (c2.isSelected()) facility.append(" Internet Banking");
                if (c3.isSelected()) facility.append(" Mobile Banking");
                if (c4.isSelected()) facility.append(" EMAIL Alerts");
                if (c5.isSelected()) facility.append(" Cheque Book");
                if (c6.isSelected()) facility.append(" E-Statement");

                // Save to database
                Conn dbConn = new Conn();

                // Insert into signup3
                String q1 = "INSERT INTO signup3 VALUES(?,?,?,?,?)";
                PreparedStatement pstmt = dbConn.prepareStatement(q1);
                pstmt.setString(1, formno);
                pstmt.setString(2, atype);
                pstmt.setString(3, cardno);
                pstmt.setString(4, pin);  // User's PIN, not random
                pstmt.setString(5, facility.toString());
                pstmt.executeUpdate();

                // Insert into login
                String q2 = "INSERT INTO login VALUES(?,?)";
                PreparedStatement pstmt2 = dbConn.prepareStatement(q2);
                pstmt2.setString(1, cardno);
                pstmt2.setString(2, pin);  // User's PIN
                pstmt2.executeUpdate();

                dbConn.close();

                // Show success message
                JOptionPane.showMessageDialog(this,
                        "✅ ACCOUNT CREATED SUCCESSFULLY!\n\n" +
                                "Card Number: " + cardno + "\n" +
                                "Your PIN: " + pin + "\n\n" +
                                "Please remember your PIN for login.");

                // Go to Login page
                dispose();
                new Login().setVisible(true);

            } else if (ae.getSource() == b2) {  // CANCEL button
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}