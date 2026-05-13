package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Mini Statement Screen
 * Package: com.bitbywaleed
 */
public class MiniStatement extends JFrame implements ActionListener {
    String pin;

    MiniStatement(String pin) {
        this.pin = pin;
        setTitle("MINI STATEMENT");

        setLayout(null);

        JLabel bankName = new JLabel("ATM Simulator - bitbywaleed");
        bankName.setFont(new Font("System", Font.BOLD, 15));
        bankName.setBounds(120, 20, 300, 20);
        add(bankName);

        JLabel cardLabel = new JLabel();
        cardLabel.setBounds(20, 60, 300, 20);
        add(cardLabel);

        JLabel statementLabel = new JLabel();
        statementLabel.setBounds(20, 100, 360, 300);
        add(statementLabel);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 420, 300, 20);
        add(balanceLabel);

        // Fetch card number
        try {
            Conn c1 = new Conn();
            String q = "SELECT cardno FROM login WHERE pin = ?";
            PreparedStatement pstmt = c1.prepareStatement(q);
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String cardno = rs.getString("cardno");
                cardLabel.setText("Card Number: " + cardno.substring(0, 4) + "-XXXX-XXXX-" + cardno.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch transactions and calculate balance
        double balance = 0;
        StringBuilder sb = new StringBuilder("<html>");
        try {
            Conn c1 = new Conn();
            String q = "SELECT * FROM bank WHERE pin = ? ORDER BY date DESC LIMIT 5";
            PreparedStatement pstmt = c1.prepareStatement(q);
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                sb.append(date).append(" &nbsp; ")
                  .append(type).append(" &nbsp; Rs ")
                  .append(amount).append("<br>");

                double amt = Double.parseDouble(amount);
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("</html>");

        if (sb.toString().equals("<html></html>")) {
            statementLabel.setText("<html>No transactions found</html>");
        } else {
            statementLabel.setText(sb.toString());
        }

        balanceLabel.setText("Your total Balance is Rs " + balance);

        setSize(400, 500);
        setLocation(500, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // No buttons on this screen
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
