package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Balance Enquiry Screen
 * Package: com.bitbywaleed
 */
public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton b1;
    JLabel l1;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;
        setTitle("BALANCE ENQUIRY");

        l1 = new JLabel();
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");
        b1.setFont(new Font("System", Font.BOLD, 16));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        setLayout(null);

        l1.setBounds(150, 200, 400, 35);
        add(l1);

        b1.setBounds(250, 300, 150, 35);
        add(b1);

        double balance = 0;
        try {
            Conn c1 = new Conn();
            String q = "SELECT type, amount FROM bank WHERE pin = ?";
            PreparedStatement pstmt = c1.prepareStatement(q);
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                double amt = Double.parseDouble(rs.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        l1.setText("Your Current Account Balance is Rs " + balance);

        b1.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 600);
        setLocation(500, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
